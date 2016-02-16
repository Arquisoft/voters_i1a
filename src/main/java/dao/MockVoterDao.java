package dao;

import model.Voter;

import java.util.ArrayList;
import java.util.List;

public class MockVoterDao implements VoterDao {

    @Override
    public void createVoter(Voter voter) {

    }

    @Override
    public Voter getByEmail(String email) {
        return new Voter(1, "12345678A", "John", email, "abcd1234", "12345");
    }
    
    @Override
    public Voter getByEmailAndPassword(String email, String password) {
    	return new Voter(1, "12345678A", "John", email, "abcd1234", "12345");
    }

    @Override
    public Voter getById(long id) {
        return new Voter(id, "87654321B", "Smith", "smith@voters.com", "1234abcd", "54321");
    }

    @Override
    public List<Voter> getAll() {
        List<Voter> voters = new ArrayList<>();

        voters.add(getByEmail("mock@voters.com"));
        voters.add(getById(2));

        return voters;
    }

    @Override
    public void updateVoter(Voter voter) {

    }

    @Override
    public void removeVoter(Voter voter) {

    }
}
