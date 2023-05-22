package mvp.model;

import classesmetiers.Cours;
import myconnections.DBconnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoursModelDB extends Model<Cours> {
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

}
