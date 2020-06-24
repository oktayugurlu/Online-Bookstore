package com.BoomBook.Service;

import com.BoomBook.DAO.PaymentServiceDAO;
import com.BoomBook.Model.PaymentService;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PaymentServiceDAOImpl implements PaymentServiceDAO {

    private EntityManager entityManager;

    @Autowired
    public PaymentServiceDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public List<PaymentService> findAll() {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        // create a query
        Query<PaymentService> theQuery = currentSession.createQuery("from PaymentService", PaymentService.class);
        // execute query and get result list
        List<PaymentService> paymentServices = theQuery.getResultList();
        // return the results
        return paymentServices;
    }

    @Override
    @Transactional
    public PaymentService findById(int id) {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        PaymentService paymentService = currentSession.get(PaymentService.class, id);

        return paymentService;
    }

    @Override
    @Transactional
    public void save(PaymentService paymentService) {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(paymentService);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // delete object with primary key
        Query theQuery = currentSession.createQuery( "delete from PaymentService where id=:payment_service_id");
        theQuery.setParameter("payment_service_id", id);
        theQuery.executeUpdate();
    }
}
