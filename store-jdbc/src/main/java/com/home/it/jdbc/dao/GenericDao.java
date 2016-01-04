package com.home.it.jdbc.dao;

import com.home.it.jdbc.beans.DataBaseBeanInterface;

import java.io.Serializable;

public interface GenericDao <T extends DataBaseBeanInterface, PK extends Serializable> {
    PK create(T objectToCreate);

    T read(PK id);

    void update(T objectToUpdate);

    void delete(T objectToDelete);
}
