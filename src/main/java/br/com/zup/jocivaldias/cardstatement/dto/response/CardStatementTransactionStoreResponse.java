package br.com.zup.jocivaldias.cardstatement.dto.response;

public class CardStatementTransactionStoreResponse {

    private String name;
    private String city;
    private String address;

    public CardStatementTransactionStoreResponse(String name, String city, String address) {
        this.name = name;
        this.city = city;
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
}
