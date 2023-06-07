package mvp.view;
import classesmetiers.Cours;
import classesmetiers.SessionCours;
import mvp.presenter.CoursPresenter;
import static Utilitaires.Utilitaires.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


import mvp.model.CoursModelDB;
import mvp.presenter.CoursSpecialPresenter;
import mvp.presenter.Presenter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CoursViewConsole implements ViewInterface<Cours>{

    private Presenter<Cours> presenter;

    private List<Cours> ldatas;

    private Scanner sc = new Scanner(System.in);

    private static final Logger logger = LogManager.getLogger(CoursViewConsole.class);


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


    protected void modifier()
    {
        affListe(ldatas);
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
    public void menu(){
        List<String> loptions = new ArrayList<>(Arrays.asList("Afficher tous les cours","Ajouter un cours","Modifier un cours","Effacer un cours","Rechercher un cours","Speciales","Retour"));

        int choix;

        do{
            affListe(loptions);
            choix = choixElt(loptions);
            switch(choix){
                case 1 : affListe(ldatas);break;
                case 2 : ajouter();break;
                case 3 : modifier();break;
                case 4 : retirer();break;
                case 5 : rechercher();break;
                case 6 : special();break;
                case 7 :
                    System.out.println("Fin");
            }
        }while(choix!=7);
    }

    public void special()
    {
        List<String> loptions = new ArrayList<>(Arrays.asList("Afficher tous les cours","Ajouter un nouveau cours","retour"));
        int choix;
        do {
            affListe(loptions);
            choix=choixElt(loptions);
            switch (choix)
            {
                case 1:getAllCours();break;
                case 2:insert_new_cours();break;
                case 3:
                    System.out.println("fin");
            }

        }while(choix!=2);
    }

    protected void retirer()
    {
        affListe(ldatas);
        int choix = choixElt(ldatas);
        Cours cours = ldatas.get(choix-1);
        presenter.remove(cours);
        ldatas=presenter.getAll();
        affListe(ldatas);
    }

    public void getAllCours()
    {
        List<Cours> list;
        list = ((CoursSpecialPresenter)presenter).getAllCours();
        //Bonus
    }

    public int insert_new_cours()
    {
        int PK;
        PK = ((CoursSpecialPresenter)presenter).insert_new_cours();
        return PK;
    }


    protected void ajouter()
    {

            System.out.println("Nom de la matière ? :");
            String mat = sc.nextLine();
            System.out.println("Nombres d'heures?");
            int heures = sc.nextInt();

            try
            {
                Cours cours = new Cours.CoursBuilder()
                                .setMatière(mat)
                                .setHeures(heures)
                                .build();
                if (cours!=null) {
                    presenter.add(cours);
                    Cours cours1 = presenter.read(cours);
                    cours.setId(cours.getId());
                }

            }catch (Exception e)
            {
                System.out.println("une erreur est survenue :"+e.getMessage());
            }
        ldatas=presenter.getAll();
        affListe(ldatas);
        sc.skip("\n");
    }

    @Override
    public void setPresenter(Presenter<Cours> presenter) {
        this.presenter = (CoursPresenter) presenter;
    }

    @Override
    public void setListDatas(List<Cours> ldatas) {
        this.ldatas = ldatas;
        menu();

    }

    @Override
    public void affMsg(String msg) {
        System.out.println("Information : "+msg);
    }



    @Override
    public Cours selectionner(List<Cours> l) {
        affListe(l);
        int choix = choixElt(l);
        Cours c = l.get(choix-1);
        return c;
    }
}
