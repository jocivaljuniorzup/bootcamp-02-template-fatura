package br.com.zup.jocivaldias.cardstatement.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CardResponse {

    @JsonProperty("limite")
    private BigDecimal limit;

    @JsonProperty("vencimento")
    private CardDueResponse cardDueResponse;

    public CardResponse(BigDecimal limit, CardDueResponse cardDueResponse) {
        this.limit = limit;
        this.cardDueResponse = cardDueResponse;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public CardDueResponse getCardDueResponse() {
        return cardDueResponse;
    }
}
