package com.BoomBook.Service;

import javax.persistence.EntityManager;
import com.BoomBook.DAO.CourierCompanyDAO;
import com.BoomBook.Model.Book;
import com.BoomBook.Model.CourierCompany;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class CourierCompanyDAOImp implements CourierCompanyDAO {

    private EntityManager entityManager;

    @Autowired
    public CourierCompanyDAOImp(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }

    @Override
    @Transactional
    public List<CourierCompany> findAll() {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        // create a query
        Query<CourierCompany> theQuery = currentSession.createQuery("from CourierCompany", CourierCompany.class);
        // execute query and get result list
        List<CourierCompany> courierCompany = theQuery.getResultList();
        // return the results
        return courierCompany;
    }

    @Override
    public List<CourierCompany> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<CourierCompany> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<CourierCompany> findAllById(Iterable<Integer> iterable) {
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
    public void delete(CourierCompany courierCompany) {

    }

    @Override
    public void deleteAll(Iterable<? extends CourierCompany> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends CourierCompany> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<CourierCompany> findById(Integer integer) {
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
    public <S extends CourierCompany> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<CourierCompany> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public CourierCompany getOne(Integer integer) {
        return null;
    }

    @Override
    public <S extends CourierCompany> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends CourierCompany> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends CourierCompany> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends CourierCompany> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends CourierCompany> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends CourierCompany> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    @Transactional
    public CourierCompany findById(int theId) {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        CourierCompany theCourierCompany = currentSession.get(CourierCompany.class, theId);

        return theCourierCompany;
    }



    @Override
    public <S extends CourierCompany> S save(S theCourierCompany) {

        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(theCourierCompany);
        return null;
    }

    @Override
    @Transactional
    public void deleteById(int theId) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // delete object with primary key
        Query theQuery = currentSession.createQuery( "delete from CourierCompany where id=:courierCompanyId");
        theQuery.setParameter("courierCompanyId", theId);
        theQuery.executeUpdate();
    }


}
