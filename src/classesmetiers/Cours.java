package classesmetiers;

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
    /**
     * Identifiant unique de l'ID du cours attribué par la base de données
     */
    private int id_cours;
    /**
     * Nom de la matière du cours
     */
    private String matiere;
    /**
     * Nombre d'heure pour le cours
     */
    private int heures;
    /**
     * Liste de toutes les sessions de cours qu'il y a eu
     */
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
    public String toString() {
        return "Cours{" +
                "id_cours=" + id_cours +
                ", matiere='" + matiere + '\'' +
                ", heures=" + heures +
                "'}'";
    }

    /**
     * égalité de deux cours basé sur l'id du cours
     * @param o autre élement
     * @return égalité ou pas
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cours cours = (Cours) o;
        return id_cours == cours.id_cours;
    }

    /**
     * Calcul du hashcode du cours basé sur l'id du cours
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(id_cours);
    }

    /**
     * Getter Id
     * @return id du cours
     */

    public int getId() {
        return id_cours;
    }

    /**
     * Setter id
     * @param id id du cours
     */
    public void setId(int id) {
        this.id_cours = id;
    }

    /**
     * Getter Matiere
     * @return id de la matiere du cours
     */
    public String getMatiere() {
        return matiere;
    }

    /**
     * Setter Matiere
     * @param matiere matiere du cours
     */
    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    /**
     * Getter Heures
     * @return Nombre d'heures du cours
     */

    public int getHeures() {
        return heures;
    }

    /**
     * Setter Heures
     * @param heures heures du cours
     */
    public void setHeures(int heures) {
        this.heures = heures;
    }

    /**
     * Getter liste des sessions
     * @return liste de toutes les sessions
     */

    public List<SessionCours> getSessions() {
        return sessions;
    }

    /**
     * setter Sessions
     * @param sessions liste de toutes les sessions
     */

    public void setSessions(List<SessionCours> sessions) {
        this.sessions = sessions;
    }
}
