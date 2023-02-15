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
    private int id_local;
    private String sigle;
    private int places;
    private String description;
    private List<SessionCours> sessions = new ArrayList<>();
    public Local(int id,String sigle,int places,String description)
    {
        this.id_local=id;
        this.sigle=sigle;
        this.places=places;
        this.description=description;
    }

    public int getId_local() {
        return id_local;
    }

    public void setId_local(int id_local) {
        this.id_local = id_local;
    }

    public String getSigle() {
        return sigle;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SessionCours> getSessions() {
        return sessions;
    }

    public void setSessions(List<SessionCours> sessions) {
        this.sessions = sessions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Local local = (Local) o;
        return id_local == local.id_local;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_local);
    }
}
