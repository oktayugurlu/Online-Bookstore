package com.BoomBook.Service;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import com.BoomBook.DAO.CampaignDAO;
import com.BoomBook.Model.Book;
import com.BoomBook.Model.Campaign;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CampaignDAOImp implements CampaignDAO {


    private EntityManager entityManager;
    @Autowired
    public DataSource dataSource;

    @Autowired
    public CampaignDAOImp(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }

    @Override
    @Transactional
    public List<Campaign> findAll() {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        // create a query
        Query<Campaign> theQuery = currentSession.createQuery("from Campaign", Campaign.class);
        // execute query and get result list
        List<Campaign> campaigns = theQuery.getResultList();
        // return the results
        return campaigns;
    }

    @Override
    @Transactional
    public List<Campaign> findBooksCampaign(int theBookId){
        Session currentSession = entityManager.unwrap(Session.class);
        // create a query
        Query<Campaign> theQuery = currentSession.createQuery("from Campaign", Campaign.class);
        // execute query and get result list
        List<Campaign> campaigns = theQuery.getResultList();
        List<Campaign> modifiedCampaigns = new ArrayList<Campaign>();
        // return the results

        for(Campaign c:campaigns){
            if(c.getBook().getId()==theBookId){
                    modifiedCampaigns.add(c);
            }
        }
        // return the subcategory
        return modifiedCampaigns;
    }

    @Override
    @Transactional
    public List<Book> findNewArrivals() {

            Session currentSession = entityManager.unwrap(Session.class);
            Query query = currentSession.createSQLQuery(
                    "select  * from book order by id desc limit 8")
                    .addEntity(Book.class);
            List books = query.list();

            return books;
    }


    /*

    @Transactional
    @Override
    public ArrayList<Integer> findRecommendedBooksPersonal(int userId) throws SQLException {

        String query = "select distinct c.book_id from cart as c , purchase_request as p,book as b " +
                "where b.id = c.book_id and c.purchase_request_id = p.id and " +
                "c.customer_id =  "+ userId + " limit 4" ;


        Statement stmt = null;
        Connection con = dataSource.getConnection();
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        ArrayList<Integer> recommendedBooks = new ArrayList<Integer>();

        while (rs.next()){
            recommendedBooks.add(rs.getInt("c.book_id"));
        }

        String rateQuery = "select distinct  book_id from book_comment where rate > 2 " +
                " and customer_id = "+userId+ " limit 4";

        stmt = con.createStatement();
        ResultSet ratedRS = stmt.executeQuery(rateQuery);
        while(ratedRS.next()){

            if(!recommendedBooks.contains( ratedRS.getInt("book_id"))){
                recommendedBooks.add(ratedRS.getInt("book_id"));
            }
        }

        String mostRatedBooks  = "select distinct book_id from book_comment where rate > 4 limit 2";
        stmt = con.createStatement();
        ResultSet mostRated = stmt.executeQuery(mostRatedBooks);
        while(mostRated.next()){

            if(!recommendedBooks.contains( mostRated.getInt("book_id"))){
                recommendedBooks.add(mostRated.getInt("book_id"));
            }
        }
        return recommendedBooks;
    }

    @Transactional
    @Override
    public List findRecommendedBookObjects(int subcategoryId) {

        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createSQLQuery(
                "select * from book where subcategory_id =  :subCategory limit 8")
                .addEntity(Book.class).setParameter("subCategory",subcategoryId);
        List books = query.list();

        return books;
    }

     */

    @Override
    @Transactional
    public Campaign findById(int theId) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        Campaign theBook = currentSession.get(Campaign.class, theId);

        return theBook;
    }

    @Override
    @Transactional
    public void save(Campaign theCampaign) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(theCampaign);
    }

    @Override
    @Transactional
    public void deleteById(int theId) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // delete object with primary key
        Query theQuery = currentSession.createQuery( "delete from Campaign where id=:campaignId");
        theQuery.setParameter("campaignId", theId);
        theQuery.executeUpdate();
    }

}
