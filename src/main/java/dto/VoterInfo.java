package dto;

/**
 * DTO with data about a voter to be sent to the client
 */
public class VoterInfo {

    private String email;

    public VoterInfo(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

}
