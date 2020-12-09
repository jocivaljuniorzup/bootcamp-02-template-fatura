package br.com.zup.jocivaldias.cardstatement.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionStoreRequest {

    @JsonProperty(value = "nome")
    private String name;

    @JsonProperty(value = "cidade")
    private String city;

    @JsonProperty(value = "endereco")
    private String address;

    public TransactionStoreRequest(String name,
                                   String city,
                                   String address) {
        this.name = name;
        this.city = city;
        this.address = address;
    }

    public TransactionStoreRequest() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "TransactionStoreRequest{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
