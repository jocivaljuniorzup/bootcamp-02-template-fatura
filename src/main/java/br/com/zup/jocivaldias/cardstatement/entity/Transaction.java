package br.com.zup.jocivaldias.cardstatement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Transaction {

    @NotBlank
    @Id
    private String id;

    @NotNull
    @Column(nullable = false)
    private BigDecimal value;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime effectiveOn;

    @NotBlank
    @Column(nullable = false)
    private String storeName;

    @NotBlank
    @Column(nullable = false)
    private String storeCity;

    @NotBlank
    @Column(nullable = false)
    private String storeAddress;

    @NotNull
    @ManyToOne(optional = false)
    private Card card;

    @ManyToOne(optional = true)
    private CardStatement cardStatement;

    @Deprecated
    private Transaction() {
    }

    public Transaction(@NotBlank String id,
                       @NotNull BigDecimal value,
                       @NotNull LocalDateTime effectiveOn,
                       @NotBlank String storeName,
                       @NotBlank String storeCity,
                       @NotBlank String storeAddress,
                       @NotNull Card card,
                       CardStatement cardStatement) {
        this.id = id;
        this.value = value;
        this.effectiveOn = effectiveOn;
        this.storeName = storeName;
        this.storeCity = storeCity;
        this.storeAddress = storeAddress;
        this.card = card;
        this.cardStatement = cardStatement;
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

    public String getStoreName() {
        return storeName;
    }

    public String getStoreCity() {
        return storeCity;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public CardStatement getCardStatement() {
        return cardStatement;
    }

    public Card getCard() {
        return card;
    }

    public void setCardStatement(CardStatement cardStatement) {
        this.cardStatement = cardStatement;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", value=" + value +
                ", effectiveOn=" + effectiveOn +
                ", storeName='" + storeName + '\'' +
                ", storeCity='" + storeCity + '\'' +
                ", storeAddress='" + storeAddress + '\'' +
                ", card=" + card +
                ", cardStatement=" + cardStatement +
                '}';
    }
}
