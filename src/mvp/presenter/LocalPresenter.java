package mvp.presenter;

import classesmetiers.Local;
import classesmetiers.SessionCours;
import mvp.model.CoursModelDB;
import mvp.model.DAO;
import mvp.model.LocalSpecial;
import mvp.view.ViewInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.PrivateKey;
import java.time.LocalDate;
import java.util.List;

public class LocalPresenter extends Presenter<Local> implements LocalSpecialPresenter {

    private Presenter<SessionCours> sessionCoursPresenter;

    private static final Logger logger = LogManager.getLogger(LocalPresenter.class);
    public LocalPresenter(DAO<Local> model, ViewInterface<Local> view)
    {
        super(model,view);
    }

    @Override
    public List<Local> getAvailableLocaux(LocalDate dateDebut, LocalDate dateFin, int capacite) {
        List<Local> list = ((LocalSpecial)model).getAvailableLocaux(dateDebut,dateFin,capacite);
        return list;
    }



}
