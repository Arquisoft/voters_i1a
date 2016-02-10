package service;

import dao.VoterDao;

public interface PersistenceService {

    /**
     * @return Instance to the voter DAO
     */
    VoterDao getVoterDao();

}
