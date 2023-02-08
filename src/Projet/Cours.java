package Projet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cours {
    private int id;
    private String matiere;
    private int heures;
    private List<SessionCours> sessions = new ArrayList<>();

    public Cours(int id, String matiere, int heures) {
        this.id = id;
        this.matiere = matiere;
        this.heures = heures;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cours cours = (Cours) o;
        return id == cours.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public int getHeures() {
        return heures;
    }

    public void setHeures(int heures) {
        this.heures = heures;
    }

    public List<SessionCours> getSessions() {
        return sessions;
    }

    public void setSessions(List<SessionCours> sessions) {
        this.sessions = sessions;
    }
}
