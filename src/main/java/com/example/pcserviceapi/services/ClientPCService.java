package com.example.pcserviceapi.services;

import com.example.pcserviceapi.models.ClientPC;
import com.example.pcserviceapi.models.Order;
import com.example.pcserviceapi.repositories.ClientPCRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientPCService extends GenericService<ClientPC>{
    public ClientPCService(ClientPCRepository repository) {
        super(repository);
    }
}
