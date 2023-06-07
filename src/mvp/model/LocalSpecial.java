package mvp.model;

import classesmetiers.Local;

import java.time.LocalDate;
import java.util.List;

public interface LocalSpecial {
    public List<Local> getAvailableLocaux(LocalDate dateDebut, LocalDate dateFin, int capacite);
}
