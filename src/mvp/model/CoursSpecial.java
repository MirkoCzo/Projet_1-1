package mvp.model;

import classesmetiers.Cours;

import java.util.List;

public interface CoursSpecial {
    List<Cours> getAllCours();//Bonus

    int insert_new_cours();

}
