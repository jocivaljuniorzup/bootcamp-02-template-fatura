package br.com.zup.jocivaldias.cardstatement.controller;

import br.com.zup.jocivaldias.cardstatement.dto.response.CardBalanceResponse;
import br.com.zup.jocivaldias.cardstatement.dto.response.CardStatementResponse;
import br.com.zup.jocivaldias.cardstatement.entity.Card;
import br.com.zup.jocivaldias.cardstatement.entity.CardStatement;
import br.com.zup.jocivaldias.cardstatement.exception.ApiErrorException;
import br.com.zup.jocivaldias.cardstatement.repository.CardRepository;
import br.com.zup.jocivaldias.cardstatement.repository.CardStatementRepository;
import br.com.zup.jocivaldias.cardstatement.service.CardControlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/cards/")
public class CardStatementController {

    private CardStatementRepository cardStatementRepository;
    private CardRepository cardRepository;

    private CardControlService cardControlService;

    public CardStatementController(CardStatementRepository cardStatementRepository,
                                   CardRepository cardRepository,
                                   CardControlService cardControlService) {
        this.cardStatementRepository = cardStatementRepository;
        this.cardRepository = cardRepository;
        this.cardControlService = cardControlService;
    }

    @GetMapping(path = "/{cardId}/statements")
    public ResponseEntity<?> cardStatementDetails(@PathVariable("cardId") UUID cardId){

        Optional<CardStatement> optionalCardStatement = cardStatementRepository.findOneByCardIdOrderByEndDateDesc(cardId);

        CardStatement cardStatement = optionalCardStatement.orElseThrow(() -> {
            throw new ApiErrorException(HttpStatus.NOT_FOUND, "Card not found");
        });

        CardStatementResponse cardStatementResponse = CardStatementResponse.fromModel(cardStatement);
        return ResponseEntity.ok(cardStatementResponse);
    }

    @GetMapping(path = "/{cardId}/balances")
    public ResponseEntity<?> cardBalance(@PathVariable("cardId") UUID cardId){

        Optional<Card> optionalCard = cardRepository.findById(cardId);
        Card card = optionalCard.orElseThrow(() -> {
            throw new ApiErrorException(HttpStatus.NOT_FOUND, "Card not found");
        });

        boolean success = cardControlService.getCardLimit(card);

        if(!success){
            throw new ApiErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Error processing the requisition");
        }

        List<CardStatement> unpaidCardStatements = cardStatementRepository.findAllByCardIdAndPaid(cardId, false);

        BigDecimal balance = unpaidCardStatements.stream()
                .map(cardStatement -> cardStatement.calculateTotalValue())
                .reduce(card.getLimit(), BigDecimal::subtract);

        return ResponseEntity.ok(new CardBalanceResponse(balance));
    }

}
