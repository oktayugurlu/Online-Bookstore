package com.BoomBook.Service;

import com.BoomBook.DAO.SubcategoryDAO;
import com.BoomBook.Model.Category;
import com.BoomBook.Model.Subcategory;

import java.util.ArrayList;
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
public class SubcategoryDAOImp implements SubcategoryDAO {

    private EntityManager entityManager;

    @Autowired
    public SubcategoryDAOImp(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }

    @Override
    @Transactional
    public List<Subcategory> findAll() {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        // create a query
        Query<Subcategory> theQuery = currentSession.createQuery("from Subcategory", Subcategory.class);
        // execute query and get result list
        List<Subcategory> subcategories = theQuery.getResultList();
        // return the results
        return subcategories;
    }

    @Override
    public List<Subcategory> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Subcategory> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Subcategory> findAllById(Iterable<Integer> iterable) {
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
    public void delete(Subcategory subcategory) {

    }

    @Override
    public void deleteAll(Iterable<? extends Subcategory> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Subcategory> S save(S s) {
        return null;
    }

    @Override
    public <S extends Subcategory> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Subcategory> findById(Integer integer) {
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
    public <S extends Subcategory> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Subcategory> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Subcategory getOne(Integer integer) {
        return null;
    }

    @Override
    public <S extends Subcategory> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Subcategory> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Subcategory> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Subcategory> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Subcategory> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Subcategory> boolean exists(Example<S> example) {
        return false;
    }


    @Override
    @Transactional
    public Subcategory findById(int theId) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        Subcategory theSubcategory = currentSession.get(Subcategory.class, theId);

        return theSubcategory;
    }





    /*
    @Override
    @Transactional
    public void save(Subcategory theSubcategory) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(theSubcategory);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // delete object with primary key
        Query theQuery = currentSession.createQuery( "delete from Subcategory where id=:subcategoryId");
        theQuery.setParameter("subcategoryId", id);
        theQuery.executeUpdate();
    }
*/

    @Override
    public List<Subcategory> findSubcategoriesByCategoryId(int theId) {
        Session currentSession = entityManager.unwrap(Session.class);
        // create a query
        Query<Subcategory> theQuery = currentSession.createQuery("from Subcategory", Subcategory.class);
        // execute query and get result list
        List<Subcategory> subcategories = theQuery.getResultList();
        List<Subcategory> modifiedSubcategories = new ArrayList<Subcategory>();
        // return the results

        for(Subcategory s:subcategories){
            if(s.getCategory()!=null){
                if(s.getCategory().getId()==theId){
                    modifiedSubcategories.add(s);
                }
            }
        }
        // return the subcategory
        return modifiedSubcategories;
    }

}
