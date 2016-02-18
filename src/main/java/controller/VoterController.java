package controller;

import dao.VoterDao;
import dto.VoterInfo;
import dto.VoterLogin;
import dto.VoterPasswordUpdate;
import model.Voter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.PersistenceServiceImpl;
import service.ServicesFactory;

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
    @RequestMapping(path = "/voter/get_info", method = RequestMethod.POST, 
			headers ="Accept=application/json",
    		produces = "application/json")
    public ResponseEntity<VoterInfo> voter(@RequestBody VoterLogin voterLogin) {
        // find the voter
        Voter voter = ServicesFactory.getPersistenceService().getVoterDao().getByEmail(voterLogin.getEmail());

        // if the voter doesn't exist
        if (voter == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        // if the password is wrong
        if (!voter.checkPassword(voterLogin.getPassword()))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        // create the DTO for the response
        VoterInfo output = VoterInfo.fromModel(voter);

        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    /**
     * Update a voter password
     * @param passwordUpdate DTO with the information to update the password
     * @return Http status code
     */
    @RequestMapping(path = "/voter/change_password", method = RequestMethod.POST,
			headers ="Accept=application/json",
    		produces = "application/json")
    public HttpStatus updatePassword(@RequestBody VoterPasswordUpdate passwordUpdate) {
        VoterDao dao = ServicesFactory.getPersistenceService().getVoterDao();

        Voter voter = dao.getByEmail(passwordUpdate.getEmail());

        if (voter == null)
            return HttpStatus.NOT_FOUND;
        if (!voter.checkPassword(passwordUpdate.getOldPassword()))
            return HttpStatus.FORBIDDEN;

        voter.setPassword(passwordUpdate.getNewPassword());
        dao.updateVoter(voter);

        return HttpStatus.OK;
    }

}
