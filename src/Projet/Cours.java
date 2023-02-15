package Projet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 *   classe cours de l'organisme de formation
 *   @author Mirko Cuozzo
 *   @version 1.0
 *   @see SessionCours
 */

public class Cours {
    private int id_cours;
    private String matiere;
    private int heures;
    private List<SessionCours> sessions = new ArrayList<>();

    /**
     * Constructeur par défaut
     */
    public Cours()
    {

    }

    /**
     * Constructeur paramétré
     * @param id id du cours unique affecté par la base de données
     * @param matiere
     * @param heures
     */

    public Cours(int id, String matiere, int heures) {
        this.id_cours = id;
        this.matiere = matiere;
        this.heures = heures;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cours cours = (Cours) o;
        return id_cours == cours.id_cours;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_cours);
    }

    public int getId() {
        return id_cours;
    }
    public void setId(int id) {
        this.id_cours = id;
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
