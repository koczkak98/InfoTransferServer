package com.infotransferserver.InfoTransferServer.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "user")
@Entity
public class UserModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column
    private String name;

    @Column
    private String nickname;

    @Column
    private String pwd;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "user_info_mapping",
            joinColumns = @JoinColumn(name = "userid")
    )
    @Column(name = "infoid")
    private List<Integer> infoIds;


    public UserModel() {
        this.infoIds = new ArrayList<Integer>();
    }



    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public List<Integer> getInfoIds() {
        return infoIds;
    }

    public void setInfoIds(List<Integer> infoIds) {
        this.infoIds = infoIds;
    }

    public void addInfoIds (Integer infoId)
    {
        this.infoIds.add(infoId);
    }

    public void removeInfoIds (Integer infoId)
    {
        this.infoIds.remove(infoId);
    }

}
