package controller;

import model.Voter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.PersistenceServiceImpl;

/**
 * Controller for the Voter
 */
@RestController
public class VoterController {

    /**
     * Process a request to /voter. Checks that the given password matches a voter, and, if it does, respond with
     * data about the voter
     * @param email Email of the voter
     * @param password Password of the voter
     * @return Voter
     */
    @RequestMapping(path = "/voter", method = RequestMethod.POST)
    public Voter voter(@RequestParam("email") String email, @RequestParam("password") String password) {
        Voter voter = PersistenceServiceImpl.getInstance().getVoterDao().getByEmail(email);

        if (voter == null)
            return null; // TODO respond with 404
        if (!voter.getPassword().equals(password))
            return null; // TODO respond with 404

        return voter;
    }

}
