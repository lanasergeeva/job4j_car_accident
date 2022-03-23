package ru.job4j.accident.jdbc.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.Store;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/*@Repository
@Transactional*/
public class AccidentJdbcTemplate implements Store {

    private static final Logger LOG = LoggerFactory.getLogger(AccidentJdbcTemplate.class.getName());
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void save(Accident accident) {
        if (accident.getId() != 0) {
            update(accident);
        } else {
            create(accident);
        }
    }

    private void create(Accident accident) {
        String sql = "insert into accident (name, text, address, type_id) values (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getType().getId());
            return ps;
        }, keyHolder);
        accident.setId(Objects.requireNonNull(keyHolder.getKey()).intValue());
        addToAccidentAndRules(accident);
    }

    private void update(Accident accident) {
        String sql = "update accident set name = ?, text = ?, address = ?, type_id =? "
                + " where id = ?";
        jdbc.update(sql,
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId(),
                accident.getId());
        deleteFromAccidentAndRules(accident.getId());
        addToAccidentAndRules(accident);

    }

    private void addToAccidentAndRules(Accident accident) {
        String sql = "insert into accident_rules (rules_id, accident_id) values (?, ?)";
        accident.getRules().forEach(r -> jdbc.update(sql,
                r.getId(), accident.getId()));
    }

    private void deleteFromAccidentAndRules(int id) {
        jdbc.update("delete from accident_rules where accident_id = ?",
                (long) id);
    }

    private List<Rule> findRulesByAccident(Accident accident) {
        String sql = "select r.name as name, r.id as id from "
                + "accident_rules ar join rules r on ar.rules_id = r.id "
                + " where ar.accident_id = ?";
        return jdbc.query(sql,
                (rs, rowNum) -> fromResultSetRule(rs), accident.getId());
    }

    @Override
    public List<Accident> findAll() {
        String sql = "select a.id, a.name, a.text, a.address, a.type_id, t.name as tn"
                + " from accident as a join typeaccident as t "
                + " on a.type_id=t.id order by a.id asc";
        return jdbc.query(sql,
                (rs, row) -> fromResultSetAccident(rs));
    }

    private Accident fromResultSetAccident(ResultSet rs) {
        Accident accident = null;
        try {
            accident = new Accident(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("text"),
                    rs.getString("address"),
                    (AccidentType.of(rs.getInt("type_id"), rs.getString("tn"))));
        } catch (SQLException e) {
            LOG.error("Ошибка в fromResultSet", e);
        }
        findRulesByAccident(accident).forEach(accident::addRule);
        return accident;
    }

    @Override
    public Accident findById(int id) {
        String sql = "select a.id, a.name, a.text, a.address, a.type_id, t.name as tn"
                + " from accident as a join typeaccident as t "
                + "on a.type_id=t.id where a.id = ?";
        return jdbc.queryForObject(sql,
                (rs, rowNum) -> fromResultSetAccident(rs), id);
    }

    @Override
    public List<AccidentType> findAllTypes() {
        return jdbc.query(
                "select id, name from typeaccident",
                (rs, rowNum) -> {
                    AccidentType type = new AccidentType();
                    type.setId(rs.getInt("id"));
                    type.setName(rs.getString("name"));
                    return type;
                });
    }

    @Override
    public List<Rule> findAllRules() {
        return jdbc.query(
                "select id, name from rules",
                (rs, rowNum) -> fromResultSetRule(rs));
    }

    @Override
    public Rule findRuleById(int id) {
        String sql = "select id, name from rules where id = ?";
        return jdbc.queryForObject(
                sql,
                (rs, rowNum) -> fromResultSetRule(rs), id);
    }

    private Rule fromResultSetRule(ResultSet rs) {
        Rule rule = null;
        try {
            rule = Rule.of(rs.getInt("id"),
                    rs.getString("name"));
        } catch (SQLException e) {
            LOG.error("Ошибка в fromResultSetRule", e);
        }
        return rule;
    }

    @Override
    public void deleteAccident(int id) {
        deleteFromAccidentAndRules(id);
        jdbc.update(
                "delete from accident where id = ?",
                (long) id);
    }

    @Override
    public void addRules(Accident accident, String[] ids) {
        Arrays.stream(ids)
                .mapToInt(Integer::parseInt)
                .mapToObj(this::findRuleById)
                .forEach(accident::addRule);
    }
}
