package com.BoomBook.Service;

import com.BoomBook.DAO.BookCommentDAO;
import com.BoomBook.DAO.CategoryDAO;
import com.BoomBook.Model.BookComment;
import com.BoomBook.Model.Category;
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
public class BookCommentDAOImp implements BookCommentDAO {

    private EntityManager entityManager;

    @Autowired
    public BookCommentDAOImp(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }


    @Override
    @Transactional
    public List<BookComment> findAll() {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        Query<BookComment> theQuery = currentSession.createQuery("from BookComment", BookComment.class);

        List<BookComment> bookComments = theQuery.getResultList();

        return bookComments;

    }

    @Override
    public List<BookComment> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<BookComment> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<BookComment> findAllById(Iterable<Integer> iterable) {
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
    public void delete(BookComment bookComment) {

    }

    @Override
    public void deleteAll(Iterable<? extends BookComment> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends BookComment> S save(S s) {
        return null;
    }

    @Override
    public <S extends BookComment> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<BookComment> findById(Integer integer) {
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
    public <S extends BookComment> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<BookComment> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public BookComment getOne(Integer integer) {
        return null;
    }

    @Override
    public <S extends BookComment> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends BookComment> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends BookComment> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends BookComment> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends BookComment> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends BookComment> boolean exists(Example<S> example) {
        return false;
    }


    @Transactional
    @Override
    public BookComment findById(int theId) {

        Session currentSession = entityManager.unwrap(Session.class);
        Query<BookComment> theQuery = currentSession.createQuery("from BookComment where id =:param ", BookComment.class);
        theQuery.setParameter("param", theId);
        BookComment theBookComment = theQuery.getSingleResult();

        return theBookComment;

    }

/*    @Transactional
    @Override
    public void save(BookComment bookComment) {

        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(bookComment);
    }*/

    @Transactional
    @Override
    public void deleteById(int theId) {

        Session currentSession = entityManager.unwrap(Session.class);

        // delete object with primary key
        Query theQuery = currentSession.createQuery(  "delete from BookComment " +
                "where id=:theId");

        theQuery.setParameter("theId", theId);
        theQuery.executeUpdate();

    }

    @Override
    public List<BookComment> findByBookId(int BookID) {
        return null;
    }
}
