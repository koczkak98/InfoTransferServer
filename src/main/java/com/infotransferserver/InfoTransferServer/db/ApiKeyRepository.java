package com.infotransferserver.InfoTransferServer.db;

import com.infotransferserver.InfoTransferServer.model.ApiKey;
import org.springframework.data.repository.Repository;

public interface ApiKeyRepository extends Repository<ApiKey, Integer> {

    ApiKey findByApiKey(String apiKey);

}
