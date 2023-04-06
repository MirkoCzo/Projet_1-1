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
        String query1 = "insert into APIFORMATEUR(ID_FORMATEUR,MAIL,NOM,PRENOM) values(?,?,?,?)";
        String query2 =  "select ID_FORMATEUR from APIFORMATEUR where mail = ?";
    }
}
