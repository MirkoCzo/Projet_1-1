package mvp.view;
import classesmetiers.Cours;
import mvp.presenter.CoursPresenter;
import static Utilitaires.Utilitaires.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import mvp.model.CoursModelDB;
import mvp.presenter.Presenter;

public class CoursViewConsole extends View<Cours>{

    @Override
    protected void rechercher()
    {

        try
        {
            System.out.println("Id du cours ? :");
            int id = lireInt();
            Cours rech = new Cours(id,"x",0);
            presenter.search(rech);

        }catch (Exception e)
        {
            System.out.println("erreur : "+e);
        }
    }

    @Override
    protected void modifier()
    {
        int choix = choixElt(ldatas);
        Cours c = ldatas.get(choix-1);
        do
        {
            try
            {
                String mat = modifyIfNotBlank("Matière ",c.getMatiere());
                int heures = Integer.parseInt(modifyIfNotBlank("Heures", String.valueOf(c.getHeures())));
                c.setMatiere(mat);
                c.setHeures(heures);
                break;
            }catch (Exception e)
            {
                System.out.println("erreur :"+e);
            }

        }while(true);
        presenter.update(c);
        ldatas=presenter.getAll();
        affListe(ldatas);
    }

    @Override
    protected void ajouter()
    {
        do
        {
            System.out.println("Nom de la matière ? :");
            String mat = sc.nextLine();
            System.out.println("Nombres d'heures?");
            int heures = sc.nextInt();
            Cours c = null;
            try
            {
                c = new Cours(0,mat,heures);
                presenter.add(c);
                break;

            }catch (Exception e)
            {
                System.out.println("une erreur est survenue :"+e.getMessage());
            }
        }while (true);
        ldatas=presenter.getAll();
        affListe(ldatas);
    }

}
