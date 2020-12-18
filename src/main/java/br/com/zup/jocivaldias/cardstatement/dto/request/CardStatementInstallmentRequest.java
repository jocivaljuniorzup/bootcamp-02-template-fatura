package br.com.zup.jocivaldias.cardstatement.dto.request;

import br.com.zup.jocivaldias.cardstatement.entity.CardStatement;
import br.com.zup.jocivaldias.cardstatement.entity.CardStatementInstalment;

import java.math.BigDecimal;

public class CardStatementInstallmentRequest {

    private Integer quantity;
    private BigDecimal value;

    public CardStatementInstallmentRequest(Integer quantity, BigDecimal value) {
        this.quantity = quantity;
        this.value = value;
    }

    public CardStatementInstalment toModel(CardStatement cardStatement){
        return new CardStatementInstalment(quantity, value, cardStatement);
    }

}
