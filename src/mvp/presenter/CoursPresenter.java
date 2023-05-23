package mvp.presenter;

import classesmetiers.*;
import mvp.model.DAO;
import mvp.view.ViewInterface;
import org.apache.logging.log4j.LogManager;

import java.util.logging.Logger;

public class CoursPresenter extends Presenter<Cours> {
    private static final Logger logger = (Logger) LogManager.getLogger(CoursPresenter.class);

    public CoursPresenter(DAO<Cours> model, ViewInterface<Cours> view)
    {
        super(model, view);
    }

}
