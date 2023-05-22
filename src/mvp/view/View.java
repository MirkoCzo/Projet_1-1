package mvp.view;

import mvp.presenter.Presenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static Utilitaires.Utilitaires.*;

public abstract class View<T> implements ViewInterface<T> {
    protected Presenter<T> presenter;

    protected List<T> ldatas;

    protected Scanner sc = new Scanner(System.in);
    @Override
    public void setPresenter(Presenter<T> presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setListDatas(List<T> ldatas)
    {
        this.ldatas = ldatas;
        affListe(ldatas);
        menu();
    }

    @Override
    public void affMsg(String msg)
    {
        System.out.println("Information : "+msg);
    }

    @Override
    public void affList(List lelts)
    {
        affListe(lelts);
    }

    public void menu() {
        do {

            int ch = choixListe(Arrays.asList("ajout", "retrait", "rechercher", "modifier", "fin"));
            switch (ch) {
                case 1:
                    ajouter();
                    break;
                case 2:
                    retirer();
                    break;
                case 3:
                    rechercher();
                    break;
                case 4:
                    modifier();
                    break;
                case 5:
                    return;
            }
        } while (true);
    }

    protected void retirer()
    {
        int choix = choixElt(ldatas);
        T elt = ldatas.get(choix-1);
        presenter.remove(elt);
        ldatas = presenter.getAll();
        affListe(ldatas);
    }
    @Override
    public T selectionner(List<T> l)
    {
        int nl = choixListe(l);
        T elt = l.get(nl-1);
        return elt;
    }

    protected abstract void rechercher();
    protected  abstract void modifier();
    protected abstract  void ajouter();
}
