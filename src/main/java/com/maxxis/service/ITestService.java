package com.maxxis.service;


import com.maxxis.domain.*;

import java.util.Date;
import java.util.List;

public interface ITestService {


    //       BC实验室
    public List<Test> findTestByTestItemBC(int page, int size) throws Exception;

    //        查询BC已排现在一共的已排记录
    int findStateCountBC()throws Exception;
    //        查询BC待排现在一共的记录
    int findCountDaiBC()throws  Exception;

    //  BC 把已经过的时间的past变成1
    void updatePastBC(long result1a)throws Exception;
    //      BC查询出没有过去的工时
    List<WorkTimeBC> findByRcworktimeBC()throws Exception;

    //       BC时间搜索按钮
    List<WorkTimeBC> findWorkTimeSouSuoBC(long gotoworkLingChen, long gotoworkWanShang);

    //BC实验室已排数据导出
    List<Test> ShuJuDaoChuBC()throws Exception;

    //            BC查询工时
    List<WorkTimeBC> findworktimeBC()throws Exception;

    // 获取BC实验室目前有没有已经排序的 如果已经排了2条数据  那么一键排程的时候number就从3开始
    int findByTestnumberBC()throws Exception;

    //BC查询出目前有没有已经排好的计划
    int findNumberBC()throws Exception;

    //BC一键排程
    void updateBCExpectedDate(String id, String result1a, int numberBC)throws Exception;

    //        查出BC已排最后一条数据
    Test findBycountBC(int count)throws Exception;

    //  查询BC已排并且没有再做的(按照number顺序)
    List<Test> findTestByStateNoZuoBC()throws Exception;

    //    BC排程刷新
    void updateBCExpectedDateUP(Integer numberA, String result1a)throws Exception;

    //       BC去查询数据库有几个已经开始不需要进行循环的
    int findByspbhOneBC()throws Exception;

    //        去数据库把没有循环到的已经排好的数据变成待排(BC)
    void updateBCDaiPai(int number)throws Exception;

    //    BC实验室完成查询（按时间查询）
    List<Test> findByendTimeCompleteBC(String start, String end)throws Exception;

    //  BC实验室取消查询（按时间查询）
    List<Test> findByCancellationTimeCancelBC(String startQuXiao, String endQuXiao)throws Exception;

    //        BC开始的时候把错误信息删除(先查id)
    String findTestIdBC(int number)throws Exception;

    //      BC开始的时候把颜色改成0
    void updateredBC(String id)throws Exception;

    //      BC 改变隐藏按钮number
    void spbhNumberBC(int number)throws Exception;

    //   BC查询已排 并且是查询还没有开始的已排  就是当前按钮number的后面的已经排的
    List<Test> findByNoStateNumberBC(int number)throws Exception;

    //        BC把数据库里面的操作更改数据
    void updateCaoZuoBC(int spbhOne)throws Exception;

    //    BC实验室调整
    void TestNumberBC(int number, int numberS, String id)throws Exception;
    void TestNumberDownBC(int number, int numberS, String id)throws Exception;

    //BC实验室已排取消
    public void updateCancelBC(String id, int number,String result1a) throws Exception;

    //   BC实验室取消开始
    void quxiaokaishiBC()throws Exception;

    //    BC实验室已排批量操作
    void PiLiangOperateDownBC(int numberS, int nubmerOld, String idID);
    void PiLiangOperateUpBC(int numberS, int nubmerOld, String idID);

    //BC取消已排  进入待排
    void JinRuDaiPaiBC(String anumber, String aid)throws Exception;

    //  查询BC已排(按照number顺序)
    List<Test> findTestByStateBC()throws Exception;

    //   BC实验室待排插位
    void TestNumberDaiChaBC(int numberB, String id)throws Exception;

    //    BC需求编号搜索(待排)
    List<TestClietTyre> findByTestRequestIdDaiPaiBC(String requestId)throws Exception;

    //      RR实验室
    public List<Test> findTestByTestItemRR(int page, int size) throws Exception;



    //    HSU实验室
    public List<Test> findTestByTestItemHSU(int page, int size) throws Exception;

    //    3D实验室
    public List<Test> findTestByTestItem3D(int page, int size) throws Exception;

    //    SP实验室
    public List<Test> findTestByTestItemSP(int page, int size) throws Exception;

    //把第一条的开始放开  可以操作
    public void updateKaiShi()throws  Exception;


    //  待排取消
    public void TestCancel(String id,String result1a) throws Exception;


    //BC实验室待排计划
    public List<TestClietTyre> findByStateBC() throws Exception;

    //BC实验室顺位排程
    public void updateStateBC(String id) throws Exception;


    //BC实验室完成
    public void updateCompleteBC(String id, int number, String result1a) throws Exception;


    //     SStiffness 实验室已排查询
    List<Test> findTestByTestItemSStiffness(int page, int size)throws  Exception;

    //     SStiffness实验室待排查询
    List<TestClietTyre> findByStateSStiffness()throws  Exception;

    //        查询出SStiffness待排一共多少条记录
    int findCountDaiSStiffness()throws  Exception;

    //        查询SStiffness已排现在一共的已排记录
    int findStateCountSStiffness()throws  Exception;

   //  查询SStiffness已排并且没有再做的(按照number顺序)
    List<Test> findTestByStateNoZuoSStiffness()throws  Exception;

    //    获取SStiffness设定的时间
    List<WorkTimeSStiffness> findworktimeSStiffness()throws  Exception;

    //SStiffness一键排程向上调整  （调整时间）
    void updateHSUExpectedDateUPSStiffness(Integer numberA, String result1a)throws  Exception;

    //       SStiffness 去查询数据库有几个已经开始不需要进行循环的
    int findByspbhOneSStiffness()throws  Exception;

    //        去数据库把没有循环到的已经排好的数据变成待排(SStiffness)
    void updateSStiffnessnumberDaiPai(int number)throws  Exception;

    //            根据时间去查  SStiffness 已经取消的
    List<Test> findByCancellationTimeSStiffness(String startQuXiao, String endQuXiao)throws  Exception;

    //    SStiffness实验室完成查询（按时间查询）
    List<Test> findByendTimeSStiffness(String start, String end)throws  Exception;


    //        SStiffness开始的时候把错误信息删除(先查id)
    String findTestIdSStiffness(int number)throws  Exception;

    //       SStiffness 改变隐藏按钮number
    void spbhNumberSStiffness(int number)throws  Exception;

    //   查询已排 并且是查询还没有开始的已排  就是当前按钮number的后面的已经排的
    List<Test> findByNoStateNumberSStiffness(int number)throws Exception;

    //    SStiffness实验室取消开始
    void quxiaokaishiStiffness()throws Exception;
    //    SStiffness实验室完成 (记录完成时间)
    void updateCompleteSStiffness(int number, String id, String result1a)throws Exception;

    //       SStiffness 把数据库里面的操作更改数据
    void updateCaoZuoSStiffness(int spbhOne)throws  Exception;

    //SStiffness实验室操作 上
    void TestNumberSStiffness(int number, int numberS, String id)throws Exception;

    //SStiffness实验室操作 下
    void TestNumberDownSStiffness(int number, int numberS, String id)throws Exception;

    //   SStiffness 已排 实验室取消
    void updateCancelSStiffness(String id, int number, String result1a)throws Exception;


    //  查询SStiffness已排(按照number顺序)
    List<Test> findTestByStateSStiffness() throws Exception;

    //   SStiffness实验室顺位排程
    void updateStateSStiffness(String id)throws  Exception;

    //   SStiffness实验室插位
    void TestNumberChaSStiffness(int numberB, String id)throws  Exception;

    //    SStiffness需求编号搜索(待排)
    List<TestClietTyre> findByTestRequestIdDaiPaiSStiffness(String requestId)throws  Exception;

    //SStiffness查询出目前有没有已经排好的计划
    int findNumberSStiffness()throws Exception;

    //SStiffness一键排程
    void updateExpectedDateSStiffness(String id, String result1a, int numberSP)throws Exception;

    //        查出SStiffness已排最后一条数据
    Test findBycountSStiffness(int count)throws Exception;

    //    SStiffness已排批量（1）
    void PiLiangOperateDownSStiffness(int numberS, int nubmerOld, String idID)throws Exception;

    //    SStiffness已排批量（2）
    void PiLiangOperateUpSStiffness(int numberS, int nubmerOld, String idID)throws Exception;

    //SStiffness取消已排  进入待排
    void JinRuDaiPaiSStiffness(String anumber, String aid)throws Exception;

    //SStiffness实验室已排数据导出
    List<Test> ShuJuDaoChuSStiffness()throws Exception;

    //RR实验室待排计划
    public List<TestClietTyre> findByStateRR() throws Exception;

    //        查询出待排一共多少条记录RR
    public   int findCountDaiRR()throws  Exception;

    //        查询RR已排现在一共的已排记录
     public   int findStateCountRR()throws  Exception;

    //    RR需求编号搜索(待排)
    List<TestClietTyre> findByTestRequestIdDaiPaiRR(String requestId)throws Exception;

    //RR实验室已排数据导出
    List<Test> ShuJuDaoChuRR()throws  Exception;

    //  RR实验室向上
    public void TestNumberRR(int number, int numberS, String id) throws Exception;

    //  RR实验室向上  批量
    void PiLiangOperateUpRR(int numberS, int nubmerOld, String idID)throws  Exception;

    //      RR向下调整批量处理
    public void PiLiangOperateDownRR(int numberS, int nubmerOld, String idID)throws  Exception;

    //   RR实验室向下
    public void TestNumberDownRR(int number, int numberS, String id) throws Exception;

    //        开始的时候把错误信息删除(先查id)RR
     public  String findTestIdRR(int number)throws Exception;

    //       RR 开始的时候把颜色改成0
    void updateredRR(String id)throws  Exception;

    //      RR  改变隐藏按钮number
    void spbhNumberRR(int number)throws  Exception;

    //  RR 查询已排 并且是查询还没有开始的已排  就是当前按钮number的后面的已经排的
    List<Test> findByNoStateNumberRR(int number)throws  Exception;

    //RR一键排程向上调整  （调整时间）
    void updateHSUExpectedDateUPRR(Integer numberA, String result1a)throws  Exception;

    //      RR  去查询数据库有几个已经开始不需要进行循环的
    int findByspbhOneRR()throws  Exception;
    //        RR把数据库里面的操作更改数据
    void updateCaoZuoRR(int spbhOne)throws  Exception;

    //    去数据库把没有循环到的已经排好的数据变成待排(RR)
    void updateSPnumberDaiPaiRR(int numberN)throws  Exception;

    //RR实验室完成
    public void updateCompleteRR(String id, int number, String result1a) throws Exception;

    //  查询RR已排并且没有开始的(按照number顺序)
    List<Test> findTestByStateNoZuoRR()throws  Exception;

    //RR实验室取消
    public void updateCancelRR(String id, int number,String result1a) throws Exception;


    //      设置排测时间   RR
    public void TiaoZhengShiJianRR(String tiaoZhengShiJianData, String idA, int numberA)throws  Exception;


    //    RR实验室取消开始
    public void quxiaokaishiRR()throws Exception;

    //RR取消已排  进入待排
    void JinRuDaiPaiRR(String anumber, String aid)throws  Exception;

    //RR查询出目前有没有已经排好的计划
    int findNumberRR()throws  Exception;

    //   RR实验室一键排程
    void updateExpectedDateRR(String id, String result1a, int numberSP)throws  Exception;

    //RR实验室顺位
    public void updateStateRR(String id) throws Exception;

    //   RR实验室插位
    public void TestNumberRRR(int numberB, String id) throws Exception;

    //    RR实验室完成查询（按时间查询）
    List<Test> findByendTimeCompleteRR(String start, String end)throws  Exception;

    //    RR实验室取消查询（按时间查询）
    List<Test> findByCancellationTimeCancelRR(String startQuXiao, String endQuXiao)throws Exception;


    //HSU实验室待排计划
    public List<TestClietTyre> findByStateHSU() throws Exception;

    //  查询出HSU待排一共多少条记录
    int findCountDaiHSU()throws Exception;

    //        查询HSU已排现在一共的记录
    int findStateCountHSU()throws  Exception;

    //        查出HSU已排最后一条数据
    Test findBycountHSU(int count)throws Exception;

    //    HSU实验室完成查询（按时间查询）
    List<Test> findByendTimeCompleteHSU(String start, String end)throws Exception;

    //   根据时间去查  HSU  已经取消的
    List<Test> findByCancellationTimeCancelHSU(String startQuXiao, String endQuXiao)throws  Exception;

    //   HSU顺位排程
    public void updateStateHSU(String id) throws Exception;

    //   HSU实验室插位
    public void TestNumberHSUU(int numberB, String id) throws Exception;

    //  HSU完成
    public void updateCompleteHSU(int number, String id,String result1a) throws Exception;

    //  HSU实验室向上
    public void TestNumberHSU(int number, int numberS, String id) throws Exception;

    //  HSU实验室向下
    public void TestNumberDownHSU(int number, int numberS, String id) throws Exception;

    //  查询HSU已排并且没有开始的(按照number顺序)
    List<Test> findTestByStateNoZuoHSU()throws Exception;

    //    HSU实验室已排批量操作
    void PiLiangOperateDownHSU(int numberS, int nubmerOld, String idID)throws Exception;
    void PiLiangOperateUpHSU(int numberS, int nubmerOld, String idID)throws Exception;

    //HSU取消已排  进入待排
    void JinRuDaiPaiHSU(String anumber, String aid)throws Exception;

    //  HSU实验室已排取消

    public void updateCancelHSU(String id, int number,String result1a) throws Exception;

    //        HSU去查询数据库有几个已经开始不需要进行循环的
    int findByspbhOneHSU()throws Exception;

    //        去数据库把没有循环到的已经排好的数据变成待排(HSU)
    void updateHSUDaiPai(int number)throws Exception;

    //        HSU开始的时候把错误信息删除(先查id)
    String findTestIdHSU(int number)throws Exception;

    //        HSU开始的时候把颜色改成0
    void updateredHSU(String id)throws Exception;

    //       HSU 改变隐藏按钮number
    void spbhNumberHSU(int number)throws Exception;

    //   查询已排 并且是查询还没有开始的已排  就是当前按钮number的后面的已经排的
    List<Test> findByNoStateNumberHSU(int number)throws Exception;

    //        HSU把数据库里面的操作更改数据
    void updateCaoZuoHSU(int spbhOne)throws Exception;

    //   HSU实验室取消开始
    void quxiaokaishiHSU()throws Exception;

    //    HSU需求编号搜索(待排)
    List<TestClietTyre> findByTestRequestIdDaiPaiHSU(String requestId)throws Exception;

    //    HSU实验室待排批量操作（插位排程）
    void TestNumberChaHSU(int numberS, String idA)throws Exception;

    //    3D实验室完成按钮
    public void updateComplete3D( String id,int number,String result1a) throws Exception;

    //  3D实验室向上
    public void TestNumber3D(int number, int numberS, String id);

    //  3D实验室向下
    public void TestNumberDown3D(int number, int numberS, String id);

    //    3D实验室已排取消
    public void updateCancel3D(String id, int number,String result1a) throws Exception;


    //    3D实验室待排
    public List<TestClietTyre> findByState3D() throws Exception;

    //        查询3D已排现在一共的已排记录
    int findStateCount3D()throws Exception;

    //        查询3D待排现在一共的记录
    int findCountDai3D()throws Exception;

    //  3D把已经过的时间的past变成1
    void updatePast3D(long result1a)throws Exception;

    //      3D查询出没有过去的工时
    List<WorkTime3D> findByRcworktime3D()throws Exception;

    //       3D时间搜索按钮
    List<WorkTime3D> findWorkTimeSouSuo3D(long gotoworkLingChen, long gotoworkWanShang);


    //    3D实验室完成查询（按时间查询）
    List<Test> findByendTimeComplete3D(String start, String end)throws Exception;

    //  3D实验室取消查询（按时间查询）
    List<Test> findByCancellationTimeCancel3D(String startQuXiao, String endQuXiao)throws Exception;

    //            3D查询工时
    List<WorkTime3D> findworktime3D()throws Exception;

    // 获取3D实验室目前有没有已经排序的 如果已经排了2条数据  那么一键排程的时候number就从3开始
    int findByTestnumber3D()throws Exception;

    //3D查询出目前有没有已经排好的计划
    int findNumber3D()throws Exception;

    //      3D实验室一键排程
    void update3DExpectedDate(String id, String result1a, int number3D)throws Exception;

    //        查出3D已排最后一条数据
    Test findBycount3D(int count)throws Exception;

    //  查询3D已排并且没有再做的(按照number顺序)
    List<Test> findTestByStateNoZuo3D()throws Exception;

    //    3D排程刷新
    void update3DExpectedDateUP(Integer numberA, String result1a)throws Exception;

    //       3D去查询数据库有几个已经开始不需要进行循环的
    int findByspbhOne3D()throws Exception;

    //        去数据库把没有循环到的已经排好的数据变成待排(3D)
    void update3DDaiPai(int number)throws Exception;

    //        3D开始的时候把错误信息删除(先查id)
    String findTestId3D(int number)throws Exception;

    //      3D开始的时候把颜色改成0
    void updatered3D(String id)throws Exception;

    //     3D改变隐藏按钮number
    void spbhNumber3D(int number)throws Exception;

    //   查询已排 并且是查询还没有开始的已排  就是当前按钮number的后面的已经排的 3D
    List<Test> findByNoStateNumber3D(int number)throws Exception;

    //        3D把数据库里面的操作更改数据
    void updateCaoZuo3D(int spbhOne)throws Exception;

    //   3D实验室取消开始
    void quxiaokaishi3D()throws Exception;

    //    3D实验室已排批量操作
    void PiLiangOperateDown3D(int numberS, int nubmerOld, String idID);
    void PiLiangOperateUp3D(int numberS, int nubmerOld, String idID);

    //3D取消已排  进入待排
    void JinRuDaiPai3D(String anumber, String aid)throws Exception;

    //   3D实验室顺位排程
    void updateState3D(String id)throws Exception;

    //  3D实验室待排插位
    void TestNumberDaiCha3D(int numberB, String id)throws Exception;

    //    3D需求编号搜索(待排)
    List<TestClietTyre> findByTestRequestIdDaiPai3D(String requestId)throws Exception;

    //3D实验室已排数据导出
    List<Test> ShuJuDaoChu3D()throws Exception;

    //    SP实验室完成
    public void updateCompleteSP(int number, String id,String result1a) throws Exception;

    //SP实验室已排数据导出
    List<Test> ShuJuDaoChuSP()throws Exception;


    //        查询SP已排现在一共的已排记录
   public int findStateCount()throws  Exception;

    //        查询出SP待排一共多少条记录
   public int findCountDai()throws  Exception;

   //   查出sp已排最后一条数据的时间
    public   Test findBycount(int count)throws  Exception;

    //   SP实验室向上
    public void TestNumberSPP(int number, int numberS, String id);

    //   SP实验室向下
    public void TestNumberDownSPP(int number, int numberS, String id);

    //       SP 向下调整批量处理
   public void PiLiangOperateDown(int numberS, int nubmerOld, String idID) throws Exception;

    //      SP  向上调整批量处理
   public void PiLiangOperateUp(int numberS, int nubmerOld, String IdID)throws  Exception;

    //取消已排  进入待排
    public void JinRuDaiPai(String anumber, String aid)throws  Exception;

    //SP已排取消
    public void updateCancelSP(String id, int number,String result1a) throws Exception;

    //    SP实验室待排计划
    public List<TestClietTyre> findByStateSP() throws Exception;

    //        先把紧急程度赋予数字  便于排序
    public   void updatePriorityDesNumber()throws  Exception;

    //    SP实验室顺位排程
    public void updateStateSP(String id) throws Exception;

    //    SP实验室插位
    public void TestNumberSP(int numberB, String id) throws Exception;


    //  HSU实验室查询法规对应时间
    public List<TimeTest> findDataStandard(String standard,String TestItem) throws Exception;

    //查询工时
    public List<WorkTimeHSU> findworktime() throws Exception;

    //        获取HSU实验室目前有没有已经排序的 如果已经排了2条数据  那么一键排程的时候number就从3开始
    public int findByTestnumber() throws Exception;


    //   一键排程
    public void updateHSUExpectedDate(String id, String result1a, int numberHSU);

    //    HSU实验室时间查询
    public List<WorkTimeHSU> findWorkTime() throws Exception;

    //        清除已排数据
    public void HSUclear() throws Exception;


    //  查询hsu已排(按照number顺序)
    public List<Test> findTestByState() throws Exception;

    //HSU向上调整  （调整时间）
    public void updateHSUExpectedDateUP(Integer numberA, String result1a) throws Exception;

    //查询出目前有没有已经排好的计划
    public int findNumber() throws Exception;

    //  把待排全部变成已排
    public void UpdateTest(int nuMBer, String lid) throws Exception;

    //HSU开始   先把开始时间改成现在
    public void UpdateBeginHSU(int idd, long time1) throws Exception;

    //     HSU   根据这个时间去查询数据库对应的id     然后在去把时间改成当前的时间
    public int findByGotoworkId(long day) throws Exception;

    //HSU实验室已排数据导出
    List<Test> ShuJuDaoChuHSU()throws Exception;

    //SP查询出目前有没有已经排好的计划
    public int findNumberSP() throws Exception;


    //  点击开始的时候把数据库里面的caozuo字段全部变成1   这样的话  前端可以控制不操作第一条已经开始的数据
    public   void updateCaoZuo(int spbhOne)throws  Exception;

    //  点击开始的时候把数据库里面的caozuo字段全部变成0   这样的话  前端可以操作第一条数据
    public   void updateCaoZuoZoo()throws  Exception;

    //    SP查询工时
    public List<WorkTimeSp> findworktimeSP() throws Exception;

    //  SP一键排程
    public void updateHSUExpectedDateSP(String id, String result1a, int numberSP);


    //    SP实验室取消开始
    void quxiaokaishiSP()throws Exception;

    //  把SP待排全部变成已排
   public void UpdateTestSP(int nuMBer, String lid)throws  Exception;

    //        去查询数据库有几个已经开始不需要进行循环的
    public   int findByspbhOne()throws  Exception;

    //        去数据库把没有循环到的已经排好的数据变成待排(SP)
  public   void updateSPnumberDaiPai(int number)throws  Exception;

    //  查询sp已排(按照number顺序)
   public List<Test> findTestByStateSP()throws  Exception;

    //  查询sp已排并且没有再做的(按照number顺序)
    public List<Test> findTestByStateNoZuoSP()throws  Exception;

    //SP一键排程向上调整  （调整时间）
  public   void updateHSUExpectedDateUPSP(Integer numberA, String result1a)throws  Exception;

    //        SP实验室 根据这个时间去查询数据库对应的id     然后在去把时间改成当前的时间
  public   int findByGotoworkIdSP(long day)throws  Exception;

    //        SP更改开始时间
   public void UpdateBeginSP(int idd, long time1)throws Exception;

    //   SP试验取消查询
  public   List<Test> TestcancelledSP() throws  Exception;

    //        开始的时候把颜色改成0
  public   void updatered(String id)throws  Exception;


    //        开始的时候把错误信息删除(先查id)
  public String findTestId(int number)throws  Exception;

    //        开始的时候把错误信息删除(再去删除数据)
  public   void deleteAbnormal(String id)throws  Exception;

    //        改变隐藏按钮number
    public  void spbhNumber(int number)throws Exception;
    //   查询SP已排 并且是查询还没有开始的已排  就是当前按钮number的后面的已经排的
    public List<Test> findByNoStateNumber(int number)throws Exception;


    //  查询RR1.7已排(按照number顺序)
    public List<Test> findTestByStateRR() throws Exception;

    //    RR1.7 获取设定的时间
    List<WorkTimeRR> findworktimeRR()throws  Exception;

    //    RR1.7 排程 （一键排程时候上面有已经排好的数据时）
   public void updateRRExpectedDateUP(Integer numberA, String result1a)throws  Exception;

    //    SP需求编号搜索(待排)
   public List<TestClietTyre> findByTestRequestIdDaiPai(String requestId)throws  Exception;

    //            根据时间去查  SP  已经完成的
  public  List<Test> findByendTimeComplete(String start,String end) throws  Exception;

    //            根据时间去查  SP  已经取消的
  public   List<Test> findByCancellationTimeCancel(String startQuXiao,String endQuXiao)throws  Exception;


    //  SP 把已经过的时间的past变成1
   public void updatePast(long result1a)throws  Exception;

    //  RR 把已经过的时间的past变成1
    public void updatePastRR(long result1a)throws  Exception;

    //  SStiffness 把已经过的时间的past变成1
    void updatePastSStiffness(long result1a)throws  Exception;

    //  HSU 把已经过的时间的past变成1
    void updatePastHSU(long result1a)throws Exception;


    //  SP 查询出没有过去的工时
  public   List<WorkTimeSp> findByRcworktimeSP()throws  Exception;

    //  RR 查询出没有过去的工时
    List<WorkTimeRR> findByRcworktimeRR()throws Exception;

    //  SStiffness查询出没有过去的工时

    List<WorkTimeSStiffness> findByRcworktimeSStiffness()throws  Exception;

    //        HSU查询出没有过去的工时
    List<WorkTimeHSU> findByRcworktimeHSU()throws Exception;


    //        SP时间搜索按钮
  public   List<WorkTimeSp> findWorkTimeSouSuo(long gotoworkLingChen,long gotoworkWanShang)throws  Exception;

    //        RR时间搜索按钮
    List<WorkTimeRR> findWorkTimeSouSuoRR(long gotoworkLingChen, long gotoworkWanShang)throws  Exception;

    //        SStiffness时间搜索按钮
    List<WorkTimeSp> findWorkTimeSStiffnessSouSuo(long gotoworkLingChen, long gotoworkWanShang)throws  Exception;

    //       HSU时间搜索按钮
    List<WorkTimeHSU> findWorkTimeHSUSouSuo(long gotoworkLingChen, long gotoworkWanShang)throws Exception;

    //          委托方  查询  未完成查询
  public   List<TestClietTyre> findByWeiTuoWei(List<String> fruitA)throws  Exception;

    //          委托方  查询  完成查询
   public  List<TestClietTyre> findByWeiTuoWan(String startWT, String endWT, List<String> fruitAA)throws Exception;

    //          委托方  查询  取消查询
    List<TestClietTyre> findByWeiTuoQu(String startWT, String endWT, List<String> fruitAA) throws Exception;


//    +=========================================================

    public void adjustOrderByUserOD(List<ODAdjustOrderObj> odAdjustOrderObjList);
    public List<ODTestInfo> getAllSortInfoOD();
    public void sortedSatrtTestOD(List<String> testInfoIds);
    public void sortedFinishTestOD(List<String> testInfoIds);
    public void updateOrderAfterOrgOrdersOD(List<String> orgOrders);
    public void unSortTestCancelOD(List<String> testInfoIds);
    public void sortedToUnSortOD(List<String> testInfoIds);
    public void unSortToSortByPurposeOrderOD(int purposeOrder,List<String> testInfoIds,int operateNum);
    public void sortedReSortByOrderOD(int purposeOrder,List<String> testInfoIds,int operateNum,List<String> orgOrders);
    public void unSortToSortByOriginalOrderOD(int maxOrder,List<String> testInfoIds);
    public int getUnSortCountOD(List<String> states);
    public List<ODTestInfo> getOnePageDataOD(List<String> states,int fromNo,int toNo);
    public int getMaxOrderOD();
    public void updateWorkTimeOD(String startTime,String endTime,String workTimeID);
    public void deleteUnFinishedWorkTimeOD(int workTimeID);
    public List<ODWorkTime> getUnFinishedWorkTimeOD(String nowTime);
    public List<ODWorkTime> isTimeConflictOD(String startDate,String endDate,String nowDate,String operateID);
    public void insertWorkTimeOD(Date startDate,Date endDate);


}
