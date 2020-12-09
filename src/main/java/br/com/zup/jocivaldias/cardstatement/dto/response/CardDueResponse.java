package br.com.zup.jocivaldias.cardstatement.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CardDueResponse {

    private String id;
    @JsonProperty("dia")
    private Integer day;
    @JsonProperty("dataDeCriacao")
    private String creationDate;

    public CardDueResponse(String id, Integer day, String creationDate) {
        this.id = id;
        this.day = day;
        this.creationDate = creationDate;
    }

    public Integer getDay() {
        return day;
    }
}
