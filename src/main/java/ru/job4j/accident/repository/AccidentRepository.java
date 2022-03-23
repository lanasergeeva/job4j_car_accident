package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Accident;

import java.util.List;

public interface AccidentRepository
        extends CrudRepository<Accident, Integer> {
    @Query("SELECT DISTINCT a FROM Accident a "
           + "LEFT JOIN FETCH a.rules r "
           + "ORDER BY a.id")
    List<Accident> findAll();

}
