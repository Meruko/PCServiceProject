package com.example.pcservice.models;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
public class ClientPC extends AbstractEntity implements Serializable, GenericEntity<ClientPC> {
    @NotBlank(message = "Model is required")
    @Size(min = 1, max = 100, message = "Size must be between 1 and 100!")
    private String model;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pctype")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PCType pcType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pcmark")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PCMark pcMark;

//    @NotNull(message = "Client is required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User client;

//    @OneToMany(mappedBy = "clientPC", cascade = CascadeType.ALL)
//    @JsonIgnore
//    private List<Order> orders;

    @Override
    public void update(ClientPC source) {
        this.model = source.model;
        this.pcType = source.pcType;
        this.pcMark = source.pcMark;
        this.client = source.client;
//        this.orders = source.orders;
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public ClientPC createNewInstance() {
        ClientPC newInstance = new ClientPC();
        newInstance.update(this);

        return newInstance;
    }

    public String getName() {
        return "ID: " + this.id + ". " + getModel() + " (" + getPcType().getName() + " " + getPcMark().getName() + ") -- " + getClient().getUsername();
    }
}
