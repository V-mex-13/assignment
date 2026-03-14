package com.greenfleet.dao;

import com.greenfleet.model.MaintenanceLog;
import com.greenfleet.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class MaintenanceLogDAO {

    public MaintenanceLogDAO() {}

    public boolean addLog(MaintenanceLog log) {
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

    public List<MaintenanceLog> getAllLogs() {
        List<MaintenanceLog> logs = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "SELECT m, " +
                         "(SELECT v.registrationNumber FROM Vehicle v WHERE v.vehicleId = m.vehicleId) " +
                         "FROM MaintenanceLog m ORDER BY m.logDate DESC";
            List<Object[]> rows = session.createQuery(hql, Object[].class).list();
            for (Object[] row : rows) {
                MaintenanceLog log = (MaintenanceLog) row[0];
                log.setVehicleReg((String) row[1]);
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
