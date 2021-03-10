package com.maxxis.service.impl;

import com.maxxis.dao.WorkTimeHSUDao;
import com.maxxis.domain.WorkTimeHSU;
import com.maxxis.service.IWorkTimeHSUServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class IWorkTimeHSUServerImpl implements IWorkTimeHSUServer {
    @Autowired
    private WorkTimeHSUDao workTimeHSUDao;
//HSU实验室添加工时的方法
    @Override
    public void setHSU(long gotowork, long getoffwork) throws Exception {
//        System.out.println(gotowork);
//        System.out.println(getoffwork);
        workTimeHSUDao.setHSU(gotowork,getoffwork);

    }

//    HSU删除工时的方法
    @Override
    public void deleteWorkTimeIdHSU(int id) throws Exception {
        workTimeHSUDao.deleteWorkTimeIdHSU(id);
    }

    //    HSU修改工时方法
    @Override
    public void UpdateWorkTimeHSU(int id,long getoffwork) throws Exception {
        workTimeHSUDao.UpdateWorkTimeHSU(id,getoffwork);
    }

    @Override
    public void savea(String gotowork, String getoffwork) throws Exception {
        workTimeHSUDao.savea(gotowork,getoffwork);
    }

    //        添加工时之前先去查询
    @Override
    public List<WorkTimeHSU> findHSU() throws Exception {
        return workTimeHSUDao.findHSU();
    }

}
