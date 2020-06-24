package com.BoomBook.Service;

import com.BoomBook.DAO.CategoryDAO;
import com.BoomBook.DAO.InCargoDAO;
import com.BoomBook.Model.BookComment;
import com.BoomBook.Model.Category;

import java.util.List;

import javax.persistence.EntityManager;

import com.BoomBook.Model.InCargo;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class InCargoDAOImp implements InCargoDAO {

    private EntityManager entityManager;

    @Autowired
    public InCargoDAOImp(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }

    @Transactional
    @Override
    public List<InCargo> findAll() {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        Query<InCargo> theQuery = currentSession.createQuery("from InCargo", InCargo.class);

        List<InCargo> inCargoList = theQuery.getResultList();

        return inCargoList;

    }

    @Transactional
    @Override
    public InCargo findById(int theID) {

        Session currentSession = entityManager.unwrap(Session.class);

        Query<InCargo> theQuery = currentSession.createQuery("from InCargo", InCargo.class);

        InCargo inCargo = currentSession.get(InCargo.class, theID);

        return inCargo;
    }

    @Transactional
    @Override
    public void save(InCargo inCargo) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(inCargo);
    }

    @Transactional
    @Override
    public void deleteById(int theID) {

        Session currentSession = entityManager.unwrap(Session.class);

        // delete object with primary key
        Query theQuery = currentSession.createQuery(  "delete from InCargo where id=:in_cargo_id");

        theQuery.setParameter("in_cargo_id", theID);

        theQuery.executeUpdate();

    }
}
