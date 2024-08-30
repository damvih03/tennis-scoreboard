package com.damvih.dao;

import com.damvih.exceptions.DatabaseOperationException;
import com.damvih.utils.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

abstract public class Dao<T> {

    public T save(T entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getInstance().openSession()) {
            transaction = session.beginTransaction();
            T mergedEntity = (T) session.merge(entity);
            transaction.commit();
            return mergedEntity;
        } catch (HibernateException exception) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new DatabaseOperationException();
        }
    }
}
