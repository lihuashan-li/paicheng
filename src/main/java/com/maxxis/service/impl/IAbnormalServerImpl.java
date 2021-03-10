package com.maxxis.service.impl;

import com.maxxis.dao.AbnormalDao;
import com.maxxis.domain.Abnormal;
import com.maxxis.service.IAbnormalServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IAbnormalServerImpl implements IAbnormalServer {

    @Autowired
    private AbnormalDao abnormalDao;
    //暂停原因保存
    @Override
    public void saveAbnormal(String remarks, String id) throws  Exception{
       abnormalDao.saveAbnormal(remarks,id);
    }

    //      更改背景颜色
    @Override
    public void updateTestRed(String idd) throws Exception {
        abnormalDao.updateTestRed(idd);
    }


    //   出错信息方法
    @Override
    public Abnormal remarks(String id) throws Exception {
        return abnormalDao.remarks(id);
    }


    //      暂停时隐藏完成按钮
    @Override
    public void spbhA(String id) throws Exception {
        abnormalDao.spbhA(id);
    }
}
