package mvp.view;

import classesmetiers.Cours;
import classesmetiers.Local;
import classesmetiers.SessionCours;
import mvp.presenter.SessionCoursPresenter;

import java.time.LocalDate;

import static Utilitaires.Utilitaires.*;

public class SessionCoursViewConsole extends View<SessionCours> {

    @Override
    protected void rechercher() {
        try
        {
            System.out.println("Id de la Session :");
            int idSession = lireInt();
            SessionCours rech = null;
            rech = new SessionCours(idSession,null,null,0,null,null);
            presenter.search(rech);
        }catch(Exception e)
        {
            System.out.println("Erreur "+e);
        }
    }

    @Override
    protected void modifier() {
        int choix = choixElt(ldatas);
        SessionCours sc = ldatas.get(choix-1);
        do {
            try
            {
                LocalDate dateDebut = LocalDate.parse(modifyIfNotBlank("Date début", String.valueOf(sc.getDateDebut())));
                LocalDate dateFin = LocalDate.parse(modifyIfNotBlank("date fin", String.valueOf(sc.getDateFin())));
                int nbr = Integer.parseInt(modifyIfNotBlank("Nombre d'inscrits", String.valueOf(sc.getNbreInscrits())));
                sc.setDateDebut(dateDebut);
                sc.setDateFin(dateFin);
                sc.setNbreInscrits(nbr);
                break;
            }catch (Exception e)
            {
                System.out.println("Erreur :"+e);
            }
        }while (true);
        presenter.update(sc);
        ldatas = presenter.getAll();
        affListe(ldatas);

    }

    @Override
    protected void ajouter() {
        System.out.println("Date de début");
        String[] jma = sc.nextLine().split(" ");
        int j = Integer.parseInt(jma[0]);
        int m = Integer.parseInt(jma[1]);
        int a = Integer.parseInt(jma[2]);
        LocalDate dateDebut = LocalDate.of(a,m,j);
        System.out.println("Date de fin");
        String[] zer = sc.nextLine().split(" ");
        int z = Integer.parseInt(jma[0]);
        int e = Integer.parseInt(jma[1]);
        int r = Integer.parseInt(jma[2]);
        LocalDate dateFin = LocalDate.of(z,e,r);
        System.out.println("Nombre d'inscrits");
        int nbr = sc.nextInt();
        System.out.println("Cours");
        Cours c = ((SessionCoursPresenter)presenter).choixCours();
        System.out.println("Local");
        Local l = ((SessionCoursPresenter)presenter).choixLocal();
        SessionCours sess = null;
        try
        {
            sess = new SessionCours(0,dateDebut,dateFin,nbr,c,l);
        }catch (Exception exception)
        {
            throw new RuntimeException(exception);
        }
        presenter.add(sess);
    }

    protected void retirer()
    {
        int choix = choixElt(ldatas);
        SessionCours sess = ldatas.get(choix-1);
        presenter.remove(sess);
        ldatas=presenter.getAll();
        affListe(ldatas);
    }
}
