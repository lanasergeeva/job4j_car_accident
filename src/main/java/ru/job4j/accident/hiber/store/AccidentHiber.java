package ru.job4j.accident.hiber.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.Store;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@Repository
@Transactional
public class AccidentHiber implements Store {

    private final SessionFactory sessionFactory;

    public AccidentHiber(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Accident> findAll() {
        String select = "SELECT DISTINCT a FROM Accident a "
                + "LEFT JOIN FETCH a.rules r "
                + "ORDER BY a.id";
        return tx(
                session -> session.createQuery(select, Accident.class).list());
    }

    @Override
    public void save(Accident accident) {
        tx(session -> {
            if (accident.getId() == 0) {
                session.save(accident);
            } else {
                session.update(accident);
            }
            return true;
        });
    }

    @Override
    public Accident findById(int id) {
        return tx(session -> session.get(Accident.class, id));
    }

    @Override
    public List<AccidentType> findAllTypes() {
        return tx(session -> session.createQuery("from AccidentType").list());
    }

    @Override
    public List<Rule> findAllRules() {
        return tx(session -> session.createQuery("from Rule").list());
    }

    @Override
    public Rule findRuleById(int id) {
        return tx(session -> session.get(Rule.class, id));
    }

    @Override
    public void deleteAccident(int id) {
        tx(session -> session.createQuery("delete from Accident"
                        + " where id =:acId")
                .setParameter("acId", id)
                .executeUpdate());
    }

    @Override
    public void addRules(Accident accident, String[] ids) {
        Arrays.stream(ids)
                .mapToInt(Integer::parseInt)
                .mapToObj(this::findRuleById)
                .forEach(accident::addRule);
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sessionFactory.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
