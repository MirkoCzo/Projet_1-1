package mvp.view;

import mvp.presenter.Presenter;

import java.util.List;

public interface ViewInterface<T> {

    public void setPresenter(Presenter<T> presenter);

    public void setListDatas(List<T> ldatas);

    public void affMsg(String msg);



    public T selectionner(List<T> l);
}
