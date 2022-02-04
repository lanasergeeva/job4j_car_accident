package ru.job4j.accident.memory.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.memory.services.MemoryServices;


import javax.servlet.http.HttpServletRequest;
import java.util.*;

/*@Controller*/
public class MemAccidentControl {

    private final MemoryServices services;

    public MemAccidentControl(MemoryServices services) {

        this.services = services;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Accident> all = services.findAll();
        model.addAttribute("accidents", all);
        System.out.println(all);
        return "memindex";
    }

    @PostMapping("/savemem")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        Arrays.stream(ids)
                .mapToInt(Integer::parseInt)
                .mapToObj(services::findRuleById)
                .forEach(accident::addRule);
        accident.setType(services.findByIdType(accident.getType().getId()));
        System.out.println(accident);
        services.save(accident);
        return "redirect:/";
    }

    @GetMapping("/createmem")
    public String create(Model model) {
        List<Rule> allRules = services.findAllRules();
        List<AccidentType> allTypes = services.findAllTypes();
        model.addAttribute("rules", allRules);
        model.addAttribute("types", allTypes);
        return "memcreate";
    }

    @GetMapping("/updatemem")
    public String update(@RequestParam("acId") int id, Model model) {
        model.addAttribute("accident", services.findById(id));
        List<Rule> allRules = services.findAllRules();
        List<AccidentType> allTypes = services.findAllTypes();
        model.addAttribute("rules", allRules);
        model.addAttribute("types", allTypes);
        return "memupdate";
    }

    @RequestMapping("/deletemem")
    public String deleteEmployee(@RequestParam("acId") int id) {
        services.deleteAccident(id);
        return "redirect:/";
    }
}
