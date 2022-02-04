package ru.job4j.accident.memory.store;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/*@Repository
@Transactional*/
public class MemAccidentStore implements MemoryStore {
    private final Map<Integer, Accident> accidents = new HashMap<>();

    private final Map<Integer, AccidentType> accidentTypes =
            Map.of(1, AccidentType.of(1, "Две машины"),
                    2, AccidentType.of(2, "Машина и человек"),
                    3, AccidentType.of(3, "Машина и велосипед"));

    private final Map<Integer, Rule> rules =
            Map.of(1, Rule.of(1, "Статья. 1"),
                    2, Rule.of(2, "Статья. 2"),
                    3, Rule.of(3, "Статья. 3"));

    private final AtomicInteger accIndex = new AtomicInteger(1);


    public MemAccidentStore() {
        init();
    }

    public void init() {
        Accident accident = new Accident("Водитель и забор",
                "Водитель въехал в забор",
                "Маяковского");
        Accident accidentTwo = new Accident("Водитель и столб",
                "Водитель въехал в столб и лишил элекстричества 15 тыс человек",
                "Шукшина");
        Accident accidentThree = new Accident("Машина и троллейбус",
                "Не справился с управлением и въехал в троллейбус",
                "Чайковского");
        save(accident);
        save(accidentTwo);
        save(accidentThree);
    }

    @Override
    public List<Accident> findAll() {
        return new ArrayList<>(accidents.values());
    }

    @Override
    public void save(Accident accident) {
        if (accident.getId() == 0) {
            accident.setId(accIndex.getAndIncrement());
        }
        accidents.put(accident.getId(), accident);
    }

    @Override
    public Accident findById(int id) {
        return accidents.get(id);
    }

    @Override
    public List<AccidentType> findAllTypes() {
        return new ArrayList<>(accidentTypes.values());
    }

    @Override
    public List<Rule> findAllRules() {
        return new ArrayList<>(rules.values());
    }

    @Override
    public Rule findRuleById(int id) {
        return rules.get(id);
    }

    @Override
    public void deleteAccident(int id) {
        Accident accident = accidents.get(id);
        if (accident != null) {
            accidents.remove(id, accident);
        }
    }

    @Override
    public AccidentType findByIdType(int id) {
        return accidentTypes.get(id);
    }

}
