package mvp.model;

import classesmetiers.Cours;
import classesmetiers.Local;

import java.util.List;

public interface DAOLocal {
    Local addLocal(Local local);

    boolean removeLocal(Local local);

    Local updateLocal(Local local);

    Local readLocal(int idLocal);

    List<Local> getLocal();
}
