package model;

/**
 * Represents a voter
 */
public class Voter {

    private long id;

    private String email;
    private String password;

    public Voter(long id,String email,String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    /**
     * Check that a password matches the user's
     * @param password Password to be checked
     * @return True when it does. False otherwise
     */
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public long getId() {
        return id;
    }
}
