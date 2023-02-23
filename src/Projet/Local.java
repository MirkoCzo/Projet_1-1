package Projet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * classe Local de l'organisme de formation
 *   @author Mirko Cuozzo
 *   @version 1.0
 *   @see SessionCours
 */
public class Local {
    /**
     * Identifiant unique du local
     */
    private int id_local;
    /**
     * Sigle du local - unique
     */
    private String sigle;
    /**
     * Nombre de places du local
     */
    private int places;
    /**
     * Description du local
     */
    private String description;
    /**
     * Liste des sessions de cours du local
     */
    private List<SessionCours> sessions = new ArrayList<>();

    /**
     * Constructeur paramétrés
     * @param id id du local
     * @param sigle sigle du local
     * @param places nombre de place du local
     * @param description description du local
     */
    public Local(int id,String sigle,int places,String description)
    {
        this.id_local=id;
        this.sigle=sigle;
        this.places=places;
        this.description=description;
    }

    /**
     * Getter
     * @return l'id du local
     */

    public int getId_local() {
        return id_local;
    }

    /**
     * Setter
     * @param id_local l'id du local
     */

    public void setId_local(int id_local) {
        this.id_local = id_local;
    }

    /**
     * Getter
     * @return le sigle du local
     */

    public String getSigle() {
        return sigle;
    }

    /**
     * Setter
     * @param sigle du local
     */

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    /**
     * Getter
     * @return Nombre de places du local
     */

    public int getPlaces() {
        return places;
    }

    /**
     * Setter
     * @param places nombre de places du local
     */

    public void setPlaces(int places) {
        this.places = places;
    }

    /**
     * Getter
     * @return Description du local
     */

    public String getDescription() {
        return description;
    }

    /**
     * Setter
     * @param description description du local
     */

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter
     * @return Liste des sessions de cours prévu dans le local
     */

    public List<SessionCours> getSessions() {
        return sessions;
    }

    /**
     * Setter
     * @param sessions liste des sessions de cours
     */
    public void setSessions(List<SessionCours> sessions) {
        this.sessions = sessions;
    }

    /**
     * test d'égalité basé sur l'id du local
     * @param o autre commande
     * @return égalité ou pas
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Local local = (Local) o;
        return id_local == local.id_local;
    }

    /**
     * Calcul du hashcode basé sur l'id du local
     * @return hashcode du local
     */

    @Override
    public int hashCode() {
        return Objects.hash(id_local);
    }
}
