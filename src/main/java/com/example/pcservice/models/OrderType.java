package com.example.pcservice.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@Table(name = "ordertype")
public class OrderType extends AbstractEntity implements Serializable, GenericEntity<OrderType> {
//    REPAIRING("Ремонт"), CLEANING("Чистка");
    @NotBlank(message = "Name is required")
    @Size(min = 1, max = 100, message = "Size must be between 1 and 100")
    private String name;
//    @OneToMany(mappedBy = "orderType", cascade = CascadeType.ALL)
//    @JsonIgnore
//    private List<Order> orders;

    @Override
    public void update(OrderType source) {
        this.name = source.name;
//        this.orders = source.orders;
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public OrderType createNewInstance() {
        OrderType newInstance = new OrderType();
        newInstance.update(this);

        return newInstance;
    }
}
