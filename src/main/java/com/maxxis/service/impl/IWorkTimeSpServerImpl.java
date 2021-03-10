package com.maxxis.service.impl;

import com.maxxis.dao.WorkTimeSpDao;
import com.maxxis.domain.WorkTimeSp;
import com.maxxis.service.IWorkTimeSpServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IWorkTimeSpServerImpl implements IWorkTimeSpServer {
    @Autowired
    private WorkTimeSpDao workTimeSpDao;

    //    SP添加工时的方法
    @Override
    public void setSP(long gotowork, long getoffwork) throws Exception {
        workTimeSpDao.setSP(gotowork,getoffwork);
    }


    //    SP删除工时的方法
    @Override
    public void deleteWorkTimeIdSP(int id) throws Exception {
        workTimeSpDao.deleteWorkTimeIdSP(id);
    }


    //    SP修改工时方法
    @Override
    public void UpdateWorkTimeSP(int id, long getoffwork) throws Exception {
        workTimeSpDao.UpdateWorkTimeSP(id,getoffwork);
    }

    //        添加工时之前先去查询
    @Override
    public List<WorkTimeSp> findSP() throws Exception {
        return workTimeSpDao.findSP();
    }
}
