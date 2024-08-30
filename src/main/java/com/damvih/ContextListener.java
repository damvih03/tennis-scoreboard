package com.damvih;

import com.damvih.services.FinishedMatchesPersistenceService;
import com.damvih.services.MatchScoreCalculationService;
import com.damvih.services.OngoingMatchesService;
import com.damvih.utils.HibernateUtils;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.hibernate.SessionFactory;

@WebListener
public class ContextListener implements ServletContextListener {

    private SessionFactory sessionFactory;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();

        sessionFactory = HibernateUtils.getInstance();

        createServiceObjects(context);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    private void createServiceObjects(ServletContext context) {
        OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();
        context.setAttribute("OngoingMatchesService", ongoingMatchesService);

        MatchScoreCalculationService matchScoreCalculationService = new MatchScoreCalculationService();
        context.setAttribute("MatchScoreCalculationService", matchScoreCalculationService);

        FinishedMatchesPersistenceService finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();
        context.setAttribute("FinishedMatchesPersistenceService", finishedMatchesPersistenceService);
    }

}
