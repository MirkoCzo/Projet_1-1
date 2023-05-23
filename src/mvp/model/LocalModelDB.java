package mvp.model;

import classesmetiers.*;
import myconnections.DBconnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;



public class LocalModelDB extends Model<Local>
{
    private static final Logger logger = LogManager.getLogger(LocalModelDB.class);
    protected Connection dbConnect;

    public LocalModelDB()
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
