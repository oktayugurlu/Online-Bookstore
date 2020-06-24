package com.BoomBook.Service;



import com.BoomBook.DAO.MailDAO;
import com.BoomBook.Model.Book;
import com.BoomBook.Model.Mail;
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
public class MailDAOImp implements MailDAO {

    private EntityManager entityManager;

    @Autowired
    public MailDAOImp(EntityManager theEntityManager){ entityManager = theEntityManager; }


    @Override
    @Transactional
    public List<Mail> findAll() {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        // create a query
        Query<Mail> theQuery = currentSession.createQuery("from Mail", Mail.class);
        // execute query and get result list
        List<Mail> mails = theQuery.getResultList();
        // return the results
        return mails;
    }



    public <S extends Mail> S save(S theMail) {

        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(theMail);
        return null;
    }

    @Override
    public List<Mail> findAll(Sort sort) {
        return null;
    }

    @Override
    public List<Mail> findAllById(Iterable<Integer> iterable) {
        return null;
    }

    @Override
    public <S extends Mail> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Mail> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Mail> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Mail getOne(Integer integer) {
        return null;
    }

    @Override
    public <S extends Mail> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Mail> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public Page<Mail> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Mail> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Mail mail) {

    }

    @Override
    public void deleteAll(Iterable<? extends Mail> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Mail> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Mail> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Mail> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Mail> boolean exists(Example<S> example) {
        return false;
    }

    @Transactional
    @Override
    public void deleteById(int theId) {

        Session currentSession = entityManager.unwrap(Session.class);

        // delete object with primary key
        Query theQuery = currentSession.createQuery("delete from Mail where id=:mailId");
        theQuery.setParameter("mailId", theId);
        theQuery.executeUpdate();

    }





    @Transactional
    @Override
    public Mail findById(int theId) {

        Session currentSession = entityManager.unwrap(Session.class);
        Mail mail = currentSession.get(Mail.class, theId);

        return mail;
    }




}
