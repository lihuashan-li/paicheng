package com.maxxis.service.impl;

import com.maxxis.dao.WorkTimeRRDao;
import com.maxxis.dao.WorkTimeSpDao;
import com.maxxis.domain.WorkTimeRR;
import com.maxxis.service.IWorkTimeRRServer;
import com.maxxis.service.IWorkTimeSpServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IWorkTimeRRServerImpl implements IWorkTimeRRServer {
    @Autowired
    private  WorkTimeRRDao workTimeRRDao;

    //    RR添加工时的方法
    @Override
    public void setRR(long gotowork, long getoffwork) throws Exception {
        workTimeRRDao.setRR(gotowork,getoffwork);
    }

    //    SP删除工时的方法
    @Override
    public void deleteWorkTimeIdRR(int id) throws Exception {
        workTimeRRDao.deleteWorkTimeIdRR(id);
    }

    //    RR修改工时方法
    @Override
    public void UpdateWorkTimeRR(int id, long getoffwork) throws Exception {
        workTimeRRDao.UpdateWorkTimeRR(id,getoffwork);
    }

    //        添加工时之前先去查询
    @Override
    public List<WorkTimeRR> findRR() throws Exception {
        return workTimeRRDao.findRR();
    }


}
