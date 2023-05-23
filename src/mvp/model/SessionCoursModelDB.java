package mvp.model;

import myconnections.DBconnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;


import classesmetiers.SessionCours;

public class SessionCoursModelDB extends Model<SessionCours>{
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

}
