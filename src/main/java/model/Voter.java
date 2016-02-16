package model;

/**
 * Represents a voter
 */
public class Voter {

    private long id;

    private String nif;
    private String email;
    private String name;
    private String password;

    private String pollingStationCode;

    public Voter(long id, String nif, String name, String email, String password, String pollingStationCode) {
        this.id = id;
        this.nif = nif;
        this.email = email;
        this.name = name;
        this.password = password;
        this.pollingStationCode = pollingStationCode;
    }

    /**
     * Check that a password matches the user's
     * @param password Password to be checked
     * @return True when it does. False otherwise
     */
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public String getNif() {
        return nif;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPollingStationCode() {
        return pollingStationCode;
    }

    public long getId() {
        return id;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPollingStationCode(String pollingStationCode) {
        this.pollingStationCode = pollingStationCode;
    }
}
