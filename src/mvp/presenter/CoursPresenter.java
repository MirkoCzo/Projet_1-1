package mvp.presenter;

import classesmetiers.*;
import mvp.model.DAO;
import mvp.view.ViewInterface;

public class CoursPresenter extends Presenter<Cours> {

    public CoursPresenter(DAO<Cours> model, ViewInterface<Cours> view)
    {
        super(model, view);
    }

}
