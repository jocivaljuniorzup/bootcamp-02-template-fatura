package br.com.zup.jocivaldias.cardstatement.entity;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class CardStatementInstalment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Min(1)
    @Column(nullable = false)
    private Integer quantity;

    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal value;

    @NotNull
    @ManyToOne(optional = false)
    private CardStatement cardStatement;

    public CardStatementInstalment(@NotNull @Min(1) Integer quantity,
                                   @NotNull @DecimalMin(value = "0.01") BigDecimal value,
                                   CardStatement cardStatement) {
        this.quantity = quantity;
        this.value = value;
        this.cardStatement = cardStatement;
    }






}
