package dto;

/**
 * DTO with info to update the password of a voter
 */
public class VoterPasswordUpdate {

    private String email;
    private String oldPassword;
    private String newPassword;

    public String getEmail() {
        return email;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

}
