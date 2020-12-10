package br.com.zup.jocivaldias.cardstatement.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class CardControlResponse {

    @JsonProperty("limite")
    private BigDecimal limit;

    @JsonProperty("vencimento")
    private CardControlDueResponse cardControlDueResponse;

    public CardControlResponse(BigDecimal limit, CardControlDueResponse cardControlDueResponse) {
        this.limit = limit;
        this.cardControlDueResponse = cardControlDueResponse;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public CardControlDueResponse getCardDueResponse() {
        return cardControlDueResponse;
    }
}
