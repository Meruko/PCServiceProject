package com.example.pcservice.controllers;

import com.example.pcservice.api.ApiHelper;
import com.example.pcservice.models.ClientPC;
import com.example.pcservice.models.PCMark;
import com.example.pcservice.models.PCType;
import com.example.pcservice.restclients.*;
import com.example.pcservice.summaries.ClientPCSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/pcs")
@PreAuthorize("hasAnyAuthority('1')")
public class PCController extends AbstractController<ClientPC, ClientPCSummary> {
    private final UserClient userClient = new UserClient();
    private final PCMarkClient pcMarkClient = new PCMarkClient();
    private final PCTypeClient pcTypeClient = new PCTypeClient();
    private final ClientPCClient clientPCClient = new ClientPCClient();

    public PCController() {
        super("clientpc");
    }

    @Override
    @GetMapping()
    public String index(Model model) {
        List<ClientPC> pcs = clientPCClient.findAll()
                .stream().filter(pc -> pc.getClient().getUsername().equals(MainController.getUsername())).toList();
        model.addAttribute(name+"s", pcs);
        return name+"/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        model.addAttribute(name, clientPCClient.findById(id));
        return name+"/show";
    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "id", defaultValue = "-1") long id, Model model) {
        ClientPC clientPC = clientPCClient.findById(id);
        if (!clientPC.getClient().getUsername().equals(MainController.getUsername()))
            throw new IllegalArgumentException("Invalid "+name+" Id:" + id);

        model.addAttribute(name, clientPCClient.findById(id));
        return name+"/show";
    }

    @Override
    @GetMapping("/new")
    public String newEntity(@ModelAttribute("pc") ClientPC entity, Model model) {
        model.addAttribute("pcMarks", pcMarkClient.findAll());
        model.addAttribute("pcTypes", pcTypeClient.findAll());

        return name+"/new";
    }

    @PostMapping()
    public String create(@RequestParam("model") String model,
                         @RequestParam("pcType") long pctype,
                         @RequestParam("pcMark") long pcmark) {
        ClientPC clientPC = new ClientPC();
        clientPC.setModel(model);
        clientPC.setPcType(pcTypeClient.findById(pctype));
        clientPC.setPcMark(pcMarkClient.findById(pcmark));
        clientPC.setClient(userClient.findAll()
                .stream().filter(u -> u.getUsername().equals(MainController.getUsername())).findFirst().orElseThrow());

        clientPCClient.create(clientPC);
        return "redirect:/pcs";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") long id,
                         @RequestParam("model") String model,
                         @RequestParam("pcType") long pctype,
                         @RequestParam("pcMark") long pcmark) {
        ClientPC clientPC = clientPCClient.findById(id);
        clientPC.setModel(model);
        clientPC.setPcType(pcTypeClient.findById(pctype));
        clientPC.setPcMark(pcMarkClient.findById(pcmark));

        clientPCClient.update(clientPC);
        return "redirect:/pcs";
    }

    @Override
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("pcMarks", pcMarkClient.findAll());
        model.addAttribute("pcTypes", pcTypeClient.findAll());

        model.addAttribute(name, clientPCClient.findById(id));
        return name+"/edit";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") long id) {
        clientPCClient.deleteById(id);
        return "redirect:/pcs";
    }
}
