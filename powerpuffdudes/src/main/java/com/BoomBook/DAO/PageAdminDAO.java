package com.BoomBook.DAO;

import com.BoomBook.Model.Customer;
import com.BoomBook.Model.PageAdmin;

import java.util.List;

public interface PageAdminDAO {

    public List<PageAdmin> findAll();

    public PageAdmin findById(int theID);

    public void save(PageAdmin pageAdmin);

    public void deleteById(int theID);

    public List<PageAdmin> findByEmail(String email);
}
