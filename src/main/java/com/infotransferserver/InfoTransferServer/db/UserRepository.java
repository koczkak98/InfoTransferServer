package com.infotransferserver.InfoTransferServer.db;

import com.infotransferserver.InfoTransferServer.model.UserModel;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface UserRepository extends Repository<UserModel, Integer> {

    List<UserModel> findAll();

    UserModel findById(Integer userId);

    List<UserModel> findByName(String name);

    void save(UserModel user);


}
