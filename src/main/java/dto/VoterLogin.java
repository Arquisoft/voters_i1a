package dto;

/**
 * Data Transfer Object for requests that contain login information about a voter
 */
public class VoterLogin {

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
