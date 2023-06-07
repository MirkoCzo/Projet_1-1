package mvp.presenter;

import classesmetiers.Cours;

import java.util.List;

public interface CoursSpecialPresenter {
     List<Cours> getAllCours();//Bonus

     int insert_new_cours();
}
