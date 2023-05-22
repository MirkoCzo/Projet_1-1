package mvp.view;

import classesmetiers.Formateur;
import mvp.presenter.FormateurPresenter;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static Utilitaires.Utilitaires.*;

public class FormateurViewConsole extends View<Formateur>{
    @Override
    protected void rechercher()
    {
        try
        {
            System.out.println("id du formateur :");
            int id = lireInt();
            Formateur rech = new Formateur(id,"x","x","x");
            presenter.search(rech);
        }catch (Exception e)
        {
            System.out.println("Erreur : "+e);
        }
    }
    @Override
    protected void modifier()
    {
        int choix = choixElt(ldatas);
        Formateur f = ldatas.get(choix-1);
        do
        {
            try
            {
              String mail = modifyIfNotBlank("Mail",f.getMail());
              String nom = modifyIfNotBlank("Nom",f.getNom());
              String prenom = modifyIfNotBlank("Prenom",f.getPrenom());
              f.setMail(mail);
              f.setNom(nom);
              f.setPrenom(prenom);
              break;
            }catch (Exception e)
            {
                System.out.println("Erreur :"+e);
            }
        }while (true);
        presenter.update(f);
        ldatas=presenter.getAll();
        affListe(ldatas);
    }
    @Override
    protected  void retirer() {
        int choix = choixElt(ldatas);
        Formateur formateur = ldatas.get(choix-1);
        presenter.remove(formateur);
        ldatas=presenter.getAll();//rafraichissement
        affListe(ldatas);
    }

    @Override
    protected void ajouter()
    {
        do
        {
            System.out.println("Mail :");
            String mail = sc.nextLine();
            System.out.println("Nom :");
            String nom = sc.nextLine();
            System.out.println("Prenom :");
            String prenom = sc.nextLine();
            Formateur f = null;
            try
            {
                f = new Formateur(0,mail,nom,prenom);
                presenter.add(f);
                break;
            }catch (Exception e)
            {
                System.out.println("Erreur :"+e);
            }


        }while (true);
        ldatas=presenter.getAll();
        affListe(ldatas);
    }



}
