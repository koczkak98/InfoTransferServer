package com.infotransferserver.InfoTransferServer.db;

import com.infotransferserver.InfoTransferServer.model.InfoModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface InfoRepository extends Repository<InfoModel, Integer> {

    InfoModel findById (Integer id);
    void save(InfoModel info);

    @Query("SELECT info FROM InfoModel info WHERE info.date LIKE ('%' || :date || '%')")
    List<InfoModel> findByDate(@Param("date") String date);
}
