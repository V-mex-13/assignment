package com.greenfleet.dao;

import com.greenfleet.model.Trip;
import com.greenfleet.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TripDAO {

    public TripDAO() {}

    public boolean addTrip(Trip trip) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(trip);
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

    public List<Trip> getAllTrips() {
        List<Trip> result = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "SELECT t, " +
                         "(SELECT v.registrationNumber FROM Vehicle v WHERE v.vehicleId = t.vehicleId), " +
                         "(SELECT u.fullName FROM User u WHERE u.userId = (SELECT d.userId FROM Driver d WHERE d.driverId = t.driverId)) " +
                         "FROM Trip t ORDER BY t.startTime DESC";
            List<Object[]> rows = session.createQuery(hql, Object[].class).list();
            for (Object[] row : rows) {
                Trip t = (Trip) row[0];
                t.setVehicleReg((String) row[1]);
                t.setDriverName((String) row[2]);
                result.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }
}
