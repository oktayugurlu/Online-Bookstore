package com.BoomBook.DAO;


import com.BoomBook.Model.Book;
import com.BoomBook.Model.BookComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookDAO  extends JpaRepository<Book,Integer> {
    // FIND ALL it is addded to sort functionality in search book page
    public List<Book> findAll();

    public Book findById(int theId);

    public void deleteById(int theId);

    public List<Book> findByisbn(String isbn);

    // FIND BY TITLE it is addded to sort functionality in search book page
    @Query("select s from Book s where s.title like %?1% OR s.authorName like %?1% OR s.publisherName like %?1% OR " +
            "s.subject like %?1% or s.subcategory.title like %?1% or s.subcategory.category.title like %?1% " +
            "or s.year = ?2 or s.priceWithCampaign = ?3")
    Page<Book> findByTitle(String title, int year, float number, Pageable pageable);


    // FIND BY SUBCATEGORY it is addded to sort functionality in search book page
    @Query("select s from Book s where s.subcategory.id = ?1")
    Page<Book> findBySubcategory(int id, Pageable pageable);

    //FIND BY CATEGORY
    @Query("select s from Book s, Subcategory b where b.id = s.subcategory.id and b.category.id = ?1")
    Page<Book> findByCategory(int id, Pageable pageable);

    //FIND BY CATEGORY
    @Query("select distinct s from Book s, Campaign c where s.campaign.id = c.book.id")
    Page<Book> findCampaign(Pageable pageable);

    //Rated Books
    @Query("select avg(b.rate) from Book s, BookComment b where s.id = ?1 and b.book.id = s.id and b.rate <>0")
    Float bookRate(int bookId);
}
