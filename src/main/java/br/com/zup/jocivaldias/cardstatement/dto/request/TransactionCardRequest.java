package br.com.zup.jocivaldias.cardstatement.dto.request;

public class TransactionCardRequest {

    private String id;
    private String email;

    public TransactionCardRequest(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public TransactionCardRequest() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "TransactionCardRequest{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
