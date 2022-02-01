package ru.job4j.accident.service;

import ru.job4j.accident.model.Accident;

import java.util.List;

public interface Services {
    List<Accident> findAll();

    void save(Accident accident);

    Accident findById(int id);
}
