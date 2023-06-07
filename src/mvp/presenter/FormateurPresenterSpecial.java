package mvp.presenter;

import classesmetiers.SessionCours;

import java.util.List;

public interface FormateurPresenterSpecial {
    public int getTotalFormateurHeures(int idSessionCours);


    List<SessionCours> lSessionCours();

    void setSessionCoursPresenter(SessionCoursPresenter sessionCoursPresenter);
}
