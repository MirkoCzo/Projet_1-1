package mvp.presenter;

import classesmetiers.*;
import mvp.model.DAO;
import mvp.view.ViewInterface;
import org.apache.logging.log4j.LogManager;

import java.util.logging.Logger;

public class FormateurPresenter extends Presenter<Formateur> {
    private static final Logger logger = (Logger) LogManager.getLogger(FormateurPresenter.class);

    public FormateurPresenter(DAO<Formateur> model, ViewInterface<Formateur> view) {
        super(model, view);
    }
}
