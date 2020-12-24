package com.infotransferserver.InfoTransferServer.controller;
import com.infotransferserver.InfoTransferServer.db.ApiKeyRepository;
import com.infotransferserver.InfoTransferServer.db.InfoRepository;
import com.infotransferserver.InfoTransferServer.model.ApiKey;
import com.infotransferserver.InfoTransferServer.model.InfoLists;
import com.infotransferserver.InfoTransferServer.model.InfoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class InfoController {

    @Autowired
    private InfoRepository infoRepo;
    @Autowired
    private ApiKeyRepository apiKeyRepo;
    private String forUser = "";


    @GetMapping("/")
    public String welcome ()
    {
        return "";
    }

    @GetMapping("/addmessage/apikey={apikey}")
    public String startInputInfo (
            Model model,
            @PathVariable("apikey") String apikey) {

        String returnLink = "";

        /** FIND THE USER */
        try {
            ApiKey apiKey = apiKeyRepo.findByApiKey(apikey);
            if (apiKey == null)
            {
                returnLink = "error.html";
            }
            else {
                model.addAttribute("apikey", apiKey);
                returnLink = "input.html";
            }

        }
        catch (Exception e)
        {
            returnLink = "error.html";
            System.out.println(apikey);
        }
        model.addAttribute("forUser", forUser);

        return returnLink;
    }

    @PostMapping("/addmessage/apikey={apikey}")
    public String finishInputInfo (
            @RequestParam("infoTitle") String infoTitle,
            @RequestParam("message") String message,
            @PathVariable("apikey") String apikey,
            Model model)
    {

        String returnLink = "redirect:/addmessage/apikey=" + apikey;
        InfoModel info = new InfoModel();

        try {

            ApiKey apiKey = apiKeyRepo.findByApiKey(apikey);
            if (apiKey == null)
            {
                returnLink = "error.html";
            }
            else {

                /** SAVE DB */
                info.setInfoTitle(infoTitle);
                System.out.println(info.getInfoTitle());
                info.setMessage(message);
                System.out.println(info.getMessage());

                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                System.out.println(formatter.format(date));
                info.setDate(formatter.format(date));

                infoRepo.save(info);

                forUser = "SUCCESSFULLY";
            }
        }
        catch (NullPointerException e)
        {
            System.out.println("NullPointerException");
            forUser = "NOT SUCCESSFULLY";
        }
        catch (Exception e)
        {
            System.out.println("Exception");
            forUser = "NOT SUCCESSFULLY";
        }

        return returnLink;
    }

    @GetMapping("/update/{title}apikey={apikey}")
    public String startUpdateInfoByTitle(
            @PathVariable("title") String title,
            @PathVariable("apikey") String apikey,
            Model model)
    {
        String returnLink = "";

        ApiKey apiKey = apiKeyRepo.findByApiKey(apikey);
        if (apiKey == null)
        {
            returnLink = "error.html";
        }
        else
        {
            InfoLists infoLists = new InfoLists();
            infoLists.setInfos(infoRepo.findByTitle(title));

            model.addAttribute("infos", infoLists);
            model.addAttribute("apikey", apiKey);

            returnLink = "update.html";
        }
        return returnLink;
    }

    @PostMapping("/update/{id}apikey={apikey}")
    public String finishUpdateInfo (
            @PathVariable("id") int id,
            @PathVariable("apikey") String apikey)
    {
        System.out.println("finishUpdateInfo");

        String returnLink = "";

        ApiKey apiKey = apiKeyRepo.findByApiKey(apikey);
        if (apiKey == null)
        {
            returnLink = "error.html";
        }
        else
        {
            InfoModel info = infoRepo.findById(id);

            returnLink= "redirect:/update/" + info.getInfoTitle() + "apikey=" + apikey;
        }

        return returnLink;
    }




}
