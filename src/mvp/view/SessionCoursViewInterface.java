package mvp.view;

import classesmetiers.SessionCours;
import mvp.presenter.SessionCoursPresenter;

import java.util.List;

public interface SessionCoursViewInterface{
    public void setPresenter(SessionCoursPresenter presenter);

    public void setListDatas(List<SessionCours> sc);

    public void affMsg(String msg);

    public void affList(List infos);

    public SessionCours selectionner(List<SessionCours>lsc);
}