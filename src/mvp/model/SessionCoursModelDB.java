package mvp.model;

import classesmetiers.Local;
import classesmetiers.Cours;
import classesmetiers.SessionCours;
import myconnections.DBconnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.transform.Result;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class SessionCoursModelDB implements DAOSessionCours{
    private static final Logger logger = LogManager.getLogger(SessionCoursModelDB.class);
    protected Connection dbConnect;

    public SessionCoursModelDB()
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
    public SessionCours addSessionCours(SessionCours Sc) {
        String query1 = "insert into APISESSIONCOURS (DATEDEBUT,DATEFIN,NBREINSCRIT,ID_LOCAL,ID_COURS) values (?,?,?,?,?)";
        String query2 = "select ID_SESSIONCOURS from APISESSIONCOURS where ID_SESSIONCOURS = ?";
        try(PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
            PreparedStatement pstm2 = dbConnect.prepareStatement(query2);
            ){
            pstm1.setDate(1, Date.valueOf(Sc.getDateDebut()));
            pstm1.setDate(2,Date.valueOf(Sc.getDateFin()));
            pstm1.setInt(3,Sc.getNbreInscrits());
            pstm1.setInt(4,Sc.getLocal().getId_local());
            pstm1.setInt(5,Sc.getCours().getId());
            int n = pstm1.executeUpdate();
            if(n==1)
            {
                pstm2.setInt(1,Sc.getId_sessionCours());
                ResultSet rs = pstm2.executeQuery();
                if(rs.next())
                {
                    int id_sess = rs.getInt(1);
                    Sc.setId_sessionCours(id_sess);
                    return Sc;
                }
                else
                {
                    logger.error("record introuvable");
                    return null;
                }
            } else return null;
        }catch (SQLException e)
        {
            logger.error("erreur sql : "+e);
            return null;
        }
    }

    @Override
    public boolean removeSessionCours(SessionCours Sc) {
        String query = "delete from APISESSIONCOURS where ID_SESSIONCOURS = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query))
        {
            pstm.setInt(1,Sc.getId_sessionCours());
            int n = pstm.executeUpdate();
            if(n!=0) return true;
            else return false;
        } catch (SQLException e)
        {
            logger.error("erreur d'effacement : "+e);
            return false;
        }
    }

    @Override
    public SessionCours updateSessionCours(SessionCours sc) {
        String query = "update APISESSIONCOURS set DATEDEBUT = ?, DATEFIN = ?, NBREINSCRITS = ?, ID_LOCAL = ?, ID_COURS = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query))
        {
            pstm.setDate(1,Date.valueOf(sc.getDateDebut()));
            pstm.setDate(2,Date.valueOf(sc.getDateFin()));
            pstm.setInt(3,sc.getNbreInscrits());
            pstm.setInt(4,sc.getLocal().getId_local());
            pstm.setInt(5,sc.getCours().getId());
            int n = pstm.executeUpdate();
            if(n!=0) return readSessionCours(sc.getId_sessionCours());
            else return null;
        } catch(SQLException e)
        {
            logger.error("Erreur d'update : "+e);
            return null;
        }
    }

    @Override
    public SessionCours readSessionCours(int idSe) {
        String query = "select * from APISESSIONCOURS where ID_SESSIONCOURS = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query))
        {
            pstm.setInt(1,idSe);
            ResultSet rs = pstm.executeQuery();
            if(rs.next())
            {
                LocalDate dated = rs.getDate(2).toLocalDate();
                LocalDate datef = rs.getDate(3).toLocalDate();
                int NBREI = rs.getInt(4);
                Local loc = (Local) rs.getObject(5);
                Cours cours = (Cours) rs.getObject(6);
                SessionCours sc = new SessionCours(idSe,dated,datef,NBREI, cours, loc);
                return sc;
            }
            else {
                return null;
            }
        }catch(SQLException e)
        {
            logger.error("Erreur SQL : "+e);
            return null;
        }
    }

    @Override
    public List<SessionCours> getSessionCours() {
        return null;
    }
}
