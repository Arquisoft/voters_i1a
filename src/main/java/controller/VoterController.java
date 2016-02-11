package controller;

import dto.VoterLogin;
import model.Voter;
import org.springframework.web.bind.annotation.*;
import service.PersistenceServiceImpl;

/**
 * Controller for the Voter
 */
@RestController
public class VoterController {

    /**
     * Process a request to /voter. Checks that the given password matches a voter, and, if it does, respond with
     * data about the voter
     * @return Voter
     */
    @RequestMapping(path = "/voter", method = RequestMethod.POST)
    public Voter voter(@RequestBody VoterLogin voterLogin) {
        Voter voter = PersistenceServiceImpl.getInstance().getVoterDao().getByEmail(voterLogin.getLogin());

        if (voter == null)
            return null; // TODO respond with 404
        if (!voter.getPassword().equals(voterLogin.getPassword()))
            return null; // TODO respond with 404

        return voter;
    }

}
