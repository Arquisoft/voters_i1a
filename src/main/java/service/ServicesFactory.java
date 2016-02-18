package service;

public class ServicesFactory {

    public static PersistenceService getPersistenceService() {
        return PersistenceServiceImpl.getInstance();
    }

}
