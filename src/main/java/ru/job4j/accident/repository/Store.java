package ru.job4j.accident.repository;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.List;

public interface Store {
    List<Accident> findAll();

    void save(Accident accident);

    Accident findById(int id);

    List<AccidentType> findAllTypes();

    List<Rule> findAllRules();

    Rule findRuleById(int id);

    void deleteAccident(int id);

    void addRules(Accident accident, String[] ids);
}
