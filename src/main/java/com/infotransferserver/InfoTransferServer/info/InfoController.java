package com.infotransferserver.InfoTransferServer.info;
import com.infotransferserver.InfoTransferServer.user.UserModel;
import com.infotransferserver.InfoTransferServer.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class InfoController {

    private InfoRepository infoRepo;
    private UserRepository userRepo;

    private String forUser = "";

    public InfoController(InfoRepository infoRepo, UserRepository userRepo) {
        this.infoRepo = infoRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("/sendmessage")
    public String startInputInfo (Model model)
    {
        model.addAttribute("info", new InfoModel());
        model.addAttribute("forUser", forUser);

        return "input.html";
    }

    @PostMapping("/sendmessage")
    public String finishInputInfo (
            @RequestParam("userid") int userid,
            @RequestParam("infoTitle") String infoTitle,
            @RequestParam("message") String message,
            Model model)
    {
        /** FIND THE USER */

        UserModel user = userRepo.findById(userid);
        InfoModel info = new InfoModel();

        try {

            /** SAVE DB */
            info.setInfoTitle(infoTitle);
            System.out.println(info.getInfoTitle());
            info.setMessage(message);
            System.out.println(info.getMessage());
            infoRepo.save(info);

            /** UPDATE uim */
            System.out.println(info.getInfoId());
            user.addInfoIds(info.getInfoId());
            userRepo.save(user);

            System.out.println(user.getInfoIds());

            forUser = "SUCCESSFULLY";
        }
        catch (NullPointerException e)
        {
            System.out.println("NullPointerException");
            forUser = "NOT SUCCESSFULLY";
        }
        catch (Exception e)
        {
            System.out.println("Exception");
            forUser = "NO SUCCESSFULLY";
        }


        return "redirect:/sendmessage";
    }






}
