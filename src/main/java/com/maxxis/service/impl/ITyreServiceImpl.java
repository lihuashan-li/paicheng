package com.maxxis.service.impl;

import com.maxxis.dao.TyreDao;
import com.maxxis.domain.Tyre;
import com.maxxis.service.ITyreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ITyreServiceImpl implements ITyreService {
    @Autowired
    private TyreDao tyreDao;
    @Override
    public List<Tyre> findByClietId(String clietId) throws Exception {
        return tyreDao.findByClietId(clietId);
    }

    @Override
    public List<Tyre> findByTyreIdA(String id) throws Exception {
        return tyreDao.findByTyreIdA(id);
    }
}
