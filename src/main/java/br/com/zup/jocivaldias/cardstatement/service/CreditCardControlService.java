package br.com.zup.jocivaldias.cardstatement.service;


import br.com.zup.jocivaldias.cardstatement.dto.response.CardResponse;
import br.com.zup.jocivaldias.cardstatement.entity.Card;
import br.com.zup.jocivaldias.cardstatement.entity.CardStatement;
import br.com.zup.jocivaldias.cardstatement.entity.Transaction;
import br.com.zup.jocivaldias.cardstatement.entity.enums.CardStatus;
import br.com.zup.jocivaldias.cardstatement.repository.CardRepository;
import br.com.zup.jocivaldias.cardstatement.repository.CardStatementRepository;
import br.com.zup.jocivaldias.cardstatement.repository.TransactionRepository;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class CreditCardControlService {

    private CreditCardControl creditCardControl;
    private CardRepository cardRepository;
    private TransactionRepository transactionRepository;
    private CardStatementRepository cardStatementRepository;

    private final TransactionTemplate transactionTemplate;

    private Logger logger = LoggerFactory.getLogger(CreditCardControlService.class);

    public CreditCardControlService(CreditCardControl creditCardControl,
                                    CardRepository cardRepository,
                                    TransactionRepository transactionRepository,
                                    CardStatementRepository cardStatementRepository,
                                    TransactionTemplate transactionTemplate) {
        this.creditCardControl = creditCardControl;
        this.cardRepository = cardRepository;
        this.transactionRepository = transactionRepository;
        this.cardStatementRepository = cardStatementRepository;
        this.transactionTemplate = transactionTemplate;
    }

    @Scheduled(fixedDelay = 5000)
    protected void getCreditCardInfo(){
        List<Card> cardsWithoutInfo = cardRepository.findAllByStatus(CardStatus.UNPROCESSED,
                PageRequest.of(0, 100));

        for (Card card : cardsWithoutInfo) {
            try {
                CardResponse cardResponse = creditCardControl.getCreditCard(card.getCardNumber());
                card.setStatus(CardStatus.PROCESSED);
                card.setLimit(cardResponse.getLimit());
                card.setDueDay(cardResponse.getCardDueResponse().getDay());

                transactionTemplate.execute(status -> {
                    cardRepository.save(card);
                    return true;
                });
            } catch (FeignException exception){
                logger.error("Error in Credit Card API - Status code: {}, Body: {}, Message: {}",
                        exception.status(),
                        exception.contentUTF8(),
                        exception.getMessage());
            }
        }
    }

    @Scheduled(fixedDelay = 5000)
    protected void processTransactions(){
        Pageable pageable = PageRequest.of(0, 100, Sort.by(Sort.Direction.DESC, "effectiveOn"));
        List<Transaction> unprocessedTransactions = transactionRepository.findAllByCardStatementAndCardStatus(null,
                CardStatus.PROCESSED,
                pageable);

        for(Transaction transaction : unprocessedTransactions){
            Card card = transaction.getCard();

            Optional<CardStatement> optionalCardStatement =
                    cardStatementRepository.findTop1ByEndDateAfterAndCardOrderByEndDateAsc(transaction.getEffectiveOn().toLocalDate(), card);

            CardStatement cardStatement = optionalCardStatement.orElse(null);
            if(cardStatement == null){
                Integer cardDueDay = card.getDueDay();
                int lengthOfMonth = transaction.getEffectiveOn().toLocalDate().lengthOfMonth();
                int month = transaction.getEffectiveOn().toLocalDate().getMonthValue();
                int year = transaction.getEffectiveOn().toLocalDate().getYear();

                // Generate the due day and last purchase day of statement
                int lastDayOfStatement = cardDueDay > lengthOfMonth ? lengthOfMonth - 5 : cardDueDay - 5;
                int dueDayOfStatement = cardDueDay > lengthOfMonth ? lengthOfMonth : cardDueDay;

                LocalDate endDate = LocalDate.of(year, month, lastDayOfStatement);
                LocalDate dueDate = LocalDate.of(year, month, dueDayOfStatement);

                cardStatement = new CardStatement(card, endDate, dueDate);
                cardStatementRepository.save(cardStatement);
            }

            transaction.setCardStatement(cardStatement);
            transactionRepository.save(transaction);
        }

    }

}
