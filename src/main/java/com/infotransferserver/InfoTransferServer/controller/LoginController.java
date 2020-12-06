package com.infotransferserver.InfoTransferServer.controller;

import com.infotransferserver.InfoTransferServer.db.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepo;


}
