package com.example.pcserviceapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "pctype")
public class PCType extends AbstractEntity implements Serializable, GenericEntity<PCType> {
//    PC("ПК"), LAPTOP("Ноутбук");
    @NotBlank(message = "Name is required")
    @Size(min = 1, max = 100, message = "Size must be between 1 and 100")
    private String name;
//    @OneToMany(mappedBy = "pcType", cascade = CascadeType.ALL)
//    @JsonIgnore
//    private List<ClientPC> pcs;

    @Override
    public void update(PCType source) {
        this.name = source.name;
//        this.pcs = source.pcs;
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public PCType createNewInstance() {
        PCType newInstance = new PCType();
        newInstance.update(this);

        return newInstance;
    }
}
