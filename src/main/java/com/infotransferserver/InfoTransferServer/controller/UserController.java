package com.infotransferserver.InfoTransferServer.controller;

import com.infotransferserver.InfoTransferServer.db.ApiKeyRepository;
import com.infotransferserver.InfoTransferServer.model.InfoLists;
import com.infotransferserver.InfoTransferServer.model.InfoModel;
import com.infotransferserver.InfoTransferServer.db.InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private InfoRepository infoRepo;
    @Autowired
    private ApiKeyRepository apiKeyRepo;


    @GetMapping("/getinfo/today/apikey={apikey}")
    public InfoLists getInfoByActuallyTime ()
    {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(formatter.format(date));


        InfoLists infoLists = new InfoLists();
        infoLists.setInfos(infoRepo.findByDate(formatter.format(date)));

        return infoLists;
    }

    @GetMapping("/getinfo/{day}apikey={apikey}")
    public InfoLists getInfoByDay (@PathVariable("day") String day)
    {

        System.out.println(day);

        InfoLists infoLists = new InfoLists();
        infoLists.setInfos(infoRepo.findByDate(day));

        return infoLists;
    }

}
