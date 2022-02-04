package ru.job4j.accident.memory.services;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.memory.store.MemoryStore;

import javax.transaction.Transactional;

import java.util.List;

@Service
@Transactional
public class MemAccidentService implements MemoryServices {

    private final MemoryStore store;

    public MemAccidentService(MemoryStore store) {
        this.store = store;
    }

    @Override
    public List<Accident> findAll() {
        return store.findAll();
    }

    @Override
    public void save(Accident accident) {
        store.save(accident);
    }

    @Override
    public Accident findById(int id) {
        return store.findById(id);
    }

    @Override
    public List<AccidentType> findAllTypes() {
        return store.findAllTypes();
    }

    @Override
    public List<Rule> findAllRules() {
        return store.findAllRules();
    }

    @Override
    public Rule findRuleById(int id) {
        return store.findRuleById(id);
    }

    @Override
    public void deleteAccident(int id) {
        store.deleteAccident(id);
    }

    @Override
    public AccidentType findByIdType(int id) {
       return store.findByIdType(id);
    }
}
