package mvp.model;

import classesmetiers.Cours;

import java.util.List;

public interface DAOCours {
    Cours addCours(Cours cours);

    boolean removeCours(Cours cours);

    Cours updateCours(Cours cours);

    Cours readCours(int idCours);

    List<Cours> getCours();

}
