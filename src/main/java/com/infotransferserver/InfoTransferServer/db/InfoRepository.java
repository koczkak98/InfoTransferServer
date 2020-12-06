package com.infotransferserver.InfoTransferServer.db;

import com.infotransferserver.InfoTransferServer.model.InfoModel;
import org.springframework.data.repository.Repository;

public interface InfoRepository extends Repository<InfoModel, Integer> {

    InfoModel findById (Integer id);
    void save(InfoModel info);
}
