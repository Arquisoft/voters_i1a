package dto;

import model.Voter;

/**
 * DTO with data about a voter to be sent to the client
 */
public class VoterInfo {

    private String email;
    private String nif;
    private String name;
    private String pollingStationCode;

    public VoterInfo(String email, String name, String nif, String pollingStationCode) {
        this.email = email;
        this.name = name;
        this.nif = nif;
        this.pollingStationCode = pollingStationCode;
    }

    public String getEmail() {
        return email;
    }

    public String getNif() {
        return nif;
    }

    public String getName() {
        return name;
    }

    public String getPollingStationCode() {
        return pollingStationCode;
    }

    public static VoterInfo fromModel(Voter voter) {
        return new VoterInfo(voter.getEmail(), voter.getName(), voter.getNif(), voter.getPollingStationCode());
    }

}
