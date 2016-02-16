package dto;

/**
 * Data Transfer Object for requests that contain login information about a voter
 */
public class VoterLogin {

    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

}
