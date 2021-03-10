package com.maxxis.service.impl;

import com.github.pagehelper.PageHelper;
import com.maxxis.dao.TestDao;
import com.maxxis.domain.*;
import com.maxxis.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Service
@Transactional
public class ITestServiceImpl implements ITestService {

    @Autowired
    private TestDao testDao;

//    BC实验室
    @Override
    public List<Test> findTestByTestItemBC(int page,int size) throws Exception {
        PageHelper.startPage(page, size);
        return testDao.findTestByTestItemBC() ;
    }

    //        查询BC已排现在一共的已排记录
    @Override
    public int findStateCountBC() throws Exception {
        return testDao.findStateCountBC();
    }

    //        查询BC待排现在一共的记录
    @Override
    public int findCountDaiBC() throws Exception {
        return testDao.findCountDaiBC();
    }

    //  BC 把已经过的时间的past变成1
    @Override
    public void updatePastBC(long result1a) throws Exception {
        testDao.updatePastBC(result1a);
    }
    //      BC查询出没有过去的工时
    @Override
    public List<WorkTimeBC> findByRcworktimeBC() throws Exception {
        return testDao.findByRcworktimeBC();
    }

    //       BC时间搜索按钮
    @Override
    public List<WorkTimeBC> findWorkTimeSouSuoBC(long gotoworkLingChen, long gotoworkWanShang) {
        return testDao.findWorkTimeSouSuoBC(gotoworkLingChen,gotoworkWanShang);
    }

    //BC实验室已排数据导出
    @Override
    public List<Test> ShuJuDaoChuBC() throws Exception {
        return testDao.findTestByTestItemBC();
    }

    //            BC查询工时
    @Override
    public List<WorkTimeBC> findworktimeBC() throws Exception {
        return testDao.findworktimeBC();
    }

    // 获取BC实验室目前有没有已经排序的 如果已经排了2条数据  那么一键排程的时候number就从3开始
    @Override
    public int findByTestnumberBC() throws Exception {
        return testDao.findByTestnumberBC();
    }

    //BC查询出目前有没有已经排好的计划
    @Override
    public int findNumberBC() throws Exception {
        return testDao.findNumberBC();
    }

    //BC一键排程
    @Override
    public void updateBCExpectedDate(String id, String result1a, int numberBC) throws Exception {
        testDao.updateBCExpectedDate(id,result1a,numberBC);
    }

    //        查出BC已排最后一条数据
    @Override
    public Test findBycountBC(int count) throws Exception {
        return testDao.findBycountBC(count);
    }

    //  查询BC已排并且没有再做的(按照number顺序)
    @Override
    public List<Test> findTestByStateNoZuoBC() throws Exception {
        return testDao.findTestByStateNoZuoBC();
    }

    //    BC排程刷新
    @Override
    public void updateBCExpectedDateUP(Integer numberA, String result1a) throws Exception {
        testDao.updateBCExpectedDateUP(numberA,result1a);
    }

    //       BC去查询数据库有几个已经开始不需要进行循环的
    @Override
    public int findByspbhOneBC() throws Exception {
        return testDao.findByspbhOneBC();
    }

    //        去数据库把没有循环到的已经排好的数据变成待排(BC)
    @Override
    public void updateBCDaiPai(int number) throws Exception {
        testDao.updateBCDaiPai(number);
    }

    //    BC实验室完成查询（按时间查询）
    @Override
    public List<Test> findByendTimeCompleteBC(String start, String end)throws Exception {
        return testDao.findByendTimeCompleteBC(start,end);
    }

    //  BC实验室取消查询（按时间查询）
    @Override
    public List<Test> findByCancellationTimeCancelBC(String startQuXiao, String endQuXiao)throws Exception {
        return testDao.findByCancellationTimeCancelBC(startQuXiao,endQuXiao);
    }

    //        BC开始的时候把错误信息删除(先查id)
    @Override
    public String findTestIdBC(int number) throws Exception {
        return testDao.findTestIdBC(number);
    }

    //      BC开始的时候把颜色改成0
    @Override
    public void updateredBC(String id) throws Exception {
        testDao.updateredBC(id);
    }

    //      BC 改变隐藏按钮number
    @Override
    public void spbhNumberBC(int number) throws Exception {
        testDao.spbhNumberBC(number);
    }

    //   BC查询已排 并且是查询还没有开始的已排  就是当前按钮number的后面的已经排的
    @Override
    public List<Test> findByNoStateNumberBC(int number) throws Exception {
        return testDao.findByNoStateNumberBC(number);
    }

    //        BC把数据库里面的操作更改数据
    @Override
    public void updateCaoZuoBC(int spbhOne) throws Exception {
        testDao.updateCaoZuoBC(spbhOne);
    }

    //    BC实验室调整
    @Override
    public void TestNumberBC(int number, int numberS, String id) throws Exception {
        testDao.TestNumberBC(number,numberS,id);
    }
    @Override
    public void TestNumberDownBC(int number, int numberS, String id) throws Exception {
        testDao.TestNumberDownBC(number,numberS,id);
    }

    //BC实验室已排取消
    @Override
    public void updateCancelBC(String id, int number,String result1a) throws Exception {
        testDao.updateCancelBC(id,number,result1a);
    }

    //   BC实验室取消开始
    @Override
    public void quxiaokaishiBC() throws Exception {
        testDao.quxiaokaishiBC();
    }

    //    BC实验室已排批量操作
    @Override
    public void PiLiangOperateDownBC(int numberS, int nubmerOld, String idID) {
        testDao.PiLiangOperateDownBC(numberS,nubmerOld,idID);
    }

    @Override
    public void PiLiangOperateUpBC(int numberS, int nubmerOld, String idID) {
        testDao.PiLiangOperateUpBC(numberS,nubmerOld,idID);
    }

    //BC取消已排  进入待排
    @Override
    public void JinRuDaiPaiBC(String anumber, String aid) throws Exception {
        testDao.JinRuDaiPaiBC(anumber,aid);
    }

    //  查询BC已排(按照number顺序)
    @Override
    public List<Test> findTestByStateBC() throws Exception {
        return testDao.findTestByStateBC();
    }

    //   BC实验室待排插位
    @Override
    public void TestNumberDaiChaBC(int numberB, String id) throws Exception {
        testDao.TestNumberDaiChaBC(numberB,id);
    }

    //    BC需求编号搜索(待排)
    @Override
    public List<TestClietTyre> findByTestRequestIdDaiPaiBC(String requestId)throws Exception {
        return testDao.findByTestRequestIdDaiPaiBC(requestId);
    }

//    RR实验室
    @Override
    public List<Test> findTestByTestItemRR(int page, int size) throws Exception {
        PageHelper.startPage(page, size);
        return testDao.findTestByTestItemRR();
    }
//HSU实验室
    @Override
    public List<Test> findTestByTestItemHSU(int page, int size) throws Exception {
        PageHelper.startPage(page, size);
        return testDao.findTestByTestItemHSU();
    }
//3D实验室
    @Override
    public List<Test> findTestByTestItem3D(int page, int size) throws Exception {
        PageHelper.startPage(page, size);
        return testDao.findTestByTestItem3D();
    }

//    SP实验室已排
    @Override
    public List<Test> findTestByTestItemSP(int page, int size) throws Exception {
        PageHelper.startPage(page, size);
        return testDao.findTestByTestItemSP();
    }


    //把第一条的开始放开  可以操作
    @Override
    public void updateKaiShi() throws Exception {
        testDao.updateKaiShi();
    }



    //待排取消
    @Override
    public void TestCancel(String id,String result1a) throws Exception {
        testDao.TestCancel(id,result1a);
    }

    //BC实验室待排计划
    @Override
    public List<TestClietTyre> findByStateBC() throws Exception {
        return testDao.findByStateBC();
    }

    //BC实验室顺位排程
    @Override
    public void updateStateBC(String id) throws Exception {
        testDao.updateStateBC(id);
    }

//BC试验室完成
    @Override
    public void updateCompleteBC(String id,int number,String result1a) throws Exception {
        testDao.updateCompleteBC(id,number,result1a);
    }


    //  SStiffness 实验室已排查询
    @Override
    public List<Test> findTestByTestItemSStiffness(int page, int size) throws Exception {
        PageHelper.startPage(page, size);
        return testDao.findTestByTestItemSStiffness();
    }

    //     SStiffness实验室待排查询
    @Override
    public List<TestClietTyre> findByStateSStiffness() throws Exception {
        return testDao.findByStateSStiffness();
    }

    //        查询出SStiffness待排一共多少条记录
    @Override
    public int findCountDaiSStiffness() throws Exception {
        return testDao.findCountDaiSStiffness();
    }

    //        查询SStiffness已排现在一共的已排记录
    @Override
    public int findStateCountSStiffness() throws Exception {
        return testDao.findStateCountSStiffness();
    }

    //  查询SStiffness已排并且没有再做的(按照number顺序)
    @Override
    public List<Test> findTestByStateNoZuoSStiffness() throws Exception {
        return testDao.findTestByStateNoZuoSStiffness();
    }

    //    获取SStiffness设定的时间
    @Override
    public List<WorkTimeSStiffness> findworktimeSStiffness() throws Exception {
        return testDao.findworktimeSStiffness();
    }

    //SStiffness一键排程向上调整  （调整时间）
    @Override
    public void updateHSUExpectedDateUPSStiffness(Integer numberA, String result1a) throws Exception {
        testDao.updateHSUExpectedDateUPSStiffness(numberA,result1a);
    }

    //       SStiffness 去查询数据库有几个已经开始不需要进行循环的
    @Override
    public int findByspbhOneSStiffness() throws Exception {
        return testDao.findByspbhOneSStiffness();
    }

    //        去数据库把没有循环到的已经排好的数据变成待排(SStiffness)
    @Override
    public void updateSStiffnessnumberDaiPai(int number) throws Exception {
        testDao.updateSStiffnessnumberDaiPai(number);
    }

    //   根据时间去查  SStiffness 已经取消的
    @Override
    public List<Test> findByCancellationTimeSStiffness(String startQuXiao, String endQuXiao) throws Exception {
        return testDao.findByCancellationTimeSStiffness(startQuXiao,endQuXiao);
    }

    //   根据时间去查  SStiffness 已经取消的
    @Override
    public List<Test> findByendTimeSStiffness(String start, String end) throws Exception {
        return testDao.findByendTimeSStiffness(start,end);
    }

    //        SStiffness开始的时候把错误信息删除(先查id)
    @Override
    public String findTestIdSStiffness(int number) throws Exception {
        return testDao.findTestIdSStiffness(number);
    }

    //       SStiffness 改变隐藏按钮number
    @Override
    public void spbhNumberSStiffness(int number) throws Exception {
        testDao.spbhNumberSStiffness(number);
    }

    //   查询已排 并且是查询还没有开始的已排  就是当前按钮number的后面的已经排的
    @Override
    public List<Test> findByNoStateNumberSStiffness(int number) throws Exception {
        return testDao.findByNoStateNumberSStiffness(number);
    }

    //    SStiffness实验室取消开始
    @Override
    public void quxiaokaishiStiffness() throws Exception {
        testDao.quxiaokaishiStiffness();
    }

    //    SStiffness实验室完成 (记录完成时间)
    @Override
    public void updateCompleteSStiffness(int number, String id, String result1a)throws  Exception {
        testDao.updateCompleteSStiffness(number,id,result1a);
    }

    //       SStiffness 把数据库里面的操作更改数据
    @Override
    public void updateCaoZuoSStiffness(int spbhOne) throws Exception {
        testDao.updateCaoZuoSStiffness(spbhOne);
    }

    //SStiffness实验室操作 上
    @Override
    public void TestNumberSStiffness(int number, int numberS, String id) throws Exception {
        testDao.TestNumberSStiffness(number,numberS,id);
    }

    //SStiffness实验室操作 下
    @Override
    public void TestNumberDownSStiffness(int number, int numberS, String id)throws Exception {
        testDao.TestNumberDownSStiffness(number,numberS,id);
    }

    //   SStiffness 已排 实验室取消
    @Override
    public void updateCancelSStiffness(String id, int number, String result1a) throws Exception {
        testDao.updateCancelSStiffness(id,number,result1a);
    }

    //  查询SStiffness已排(按照number顺序)
    @Override
    public List<Test> findTestByStateSStiffness() throws Exception {
        return testDao.findTestByStateSStiffness();
    }

    //   SStiffness实验室顺位排程
    @Override
    public void updateStateSStiffness(String id) throws Exception {
        testDao.updateStateSStiffness(id);
    }

    //   SStiffness实验室插位
    @Override
    public void TestNumberChaSStiffness(int numberB, String id) throws Exception {
        testDao.TestNumberChaSStiffness(numberB,id);
    }

    //    SStiffness需求编号搜索(待排)
    @Override
    public List<TestClietTyre> findByTestRequestIdDaiPaiSStiffness(String requestId) throws Exception {
        return testDao.findByTestRequestIdDaiPaiSStiffness(requestId);
    }

    //SStiffness查询出目前有没有已经排好的计划
    @Override
    public int findNumberSStiffness() throws Exception {
        return testDao.findNumberSStiffness();
    }

    //SStiffness一键排程（1)
    @Override
    public void updateExpectedDateSStiffness(String id, String result1a, int numberSP) throws Exception {
        testDao.updateExpectedDateSStiffness(id,result1a,numberSP);
    }

    //        查出SStiffness已排最后一条数据
    @Override
    public Test findBycountSStiffness(int count) throws Exception {
        return testDao.findBycountSStiffness(count);
    }

    //    SStiffness已排批量（1）
    @Override
    public void PiLiangOperateDownSStiffness(int numberS, int nubmerOld, String idID) throws Exception {
        testDao.PiLiangOperateDownSStiffness(numberS,nubmerOld,idID);
    }
    //    SStiffness已排批量（2）
    @Override
    public void PiLiangOperateUpSStiffness(int numberS, int nubmerOld, String idID)throws Exception {
        testDao.PiLiangOperateUpSStiffness(numberS,nubmerOld,idID);
    }

    //SStiffness取消已排  进入待排
    @Override
    public void JinRuDaiPaiSStiffness(String anumber, String aid) throws Exception {
        testDao.JinRuDaiPaiSStiffness(anumber,aid);
    }

    //SStiffness实验室已排数据导出
    @Override
    public List<Test> ShuJuDaoChuSStiffness() throws Exception {
        return testDao.findTestByTestItemSStiffness();
    }


    //RR实验室待排计划
    @Override
    public List<TestClietTyre> findByStateRR() throws Exception {
        return testDao.findByStateRR();
    }

    //        查询出待排一共多少条记录RR
    @Override
    public int findCountDaiRR() throws Exception {
        return testDao.findCountDaiRR();
    }

    //        查询RR已排现在一共的已排记录
    @Override
    public int findStateCountRR() throws Exception {
        return testDao.findStateCountRR();
    }

    //    RR需求编号搜索(待排)
    @Override
    public List<TestClietTyre> findByTestRequestIdDaiPaiRR(String requestId) throws Exception {
        return testDao.findByTestRequestIdDaiPaiRR(requestId);
    }

    //RR实验室已排数据导出
    @Override
    public List<Test> ShuJuDaoChuRR() throws Exception {
        return testDao.findTestByTestItemRR();
    }

    //    RR实验室向上
    @Override
    public void TestNumberRR(int number, int numberS, String id) {
        testDao.TestNumberRR(number,numberS,id);
    }

    //  RR实验室向上  批量
    @Override
    public void PiLiangOperateUpRR(int numberS, int nubmerOld, String idID) throws Exception {
        testDao.PiLiangOperateUpRR(numberS,nubmerOld,idID);
    }


    //      RR向下调整批量处理
    @Override
    public void PiLiangOperateDownRR(int numberS, int nubmerOld, String idID) throws Exception {
        testDao.PiLiangOperateDownRR(numberS,nubmerOld,idID);
    }

    //    RR实验室向下
    @Override
    public void TestNumberDownRR(int number, int numberS, String id) {
      testDao.TestNumberDownRR(number,numberS,id);
    }

    //     RR   开始的时候把错误信息删除(先查id)
    @Override
    public String findTestIdRR(int number) throws Exception {
        return testDao.findTestIdRR(number);
    }

    //       RR 开始的时候把颜色改成0
    @Override
    public void updateredRR(String id) throws Exception {
        testDao.updateredRR(id);
    }
    //      RR  改变隐藏按钮number
    @Override
    public void spbhNumberRR(int number) throws Exception {
        testDao.spbhNumberRR(number);
    }
    //   查询已排 并且是查询还没有开始的已排  就是当前按钮number的后面的已经排的
    @Override
    public List<Test> findByNoStateNumberRR(int number) throws Exception {
        return testDao.findByNoStateNumberRR(number);
    }

    //RR一键排程向上调整  （调整时间）
    @Override
    public void updateHSUExpectedDateUPRR(Integer numberA, String result1a) throws Exception {
        testDao.updateHSUExpectedDateUPRR(numberA,result1a);
    }

    //      RR  去查询数据库有几个已经开始不需要进行循环的
    @Override
    public int findByspbhOneRR() throws Exception {
        return testDao.findByspbhOneRR();
    }

    //        RR把数据库里面的操作更改数据
    @Override
    public void updateCaoZuoRR(int spbhOne) throws Exception {
     testDao.updateCaoZuoRR(spbhOne);
    }

    //    去数据库把没有循环到的已经排好的数据变成待排(RR)
    @Override
    public void updateSPnumberDaiPaiRR(int numberN) throws Exception {
             testDao.updateSPnumberDaiPaiRR(numberN);
    }

    //RR实验室完成
    @Override
    public void updateCompleteRR(String id, int number, String result1a) throws Exception {
        testDao.updateCompleteRR(id,number,result1a);
    }

    //  查询RR已排并且没有开始的(按照number顺序)
    @Override
    public List<Test> findTestByStateNoZuoRR() throws Exception {
        return testDao.findTestByStateNoZuoRR();
    }


    //    RR实验室取消  已排
    @Override
    public void updateCancelRR(String id, int number,String result1a) throws Exception {
        testDao.updateCancelRR(id,number,result1a);
    }

    //      设置排测时间   RR
    @Override
    public void TiaoZhengShiJianRR(String tiaoZhengShiJianData, String idA, int numberA) throws Exception {
        testDao.TiaoZhengShiJianRR(tiaoZhengShiJianData,idA,numberA);
    }

    //    RR实验室取消开始
    @Override
    public void quxiaokaishiRR() throws Exception {
        testDao.quxiaokaishiRR();
    }

    //RR取消已排  进入待排
    @Override
    public void JinRuDaiPaiRR(String anumber, String aid) throws Exception {
        testDao.JinRuDaiPaiRR(anumber,aid);
    }

    //RR查询出目前有没有已经排好的计划
    @Override
    public int findNumberRR() throws Exception {
        return testDao.findNumberRR();
    }

    //   RR实验室一键排程
    @Override
    public void updateExpectedDateRR(String id, String result1a, int numberSP) throws Exception {
           testDao.updateExpectedDateRR(id,result1a,numberSP);
    }

    //    RR实验室顺位
    @Override
    public void updateStateRR(String id) throws Exception {
        testDao.updateStateRR(id);

    }

//    RR实验室插位
    @Override
    public void TestNumberRRR(int numberB, String id) throws Exception {
        testDao.TestNumberRRR(numberB,id);
    }

    //    RR实验室完成查询（按时间查询）
    @Override
    public List<Test> findByendTimeCompleteRR(String start, String end) throws Exception {
        return testDao.findByendTimeCompleteRR(start,end);
    }

    //    RR实验室取消查询（按时间查询）
    @Override
    public List<Test> findByCancellationTimeCancelRR(String startQuXiao, String endQuXiao) throws Exception {
        return testDao.findByCancellationTimeCancelRR(startQuXiao,endQuXiao);
    }

    //HSU实验室待排计划
    @Override
    public List<TestClietTyre> findByStateHSU() throws Exception {
        return testDao.findByStateHSU();
    }

    //  查询出HSU待排一共多少条记录
    @Override
    public int findCountDaiHSU() throws Exception {
        return testDao.findCountDaiHSU();
    }

    //        查询HSU已排现在一共的记录
    @Override
    public int findStateCountHSU() throws Exception {
        return testDao.findStateCountHSU();
    }

    //        查出HSU已排最后一条数据
    @Override
    public Test findBycountHSU(int count) throws Exception {
        return testDao.findBycountHSU(count);
    }

    //    HSU实验室完成查询（按时间查询）
    @Override
    public List<Test> findByendTimeCompleteHSU(String start, String end) throws Exception {
        return testDao.findByendTimeCompleteHSU(start,end);
    }

    //   根据时间去查  HSU  已经取消的
    @Override
    public List<Test> findByCancellationTimeCancelHSU(String startQuXiao, String endQuXiao) throws Exception {
        return testDao.findByCancellationTimeCancelHSU(startQuXiao,endQuXiao);
    }

    //   HSU顺位排程
    @Override
    public void updateStateHSU(String id) throws Exception {
        testDao.updateStateHSU(id);
    }

    //   HSU实验室插位
    @Override
    public void TestNumberHSUU(int numberB, String id) throws Exception {
        testDao.TestNumberHSUU(numberB,id);
    }

    //HSU实验室完成按钮
    @Override
    public void updateCompleteHSU(int number, String id,String result1a) throws Exception {
        testDao.updateCompleteHSU(number,id,result1a);

    }

    //  HSU实验室向上
    @Override
    public void TestNumberHSU(int number, int numberS, String id) throws Exception {
        testDao.TestNumberHSU(number,numberS,id);
    }

    //  HSU实验室向下
    @Override
    public void TestNumberDownHSU(int number, int numberS, String id) throws Exception {
        testDao.TestNumberDownHSU(number,numberS,id);
    }

    //  查询HSU已排并且没有开始的(按照number顺序)
    @Override
    public List<Test> findTestByStateNoZuoHSU() throws Exception {
        return testDao.findTestByStateNoZuoHSU();
    }

    //    HSU实验室已排批量操作
    @Override
    public void PiLiangOperateDownHSU(int numberS, int nubmerOld, String idID)throws Exception {
        testDao.PiLiangOperateDownHSU(numberS,nubmerOld,idID);
    }
    @Override
    public void PiLiangOperateUpHSU(int numberS, int nubmerOld, String idID)throws Exception {
        testDao.PiLiangOperateUpHSU(numberS,nubmerOld,idID);
    }

    //HSU取消已排  进入待排
    @Override
    public void JinRuDaiPaiHSU(String anumber, String aid) throws Exception {
        testDao.JinRuDaiPaiHSU(anumber,aid);
    }

    //  HSU实验室已排取消
    @Override
    public void updateCancelHSU(String id, int number,String result1a)throws  Exception {
        testDao.updateCancelHSU(id,number,result1a);
    }

    //        HSU去查询数据库有几个已经开始不需要进行循环的
    @Override
    public int findByspbhOneHSU() throws Exception {
        return testDao.findByspbhOneHSU();
    }

    //        去数据库把没有循环到的已经排好的数据变成待排(HSU)
    @Override
    public void updateHSUDaiPai(int number) throws Exception {
        testDao.updateHSUDaiPai(number);
    }

    //        HSU开始的时候把错误信息删除(先查id)
    @Override
    public String findTestIdHSU(int number) throws Exception {
        return testDao.findTestIdHSU(number);
    }

    //        HSU开始的时候把颜色改成0
    @Override
    public void updateredHSU(String id) throws Exception {
        testDao.updateredHSU(id);
    }

    //       HSU 改变隐藏按钮number
    @Override
    public void spbhNumberHSU(int number) throws Exception {
        testDao.spbhNumberHSU(number);
    }

    //   查询已排 并且是查询还没有开始的已排  就是当前按钮number的后面的已经排的
    @Override
    public List<Test> findByNoStateNumberHSU(int number) throws Exception {
        return testDao.findByNoStateNumberHSU(number);
    }

    //        HSU把数据库里面的操作更改数据
    @Override
    public void updateCaoZuoHSU(int spbhOne) throws Exception {
        testDao.updateCaoZuoHSU(spbhOne);
    }

    //   HSU实验室取消开始
    @Override
    public void quxiaokaishiHSU() throws Exception {
        testDao.quxiaokaishiHSU();
    }

    //    HSU需求编号搜索(待排)
    @Override
    public List<TestClietTyre> findByTestRequestIdDaiPaiHSU(String requestId)throws Exception {
        return testDao.findByTestRequestIdDaiPaiHSU(requestId);
    }

    //    HSU实验室待排批量操作（插位排程）
    @Override
    public void TestNumberChaHSU(int numberS, String idA) throws Exception {
        testDao.TestNumberChaHSU(numberS,idA);
    }


    //    3D实验室完成按钮
    @Override
    public void updateComplete3D(String id,int number, String result1a) throws Exception {
        testDao.updateComplete3D(id,number,result1a);
    }

    //  3D实验室向上
    @Override
    public void TestNumber3D(int number, int numberS, String id) {
        testDao.TestNumber3D(number,numberS,id);
    }
    //  3D实验室向下
    @Override
    public void TestNumberDown3D(int number, int numberS, String id) {
          testDao.TestNumberDown3D(number,numberS,id);
    }

    //    3D实验室已排取消
    @Override
    public void updateCancel3D(String id, int number,String result1a) throws Exception {
        testDao.updateCancel3D(id,number,result1a);
    }


    //    3D实验室待排
    @Override
    public List<TestClietTyre> findByState3D() throws Exception {
        return testDao.findByState3D();
    }

    //        查询3D已排现在一共的已排记录
    @Override
    public int findStateCount3D() throws Exception {
        return testDao.findStateCount3D();
    }

    //        查询3D待排现在一共的记录
    @Override
    public int findCountDai3D() throws Exception {
        return testDao.findCountDai3D();
    }

    //  3D把已经过的时间的past变成1
    @Override
    public void updatePast3D(long result1a) throws Exception {
        testDao.updatePast3D(result1a);
    }

    //      3D查询出没有过去的工时
    @Override
    public List<WorkTime3D> findByRcworktime3D() throws Exception {
        return testDao.findByRcworktime3D();
    }

    //       3D时间搜索按钮
    @Override
    public List<WorkTime3D> findWorkTimeSouSuo3D(long gotoworkLingChen, long gotoworkWanShang) {
        return testDao.findWorkTimeSouSuo3D(gotoworkLingChen,gotoworkWanShang);
    }

    //    3D实验室完成查询（按时间查询）
    @Override
    public List<Test> findByendTimeComplete3D(String start, String end) throws Exception {
        return testDao.findByendTimeComplete3D(start,end);
    }
    //  3D实验室取消查询（按时间查询）
    @Override
    public List<Test> findByCancellationTimeCancel3D(String startQuXiao, String endQuXiao) throws Exception {
        return testDao.findByCancellationTimeCancel3D(startQuXiao,endQuXiao);
    }

    //            3D查询工时
    @Override
    public List<WorkTime3D> findworktime3D() throws Exception {
        return testDao.findworktime3D();
    }

    // 获取3D实验室目前有没有已经排序的 如果已经排了2条数据  那么一键排程的时候number就从3开始
    @Override
    public int findByTestnumber3D() throws Exception {
        return testDao.findByTestnumber3D();
    }

    //3D查询出目前有没有已经排好的计划
    @Override
    public int findNumber3D() throws Exception {
        return testDao.findNumber3D();
    }

    //      3D实验室一键排程
    @Override
    public void update3DExpectedDate(String id, String result1a, int number3D) throws Exception {
        testDao.update3DExpectedDate(id,result1a,number3D);
    }

    //        查出3D已排最后一条数据
    @Override
    public Test findBycount3D(int count) throws Exception {
        return testDao.findBycount3D(count);
    }

    //  查询3D已排并且没有再做的(按照number顺序)
    @Override
    public List<Test> findTestByStateNoZuo3D() throws Exception {
        return testDao.findTestByStateNoZuo3D();
    }

    //    3D排程刷新
    @Override
    public void update3DExpectedDateUP(Integer numberA, String result1a) throws Exception {
        testDao.update3DExpectedDateUP(numberA,result1a);
    }

    //       3D去查询数据库有几个已经开始不需要进行循环的
    @Override
    public int findByspbhOne3D() throws Exception {
        return testDao.findByspbhOne3D();
    }

    //        去数据库把没有循环到的已经排好的数据变成待排(3D)
    @Override
    public void update3DDaiPai(int number) throws Exception {
        testDao.update3DDaiPai(number);
    }

    //        3D开始的时候把错误信息删除(先查id)
    @Override
    public String findTestId3D(int number) throws Exception {
        return testDao.findTestId3D(number);
    }

    //      3D开始的时候把颜色改成0
    @Override
    public void updatered3D(String id) throws Exception {
        testDao.updatered3D(id);
    }

    //     3D改变隐藏按钮number
    @Override
    public void spbhNumber3D(int number) throws Exception {
        testDao.spbhNumber3D(number);
    }

    //   查询已排 并且是查询还没有开始的已排  就是当前按钮number的后面的已经排的 3D
    @Override
    public List<Test> findByNoStateNumber3D(int number) throws Exception {
        return testDao.findByNoStateNumber3D(number);
    }

    //        3D把数据库里面的操作更改数据
    @Override
    public void updateCaoZuo3D(int spbhOne) throws Exception {
        testDao.updateCaoZuo3D(spbhOne);
    }

    //   3D实验室取消开始
    @Override
    public void quxiaokaishi3D() throws Exception {
        testDao.quxiaokaishi3D();
    }

    //    3D实验室已排批量操作
    @Override
    public void PiLiangOperateDown3D(int numberS, int nubmerOld, String idID) {
        testDao.PiLiangOperateDown3D(numberS,nubmerOld,idID);
    }
    @Override
    public void PiLiangOperateUp3D(int numberS, int nubmerOld, String idID) {
        testDao.PiLiangOperateUp3D(numberS,nubmerOld,idID);
    }

    //3D取消已排  进入待排
    @Override
    public void JinRuDaiPai3D(String anumber, String aid) throws Exception {
        testDao.JinRuDaiPai3D(anumber,aid);
    }

    //   3D实验室顺位排程
    @Override
    public void updateState3D(String id) throws Exception {
        testDao.updateState3D(id);
    }

    //  3D实验室待排插位
    @Override
    public void TestNumberDaiCha3D(int numberB, String id) throws Exception {
        testDao.TestNumberDaiCha3D(numberB,id);
    }

    //    3D需求编号搜索(待排)
    @Override
    public List<TestClietTyre> findByTestRequestIdDaiPai3D(String requestId) throws Exception {
        return testDao.findByTestRequestIdDaiPai3D(requestId);
    }

    //3D实验室已排数据导出
    @Override
    public List<Test> ShuJuDaoChu3D() throws Exception {
        return testDao.findTestByTestItem3D();
    }


    //    SP实验室完成
    @Transactional
    @Override
    public void updateCompleteSP(int number, String id,String result1a)throws  Exception {
        testDao.updateCompleteSP(number,id,result1a);
    }

    @Override
    public List<Test> ShuJuDaoChuSP() throws Exception {
        return testDao.findTestByTestItemSP();
    }

    //        查询SP已排现在一共的已排记录
    @Transactional
    @Override
    public int findStateCount() throws Exception {
        return testDao.findStateCount();
    }

    //        查询出SP待排一共多少条记录
    @Transactional
    @Override
    public int findCountDai() throws Exception {
        return testDao.findCountDai();
    }


    //   查出sp已排最后一条数据的时间
    @Transactional
    @Override
    public Test findBycount(int count) throws Exception {
        return testDao.findBycount(count);
    }

    //   SP实验室向上
    @Transactional
    @Override
    public void TestNumberSPP(int number, int numberS, String id) {
        testDao.TestNumberSPP(number,numberS,id);
    }
    //   SP实验室向下
    @Transactional
    @Override
    public void TestNumberDownSPP(int number, int numberS, String id) {
        testDao.TestNumberDownSPP(number,numberS,id);
    }

    //       SP 向下调整批量处理
    @Transactional
    @Override
    public void PiLiangOperateDown(int numberS, int nubmerOld, String idID)throws Exception {
        testDao.PiLiangOperateDown(numberS,nubmerOld,idID);
    }
    //      SP  向上调整批量处理
    @Transactional
    @Override
    public void PiLiangOperateUp(int numberS, int nubmerOld, String IdID) throws Exception {
        testDao.PiLiangOperateUp(numberS,nubmerOld,IdID);
    }

    //SP取消已排  进入待排
    @Transactional
    @Override
    public void JinRuDaiPai(String anumber, String aid) throws Exception {
        testDao.JinRuDaiPai(anumber,aid);
    }

    //SP已排取消
    @Transactional
    @Override
    public void updateCancelSP(String id, int number,String result1a)throws  Exception{
        testDao.updateCancelSP(id,number,result1a);
    }


    //    SP实验室待排计划
    @Transactional
    @Override
    public List<TestClietTyre> findByStateSP() throws Exception {
        return testDao.findByStateSP();
    }


    //        先把紧急程度赋予数字  便于排序
    @Transactional
    @Override
    public void updatePriorityDesNumber() throws Exception {
        testDao.updatePriorityDesNumber();
    }


    //    SP实验室顺位排程
    @Transactional
    @Override
    public void updateStateSP(String id)throws  Exception {
        testDao.updateStateSP(id);
    }

    //    SP实验室插位
    @Transactional
    @Override
    public void TestNumberSP(int numberB, String id)throws  Exception {
        testDao.TestNumberSP(numberB,id);
    }

    //  HSU实验室查询法规对应时间
    @Override
    public List<TimeTest> findDataStandard(String standard,String TestItem) throws Exception {
//        System.out.println("我是测试"+standard);
//        System.out.println("我是测试"+TestItem);
        return testDao.findDataStandard(standard,TestItem);
    }
//查询工时
    @Override
    public List<WorkTimeHSU> findworktime() throws Exception {
        return testDao.findworktime();
    }

    //   获取HSU实验室目前有没有已经排序的 如果已经排了2条数据  那么一键排程的时候number就从3开始
    @Override
    public int findByTestnumber() throws Exception {
        return testDao.findByTestnumber() ;
    }

    //   一键排程
    @Transactional
    @Override
    public void updateHSUExpectedDate(String id, String result1a, int numberHSU) {
        testDao.updateHSUExpectedDate(id,result1a,numberHSU);
    }

    //    HSU实验室时间查询
    @Transactional
    @Override
    public List<WorkTimeHSU> findWorkTime() throws Exception {
        return testDao.findWorkTime();
    }

    //        清除SHU已排数据
    @Override
    public void HSUclear() throws Exception {
        testDao.HSUclear();
    }


    //  查询hsu已排(按照number顺序)
    @Transactional
    @Override
    public List<Test> findTestByState() throws Exception {
        return testDao.findTestByState();
    }

    //HSU向上调整  （调整时间）
    @Override
    public void updateHSUExpectedDateUP(Integer numberA, String result1a) throws Exception {
        testDao.updateHSUExpectedDateUP(numberA,result1a);
    }

    //查询出目前有没有已经排好的计划
    @Override
    public int findNumber() throws Exception {
        return testDao.findNumber();
    }

    //  把待排全部变成已排
    @Override
    public void UpdateTest(int nuMBer, String lid) throws Exception {
        testDao.UpdateTest(nuMBer,lid);
    }


    //HSU开始   先把开始时间改成现在
    @Override
    public void UpdateBeginHSU( int idd,long time1) throws Exception {
        testDao.UpdateBeginHSU(idd,time1);
    }


    //        根据这个时间去查询数据库对应的id     然后在去把时间改成当前的时间
    @Override
    public int findByGotoworkId(long day) throws Exception {
        return testDao.findByGotoworkId(day);
    }

    //HSU实验室已排数据导出
    @Override
    public List<Test> ShuJuDaoChuHSU() throws Exception {
        return testDao.findTestByTestItemHSU();
    }


    //SP查询出目前有没有已经排好的计划
    @Override
    public int findNumberSP() throws Exception {
        return testDao.findNumberSP();
    }


    //  点击开始的时候把数据库里面的caozuo字段全部变成1   这样的话  前端可以控制不操作第一条已经开始的数据
    @Override
    public void updateCaoZuo(int spbhOne) throws Exception {
        testDao.updateCaoZuo(spbhOne);
    }
    //  点击开始的时候把数据库里面的caozuo字段全部变成0   这样的话  前端可以操作第一条数据
    @Override
    public void updateCaoZuoZoo() throws Exception {
        testDao.updateCaoZuoZoo();
    }


    //    SP查询工时
    @Override
    public List<WorkTimeSp> findworktimeSP() throws Exception {
        return testDao.findworktimeSP();
    }


    //  SP一键排程
    @Transactional
    @Override
    public void updateHSUExpectedDateSP(String id, String result1a, int numberSP) {
         testDao.updateHSUExpectedDateSP(id,result1a,numberSP);
    }

    //    SP实验室取消开始
    @Override
    public void quxiaokaishiSP() throws Exception {
        testDao. quxiaokaishiSP();
    }


    //  把SP待排全部变成已排
    @Override
    public void UpdateTestSP(int nuMBer, String lid) throws Exception {
        testDao.UpdateTestSP(nuMBer,lid);
    }

    //        去查询数据库有几个已经开始不需要进行循环的
    @Override
    public int findByspbhOne() throws Exception {
        return testDao.findByspbhOne();
    }

    //        去数据库把没有循环到的已经排好的数据变成待排(SP)
    @Override
    public void updateSPnumberDaiPai(int number) throws Exception {
      testDao.updateSPnumberDaiPai(number);
    }


    //  查询sp已排(按照number顺序)
    @Override
    public List<Test> findTestByStateSP() throws Exception {
        return testDao.findTestByStateSP();
    }

    //  查询sp已排并且没有再做的(按照number顺序)
    @Override
    public List<Test> findTestByStateNoZuoSP() throws Exception {
        return testDao.findTestByStateNoZuoSP();
    }


    //HSU一键排程向上调整  （调整时间）
    @Override
    public void updateHSUExpectedDateUPSP(Integer numberA, String result1a) throws Exception {
          testDao.updateHSUExpectedDateUPSP(numberA,result1a);
    }

    //        SP实验室 根据这个时间去查询数据库对应的id     然后在去把时间改成当前的时间
    @Override
    public int findByGotoworkIdSP(long day) throws Exception {
        return testDao.findByGotoworkIdSP(day);
    }

    //        SP更改开始时间
    @Override
    public void UpdateBeginSP(int idd, long time1) throws Exception {
        testDao.UpdateBeginSP(idd,time1);
    }




    //   SP试验取消查询
    @Override
    public List<Test> TestcancelledSP() throws Exception {
        return testDao.TestcancelledSP();
    }


    //        开始的时候把颜色改成0
    @Override
    public void updatered(String id) throws Exception {
        testDao.updatered(id);
    }


    //        开始的时候把错误信息删除(先查id)
    @Override
    public String findTestId(int number) throws Exception {
        return testDao.findTestId(number);
    }

    //        开始的时候把错误信息删除(再去删除数据)
    @Override
    public void deleteAbnormal(String id) throws Exception {
        testDao.deleteAbnormal(id);
    }

    //        改变隐藏按钮number
    @Override
    public void spbhNumber(int number) throws Exception {
        testDao.spbhNumber(number);
    }

    //   查询SP已排 并且是查询还没有开始的已排  就是当前按钮number的后面的已经排的
    @Override
    public List<Test> findByNoStateNumber(int number) throws Exception {
     return    testDao.findByNoStateNumber(number);
    }


    //    查询RR1.7已排
    @Override
    public List<Test> findTestByStateRR() throws Exception {
        return testDao.findTestByStateRR();
    }


    //    RR1.7 获取设定的时间
    @Override
    public List<WorkTimeRR> findworktimeRR() throws Exception {
        return testDao.findworktimeRR();
    }


    //    RR1.7 排程 （一键排程时候上面有已经排好的数据时）
    @Override
    public void updateRRExpectedDateUP(Integer numberA, String result1a) throws Exception {
        testDao.updateRRExpectedDateUP(numberA,result1a);
    }


    //    SP需求编号搜索(待排)
    @Override
    public List<TestClietTyre> findByTestRequestIdDaiPai(String requestId) throws Exception {
        return testDao.findByTestRequestIdDaiPai(requestId);
    }


    //            根据时间去查  SP  已经完成的

    @Override
    public List<Test> findByendTimeComplete(String start,String end) throws Exception {
        return testDao.findByendTimeComplete(start,end);
    }
    //            根据时间去查  SP  已经取消的
    @Override
    public List<Test> findByCancellationTimeCancel(String startQuXiao,String endQuXiao) throws Exception {
        return testDao.findByCancellationTimeCancel(startQuXiao,endQuXiao);
    }

    //  SP 把已经过的时间的past变成1
    @Override
    public void updatePast(long result1a) throws Exception {
        testDao.updatePast(result1a);
    }

    //  RR 把已经过的时间的past变成1
    @Override
    public void updatePastRR(long result1a) throws Exception {
        testDao.updatePastRR(result1a);
    }

    //  SStiffness 把已经过的时间的past变成1
    @Override
    public void updatePastSStiffness(long result1a) throws Exception {
        testDao.updatePastSStiffness(result1a);
    }

    //  HSU 把已经过的时间的past变成1
    @Override
    public void updatePastHSU(long result1a) throws Exception {
        testDao.updatePastHSU(result1a);
    }


    //  SP 查询出没有过去的工时
    @Override
    public List<WorkTimeSp> findByRcworktimeSP() throws Exception {
        return testDao.findByRcworktimeSP();
    }

    //  RR 查询出没有过去的工时
    @Override
    public List<WorkTimeRR> findByRcworktimeRR() throws Exception {
        return testDao.findByRcworktimeRR();
    }

    //  SStiffness查询出没有过去的工时
    @Override
    public List<WorkTimeSStiffness> findByRcworktimeSStiffness() throws Exception {
        return testDao.findByRcworktimeSStiffness();
    }

    //        HSU查询出没有过去的工时
    @Override
    public List<WorkTimeHSU> findByRcworktimeHSU() throws Exception {
        return testDao.findByRcworktimeHSU();
    }


    //        SP时间搜索按钮
    @Override
    public List<WorkTimeSp> findWorkTimeSouSuo(long gotoworkLingChen,long gotoworkWanShang) throws Exception {
        return testDao.findWorkTimeSouSuo(gotoworkLingChen,gotoworkWanShang);
    }

    //        RR时间搜索按钮
    @Override
    public List<WorkTimeRR> findWorkTimeSouSuoRR(long gotoworkLingChen, long gotoworkWanShang) throws Exception {
        return testDao.findWorkTimeSouSuoRR(gotoworkLingChen,gotoworkWanShang);
    }

    //        SStiffness时间搜索按钮
    @Override
    public List<WorkTimeSp> findWorkTimeSStiffnessSouSuo(long gotoworkLingChen, long gotoworkWanShang) throws Exception {
        return testDao.findWorkTimeSStiffnessSouSuo(gotoworkLingChen,gotoworkWanShang);
    }

    //       HSU时间搜索按钮
    @Override
    public List<WorkTimeHSU> findWorkTimeHSUSouSuo(long gotoworkLingChen, long gotoworkWanShang) throws Exception {
        return testDao.findWorkTimeHSUSouSuo(gotoworkLingChen,gotoworkWanShang);
    }


    //          委托方  查询  未完成查询
    @Override
    public List<TestClietTyre> findByWeiTuoWei(List<String> fruitA) throws Exception {

//         long ccc=testDao.countSql(fruitA);
//        PageHelper.startPage(page, size,false);
        return testDao.findByWeiTuoWei(fruitA);
    }

    //          委托方  查询  完成查询
    @Override
    public List<TestClietTyre> findByWeiTuoWan(String startWT, String endWT, List<String> fruitAA) throws Exception {
//        PageHelper.startPage(page, size,false);
//        long count=testDao.findByWeiTuoWanCount(startWT,endWT,fruitAA);
        return testDao.findByWeiTuoWan(startWT,endWT,fruitAA);
    }

    //          委托方  查询  取消查询
    @Override
    public List<TestClietTyre> findByWeiTuoQu(String startWT, String endWT, List<String> fruitAA)throws Exception {
//        PageHelper.startPage(page, size,false);
//        long count=testDao.findByWeiTuoQuCount(startWT,endWT,fruitAA);
        return testDao.findByWeiTuoQu(startWT,endWT,fruitAA);
    }

    /*****************************************************/
    public void adjustOrderByUserOD(List<ODAdjustOrderObj> odAdjustOrderObjList){
        testDao.adjustOrderByUserOD(odAdjustOrderObjList);
    }
    public List<ODTestInfo> getAllSortInfoOD(){
        return testDao.getAllSortInfoOD();
    }
    public void sortedSatrtTestOD(List<String> testInfoIds){
        testDao.sortedSatrtTestOD(testInfoIds);
    }
    public void sortedFinishTestOD(List<String> testInfoIds){
        testDao.sortedFinishTestOD(testInfoIds);
    }
    public void updateOrderAfterOrgOrdersOD(List<String> orgOrders){
        testDao.updateOrderAfterOrgOrdersOD(orgOrders);
    }
    public void unSortTestCancelOD(List<String> testInfoIds){
        testDao.unSortTestCancelOD(testInfoIds);
    }
    public void sortedToUnSortOD(List<String> testInfoIds){
        testDao.sortedToUnSortOD(testInfoIds);
    }
    public void unSortToSortByPurposeOrderOD(int purposeOrder,List<String> testInfoIds,int operateNum){
        testDao.unSortToSortByPurposeOrderOD(purposeOrder,testInfoIds,operateNum);
    }
    public void sortedReSortByOrderOD(int purposeOrder,List<String> testInfoIds,int operateNum,List<String> orgOrders){
        testDao.sortedReSortByOrderOD(purposeOrder,testInfoIds,operateNum,orgOrders);
    }
    public void unSortToSortByOriginalOrderOD(int maxOrder,List<String> testInfoIds){
        testDao.unSortToSortByOriginalOrderOD(maxOrder,testInfoIds);
    }
    public int getUnSortCountOD(List<String> states){
        return testDao.getUnSortCountOD(states);
    }
    public List<ODTestInfo> getOnePageDataOD(List<String> states,int fromNo, int toNo){
        return testDao.getOnePageDataOD(states,fromNo,toNo);
    }
    public int getMaxOrderOD(){
        return testDao.getMaxOrderOD();
    }
    public void updateWorkTimeOD(String startTime,String endTime,String workTimeID){
        testDao.updateWorkTimeOD(startTime,endTime,workTimeID);
    }
    public void deleteUnFinishedWorkTimeOD(int workTimeID){
        testDao.deleteUnFinishedWorkTimeOD(workTimeID);
    }
    public List<ODWorkTime> getUnFinishedWorkTimeOD(String nowTime){
        return testDao.getUnFinishedWorkTimeOD(nowTime);
    }
    public List<ODWorkTime> isTimeConflictOD(String startDate,String endDate,String nowDate,String operateID){
        return testDao.isTimeConflictOD(startDate,endDate,nowDate,operateID);
    }
    public void insertWorkTimeOD(Date startDate,Date endDate){
        testDao.insertWorkTimeOD(startDate,endDate);
    }



}

