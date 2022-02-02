package ru.job4j.accident.repository;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.List;

public interface Store {
    List<Accident> findAll();

    void save(Accident accident);

    Accident findById(int id);

    List<AccidentType> findAllTypes();
}
