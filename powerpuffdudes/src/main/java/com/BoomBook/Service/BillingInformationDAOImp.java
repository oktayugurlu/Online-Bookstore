package com.BoomBook.Service;

import com.BoomBook.DAO.BillingInformationDAO;
import com.BoomBook.Model.BillingInformation;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;


@Repository
public class BillingInformationDAOImp implements BillingInformationDAO {

    private EntityManager entityManager;

    @Autowired
    public BillingInformationDAOImp(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }

    @Override
    public List<BillingInformation> findAll() {
        return null;
    }

    @Override
    public List<BillingInformation> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<BillingInformation> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<BillingInformation> findAllById(Iterable<Integer> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(BillingInformation billingInformation) {

    }

    @Override
    public void deleteAll(Iterable<? extends BillingInformation> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends BillingInformation> S save(S s) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(s);
        return null;
    }

    @Override
    public <S extends BillingInformation> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<BillingInformation> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends BillingInformation> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<BillingInformation> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public BillingInformation getOne(Integer integer) {
        return null;
    }

    @Override
    public <S extends BillingInformation> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends BillingInformation> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends BillingInformation> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends BillingInformation> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends BillingInformation> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends BillingInformation> boolean exists(Example<S> example) {
        return false;
    }
}
