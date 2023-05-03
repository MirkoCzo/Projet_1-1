package mvp.view;

import classesmetiers.Formateur;
import mvp.presenter.FormateurPresenter;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static Utilitaires.Utilitaires.*;

public class FormateurViewConsole implements FormateurViewInterface{
    private FormateurPresenter presenter;

    private List<Formateur> lf;

    private Scanner sc = new Scanner(System.in);

    public FormateurViewConsole()
    {

    }

    @Override
    public void setPresenter(FormateurPresenter presenter)
    {
        this.presenter = presenter;
    }

    @Override
    public void setListDatas(List<Formateur> formateurs)
    {
        this.lf = formateurs;
        affList(lf);
        menu();
    }
    @Override
    public void affMsg(String msg)
    {
        System.out.println("Information : "+msg);
    }
    @Override
    public void affList(List infos)
    {
        affList(infos);
    }

    public void menu()
    {
        int choix = choixListe(Arrays.asList("ajout","retirer","rechercher","modifier","fin"));
        switch (choix)
        {
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
        }while(true);
    }

    private void ajouter()
    {
        System.out.println("Nom : ");
        String nom = sc.nextLine();
        System.out.println("Prenom : ");
        String prenom = sc.nextLine();
        System.out.println("Mail : ");
        String mail = sc.nextLine();
        presenter.addFormateur(new Formateur(0,mail,nom,prenom));
        lf = presenter.getAll();
        affList(lf);
    }
    private void retirer()
    {
        int nl = choixElt(lf)-1;
        Formateur formateur = lf.get(nl);
        presenter.removeFormateur(formateur);
        lf = presenter.getAll();
        affList(lf);
    }
    private void rechercher()
    {
        int idFormateur = -1;
        do {
            try {
                System.out.println("Id Formateur : ");
                String ids = sc.nextLine();
                idFormateur = Integer.parseInt(ids);
                presenter.search(idFormateur);
                break;
            }catch(Exception e)
            {
                System.out.println("Vous devez entrer un nombre entier");
            }
        }while(true);

    }
    private void modifier()
    {
        int nl = choixElt(lf)-1;
        Formateur formateur = lf.get(nl);
        String mail = modifyIfNotBlank("mail",formateur.getMail());
        String nom = modifyIfNotBlank("nom",formateur.getNom());
        String prenom = modifyIfNotBlank("prenom",formateur.getPrenom());
        presenter.update(new Formateur(formateur.getId_formateur(),mail,nom,prenom));
        lf = presenter.getAll();
        affList(lf);
    }
    public Formateur selectionner(List<Formateur> lf)
    {
        int nl = choixListe(lf);
        Formateur formateur = lf.get(nl-1);
        return formateur;
    }
}
