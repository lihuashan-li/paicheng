package com.maxxis.service.impl;

import com.github.pagehelper.PageHelper;
import com.maxxis.dao.UserDao;
import com.maxxis.domain.Permission;
import com.maxxis.domain.Role;
import com.maxxis.domain.Users;
import com.maxxis.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class IUserServiceImpl implements IUserService {
    @Autowired
    private UserDao userDao;
    @Override
    public Users findByUsername(String username) throws Exception {
        return userDao.findByUsername(username);
    }

    @Override
    public List<Users> findAll(int page,int size) throws Exception {
        PageHelper.startPage(page, size);
        return userDao.findAll();
    }

    @Override
    public void save(Users users)throws Exception {
        userDao.save(users);
    }

    @Override
    public List<Role> findRoleById(int id) throws Exception {
        return userDao.findRoleById(id);
    }

    @Override
    public List<Permission> findPermissionByrid(int rid) throws Exception {
        return userDao.findPermissionByrid(rid);
    }

    @Override
    public  Users findById(int id) throws Exception {
        return userDao.findById(id);
    }
//用户删除
    @Override
    public void delete(int id) throws Exception {
        userDao.delete(id);
    }
//查询
    @Override
    public List<Users>  findByUserUsername(String username) throws Exception {
        return userDao.findByUserUsername(username);
    }

//    根据id查询出来这个用户没有的角色
    @Override
    public List<Role> findUserByIdAndAllRole(int userId) {
        return userDao.findUserByIdAndAllRole(userId);
    }

    @Override
    public void addRoleToUser(int userId, int[] roleids)throws Exception {
        for (int roleids1 :roleids){
            userDao.addRoleToUser(userId,roleids1);
        }

    }


}
