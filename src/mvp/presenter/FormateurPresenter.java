package mvp.presenter;

import classesmetiers.*;
import mvp.model.DAO;
import mvp.view.ViewInterface;

public class FormateurPresenter extends Presenter<Formateur> {

    public FormateurPresenter(DAO<Formateur> model, ViewInterface<Formateur> view) {
        super(model, view);
    }
}
