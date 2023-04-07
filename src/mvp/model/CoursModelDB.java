package mvp.model;

import classesmetiers.Cours;
import myconnections.DBconnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoursModelDB implements DAOCours {
    private static final Logger logger = LogManager.getLogger(CoursModelDB.class);
    protected Connection dbConnect;

    public CoursModelDB()
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
    public Cours addCours(Cours cours)
    {
        String query1 = "insert into APICOURS(MATIÈRE,HEURES) values (?,?)";
        String query2 = "select id_cours from APICOURS where MATIÈRE=?";
        try(PreparedStatement pstm1= dbConnect.prepareStatement(query1);
            PreparedStatement pstm2= dbConnect.prepareStatement(query2);
            )
        {
            pstm1.setString(1,cours.getMatiere());
            pstm1.setInt(2,cours.getHeures());
            int n = pstm1.executeUpdate();
            if(n==1)
            {
                pstm2.setString(1,cours.getMatiere());
                ResultSet rs = pstm2.executeQuery();
                if (rs.next())
                {
                    int id_Cours = rs.getInt(1);
                    cours.setId(id_Cours);
                    return cours;
                }
                else
                {
                    logger.error("record introuvable");
                    return null;
                }
            }
            else return null;
        }
        catch (SQLException e)
        {
            logger.error("erreur sql :"+e);
            return null;
        }
    }

    @Override
    public boolean removeCours(Cours cours)
    {
        String query = "delete from API where id_cours = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query))
        {
            pstm.setInt(1,cours.getId());
            int n = pstm.executeUpdate();
            if(n!=0) return true;
            else return false;
        }
        catch (SQLException e)
        {
            logger.error("Erreur : "+e);
            return false;
        }
    }

    @Override
    public Cours updateCours(Cours cours)
    {
        String query = "update APICOURS set MATIÈRE=?,HEURES=? where id_cours = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query))
        {
            pstm.setString(1,cours.getMatiere());
            pstm.setInt(2,cours.getHeures());
            pstm.setInt(3,cours.getId());
            int n = pstm.executeUpdate();
            if(n!=0) return readCours(cours.getId());
            else return null;
        }
        catch (SQLException e)
        {
            logger.error("Erreur d'update : "+e);
            return null;
        }
    }

    @Override
    public Cours readCours(int idCours)
    {
        String query = "select * from APICOURS where id_cours = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query))
        {
            pstm.setInt(1,idCours);
            ResultSet rs=pstm.executeQuery();
            if(rs.next())
            {
                String matière = rs.getString(2);
                int heures = rs.getInt(3);
                Cours c = new Cours(idCours,matière,heures);
                return c;
            }
            else return null;
        }
        catch(SQLException e)
        {
            logger.error("Erreur sql : "+e);
            return null;
        }
    }

    @Override
    public List<Cours> getCours()
    {
        List<Cours> lcours = new ArrayList<>();
        String query="select * from APICOURS";
        try(Statement stm = dbConnect.createStatement())
        {
            ResultSet rs = stm.executeQuery((query));
            while(rs.next())
            {
                int idcours = rs.getInt(1);
                String mat = rs.getString(2);
                int heures = rs.getInt(3);
                Cours c = new Cours(idcours,mat,heures);
                lcours.add(c);
            }
            return lcours;
        }
        catch(SQLException e)
        {
            logger.error("Erreur SQL : "+e);
            return null;
        }
    }

}
