package database;

import static org.junit.Assert.*;

import java.sql.SQLException;

import model.Voter;
import dao.JDBCVoterDao;

import org.junit.Before;
import org.junit.Test;


public class DatabaseTests {
	
	JDBCVoterDao database;
	Voter John;
	
	String email;
	String password;

	@Before
	public void setUp() throws Exception {
		
		database = new JDBCVoterDao();
		
		John = new Voter(1, "123456789", "John", "john@gmail.com", "johnrocks", "123");
		
		database.updateVoter(John);
		
	}

	@Test
	public void testAccess() {
		
		email = "john@gmail.com";
		password = "johnrocks";
		
		try {
		Voter voter = database.getByEmailAndPassword(email, password);
		
		if(voter.equals(John)) {
			// John writes his email and password correctly and succesfuly accesses the database
			assertTrue(true);
		}
		else {
			fail("Voter was not John, email or password might be wrong"+voter.toString()+" "+ John.toString());
		}
		
		} catch(NullPointerException n) {
			fail("Access was denied to the database, user is unknown (null)");
		}
		
	}
	
	@Test
	public void testAccessDeniedUnknownUser() {
		
		email = "johny@gmail.com";
		password = "johnrocks";
		
		try {
		Voter voter = database.getByEmailAndPassword(email, password);
		
		if(voter.equals(John)) {
			fail("Voter was found even though he's supposed to not be in the database");
		}
		else {
			fail("Voter was not John, email or password might be wrong");
		}
		
		} catch(NullPointerException n) {
			// Johny@gmail.com is not on the emails available in Users
			assertTrue(true);
		}
		
	}
	
	@Test
	public void testAccessDeniedWrongPassword() {
		
		email = "john@gmail.com";
		password = "johnyrocks";
		
		try {
		Voter voter = database.getByEmailAndPassword(email, password);
		
		if(!voter.equals(null))
			fail("Voter was found even though password is supposed to be wrong");
		
		} catch(NullPointerException n) {
			//True if user is not found
			assertTrue(true);
		}
		
	}
	
	@Test
	public void testAccessPasswordChange() {
		
		email = "john@gmail.com";
		password = "johnrocks";
		
		String newPass = "johndoesntread";
		
		try {
			Voter voter = database.getByEmailAndPassword(email, password);
		
			if(voter.equals(John)) {
				voter.setPassword(newPass);
				database.updateVoter(voter);
			}
			else {
				fail("Voter was not John, email or password might be wrong");
			}
		
			} catch(NullPointerException n) {
				fail("Access was denied to the database, user is unknown (null)");
			}
		
		//Try to re-enter with old password
		try {
			Voter voter = database.getByEmailAndPassword(email, password);
			
			if(voter.equals(John)) {
				fail("Access should not be allowed");
			}
			
			} catch(NullPointerException n) {
				// John cant access with his old password
				assertTrue(true);
			}
		
	}

}
