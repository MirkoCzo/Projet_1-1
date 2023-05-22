package mvp.presenter;

import classesmetiers.*;
import mvp.model.DAO;
import mvp.model.DAOFormateur;
import mvp.view.FormateurViewInterface;
import mvp.view.ViewInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.Format;
import java.util.List;

public class FormateurPresenter extends Presenter<Formateur> {

    public FormateurPresenter(DAO<Formateur> model, ViewInterface<Formateur> view) {
        super(model, view);
    }
}
