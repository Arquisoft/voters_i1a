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
        return new Voter(1, email, "abcd1234");
    }

    @Override
    public Voter getById(long id) {
        return new Voter(id, "email@gmail.com", "1234abcd");
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
