package mvp.presenter;

import classesmetiers.Local;
import classesmetiers.SessionCours;

import java.time.LocalDate;
import java.util.List;

public interface LocalSpecialPresenter {
    List<Local> getAvailableLocaux(LocalDate dateDebut, LocalDate dateFin, int capacite);


}
