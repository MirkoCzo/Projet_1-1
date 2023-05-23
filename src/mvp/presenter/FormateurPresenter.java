package mvp.presenter;

import classesmetiers.*;
import mvp.model.CoursModelDB;
import mvp.model.DAO;
import mvp.view.ViewInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FormateurPresenter extends Presenter<Formateur> {
    private static final Logger logger = LogManager.getLogger(FormateurPresenter.class);
    public FormateurPresenter(DAO<Formateur> model, ViewInterface<Formateur> view) {
        super(model, view);
    }
}
