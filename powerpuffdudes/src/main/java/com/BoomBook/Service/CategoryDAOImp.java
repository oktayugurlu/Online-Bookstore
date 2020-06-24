package com.BoomBook.Service;

import com.BoomBook.DAO.CategoryDAO;
import com.BoomBook.Model.Category;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
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
public class CategoryDAOImp  implements CategoryDAO{

    private EntityManager entityManager;

    @Autowired
    public CategoryDAOImp(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }

    @Override
    @Transactional
    public List<Category> findAll() {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        // create a query
        Query<Category> theQuery = currentSession.createQuery("from Category", Category.class);
        // execute query and get result list
        List<Category> categories = theQuery.getResultList();
        // return the results
        return categories;
    }

    @Override
    public List<Category> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Category> findAllById(Iterable<Integer> iterable) {
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
    public void delete(Category category) {

    }

    @Override
    public void deleteAll(Iterable<? extends Category> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Category> S save(S theCategory) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(theCategory);
        return null;
    }

    @Override
    public <S extends Category> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Category> findById(Integer integer) {
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
    public <S extends Category> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Category> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Category getOne(Integer integer) {
        return null;
    }

    @Override
    public <S extends Category> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Category> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Category> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Category> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Category> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Category> boolean exists(Example<S> example) {
        return false;
    }


    @Override
    @Transactional
    public Category findById(int theId) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        Category theCategory = currentSession.get(Category.class, theId);

        return theCategory;
    }




/*
    @Override
    @Transactional
    public void save(Category theCategory) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(theCategory);
    }
*/



    @Override
    @Transactional
    public void deleteById(int theId) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // delete object with primary key
        Query theQuery = currentSession.createQuery(  "delete from Category where id=:categoryId");

        theQuery.setParameter("categoryId", theId);

        theQuery.executeUpdate();
    }

    @Override
    public Page<Category> findCategoryById(int id, Pageable pageable) {
        return null;
    }


}
