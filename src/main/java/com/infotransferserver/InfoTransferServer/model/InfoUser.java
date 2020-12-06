package com.infotransferserver.InfoTransferServer.model;

import com.infotransferserver.InfoTransferServer.model.InfoModel;

import java.util.ArrayList;
import java.util.List;

public class InfoUser {

    private Integer userId;

    private List<InfoModel> infos;


    public InfoUser(Integer userId) {
        this.userId = userId;
        this.infos = new ArrayList<InfoModel>();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<InfoModel> getInfos() {
        return infos;
    }

    public void setInfos(List<InfoModel> infos) {
        this.infos = infos;
    }

    public void addInfo (InfoModel info)
    {
        this.infos.add(info);
    }
}
