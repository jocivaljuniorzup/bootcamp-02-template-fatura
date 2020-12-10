package br.com.zup.jocivaldias.cardstatement.service;

import br.com.zup.jocivaldias.cardstatement.dto.response.CardControlResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="creditCardGenerator", url = "${credit.card.control.host}")
public interface CardControl {

    @GetMapping(path = "/api/cartoes/{cardNumber}", consumes = "application/json", produces = "application/json")
    CardControlResponse getCreditCard(@PathVariable(name="cardNumber") String cardNumber);

}
