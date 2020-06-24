package com.BoomBook.Service;

import com.BoomBook.DAO.BookDAO;
import com.BoomBook.Model.Book;
import java.util.List;
import java.util.Optional;


import javax.persistence.EntityManager;

import com.BoomBook.Model.BookComment;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class BookDAOImp implements BookDAO {

    private EntityManager entityManager;

    @Autowired
    public BookDAOImp(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }

    @Override
    @Transactional
    public List<Book> findAll() {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        // create a query
        Query<Book> theQuery = currentSession.createQuery("from Book", Book.class);
        // execute query and get result list
        List<Book> books = theQuery.getResultList();
        // return the results
        return books;
    }

    @Override
    public List<Book> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        System.out.println("HI");
        return null;
    }

    @Override
    public List<Book> findAllById(Iterable<Integer> iterable) {
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
    public void delete(Book book) {

    }

    @Override
    public void deleteAll(Iterable<? extends Book> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Book> S save(S theBook) {

        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(theBook);
        return null;
    }

    @Override
    public <S extends Book> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Book> findById(Integer integer) {
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
    public <S extends Book> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Book> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Book getOne(Integer integer) {
        return null;
    }

    @Override
    public <S extends Book> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Book> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Book> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Book> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Book> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Book> boolean exists(Example<S> example) {
        return false;
    }


    @Override
    @Transactional
    public Book findById(int theId) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        Book theBook = currentSession.get(Book.class, theId);

        return theBook;
    }


    @Override
    @Transactional
    public void deleteById(int theId) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // delete object with primary key
        Query theQuery =
                currentSession.createQuery(
                        "delete from Book where id=:bookId");
        theQuery.setParameter("bookId", theId);

        theQuery.executeUpdate();
    }


    @Override
    @Transactional
    public List<Book> findByisbn(String isbn) {


        Session currentSession = entityManager.unwrap(Session.class);

        Query<Book> theQuery = currentSession.createQuery("from Book where isbn = '"+isbn+"'", Book.class);
        // execute query and get result list
        List<Book> books = theQuery.getResultList();

        return books;
    }


    @Override
    public Page<Book> findByTitle(String title, int year, float number, Pageable pageable) {
        return null;
    }


    @Override
    public Page<Book> findBySubcategory(int id, Pageable pageable) {
        return null;
    }

    @Override
    public Page<Book> findByCategory(int id, Pageable pageable) {
        return null;
    }

    @Override
    public Page<Book> findCampaign(Pageable pageable) {
        return null;
    }

    @Override
    public Float bookRate(int bookId) {
        return null;
    }
}
