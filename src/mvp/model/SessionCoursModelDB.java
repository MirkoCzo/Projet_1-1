package mvp.model;

import classesmetiers.Cours;
import classesmetiers.Local;
import myconnections.DBconnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import classesmetiers.SessionCours;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SessionCoursModelDB implements DAO<SessionCours>,SessionCoursSpecial{
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
    public SessionCours add(SessionCours Sc) {
        String query1 = "INSERT INTO APISESSIONCOURS (DATEDEBUT,DATEFIN,NBREINSCRITS,ID_LOCAL,ID_COURS) VALUES (?,?,?,?,?)";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS)) {
            pstm1.setDate(1, Date.valueOf(Sc.getDateDebut()));
            pstm1.setDate(2, Date.valueOf(Sc.getDateFin()));
            pstm1.setInt(3, Sc.getNbreInscrits());
            pstm1.setInt(4, Sc.getLocal().getId_local());
            pstm1.setInt(5, Sc.getCours().getId());
            int n = pstm1.executeUpdate();
            if (n == 1) {
                ResultSet rs = pstm1.getGeneratedKeys();
                if (rs.next()) {
                    String rowId = rs.getString(1);
                    Statement stmt = dbConnect.createStatement();
                    ResultSet rs2 = stmt.executeQuery("SELECT ID_SESSIONCOURS FROM APISESSIONCOURS WHERE ROWID = '" + rowId + "'"); //J'ai du passer par la car getGeneratedKeys() me renvoyait le ROWID et pas la valeur de mon trigger et je ne peux pas passer par une deuxieme requete car je n'ai rien d'unique
                    if (rs2.next()) {
                        int id_sess = rs2.getInt(1);
                        Sc.setId_sessionCours(id_sess);
                        return Sc;
                    } else {
                        logger.error("record introuvable");
                        return null;
                    }
                } else {
                    logger.error("record introuvable");
                    return null;
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            logger.error("erreur sql : " + e);
            return null;
        }
    }



    @Override
    public boolean remove(SessionCours Sc) {
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
    public SessionCours update(SessionCours sc) {
        String query = "update APISESSIONCOURS set DATEDEBUT = ?, DATEFIN = ?, NBREINSCRITS = ?, ID_LOCAL = ?, ID_COURS = ? WHERE ID_SESSIONCOURS = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query))
        {
            pstm.setDate(1,Date.valueOf(sc.getDateDebut()));
            pstm.setDate(2,Date.valueOf(sc.getDateFin()));
            pstm.setInt(3,sc.getNbreInscrits());
            pstm.setInt(4,sc.getLocal().getId_local());
            pstm.setInt(5,sc.getCours().getId());
            pstm.setInt(6,sc.getId_sessionCours());
            int n = pstm.executeUpdate();
            if(n!=0) return read(sc);
            else return null;
        } catch(SQLException e)
        {
            logger.error("Erreur d'update : "+e);
            return null;
        }
    }

    @Override
    public SessionCours read(SessionCours sess) {
        String query = "select * from APISESSIONCOURS where ID_SESSIONCOURS = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query))
        {
            pstm.setInt(1,sess.getId_sessionCours());
            ResultSet rs = pstm.executeQuery();
            if(rs.next())
            {
                LocalDate dated = rs.getDate(2).toLocalDate();
                LocalDate datef = rs.getDate(3).toLocalDate();
                int NBREI = rs.getInt(4);
                int idloc = rs.getInt(5);
                int idcours = rs.getInt(6);
                Cours c = getCoursByID(idcours);
                Local l = getLocalByID(idloc);
                SessionCours sc = new SessionCours(sess.getId_sessionCours(),dated,datef,NBREI, c, l);
                return sc;
            }
            else {
                System.out.println("Aucune session trouvée");
                return null;
            }
        }catch(SQLException e)
        {
            logger.error("Erreur SQL : "+e);
            return null;
        }
    }

    @Override
    public List<SessionCours> getAll() {
        List<SessionCours> lsc = new ArrayList<>();
        String query = "Select * from APISESSIONCOURS";
        try(Statement stm = dbConnect.createStatement())
        {
            ResultSet rs = stm.executeQuery(query);
            while(rs.next())
            {
                int idsc = rs.getInt(1);
                LocalDate dated = rs.getDate(2).toLocalDate();
                LocalDate datef = rs.getDate(3).toLocalDate();
                int NBREI = rs.getInt(4);
                int idloc = rs.getInt(5);
                int idcours = rs.getInt(6);
                Cours c = getCoursByID(idcours);
                Local l = getLocalByID(idloc);
                SessionCours sc = new SessionCours(idsc,dated,datef,NBREI,c,l);
                lsc.add(sc);
            }
            return lsc;
        } catch(SQLException e )
        {
            logger.error("Erreur SQL : "+e);
            return null;
        }
    }

    @Override
    public Local getLocalByID(int id) {
        Local tmpLocal;
        String query = "SELECT * FROM APILOCAL WHERE ID_LOCAL = ?";

        try(PreparedStatement pstm = dbConnect.prepareStatement(query))
        {
            pstm.setInt(1,id);
            ResultSet rs = pstm.executeQuery();
            if(rs.next())
            {
                int localId = rs.getInt(1);
                String sigle = rs.getString(2);
                int places = rs.getInt(3);
                String desc = rs.getString(4);
                tmpLocal = new Local(localId,sigle,places,desc);
                return tmpLocal;

            }
            else return null;

        }catch (SQLException e)
        {
            logger.error("Erreur "+e);
            return null;
        }

    }

    @Override
    public Cours getCoursByID(int id) {
        Cours tmpCours;
        String query = "SELECT * FROM APICOURS WHERE ID_COURS = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query))
        {
            pstm.setInt(1,id);
            ResultSet rs = pstm.executeQuery();
            if(rs.next())
            {
                int id_cours = rs.getInt(1);
                String mat = rs.getString(2);
                int heures = rs.getInt(3);
                tmpCours = new Cours(id_cours,mat,heures);
                return tmpCours;
            }
            else return null;
        }catch (SQLException e)
        {
            logger.error("Erreur "+e);
            return null;
        }
    }
}
