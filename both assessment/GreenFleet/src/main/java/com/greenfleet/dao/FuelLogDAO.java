package com.greenfleet.dao;

import com.greenfleet.model.FuelLog;
import com.greenfleet.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class FuelLogDAO {

    public FuelLogDAO() {}

    public boolean addLog(FuelLog log) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(log);
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

    public List<FuelLog> getAllLogs() {
        List<FuelLog> logs = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "SELECT f, " +
                         "(SELECT v.registrationNumber FROM Vehicle v WHERE v.vehicleId = f.vehicleId), " +
                         "(SELECT u.fullName FROM User u WHERE u.userId = (SELECT d.userId FROM Driver d WHERE d.driverId = f.driverId)) " +
                         "FROM FuelLog f ORDER BY f.fillUpDate DESC";
            List<Object[]> rows = session.createQuery(hql, Object[].class).list();
            for (Object[] row : rows) {
                FuelLog log = (FuelLog) row[0];
                log.setVehicleReg((String) row[1]);
                log.setDriverName((String) row[2]);
                logs.add(log);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return logs;
    }
}
