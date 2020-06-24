package com.BoomBook.DAO;

import com.BoomBook.Model.BookComment;
import com.BoomBook.Model.InCargo;

import java.util.List;

public interface InCargoDAO {

    public List<InCargo> findAll();

    public InCargo findById(int theID);

    public void save(InCargo inCargo);

    public void deleteById(int theID);



}
