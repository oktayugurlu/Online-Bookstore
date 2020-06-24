package com.BoomBook.Service;


import com.BoomBook.DAO.PageAdminDAO;
import com.BoomBook.Model.Customer;
import com.BoomBook.Model.PageAdmin;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PageAdminDAOImp implements PageAdminDAO {

    private EntityManager entityManager;

    @Autowired
    public PageAdminDAOImp(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }

    @Override
    public List<PageAdmin> findAll() {
        return null;
    }

    /*
        @Transactional
        @Override
        public List<PageAdmin> findAll() {
            // get the current hibernate session
            Session currentSession = entityManager.unwrap(Session.class);

            Query<PageAdmin> theQuery = currentSession.createQuery("from page_admin", PageAdmin.class);

            List<PageAdmin> pageAdminList = theQuery.getResultList();

            return pageAdminList;
        }
    */
    @Override
    public PageAdmin findById(int theID) {
        return null;
    }

/*
    @Transactional
    @Override
    public PageAdmin findById(int theID) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<PageAdmin> theQuery = currentSession.createQuery("from page_admin", PageAdmin.class);

        PageAdmin pageAdmin = currentSession.get(PageAdmin.class, theID);

        return pageAdmin;
    }

 */

    @Transactional
    @Override
    public void save(PageAdmin pageAdmin) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(pageAdmin);
    }

    @Transactional
    @Override
    public void deleteById(int theID) {

        Session currentSession = entityManager.unwrap(Session.class);

        // delete object with primary key
        Query theQuery = currentSession.createQuery(  "delete from page_admin where id=:pageAdminId");

        theQuery.setParameter("pageAdminId", theID);

        theQuery.executeUpdate();

    }

    @Override
    public List<PageAdmin> findByEmail(String email) {


        Session currentSession = entityManager.unwrap(Session.class);
        Query<PageAdmin> theQuery = currentSession.createQuery("from PageAdmin where email =:emailparam ", PageAdmin.class);
        theQuery.setParameter("emailparam", email);
        List<PageAdmin> pageAdmins = theQuery.getResultList();
        return pageAdmins;

    }


}
