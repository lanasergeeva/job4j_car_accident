package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.model.Accident;

import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.service.Services;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AccidentControl {

    private final Services services;

    public AccidentControl(Services services) {
        this.services = services;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Accident> all = services.findAll();
        System.out.println(all);
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
        List<Rule> rules = services.findAllRules();
        model.addAttribute("rules", rules);
        System.out.println(rules);
        return "create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        services.addRules(accident, ids);
        services.save(accident);
        return "redirect:/";
    }

    @RequestMapping("/update")
    public String updateEmployee(@RequestParam("acId") int id, Model model) {
        Accident accident = services.findById(id);
        model.addAttribute("accident", accident);
        List<AccidentType> types = services.findAllTypes();
        model.addAttribute("types", types);
        List<Rule> rules = services.findAllRules();
        model.addAttribute("rules", rules);
        return "create";
    }

    @RequestMapping("/delete")
    public String deleteEmployee(@RequestParam("acId") int id) {
        services.deleteAccident(id);
        return "redirect:/";
    }

}
