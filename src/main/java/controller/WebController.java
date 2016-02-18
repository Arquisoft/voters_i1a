package controller;

import dto.VoterInfo;
import dto.VoterLogin;
import model.Voter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.ServicesFactory;

@Controller
public class WebController {

    @RequestMapping(path = "/get_info", method = RequestMethod.GET)
    public String getInfo(Model model) {
        model.addAttribute("voter", new VoterLogin());
        return "loginVoter";
    }

    @RequestMapping(path = "/voter_info", method = RequestMethod.POST)
    public String info(@ModelAttribute VoterLogin login, Model model) {
        // find the voter
        Voter voter = ServicesFactory.getPersistenceService().getVoterDao().getByEmail(login.getEmail());

        System.out.println(login.getEmail());

        // if the voter doesn't exist
        if (voter == null)
            return "404";
        // if the password is wrong
        if (!voter.checkPassword(login.getPassword()))
            return "404";

        // create the DTO for the response
        model.addAttribute("email", voter.getEmail());
        model.addAttribute("nif", voter.getNif());
        model.addAttribute("name", voter.getName());
        model.addAttribute("pollingStationCode", voter.getPollingStationCode());

        return "voterInfo";
    }

}
