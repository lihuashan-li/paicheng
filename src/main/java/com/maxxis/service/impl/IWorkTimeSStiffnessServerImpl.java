package com.maxxis.service.impl;

import com.maxxis.dao.WorkTimeSStiffnessDao;
import com.maxxis.dao.WorkTimeSpDao;
import com.maxxis.domain.WorkTimeSStiffness;
import com.maxxis.service.IWorkTimeSStiffnessServer;
import com.maxxis.service.IWorkTimeSpServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class IWorkTimeSStiffnessServerImpl implements IWorkTimeSStiffnessServer {
    @Autowired
    private WorkTimeSStiffnessDao workTimeSStiffnessDao;

    //    SS添加工时的方法
    @Override
    public void setSStiffness(long gotowork, long getoffwork) throws Exception {
        workTimeSStiffnessDao.setSStiffness(gotowork,getoffwork);
    }


    //    SS删除工时的方法
    @Override
    public void deleteWorkTimeIdSStiffness(int id) throws Exception {
        workTimeSStiffnessDao.deleteWorkTimeIdSStiffness(id);
    }


    //    SS修改工时方法
    @Override
    public void UpdateWorkTimeSStiffness(int id, long getoffwork) throws Exception {
        workTimeSStiffnessDao.UpdateWorkTimeSStiffness(id,getoffwork);
    }

    //        添加工时之前先去查询
    @Override
    public List<WorkTimeSStiffness> findSStiffness() throws Exception {
        return workTimeSStiffnessDao.findSStiffness();
    }

}
