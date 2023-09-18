package com.example.pcserviceapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Entity
@Table(name = "user_order")
public class Order extends AbstractEntity implements Serializable, GenericEntity<Order> {
    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

//    @NotNull(message = "Price is required")
//    @Range(min = 1, max = 100000, message = "Price must be between 1 and 100000")
    private double price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ordertype")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private OrderType orderType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "orderstatus")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private OrderStatus orderStatus;

    @NotNull(message = "Client PC is required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clientPC_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ClientPC clientPC;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User employee;

    @Override
    public void update(Order source) {
        this.createdAt = source.createdAt;
        this.price = source.price;
        this.orderStatus = source.orderStatus;
        this.orderType = source.orderType;
        this.clientPC = source.clientPC;
        this.employee = source.employee;
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public Order createNewInstance() {
        Order newInstance = new Order();
        newInstance.update(this);

        return newInstance;
    }

    public String getName(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return "ID: " + this.id + ". " + format.format(getCreatedAt()) + " - " + getClientPC().getName() + " " +
                getOrderType().getName() + "(" + getOrderStatus().getName() + ")";
    }

    public String getNameEmp(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return "ID: " + this.id + ". " + format.format(getCreatedAt()) + " - " + getClientPC().getName() + " " +
                getOrderType().getName() + "(" + getOrderStatus().getName() + ") --- " +
                getClientPC().getClient().getUsername() + " " + getPrice() + " руб.";
    }
}
