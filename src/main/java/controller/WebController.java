package controller;

import dao.VoterDao;
import dto.VoterInfo;
import dto.VoterLogin;
import dto.VoterPasswordUpdate;
import model.Voter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.ServicesFactory;

@Controller
public class WebController {

    /**
     * Serve the get info page
     */
    @RequestMapping(path = "/get_info", method = RequestMethod.GET)
    public String getInfo(Model model) {
        model.addAttribute("voter", new VoterLogin());
        return "loginVoter";
    }

    /**
     * Serve the update password page
     */
    @RequestMapping(path="/update_password/{id}", method = RequestMethod.GET)
    public String updatePassword(@PathVariable long id, Model model) {
        Voter voter = ServicesFactory.getPersistenceService().getVoterDao()
                .getById(id);

        VoterPasswordUpdate dto = new VoterPasswordUpdate();

        dto.setEmail(voter.getEmail());

        model.addAttribute("update", dto);

        return "updatePassword";
    }

    /**
     * Get the info from a user given his credentials and serve it to him
     * @param login DTO with the user's credentials
     */
    @RequestMapping(path = "/voter_info", method = RequestMethod.POST)
    public String info(@ModelAttribute VoterLogin login, Model model) {
        // find the voter
        Voter voter = ServicesFactory.getPersistenceService().getVoterDao().getByEmail(login.getEmail());

        System.out.println(login.getEmail());

        // if the voter doesn't exist
        if (voter == null) {
            return "loginFail";
        }
        // if the password is wrong
        if (!voter.checkPassword(login.getPassword())) {
            return "loginFail";
        }

        // send the user data to the template
        model.addAttribute("voter", voter);

        return "voterInfo";
    }

    /**
     * Update the password of a user
     * @param update DTO with data about the update
     */
    @RequestMapping(path="/do_update_password", method = RequestMethod.POST)
    public String doUpdatePassword(@ModelAttribute VoterPasswordUpdate update, Model model) {
        VoterDao dao = ServicesFactory.getPersistenceService().getVoterDao();

        Voter voter = dao.getByEmail(update.getEmail());

        if (!voter.checkPassword(update.getOldPassword()))
            return "error"; // TODO redirect to error page

        voter.setPassword(update.getNewPassword());
        dao.updateVoter(voter);

        return "success";   // TODO redirect to success page or go back to info page
    }

}
