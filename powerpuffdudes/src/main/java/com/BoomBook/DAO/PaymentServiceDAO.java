package com.BoomBook.DAO;

import com.BoomBook.Model.PaymentService;

import java.util.List;

public interface PaymentServiceDAO {
    public List<PaymentService> findAll();

    public PaymentService findById(int id);

    public void save(PaymentService paymentService);

    public void deleteById(int id);
}
