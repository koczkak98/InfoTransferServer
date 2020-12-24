package com.infotransferserver.InfoTransferServer.controller;

import com.infotransferserver.InfoTransferServer.db.ApiKeyRepository;
import com.infotransferserver.InfoTransferServer.model.ApiKey;
import com.infotransferserver.InfoTransferServer.model.InfoLists;
import com.infotransferserver.InfoTransferServer.db.InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class UserController {

    @Autowired
    private InfoRepository infoRepo;
    @Autowired
    private ApiKeyRepository apiKeyRepo;


    @GetMapping("/getinfo/today/apikey={apikey}")
    public InfoLists getInfoByActuallyTime (@PathVariable("apikey") String apikey)
    {
        ApiKey apiKey = apiKeyRepo.findByApiKey(apikey);
        if (apiKey == null)
        {
            return new InfoLists();
        }
        else {

            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println(formatter.format(date));


            InfoLists infoLists = new InfoLists();
            infoLists.setInfos(infoRepo.findByDate(formatter.format(date)));

            return infoLists;
        }
    }

    @GetMapping("/getinfo/{day}apikey={apikey}")
    public InfoLists getInfoByDay (
            @PathVariable("apikey") String apikey,
            @PathVariable("day") String day)
    {
        ApiKey apiKey = apiKeyRepo.findByApiKey(apikey);
        if (apiKey == null)
        {
            return new InfoLists();
        }
        else {

            System.out.println(day);

            InfoLists infoLists = new InfoLists();
            infoLists.setInfos(infoRepo.findByDate(day));

            return infoLists;
        }
    }

}
