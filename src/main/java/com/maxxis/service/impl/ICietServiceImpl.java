package com.maxxis.service.impl;

import com.github.pagehelper.PageHelper;
import com.maxxis.dao.ClietDao;
import com.maxxis.domain.Cliet;
import com.maxxis.service.IClietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ICietServiceImpl implements IClietService {
    @Autowired
    private ClietDao clietDao;
//    @Override
//    public List<Cliet> findAll() throws Exception {
//        return clietDao.findAll();
//    }

    @Override
    public List<Cliet> findAll(int page,int size) throws Exception {
        PageHelper.startPage(page, size);
        return clietDao.findAll();
    }

    @Override
    public List<Cliet> findByClietId(String id) throws Exception {
        return clietDao.findByClietId(id);
    }

//    项目工程师搜索
    @Override
    public List<Cliet> findByclietEngineer(String engineer) throws Exception {
        return clietDao.findByclietEngineer(engineer);
    }




}
