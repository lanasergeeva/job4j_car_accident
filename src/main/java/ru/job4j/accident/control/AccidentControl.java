package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.model.Accident;

import ru.job4j.accident.model.AccidentType;
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
        List<AccidentType> types = services.findAllTypes();
        model.addAttribute("types", types);
        System.out.println(types);
        return "create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident) {
        services.save(accident);
        return "redirect:/";
    }

    @RequestMapping("/update")
    public String updateEmployee(@RequestParam("acId") int id, Model model) {
        Accident accident = services.findById(id);
        model.addAttribute("accident", accident);
        List<AccidentType> types = services.findAllTypes();
        model.addAttribute("types", types);
        return "create";
    }

}
