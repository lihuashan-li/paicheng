package com.maxxis.service;

import com.maxxis.domain.Abnormal;

public interface IAbnormalServer {
    //暂停原因保存
  public   void saveAbnormal(String remarks, String id) throws Exception;

    //      更改背景颜色
 public    void updateTestRed(String idd)throws  Exception;

    //   出错信息方法
 public    Abnormal remarks(String id)throws  Exception;

    //      暂停时隐藏完成按钮
    public void spbhA(String id)throws  Exception;
}
