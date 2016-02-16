package dao;

import model.Voter;

import java.util.List;

/**
 * Accesses the persistence layer to perform operations related to voters
 */
public interface VoterDao {

    // Creation

    /**
     * Create a new voter
     * @param voter
     */
    void createVoter(Voter voter);

    // Reading

    /**
     * Get a voter by his email.
     * @param email Email of the voter
     * @return Instance of voter. Null if not found
     */
    Voter getByEmail(String email);
    
    Voter getByEmailAndPassword(String email, String password);

    /**
     * Get a voter by his id.
     * @param id ID of the voter
     * @return Instance of voter. Null if not found
     */
    Voter getById(long id);

    /**
     * @return List with all the voters
     */
    List<Voter> getAll();

    // Updating

    /**
     * Update an existing voter.
     * @param voter Voter to be updater
     */
    void updateVoter(Voter voter);

    // Removing

    /**
     * Remove an existing voter
     * @param voter Voter to be removed
     */
    void removeVoter(Voter voter);
}
