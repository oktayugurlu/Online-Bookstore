package com.BoomBook.Service;


import javax.persistence.EntityManager;

import com.BoomBook.DAO.CartDAO;
import com.BoomBook.Model.Cart;
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
public class CartDAOImp implements CartDAO {

    private EntityManager entityManager;

    @Autowired
    public CartDAOImp(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }

    @Override
    @Transactional
    public List<Cart> findAll() {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        // create a query
        Query<Cart> theQuery = currentSession.createQuery("from Cart", Cart.class);
        // execute query and get result list
        List<Cart> carts = theQuery.getResultList();
        // return the results
        return carts;
    }

    @Override
    public List<Cart> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Cart> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Cart> findAllById(Iterable<Integer> iterable) {
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
    public void delete(Cart cart) {

    }

    @Override
    public void deleteAll(Iterable<? extends Cart> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Cart> S save(S s) {
        return null;
    }

    @Override
    public <S extends Cart> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Cart> findById(Integer integer) {
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
    public <S extends Cart> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Cart> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Cart getOne(Integer integer) {
        return null;
    }

    @Override
    public <S extends Cart> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Cart> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Cart> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Cart> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Cart> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Cart> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    @Transactional
    public Cart findById(int theId) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        Cart theCart = currentSession.get(Cart.class, theId);
        return theCart;
    }


/*    @Override

    @Transactional
    public void save(Cart theCart) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(theCart);

    }*/


    @Override
    @Transactional
    public void deleteById(int theId) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // delete object with primary key
        Query theQuery = currentSession.createQuery( "delete from Cart where id=:cartId");
        theQuery.setParameter("cartId", theId);
        theQuery.executeUpdate();
    }

    @Override
    public Cart findCartByCustomer(Customer c) {
        return null;
    }
 
    public Cart checkCountOfBook(int customerId, int bookId) {
 
        return null;
    }

    @Override
    public List<Cart> findCartsByCustomer(Customer c) {
            return null;
    }

    public List<Cart> getCartsByUser(int customerId) {
        return null;
    }

    @Override
    public Float getTotalCart(int customerId) {
        return null;
    }

    @Override
    public List<Cart> checkIsCountValid(int customerId) {
        return null;
    }
}
