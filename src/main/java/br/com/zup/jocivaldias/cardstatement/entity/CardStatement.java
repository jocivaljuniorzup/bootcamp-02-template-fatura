package br.com.zup.jocivaldias.cardstatement.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
public class CardStatement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "binary(16)")
    private UUID id;

    @ManyToOne(optional = false)
    private Card card;

    @NotNull
    @Column(nullable = false)
    private LocalDate endDate;

    @NotNull
    @Column(nullable = false)
    private LocalDate dueDate;

    @NotNull
    @Column(nullable = false)
    private Boolean paid;

    @OneToMany(mappedBy = "cardStatement")
    private Set<Transaction> transactions;

    @Deprecated
    private CardStatement() {
    }

    public CardStatement(Card card, @NotNull LocalDate endDate, @NotNull LocalDate dueDate, @NotNull Boolean paid) {
        this.card = card;
        this.endDate = endDate;
        this.dueDate = dueDate;
        this.paid = paid;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public Card getCard() {
        return card;
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

    public Boolean getPaid() {
        return paid;
    }

    @Override
    public String toString() {
        return "CardStatement{" +
                "id=" + id +
                ", cardId=" + card +
                ", endDate=" + endDate +
                ", dueDate=" + dueDate +
                ", transactions=" + transactions +
                '}';
    }

    public BigDecimal calculateTotalValue() {
        return transactions.stream()
                .map(x -> x.getValue())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
