package mvp.presenter;

import classesmetiers.Cours;
import classesmetiers.Local;
import classesmetiers.SessionCours;
import mvp.model.CoursModelDB;
import mvp.model.DAO;

import mvp.view.ViewInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SessionCoursPresenter extends Presenter<SessionCours> implements SpecialSessionCoursPresenter {
    private static final Logger logger = LogManager.getLogger(SessionCoursPresenter.class);
    private Presenter<SessionCours> sessionCoursPresenter;

    private Presenter<Cours> coursPresenter;

    private Presenter<Local> localPresenter;

    public SessionCoursPresenter(DAO<SessionCours> model, ViewInterface<SessionCours> view)
    {
        super(model, view);
    }

    @Override
    public Cours choixCours() {
        return coursPresenter.selection();
    }

    @Override
    public Local choixLocal()
    {
        return localPresenter.selection();
    }
}
