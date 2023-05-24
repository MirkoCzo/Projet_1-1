package mvp.presenter;

import classesmetiers.*;
import mvp.model.CoursModelDB;
import mvp.model.DAO;
import mvp.view.ViewInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CoursPresenter extends Presenter<Cours> {
    private static final Logger logger = LogManager.getLogger(CoursPresenter.class);

    public CoursPresenter(DAO<Cours> model, ViewInterface<Cours> view)
    {
        super(model, view);
        this.view.setPresenter(this);
    }

}
