package mvp.presenter;

import classesmetiers.Cours;
import classesmetiers.Local;
import classesmetiers.SessionCours;
import mvp.model.CoursModelDB;
import mvp.model.DAO;

import mvp.view.ViewInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class SessionCoursPresenter extends Presenter<SessionCours> implements SpecialSessionCoursPresenter {
    private static final Logger logger = LogManager.getLogger(SessionCoursPresenter.class);

    private Presenter<Cours> coursPresenter;

    private Presenter<Local> localPresenter;

    public SessionCoursPresenter(DAO<SessionCours> model, ViewInterface<SessionCours> view)
    {
        super(model, view);

    }

    @Override
    public List<Cours> choixCours() {
        List<Cours> lcours = coursPresenter.getAll();
        return lcours;
    }

    @Override
    public List<Local> choixLocal()
    {
        List<Local> lLocal = localPresenter.getAll();
        return lLocal;
    }

    @Override
    public void setCoursPresenter(CoursPresenter coursPresenter) {
        this.coursPresenter = coursPresenter;
    }

    @Override
    public void setLocalPresenter(LocalPresenter localPresenter) {
        this.localPresenter = localPresenter;
    }
}
