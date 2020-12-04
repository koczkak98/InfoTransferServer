package com.infotransferserver.InfoTransferServer.user;

import org.springframework.data.repository.Repository;

import java.util.List;

public interface UserRepository extends Repository<UserModel, Integer> {

    UserModel findById(Integer userId);

    void save(UserModel user);

}
