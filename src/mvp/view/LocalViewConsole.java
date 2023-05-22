package mvp.view;

import classesmetiers.Local;

import static Utilitaires.Utilitaires.*;


public class LocalViewConsole extends View<Local> {

    @Override
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

    @Override
    protected void modifier() {
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

    @Override
    protected void ajouter() {
        do
        {
            System.out.println("Sigle :");
            String sigle = sc.nextLine();
            System.out.println("Places :");
            int places = sc.nextInt();
            System.out.println("Description :");
            String desc = sc.nextLine();
            Local loc = null;
            try
            {
                loc = new Local(0,sigle,places,desc);
                presenter.add(loc);
                break;
            }catch (Exception e)
            {
                System.out.println("Erreur : "+e);
            }
        }while(true);
        ldatas=presenter.getAll();
        affListe(ldatas);
    }


    protected void retirer() {
        int choix = choixElt(ldatas);
        Local local = ldatas.get(choix-1);
        presenter.remove(local);
        ldatas = presenter.getAll();
        affListe(ldatas);
    }
}
