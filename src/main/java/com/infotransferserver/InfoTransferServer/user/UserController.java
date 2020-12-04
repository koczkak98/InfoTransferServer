package com.infotransferserver.InfoTransferServer.user;

import com.infotransferserver.InfoTransferServer.info.InfoModel;
import com.infotransferserver.InfoTransferServer.info.InfoRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private InfoRepository infoRepo;
    private UserRepository userRepo;

    private String forUser = "";

    public UserController(InfoRepository infoRepo, UserRepository userRepo) {
        this.infoRepo = infoRepo;
        this.userRepo = userRepo;
    }


    @GetMapping("/getmessage/{userid}")
    public InfoUser getInfoByUserId (
            @PathVariable("userid") int userid,
            Model model)
    {
        UserModel user = userRepo.findById(userid);
        InfoUser infoUser = new InfoUser(userid);

        for (int i = 0; i < user.getInfoIds().size(); i++)
        {
            InfoModel info = infoRepo.findById( user.getInfoIds().get(i) );
            infoUser.addInfo(info);
        }

        return infoUser;
    }

}
