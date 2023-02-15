package Projet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *   classe SessionCours de l'organisme de formation
 *   @author Mirko Cuozzo
 *   @version 1.0
 *
 */
public class SessionCours {
    private int id_sessionCours;
    private Date dateDebut,dateFin;
    private int nbreInscrits;
    private Cours cours;
    private Local local;
    private List<Infos> infosList = new ArrayList<>();
    public SessionCours(int id,Date dateDebut,Date dateFin,int nbreInscrits,Cours cours,Local local)
    {
        this.id_sessionCours=id;
        this.dateDebut=dateDebut;
        this.dateFin=dateFin;
        this.nbreInscrits=nbreInscrits;
        this.cours=cours;
        this.local=local;
    }

    public int getId_sessionCours() {
        return id_sessionCours;
    }

    public void setId_sessionCours(int id_sessionCours) {
        this.id_sessionCours = id_sessionCours;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public int getNbreInscrits() {
        return nbreInscrits;
    }

    public void setNbreInscrits(int nbreInscrits) {
        this.nbreInscrits = nbreInscrits;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public List<Infos> getInfosList() {
        return infosList;
    }

    public void setInfosList(List<Infos> infosList) {
        this.infosList = infosList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionCours that = (SessionCours) o;
        return id_sessionCours == that.id_sessionCours;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_sessionCours);
    }
}
