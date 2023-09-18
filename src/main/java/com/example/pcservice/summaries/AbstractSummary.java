package com.example.pcservice.summaries;

import com.example.pcservice.models.GenericEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AbstractSummary<T extends GenericEntity<T>> {
    private List<T> content;
}
