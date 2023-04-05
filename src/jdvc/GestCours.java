package jdvc;

import Utilitaires.Utilitaires;
import classesmetiers.Gestion;
import classesmetiers.Cours;
import classesmetiers.Formateur;
import classesmetiers.Local;
import classesmetiers.SessionCours;
import myconnections.DBconnection;

import javax.sound.midi.SysexMessage;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

public class GestCours {
    private Scanner sc = new Scanner(System.in);
    private Connection dbConnect;

    public void gestion()
    {
        dbConnect = DBconnection.getConnection();
        if(dbConnect == null)
        {
            System.exit(1);
        }
        System.out.println("Connexion établie");
        do {
            System.out.println("1.ajout\n2.recherche\n3.modifier\n4.suppression\n5.tous\n6.fin");
            System.out.println("choix : ");
            int choix = sc.nextInt();
            sc.skip("\n");
            switch (choix)
            {
                case 1:
                    ajout();
                    break;
                case 2:
                    recherche();
                    break;
                case 3:
                    modification();
                    break;
                case 4:
                    suppression();
                    break;
                case 5:
                    tous();
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Choix invalide recommencez.");

            }
        }while (true);
    }
    public void ajout() {
        System.out.println("Nom de la matière du cours? :");
        String mat = sc.nextLine();
        System.out.println("Nombre d'heures du cours? :");
        int heures = sc.nextInt();
        sc.skip("\n");
        String query1 = "insert into APICOURS(MATIÈRE,HEURES) values (?,?)";
        String query2 = "select ID_COURS from APICOURS where rownum = 1 order by ID_COURS desc"; //Selectionne le dernier cours rentré
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2)) {
            pstm1.setString(1, mat);
            pstm1.setInt(2, heures);
            int n = pstm1.executeUpdate();
            System.out.println(n + " ligne insérée");

            // Récupération de l'ID du dernier cours créé
            try (ResultSet rs = pstm2.executeQuery()) {
                if (rs.next()) {
                    int idCours = rs.getInt("ID_COURS");
                    System.out.println("Nouveau cours créé avec l'ID : " + idCours);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur sql : " + e);
        }
    }
    public void recherche()
    {
        System.out.println("Id du cours recherché ");
        int id = sc.nextInt();
        String query = "select * from APICOURS where ID_COURS = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query))
        {
            pstm.setInt(1,id);
            ResultSet rs = pstm.executeQuery();
            if(rs.next())
            {
                String mat = rs.getString(2);
                int heures = rs.getInt(3);
                Cours c = new Cours(id,mat,heures);
                System.out.println(c);
                opSpeciales(c);
            }
            else System.out.println("Record introuvable. ");
        }catch (SQLException e)
        {
            System.out.println("Erreur SQL :"+e);
        }

    }
    private void opSpeciales(Cours cours)
    {
        do {

                System.out.println("1.Tout les formateurs sans doublons\n2.Toutes les sessions + local\n3.Toutes les sessions + local entre 2 dates\n4.Retour");
                System.out.println("Choix : ");
                int choix = sc.nextInt();
                sc.skip("\n");
                switch (choix)
                {
                    case 1:
                        formateurs(cours); //TODO tout les formateurs sans doublons
                        break;
                    case 2:
                        sessions(cours); //TODO toutes les sessions avec le local
                        break;
                    case 3:
                        sessionsDates(cours); //TODO toutes les sessions avec le local dont la date de début est comprise entre 2 dates encodées
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Mauvais choix recommencez ");
                }
        }while(true);
    }
    /*private void rechercheForm(PreparedStatement pstm) throws SQLException
    {
        ResultSet rs = pstm.executeQuery();
        boolean trouve = false;
        while (rs.next())
        {
            trouve=true;
            int id_form = rs.getInt(1);
            String mail = rs.getString(2);
            String nom = rs.getString(3);
            String prenom = rs.getString(4);
            Formateur f = new Formateur(id_form,mail,nom,prenom);
            System.out.println(f);

        }
    }*/
    /*public void formateurs(Cours cours) //Sans vue
    {
        boolean trouve=false;
        String query = "SELECT DISTINCT F.* FROM APIFORMATEUR F JOIN APIINFOS I ON F.ID_FORMATEUR = I.ID_FORMATEUR JOIN APISESSIONCOURS SE ON I.ID_SESSIONCOURS = SE.ID_SESSIONCOURS JOIN APICOURS C ON SE.ID_COURS = C.ID_COURS WHERE SE.ID_COURS = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query))
        {
            pstm.setInt(1,cours.getId());
            ResultSet rs = pstm.executeQuery();
            while(rs.next())
            {
                trouve=true;
                int id_form= rs.getInt(1);
                String mail = rs.getString(2);
                String nom = rs.getString(3);
                String prenom = rs.getString(4);
                Formateur f = new Formateur(id_form,mail,nom,prenom);
                System.out.println(f);
            }
            if(!trouve)
            {
                System.out.println("Pas de prof pour ce cours.");
            }

            }catch (SQLException e )
        {
            System.out.println("Erreur sql:"+e);
        }
    }*/
    public void formateurs(Cours cours)
    {
        String query = "SELECT * FROM FORMATEURSPARCOURS where id_cours = ?";
        boolean trouve = false;
        try(PreparedStatement pstm = dbConnect.prepareStatement(query))
        {
            pstm.setInt(1,cours.getId());
            ResultSet rs = pstm.executeQuery();
            while(rs.next())
            {
                trouve=true;
                int id_form=rs.getInt(1);
                String nom=rs.getString(2);
                String prenom=rs.getString(3);
                int id_cours=rs.getInt(4);
                System.out.println("Cours : "+cours.getMatiere()+" Nom :"+nom+" prenom :"+prenom);
            }
            if(!trouve)
            {
                System.out.println("aucun formateurs pour ce cours");
            }
        }
        catch(SQLException e)
        {
            System.out.println("ERREUR Sql :"+e);
        }
    }
    public void sessions(Cours cours)
    {
        String query = "SELECT * FROM APISESSIONCOURS SE JOIN APICOURS C ON SE.ID_COURS = C.ID_COURS WHERE C.ID_COURS = ?";

        boolean trouve=false;
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)){
            pstm.setInt(1,cours.getId());
            ResultSet rs = pstm.executeQuery();
            while (rs.next())
            {
                trouve=true;
                int id_sess = rs.getInt(1);
                java.sql.Date datedebutSQL = rs.getDate(2);
                java.sql.Date datefinSQL = rs.getDate(3);
                LocalDate datedebut = datedebutSQL.toLocalDate();
                LocalDate datefin = datefinSQL.toLocalDate();
                int nbrI = rs.getInt(4);
                int id_loc = rs.getInt(5);
                SessionCours se = new SessionCours(id_sess,datedebut,datefin,nbrI,cours,returnLoc(id_loc));
                System.out.println(se);
            }
            if(!trouve)
            {
                System.out.println("Pas de session pour ce cours");
            }

        }catch (SQLException e)
        {
            System.out.println("Erreur sql :"+e);
        }

    }
    public Local returnLoc(int id)
    {
        String query = "Select * from APILOCAL where ID_LOCAL ="+id;
        try(PreparedStatement pstm=dbConnect.prepareStatement(query))
        {
            ResultSet rs = pstm.executeQuery();
            while (rs.next())
            {
                int id_loc = rs.getInt(1);
                String sigle = rs.getString(2);
                int places = rs.getInt(3);
                String desc = rs.getString(4);
                Local l = new Local(id_loc,sigle,places,desc);
                return l;
            }



        }catch (SQLException e)
        {
            System.out.println("Erreur SQL:"+e);
        }
        return null;
    }
    public void sessionsDates(Cours cours)
    {
        System.out.println("Entrez la première date (jour-mois-année) : ");
        LocalDate d1 = Utilitaires.lectureDate();
        System.out.println("Entrez la deuxième date (jour-mois-année) : ");
        LocalDate d2 = Utilitaires.lectureDate();
        java.sql.Date d1Sql = java.sql.Date.valueOf(d1);
        java.sql.Date d2Sql = java.sql.Date.valueOf(d2);
        boolean trouve=false;
        String query = "select * from APISESSIONCOURS where APISESSIONCOURS.ID_COURS = ? AND datedebut>=? AND datefin<=?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query))
        {
            pstm.setInt(1,cours.getId());
            pstm.setDate(2,d1Sql);
            pstm.setDate(3,d2Sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()) {
                int id_sess = rs.getInt(1);
                java.sql.Date datedebutSQL = rs.getDate(2);
                java.sql.Date datefinSQL = rs.getDate(3);
                LocalDate datedebut = datedebutSQL.toLocalDate();
                LocalDate datefin = datefinSQL.toLocalDate();
                int nbrI = rs.getInt(4);
                int id_loc = rs.getInt(5);
                SessionCours se = new SessionCours(id_sess, datedebut, datefin, nbrI, cours, returnLoc(id_loc));
                System.out.println(se);
                trouve=true;
            }
            if (!trouve)
            {
                System.out.println("Pas de sessions entre ces 2 dates ");
            }

        }catch (SQLException e)
        {
            System.out.println("erreur sql :"+e);
        }

    }
    public void modification()
    {
        System.out.println("Id du cours à modifier");
        int id = sc.nextInt();
        sc.skip("\n");
        System.out.println("Souhaitez-vous modifier le 1.nom du cours ou 2.les heures du cours");
        int choix;
        do {
            choix=sc.nextInt();
            switch(choix)
            {
                case 1:
                    System.out.println("Nouveau nom de cours ");
                    sc.skip("\n");
                    String nom=sc.nextLine();
                    String query = "update APICOURS set MATIÈRE=? where ID_COURS = ?";
                    try(PreparedStatement pstm = dbConnect.prepareStatement(query))
                    {
                        pstm.setString(1,nom);
                        pstm.setInt(2,id);
                        int n = pstm.executeUpdate();
                        if(n!=0) System.out.println(n+ "ligne mise à jour");
                        else System.out.println("record introuvable");
                    }catch (SQLException e)
                    {
                        System.out.println("erreur sql : "+e);
                    }
                    break;
                case 2:
                    System.out.println("Nouveau nombre d'heures ");
                    int heures=sc.nextInt();
                    String query1 = "update APICOURS set HEURES=? where ID_COURS = ?";
                    try(PreparedStatement pstm = dbConnect.prepareStatement(query1))
                    {
                        pstm.setInt(1,heures);
                        pstm.setInt(2,id);
                        int n = pstm.executeUpdate();
                        if(n!=0) System.out.println(n+ "ligne mise à jour");
                        else System.out.println("record introuvable");
                    }catch (SQLException e)
                    {
                        System.out.println("erreur sql : "+e);
                    }
                    break;

                default:
                    System.out.println("Mauvais choix recommencez");
            }
        }while (choix!=1&&choix!=2);
    }
    public void suppression()
    {
        System.out.println("Id du cours à supprimer ");
        int id = sc.nextInt();
        String query = "delete from APICOURS where id_cours = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query))
        {
            pstm.setInt(1,id);
            int n = pstm.executeUpdate();
            if(n!=0) {
                System.out.println(n + " ligne supprimée");

            }
            else System.out.println("Record introuvable");
        }catch (SQLException e)
        {
            System.out.println("erreur sql : "+e);
        }
    }
    public void tous()
    {
        String query="select * from APICOURS";
        try(Statement stm = dbConnect.createStatement())
        {
            ResultSet rs = stm.executeQuery(query);
            while(rs.next())
            {
                int idcours= rs.getInt(1);
                String mat = rs.getString(2);
                int heures = rs.getInt(3);
                Cours c = new Cours(idcours,mat,heures);
                System.out.println(c);
            }
        }catch (SQLException e)
        {
            System.out.println("Erreur sql :"+e);
        }

    }
    public static void main(String[] args)
    {
        GestCours g = new GestCours();
        g.gestion();
    }
}
