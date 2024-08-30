package com.damvih.utils;

import com.damvih.entities.Match;
import com.damvih.entities.Player;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

    private static final SessionFactory INSTANCE;

    static {
        Configuration configuration = new Configuration();
        addEntities(configuration);
        INSTANCE = configuration.buildSessionFactory();
    }

    private HibernateUtils() {

    }

    public static SessionFactory getInstance() {
        return INSTANCE;
    }

    private static void addEntities(Configuration configuration) {
        configuration.addAnnotatedClass(Player.class);
        configuration.addAnnotatedClass(Match.class);
    }

}
