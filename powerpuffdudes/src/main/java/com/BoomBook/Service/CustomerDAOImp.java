package com.BoomBook.Service;


import javax.persistence.EntityManager;

import com.BoomBook.DAO.CustomerDAO;

import com.BoomBook.Model.Book;

import com.BoomBook.Model.BookComment;

import com.BoomBook.Model.Campaign;
import com.BoomBook.Model.Customer;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerDAOImp implements CustomerDAO {

    private EntityManager entityManager;

    @Autowired
    public CustomerDAOImp(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }

    @Override
    @Transactional
    public List<Customer> findAll() {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        // create a query
        Query<Customer> theQuery = currentSession.createQuery("from Customer", Customer.class);
        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();
        // return the results
        return customers;
    }

    @Override
    public List<Customer> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Customer> findAllById(Iterable<Integer> iterable) {
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
    public void delete(Customer customer) {

    }

    @Override
    public void deleteAll(Iterable<? extends Customer> iterable) {

    }

    @Override
    public void deleteAll() {

    }


    @Override
    public <S extends Customer> S save(S theCustomer) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(theCustomer);
        return null;
    }


    @Override
    public <S extends Customer> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Customer> findById(Integer integer) {
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
    public <S extends Customer> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Customer> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Customer getOne(Integer integer) {
        return null;
    }

    @Override
    public <S extends Customer> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Customer> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Customer> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Customer> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Customer> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Customer> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    @Transactional
    public Customer findById(int theId) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        Customer theCustomer = currentSession.get(Customer.class, theId);

        return theCustomer;
    }


    /*
    @Override
    @Transactional
    public void save(Customer theCustomer) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(theCustomer);
    }

     */


    @Override
    @Transactional
    public void deleteById(int theId) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // delete object with primary key
        Query theQuery = currentSession.createQuery( "delete from Customer where id=:customerId");
        theQuery.setParameter("customerId", theId);
        theQuery.executeUpdate();
    }

    @Override

    public Page<Customer> findCustomerById(int id, Pageable pageable) {
        return null;
    }



    public List<Customer> findByEmail(String email) {

        Session currentSession = entityManager.unwrap(Session.class);
        Query<Customer> theQuery = currentSession.createQuery("from Customer where email =:emailparam ", Customer.class);
        theQuery.setParameter("emailparam", email);
        List<Customer> customers = theQuery.getResultList();
        return customers;
    }

    @Override
 

    public Page<Customer> findByCustomerNameOrEmail(String name, Pageable pageable) {
        return null;
    }

 
    public Customer isCustomerMadeComment(int userId, int bookId) {
        return null;
    }

    @Override
    public List<Customer> isCustomerPurchaseBook(int userId, int bookId) {
  
        return null;
    }

}
