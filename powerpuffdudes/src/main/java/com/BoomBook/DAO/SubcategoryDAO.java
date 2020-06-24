package com.BoomBook.DAO;

import com.BoomBook.Model.Category;
import com.BoomBook.Model.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubcategoryDAO extends JpaRepository<Subcategory,Integer> {
    public List<Subcategory> findAll();

    public Subcategory findById(int theId);

    //public void save(Subcategory theSubcategory);

    //public void deleteById(int theId);

    public List<Subcategory> findSubcategoriesByCategoryId(int theId);
}
