package br.com.zup.jocivaldias.cardstatement.dto.response;

import br.com.zup.jocivaldias.cardstatement.entity.CardStatement;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class CardStatementResponse {

    private UUID id;

    @JsonProperty("end_date")
    private LocalDate endDate;

    @JsonProperty("due_date")
    private LocalDate dueDate;

    @JsonProperty("total_value")
    private BigDecimal totalValue;

    @JsonProperty("credit_card")
    private CardStatementCardResponse card;

    @JsonProperty("transactions")
    private Set<CardStatementTransactionResponse> transactions;

    public CardStatementResponse(UUID id,
                                 CardStatementCardResponse card,
                                 LocalDate endDate,
                                 LocalDate dueDate,
                                 BigDecimal totalValue,
                                 Set<CardStatementTransactionResponse> transactions) {
        this.id = id;
        this.card = card;
        this.endDate = endDate;
        this.dueDate = dueDate;
        this.totalValue = totalValue;
        this.transactions = transactions;
    }

    public UUID getId() {
        return id;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public CardStatementCardResponse getCard() {
        return card;
    }

    public Set<CardStatementTransactionResponse> getTransactions() {
        return transactions;
    }

    public static CardStatementResponse fromModel(CardStatement cardStatement) {
        CardStatementCardResponse cardStatementCardResponse = CardStatementCardResponse.fromModel(cardStatement.getCard());

        Set<CardStatementTransactionResponse> cardStatementTransactionResponses = cardStatement.getTransactions()
                .stream()
                .map(CardStatementTransactionResponse::fromModel)
                .collect(Collectors.toSet());

        return new CardStatementResponse(cardStatement.getId(),
                cardStatementCardResponse,
                cardStatement.getEndDate(),
                cardStatement.getDueDate(),
                cardStatement.calculateTotalValue(),
                cardStatementTransactionResponses);
    }
}
