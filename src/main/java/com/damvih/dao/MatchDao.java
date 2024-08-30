package com.damvih.dao;

import com.damvih.entities.Match;
import com.damvih.exceptions.DatabaseOperationException;
import com.damvih.utils.HibernateUtils;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class MatchDao extends Dao<Match> {

    private static final int PAGE_SIZE = 8;

    public List<Match> findAll(int page, String playerName) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getInstance().openSession()) {
            transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Match> query = criteriaBuilder.createQuery(Match.class);
            Root<Match> root = getRoot(query);

            query.select(root);

            setPlayerNamePredicate(playerName, criteriaBuilder, query, root);

            List<Match> matches = getMatchesWithPagination(page, session, query);
            transaction.commit();
            return matches;
        } catch (HibernateException exception) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new DatabaseOperationException();
        }
    }

    public int getTotalPages(String playerName) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getInstance().openSession()) {
            transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Match> query = criteriaBuilder.createQuery(Match.class);
            Root<Match> root = getRoot(query);

            query.select(root);

            setPlayerNamePredicate(playerName, criteriaBuilder, query, root);

            Long totalRecords = getTotalRecords(session, query);
            transaction.commit();
            return (int) Math.ceil(totalRecords / (double) PAGE_SIZE);
        } catch (HibernateException exception) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new DatabaseOperationException();
        }
    }

    private Root<Match> getRoot(CriteriaQuery<Match> query) {
        Root<Match> root = query.from(Match.class);
        root.fetch("playerOne", JoinType.INNER);
        root.fetch("playerTwo", JoinType.INNER);
        return root;
    }

    private Long getTotalRecords(Session session, CriteriaQuery<Match> query) {
        return session.createQuery(query).getResultCount();
    }

    private <T> void setPlayerNamePredicate(String playerName, CriteriaBuilder criteriaBuilder, CriteriaQuery<T> query, Root<Match> root) {
        if (playerName == null || playerName.isEmpty()) {
            return;
        }
        Predicate predicate = getPlayerNamePredicate(playerName, criteriaBuilder, root);
        query.where(predicate);
    }

    private Predicate getPlayerNamePredicate(String playerName, CriteriaBuilder criteriaBuilder, Root<Match> root) {
        Predicate firstPlayerNamePredicate = criteriaBuilder.equal(root.get("playerOne").get("name"), playerName);
        Predicate secondPlayerNamePredicate = criteriaBuilder.equal(root.get("playerTwo").get("name"), playerName);

        return criteriaBuilder.or(firstPlayerNamePredicate, secondPlayerNamePredicate);
    }

    private List<Match> getMatchesWithPagination(int page, Session session, CriteriaQuery<Match> query) {
        return session.createQuery(query)
                .setFirstResult((page - 1) * PAGE_SIZE)
                .setMaxResults(PAGE_SIZE)
                .getResultList();
    }

}
