package com.example.pcservice.summaries;

import com.example.pcservice.models.ClientPC;
import com.example.pcservice.models.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientPCSummary extends AbstractSummary<ClientPC> {
}
