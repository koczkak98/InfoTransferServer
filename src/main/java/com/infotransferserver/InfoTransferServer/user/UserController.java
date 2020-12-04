package com.infotransferserver.InfoTransferServer.user;

import com.infotransferserver.InfoTransferServer.info.InfoModel;
import com.infotransferserver.InfoTransferServer.info.InfoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private InfoRepository infoRepo;
    private UserRepository userRepo;

    private String forUser = "";

    public UserController(InfoRepository infoRepo, UserRepository userRepo) {
        this.infoRepo = infoRepo;
        this.userRepo = userRepo;
    }


    @GetMapping("/getmessage")
    public String welcome (Model model)
    {
        InfoModel info = new InfoModel(0);
        info.setInfoTitle("");
        info.setMessage("");
        InfoUser infoUser = new InfoUser(0);
        infoUser.addInfo(info);
        model.addAttribute("infos", infoUser);

        return "getInfo.html";
    }

    @PostMapping("/getmessage")
    public String getInfo (
            @RequestParam("userid") int userid,
            Model model)
    {
        UserModel user = userRepo.findById(userid);

        InfoUser infoUser = new InfoUser(userid);

        for (int i = 0; i < user.getInfoIds().size(); i++)
        {
            InfoModel info = infoRepo.findById(user.getInfoIds().get(i));
            infoUser.addInfo(info);
        }

        model.addAttribute("infos", infoUser);

        return "getInfo.html";
    }

}
