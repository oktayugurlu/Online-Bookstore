package com.BoomBook.DAO;


import com.BoomBook.Model.Book;
import com.BoomBook.Model.CourierCompany;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourierCompanyDAO extends JpaRepository<CourierCompany,Integer>  {

    public List<CourierCompany> findAll();

    public CourierCompany findById(int theId);

    public void deleteById(int theId);

}
