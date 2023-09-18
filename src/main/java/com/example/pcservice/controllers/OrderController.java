package com.example.pcservice.controllers;

import com.example.pcservice.api.ApiHelper;
import com.example.pcservice.models.*;
import com.example.pcservice.models.Order;
import com.example.pcservice.restclients.*;
import com.example.pcservice.summaries.OrderSummary;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/orders")
@PreAuthorize("hasAnyAuthority('1')")
public class OrderController extends AbstractController<Order, OrderSummary>{
    private final OrderClient orderClient = new OrderClient();
    private final OrderTypeClient orderTypeClient = new OrderTypeClient();
    private final OrderStatusClient orderStatusClient = new OrderStatusClient();
    private final ClientPCClient clientPCClient = new ClientPCClient();

    public OrderController() {
        super("order");
    }

    @Override
    @GetMapping()
    public String index(Model model) {
        List<Order> orders = orderClient.findAll()
                .stream().filter(o -> o.getClientPC().getClient().getUsername().equals(MainController.getUsername()))
                .toList();
        model.addAttribute(name+"s", orders);
        return name+"/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        model.addAttribute(name, orderClient.findById(id));
        return name+"/show";
    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "id", defaultValue = "-1") long id, Model model) {
        Order order = orderClient.findById(id);
        if (!order.getClientPC().getClient().getUsername().equals(MainController.getUsername()))
            throw new IllegalArgumentException("Invalid "+name+" Id:" + id);

        model.addAttribute(name, orderClient.findById(id));
        return name+"/show";
    }

    @Override
    @GetMapping("/new")
    public String newEntity(@ModelAttribute("order") Order entity, Model model) {
        List<ClientPC> pcs = clientPCClient.findAll()
                .stream().filter(pc -> pc.getClient().getUsername().equals(MainController.getUsername())).toList();
        model.addAttribute("pcs", pcs);
        model.addAttribute("orderTypes", orderTypeClient.findAll());

        return name+"/new";
    }

    @PostMapping()
    public String create(@RequestParam("clientPC") long pc,
                         @RequestParam("orderType") long orderType, Order entity, BindingResult result) {
        entity.setCreatedAt(new Date());
        entity.setClientPC(clientPCClient.findById(pc));
        entity.setPrice(Math.ceil(new Random().nextDouble(100000) * 10));
        entity.setOrderType(orderTypeClient.findById(orderType));
        entity.setOrderStatus(orderStatusClient.findAll()
                .stream().filter(os -> os.getName().equals("В обработке")).findFirst().orElseThrow());

        orderClient.create(entity);
        return "redirect:/"+name+"s";
    }

    @Override
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        List<ClientPC> pcs = clientPCClient.findAll()
                .stream().filter(pc -> pc.getClient().getUsername().equals(MainController.getUsername())).toList();
        model.addAttribute("pcs", pcs);
        model.addAttribute("orderTypes", orderTypeClient.findAll());

        model.addAttribute(name, orderClient.findById(id));
        return name+"/edit";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") long id,
                         @RequestParam("clientPC") long pc,
                         @RequestParam("orderType") long orderType,
                         Order entity, BindingResult result) {
        Order order = orderClient.findById(id);
        order.setClientPC(clientPCClient.findById(pc));
        order.setOrderType(orderTypeClient.findById(orderType));

        orderClient.update(order);
        return "redirect:/"+name+"s";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") long id) {
        Order order = orderClient.findById(id);
        order.setOrderStatus(orderStatusClient.findAll()
                .stream().filter(os -> os.getName().equals("Отменён")).findFirst().orElseThrow());

        orderClient.update(order);
        return "redirect:/"+name+"s";
    }
}
