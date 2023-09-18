package com.example.pcservice.controllers;

import com.example.pcservice.api.ApiHelper;
import com.example.pcservice.api.GenericRestClient;
import com.example.pcservice.models.AbstractEntity;
import com.example.pcservice.models.GenericEntity;
import com.example.pcservice.summaries.AbstractSummary;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class AbstractController<E extends GenericEntity<E>, S extends AbstractSummary<E>>{
    protected final String name;
    private GenericRestClient<E, S> genericRestClient;

    public AbstractController(String name) {
        this.name = name;
        this. genericRestClient = new GenericRestClient<>("");
    }

    @GetMapping()
    public String index(Model model) {
        List<E> list = genericRestClient.findAll(ApiHelper.getDefaultUrl() + name + "s");
        model.addAttribute(name+"s", list);
        return name+"/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        model.addAttribute(name, genericRestClient.findById(ApiHelper.getDefaultUrl() + name + "s", id));
        return name+"/show";
    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "id", defaultValue = "-1") long id, Model model) {
        model.addAttribute(name, genericRestClient.findById(ApiHelper.getDefaultUrl() + name + "s", id));
        return name+"/show";
    }

    @GetMapping("/new")
    public abstract String newEntity(@ModelAttribute E entity, Model model);

    @GetMapping("/{id}/edit")
    public abstract String edit(Model model, @PathVariable("id") long id);

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") long id) {
        genericRestClient.deleteById(ApiHelper.getDefaultUrl() + name + "s", id);
        return "redirect:/"+name+"s";
    }
}
