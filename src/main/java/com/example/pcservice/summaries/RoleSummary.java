package com.example.pcservice.summaries;

import com.example.pcservice.models.Role;
import com.example.pcservice.models.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleSummary extends AbstractSummary<Role> {
}
