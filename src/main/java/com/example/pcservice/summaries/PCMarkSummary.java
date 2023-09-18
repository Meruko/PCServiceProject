package com.example.pcservice.summaries;

import com.example.pcservice.models.PCMark;
import com.example.pcservice.models.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PCMarkSummary extends AbstractSummary<PCMark> {
}
