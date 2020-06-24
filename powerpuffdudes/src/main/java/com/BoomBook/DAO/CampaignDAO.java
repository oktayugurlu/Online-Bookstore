package com.BoomBook.DAO;

import com.BoomBook.Model.Book;
import com.BoomBook.Model.Campaign;

import java.sql.SQLException;

import java.util.ArrayList;

import java.util.List;

public interface CampaignDAO {

    public List<Campaign> findAll();

    public Campaign findById(int theId);

    public void save(Campaign theCampaign);

    public void deleteById(int theId);

    public List<Campaign> findBooksCampaign(int theBookId);

    public List findNewArrivals();

    /*
    public ArrayList<Integer> findBestSellers() throws SQLException;

    public ArrayList<Integer> findRecommendedBooksPersonal(int userID) throws SQLException;

    public List  findRecommendedBookObjects(int subcategoryId);
     */

}
