package br.com.zup.jocivaldias.cardstatement.dto.event;

import br.com.zup.jocivaldias.cardstatement.dto.request.TransactionCardRequest;
import br.com.zup.jocivaldias.cardstatement.dto.request.TransactionStoreRequest;
import br.com.zup.jocivaldias.cardstatement.entity.Card;
import br.com.zup.jocivaldias.cardstatement.entity.Transaction;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionEvent {

    private String id;

    @JsonProperty("valor")
    private BigDecimal value;

    @JsonProperty("estabelecimento")
    private TransactionStoreRequest transactionStoreRequest;

    @JsonProperty("cartao")
    private TransactionCardRequest cardRequest;

    @JsonProperty("efetivadaEm")
    private LocalDateTime effectiveOn;

    public TransactionEvent(String id,
                            BigDecimal value,
                            TransactionStoreRequest transactionStoreRequest,
                            TransactionCardRequest cardRequest,
                            LocalDateTime effectiveOn) {
        this.id = id;
        this.value = value;
        this.transactionStoreRequest = transactionStoreRequest;
        this.cardRequest = cardRequest;
        this.effectiveOn = effectiveOn;
    }

    public TransactionEvent() {
    }

    public TransactionCardRequest getCardRequest() {
        return cardRequest;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public void setTransactionStoreRequest(TransactionStoreRequest transactionStoreRequest) {
        this.transactionStoreRequest = transactionStoreRequest;
    }

    public void setCardRequest(TransactionCardRequest cardRequest) {
        this.cardRequest = cardRequest;
    }

    public void setEffectiveOn(LocalDateTime effectiveOn) {
        this.effectiveOn = effectiveOn;
    }

    public Transaction toModel(Card card){
        return new Transaction(id,
                value,
                effectiveOn,
                transactionStoreRequest.getName(),
                transactionStoreRequest.getCity(),
                transactionStoreRequest.getAddress(),
                card,
                null);
    }

    @Override
    public String toString() {
        return "TransactionEvent{" +
                "id='" + id + '\'' +
                ", value=" + value +
                ", establishmentRequest=" + transactionStoreRequest +
                ", cardRequest=" + cardRequest +
                ", effectiveOn=" + effectiveOn +
                '}';
    }
}
