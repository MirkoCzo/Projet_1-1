package mvp.model;

import classesmetiers.Formateur;
import myconnections.DBconnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;


public class FormateurModelDB extends Model<Formateur>{
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


}
