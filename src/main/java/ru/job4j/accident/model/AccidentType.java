package ru.job4j.accident.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "typeAccident")
public class AccidentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @OneToMany(mappedBy = "type", cascade = {CascadeType.MERGE, CascadeType.DETACH,
            CascadeType.REFRESH, CascadeType.PERSIST},
            orphanRemoval = true)
    private List<Accident> allAccident;


    public static AccidentType of(int id, String name) {
        AccidentType type = new AccidentType();
        type.id = id;
        type.name = name;
        return type;
    }

    public AccidentType() {
    }

    public AccidentType(String name) {
        this.name = name;
    }

    public AccidentType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addAccidentToType(Accident accident) {
        if (allAccident == null) {
            allAccident = new ArrayList<>();
        }
        allAccident.add(accident);
        accident.setType(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AccidentType that = (AccidentType) o;
        return id == that.id;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "AccidentType{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}
