package service;

import dao.JDBCVoterDao;
import dao.MockVoterDao;
import dao.VoterDao;

/**
 * Façade to access the persistence layer
 */
public class PersistenceServiceImpl implements PersistenceService {

    private static PersistenceServiceImpl instance; // singleton

    private PersistenceServiceImpl() {} // cannot be instantiated by outside

    static PersistenceServiceImpl getInstance() {
        if (instance == null)
            instance = new PersistenceServiceImpl();

        return instance;
    }

    @Override
    public VoterDao getVoterDao() {
        return new JDBCVoterDao();
    }

}
