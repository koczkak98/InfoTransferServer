package com.infotransferserver.InfoTransferServer.controller;
import com.infotransferserver.InfoTransferServer.db.InfoRepository;
import com.infotransferserver.InfoTransferServer.db.UserRepository;
import com.infotransferserver.InfoTransferServer.model.InfoModel;
import com.infotransferserver.InfoTransferServer.model.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.NoSuchAlgorithmException;

@Controller
public class InfoController {

    private InfoRepository infoRepo;
    private UserRepository userRepo;

    private String forUser = "";

    public InfoController(InfoRepository infoRepo, UserRepository userRepo) {
        this.infoRepo = infoRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("/")
    public String welcome ()
    {
        return "";
    }

    @GetMapping("/sendmessage")
    public String startInputInfo (
            Model model) throws NoSuchAlgorithmException {
        Security security = new Security();

        String keyExample = "test";
        String keyExampleEncrypt = security.encrypt(keyExample);



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
