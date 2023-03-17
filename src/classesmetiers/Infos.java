package classesmetiers;

/**
 *   classe Infos de l'organisme de formation
 *   @author Mirko Cuozzo
 *   @version 1.0
 *   @see Formateur && SessionCours
 *
 */
public class Infos {
    /**
     * Nombre d'heure qu'un professeur peut avoir pour une session de cours
     */
    private int nh;
    /**
     * Formateur à qui appartient les info
     */
    private Formateur formateur;
    /**
     * Infos de la session de cours
     */
    private SessionCours sessionCours;

    /**
     * Constructeur paramétré
     * @param nh Nombre d'heure
     * @param f Formateur
     * @param s Session
     */
    public Infos(int nh,Formateur f, SessionCours s)
    {
        this.nh=nh;
        this.formateur=f;
        this.sessionCours=s;
    }

    /**
     * Getter
     * @return nombre d'heures
     */

    public int getNh() {
        return nh;
    }

    /**
     * Setter
     * @param nh nombre d'heure
     */

    public void setNh(int nh) {
        this.nh = nh;
    }

    /**
     * Getter
     * @return Formateur
     */

    public Formateur getFormateur() {
        return formateur;
    }

    /**
     * Setter
     * @param formateur
     */

    public void setFormateur(Formateur formateur) {
        this.formateur = formateur;
    }

    /**
     * Getter
     * @return La Session du cours
     */

    public SessionCours getSessionCours() {
        return sessionCours;
    }

    /**
     * Setter
     * @param sessionCours
     */

    public void setSessionCours(SessionCours sessionCours) {
        this.sessionCours = sessionCours;
    }

}
