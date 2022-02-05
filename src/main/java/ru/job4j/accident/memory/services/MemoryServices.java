package ru.job4j.accident.memory.services;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.List;

public interface MemoryServices {

    AccidentType findByIdType(int id);

    List<Accident> findAll();

    void save(Accident accident);

    Accident findById(int id);

    List<AccidentType> findAllTypes();

    List<Rule> findAllRules();

    Rule findRuleById(int id);

    void deleteAccident(int id);

    void addRulesAndType(Accident accident, String[] rIds);
}
