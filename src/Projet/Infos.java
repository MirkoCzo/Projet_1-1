package Projet;

/**
 *   classe Infos de l'organisme de formation
 *   @author Mirko Cuozzo
 *   @version 1.0
 *   @see Formateur && SessionCours
 *
 */
public class Infos {
    private int nh;
    private Formateur formateur;
    private SessionCours sessionCours;
    public Infos(int nh,Formateur f, SessionCours s)
    {
        this.nh=nh;
        this.formateur=f;
        this.sessionCours=s;
    }

    public int getNh() {
        return nh;
    }

    public void setNh(int nh) {
        this.nh = nh;
    }

    public Formateur getFormateur() {
        return formateur;
    }

    public void setFormateur(Formateur formateur) {
        this.formateur = formateur;
    }

    public SessionCours getSessionCours() {
        return sessionCours;
    }

    public void setSessionCours(SessionCours sessionCours) {
        this.sessionCours = sessionCours;
    }

}
