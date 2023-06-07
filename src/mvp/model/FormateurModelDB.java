package mvp.model;

import classesmetiers.Formateur;
import myconnections.DBconnection;
import oracle.jdbc.OracleTypes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class FormateurModelDB implements DAO<Formateur>,FormateurSpecial{
    private static final Logger logger = LogManager.getLogger(FormateurModelDB.class);
    protected Connection dbConnect;

    public FormateurModelDB()
    {
        dbConnect = DBconnection.getConnection();
        if(dbConnect == null)
        {
            logger.error("Erreur de connexion");
            System.exit(1);
        }
        logger.info("Connexion établie");
    }


    @Override
    public Formateur add(Formateur formateur) {
        String query1 = "insert into APIFORMATEUR (MAIL, NOM, PRENOM) values (?, ?, ?)";
        String query2 = "select ID_FORMATEUR from APIFORMATEUR where MAIL = ?";

        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2)) {

            pstm1.setString(1, formateur.getMail());
            pstm1.setString(2, formateur.getNom());
            pstm1.setString(3, formateur.getPrenom());

            int n = pstm1.executeUpdate();

            if (n == 1) {
                pstm2.setString(1, formateur.getMail());
                ResultSet rs = pstm2.executeQuery();

                if (rs.next()) {
                    int idFormateur = rs.getInt(1);
                    formateur.setId_formateur(idFormateur);
                    return formateur;
                } else {
                    logger.error("Record introuvable");
                    return null;
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            logger.error("Erreur SQL : " + e);
            return null;
        }
    }


    @Override
    public boolean remove(Formateur formateur)
    {
        String query = "delete from APIFORMATEUR where ID_FORMATEUR = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query))
        {
            pstm.setInt(1,formateur.getId_formateur());
            int n = pstm.executeUpdate();
            if(n!=0) return true;
            else return false;
        }
        catch (SQLException e)
        {
            logger.error("Erreur d'effacement : "+e);
            return false;
        }
    }

    @Override
    public Formateur update(Formateur formateur)
    {
        String query = "update APIFORMATEUR set MAIL = ?, NOM = ?, PRENOM = ? where ID_FORMATEUR = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query))
        {
            pstm.setString(1, formateur.getMail());
            pstm.setString(2, formateur.getNom());
            pstm.setString(3, formateur.getPrenom());
            pstm.setInt(4,formateur.getId_formateur());
            int n = pstm.executeUpdate();
            if(n!=0) return read(formateur);
            else return null;
        }
        catch (SQLException e)
        {
            logger.error("Erreur d'update : "+e);
            return null;
        }
    }

    @Override
    public Formateur read(Formateur Formateur)
    {
        String query = "select * from APIFORMATEUR where ID_FORMATEUR = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query))
        {
            pstm.setInt(1,Formateur.getId_formateur());
            ResultSet rs = pstm.executeQuery();
            if(rs.next())
            {
                String mail = rs.getString(2);
                String nom = rs.getString(3);
                String prenom = rs.getString(4);
                Formateur f = new Formateur(Formateur.getId_formateur(),mail,nom,prenom);
                return f;
            }
            else
            {
                System.out.println("Aucun formateur trouvé");
                return null;
            }
        }
        catch (SQLException e)
        {
            logger.error("Erreur SQL : "+e);
            return null;
        }
    }

    @Override
    public List<Formateur> getAll()
    {
        List<Formateur> lf = new ArrayList<>();
        String query = "select * from APIFORMATEUR";
        try(Statement stm = dbConnect.createStatement())
        {
            ResultSet rs = stm.executeQuery(query);
            while(rs.next())
            {
                int idform = rs.getInt(1);
                String Mail = rs.getString(2);
                String Nom = rs.getString(3);
                String Prenom = rs.getString(4);
                Formateur f = new Formateur(idform,Mail,Nom,Prenom);
                lf.add(f);
            }
            return lf;
        } catch(SQLException e)
        {
            logger.error("Erreur SQL : "+e);
            return null;
        }

    }

    @Override
    public int getTotalFormateurHeures(int idSessionCours) {
        int totalHeures = 0;
        try(CallableStatement cs = dbConnect.prepareCall("{?=call GET_TOTAL_FORMATEUR_HEURES(?)}"))
        {
            cs.registerOutParameter(1, OracleTypes.NUMBER);
            cs.setInt(2, idSessionCours);
            cs.executeQuery();
            totalHeures = cs.getInt(1);
            System.out.println("Total des heures de formateur pour la session " + idSessionCours + ": " + totalHeures);
        }catch(Exception e)
        {
            System.out.println("Erreur "+e);
        }
        return totalHeures;
    }

}
