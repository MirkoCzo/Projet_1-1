package mvp.model;

import classesmetiers.Cours;
import classesmetiers.Formateur;

import java.util.List;

public interface DAOFormateur {
    Formateur addFormateur(Formateur formateur);

    boolean removeFormateur(Formateur formateur);

    Formateur updateFormateur(Formateur formateur);

    Formateur readFormateur(int idForm);

    List<Formateur> getFormateurs();
}
