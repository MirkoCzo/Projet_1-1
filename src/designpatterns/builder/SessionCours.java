package designpatterns.builder;

import classesmetiers.Infos;
import classesmetiers.Local;

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
    public SessionCours(SessionCoursBuilder scb)
    {
        this.id_sessionCours=scb.id_sessionCours;
        this.dateDebut=scb.dateDebut;
        this.dateFin=scb.dateFin;
        this.nbreInscrits=scb.nbreInscrits;
        this.cours=scb.cours;
        this.local=scb.local;
    }

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

    public static class SessionCoursBuilder
    {
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
        public SessionCoursBuilder setIdSessionCours(int id_sessionCours)
        {
            this.id_sessionCours = id_sessionCours;
            return this;
        }
        public SessionCoursBuilder setDateDebut(LocalDate dateDebut)
        {
            this.dateDebut = dateDebut;
            return this;
        }
        public SessionCoursBuilder setDateFin(LocalDate dateFin)
        {
            this.dateFin = dateFin;
            return this;
        }
        public SessionCoursBuilder setNbreInscrits(int nbreInscrits)
        {
            this.nbreInscrits = nbreInscrits;
            return this;
        }
        public SessionCoursBuilder setCours(Cours cours)
        {
            this.cours = cours;
            return this;

        }
        public SessionCoursBuilder setLocal(Local local)
        {
            this.local = local;
            return this;
        }
        public SessionCoursBuilder setListInfo(List<Infos> li)
        {
            this.infosList = li;
            return this;
        }
        public SessionCours build() throws Exception
        {
            if (dateDebut == null || dateFin == null) {
                throw new Exception("La date de début et la date de fin doivent être renseignées.");
            }

            if (dateDebut.isAfter(dateFin)) {
                throw new Exception("La date de début doit être antérieure à la date de fin.");
            }

            if (cours == null) {
                throw new Exception("Un cours doit être associé à la session de cours.");
            }

            if (nbreInscrits < 0) {
                throw new Exception("Le nombre d'inscrits ne peut pas être négatif.");
            }

            return new SessionCours(this);
        }

    }
}
