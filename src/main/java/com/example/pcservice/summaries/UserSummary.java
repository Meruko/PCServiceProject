package com.example.pcservice.summaries;

import com.example.pcservice.models.AbstractEntity;
import com.example.pcservice.models.GenericEntity;
import com.example.pcservice.models.Role;
import com.example.pcservice.models.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserSummary extends AbstractSummary<User> {
}
