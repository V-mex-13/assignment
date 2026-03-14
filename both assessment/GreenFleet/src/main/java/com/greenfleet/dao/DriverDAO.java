package com.greenfleet.dao;

import com.greenfleet.model.Driver;
import com.greenfleet.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class DriverDAO {

    public DriverDAO() {}

    public boolean addDriver(Driver driver) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(driver);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public List<Driver> getAllDrivers() {
        List<Driver> result = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            List<Object[]> rows = session.createQuery(
                "SELECT d, (SELECT u.fullName FROM User u WHERE u.userId = d.userId) FROM Driver d", 
                Object[].class).list();
            for (Object[] row : rows) {
                Driver d = (Driver) row[0];
                d.setDriverName((String) row[1]);
                result.add(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }
    
    public Driver getDriverById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Object[] row = session.createQuery(
                "SELECT d, (SELECT u.fullName FROM User u WHERE u.userId = d.userId) FROM Driver d WHERE d.driverId = :id", 
                Object[].class)
                .setParameter("id", id)
                .uniqueResult();
            if (row != null) {
                Driver d = (Driver) row[0];
                d.setDriverName((String) row[1]);
                return d;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }
}
