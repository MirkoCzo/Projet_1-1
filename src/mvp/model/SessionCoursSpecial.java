package mvp.model;

import classesmetiers.Cours;
import classesmetiers.Local;
import classesmetiers.SessionCours;

import java.util.List;


public interface SessionCoursSpecial {

    public Local getLocalByID(int id);

    public Cours getCoursByID(int id);


    int addSession(SessionCours sc);
}
