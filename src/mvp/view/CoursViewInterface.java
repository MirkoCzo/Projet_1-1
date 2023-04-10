package mvp.view;

import classesmetiers.Cours;
import mvp.presenter.CoursPresenter;

import java.util.List;

public interface CoursViewInterface {
    public void setPresenter(CoursPresenter presenter);

    public void setListDatas(List<Cours> cours);

    public void affMsg(String msg);

    public void affList(List infos);

    public Cours selectionner(List<Cours> lc);

}
