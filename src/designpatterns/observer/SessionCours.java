package designpatterns.observer;

import classesmetiers.Infos;
import classesmetiers.Local;
import designpatterns.builder.Cours;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SessionCours extends Subject{
    public SessionCours(int id,Local l,int nbreInscrits)
    {
        this.id_sessionCours=id;
        this.local =l;
        this.nbreInscrits=nbreInscrits;
    }
    private boolean LocalChanged = false;
    private boolean NbrInscritsChanged = false;
    /**
     * Identifiant unique de la session du cours attribué par la base de données
     */
    protected int id_sessionCours;
    /**
     * Date du debut de la session du cours
     */
    protected LocalDate dateDebut;
    /**
     * Date de fin de la session du cours
     */

    protected LocalDate dateFin;
    /**
     * Nombre d'inscrit pour une session de cours
     */
    protected int nbreInscrits;
    /**
     * Cours pour la session de cours
     */
    protected Cours cours;
    /**
     * Local pour la session de cours
     */
    protected Local local;
    /**
     * Liste de toutes les infos lié à ce cours @see Infos
     */
    protected List<Infos> infosList = new ArrayList<>();
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
     * @param scb objet de la classe SessionCoursBuilder contenant les informations d'initialisation
     */

    /**
     * Getter Id
     * @return id de la session
     */
    public int getId_sessionCours() {
        return id_sessionCours;
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
     * Getter date de fin
     *
     * @return date de fin de la session
     */

    public LocalDate getDateFin() {
        return dateFin;
    }


    /**
     * Getter Nombre d'inscrit
     * @return Nombre d'inscrit de la session
     */

    public int getNbreInscrits() {
        return nbreInscrits;
    }


    /**
     * Getter Cours
     * @return cours de la session
     */

    public Cours getCours() {
        return cours;
    }


    /**
     * Getter Local
     * @return Local du cours
     */

    public Local getLocal() {
        return local;
    }


    /**
     * Getter Infos
     * @return liste des infos du cours @see infos
     */

    public List<Infos> getInfosList() {
        return infosList;
    }

    /**
     * égalité entre deux sessions de cours basé sur l'ID de la session
     * @param o autre paramètre
     * @return égalité ou non
     */



    /**
     * Calcul du hashcode basé sur l'id de la session du cours
     * @return hashcode de la session du cours
     */

    @Override
    public int hashCode() {
        return Objects.hash(id_sessionCours);
    }


    @Override
    public String getNotification() {
        StringBuilder sb = new StringBuilder();
        if(LocalChanged)
        {
            LocalChanged=false;
            sb.append("Le local a été changé. Nouveau local = " + local);
        }
        if (NbrInscritsChanged)
        {
            NbrInscritsChanged=false;
            sb.append("Le nombre d'inscrits a été changé. Nouveau nombre d'inscrits = " + nbreInscrits);
        }
        return sb.toString();
    }

    public void setId_sessionCours(int id_sessionCours) {
        this.id_sessionCours = id_sessionCours;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public void setNbreInscrits(int nbreInscrits) {
        this.nbreInscrits = nbreInscrits;
        NbrInscritsChanged = true;
        notifyObservers();

    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public void setLocal(Local local) {
        this.local = local;
        LocalChanged = true;
        notifyObservers();

    }

    public void setInfosList(List<Infos> infosList) {
        this.infosList = infosList;
    }
}
