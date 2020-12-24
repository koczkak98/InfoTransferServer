package com.infotransferserver.InfoTransferServer.model;

import javax.persistence.*;

@Entity
@Table(name = "user_apikey")
public class ApiKey {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "apikey")
    private String apiKey;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
