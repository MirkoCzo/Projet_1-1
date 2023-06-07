package mvp.view;

import classesmetiers.Cours;
import classesmetiers.Local;
import mvp.presenter.LocalPresenter;
import mvp.presenter.LocalSpecialPresenter;
import mvp.presenter.Presenter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static Utilitaires.Utilitaires.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LocalViewConsole implements ViewInterface<Local> {

    private Presenter<Local> presenter;

    private List<Local> ldatas;

    private Scanner sc = new Scanner(System.in);

    private static final Logger logger = LogManager.getLogger(LocalViewConsole.class);


    protected void rechercher() {
        try
        {
            System.out.println("Id du Local :");
            int idLocal = lireInt();
            Local rech = null;
            rech = new Local(idLocal,"x",0,"x");
            presenter.search(rech);
        }catch (Exception e)
        {
            System.out.println("Erreur "+e);
        }

    }


    protected void modifier() {
        affListe(ldatas);
        int choix = choixElt(ldatas);
        Local l = ldatas.get(choix-1);
        do {
            try
            {
                String sigle = modifyIfNotBlank("Sigle",l.getSigle());
                int places = Integer.parseInt(modifyIfNotBlank("Places", String.valueOf(l.getPlaces())));
                String desc = modifyIfNotBlank("Description",l.getDescription());
                l.setSigle(sigle);
                l.setPlaces(places);
                l.setDescription(desc);
                break;
            }catch (Exception e)
            {
                System.out.println("Erreur :"+e);
            }
        }while (true);
            presenter.update(l);
        ldatas = presenter.getAll();
        affListe(ldatas);

    }


    protected void ajouter() {
        System.out.println("Sigle :");
        String sigle = sc.nextLine();
        System.out.println("Places :");
        int places = sc.nextInt();
        sc.skip("\n");
        System.out.println("Description :");
        String desc = sc.nextLine();
        try
        {
            Local local = new Local.LocalBuilder()
                    .setSigle(sigle)
                    .setPlaces(places)
                    .setDescription(desc)
                    .build();
            if (local!=null)
            {
                presenter.add(local);
                Local loc = presenter.read(local);
                local.setId_local(loc.getId_local());
            }
        }catch (Exception e)
        {
            System.out.println("Erreur : "+e);
        }
        ldatas = presenter.getAll();
        affListe(ldatas);
    }


    public void menu(){
        List<String> loptions = new ArrayList<>(Arrays.asList("Afficher tous les Locaux","Ajouter un local","Modifier un local","Effacer un local","Rechercher un local","Speciales","Retour"));
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
        List<String> loptions = new ArrayList<>(Arrays.asList("Afficher les locaux disponible entre deux dates","retour"));
        int choix;
        do {
            affListe(loptions);
            choix=choixElt(loptions);
            switch (choix)
            {
                case 1:creaSpecial();break;
                case 2:
                    System.out.println("fin");
            }

        }while(choix!=2);
    }

    protected void creaSpecial()
    {
        LocalDate DateDebut,DateFin;
        int Places;
        System.out.println("Date debut");
        DateDebut=lectureDate();
        System.out.println("Date fin");
        DateFin=lectureDate();
        System.out.println("Places necessaires?");
        Places = sc.nextInt();
        List<Local> list = ((LocalSpecialPresenter)presenter).getAvailableLocaux(DateDebut,DateFin,Places);

    }


    protected void retirer() {
        affListe(ldatas);
        int choix = choixElt(ldatas);
        Local local = ldatas.get(choix-1);
        presenter.remove(local);
        ldatas = presenter.getAll();
        affListe(ldatas);
    }

    @Override
    public void setPresenter(Presenter<Local> presenter) {
        this.presenter = (LocalPresenter) presenter;

    }

    @Override
    public void setListDatas(List<Local> ldatas) {
        this.ldatas = ldatas;
        menu();

    }

    @Override
    public void affMsg(String msg) {
        System.out.println("Information : " + msg);

    }


    @Override
    public Local selectionner(List<Local> l) {
        affListe(l);
        int choix = choixElt(l);
        Local loc = l.get(choix-1);
        return loc;
    }
}
