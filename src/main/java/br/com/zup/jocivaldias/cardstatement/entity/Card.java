package br.com.zup.jocivaldias.cardstatement.entity;

import br.com.zup.jocivaldias.cardstatement.entity.enums.CardStatus;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "binary(16)")
    private UUID id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String cardNumber;

    @NotNull
    @Column(nullable = false)
    private String email;

    @Min(0)
    @Column(name = "card_limit")
    private BigDecimal limit;

    @Min(0)
    @Max(31)
    private Integer dueDay;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CardStatus status;

    @OneToMany(mappedBy = "card", fetch = FetchType.LAZY)
    private Set<CardStatement> cardStatements = new HashSet<>();

    @Deprecated
    private Card() {
    }

    public Card(@NotNull String cardNumber,
                @NotNull String email,
                @NotNull CardStatus status) {
        this.cardNumber = cardNumber;
        this.email = email;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setLimit(BigDecimal limit) {
        this.limit = limit;
    }

    public void setDueDay(Integer dueDay) {
        this.dueDay = dueDay;
    }

    public void setStatus(CardStatus status) {
        this.status = status;
    }

    public Integer getDueDay() {
        return dueDay;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public Set<CardStatement> getCardStatements() {
        return cardStatements;
    }

    public void addStatement(CardStatement cardStatement){
        this.cardStatements.add(cardStatement);
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
