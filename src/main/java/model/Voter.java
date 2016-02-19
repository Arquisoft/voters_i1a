package model;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Represents a voter
 */
public class Voter {

    private long id;

    private String nif;
    private String email;
    private String name;
    private String password;

    private String hashedPassword;

    private String pollingStationCode;

    public Voter() {}

    public Voter(long id, String nif, String name, String email, String password, String pollingStationCode) {
        this.id = id;
        this.nif = nif;
        this.email = email;
        this.name = name;
        this.password = password;
        hashPassword(password);
        this.pollingStationCode = pollingStationCode;
    }

    private void hashPassword(String password) {
        String salt = BCrypt.gensalt(12);
        this.hashedPassword = BCrypt.hashpw(password, salt);
    }

    /**
     * Check that a password matches the user's
     * @param password Password to be checked
     * @return True when it does. False otherwise
     */
    public boolean checkPassword(String password) {
        return BCrypt.checkpw(password, this.hashedPassword);
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

    public String getHashedPassword() {
        return hashedPassword;
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
        this.hashPassword(password);
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public void setPollingStationCode(String pollingStationCode) {
        this.pollingStationCode = pollingStationCode;
    }
    
    
	@Override
	public String toString() {
		return "Voter [nif=" + nif + ", email=" + email + ", name=" + name
				+ ", password=" + password + ", pollingStationCode="
				+ pollingStationCode + "]";
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((nif == null) ? 0 : nif.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime
				* result
				+ ((pollingStationCode == null) ? 0 : pollingStationCode
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Voter other = (Voter) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nif == null) {
			if (other.nif != null)
				return false;
		} else if (!nif.equals(other.nif))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (pollingStationCode == null) {
			if (other.pollingStationCode != null)
				return false;
		} else if (!pollingStationCode.equals(other.pollingStationCode))
			return false;
		return true;
	}
    
    
}