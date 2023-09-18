package com.example.pcservice.controllers;

import com.example.pcservice.models.ClientPC;
import com.example.pcservice.models.Order;
import com.example.pcservice.models.OrderType;
import com.example.pcservice.restclients.*;
import com.example.pcservice.summaries.OrderSummary;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orderEmps")
@PreAuthorize("hasAnyAuthority('3')")
public class OrderEmpController extends AbstractController<Order, OrderSummary>{
    private final UserClient userClient = new UserClient();
    private final OrderClient orderClient = new OrderClient();
    private final OrderTypeClient orderTypeClient = new OrderTypeClient();
    private final OrderStatusClient orderStatusClient = new OrderStatusClient();
    private final ClientPCClient clientPCClient = new ClientPCClient();

    public OrderEmpController() {
        super("orderEmp");
    }

    @GetMapping("/processing")
    public String indexProcessing(Model model) {
        model.addAttribute(name+"s", orderClient.findAll().stream()
                .filter(order -> order.getOrderStatus().getName().equals("В обработке")).toList());
        model.addAttribute("processing", true);
        return name+"/index";
    }

    @GetMapping("/process")
    public String indexProcess(Model model) {
        model.addAttribute(name+"s", orderClient.findAll().stream()
                .filter(order -> order.getOrderStatus().getName().equals("Выполняется") &&
                        order.getEmployee().getUsername().equals(MainController.getUsername())).toList());
        model.addAttribute("process", true);
        return name+"/index";
    }

    @GetMapping("/completed")
    public String indexCompleted(Model model) {
        model.addAttribute(name+"s", orderClient.findAll().stream()
                .filter(order -> order.getOrderStatus().getName().equals("Выполнен") &&
                        order.getEmployee().getUsername().equals(MainController.getUsername())).toList());
        model.addAttribute("completed", true);
        return name+"/index";
    }

    @GetMapping("/cancelled")
    public String indexCancelled(Model model) {
        model.addAttribute(name+"s", orderClient.findAll().stream()
                .filter(order -> order.getOrderStatus().getName().equals("Отменён") &&
                        order.getEmployee() != null &&
                        order.getEmployee().getUsername().equals(MainController.getUsername())).toList());
        model.addAttribute("cancelled", true);
        return name+"/index";
    }

    @PostMapping("/{id}/take")
    public String takeOrder(@PathVariable("id") long id){
        Order order = orderClient.findById(id);
        order.setOrderStatus(orderStatusClient.findAll()
                .stream().filter(os -> os.getName().equals("Выполняется")).findFirst().orElseThrow());
        order.setEmployee(userClient.findAll()
                .stream().filter(u -> u.getUsername().equals(MainController.getUsername())).findFirst().orElseThrow());

        orderClient.update(order);
        return "redirect:/"+name+"s/processing";
    }

    @PostMapping("/{id}/complete")
    public String completeOrder(@PathVariable("id") long id){
        Order order = orderClient.findById(id);
        order.setOrderStatus(orderStatusClient.findAll()
                .stream().filter(os -> os.getName().equals("Выполнен")).findFirst().orElseThrow());
        order.setEmployee(userClient.findAll()
                .stream().filter(u -> u.getUsername().equals(MainController.getUsername())).findFirst().orElseThrow());

        orderClient.update(order);
        return "redirect:/"+name+"s/process";
    }

    @PostMapping("/{id}/cancel")
    public String cancelOrder(@PathVariable("id") long id){
        Order order = orderClient.findById(id);
        order.setOrderStatus(orderStatusClient.findAll()
                .stream().filter(os -> os.getName().equals("Отменён")).findFirst().orElseThrow());
        order.setEmployee(userClient.findAll()
                .stream().filter(u -> u.getUsername().equals(MainController.getUsername())).findFirst().orElseThrow());

        orderClient.update(order);
        return "redirect:/"+name+"s/processing";
    }

    @PostMapping("/{id}/cancel2")
    public String cancel2Order(@PathVariable("id") long id){
        Order order = orderClient.findById(id);
        order.setOrderStatus(orderStatusClient.findAll()
                .stream().filter(os -> os.getName().equals("Отменён")).findFirst().orElseThrow());
        order.setEmployee(userClient.findAll()
                .stream().filter(u -> u.getUsername().equals(MainController.getUsername())).findFirst().orElseThrow());

        orderClient.update(order);
        return "redirect:/"+name+"s/process";
    }

    @Override
    @GetMapping("/new")
    public String newEntity(@ModelAttribute("orderEmp") Order entity, Model model) {
        //Empty
        return name+"/new";
    }

    @PostMapping()
    public String create(@RequestParam("clientPC") long pc, Order entity, BindingResult result) {
        //Empty
        return "redirect:/"+name+"s";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") long id,
                         @RequestParam("clientPC") long pc,
                         @RequestParam("orderType") OrderType orderType,
                         Order entity, BindingResult result) {
        Order orderEmp = orderClient.findById(id);
        orderEmp.setClientPC(clientPCClient.findById(pc));
        orderEmp.setOrderType(orderType);

        orderClient.update(orderEmp);
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

    @GetMapping("/search/processing")
    public String searchProcessing(@RequestParam(value = "id", defaultValue = "-1") long id, Model model) {
        Order order = orderClient.findById(id);
        if (!order.getOrderStatus().getName().equals("В обработке"))
            throw new IllegalArgumentException("Invalid "+name+" Id:" + id);

        model.addAttribute(name, order);
        return name+"/show";
    }

    @GetMapping("/search/process")
    public String searchProcess(@RequestParam(value = "id", defaultValue = "-1") long id, Model model) {
        Order order = orderClient.findById(id);
        if (!order.getOrderStatus().getName().equals("Выполняется"))
            throw new IllegalArgumentException("Invalid "+name+" Id:" + id);

        model.addAttribute(name, order);
        return name+"/show";
    }

    @GetMapping("/search/completed")
    public String searchCompleted(@RequestParam(value = "id", defaultValue = "-1") long id, Model model) {
        Order order = orderClient.findById(id);
        if (!order.getOrderStatus().getName().equals("Выполнен"))
            throw new IllegalArgumentException("Invalid "+name+" Id:" + id);

        model.addAttribute(name, order);
        return name+"/show";
    }

    @GetMapping("/search/cancelled")
    public String searchCancelled(@RequestParam(value = "id", defaultValue = "-1") long id, Model model) {
        Order order = orderClient.findById(id);
        if (!order.getOrderStatus().getName().equals("Отменён"))
            throw new IllegalArgumentException("Invalid "+name+" Id:" + id);

        model.addAttribute(name, order);
        return name+"/show";
    }
}
