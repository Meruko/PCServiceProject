package com.example.pcservice.summaries;

import com.example.pcservice.models.PCType;
import com.example.pcservice.models.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PCTypeSummary extends AbstractSummary<PCType> {
}
