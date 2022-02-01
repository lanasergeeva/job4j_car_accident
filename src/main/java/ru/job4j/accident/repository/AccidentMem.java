package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class AccidentMem implements Store {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Accident> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Accident").list();
    }
}
