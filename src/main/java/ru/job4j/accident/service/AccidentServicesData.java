package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.AccidentTypeRepository;
import ru.job4j.accident.repository.RuleRepository;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class AccidentServicesData implements Services {
    private final AccidentTypeRepository accidentTypeRepository;
    private final AccidentRepository accidentRepository;
    private final RuleRepository ruleRepository;


    public AccidentServicesData(AccidentTypeRepository accidentTypeRepository,
                                AccidentRepository accidentRepository,
                                RuleRepository ruleRepository) {
        this.accidentTypeRepository = accidentTypeRepository;
        this.accidentRepository = accidentRepository;
        this.ruleRepository = ruleRepository;
    }

    @Override
    @Transactional
    public List<Accident> findAll() {
        return accidentRepository.findAll();
    }

    @Override
    @Transactional
    public void save(Accident accident) {
        accidentRepository.save(accident);
    }

    @Override
    @Transactional
    public Accident findById(int id) {
        return accidentRepository.findById(id).get();
    }

    @Override
    @Transactional
    public List<AccidentType> findAllTypes() {
        return (List<AccidentType>) accidentTypeRepository.findAll();
    }

    @Override
    public List<Rule> findAllRules() {
        return (List<Rule>) ruleRepository.findAll();
    }

    @Override
    @Transactional
    public Rule findRuleById(int id) {
        return ruleRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void deleteAccident(int id) {
        Accident accident = accidentRepository.findById(id).get();
        accidentRepository.delete(accident);
    }

    @Override
    public void addRules(Accident accident, String[] ids) {
        Arrays.stream(ids)
                .mapToInt(Integer::parseInt)
                .mapToObj(i -> ruleRepository.findById(i).get())
                .forEach(accident::addRule);
    }
}
