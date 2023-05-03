package mvp.presenter;

import classesmetiers.*;
import mvp.model.DAOCours;
import mvp.view.CoursViewInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CoursPresenter {
    private DAOCours model;

    private CoursViewInterface view;

    private static final Logger logger = LogManager.getLogger(CoursPresenter.class);

    public CoursPresenter(DAOCours model, CoursViewInterface view)
    {
        this.model = model;
        this.view = view;
        this.view.setPresenter(this);
    }

    public void start()
    {
        view.setListDatas(getAll());
    }

    public List<Cours> getAll()
    {
        return model.getCours();
    }

    public void addCours(Cours cours)
    {
        Cours c = model.addCours(cours);
        if(c!=null) view.affMsg("création de :"+c);
        else view.affMsg("erreur de création");
    }

    public void removeCours(Cours cours)
    {
        boolean ok = model.removeCours(cours);
        if(ok) view.affMsg("Cours effacé");
        else view.affMsg("Cours non effacé");
    }

    public Cours selectionner()
    {
        logger.info("appel de sélection");
        Cours c = view.selectionner(model.getCours());
        return c;
    }

    public void update(Cours cours)
    {
        Cours c = model.updateCours(cours);
        if(c==null) view.affMsg("Mise à jour infructueuse");
        else view.affMsg("Mise à jour effectuée : "+c);
    }

    public Cours search(int idCours)
    {
        Cours c = model.readCours(idCours);
        if(c==null) view.affMsg("recherche infructueuse");
        else view.affMsg(c.toString());
        return c;
    }



}
