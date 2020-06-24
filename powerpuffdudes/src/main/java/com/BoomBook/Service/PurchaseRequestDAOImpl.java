package com.BoomBook.Service;

import com.BoomBook.DAO.PurchaseRequestDAO;
import com.BoomBook.Model.PurchaseRequest;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class PurchaseRequestDAOImpl implements PurchaseRequestDAO {

    private EntityManager entityManager;

    @Autowired
    public PurchaseRequestDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /*
    @Override
    @Transactional
    public void save(PurchaseRequest purchaseRequest) {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(purchaseRequest);
    }
     */

    @Override
    @Transactional
    public List<PurchaseRequest> findAll() {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        // create a query
        Query<PurchaseRequest> theQuery = currentSession.createQuery("from PurchaseRequest", PurchaseRequest.class);
        // execute query and get result list
        List<PurchaseRequest> purchaseRequests = theQuery.getResultList();
        // return the results
        return purchaseRequests;
    }

    @Override
    public List<PurchaseRequest> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<PurchaseRequest> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<PurchaseRequest> findAllById(Iterable<Integer> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(PurchaseRequest purchaseRequest) {

    }

    @Override
    public void deleteAll(Iterable<? extends PurchaseRequest> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    @Transactional
    public <S extends PurchaseRequest> S save(S s) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(s);
        return null;
    }

    @Override
    public <S extends PurchaseRequest> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<PurchaseRequest> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends PurchaseRequest> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<PurchaseRequest> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public PurchaseRequest getOne(Integer integer) {
        return null;
    }

    @Override
    public <S extends PurchaseRequest> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends PurchaseRequest> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends PurchaseRequest> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends PurchaseRequest> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends PurchaseRequest> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends PurchaseRequest> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    @Transactional
    public PurchaseRequest findById(int id) {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        PurchaseRequest purchaseRequest = currentSession.get(PurchaseRequest.class, id);

        return purchaseRequest;
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // delete object with primary key
        Query theQuery = currentSession.createQuery( "delete from PurchaseRequest where id=:purchase_request_id");
        theQuery.setParameter("purchase_request_id", id);
        theQuery.executeUpdate();
    }

    @Override 
    public List<PurchaseRequest> findPurchaseRequestByCartID(int id) {
        return null;
    }

    @Override
    public List<PurchaseRequest> findPurchaseRequestByCustomerId(int id) {
        return null;
    }

    @Override
    public List<PurchaseRequest> findAllSorted() {
        return null;
    }
/*

    @Override
    public List<PurchaseRequest> findPurchaseRequestByCartID(int id) {
        return null;
    }


*/


}
