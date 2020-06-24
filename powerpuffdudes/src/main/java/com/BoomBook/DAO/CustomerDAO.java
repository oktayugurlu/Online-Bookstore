package com.BoomBook.DAO;

import com.BoomBook.Model.Book;
import com.BoomBook.Model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerDAO extends JpaRepository<Customer,Integer> {
    public List<Customer> findAll();

    public Customer findById(int theId);

    //public void save(Customer theCustomer);

    public void deleteById(int theId);


    //@Query(value = "select * from Customer cust, Purchase_Request purch, Cart cart, In_Cargo inCargo where cust.id = cart.customer_id and purch.cart_id = cart.id ", nativeQuery = true)
    public Page<Customer> findCustomerById(int id, Pageable pageable);

    public List<Customer> findByEmail(String email);

    // IF CUSTOMER MADE A COMMENT, THIS QUERY IS RETURN FULL.
    @Query("select s from Customer s,BookComment b where s.id=?1 and b.customer.id=?1 and b.book.id = ?2")
    Customer isCustomerMadeComment(int userId, int bookId);

    @Query("select c from Customer c where c.email LIKE %?1% or c.customerName LIKE %?1%")
    Page<Customer> findByCustomerNameOrEmail(String name, Pageable pageable);

    @Query("select s from Customer s, Cart c where s.id=?1 and c.customer.id=?1 and c.purchaseRequest.isConfirmed = 1 and c.bookId.id=?2")
    List<Customer> isCustomerPurchaseBook(int userId, int bookId);

}
