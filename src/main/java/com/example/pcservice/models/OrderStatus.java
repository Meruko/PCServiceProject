package com.example.pcservice.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@Table(name = "orderstatus")
public class OrderStatus extends AbstractEntity implements Serializable, GenericEntity<OrderStatus> {
//    IN_PROCESSING("В обработке"), IN_PROCESS("Выполняется"), COMPLETED("Выполнен"), CANCELED("Отменён");

    @NotBlank(message = "Name is required")
    @Size(min = 1, max = 100, message = "Size must be between 1 and 100")
    private String name;
//    @OneToMany(mappedBy = "orderStatus", cascade = CascadeType.ALL)
//    @JsonIgnore
//    private List<Order> orders;

    @Override
    public void update(OrderStatus source) {
        this.name = source.name;
//        this.orders = source.orders;
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public OrderStatus createNewInstance() {
        OrderStatus newInstance = new OrderStatus();
        newInstance.update(this);

        return newInstance;
    }
}
