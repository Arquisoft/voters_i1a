package dao;

import model.Voter;

import java.sql.*;
import java.util.List;

/**
 * DAO for the voter using JDBC
 */
public class JDBCVoterDao implements VoterDao {

    private Connection conn;

    private static final String DB_URL = "jdbc:hsqldb:hsql://localhost";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    public JDBCVoterDao() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createVoter(Voter voter) {

    }

    @Override
    public Voter getByEmail(String email) {
        String sql = "SELECT id, name, nif, email, password, pollingstationcode FROM Users WHERE email = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next())
                return null;

            Voter voter = new Voter(
                    resultSet.getLong("id"),
                    resultSet.getString("nif"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    "",
                    resultSet.getString("pollingstationcode"));

            voter.setHashedPassword(resultSet.getString("password"));

            resultSet.close();

            return voter;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public Voter getByEmailAndPassword(String email, String password) {
    	String sql = "SELECT id, name, nif, email, password, pollingstationcode FROM Users WHERE email = ? AND password = ?";
        
    	try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next())
                return null;

            Voter voter = new Voter(
                    resultSet.getLong("id"),
                    resultSet.getString("nif"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                    resultSet.getString("pollingstationcode"));

            resultSet.close();

            return voter;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Voter getById(long id) {
        String sql = "SELECT id, name, nif, email, password, pollingstationcode FROM Users WHERE id = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next())
                return null;

            Voter voter = new Voter(
                    resultSet.getLong("id"),
                    resultSet.getString("nif"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    "",
                    resultSet.getString("pollingstationcode"));

            voter.setHashedPassword(resultSet.getString("password"));

            resultSet.close();

            return voter;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Voter> getAll() {
        return null;
    }

    @Override
    public void updateVoter(Voter voter) {
        String sql = "UPDATE Users SET nif = ?, name = ?, email = ?, password = ?, pollingstationcode = ? WHERE id = ?";

        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, voter.getNif());
            statement.setString(2, voter.getName());
            statement.setString(3, voter.getEmail());
            statement.setString(4, voter.getHashedPassword());
            statement.setString(5, voter.getPollingStationCode());
            statement.setLong(6, voter.getId());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeVoter(Voter voter) {

    }

}
