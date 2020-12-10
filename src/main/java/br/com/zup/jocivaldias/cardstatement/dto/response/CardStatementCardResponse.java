package br.com.zup.jocivaldias.cardstatement.dto.response;

import br.com.zup.jocivaldias.cardstatement.entity.Card;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CardStatementCardResponse {

    @JsonProperty("card_number")
    private String cardNumber;

    public CardStatementCardResponse(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public static CardStatementCardResponse fromModel(Card card) {
        return new CardStatementCardResponse(card.getCardNumber());
    }

    public String getCardNumber() {
        return cardNumber;
    }
}
