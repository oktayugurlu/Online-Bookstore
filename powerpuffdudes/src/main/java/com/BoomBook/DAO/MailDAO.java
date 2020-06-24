package com.BoomBook.DAO;

import com.BoomBook.Model.Mail;
import java.util.Date;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MailDAO extends JpaRepository<Mail,Integer>{

    public List<Mail> findAll();

    public Mail findById(int theId);


    public void deleteById(int theId);
}
