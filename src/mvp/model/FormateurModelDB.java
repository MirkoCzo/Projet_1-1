package mvp.model;

import classesmetiers.Formateur;
import myconnections.DBconnection;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class FormateurModelDB implements DAOFormateur {

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
    public Formateur addFormateur(Formateur formateur)
    {
        String query1 = "insert into APIFORMATEUR(MAIL,NOM,PRENOM) values(?,?,?)";
        String query2 =  "select ID_FORMATEUR from APIFORMATEUR where mail = ?";
        try(PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
            PreparedStatement pstm2 = dbConnect.prepareStatement(query2);
        ){
            pstm1.setString(1,formateur.getMail());
            pstm1.setString(2,formateur.getNom());
            pstm1.setString(3,formateur.getPrenom());
            int n = pstm1.executeUpdate();
            if(n==1)
            {
                pstm2.setString(1,formateur.getMail());
                ResultSet rs = pstm2.executeQuery();
                if(rs.next())
                {
                    int idform = rs.getInt(1);
                    formateur.setId_formateur(idform);
                    return formateur;
                }
                else {
                    logger.error("Record introuvable");
                    return null;
                }
            }
            else return null;
        }catch (SQLException e )
        {
            logger.error("Erreur sql : "+e);
            return null;
        }

    }
    @Override
    public boolean removeFormateur(Formateur formateur)
    {
        String query = "delete from APICOURS where ID_FORMATEUR = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query))
        {

        }
    }
}
