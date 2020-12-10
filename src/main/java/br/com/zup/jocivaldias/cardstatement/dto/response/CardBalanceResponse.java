package br.com.zup.jocivaldias.cardstatement.dto.response;

import java.math.BigDecimal;

public class CardBalanceResponse {

    private BigDecimal balance;

    public CardBalanceResponse(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
