package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.Services;

import java.util.List;

@Controller
public class IndexControl {
    private Services services;

    public IndexControl(Services services) {
        this.services = services;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Accident> all = services.findAll();
        model.addAttribute("all", all);
        return "index";
    }
}
