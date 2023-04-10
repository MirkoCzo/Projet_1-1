package mvp.view;
import classesmetiers.Formateur;
import mvp.presenter.FormateurPresenter;

import java.util.List;

public interface FormateurViewInterface{
    public void setPresenter(FormateurPresenter presenter);

    public void setListDatas(List<Formateur> formateur);

    public void affMsg(String msg);

    public void affList(List infos);

    public Formateur selectionner(List<Formateur>lf);
}
