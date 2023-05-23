package mvp;

import classesmetiers.Cours;
import classesmetiers.Formateur;
import classesmetiers.Local;
import classesmetiers.SessionCours;
import mvp.model.*;
import mvp.presenter.*;
import mvp.view.*;

import java.util.Arrays;
import java.util.List;
import static Utilitaires.Utilitaires.*;


public class Gest {

    private DAO<Cours> cm;

    private ViewInterface<Cours> cv;

    private Presenter<Cours> cp;


    private DAO<Formateur> fm;

    private ViewInterface<Formateur> fv;

    private Presenter<Formateur> fp;


    private DAO<Local> lm;

    private ViewInterface<Local> lv;

    private Presenter<Local> lp;


    private DAO<SessionCours> scm;

    private ViewInterface<SessionCours> scv;

    private  Presenter<SessionCours> scp;

    public void gestion()
    {
        cm = new CoursModelDB();
        cv = new CoursViewConsole();
        cp = new CoursPresenter(cm,cv);

        fm = new FormateurModelDB();
        fv = new FormateurViewConsole();
        fp = new FormateurPresenter(fm,fv);

        lm = new LocalModelDB();
        lv = new LocalViewConsole();
        lp = new LocalPresenter(lm,lv);

        scm = new SessionCoursModelDB();
        scv = new SessionCoursViewConsole();
        scp = new SessionCoursPresenter(scm,scv);

        List<String> loptions = Arrays.asList("Cours","Formateurs","Locaux","Session de cours","fin");
        do {
            int ch = choixListe(loptions);
            switch (ch){
                case 1: cp.start();
                    break;
                case 2: fp.start();
                    break;
                case 3: lp.start();
                    break;
                case 4: scp.start();
                    break;

                case 5 : System.exit(0);
            }
        }while(true);
    }

    public static void main(String[] args)
    {
        Gest g = new Gest();
        g.gestion();
    }
}

