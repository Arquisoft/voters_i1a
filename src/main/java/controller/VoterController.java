package controller;

import dto.VoterInfo;
import dto.VoterLogin;
import model.Voter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<VoterInfo> voter(@RequestBody VoterLogin voterLogin) {
        Voter voter = PersistenceServiceImpl.getInstance().getVoterDao().getByEmail(voterLogin.getLogin());

        if (voter == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (!voter.getPassword().equals(voterLogin.getPassword()))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        VoterInfo output = new VoterInfo(voter.getEmail());

        return new ResponseEntity<>(output, HttpStatus.OK);
    }

}
