package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentHibernate;


import java.util.List;

@Controller
public class IndexControl {
    private final AccidentHibernate accidents;

    public IndexControl(AccidentHibernate accidents) {

        this.accidents = accidents;
    }

    @GetMapping("/hiber")
    public String index(Model model) {
        List<Accident> all = accidents.getAll();
        model.addAttribute("accidents", all);
        return "indexjdbc";
    }
}
