package classesmetiers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *   classe SessionCours de l'organisme de formation
 *   @author Mirko Cuozzo
 *   @version 1.0
 *
 */
public class SessionCours {
    /**
     * Identifiant unique de la session du cours attribué par la base de données
     */
    private int id_sessionCours;
    /**
     * Date du debut de la session du cours
     */
    private LocalDate dateDebut;
    /**
     * Date de fin de la session du cours
     */

    private LocalDate dateFin;
    /**
     * Nombre d'inscrit pour une session de cours
     */
    private int nbreInscrits;
    /**
     * Cours pour la session de cours
     */
    private Cours cours;
    /**
     * Local pour la session de cours
     */
    private Local local;
    /**
     * Liste de toutes les infos lié à ce cours @see Infos
     */
    private List<Infos> infosList = new ArrayList<>();
    /**
     * méthode toString
     *
     * @return informations complètes sans la liste d'info
     */

    @Override
    public String toString() {
        return "SessionCours{" +
                "id_sessionCours=" + id_sessionCours +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", nbreInscrits=" + nbreInscrits +
                ", cours=" + cours +
                ", local=" + local +
                '}';
    }

    /**
     * Constructeur paramétré
     * @param id id de la session du cours
     * @param dateDebut date de debut de la session du cours
     * @param dateFin date de fin de la session du cours
     * @param nbreInscrits nombre d'inscrit pour la session du cours
     * @param cours cours pour la session
     * @param local local pour la session
     */
    public SessionCours(int id, LocalDate dateDebut, LocalDate dateFin, int nbreInscrits, Cours cours, Local local)
    {
        this.id_sessionCours=id;
        this.dateDebut=dateDebut;
        this.dateFin=dateFin;
        this.nbreInscrits=nbreInscrits;
        this.cours=cours;
        this.local=local;
    }

    /**
     * Getter Id
     * @return id de la session
     */
    public int getId_sessionCours() {
        return id_sessionCours;
    }

    /**
     * Setter Id de session
     * @param id_sessionCours id de la session
     */

    public void setId_sessionCours(int id_sessionCours) {
        this.id_sessionCours = id_sessionCours;
    }

    /**
     * Getter Date de debut
     *
     * @return date de debut de la session
     */

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    /**
     * Setter Date de debut
     * @param dateDebut date de debut de la session
     */

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * Getter date de fin
     *
     * @return date de fin de la session
     */

    public LocalDate getDateFin() {
        return dateFin;
    }

    /**
     * Setter date de fin
     * @param dateFin date de fin de la session
     */

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * Getter Nombre d'inscrit
     * @return Nombre d'inscrit de la session
     */

    public int getNbreInscrits() {
        return nbreInscrits;
    }

    /**
     * Setter nombre d'inscrit
     * @param nbreInscrits nombre d'inscrit de la session
     */

    public void setNbreInscrits(int nbreInscrits) {
        this.nbreInscrits = nbreInscrits;
    }

    /**
     * Getter Cours
     * @return cours de la session
     */

    public Cours getCours() {
        return cours;
    }

    /**
     * Setter Cours
     * @param cours cours de la session
     */

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    /**
     * Getter Local
     * @return Local du cours
     */

    public Local getLocal() {
        return local;
    }

    /**
     * Setter Local
     * @param local Local du cours
     */

    public void setLocal(Local local) {
        this.local = local;
    }

    /**
     * Getter Infos
     * @return liste des infos du cours @see infos
     */

    public List<Infos> getInfosList() {
        return infosList;
    }

    /**
     * Setter Infos
     * @param infosList liste des infos du cours
     */
    public void setInfosList(List<Infos> infosList) {
        this.infosList = infosList;
    }

    /**
     * égalité entre deux sessions de cours basé sur l'ID de la session
     * @param o autre paramètre
     * @return égalité ou non
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionCours that = (SessionCours) o;
        return id_sessionCours == that.id_sessionCours;
    }

    /**
     * Calcul du hashcode basé sur l'id de la session du cours
     * @return hashcode de la session du cours
     */

    @Override
    public int hashCode() {
        return Objects.hash(id_sessionCours);
    }
}
