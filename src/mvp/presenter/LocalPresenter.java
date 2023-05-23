package mvp.presenter;

import classesmetiers.Local;
import mvp.model.DAO;
import mvp.view.ViewInterface;
import org.apache.logging.log4j.LogManager;

import java.util.logging.Logger;

public class LocalPresenter extends Presenter<Local> {
    private static final Logger logger = (Logger) LogManager.getLogger(LocalPresenter.class);

    public LocalPresenter(DAO<Local> model, ViewInterface<Local> view)
    {
        super(model,view);
    }

}
