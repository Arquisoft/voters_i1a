package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for the voter model
 */
public class VoterTests {

    private Voter voter;

    private static String PASSWORD = "abcd1234";

    @Before
    public void setUp() {
        voter = new Voter(1, "12345678A", "Broter", "broter@asw.es", PASSWORD, "PSC01");
    }

    @Test
    public void testCorrectPassword() {
        assertTrue(voter.checkPassword(PASSWORD));
    }

    @Test
    public void testIncorrectPassword() {
        assertFalse(voter.checkPassword("incorrect"));
    }

    @Test
    public void testEncryption() {
        Voter voter2 = new Voter(2, "01234567A", "Voter2", "voter2@asw.es", PASSWORD, "PSC02");
        assertFalse(voter2.getHashedPassword().equals(PASSWORD));
    }

    @Test
    public void changePassword() {
        Voter voter2 = new Voter(2, "01234567A", "Voter2", "voter2@asw.es", PASSWORD, "PSC02");
        String hashed = voter2.getHashedPassword();

        voter2.setPassword("different");

        assertFalse(hashed.equals(voter2.getHashedPassword()));
        assertFalse(voter2.checkPassword(PASSWORD));
    }

}
