package mvp.presenter;

import classesmetiers.Cours;
import classesmetiers.Local;
import classesmetiers.SessionCours;

import java.util.List;

public interface SpecialSessionCoursPresenter {

    List<Cours> choixCours();

    List<Local> choixLocal();

    void setCoursPresenter(CoursPresenter coursPresenter);

    void setLocalPresenter(LocalPresenter localPresenter);

    int addSession(SessionCours sc);
}
