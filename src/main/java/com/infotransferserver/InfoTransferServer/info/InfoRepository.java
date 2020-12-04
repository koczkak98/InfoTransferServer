package com.infotransferserver.InfoTransferServer.info;

import org.springframework.data.repository.Repository;

public interface InfoRepository extends Repository<InfoModel, Integer> {

    InfoModel findById (Integer id);
    void save(InfoModel info);
}
