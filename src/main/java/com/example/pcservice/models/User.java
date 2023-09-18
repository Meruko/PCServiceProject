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
public class User extends AbstractEntity implements Serializable, GenericEntity<User> {
    @NotBlank(message = "Login is required")
    @Size(min = 3, max = 32, message = "Size must be between 3 and 32!")
    private String username;
    @NotBlank(message = "Password is required")
    @Size(min = 3, max = 255, message = "Size must be between 3 and 255!")
    private String password;
    @NotBlank(message = "FIO is required")
    @Size(min = 3, max = 200, message = "Size must be between 3 and 255!")
    private String FIO;
    private boolean active;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Role role;

//    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
//    private List<ClientPC> PCs;
//
//    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
//    private List<Order> orders;

    @Override
    public String toString() {
        return "ID: " + getId() + ". " + getUsername() + " (" + getRole().getName() + ")";
    }

    @Override
    public void update(User source) {
        this.active = source.active;
        this.username = source.username;
        this.password = source.password;
        this.FIO = source.FIO;
//        this.PCs = source.PCs;
        this.role = source.role;
//        this.orders = source.orders;
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public User createNewInstance() {
        User newInstance = new User();
        newInstance.update(this);

        return newInstance;
    }
}
