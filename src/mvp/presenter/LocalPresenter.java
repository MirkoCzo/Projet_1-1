package mvp.presenter;

import classesmetiers.Local;
import mvp.model.DAO;
import mvp.view.ViewInterface;

public class LocalPresenter extends Presenter<Local> {

    public LocalPresenter(DAO<Local> model, ViewInterface<Local> view)
    {
        super(model,view);
    }

}
