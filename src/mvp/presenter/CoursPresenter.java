package mvp.presenter;

import classesmetiers.*;
import mvp.model.CoursModelDB;
import mvp.model.CoursSpecial;
import mvp.model.DAO;
import mvp.view.ViewInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CoursPresenter extends Presenter<Cours> implements CoursSpecialPresenter {
    private static final Logger logger = LogManager.getLogger(CoursPresenter.class);

    public CoursPresenter(DAO<Cours> model, ViewInterface<Cours> view)
    {
        super(model, view);
        this.view.setPresenter(this);
    }


    @Override
    public List<Cours> getAllCours() {
        List<Cours> list = ((CoursSpecial)model).getAllCours();
        return list;//Bonus
    }
    @Override
    public int insert_new_cours()
    {
        int PK;
        PK = ((CoursSpecial)model).insert_new_cours();
        return PK;
    }
}
