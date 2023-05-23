package mvp.model;

import classesmetiers.Cours;
import classesmetiers.Local;


import java.sql.*;




public interface SessionCoursSpecial {

    public Local getLocalByID(int id);

    public Cours getCoursByID(int id);

}
