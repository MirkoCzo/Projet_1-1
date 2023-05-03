package mvp.view;
import classesmetiers.Cours;
import mvp.presenter.CoursPresenter;
import static Utilitaires.Utilitaires.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class CoursViewConsole implements CoursViewInterface{

    private CoursPresenter presenter;

    private List<Cours> lc;

    private Scanner sc = new Scanner(System.in);

    public CoursViewConsole()
    {

    }

    @Override
    public void setPresenter(CoursPresenter presenter)
    {
        this.presenter = presenter;
    }

    @Override
    public void setListDatas(List<Cours> cours)
    {
        this.lc = cours;
        affListe(lc);
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
        affListe(infos);
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
    private void modifier()
    {
        int nl = choixElt(lc) -1;

        Cours cours = lc.get(nl);
        String matieres = modifyIfNotBlank("Matiere",cours.getMatiere());
        int heures = Integer.parseInt(modifyIfNotBlank("heures", String.valueOf(cours.getHeures())));
        presenter.update(new Cours(cours.getId(),matieres,heures));

    }

    private void ajouter()
    {
        System.out.println("matière : ");
        String mat = sc.nextLine();
        System.out.println("heures : ");
        int heures = sc.nextInt();
        presenter.addCours(new Cours(0,mat,heures));
        lc = presenter.getAll();
        affListe(lc);
    }
    @Override
    public Cours selectionner(List<Cours> lc)
    {
        int nl=choixListe(lc);
        Cours cours = lc.get(nl-1);
        return cours;
    }
    private void retirer()
    {
        int nl = choixElt(lc)-1;
        Cours cours = lc.get(nl);
        presenter.removeCours(cours);
        lc = presenter.getAll();
        affListe(lc);
    }

    private void rechercher()
    {
        System.out.println("idcours : ");
        int idCours = sc.nextInt();
        presenter.search(idCours);
    }

}
