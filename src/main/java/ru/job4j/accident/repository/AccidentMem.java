package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class AccidentMem implements Store {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Accident> findAll() {
        Session session = sessionFactory.getCurrentSession();
        String select = "SELECT DISTINCT a FROM Accident a "
                + "LEFT JOIN FETCH a.rules r "
                + "ORDER BY a.id";
        return session.createQuery(select, Accident.class).list();
    }

    @Override
    @Transactional
    public void save(Accident accident) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(accident);
    }

    @Override
    @Transactional
    public Accident findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Accident.class, id);
    }

    @Override
    @Transactional
    public List<AccidentType> findAllTypes() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from AccidentType").list();
    }

    @Override
    @Transactional
    public List<Rule> findAllRules() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Rule").list();
    }

    @Override
    @Transactional
    public Rule findRuleById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Rule.class, id);
    }
}
