package br.com.zup.jocivaldias.cardstatement.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @OneToMany(mappedBy = "cardStatement")
    private Set<Transaction> transactions;

    @Deprecated
    private CardStatement() {
    }

    public CardStatement(Card card,
                         @NotNull LocalDate endDate,
                         @NotNull LocalDate dueDate) {
        this.card = card;
        this.endDate = endDate;
        this.dueDate = dueDate;
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
}
