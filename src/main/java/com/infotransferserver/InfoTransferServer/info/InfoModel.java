package com.infotransferserver.InfoTransferServer.info;

import javax.persistence.*;

@Table(name = "info")
@Entity
public class InfoModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer infoId;

    @Column(name = "title")
    private String infoTitle;

    @Column
    private String message;

    public InfoModel() {
    }

    public InfoModel(Integer infoId) {
        this.infoId = infoId;
    }

    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    public String getInfoTitle() {
        return infoTitle;
    }

    public void setInfoTitle(String infoTitle) {
        this.infoTitle = infoTitle;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


