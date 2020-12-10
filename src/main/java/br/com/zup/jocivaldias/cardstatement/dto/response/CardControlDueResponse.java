package br.com.zup.jocivaldias.cardstatement.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CardControlDueResponse {

    private String id;
    @JsonProperty("dia")
    private Integer day;
    @JsonProperty("dataDeCriacao")
    private String creationDate;

    public CardControlDueResponse(String id, Integer day, String creationDate) {
        this.id = id;
        this.day = day;
        this.creationDate = creationDate;
    }

    public Integer getDay() {
        return day;
    }
}
