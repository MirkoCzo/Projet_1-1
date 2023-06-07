package mvp.presenter;

import classesmetiers.*;
import mvp.model.CoursModelDB;
import mvp.model.DAO;
import mvp.model.FormateurSpecial;
import mvp.view.ViewInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FormateurPresenter extends Presenter<Formateur> implements FormateurPresenterSpecial {
    private static final Logger logger = LogManager.getLogger(FormateurPresenter.class);
    public FormateurPresenter(DAO<Formateur> model, ViewInterface<Formateur> view) {
        super(model, view);
    }

    private Presenter<SessionCours> sessionCoursPresenter;

    @Override
    public int getTotalFormateurHeures(int idSessionCours) {
        int heures;
        heures = ((FormateurSpecial)model).getTotalFormateurHeures(idSessionCours);
        return heures;
    }

    @Override
    public List<SessionCours> lSessionCours() {
        List<SessionCours> sessionCoursList = sessionCoursPresenter.getAll();
        return sessionCoursList;
    }


    @Override
    public void setSessionCoursPresenter(SessionCoursPresenter sessionCoursPresenter)
    {
        this.sessionCoursPresenter = sessionCoursPresenter;
    }
}
