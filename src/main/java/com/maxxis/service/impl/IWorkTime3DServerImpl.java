package com.maxxis.service.impl;

import com.maxxis.dao.WorkTime3DDao;
import com.maxxis.dao.WorkTimeBCDao;
import com.maxxis.domain.WorkTime3D;
import com.maxxis.service.IWorkTime3DServer;
import com.maxxis.service.IWorkTimeBCServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IWorkTime3DServerImpl implements IWorkTime3DServer {
    @Autowired
    private WorkTime3DDao workTime3DDao;
//BC实验室添加工时的方法
    @Override
    public void set3D(long gotowork, long getoffwork) throws Exception {
        workTime3DDao.set3D(gotowork,getoffwork);

    }

//   3D删除工时的方法
    @Override
    public void deleteWorkTimeId3D(int id) throws Exception {
        workTime3DDao.deleteWorkTimeId3D(id);
    }

    //  3D修改工时方法
    @Override
    public void UpdateWorkTime3D(int id,long getoffwork) throws Exception {
        workTime3DDao.UpdateWorkTime3D(id,getoffwork);
    }

    //        添加工时之前先去查询
    @Override
    public List<WorkTime3D> find3D() throws Exception {
        return workTime3DDao.find3D();
    }

}
