package com.maxxis.service.impl;

import com.maxxis.dao.WorkTimeBCDao;
import com.maxxis.dao.WorkTimeHSUDao;
import com.maxxis.domain.WorkTimeBC;
import com.maxxis.service.IWorkTimeBCServer;
import com.maxxis.service.IWorkTimeHSUServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IWorkTimeBCServerImpl implements IWorkTimeBCServer {
    @Autowired
    private WorkTimeBCDao workTimeBCDao;
//BC实验室添加工时的方法
    @Override
    public void setBC(long gotowork, long getoffwork) throws Exception {
        workTimeBCDao.setBC(gotowork,getoffwork);

    }

//   BC删除工时的方法
    @Override
    public void deleteWorkTimeIdBC(int id) throws Exception {
        workTimeBCDao.deleteWorkTimeIdBC(id);
    }

    //   BC修改工时方法
    @Override
    public void UpdateWorkTimeBC(int id,long getoffwork) throws Exception {
        workTimeBCDao.UpdateWorkTimeBC(id,getoffwork);
    }

    //        添加工时之前先去查询
    @Override
    public List<WorkTimeBC> findBC() throws Exception {
        return workTimeBCDao.findBC();
    }

}
