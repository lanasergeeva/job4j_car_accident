package ru.job4j.accident.control;

import org.springframework.security.core.context.SecurityContextHolder;
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
        model.addAttribute("user",
                SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Accident accident = new Accident();
        extracted(model, accident);
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
        model.addAttribute("user",
                SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Accident accident = services.findById(id);
        extracted(model, accident);
        return "create";
    }

    @RequestMapping("/delete")
    public String deleteEmployee(@RequestParam("acId") int id) {
        services.deleteAccident(id);
        return "redirect:/";
    }

    private void extracted(Model model, Accident accident) {
        model.addAttribute("accident", accident);
        List<AccidentType> types = services.findAllTypes();
        model.addAttribute("types", types);
        List<Rule> rules = services.findAllRules();
        model.addAttribute("rules", rules);
    }
}
