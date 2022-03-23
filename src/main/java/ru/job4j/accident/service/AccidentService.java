package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.Store;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AccidentService implements Services {

    private final Store store;

    public AccidentService(Store store) {
        this.store = store;
    }

    @Override
    @Transactional
    public List<Accident> findAll() {
        return store.findAll();
    }

    @Override
    @Transactional
    public void save(Accident accident) {
        store.save(accident);
    }

    @Override
    @Transactional
    public Accident findById(int id) {
        return store.findById(id);
    }

    @Override
    public List<AccidentType> findAllTypes() {
        return store.findAllTypes();
    }

    @Override
    @Transactional
    public List<Rule> findAllRules() {
        return store.findAllRules();
    }

    @Override
    @Transactional
    public Rule findRuleById(int id) {
        return store.findRuleById(id);
    }

    @Override
    public void deleteAccident(int id) {
        store.deleteAccident(id);
    }

    @Override
    public void addRules(Accident accident, String[] ids) {
        store.addRules(accident, ids);
    }
}
