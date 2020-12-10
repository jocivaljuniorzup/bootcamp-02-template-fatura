package br.com.zup.jocivaldias.cardstatement.dto.response;

import br.com.zup.jocivaldias.cardstatement.entity.Transaction;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CardStatementTransactionResponse {

    private String id;
    private BigDecimal value;
    private LocalDateTime effectiveOn;
    @JsonProperty("store")
    private CardStatementTransactionStoreResponse cardStatementTransactionStoreResponse;

    public CardStatementTransactionResponse(String id,
                                            BigDecimal value,
                                            LocalDateTime effectiveOn,
                                            CardStatementTransactionStoreResponse cardStatementTransactionStoreResponse) {
        this.id = id;
        this.value = value;
        this.effectiveOn = effectiveOn;
        this.cardStatementTransactionStoreResponse = cardStatementTransactionStoreResponse;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public LocalDateTime getEffectiveOn() {
        return effectiveOn;
    }

    public CardStatementTransactionStoreResponse getCardStatementTransactionStoreResponse() {
        return cardStatementTransactionStoreResponse;
    }

    public static CardStatementTransactionResponse fromModel(Transaction transaction) {
        CardStatementTransactionStoreResponse store = new CardStatementTransactionStoreResponse(transaction.getStoreName(),
                transaction.getStoreCity(),
                transaction.getStoreAddress());

        return new CardStatementTransactionResponse(transaction.getId(),
                transaction.getValue(),
                transaction.getEffectiveOn(),
                store);
    }
}
