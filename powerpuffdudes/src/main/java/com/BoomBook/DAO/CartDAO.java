package com.BoomBook.DAO;


import com.BoomBook.Model.Cart;
import com.BoomBook.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartDAO extends JpaRepository<Cart,Integer> {
    public List<Cart> findAll();

    public Cart findById(int theId);


    // public void save(Cart theCart);

    public void deleteById(int theId);

    @Query("select s from Cart s where s.customer.id = ?1 and s.bookId.id = ?2 and s.purchaseRequest is null")
    public Cart checkCountOfBook(int customerId, int bookId);

    @Query("select s from Cart s where s.customer.id = ?1 and s.purchaseRequest is null ")
    public List<Cart> getCartsByUser(int customerId);

    @Query("select sum(s.bookId.priceWithCampaign*s.count) from Cart s where s.customer.id = ?1 and s.purchaseRequest is null ")
    public Float getTotalCart(int customerId);

    @Query("select s from Cart s where s.customer.id = ?1 and s.purchaseRequest is null and s.bookId.count-s.count<0")
    public List<Cart> checkIsCountValid(int customerId);


    public List<Cart> findCartsByCustomer(Customer c);

    public Cart findCartByCustomer(Customer c);

}
