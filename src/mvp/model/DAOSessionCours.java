package mvp.model;

import classesmetiers.Local;
import classesmetiers.SessionCours;

import java.util.List;

public interface DAOSessionCours {
    SessionCours addSessionCours(SessionCours Sc);

    boolean removeSessionCours(SessionCours Sc);

    SessionCours updateSessionCours(SessionCours sc);

    SessionCours readLocal(int idSe);

    List<SessionCours> getSessionCours();
}
