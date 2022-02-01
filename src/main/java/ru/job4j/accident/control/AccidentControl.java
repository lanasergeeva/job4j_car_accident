package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accident.model.Accident;

import ru.job4j.accident.service.Services;

import java.util.List;

@Controller
public class AccidentControl {

    private Services services;

    public AccidentControl(Services services) {
        this.services = services;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Accident> all = services.findAll();
        model.addAttribute("all", all);
        return "index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Accident accident = new Accident();
        model.addAttribute("accident", accident);
        return "create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident) {
        services.save(accident);
        return "redirect:/";
    }
}
