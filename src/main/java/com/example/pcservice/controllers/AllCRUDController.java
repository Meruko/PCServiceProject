package com.example.pcservice.controllers;

import com.example.pcservice.models.*;
import com.example.pcservice.models.ClientPC;
import com.example.pcservice.restclients.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/allcrud")
@PreAuthorize("hasAnyAuthority('4')")
public class AllCRUDController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    //region Clients
    private final OrderClient orderClient = new OrderClient();
    private final OrderTypeClient orderTypeClient = new OrderTypeClient();
    private final OrderStatusClient orderStatusClient = new OrderStatusClient();
    private final ClientPCClient clientPCClient = new ClientPCClient();
    private final PCTypeClient pcTypeClient = new PCTypeClient();
    private final PCMarkClient pcMarkClient = new PCMarkClient();
    private final UserClient userClient = new UserClient();
    private final RoleClient roleClient = new RoleClient();
    //endregion

    //region Users
    @GetMapping("/users")
    public String indexUsers(Model model) {
        List<User> users = userClient.findAll();
        users.removeIf(u -> u.getUsername().equals(MainController.getUsername()));

        model.addAttribute("users", users);
        return "allcrud/users/index";
    }

    @GetMapping("/users/search")
    public String searchUsers(@RequestParam(value = "id", defaultValue = "-1") long id, Model model) {
        User user = userClient.findById(id);
        if (user.getUsername().equals(MainController.getUsername()))
            throw new IllegalArgumentException("Invalid user Id:" + id);

        model.addAttribute("user", user);
        return "allcrud/users/show";
    }

    @GetMapping("/users/{id}")
    public String showUsers(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userClient.findById(id));
        return "allcrud/users/show";
    }

    @GetMapping("/users/new")
    public String newEntityUsers(@ModelAttribute("user") User entity, Model model) {
        model.addAttribute("roles", roleClient.findAll());

        return "allcrud/users/new";
    }

    @PostMapping("/users")
    public String createUsers(@RequestParam("role") long role,
                              @RequestParam("username") String username,
                              @RequestParam("password") String password,
                              @RequestParam("FIO") String FIO) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(roleClient.findById(role));
        user.setFIO(FIO);
        user.setActive(true);

        userClient.create(user);
        return "redirect:/allcrud/users";
    }

    @GetMapping("/users/{id}/edit")
    public String editUsers(Model model, @PathVariable("id") long id) {
        model.addAttribute("roles", roleClient.findAll());

        model.addAttribute("user", userClient.findById(id));
        return "allcrud/users/edit";
    }

    @PostMapping("/users/{id}")
    public String updateUsers(@PathVariable("id") long id,
                              @RequestParam("role") long role,
                              @RequestParam("username") String username,
                              @RequestParam("password") String password,
                              @RequestParam("FIO") String FIO) {
        User user = userClient.findById(id);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(roleClient.findById(role));
        user.setFIO(FIO);

        userClient.update(user);
        return "redirect:/allcrud/users";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUsers(@PathVariable("id") long id) {
        userClient.deleteById(id);
        return "redirect:/allcrud/users";
    }
    //endregion

    //region Orders
    @GetMapping("/orders")
    public String indexOrders(Model model) {
        List<Order> orders = orderClient.findAll();

        model.addAttribute("orders", orders);
        return "allcrud/orders/index";
    }

    @GetMapping("/orders/search")
    public String searchOrders(@RequestParam(value = "id", defaultValue = "-1") long id, Model model) {
        Order order = orderClient.findById(id);

        model.addAttribute("order", order);
        return "allcrud/orders/show";
    }

    @GetMapping("/orders/{id}")
    public String showOrders(@PathVariable("id") long id, Model model) {
        model.addAttribute("order", orderClient.findById(id));
        return "allcrud/orders/show";
    }

    @GetMapping("/orders/new")
    public String newEntityOrders(@ModelAttribute("order") Order entity, Model model) {
        List<User> employees = userClient.findAll().stream().filter(u -> u.getRole().getName().equals("EMPLOYEE")).toList();
        if (employees.isEmpty())
            throw new IllegalArgumentException("Сначала создайте хотя бы одного сотрудника!");
        model.addAttribute("employees", employees);

        model.addAttribute("pcs", clientPCClient.findAll());

        model.addAttribute("ordertypes", orderTypeClient.findAll());
        model.addAttribute("orderstatuses", orderStatusClient.findAll());

        return "allcrud/orders/new";
    }

    @PostMapping("/orders")
    public String createOrders(@RequestParam("price") double price,
                               @RequestParam("ordertype") long ordertype,
                               @RequestParam("orderstatus") long orderstatus,
                               @RequestParam("clientPC") long clientPC,
                               @RequestParam("employee") long employee) {
        Order order = new Order();
        order.setCreatedAt(new Date());
        order.setPrice(price);
        order.setOrderType(orderTypeClient.findById(ordertype));
        order.setOrderStatus(orderStatusClient.findById(orderstatus));
        order.setClientPC(clientPCClient.findById(clientPC));
        order.setEmployee(userClient.findById(employee));

        orderClient.create(order);
        return "redirect:/allcrud/orders";
    }

    @GetMapping("/orders/{id}/edit")
    public String editOrders(Model model, @PathVariable("id") long id) {
        List<User> employees = userClient.findAll().stream().filter(u -> u.getRole().getName().equals("EMPLOYEE")).toList();
        if (employees.isEmpty())
            throw new IllegalArgumentException("Сначала создайте хотя бы одного сотрудника!");

        model.addAttribute("employees", employees);

        model.addAttribute("pcs", clientPCClient.findAll());

        model.addAttribute("ordertypes", orderTypeClient.findAll());
        model.addAttribute("orderstatuses", orderStatusClient.findAll());

        Order order = orderClient.findById(id);
        if (order.getEmployee() == null)
            order.setEmployee(new User());

        model.addAttribute("order", order);
        return "allcrud/orders/edit";
    }

    @PostMapping("/orders/{id}")
    public String updateOrders(@PathVariable("id") long id,
                               @RequestParam("price") double price,
                               @RequestParam("ordertype") long ordertype,
                               @RequestParam("orderstatus") long orderstatus,
                               @RequestParam("clientPC") long clientPC,
                               @RequestParam("employee") long employee) {
        Order order = orderClient.findById(id);
        order.setPrice(price);
        order.setOrderType(orderTypeClient.findById(ordertype));
        order.setOrderStatus(orderStatusClient.findById(orderstatus));
        order.setClientPC(clientPCClient.findById(clientPC));
        order.setEmployee(userClient.findById(employee));

        orderClient.update(order);
        return "redirect:/allcrud/orders";
    }

    @GetMapping("/orders/delete/{id}")
    public String deleteOrders(@PathVariable("id") long id) {
        orderClient.deleteById(id);
        return "redirect:/allcrud/orders";
    }
    //endregion

    //region ClientPCs
    @GetMapping("/pcs")
    public String indexPcs(Model model) {
        List<ClientPC> pcs = clientPCClient.findAll();

        model.addAttribute("pcs", pcs);
        return "allcrud/pcs/index";
    }

    @GetMapping("/pcs/search")
    public String searchPcs(@RequestParam(value = "id", defaultValue = "-1") long id, Model model) {
        ClientPC clientPC = clientPCClient.findById(id);

        model.addAttribute("clientPC", clientPC);
        return "allcrud/pcs/show";
    }

    @GetMapping("/pcs/{id}")
    public String showPcs(@PathVariable("id") long id, Model model) {
        model.addAttribute("clientPC", clientPCClient.findById(id));
        return "allcrud/pcs/show";
    }

    @GetMapping("/pcs/new")
    public String newEntityPcs(@ModelAttribute("clientPC") ClientPC entity, Model model) {
        model.addAttribute("pctypes", pcTypeClient.findAll());
        model.addAttribute("pcmarks", pcMarkClient.findAll());

        List<User> clients = userClient.findAll().stream().filter(u -> u.getRole().getName().equals("USER")).toList();
        model.addAttribute("clients", clients);

        return "allcrud/pcs/new";
    }

    @PostMapping("/pcs")
    public String createPcs(@RequestParam("client") long client,
                            @RequestParam("model") String model,
                            @RequestParam("pctype") long pctype,
                            @RequestParam("pcmark") long pcmark) {
        ClientPC clientPC = new ClientPC();
        clientPC.setModel(model);
        clientPC.setPcMark(pcMarkClient.findById(pcmark));
        clientPC.setPcType(pcTypeClient.findById(pctype));
        clientPC.setClient(userClient.findById(client));

        clientPCClient.create(clientPC);
        return "redirect:/allcrud/pcs";
    }

    @GetMapping("/pcs/{id}/edit")
    public String editPcs(Model model, @PathVariable("id") long id) {
        model.addAttribute("pctypes", pcTypeClient.findAll());
        model.addAttribute("pcmarks", pcMarkClient.findAll());

        List<User> clients = userClient.findAll().stream().filter(u -> u.getRole().getName().equals("USER")).toList();
        model.addAttribute("clients", clients);

        model.addAttribute("clientPC", clientPCClient.findById(id));
        return "allcrud/pcs/edit";
    }

    @PostMapping("/pcs/{id}")
    public String updatePcs(@PathVariable("id") long id,
                            @RequestParam("client") long client,
                            @RequestParam("model") String model,
                            @RequestParam("pctype") long pctype,
                            @RequestParam("pcmark") long pcmark) {
        ClientPC clientPC = clientPCClient.findById(id);
        clientPC.setModel(model);
        clientPC.setPcMark(pcMarkClient.findById(pcmark));
        clientPC.setPcType(pcTypeClient.findById(pctype));
        clientPC.setClient(userClient.findById(client));

        clientPCClient.update(clientPC);
        return "redirect:/allcrud/pcs";
    }

    @GetMapping("/pcs/delete/{id}")
    public String deletePcs(@PathVariable("id") long id) {
        clientPCClient.deleteById(id);
        return "redirect:/allcrud/pcs";
    }
    //endregion

    //region OrderTypes
    @GetMapping("/ordertypes")
    public String indexOrderTypes(Model model) {
        List<OrderType> ordertypes = orderTypeClient.findAll();

        model.addAttribute("ordertypes", ordertypes);
        return "allcrud/ordertypes/index";
    }

    @GetMapping("/ordertypes/search")
    public String searchOrderTypes(@RequestParam(value = "id", defaultValue = "-1") long id, Model model) {
        OrderType ordertype = orderTypeClient.findById(id);

        model.addAttribute("ordertype", ordertype);
        return "allcrud/ordertypes/show";
    }

    @GetMapping("/ordertypes/{id}")
    public String showOrderTypes(@PathVariable("id") long id, Model model) {
        model.addAttribute("ordertype", orderTypeClient.findById(id));
        return "allcrud/ordertypes/show";
    }

    @GetMapping("/ordertypes/new")
    public String newEntityOrderTypes(@ModelAttribute("ordertype") OrderType entity, Model model) {
        return "allcrud/ordertypes/new";
    }

    @PostMapping("/ordertypes")
    public String createOrderTypes(@RequestParam("name") String name) {
        OrderType existing = orderTypeClient.findAll().stream().filter(e -> e.getName().equals(name)).findFirst().orElse(null);
        if (existing != null)
            throw new IllegalArgumentException("OrderType with this name already exists");

        OrderType ordertype = new OrderType();
        ordertype.setName(name);

        orderTypeClient.create(ordertype);
        return "redirect:/allcrud/ordertypes";
    }

    @GetMapping("/ordertypes/{id}/edit")
    public String editOrderTypes(Model model, @PathVariable("id") long id) {
        model.addAttribute("ordertype", orderTypeClient.findById(id));
        return "allcrud/ordertypes/edit";
    }

    @PostMapping("/ordertypes/{id}")
    public String updateOrderTypes(@PathVariable("id") long id,
                                   @RequestParam("name") String name) {
        OrderType existing = orderTypeClient.findAll().stream().filter(e -> e.getName().equals(name)).findFirst().orElse(null);
        if (existing != null)
            throw new IllegalArgumentException("OrderType with this name already exists");

        OrderType ordertype = orderTypeClient.findById(id);
        ordertype.setName(name);

        orderTypeClient.update(ordertype);
        return "redirect:/allcrud/ordertypes";
    }

    @GetMapping("/ordertypes/delete/{id}")
    public String deleteOrderTypes(@PathVariable("id") long id) {
        orderTypeClient.deleteById(id);
        return "redirect:/allcrud/ordertypes";
    }
    //endregion

    //region OrderStatuses
    @GetMapping("/orderstatuses")
    public String indexOrderStatuses(Model model) {
        List<OrderStatus> orderstatuses = orderStatusClient.findAll();

        model.addAttribute("orderstatuses", orderstatuses);
        return "allcrud/orderstatuses/index";
    }

    @GetMapping("/orderstatuses/search")
    public String searchOrderStatuses(@RequestParam(value = "id", defaultValue = "-1") long id, Model model) {
        OrderStatus orderstatus = orderStatusClient.findById(id);

        model.addAttribute("orderstatus", orderstatus);
        return "allcrud/orderstatuses/show";
    }

    @GetMapping("/orderstatuses/{id}")
    public String showOrderStatuses(@PathVariable("id") long id, Model model) {
        model.addAttribute("orderstatus", orderStatusClient.findById(id));
        return "allcrud/orderstatuses/show";
    }

    @GetMapping("/orderstatuses/new")
    public String newEntityOrderStatuses(@ModelAttribute("orderstatus") OrderStatus entity, Model model) {
        return "allcrud/orderstatuses/new";
    }

    @PostMapping("/orderstatuses")
    public String createOrderStatuses(@RequestParam("name") String name) {
        OrderStatus existing = orderStatusClient.findAll().stream().filter(e -> e.getName().equals(name)).findFirst().orElse(null);
        if (existing != null)
            throw new IllegalArgumentException("OrderStatus with this name already exists");

        OrderStatus orderstatus = new OrderStatus();
        orderstatus.setName(name);

        orderStatusClient.create(orderstatus);
        return "redirect:/allcrud/orderstatuses";
    }

    @GetMapping("/orderstatuses/{id}/edit")
    public String editOrderStatuses(Model model, @PathVariable("id") long id) {
        model.addAttribute("orderstatus", orderStatusClient.findById(id));
        return "allcrud/orderstatuses/edit";
    }

    @PostMapping("/orderstatuses/{id}")
    public String updateOrderStatuses(@PathVariable("id") long id,
                                      @RequestParam("name") String name) {
        OrderStatus existing = orderStatusClient.findAll().stream().filter(e -> e.getName().equals(name)).findFirst().orElse(null);
        if (existing != null)
            throw new IllegalArgumentException("OrderStatus with this name already exists");

        OrderStatus orderstatus = orderStatusClient.findById(id);
        orderstatus.setName(name);

        orderStatusClient.update(orderstatus);
        return "redirect:/allcrud/orderstatuses";
    }

    @GetMapping("/orderstatuses/delete/{id}")
    public String deleteOrderStatuses(@PathVariable("id") long id) {
        orderStatusClient.deleteById(id);
        return "redirect:/allcrud/orderstatuses";
    }
    //endregion

    //region PCMarks
    @GetMapping("/pcmarks")
    public String indexPCMarks(Model model) {
        List<PCMark> pcmarks = pcMarkClient.findAll();

        model.addAttribute("pcmarks", pcmarks);
        return "allcrud/pcmarks/index";
    }

    @GetMapping("/pcmarks/search")
    public String searchPCMarks(@RequestParam(value = "id", defaultValue = "-1") long id, Model model) {
        PCMark pcmark = pcMarkClient.findById(id);

        model.addAttribute("pcmark", pcmark);
        return "allcrud/pcmarks/show";
    }

    @GetMapping("/pcmarks/{id}")
    public String showPCMarks(@PathVariable("id") long id, Model model) {
        model.addAttribute("pcmark", pcMarkClient.findById(id));
        return "allcrud/pcmarks/show";
    }

    @GetMapping("/pcmarks/new")
    public String newEntityPCMarks(@ModelAttribute("pcmark") PCMark entity, Model model) {
        return "allcrud/pcmarks/new";
    }

    @PostMapping("/pcmarks")
    public String createPCMarks(@RequestParam("name") String name) {
        PCMark existing = pcMarkClient.findAll().stream().filter(e -> e.getName().equals(name)).findFirst().orElse(null);
        if (existing != null)
            throw new IllegalArgumentException("PCMark with this name already exists");

        PCMark pcmark = new PCMark();
        pcmark.setName(name);

        pcMarkClient.create(pcmark);
        return "redirect:/allcrud/pcmarks";
    }

    @GetMapping("/pcmarks/{id}/edit")
    public String editPCMarks(Model model, @PathVariable("id") long id) {
        model.addAttribute("pcmark", pcMarkClient.findById(id));
        return "allcrud/pcmarks/edit";
    }

    @PostMapping("/pcmarks/{id}")
    public String updatePCMarks(@PathVariable("id") long id,
                                @RequestParam("name") String name) {
        PCMark existing = pcMarkClient.findAll().stream().filter(e -> e.getName().equals(name)).findFirst().orElse(null);
        if (existing != null)
            throw new IllegalArgumentException("PCMark with this name already exists");

        PCMark pcmark = pcMarkClient.findById(id);
        pcmark.setName(name);

        pcMarkClient.update(pcmark);
        return "redirect:/allcrud/pcmarks";
    }

    @GetMapping("/pcmarks/delete/{id}")
    public String deletePCMarks(@PathVariable("id") long id) {
        pcMarkClient.deleteById(id);
        return "redirect:/allcrud/pcmarks";
    }
    //endregion

    //region PCTypes
    @GetMapping("/pctypes")
    public String indexPCTypes(Model model) {
        List<PCType> pctypes = pcTypeClient.findAll();

        model.addAttribute("pctypes", pctypes);
        return "allcrud/pctypes/index";
    }

    @GetMapping("/pctypes/search")
    public String searchPCTypes(@RequestParam(value = "id", defaultValue = "-1") long id, Model model) {
        PCType pctype = pcTypeClient.findById(id);

        model.addAttribute("pctype", pctype);
        return "allcrud/pctypes/show";
    }

    @GetMapping("/pctypes/{id}")
    public String showPCTypes(@PathVariable("id") long id, Model model) {
        model.addAttribute("pctype", pcTypeClient.findById(id));
        return "allcrud/pctypes/show";
    }

    @GetMapping("/pctypes/new")
    public String newEntityPCTypes(@ModelAttribute("pctype") PCType entity, Model model) {
        return "allcrud/pctypes/new";
    }

    @PostMapping("/pctypes")
    public String createPCTypes(@RequestParam("name") String name) {
        PCType existing = pcTypeClient.findAll().stream().filter(e -> e.getName().equals(name)).findFirst().orElse(null);
        if (existing != null)
            throw new IllegalArgumentException("PCType with this name already exists");

        PCType pctype = new PCType();
        pctype.setName(name);

        pcTypeClient.create(pctype);
        return "redirect:/allcrud/pctypes";
    }

    @GetMapping("/pctypes/{id}/edit")
    public String editPCTypes(Model model, @PathVariable("id") long id) {
        model.addAttribute("pctype", pcTypeClient.findById(id));
        return "allcrud/pctypes/edit";
    }

    @PostMapping("/pctypes/{id}")
    public String updatePCTypes(@PathVariable("id") long id,
                                @RequestParam("name") String name) {
        PCType existing = pcTypeClient.findAll().stream().filter(e -> e.getName().equals(name)).findFirst().orElse(null);
        if (existing != null)
            throw new IllegalArgumentException("PCType with this name already exists");

        PCType pctype = pcTypeClient.findById(id);
        pctype.setName(name);

        pcTypeClient.update(pctype);
        return "redirect:/allcrud/pctypes";
    }

    @GetMapping("/pctypes/delete/{id}")
    public String deletePCTypes(@PathVariable("id") long id) {
        pcTypeClient.deleteById(id);
        return "redirect:/allcrud/pctypes";
    }
    //endregion

    //region Roles
    @GetMapping("/roles")
    public String indexRoles(Model model) {
        List<Role> roles = roleClient.findAll();
        roles.removeIf(r -> r.getId() >= 1 && r.getId() <= 4);

        model.addAttribute("roles", roles);
        return "allcrud/roles/index";
    }

    @GetMapping("/roles/search")
    public String searchRoles(@RequestParam(value = "id", defaultValue = "-1") long id, Model model) {
        if (id >= 1 && id <= 4)
            throw new IllegalArgumentException("Invalid role Id:" + id);

        Role role = roleClient.findById(id);

        model.addAttribute("role", role);
        return "allcrud/roles/show";
    }

    @GetMapping("/roles/{id}")
    public String showRoles(@PathVariable("id") long id, Model model) {
        model.addAttribute("role", roleClient.findById(id));
        return "allcrud/roles/show";
    }

    @GetMapping("/roles/new")
    public String newEntityRoles(@ModelAttribute("role") Role entity, Model model) {
        return "allcrud/roles/new";
    }

    @PostMapping("/roles")
    public String createRoles(@RequestParam("name") String name) {
        Role existing = roleClient.findAll().stream().filter(e -> e.getName().equals(name)).findFirst().orElse(null);
        if (existing != null)
            throw new IllegalArgumentException("Role with this name already exists");

        Role role = new Role();
        role.setName(name);

        roleClient.create(role);
        return "redirect:/allcrud/roles";
    }

    @GetMapping("/roles/{id}/edit")
    public String editRoles(Model model, @PathVariable("id") long id) {
        model.addAttribute("role", roleClient.findById(id));
        return "allcrud/roles/edit";
    }

    @PostMapping("/roles/{id}")
    public String updateRoles(@PathVariable("id") long id,
                                @RequestParam("name") String name) {
        Role existing = roleClient.findAll().stream().filter(e -> e.getName().equals(name)).findFirst().orElse(null);
        if (existing != null)
            throw new IllegalArgumentException("Role with this name already exists");

        Role role = roleClient.findById(id);
        role.setName(name);

        roleClient.update(role);
        return "redirect:/allcrud/roles";
    }

    @GetMapping("/roles/delete/{id}")
    public String deleteRoles(@PathVariable("id") long id) {
        if (id >= 1 && id <= 4)
            throw new IllegalArgumentException("Invalid role Id:" + id);

        roleClient.deleteById(id);
        return "redirect:/allcrud/roles";
    }
    //endregion
}
