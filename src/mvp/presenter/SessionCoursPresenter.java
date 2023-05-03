package mvp.presenter;

import mvp.model.DAOSessionCours;
import mvp.view.SessionCoursViewInterface;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SessionCoursPresenter {
    private DAOSessionCours model;

    private SessionCoursViewInterface view;

    private static final Logger logger = LogManager.getLogger(SessionCoursPresenter.class);
}
