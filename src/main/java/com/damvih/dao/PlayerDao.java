package com.damvih.dao;

import com.damvih.entities.Player;
import com.damvih.exceptions.DatabaseOperationException;
import com.damvih.utils.HibernateUtils;
import jakarta.persistence.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class PlayerDao extends Dao<Player> {

    public Optional<Player> findByName(String name) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getInstance().openSession()) {
            transaction = session.beginTransaction();
            String jpqlQuery = """
                    select p from Player p where p.name=:name
                    """;
            Query query = session
                    .createQuery(jpqlQuery, Player.class)
                    .setParameter("name", name);
            List<Player> players = query.getResultList();
            transaction.commit();
            return players.isEmpty() ? Optional.empty() : Optional.of(players.getFirst());
        } catch (HibernateException exception) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new DatabaseOperationException();
        }
    }

}
