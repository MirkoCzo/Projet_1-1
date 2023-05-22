package mvp.presenter;

import classesmetiers.*;
import mvp.model.DAO;
import mvp.model.DAOCours;
import mvp.view.CoursViewInterface;
import mvp.view.ViewInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;

public class CoursPresenter extends Presenter<Cours> {

    public CoursPresenter(DAO<Cours> model, ViewInterface<Cours> view)
    {
        super(model, view);
    }

}
