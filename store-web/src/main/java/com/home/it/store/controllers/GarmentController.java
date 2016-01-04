package com.home.it.store.controllers;

import com.home.it.jdbc.beans.Garment;
import com.home.it.jdbc.dao.GarmentManager;
import com.home.it.jdbc.dao.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GarmentController {
    @Autowired
    private GenericDao<Garment, Integer> garmentDao;

    @Autowired
    private GarmentManager garmentManager;

    public Garment getGarment(int id) {
        return garmentDao.read(id);
    }

    public List<Garment> getAllGarments() {
        return garmentManager.getAllGarments();
    }
}
