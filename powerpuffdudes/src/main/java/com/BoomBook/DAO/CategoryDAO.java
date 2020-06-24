package com.BoomBook.DAO;


import com.BoomBook.Model.Book;
import com.BoomBook.Model.Campaign;
import com.BoomBook.Model.Category;
import com.BoomBook.Model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryDAO  extends JpaRepository<Category,Integer>{
    public List<Category> findAll();

    public Category findById(int theId);

    //public void save(Category theCategory);

    public void deleteById(int theId);

    public Page<Category> findCategoryById(int id, Pageable pageable);


}
