package mvp.model;

import classesmetiers.*;

import myconnections.DBconnection;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.transform.Result;

public class LocalModelDB implements  DAOLocal{
    private static final Logger logger = LogManager.getLogger(LocalModelDB.class);
    protected Connection dbConnect;

    public LocalModelDB()
    {
        dbConnect = DBconnection.getConnection();
        if(dbConnect == null)
        {
            logger.error("Erreur de connextion");
            System.exit(1);
        }
        logger.info("Connexion établie");
    }

    @Override
    public Local addLocal(Local local) {
        String query1 = "insert into APILOCAL (SIGLE,PLACES,DESCRIPTION) values (?,?,?)";
        String query2 = "select ID_LOCAL from APILOCAL where SIGLE = ?";
        try(PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
            PreparedStatement pstm2 = dbConnect.prepareStatement(query2);
        ){
            pstm1.setString(1,local.getSigle());
            pstm1.setInt(2,local.getPlaces());
            pstm1.setString(3,local.getDescription());
            int n = pstm1.executeUpdate();
            if(n==1)
            {
                pstm2.setString(1, local.getSigle());
                ResultSet rs = pstm2.executeQuery();
                if(rs.next())
                {
                    int id_loc = rs.getInt(1);
                    local.setId_local(id_loc);
                    return local;
                }
                else {
                    logger.error("Record introuvable");
                    return null;
                }
            }
            else return null;
        } catch (SQLException e)
        {
            logger.error("Erreur SQL : "+e);
            return null;
        }
    }

    @Override
    public boolean removeLocal(Local local) {
        String query = "delete from APILOCAL where ID_LOCAL = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query))
        {
            pstm.setInt(1,local.getId_local());
            int n = pstm.executeUpdate();
            if(n!=0) return true;
            else return false;
        } catch(SQLException e )
        {
            logger.error("Erreur d'effacement : "+e);
            return false;
        }
    }

    @Override
    public Local updateLocal(Local local) {
        String query = "Update APILOCAL set SIGLE = ?, PLACES = ?, DESCRIPTION = ? where ID_LOCAL = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query))
        {
            pstm.setString(1,local.getSigle());
            pstm.setInt(2,local.getPlaces());
            pstm.setString(3,local.getDescription());
            pstm.setInt(4,local.getId_local());
            int n = pstm.executeUpdate();
            if(n!=0) return readLocal(local.getId_local());
            else return null;
        } catch (SQLException e)
        {
            logger.error("Erreur d'update : "+e);
            return null;
        }
    }

    @Override
    public Local readLocal(int idLocal) {
        String query = "select * from APILOCAL where ID_LOCAL = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query))
        {
            pstm.setInt(1,idLocal);
            ResultSet rs = pstm.executeQuery();
            if(rs.next())
            {
                String sigle = rs.getString(2);
                int places = rs.getInt(3);
                String desc = rs.getString(4);
                Local loc = new Local(idLocal,sigle,places,desc);
                return loc;
            }
            else return null;
        } catch (SQLException e)
        {
            logger.error("Erreur SQL : "+e);
            return null;
        }
    }

    @Override
    public List<Local> getLocal() {
        List<Local> lloc = new ArrayList<>();
        String query = "Select * from APILOCAL";
        try(Statement stm = dbConnect.createStatement())
        {
            ResultSet rs = stm.executeQuery(query);
            while(rs.next())
            {
                int idloc = rs.getInt(1);
                String sigle = rs.getString(2);
                int places = rs.getInt(3);
                String desc = rs.getString(4);
                Local loc = new Local(idloc,sigle,places,desc);
                lloc.add(loc);
            }
            return lloc;
        } catch(SQLException e )
        {
            logger.error("Erreur SQL : "+e);
            return null;
        }
    }
}
