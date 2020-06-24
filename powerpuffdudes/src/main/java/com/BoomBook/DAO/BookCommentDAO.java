package com.BoomBook.DAO;

import com.BoomBook.Model.BookComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookCommentDAO  extends JpaRepository<BookComment,Integer> {

    public List<BookComment> findAll();

    public BookComment findById(int theId);

    public void deleteById(int theId);

    @Query("select s from BookComment s where s.book.id = ?1")
    public List<BookComment> findByBookId(int BookID);
}
