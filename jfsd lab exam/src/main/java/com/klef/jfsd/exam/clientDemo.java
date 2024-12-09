package com.klef.jfsd.exam;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import jakarta.persistence.Query;

public class clientDemo {
    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = cfg.buildSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            // HQL Update Operation with Positional Parameters
            String hql = "UPDATE Department SET name = ?1, location = ?2 WHERE deptId = ?3";
            Query query = session.createQuery(hql);
            query.setParameter(1, "Updated Name");
            query.setParameter(2, "Updated Location");
            query.setParameter(3, 1); // Assume we are updating the Department with ID 1

            int result = query.executeUpdate();
            System.out.println("Number of records updated: " + result);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
