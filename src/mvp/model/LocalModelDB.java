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

public class LocalModelDB implements  DAOLocal{
    private static final Logger logger = LogManager.getLogger(LocalModelDB.class);
    protected Connection dbConnect;

    @Override
    public Local addLocal(Local local) {
        return null;
    }

    @Override
    public boolean removeLocal(Local local) {
        return false;
    }

    @Override
    public Local updateLocal(Local local) {
        return null;
    }

    @Override
    public Local readLocal(int idLocal) {
        return null;
    }

    @Override
    public List<Local> getLocal() {
        return null;
    }
}
