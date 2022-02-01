package ru.job4j.accident.repository;

import ru.job4j.accident.model.Accident;

import java.util.List;

public interface Store {
    List<Accident> findAll();
}
