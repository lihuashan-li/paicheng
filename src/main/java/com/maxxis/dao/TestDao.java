package com.maxxis.dao;

import com.maxxis.domain.*;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface TestDao {



//BC实验室
@Select("select * from Rctest where TestItem='BC'and state='1'and complete='0'and cancel='0' and factory='CSTC' order by number ASC")
@Results({
        @Result(id = true,column ="id" ,property ="id"),
        @Result(column ="number" ,property ="number"),
        @Result(column ="TestItem" ,property ="TestItem"),
        @Result(column ="sampleID" ,property ="sampleID"),
        @Result(column ="Standard" ,property ="Standard"),
        @Result(column ="UsageRim" ,property ="UsageRim"),
        @Result(column ="pt" ,property ="pt"),
        @Result(column ="Fz" ,property ="Fz"),
        @Result(column ="Vr" ,property ="Vr"),
        @Result(column ="CA" ,property ="CA"),
        @Result(column ="SA" ,property ="SA"),
        @Result(column ="PA" ,property ="PA"),
        @Result(column ="Mileage" ,property ="Mileage"),
        @Result(column ="FRAngleDeg" ,property ="FRAngleDeg"),
        @Result(column ="FFTOrder" ,property ="FFTOrder"),
        @Result(column ="FlatSpotPt" ,property ="FlatSpotPt"),
        @Result(column ="FlatSpotFz" ,property ="FlatSpotFz"),
        @Result(column ="ExpectedDate" ,property ="ExpectedDate"),
        @Result(column ="state" ,property ="state"),
        @Result(column ="complete" ,property ="complete"),
        @Result(column ="cancel" ,property ="cancel"),
        @Result(column ="red" ,property ="red"),
        @Result(column ="spbh" ,property ="spbh"),
        @Result(column ="caozuo" ,property ="caozuo"),
        @Result(column ="kaishi" ,property ="kaishi"),
        @Result(column ="endTime" ,property ="endTime"),
        @Result(column ="cancellationTime" ,property ="cancellationTime"),
        @Result(column ="factory" ,property ="factory"),
        @Result(column = "tyreId",property = "tyre",javaType = Tyre.class,one = @One(select = "com.maxxis.dao.TyreDao.findById"))

})

    public  List<Test> findTestByTestItemBC() throws Exception;


    //        查询BC已排现在一共的已排记录
 @Select("select count(*) from Rctest where state='1'and complete='0'and cancel='0' and TestItem='BC' and factory='CSTC'")
    int findStateCountBC()throws Exception;

    //        查询BC待排现在一共的记录
    @Select("select count(*) from  Rctest t1,Rctyre t2,Rccliet t3 where t1.tyreId =t2.id and t2.clietId=t3.id and TestItem='BC' and state='0'and cancel='0' and complete='0' and factory='CSTC'")
    int findCountDaiBC()throws Exception;

    //  BC 把已经过的时间的past变成1
    @Update("update RcworktimeBC set past=1 where getoffwork<=#{result1a} and past=0")
    void updatePastBC(long result1a)throws Exception;

    //      BC查询出没有过去的工时
    @Select("select * from RcworktimeBC where past=0 order by gotowork")
    List<WorkTimeBC> findByRcworktimeBC()throws Exception;

    //            BC查询工时
    @Select("select * from RcworktimeBC order by gotowork")
    List<WorkTimeBC> findworktimeBC()throws Exception;

    // 获取BC实验室目前有没有已经排序的 如果已经排了2条数据  那么一键排程的时候number就从3开始
    @Select("SELECT COUNT(*)+1 FROM  Rctest t1,Rctyre t2,Rccliet t3 where t1.tyreId =t2.id and t2.clietId=t3.id and number IS NOT NULL and number!=0 and TestItem='BC' and factory='CSTC'")
    int findByTestnumberBC()throws Exception;

    //BC查询出目前有没有已经排好的计划
    @Select("SELECT COUNT(*) FROM Rctest t1,Rctyre t2,Rccliet t3 where t1.tyreId =t2.id and t2.clietId=t3.id and number IS NOT NULL and number!=0 and TestItem='BC' and factory='CSTC'")
    int findNumberBC()throws Exception;

    //BC一键排程
    @Update("update Rctest set number=#{numberBC} where id=#{id};update Rctest set state='1' where id=#{id};update Rctest set ExpectedDate=#{result1a} where id=#{id}")
    void updateBCExpectedDate(@Param("id") String id, @Param("result1a") String result1a, @Param("numberBC") int numberBC)throws Exception;

    //        查出BC已排最后一条数据
    @Select("select * from Rctest where number=#{count} and TestItem='BC' and factory='CSTC'")
    Test findBycountBC(int count)throws Exception;

    //  查询BC已排并且没有再做的(按照number顺序)
    @Select("select * from Rctest  where TestItem='BC'  and state=1 and cancel=0 and complete=0 and spbh=0 and red=0 and factory='CSTC' order by number")
    List<Test> findTestByStateNoZuoBC()throws Exception;

    //    BC排程刷新
    @Update("update Rctest set ExpectedDate=#{result1a} where number=#{numberA} and TestItem='BC' and factory='CSTC'")
    void updateBCExpectedDateUP(@Param("numberA") Integer numberA, @Param("result1a") String result1a)throws Exception;

    //       BC去查询数据库有几个已经开始不需要进行循环的
    @Select("select COUNT(*) FROM Rctest where TestItem='BC'  and state=1 and cancel=0 and complete=0 and (spbh=1 or red=1) and factory='CSTC'")
    int findByspbhOneBC()throws Exception;

    //        去数据库把没有循环到的已经排好的数据变成待排(BC)
    @Update("update Rctest set state=0,number=0 where number>=#{number} and TestItem='BC' and cancel=0 and complete=0 and factory='CSTC'")
    void updateBCDaiPai(int number)throws Exception;

    //    BC实验室完成查询（按时间查询）
    @Select("select * from Rctest where TestItem='BC' and factory='CSTC' and complete='1'and endTime > #{start} and endTime < #{end}  order by endTime")
    @Results({
            @Result(id = true,column ="id" ,property ="id"),
            @Result(column ="number" ,property ="number"),
            @Result(column ="TestItem" ,property ="TestItem"),
            @Result(column ="sampleID" ,property ="sampleID"),
            @Result(column ="Standard" ,property ="Standard"),
            @Result(column ="UsageRim" ,property ="UsageRim"),
            @Result(column ="pt" ,property ="pt"),
            @Result(column ="Fz" ,property ="Fz"),
            @Result(column ="Vr" ,property ="Vr"),
            @Result(column ="CA" ,property ="CA"),
            @Result(column ="SA" ,property ="SA"),
            @Result(column ="PA" ,property ="PA"),
            @Result(column ="Mileage" ,property ="Mileage"),
            @Result(column ="FRAngleDeg" ,property ="FRAngleDeg"),
            @Result(column ="FFTOrder" ,property ="FFTOrder"),
            @Result(column ="FlatSpotPt" ,property ="FlatSpotPt"),
            @Result(column ="FlatSpotFz" ,property ="FlatSpotFz"),
            @Result(column ="ExpectedDate" ,property ="ExpectedDate"),
            @Result(column ="state" ,property ="state"),
            @Result(column ="complete" ,property ="complete"),
            @Result(column ="cancel" ,property ="cancel"),
            @Result(column ="red" ,property ="red"),
            @Result(column ="spbh" ,property ="spbh"),
            @Result(column ="caozuo" ,property ="caozuo"),
            @Result(column ="kaishi" ,property ="kaishi"),
            @Result(column ="endTime" ,property ="endTime"),
            @Result(column ="cancellationTime" ,property ="cancellationTime"),
            @Result(column ="factory" ,property ="factory"),
            @Result(column = "tyreId",property = "tyre",javaType = Tyre.class,one = @One(select = "com.maxxis.dao.TyreDao.findById"))

    })
    List<Test> findByendTimeCompleteBC(@Param("start") String start, @Param("end") String end)throws Exception;

    //  BC实验室取消查询（按时间查询）
    @Select("select * from Rctest where TestItem='BC' and factory='CSTC' and cancel='1'and cancellationTime > #{startQuXiao} and cancellationTime < #{endQuXiao} order by cancellationTime")
    @Results({
            @Result(id = true,column ="id" ,property ="id"),
            @Result(column ="number" ,property ="number"),
            @Result(column ="TestItem" ,property ="TestItem"),
            @Result(column ="sampleID" ,property ="sampleID"),
            @Result(column ="Standard" ,property ="Standard"),
            @Result(column ="UsageRim" ,property ="UsageRim"),
            @Result(column ="pt" ,property ="pt"),
            @Result(column ="Fz" ,property ="Fz"),
            @Result(column ="Vr" ,property ="Vr"),
            @Result(column ="CA" ,property ="CA"),
            @Result(column ="SA" ,property ="SA"),
            @Result(column ="PA" ,property ="PA"),
            @Result(column ="Mileage" ,property ="Mileage"),
            @Result(column ="FRAngleDeg" ,property ="FRAngleDeg"),
            @Result(column ="FFTOrder" ,property ="FFTOrder"),
            @Result(column ="FlatSpotPt" ,property ="FlatSpotPt"),
            @Result(column ="FlatSpotFz" ,property ="FlatSpotFz"),
            @Result(column ="ExpectedDate" ,property ="ExpectedDate"),
            @Result(column ="state" ,property ="state"),
            @Result(column ="complete" ,property ="complete"),
            @Result(column ="cancel" ,property ="cancel"),
            @Result(column ="red" ,property ="red"),
            @Result(column ="spbh" ,property ="spbh"),
            @Result(column ="caozuo" ,property ="caozuo"),
            @Result(column ="kaishi" ,property ="kaishi"),
            @Result(column ="endTime" ,property ="endTime"),
            @Result(column ="cancellationTime" ,property ="cancellationTime"),
            @Result(column ="factory" ,property ="factory"),
            @Result(column = "tyreId",property = "tyre",javaType = Tyre.class,one = @One(select = "com.maxxis.dao.TyreDao.findById"))

    })
    List<Test> findByCancellationTimeCancelBC(@Param("startQuXiao") String startQuXiao, @Param("endQuXiao") String endQuXiao)throws Exception;

    //        BC开始的时候把错误信息删除(先查id)
    @Select("select id from Rctest where number=#{number} and TestItem='BC' and factory='CSTC'")
    String findTestIdBC(int number)throws Exception;

    //      BC开始的时候把颜色改成0
    @Update("update Rctest set red=0 where id=#{id} and TestItem='BC' and factory='CSTC'")
    void updateredBC(String id)throws Exception;

    //      BC 改变隐藏按钮number
    @Update("update Rctest set spbh=1 where number=#{number} and TestItem='BC' and factory='CSTC'")
    void spbhNumberBC(int number)throws Exception;

    //   BC查询已排 并且是查询还没有开始的已排  就是当前按钮number的后面的已经排的
    @Select("select * from  Rctest where number>=#{number} and TestItem='BC' and state=1 and cancel=0 and complete=0 and factory='CSTC' order by number")
    @Results({
            @Result(id = true,column ="id" ,property ="id"),
            @Result(column ="number" ,property ="number"),
            @Result(column ="TestItem" ,property ="TestItem"),
            @Result(column ="sampleID" ,property ="sampleID"),
            @Result(column ="Standard" ,property ="Standard"),
            @Result(column ="UsageRim" ,property ="UsageRim"),
            @Result(column ="pt" ,property ="pt"),
            @Result(column ="Fz" ,property ="Fz"),
            @Result(column ="Vr" ,property ="Vr"),
            @Result(column ="CA" ,property ="CA"),
            @Result(column ="SA" ,property ="SA"),
            @Result(column ="PA" ,property ="PA"),
            @Result(column ="Mileage" ,property ="Mileage"),
            @Result(column ="FRAngleDeg" ,property ="FRAngleDeg"),
            @Result(column ="FFTOrder" ,property ="FFTOrder"),
            @Result(column ="FlatSpotPt" ,property ="FlatSpotPt"),
            @Result(column ="FlatSpotFz" ,property ="FlatSpotFz"),
            @Result(column ="ExpectedDate" ,property ="ExpectedDate"),
            @Result(column ="state" ,property ="state"),
            @Result(column ="complete" ,property ="complete"),
            @Result(column ="cancel" ,property ="cancel"),
            @Result(column ="red" ,property ="red"),
            @Result(column ="spbh" ,property ="spbh"),
            @Result(column ="caozuo" ,property ="caozuo"),
            @Result(column ="kaishi" ,property ="kaishi"),
            @Result(column ="endTime" ,property ="endTime"),
            @Result(column ="cancellationTime" ,property ="cancellationTime"),
            @Result(column ="factory" ,property ="factory"),
            @Result(column = "tyreId",property = "tyre",javaType = Tyre.class,one = @One(select = "com.maxxis.dao.TyreDao.findById"))

    })
    List<Test> findByNoStateNumberBC(int number)throws Exception;

    //        BC把数据库里面的操作更改数据
    @Update("update Rctest set caozuo=#{spbhOne} where TestItem='BC' and factory='CSTC'")
    void updateCaoZuoBC(int spbhOne)throws Exception;

    //    BC实验室调整
    @Update("update Rctest set number=number+1 where TestItem='BC' and factory='CSTC' and number>=#{numberS} and number < #{number};update Rctest set number=#{numberS} where id=#{id}")
    void TestNumberBC(@Param("numberS") int number, @Param("number") int numberS, @Param("id") String id);

    @Update("update Rctest set number=number-1 where TestItem='BC' and factory='CSTC' and number<=#{numberS} and number > #{number};update Rctest set number=#{numberS} where id=#{id}")
    void TestNumberDownBC(@Param("numberS") int number,  @Param("number")int numberS, @Param("id")String id);

    //BC实验室已排取消
    @Update("update Rctest set cancellationTime=#{result1a} where id=#{id};update Rctest set cancel='1'where id=#{id};update Rctest set number='0'where id=#{id};update Rctest set number=number-1 where TestItem='BC' and factory='CSTC' and number>#{number}")
    public   void updateCancelBC(@Param("id") String id, @Param("number") int number,@Param("result1a") String result1a)throws  Exception;

    //   BC实验室取消开始
    @Update("update Rctest set spbh=0,caozuo=0 where TestItem='BC' and factory='CSTC' and state='1'and complete='0'and cancel='0' ")
    void quxiaokaishiBC()throws Exception;

    //    BC实验室已排批量操作
    @Update("update Rctest set number= number-1 where TestItem='BC' and factory='CSTC' and number<=#{numberS} and number >#{nubmerOld};update Rctest set number=#{numberS} where id=#{idID}")
    void PiLiangOperateDownBC(@Param("numberS") int numberS, @Param("nubmerOld") int nubmerOld, @Param("idID") String idID);
    @Update("update Rctest set number=number+1 where TestItem='BC' and factory='CSTC' and number>=#{numberS} and number < #{nubmerOld};update Rctest set number=#{numberS} where id=#{idID}")
    void PiLiangOperateUpBC(@Param("numberS")int numberS, @Param("nubmerOld")int nubmerOld,@Param("idID")String idID);

    //BC取消已排  进入待排
    @Update("update Rctest set number=0 where id=#{aid};update Rctest set state=0 where id=#{aid};update Rctest set number=number-1 where number>#{anumber} and TestItem='BC' and factory='CSTC'")
    void JinRuDaiPaiBC(@Param("anumber") String anumber, @Param("aid") String aid)throws Exception;

    //  查询BC已排(按照number顺序)
    @Select("select * from Rctest  where TestItem='BC' and factory='CSTC' and state=1 and cancel=0 and complete=0 order by number")
    List<Test> findTestByStateBC()throws Exception;

    //   BC实验室待排插位
    @Update("update Rctest set number=number+1 where number>=#{numberB} and TestItem='BC' and factory='CSTC';update Rctest set number=#{numberB} where id=#{id};update Rctest set state='1' where id=#{id}")
    void TestNumberDaiChaBC(@Param("numberB") int numberB, @Param("id") String id)throws Exception;

    //    BC需求编号搜索(待排)
    //    HSU需求编号搜索(待排)
    @Select("select number,\n" +
            "       t1.id  as idRctest,TestItem,sampleID,Standard,UsageRim,pt,Fz,Vr,CA,SA,PA,Mileage,FRangleDeg,FFTOrder,FlatSpotPt,FlatSpotFz,ExpectedDate,state,complete,cancel,red,spbh,caozuo,kaishi,endTime,cancellationTime,factory,t2.id  as idRctyre,Size,t3.id as idRccliet,RequestId,Project,Purpose,ProjectEngineer,PriorityDes,RequestTime,TimeA,TestTime,ContactExt,Remark,PriorityDesNumber,sampleDisposal\n" +
            "  from Rctest t1,Rctyre t2,Rccliet t3 where t1.tyreId =t2.id and t2.clietId=t3.id and TestItem='BC' and factory='CSTC' and state='0'and cancel='0' and complete='0' and RequestId like '%${value}%'")
    List<TestClietTyre> findByTestRequestIdDaiPaiBC(String requestId)throws Exception;



//RR实验室
@Select("select * from Rctest  where (TestItem='RR' or TestItem='RPK' or TestItem='CDRR') and state='1'and complete='0'and cancel='0' and factory='CSTC' order by number ASC")
@Results({
        @Result(id = true,column ="id" ,property ="id"),
        @Result(column ="number" ,property ="number"),
        @Result(column ="TestItem" ,property ="TestItem"),
        @Result(column ="sampleID" ,property ="sampleID"),
        @Result(column ="Standard" ,property ="Standard"),
        @Result(column ="UsageRim" ,property ="UsageRim"),
        @Result(column ="pt" ,property ="pt"),
        @Result(column ="Fz" ,property ="Fz"),
        @Result(column ="Vr" ,property ="Vr"),
        @Result(column ="CA" ,property ="CA"),
        @Result(column ="SA" ,property ="SA"),
        @Result(column ="PA" ,property ="PA"),
        @Result(column ="Mileage" ,property ="Mileage"),
        @Result(column ="FRAngleDeg" ,property ="FRAngleDeg"),
        @Result(column ="FFTOrder" ,property ="FFTOrder"),
        @Result(column ="FlatSpotPt" ,property ="FlatSpotPt"),
        @Result(column ="FlatSpotFz" ,property ="FlatSpotFz"),
        @Result(column ="ExpectedDate" ,property ="ExpectedDate"),
        @Result(column ="state" ,property ="state"),
        @Result(column ="complete" ,property ="complete"),
        @Result(column ="cancel" ,property ="cancel"),
        @Result(column ="red" ,property ="red"),
        @Result(column ="spbh" ,property ="spbh"),
        @Result(column ="caozuo" ,property ="caozuo"),
        @Result(column ="kaishi" ,property ="kaishi"),
        @Result(column ="endTime" ,property ="endTime"),
        @Result(column ="cancellationTime" ,property ="cancellationTime"),
        @Result(column ="factory" ,property ="factory"),
        @Result(column = "tyreId",property = "tyre",javaType = Tyre.class,one = @One(select = "com.maxxis.dao.TyreDao.findById"))

})
    public List<Test> findTestByTestItemRR() throws Exception;
//HSU实验室
    @Select("select * from Rctest  where (TestItem='HSU' or TestItem='LSU' or TestItem='Flatspot') and state='1'and complete='0'and cancel='0' and factory='CSTC' order by number ASC")
    @Results({
            @Result(id = true,column ="id" ,property ="id"),
            @Result(column ="number" ,property ="number"),
            @Result(column ="TestItem" ,property ="TestItem"),
            @Result(column ="sampleID" ,property ="sampleID"),
            @Result(column ="Standard" ,property ="Standard"),
            @Result(column ="UsageRim" ,property ="UsageRim"),
            @Result(column ="pt" ,property ="pt"),
            @Result(column ="Fz" ,property ="Fz"),
            @Result(column ="Vr" ,property ="Vr"),
            @Result(column ="CA" ,property ="CA"),
            @Result(column ="SA" ,property ="SA"),
            @Result(column ="PA" ,property ="PA"),
            @Result(column ="Mileage" ,property ="Mileage"),
            @Result(column ="FRAngleDeg" ,property ="FRAngleDeg"),
            @Result(column ="FFTOrder" ,property ="FFTOrder"),
            @Result(column ="FlatSpotPt" ,property ="FlatSpotPt"),
            @Result(column ="FlatSpotFz" ,property ="FlatSpotFz"),
            @Result(column ="ExpectedDate" ,property ="ExpectedDate"),
            @Result(column ="state" ,property ="state"),
            @Result(column ="complete" ,property ="complete"),
            @Result(column ="cancel" ,property ="cancel"),
            @Result(column ="red" ,property ="red"),
            @Result(column ="spbh" ,property ="spbh"),
            @Result(column ="caozuo" ,property ="caozuo"),
            @Result(column ="kaishi" ,property ="kaishi"),
            @Result(column ="endTime" ,property ="endTime"),
            @Result(column ="cancellationTime" ,property ="cancellationTime"),
            @Result(column ="factory" ,property ="factory"),
            @Result(column = "tyreId",property = "tyre",javaType = Tyre.class,one = @One(select = "com.maxxis.dao.TyreDao.findById"))

    })
    public List<Test> findTestByTestItemHSU() throws Exception;
//3D实验室
    @Select("select * from  Rctest where (TestItem='Profile Scan(Unload)' or TestItem='Profile Scan(Rim)' or TestItem='Profile Scan(NonRim)'  or TestItem='Profile Scan(Loaded)' or TestItem='Profile Scan(Wear)') and state='1'and complete='0'and cancel='0' and factory='CSTC' order by number ASC")
    @Results({
            @Result(id = true,column ="id" ,property ="id"),
            @Result(column ="number" ,property ="number"),
            @Result(column ="TestItem" ,property ="TestItem"),
            @Result(column ="sampleID" ,property ="sampleID"),
            @Result(column ="Standard" ,property ="Standard"),
            @Result(column ="UsageRim" ,property ="UsageRim"),
            @Result(column ="pt" ,property ="pt"),
            @Result(column ="Fz" ,property ="Fz"),
            @Result(column ="Vr" ,property ="Vr"),
            @Result(column ="CA" ,property ="CA"),
            @Result(column ="SA" ,property ="SA"),
            @Result(column ="PA" ,property ="PA"),
            @Result(column ="Mileage" ,property ="Mileage"),
            @Result(column ="FRAngleDeg" ,property ="FRAngleDeg"),
            @Result(column ="FFTOrder" ,property ="FFTOrder"),
            @Result(column ="FlatSpotPt" ,property ="FlatSpotPt"),
            @Result(column ="FlatSpotFz" ,property ="FlatSpotFz"),
            @Result(column ="ExpectedDate" ,property ="ExpectedDate"),
            @Result(column ="state" ,property ="state"),
            @Result(column ="complete" ,property ="complete"),
            @Result(column ="cancel" ,property ="cancel"),
            @Result(column ="red" ,property ="red"),
            @Result(column ="spbh" ,property ="spbh"),
            @Result(column ="caozuo" ,property ="caozuo"),
            @Result(column ="kaishi" ,property ="kaishi"),
            @Result(column ="endTime" ,property ="endTime"),
            @Result(column ="cancellationTime" ,property ="cancellationTime"),
            @Result(column ="factory" ,property ="factory"),
            @Result(column = "tyreId",property = "tyre",javaType = Tyre.class,one = @One(select = "com.maxxis.dao.TyreDao.findById"))

    })
   public  List<Test> findTestByTestItem3D() throws  Exception;
//SP实验室已排
    @Select("select * from Rctest where TestItem='Footprint'and state='1'and complete='0'and cancel='0' and factory='CSTC' order by number ASC")
    @Results({
            @Result(id = true,column ="id" ,property ="id"),
            @Result(column ="number" ,property ="number"),
            @Result(column ="TestItem" ,property ="TestItem"),
            @Result(column ="sampleID" ,property ="sampleID"),
            @Result(column ="Standard" ,property ="Standard"),
            @Result(column ="UsageRim" ,property ="UsageRim"),
            @Result(column ="pt" ,property ="pt"),
            @Result(column ="Fz" ,property ="Fz"),
            @Result(column ="Vr" ,property ="Vr"),
            @Result(column ="CA" ,property ="CA"),
            @Result(column ="SA" ,property ="SA"),
            @Result(column ="PA" ,property ="PA"),
            @Result(column ="Mileage" ,property ="Mileage"),
            @Result(column ="FRAngleDeg" ,property ="FRAngleDeg"),
            @Result(column ="FFTOrder" ,property ="FFTOrder"),
            @Result(column ="FlatSpotPt" ,property ="FlatSpotPt"),
            @Result(column ="FlatSpotFz" ,property ="FlatSpotFz"),
            @Result(column ="ExpectedDate" ,property ="ExpectedDate"),
            @Result(column ="state" ,property ="state"),
            @Result(column ="complete" ,property ="complete"),
            @Result(column ="cancel" ,property ="cancel"),
            @Result(column ="red" ,property ="red"),
            @Result(column ="spbh" ,property ="spbh"),
            @Result(column ="caozuo" ,property ="caozuo"),
            @Result(column ="kaishi" ,property ="kaishi"),
            @Result(column ="endTime" ,property ="endTime"),
            @Result(column ="cancellationTime" ,property ="cancellationTime"),
            @Result(column ="factory" ,property ="factory"),
            @Result(column = "tyreId",property = "tyre",javaType = Tyre.class,one = @One(select = "com.maxxis.dao.TyreDao.findById"))

    })
  public   List<Test> findTestByTestItemSP() throws Exception;

    //把第一条的开始放开  可以操作
    @Update("update Rctest set kaishi=1 where number=1 and factory='CSTC'")
    public   void updateKaiShi()throws  Exception;


//待排计划
    @Select("select * from Rctest where state='0'and cancel='0' and factory='CSTC'")
    @Results({
            @Result(id = true,column ="id" ,property ="id"),
            @Result(column ="number" ,property ="number"),
            @Result(column ="TestItem" ,property ="TestItem"),
            @Result(column ="sampleID" ,property ="sampleID"),
            @Result(column ="Standard" ,property ="Standard"),
            @Result(column ="UsageRim" ,property ="UsageRim"),
            @Result(column ="pt" ,property ="pt"),
            @Result(column ="Fz" ,property ="Fz"),
            @Result(column ="Vr" ,property ="Vr"),
            @Result(column ="CA" ,property ="CA"),
            @Result(column ="SA" ,property ="SA"),
            @Result(column ="PA" ,property ="PA"),
            @Result(column ="Mileage" ,property ="Mileage"),
            @Result(column ="FRAngleDeg" ,property ="FRAngleDeg"),
            @Result(column ="FFTOrder",property ="FFTOrder"),
            @Result(column ="FlatSpotPt" ,property ="FlatSpotPt"),
            @Result(column ="FlatSpotFz" ,property ="FlatSpotFz"),
            @Result(column ="ExpectedDate" ,property ="ExpectedDate"),
            @Result(column ="state" ,property ="state"),
            @Result(column ="complete" ,property ="complete"),
            @Result(column ="cancel" ,property ="cancel"),
            @Result(column ="red" ,property ="red"),
            @Result(column ="spbh" ,property ="spbh"),
            @Result(column ="caozuo" ,property ="caozuo"),
            @Result(column ="kaishi" ,property ="kaishi"),
            @Result(column ="endTime" ,property ="endTime"),
            @Result(column ="cancellationTime" ,property ="cancellationTime"),
            @Result(column ="factory" ,property ="factory"),
            @Result(column = "tyreId",property = "tyre",javaType = Tyre.class,one = @One(select = "com.maxxis.dao.TyreDao.findById"))

    })
      public List<Test> testDao() throws Exception;



    //待排取消
    @Update("update Rctest set cancel='1'where id=#{id};update Rctest set cancellationTime=#{result1a} where id=#{id}")
   public void TestCancel(@Param("id")String id,@Param("result1a")String result1a)throws  Exception;


//    BC实验室待排计划
@Select("select number,\n" +
        "       t1.id  as idRctest,TestItem,sampleID,Standard,UsageRim,pt,Fz,Vr,CA,SA,PA,Mileage,FRangleDeg,FFTOrder,FlatSpotPt,FlatSpotFz,ExpectedDate,state,complete,cancel,red,spbh,caozuo,kaishi,endTime,cancellationTime,factory,t2.id  as idRctyre,Size,t3.id as idRccliet,RequestId,Project,Purpose,ProjectEngineer,PriorityDes,RequestTime,TimeA,TestTime,ContactExt,Remark,PriorityDesNumber,sampleDisposal\n" +
        "  from Rctest t1,Rctyre t2,Rccliet t3 where t1.tyreId =t2.id and t2.clietId=t3.id and TestItem='BC' and factory='CSTC' and state='0'and cancel='0' and complete='0' order by RequestTime,PriorityDesNumber,Project,requestId")
   public List<TestClietTyre> findByStateBC()throws  Exception;

//BC实验室顺位排程
@Update("update Rctest set number=(SELECT COUNT(*)+1 FROM Rctest  WHERE number IS NOT NULL and number!=0 and TestItem='BC') WHERE id=#{id};update Rctest set state='1' where id=#{id}")
  public   void updateStateBC(String id) throws Exception;


    //BC实验室完成
    @Update("update Rctest set endTime=#{result1a} where id=#{id};update Rctest set complete=1 where id=#{id};update Rctest set spbh=0 where id=#{id};update Rctest set number=0 where id=#{id};update Rctest set number=number-1 where number>#{number} and state=1 and complete='0'and TestItem='BC' and factory='CSTC'")
     public void updateCompleteBC( @Param("id")String id,@Param("number")int number,@Param("result1a") String result1a) throws  Exception;


    //  SStiffness 实验室已排查询
    @Select("select * from Rctest where (TestItem='KZ: Vertical Stiffness' or TestItem='KX: Longitudinal Stiffness' or TestItem='KY: Lateral Stiffness' or TestItem='KT: Torsional Stiffness' or TestItem='KE: Enveloping Stiffness')and state='1'and complete='0'and cancel='0' and factory='CSTC' order by number ASC")
    @Results({
            @Result(id = true,column ="id" ,property ="id"),
            @Result(column ="number" ,property ="number"),
            @Result(column ="TestItem" ,property ="TestItem"),
            @Result(column ="sampleID" ,property ="sampleID"),
            @Result(column ="Standard" ,property ="Standard"),
            @Result(column ="UsageRim" ,property ="UsageRim"),
            @Result(column ="pt" ,property ="pt"),
            @Result(column ="Fz" ,property ="Fz"),
            @Result(column ="Vr" ,property ="Vr"),
            @Result(column ="CA" ,property ="CA"),
            @Result(column ="SA" ,property ="SA"),
            @Result(column ="PA" ,property ="PA"),
            @Result(column ="Mileage" ,property ="Mileage"),
            @Result(column ="FRAngleDeg" ,property ="FRAngleDeg"),
            @Result(column ="FFTOrder" ,property ="FFTOrder"),
            @Result(column ="FlatSpotPt" ,property ="FlatSpotPt"),
            @Result(column ="FlatSpotFz" ,property ="FlatSpotFz"),
            @Result(column ="ExpectedDate" ,property ="ExpectedDate"),
            @Result(column ="state" ,property ="state"),
            @Result(column ="complete" ,property ="complete"),
            @Result(column ="cancel" ,property ="cancel"),
            @Result(column ="red" ,property ="red"),
            @Result(column ="spbh" ,property ="spbh"),
            @Result(column ="caozuo" ,property ="caozuo"),
            @Result(column ="kaishi" ,property ="kaishi"),
            @Result(column ="endTime" ,property ="endTime"),
            @Result(column ="cancellationTime" ,property ="cancellationTime"),
            @Result(column ="factory" ,property ="factory"),
            @Result(column = "tyreId",property = "tyre",javaType = Tyre.class,one = @One(select = "com.maxxis.dao.TyreDao.findById"))

    })
    List<Test> findTestByTestItemSStiffness()throws  Exception;

    //     SStiffness实验室待排查询
    @Select("select number,\n" +
            "       t1.id  as idRctest,TestItem,sampleID,Standard,UsageRim,pt,Fz,Vr,CA,SA,PA,Mileage,FRangleDeg,FFTOrder,FlatSpotPt,FlatSpotFz,ExpectedDate,state,complete,cancel,red,spbh,caozuo,kaishi,endTime,cancellationTime,factory,t2.id  as idRctyre,Size,t3.id as idRccliet,RequestId,Project,Purpose,ProjectEngineer,PriorityDes,RequestTime,TimeA,TestTime,ContactExt,Remark,PriorityDesNumber,sampleDisposal\n" +
            "  from Rctest t1,Rctyre t2,Rccliet t3 where t1.tyreId =t2.id and t2.clietId=t3.id and (TestItem='KZ: Vertical Stiffness' or TestItem='KX: Longitudinal Stiffness' or TestItem='KY: Lateral Stiffness' or TestItem='KT: Torsional Stiffness' or TestItem='KE: Enveloping Stiffness') and state='0'and cancel='0' and complete='0' and factory='CSTC' order by RequestTime,PriorityDesNumber,Project,requestId")
    List<TestClietTyre> findByStateSStiffness()throws  Exception;

    //        查询出SStiffness待排一共多少条记录
    @Select("select count(*) from  Rctest t1,Rctyre t2,Rccliet t3 where t1.tyreId =t2.id and t2.clietId=t3.id and state='0'and cancel='0' and complete='0' and factory='CSTC' and (TestItem='KZ: Vertical Stiffness' or TestItem='KX: Longitudinal Stiffness' or TestItem='KY: Lateral Stiffness' or TestItem='KT: Torsional Stiffness' or TestItem='KE: Enveloping Stiffness')")
    int findCountDaiSStiffness()throws  Exception;

    //        查询SStiffness已排现在一共的已排记录
    @Select("select count(*) from Rctest where state='1'and complete='0'and cancel='0' and factory='CSTC' and (TestItem='KZ: Vertical Stiffness' or TestItem='KX: Longitudinal Stiffness' or TestItem='KY: Lateral Stiffness' or TestItem='KT: Torsional Stiffness' or TestItem='KE: Enveloping Stiffness')")
    int findStateCountSStiffness()throws  Exception;


    //  查询SStiffness已排并且没有再做的(按照number顺序)
    @Select("select * from Rctest  where (TestItem='KZ: Vertical Stiffness' or TestItem='KX: Longitudinal Stiffness' or TestItem='KY: Lateral Stiffness' or TestItem='KT: Torsional Stiffness' or TestItem='KE: Enveloping Stiffness')  and state=1 and cancel=0 and complete=0 and spbh=0 and red=0 and factory='CSTC' order by number")
    List<Test> findTestByStateNoZuoSStiffness()throws  Exception;

    //    获取SStiffness设定的时间
    @Select("select * from RcworktimeSStiffness order by gotowork")
    List<WorkTimeSStiffness> findworktimeSStiffness()throws  Exception;

    //SStiffness一键排程向上调整  （调整时间）
    @Update("update Rctest set ExpectedDate=#{result1a} where number=#{numberA} and  (TestItem='KZ: Vertical Stiffness' or TestItem='KX: Longitudinal Stiffness' or TestItem='KY: Lateral Stiffness' or TestItem='KT: Torsional Stiffness' or TestItem='KE: Enveloping Stiffness') and factory='CSTC'")
    void updateHSUExpectedDateUPSStiffness(@Param("numberA")Integer numberA, @Param("result1a")String result1a);


    //    SStiffness 去查询数据库有几个已经开始不需要进行循环的
    @Select("select COUNT(*) FROM Rctest where (TestItem='KZ: Vertical Stiffness' or TestItem='KX: Longitudinal Stiffness' or TestItem='KY: Lateral Stiffness' or TestItem='KT: Torsional Stiffness' or TestItem='KE: Enveloping Stiffness')  and state=1 and cancel=0 and complete=0 and (spbh=1 or red=1) and factory='CSTC'")
    public int findByspbhOneSStiffness()throws  Exception;

    //        去数据库把没有循环到的已经排好的数据变成待排(SStiffness)
    @Update("update Rctest set state=0,number=0 where number>=#{number} and (TestItem='KZ: Vertical Stiffness' or TestItem='KX: Longitudinal Stiffness' or TestItem='KY: Lateral Stiffness' or TestItem='KT: Torsional Stiffness' or TestItem='KE: Enveloping Stiffness') and cancel=0 and complete=0 and factory='CSTC'")
    void updateSStiffnessnumberDaiPai(int number)throws  Exception;

    //            根据时间去查  SStiffness 已经取消的
    @Select("select * from Rctest where (TestItem='KZ: Vertical Stiffness' or TestItem='KX: Longitudinal Stiffness' or TestItem='KY: Lateral Stiffness' or TestItem='KT: Torsional Stiffness' or TestItem='KE: Enveloping Stiffness') and cancel='1' and factory='CSTC' and cancellationTime > #{startQuXiao} and cancellationTime < #{endQuXiao} order by cancellationTime")
    @Results({
            @Result(id = true,column ="id" ,property ="id"),
            @Result(column ="number" ,property ="number"),
            @Result(column ="TestItem" ,property ="TestItem"),
            @Result(column ="sampleID" ,property ="sampleID"),
            @Result(column ="Standard" ,property ="Standard"),
            @Result(column ="UsageRim" ,property ="UsageRim"),
            @Result(column ="pt" ,property ="pt"),
            @Result(column ="Fz" ,property ="Fz"),
            @Result(column ="Vr" ,property ="Vr"),
            @Result(column ="CA" ,property ="CA"),
            @Result(column ="SA" ,property ="SA"),
            @Result(column ="PA" ,property ="PA"),
            @Result(column ="Mileage" ,property ="Mileage"),
            @Result(column ="FRAngleDeg" ,property ="FRAngleDeg"),
            @Result(column ="FFTOrder" ,property ="FFTOrder"),
            @Result(column ="FlatSpotPt" ,property ="FlatSpotPt"),
            @Result(column ="FlatSpotFz" ,property ="FlatSpotFz"),
            @Result(column ="ExpectedDate" ,property ="ExpectedDate"),
            @Result(column ="state" ,property ="state"),
            @Result(column ="complete" ,property ="complete"),
            @Result(column ="cancel" ,property ="cancel"),
            @Result(column ="red" ,property ="red"),
            @Result(column ="spbh" ,property ="spbh"),
            @Result(column ="caozuo" ,property ="caozuo"),
            @Result(column ="kaishi" ,property ="kaishi"),
            @Result(column ="endTime" ,property ="endTime"),
            @Result(column ="cancellationTime" ,property ="cancellationTime"),
            @Result(column ="factory" ,property ="factory"),
            @Result(column = "tyreId",property = "tyre",javaType = Tyre.class,one = @One(select = "com.maxxis.dao.TyreDao.findById"))

    })
    List<Test> findByCancellationTimeSStiffness(@Param("startQuXiao")String startQuXiao, @Param("endQuXiao")String endQuXiao)throws  Exception;


    //    SStiffness实验室完成查询（按时间查询）
    @Select("select * from Rctest where (TestItem='KZ: Vertical Stiffness' or TestItem='KX: Longitudinal Stiffness' or TestItem='KY: Lateral Stiffness' or TestItem='KT: Torsional Stiffness' or TestItem='KE: Enveloping Stiffness') and factory='CSTC' and complete='1'and endTime > #{start} and endTime < #{end}  order by endTime")
    @Results({
            @Result(id = true,column ="id" ,property ="id"),
            @Result(column ="number" ,property ="number"),
            @Result(column ="TestItem" ,property ="TestItem"),
            @Result(column ="sampleID" ,property ="sampleID"),
            @Result(column ="Standard" ,property ="Standard"),
            @Result(column ="UsageRim" ,property ="UsageRim"),
            @Result(column ="pt" ,property ="pt"),
            @Result(column ="Fz" ,property ="Fz"),
            @Result(column ="Vr" ,property ="Vr"),
            @Result(column ="CA" ,property ="CA"),
            @Result(column ="SA" ,property ="SA"),
            @Result(column ="PA" ,property ="PA"),
            @Result(column ="Mileage" ,property ="Mileage"),
            @Result(column ="FRAngleDeg" ,property ="FRAngleDeg"),
            @Result(column ="FFTOrder" ,property ="FFTOrder"),
            @Result(column ="FlatSpotPt" ,property ="FlatSpotPt"),
            @Result(column ="FlatSpotFz" ,property ="FlatSpotFz"),
            @Result(column ="ExpectedDate" ,property ="ExpectedDate"),
            @Result(column ="state" ,property ="state"),
            @Result(column ="complete" ,property ="complete"),
            @Result(column ="cancel" ,property ="cancel"),
            @Result(column ="red" ,property ="red"),
            @Result(column ="spbh" ,property ="spbh"),
            @Result(column ="caozuo" ,property ="caozuo"),
            @Result(column ="kaishi" ,property ="kaishi"),
            @Result(column ="endTime" ,property ="endTime"),
            @Result(column ="cancellationTime" ,property ="cancellationTime"),
            @Result(column ="factory" ,property ="factory"),
            @Result(column = "tyreId",property = "tyre",javaType = Tyre.class,one = @One(select = "com.maxxis.dao.TyreDao.findById"))

    })
    List<Test> findByendTimeSStiffness(@Param("start")String start,@Param("end")String end);


    //        SStiffness开始的时候把错误信息删除(先查id)
    @Select("select id from Rctest where number=#{number} and (TestItem='KZ: Vertical Stiffness' or TestItem='KX: Longitudinal Stiffness' or TestItem='KY: Lateral Stiffness' or TestItem='KT: Torsional Stiffness' or TestItem='KE: Enveloping Stiffness') and factory='CSTC'")
    String findTestIdSStiffness(int number)throws  Exception;

    //       SStiffness 改变隐藏按钮number
    @Update("update Rctest set spbh=1 where number=#{number} and (TestItem='KZ: Vertical Stiffness' or TestItem='KX: Longitudinal Stiffness' or TestItem='KY: Lateral Stiffness' or TestItem='KT: Torsional Stiffness' or TestItem='KE: Enveloping Stiffness') and factory='CSTC'")
    void spbhNumberSStiffness(int number)throws  Exception;

    //   查询已排 并且是查询还没有开始的已排  就是当前按钮number的后面的已经排的
    @Select("select * from  Rctest where number>=#{number} and (TestItem='KZ: Vertical Stiffness' or TestItem='KX: Longitudinal Stiffness' or TestItem='KY: Lateral Stiffness' or TestItem='KT: Torsional Stiffness' or TestItem='KE: Enveloping Stiffness') and factory='CSTC' and state=1 and cancel=0 and complete=0 order by number")
    @Results({
            @Result(id = true,column ="id" ,property ="id"),
            @Result(column ="number" ,property ="number"),
            @Result(column ="TestItem" ,property ="TestItem"),
            @Result(column ="sampleID" ,property ="sampleID"),
            @Result(column ="Standard" ,property ="Standard"),
            @Result(column ="UsageRim" ,property ="UsageRim"),
            @Result(column ="pt" ,property ="pt"),
            @Result(column ="Fz" ,property ="Fz"),
            @Result(column ="Vr" ,property ="Vr"),
            @Result(column ="CA" ,property ="CA"),
            @Result(column ="SA" ,property ="SA"),
            @Result(column ="PA" ,property ="PA"),
            @Result(column ="Mileage" ,property ="Mileage"),
            @Result(column ="FRAngleDeg" ,property ="FRAngleDeg"),
            @Result(column ="FFTOrder" ,property ="FFTOrder"),
            @Result(column ="FlatSpotPt" ,property ="FlatSpotPt"),
            @Result(column ="FlatSpotFz" ,property ="FlatSpotFz"),
            @Result(column ="ExpectedDate" ,property ="ExpectedDate"),
            @Result(column ="state" ,property ="state"),
            @Result(column ="complete" ,property ="complete"),
            @Result(column ="cancel" ,property ="cancel"),
            @Result(column ="red" ,property ="red"),
            @Result(column ="spbh" ,property ="spbh"),
            @Result(column ="caozuo" ,property ="caozuo"),
            @Result(column ="kaishi" ,property ="kaishi"),
            @Result(column ="endTime" ,property ="endTime"),
            @Result(column ="cancellationTime" ,property ="cancellationTime"),
            @Result(column ="factory" ,property ="factory"),
            @Result(column = "tyreId",property = "tyre",javaType = Tyre.class,one = @One(select = "com.maxxis.dao.TyreDao.findById"))

    })
    List<Test> findByNoStateNumberSStiffness(int number)throws  Exception;

    //    SStiffness实验室完成 (记录完成时间)
    @Update("update Rctest set endTime=#{result1a} where id=#{id};update Rctest set complete=1 where id=#{id};update Rctest set spbh=0 where id=#{id};update Rctest set number=0 where id=#{id};update Rctest set number=number-1 where number>#{number} and state=1 and complete='0'and (TestItem='KZ: Vertical Stiffness' or TestItem='KX: Longitudinal Stiffness' or TestItem='KY: Lateral Stiffness' or TestItem='KT: Torsional Stiffness' or TestItem='KE: Enveloping Stiffness') and factory='CSTC'")
    void updateCompleteSStiffness(@Param("number")int number, @Param("id")String id, @Param("result1a")String result1a)throws  Exception;


    //    SStiffness实验室取消开始
    @Update("update Rctest set spbh=0,caozuo=0 where (TestItem='KZ: Vertical Stiffness' or TestItem='KX: Longitudinal Stiffness' or TestItem='KY: Lateral Stiffness' or TestItem='KT: Torsional Stiffness' or TestItem='KE: Enveloping Stiffness') and state='1'and complete='0'and cancel='0' and factory='CSTC'")
    void quxiaokaishiStiffness()throws Exception;

    //       SStiffness 把数据库里面的操作更改数据
    @Update("update Rctest set caozuo=#{spbhOne} where (TestItem='KZ: Vertical Stiffness' or TestItem='KX: Longitudinal Stiffness' or TestItem='KY: Lateral Stiffness' or TestItem='KT: Torsional Stiffness' or TestItem='KE: Enveloping Stiffness') and factory='CSTC'")
    void updateCaoZuoSStiffness(int spbhOne)throws  Exception;

    //SStiffness实验室操作 上
    @Update("update Rctest set number=number+1 where (TestItem='KZ: Vertical Stiffness' or TestItem='KX: Longitudinal Stiffness' or TestItem='KY: Lateral Stiffness' or TestItem='KT: Torsional Stiffness' or TestItem='KE: Enveloping Stiffness') and factory='CSTC' and number>=#{numberS} and number < #{number};update Rctest set number=#{numberS} where id=#{id}")
    void TestNumberSStiffness(@Param("numberS")int number, @Param("number")int numberS, @Param("id")String id)throws Exception;

    //SStiffness实验室操作 下
    @Update("update Rctest set number=number-1 where (TestItem='KZ: Vertical Stiffness' or TestItem='KX: Longitudinal Stiffness' or TestItem='KY: Lateral Stiffness' or TestItem='KT: Torsional Stiffness' or TestItem='KE: Enveloping Stiffness') and factory='CSTC' and number<=#{numberS} and number > #{number};update Rctest set number=#{numberS} where id=#{id}")
    void TestNumberDownSStiffness(@Param("numberS")int number, @Param("number")int numberS, @Param("id")String id)throws Exception;

    //   SStiffness 已排 实验室取消
    @Update("update Rctest set cancellationTime=#{result1a} where id=#{id};update Rctest set cancel='1'where id=#{id};update Rctest set number='0'where id=#{id};update Rctest set number=number-1 where (TestItem='KZ: Vertical Stiffness' or TestItem='KX: Longitudinal Stiffness' or TestItem='KY: Lateral Stiffness' or TestItem='KT: Torsional Stiffness' or TestItem='KE: Enveloping Stiffness') and factory='CSTC' and number>#{number}")
    void updateCancelSStiffness(@Param("id")String id, @Param("number")int number, @Param("result1a")String result1a)throws  Exception;

    //  查询SStiffness已排(按照number顺序)
    @Select("select * from Rctest  where (TestItem='KZ: Vertical Stiffness' or TestItem='KX: Longitudinal Stiffness' or TestItem='KY: Lateral Stiffness' or TestItem='KT: Torsional Stiffness' or TestItem='KE: Enveloping Stiffness') and factory='CSTC' and state=1 and cancel=0 and complete=0 order by number")
    List<Test> findTestByStateSStiffness()throws  Exception;

    //   SStiffness实验室顺位排程
    @Update("update Rctest set number=(SELECT COUNT(*)+1 FROM Rctest  WHERE number IS NOT NULL and number!=0 and (TestItem='KZ: Vertical Stiffness' or TestItem='KX: Longitudinal Stiffness' or TestItem='KY: Lateral Stiffness' or TestItem='KT: Torsional Stiffness' or TestItem='KE: Enveloping Stiffness') and factory='CSTC') WHERE id=#{id};update Rctest set state='1' where id=#{id}")
    void updateStateSStiffness(String id)throws  Exception;

    //   SStiffness实验室插位
    @Update("update Rctest set number=number+1 where number>=#{numberB} and factory='CSTC' and (TestItem='KZ: Vertical Stiffness' or TestItem='KX: Longitudinal Stiffness' or TestItem='KY: Lateral Stiffness' or TestItem='KT: Torsional Stiffness' or TestItem='KE: Enveloping Stiffness');update Rctest set number=#{numberB} where id=#{id};update Rctest set state='1' where id=#{id}")
    void TestNumberChaSStiffness(@Param("numberB")int numberB, @Param("id")String id)throws  Exception;

    //    SStiffness需求编号搜索(待排)
    @Select("select number,\n" +
            "       t1.id  as idRctest,TestItem,sampleID,Standard,UsageRim,pt,Fz,Vr,CA,SA,PA,Mileage,FRangleDeg,FFTOrder,FlatSpotPt,FlatSpotFz,ExpectedDate,state,complete,cancel,red,spbh,caozuo,kaishi,endTime,cancellationTime,factory,t2.id  as idRctyre,Size,t3.id as idRccliet,RequestId,Project,Purpose,ProjectEngineer,PriorityDes,RequestTime,TimeA,TestTime,ContactExt,Remark,PriorityDesNumber,sampleDisposal\n" +
            "  from Rctest t1,Rctyre t2,Rccliet t3 where t1.tyreId =t2.id and t2.clietId=t3.id and (TestItem='KZ: Vertical Stiffness' or TestItem='KX: Longitudinal Stiffness' or TestItem='KY: Lateral Stiffness' or TestItem='KT: Torsional Stiffness' or TestItem='KE: Enveloping Stiffness') and factory='CSTC' and state='0'and cancel='0' and complete='0' and RequestId like '%${value}%'")
    List<TestClietTyre> findByTestRequestIdDaiPaiSStiffness(String requestId)throws Exception;

    //SStiffness查询出目前有没有已经排好的计划
    @Select("SELECT COUNT(*) FROM Rctest  WHERE number IS NOT NULL and number!=0 and (TestItem='KZ: Vertical Stiffness' or TestItem='KX: Longitudinal Stiffness' or TestItem='KY: Lateral Stiffness' or TestItem='KT: Torsional Stiffness' or TestItem='KE: Enveloping Stiffness') and factory='CSTC'")
    int findNumberSStiffness()throws Exception;

    //SStiffness一键排程（1)
    @Update("update Rctest set number=#{numberSP} where id=#{id};update Rctest set state='1' where id=#{id};update Rctest set ExpectedDate=#{result1a} where id=#{id}")
    void updateExpectedDateSStiffness(@Param("id") String id, @Param("result1a")String result1a, @Param("numberSP")int numberSP)throws Exception;

    //        查出SStiffness已排最后一条数据
    @Select("select * from Rctest where number=#{count} and (TestItem='KZ: Vertical Stiffness' or TestItem='KX: Longitudinal Stiffness' or TestItem='KY: Lateral Stiffness' or TestItem='KT: Torsional Stiffness' or TestItem='KE: Enveloping Stiffness') and factory='CSTC'")
    Test findBycountSStiffness(int count)throws Exception;

    //    SStiffness已排批量（1）
    @Update("update Rctest set number= number-1 where (TestItem='KZ: Vertical Stiffness' or TestItem='KX: Longitudinal Stiffness' or TestItem='KY: Lateral Stiffness' or TestItem='KT: Torsional Stiffness' or TestItem='KE: Enveloping Stiffness') and factory='CSTC' and number<=#{numberS} and number >#{nubmerOld};update Rctest set number=#{numberS} where id=#{idID}")
    void PiLiangOperateDownSStiffness(@Param("numberS")int numberS, @Param("nubmerOld")int nubmerOld, @Param("idID")String idID)throws Exception;

    //    SStiffness已排批量（2）
    @Update("update Rctest set number=number+1 where (TestItem='KZ: Vertical Stiffness' or TestItem='KX: Longitudinal Stiffness' or TestItem='KY: Lateral Stiffness' or TestItem='KT: Torsional Stiffness' or TestItem='KE: Enveloping Stiffness') and factory='CSTC' and number>=#{numberS} and number < #{nubmerOld};update Rctest set number=#{numberS} where id=#{idID}")
    void PiLiangOperateUpSStiffness(@Param("numberS")int numberS, @Param("nubmerOld")int nubmerOld, @Param("idID")String idID)throws  Exception;

    //SStiffness取消已排  进入待排
    @Update("update Rctest set number=0 where id=#{aid};update Rctest set state=0 where id=#{aid};update Rctest set number=number-1 where number>#{anumber} and (TestItem='KZ: Vertical Stiffness' or TestItem='KX: Longitudinal Stiffness' or TestItem='KY: Lateral Stiffness' or TestItem='KT: Torsional Stiffness' or TestItem='KE: Enveloping Stiffness') and factory='CSTC'")
    void JinRuDaiPaiSStiffness(@Param("anumber")String anumber, @Param("aid")String aid)throws Exception;

    //RR实验室待排计划
    @Select("select number,\n" +
            "       t1.id  as idRctest,TestItem,sampleID,Standard,UsageRim,pt,Fz,Vr,CA,SA,PA,Mileage,FRangleDeg,FFTOrder,FlatSpotPt,FlatSpotFz,ExpectedDate,state,complete,cancel,red,spbh,caozuo,kaishi,endTime,cancellationTime,factory,t2.id  as idRctyre,Size,t3.id as idRccliet,RequestId,Project,Purpose,ProjectEngineer,PriorityDes,RequestTime,TimeA,TestTime,ContactExt,Remark,PriorityDesNumber,sampleDisposal\n" +
            "  from Rctest t1,Rctyre t2,Rccliet t3 where t1.tyreId =t2.id and t2.clietId=t3.id and (TestItem='RR' or TestItem='RPK' or TestItem='CDRR') and factory='CSTC' and state='0'and cancel='0' and complete='0' order by RequestTime,PriorityDesNumber,Project,requestId")
    List<TestClietTyre> findByStateRR();
    //        查询出待排一共多少条记录RR
    @Select("select count(*) from  Rctest t1,Rctyre t2,Rccliet t3 where t1.tyreId =t2.id and t2.clietId=t3.id and state='0'and cancel='0' and complete='0' and factory='CSTC' and (TestItem='RR' or TestItem='RPK' or TestItem='CDRR')")
    public  int findCountDaiRR()throws  Exception;

    //        查询RR已排现在一共的已排记录
    @Select("select count(*) from Rctest where state='1'and complete='0'and cancel='0' and factory='CSTC' and (TestItem='RR' or TestItem='RPK' or TestItem='CDRR')")
    public int findStateCountRR()throws  Exception;

    //    RR需求编号搜索(待排)
    @Select("select number,\n" +
            "       t1.id  as idRctest,TestItem,sampleID,Standard,UsageRim,pt,Fz,Vr,CA,SA,PA,Mileage,FRangleDeg,FFTOrder,FlatSpotPt,FlatSpotFz,ExpectedDate,state,complete,cancel,red,spbh,caozuo,kaishi,endTime,cancellationTime,factory,t2.id  as idRctyre,Size,t3.id as idRccliet,RequestId,Project,Purpose,ProjectEngineer,PriorityDes,RequestTime,TimeA,TestTime,ContactExt,Remark,PriorityDesNumber,sampleDisposal\n" +
            "  from Rctest t1,Rctyre t2,Rccliet t3 where t1.tyreId =t2.id and t2.clietId=t3.id and (TestItem='RR' or TestItem='RPK' or TestItem='CDRR') and factory='CSTC' and state='0'and cancel='0' and complete='0' and RequestId like '%${value}%'")
    List<TestClietTyre> findByTestRequestIdDaiPaiRR(String requestId)throws  Exception;


//    RR实验室向上
    @Update("update Rctest set number=number+1 where (TestItem='RR' or TestItem='RPK' or TestItem='CDRR') and factory='CSTC' and number>=#{numberS} and number < #{number};update Rctest set number=#{numberS} where id=#{id}")
public     void TestNumberRR(@Param("numberS") int number, @Param("number") int numberS, @Param("id") String id);

    //  RR实验室向上  批量
    @Update("update Rctest set number=number+1 where (TestItem='RR' or TestItem='RPK' or TestItem='CDRR') and factory='CSTC' and number>=#{numberS} and number < #{nubmerOld};update Rctest set number=#{numberS} where id=#{idID}")
    void PiLiangOperateUpRR(@Param("numberS") int numberS, @Param("nubmerOld") int nubmerOld, @Param("idID") String idID)throws  Exception;

    //      RR向下调整批量处理
    @Update("update Rctest set number= number-1 where (TestItem='RR' or TestItem='RPK' or TestItem='CDRR') and factory='CSTC' and number<=#{numberS} and number >#{nubmerOld};update Rctest set number=#{numberS} where id=#{idID}")
    void PiLiangOperateDownRR(@Param("numberS") int numberS, @Param("nubmerOld") int nubmerOld, @Param("idID") String idID)throws  Exception;

    //RR实验室向下
    @Update("update Rctest set number=number-1 where (TestItem='RR' or TestItem='RPK' or TestItem='CDRR') and factory='CSTC' and number<=#{numberS} and number > #{number};update Rctest set number=#{numberS} where id=#{id}")
   public void TestNumberDownRR(@Param("numberS") int number, @Param("number") int numberS, @Param("id") String id);


    //     RR   开始的时候把错误信息删除(先查id)
    @Select("select id from Rctest where number=#{number} and (TestItem='RR' or TestItem='RPK' or TestItem='CDRR') and factory='CSTC'")
    String findTestIdRR(int number)throws  Exception;

    //       RR 开始的时候把颜色改成0

    @Update("update Rctest set red=0 where id=#{id} and (TestItem='RR' or TestItem='RPK' or TestItem='CDRR') and factory='CSTC'")
    void updateredRR(String id)throws  Exception;

    //      RR  改变隐藏按钮number
    @Update("update Rctest set spbh=1 where number=#{number} and (TestItem='RR' or TestItem='RPK' or TestItem='CDRR') and factory='CSTC'")
    void spbhNumberRR(int number)throws  Exception;

    //   查询已排 并且是查询还没有开始的已排  就是当前按钮number的后面的已经排的
    @Select("select * from  Rctest where number>=#{number} and (TestItem='RR' or TestItem='RPK' or TestItem='CDRR') and factory='CSTC' and state=1 and cancel=0 and complete=0 order by number")
    @Results({
            @Result(id = true,column ="id" ,property ="id"),
            @Result(column ="number" ,property ="number"),
            @Result(column ="TestItem" ,property ="TestItem"),
            @Result(column ="sampleID" ,property ="sampleID"),
            @Result(column ="Standard" ,property ="Standard"),
            @Result(column ="UsageRim" ,property ="UsageRim"),
            @Result(column ="pt" ,property ="pt"),
            @Result(column ="Fz" ,property ="Fz"),
            @Result(column ="Vr" ,property ="Vr"),
            @Result(column ="CA" ,property ="CA"),
            @Result(column ="SA" ,property ="SA"),
            @Result(column ="PA" ,property ="PA"),
            @Result(column ="Mileage" ,property ="Mileage"),
            @Result(column ="FRAngleDeg" ,property ="FRAngleDeg"),
            @Result(column ="FFTOrder" ,property ="FFTOrder"),
            @Result(column ="FlatSpotPt" ,property ="FlatSpotPt"),
            @Result(column ="FlatSpotFz" ,property ="FlatSpotFz"),
            @Result(column ="ExpectedDate" ,property ="ExpectedDate"),
            @Result(column ="state" ,property ="state"),
            @Result(column ="complete" ,property ="complete"),
            @Result(column ="cancel" ,property ="cancel"),
            @Result(column ="red" ,property ="red"),
            @Result(column ="spbh" ,property ="spbh"),
            @Result(column ="caozuo" ,property ="caozuo"),
            @Result(column ="kaishi" ,property ="kaishi"),
            @Result(column ="endTime" ,property ="endTime"),
            @Result(column ="cancellationTime" ,property ="cancellationTime"),
            @Result(column ="factory" ,property ="factory"),
            @Result(column = "tyreId",property = "tyre",javaType = Tyre.class,one = @One(select = "com.maxxis.dao.TyreDao.findById"))

    })
    List<Test> findByNoStateNumberRR(int number)throws  Exception;

    //RR一键排程向上调整  （调整时间）
    @Update("update Rctest set ExpectedDate=#{result1a} where number=#{numberA} and  (TestItem='RR' or TestItem='RPK' or TestItem='CDRR') and factory='CSTC'")
    void updateHSUExpectedDateUPRR(@Param("numberA") Integer numberA, @Param("result1a") String result1a)throws  Exception;

    //      RR  去查询数据库有几个已经开始不需要进行循环的
    @Select("select COUNT(*) FROM Rctest where (TestItem='RR' or TestItem='RPK' or TestItem='CDRR') and factory='CSTC'  and state=1 and cancel=0 and complete=0 and (spbh=1 or red=1)")
    int findByspbhOneRR()throws  Exception;
    //        RR把数据库里面的操作更改数据
    @Update("update Rctest set caozuo=#{spbhOne} where (TestItem='RR' or TestItem='RPK' or TestItem='CDRR') and factory='CSTC'")
    void updateCaoZuoRR(int spbhOne)throws  Exception;
    //    去数据库把没有循环到的已经排好的数据变成待排(RR)
    @Update("update Rctest set state=0,number=0 where number>=#{number} and (TestItem='RR' or TestItem='RPK' or TestItem='CDRR') and factory='CSTC' and cancel=0 and complete=0")
    void updateSPnumberDaiPaiRR(int numberN)throws  Exception;

    //    RR实验室完成
    @Update("update Rctest set endTime=#{result1a} where id=#{id};update Rctest set complete=1 where id=#{id};update Rctest set spbh=0 where id=#{id};update Rctest set number=0 where id=#{id};update Rctest set number=number-1 where number>#{number} and state=1 and complete='0'and (TestItem='RR' or TestItem='RPK' or TestItem='CDRR') and factory='CSTC'")
   public void updateCompleteRR(@Param("id") String id, @Param("number") int number,@Param("result1a")String result1a);

    //  查询RR已排并且没有开始的(按照number顺序)
    @Select("select * from Rctest  where (TestItem='RR' or TestItem='RPK' or TestItem='CDRR') and factory='CSTC'  and state=1 and cancel=0 and complete=0 and spbh=0 and red=0 order by number")
    List<Test> findTestByStateNoZuoRR()throws  Exception;

    //    RR实验室取消 已排
    @Update("update Rctest set cancellationTime=#{result1a} where id=#{id};update Rctest set cancel='1'where id=#{id};update Rctest set number=number-1 where (TestItem='RR' or TestItem='RPK' or TestItem='CDRR') and factory='CSTC' and number>#{number};update Rctest set number='0'where id=#{id}")
 public    void updateCancelRR(@Param("id") String id, @Param("number")int number,@Param("result1a") String result1a)throws Exception;

    //      设置排测时间   RR
    @Update("update Rctest set ExpectedDate=#{tiaoZhengShiJianData} where id=#{idA} and number=#{numberA}")
    public   void TiaoZhengShiJianRR(@Param("tiaoZhengShiJianData") String tiaoZhengShiJianData, @Param("idA")String idA, @Param("numberA")int numberA)throws  Exception;

    //    RR实验室取消开始
    @Update("update Rctest set spbh=0,caozuo=0 where (TestItem='RR' or TestItem='RPK' or TestItem='CDRR') and factory='CSTC' and state='1'and complete='0'and cancel='0' ")
    void quxiaokaishiRR()throws Exception;

    //RR取消已排  进入待排
    @Update("update Rctest set number=0 where id=#{aid};update Rctest set state=0 where id=#{aid};update Rctest set number=number-1 where number>#{anumber} and (TestItem='RR' or TestItem='RPK' or TestItem='CDRR') and factory='CSTC'")
    void JinRuDaiPaiRR(@Param("anumber") String anumber, @Param("aid") String aid)throws  Exception;

    //RR查询出目前有没有已经排好的计划
    @Select("SELECT COUNT(*) FROM Rctest  WHERE number IS NOT NULL and number!=0 and (TestItem='RR' or TestItem='RPK' or TestItem='CDRR') and factory='CSTC'")
    int findNumberRR()throws  Exception;

    //   RR实验室一键排程
    @Update("update Rctest set number=#{numberSP} where id=#{id};update Rctest set state='1' where id=#{id};update Rctest set ExpectedDate=#{result1a} where id=#{id}")
    void updateExpectedDateRR(@Param("id") String id, @Param("result1a") String result1a, @Param("numberSP") int numberSP)throws  Exception;

//    RR实验室顺位

@Update("update Rctest set number=(SELECT COUNT(*)+1 FROM Rctest  WHERE number IS NOT NULL and number!=0 and (TestItem='RR' or TestItem='RPK' or TestItem='CDRR') and factory='CSTC') WHERE id=#{id};update Rctest set state='1' where id=#{id}")
   public void updateStateRR(String id);



// RR实验室插位排程
   @Update("update Rctest set number=number+1 where number>=#{numberB} and (TestItem='RR' or TestItem='RPK' or TestItem='CDRR') and factory='CSTC';update Rctest set number=#{numberB} where id=#{id};update Rctest set state='1' where id=#{id}")
  public void TestNumberRRR(@Param("numberB")int numberB, @Param("id") String id)throws  Exception;


    //    RR实验室完成查询（按时间查询）
    @Select("select * from Rctest where (TestItem='RR' or TestItem='RPK' or TestItem='CDRR') and factory='CSTC' and complete='1'and endTime > #{start} and endTime < #{end}  order by endTime")
    @Results({
            @Result(id = true,column ="id" ,property ="id"),
            @Result(column ="number" ,property ="number"),
            @Result(column ="TestItem" ,property ="TestItem"),
            @Result(column ="sampleID" ,property ="sampleID"),
            @Result(column ="Standard" ,property ="Standard"),
            @Result(column ="UsageRim" ,property ="UsageRim"),
            @Result(column ="pt" ,property ="pt"),
            @Result(column ="Fz" ,property ="Fz"),
            @Result(column ="Vr" ,property ="Vr"),
            @Result(column ="CA" ,property ="CA"),
            @Result(column ="SA" ,property ="SA"),
            @Result(column ="PA" ,property ="PA"),
            @Result(column ="Mileage" ,property ="Mileage"),
            @Result(column ="FRAngleDeg" ,property ="FRAngleDeg"),
            @Result(column ="FFTOrder" ,property ="FFTOrder"),
            @Result(column ="FlatSpotPt" ,property ="FlatSpotPt"),
            @Result(column ="FlatSpotFz" ,property ="FlatSpotFz"),
            @Result(column ="ExpectedDate" ,property ="ExpectedDate"),
            @Result(column ="state" ,property ="state"),
            @Result(column ="complete" ,property ="complete"),
            @Result(column ="cancel" ,property ="cancel"),
            @Result(column ="red" ,property ="red"),
            @Result(column ="spbh" ,property ="spbh"),
            @Result(column ="caozuo" ,property ="caozuo"),
            @Result(column ="kaishi" ,property ="kaishi"),
            @Result(column ="endTime" ,property ="endTime"),
            @Result(column ="cancellationTime" ,property ="cancellationTime"),
            @Result(column ="factory" ,property ="factory"),
            @Result(column = "tyreId",property = "tyre",javaType = Tyre.class,one = @One(select = "com.maxxis.dao.TyreDao.findById"))

    })
    List<Test> findByendTimeCompleteRR(@Param("start") String start, @Param("end") String end)throws Exception;

    //    RR实验室取消查询（按时间查询）
    @Select("select * from Rctest where (TestItem='RR' or TestItem='RPK' or TestItem='CDRR') and factory='CSTC' and cancel='1'and cancellationTime > #{startQuXiao} and cancellationTime < #{endQuXiao} order by cancellationTime")
    @Results({
            @Result(id = true,column ="id" ,property ="id"),
            @Result(column ="number" ,property ="number"),
            @Result(column ="TestItem" ,property ="TestItem"),
            @Result(column ="sampleID" ,property ="sampleID"),
            @Result(column ="Standard" ,property ="Standard"),
            @Result(column ="UsageRim" ,property ="UsageRim"),
            @Result(column ="pt" ,property ="pt"),
            @Result(column ="Fz" ,property ="Fz"),
            @Result(column ="Vr" ,property ="Vr"),
            @Result(column ="CA" ,property ="CA"),
            @Result(column ="SA" ,property ="SA"),
            @Result(column ="PA" ,property ="PA"),
            @Result(column ="Mileage" ,property ="Mileage"),
            @Result(column ="FRAngleDeg" ,property ="FRAngleDeg"),
            @Result(column ="FFTOrder" ,property ="FFTOrder"),
            @Result(column ="FlatSpotPt" ,property ="FlatSpotPt"),
            @Result(column ="FlatSpotFz" ,property ="FlatSpotFz"),
            @Result(column ="ExpectedDate" ,property ="ExpectedDate"),
            @Result(column ="state" ,property ="state"),
            @Result(column ="complete" ,property ="complete"),
            @Result(column ="cancel" ,property ="cancel"),
            @Result(column ="red" ,property ="red"),
            @Result(column ="spbh" ,property ="spbh"),
            @Result(column ="caozuo" ,property ="caozuo"),
            @Result(column ="kaishi" ,property ="kaishi"),
            @Result(column ="endTime" ,property ="endTime"),
            @Result(column ="cancellationTime" ,property ="cancellationTime"),
            @Result(column ="factory" ,property ="factory"),
            @Result(column = "tyreId",property = "tyre",javaType = Tyre.class,one = @One(select = "com.maxxis.dao.TyreDao.findById"))

    })
    List<Test> findByCancellationTimeCancelRR(@Param("startQuXiao") String startQuXiao, @Param("endQuXiao") String endQuXiao)throws Exception;

    //HSU实验室待排计划
    @Select("select number,\n" +
            "       t1.id  as idRctest,TestItem,sampleID,Standard,UsageRim,pt,Fz,Vr,CA,SA,PA,Mileage,FRangleDeg,FFTOrder,FlatSpotPt,FlatSpotFz,ExpectedDate,state,complete,cancel,red,spbh,caozuo,kaishi,endTime,cancellationTime,factory,t2.id  as idRctyre,Size,t3.id as idRccliet,RequestId,Project,Purpose,ProjectEngineer,PriorityDes,RequestTime,TimeA,TestTime,ContactExt,Remark,PriorityDesNumber,sampleDisposal\n" +
            "  from Rctest t1,Rctyre t2,Rccliet t3 where t1.tyreId =t2.id and t2.clietId=t3.id and (TestItem='HSU' or TestItem='LSU' or TestItem='Flatspot') and factory='CSTC' and state='0'and cancel='0' and complete='0' order by RequestTime,PriorityDesNumber,Project,requestId")
  public  List<TestClietTyre> findByStateHSU()throws  Exception;


  //  查询出HSU待排一共多少条记录
  @Select("select count(*) from  Rctest t1,Rctyre t2,Rccliet t3 where t1.tyreId =t2.id and t2.clietId=t3.id and (TestItem='HSU' or TestItem='LSU' or TestItem='Flatspot') and factory='CSTC' and state='0'and cancel='0' and complete='0'")
  int findCountDaiHSU()throws Exception;

  //        查询HSU已排现在一共的记录
  @Select("select count(*) from Rctest where state='1'and complete='0'and cancel='0' and (TestItem='HSU' or TestItem='LSU' or TestItem='Flatspot') and factory='CSTC'")
  int findStateCountHSU()throws Exception;

  //        查出HSU已排最后一条数据
  @Select("select * from Rctest where number=#{count} and (TestItem='HSU' or TestItem='LSU' or TestItem='Flatspot') and factory='CSTC'")
  Test findBycountHSU(int count)throws Exception;

  //    HSU实验室完成查询（按时间查询）
  @Select("select * from Rctest where (TestItem='HSU' or TestItem='LSU' or TestItem='Flatspot') and factory='CSTC' and complete='1'and endTime > #{start} and endTime < #{end}  order by endTime")
  @Results({
          @Result(id = true,column ="id" ,property ="id"),
          @Result(column ="number" ,property ="number"),
          @Result(column ="TestItem" ,property ="TestItem"),
          @Result(column ="sampleID" ,property ="sampleID"),
          @Result(column ="Standard" ,property ="Standard"),
          @Result(column ="UsageRim" ,property ="UsageRim"),
          @Result(column ="pt" ,property ="pt"),
          @Result(column ="Fz" ,property ="Fz"),
          @Result(column ="Vr" ,property ="Vr"),
          @Result(column ="CA" ,property ="CA"),
          @Result(column ="SA" ,property ="SA"),
          @Result(column ="PA" ,property ="PA"),
          @Result(column ="Mileage" ,property ="Mileage"),
          @Result(column ="FRAngleDeg" ,property ="FRAngleDeg"),
          @Result(column ="FFTOrder" ,property ="FFTOrder"),
          @Result(column ="FlatSpotPt" ,property ="FlatSpotPt"),
          @Result(column ="FlatSpotFz" ,property ="FlatSpotFz"),
          @Result(column ="ExpectedDate" ,property ="ExpectedDate"),
          @Result(column ="state" ,property ="state"),
          @Result(column ="complete" ,property ="complete"),
          @Result(column ="cancel" ,property ="cancel"),
          @Result(column ="red" ,property ="red"),
          @Result(column ="spbh" ,property ="spbh"),
          @Result(column ="caozuo" ,property ="caozuo"),
          @Result(column ="kaishi" ,property ="kaishi"),
          @Result(column ="endTime" ,property ="endTime"),
          @Result(column ="cancellationTime" ,property ="cancellationTime"),
          @Result(column ="factory" ,property ="factory"),
          @Result(column = "tyreId",property = "tyre",javaType = Tyre.class,one = @One(select = "com.maxxis.dao.TyreDao.findById"))

  })
  List<Test> findByendTimeCompleteHSU(@Param("start") String start, @Param("end") String end)throws  Exception;

  //   根据时间去查  HSU  已经取消的
  @Select("select * from Rctest where (TestItem='HSU' or TestItem='LSU' or TestItem='Flatspot') and factory='CSTC' and cancel='1'and cancellationTime > #{startQuXiao} and cancellationTime < #{endQuXiao} order by cancellationTime")
  @Results({
          @Result(id = true,column ="id" ,property ="id"),
          @Result(column ="number" ,property ="number"),
          @Result(column ="TestItem" ,property ="TestItem"),
          @Result(column ="sampleID" ,property ="sampleID"),
          @Result(column ="Standard" ,property ="Standard"),
          @Result(column ="UsageRim" ,property ="UsageRim"),
          @Result(column ="pt" ,property ="pt"),
          @Result(column ="Fz" ,property ="Fz"),
          @Result(column ="Vr" ,property ="Vr"),
          @Result(column ="CA" ,property ="CA"),
          @Result(column ="SA" ,property ="SA"),
          @Result(column ="PA" ,property ="PA"),
          @Result(column ="Mileage" ,property ="Mileage"),
          @Result(column ="FRAngleDeg" ,property ="FRAngleDeg"),
          @Result(column ="FFTOrder" ,property ="FFTOrder"),
          @Result(column ="FlatSpotPt" ,property ="FlatSpotPt"),
          @Result(column ="FlatSpotFz" ,property ="FlatSpotFz"),
          @Result(column ="ExpectedDate" ,property ="ExpectedDate"),
          @Result(column ="state" ,property ="state"),
          @Result(column ="complete" ,property ="complete"),
          @Result(column ="cancel" ,property ="cancel"),
          @Result(column ="red" ,property ="red"),
          @Result(column ="spbh" ,property ="spbh"),
          @Result(column ="caozuo" ,property ="caozuo"),
          @Result(column ="kaishi" ,property ="kaishi"),
          @Result(column ="endTime" ,property ="endTime"),
          @Result(column ="cancellationTime" ,property ="cancellationTime"),
          @Result(column ="factory" ,property ="factory"),
          @Result(column = "tyreId",property = "tyre",javaType = Tyre.class,one = @One(select = "com.maxxis.dao.TyreDao.findById"))

  })
  List<Test> findByCancellationTimeCancelHSU(@Param("startQuXiao") String startQuXiao, @Param("endQuXiao")String endQuXiao)throws  Exception;

    //    HSU实验室顺位排程
    @Update("update Rctest set number=(SELECT COUNT(*)+1 FROM Rctest  WHERE number IS NOT NULL and number!=0 and (TestItem='HSU' or TestItem='LSU' or TestItem='Flatspot') and factory='CSTC') WHERE id=#{id};update Rctest set state='1' where id=#{id}")
     public  void updateStateHSU(String id)throws  Exception;


    //    HSU实验室插位排程
    @Update("update Rctest set number=number+1 where number>=#{numberB} and (TestItem='HSU' or TestItem='LSU' or TestItem='Flatspot') and factory='CSTC';update Rctest set number=#{numberB} where id=#{id};update Rctest set state='1' where id=#{id}")
    public void TestNumberHSUU(@Param("numberB") int numberB, @Param("id") String id)throws  Exception;


//    HSU完成按钮
@Update("update Rctest set endTime=#{result1a} where id=#{id};update Rctest set complete=1 where id=#{id};update Rctest set spbh=0 where id=#{id};update Rctest set number=0 where id=#{id};update Rctest set number=number-1 where number>#{number} and state=1 and complete='0' and factory='CSTC' and (TestItem='HSU' or TestItem='LSU' or TestItem='Flatspot')")
   public void updateCompleteHSU(@Param("number") int number, @Param("id") String id,@Param("result1a")String result1a)throws  Exception;

    //  HSU实验室向上
    @Update("update Rctest set number=number+1 where (TestItem='HSU' or TestItem='LSU' or TestItem='Flatspot') and factory='CSTC' and number>=#{numberS} and number < #{number};update Rctest set number=#{numberS} where id=#{id}")
   public void TestNumberHSU(@Param("numberS") int number, @Param("number") int numberS, @Param("id") String id)throws Exception;

    //  HSU实验室向下
    @Update("update Rctest set number=number-1 where (TestItem='HSU' or TestItem='LSU' or TestItem='Flatspot') and factory='CSTC' and number<=#{numberS} and number > #{number};update Rctest set number=#{numberS} where id=#{id}")
  public   void TestNumberDownHSU(@Param("numberS") int number, @Param("number") int numberS, @Param("id") String id)throws  Exception;

    //  查询HSU已排并且没有开始的(按照number顺序)
    @Select("select * from Rctest  where (TestItem='HSU' or TestItem='LSU' or TestItem='Flatspot') and factory='CSTC'  and state=1 and cancel=0 and complete=0 and spbh=0 and red=0 order by number")
    List<Test> findTestByStateNoZuoHSU()throws Exception;


    //    HSU实验室已排批量操作
    @Update("update Rctest set number= number-1 where (TestItem='HSU' or TestItem='LSU' or TestItem='Flatspot') and factory='CSTC' and number<=#{numberS} and number >#{nubmerOld};update Rctest set number=#{numberS} where id=#{idID}")
    void PiLiangOperateDownHSU(@Param("numberS")int numberS, @Param("nubmerOld")int nubmerOld, @Param("idID")String idID);
    @Update("update Rctest set number=number+1 where (TestItem='HSU' or TestItem='LSU' or TestItem='Flatspot') and factory='CSTC' and number>=#{numberS} and number < #{nubmerOld};update Rctest set number=#{numberS} where id=#{idID}")
    void PiLiangOperateUpHSU(@Param("numberS")int numberS, @Param("nubmerOld")int nubmerOld, @Param("idID")String idID);

    //HSU取消已排  进入待排
    @Update("update Rctest set number=0 where id=#{aid};update Rctest set state=0 where id=#{aid};update Rctest set number=number-1 where number>#{anumber} and (TestItem='HSU' or TestItem='LSU' or TestItem='Flatspot') and factory='CSTC'")
    void JinRuDaiPaiHSU(@Param("anumber") String anumber, @Param("aid") String aid)throws Exception;

    //  HSU实验室已排取消
    @Update("update Rctest set cancellationTime=#{result1a} where id=#{id};update Rctest set cancel='1'where id=#{id};update Rctest set number=number-1 where (TestItem='HSU' or TestItem='LSU' or TestItem='Flatspot') and factory='CSTC' and number>#{number};update Rctest set number='0'where id=#{id}")
    public    void updateCancelHSU(@Param("id") String id, @Param("number")int number, @Param("result1a")String result1a)throws  Exception;

    //        HSU去查询数据库有几个已经开始不需要进行循环的
    @Select("select COUNT(*) FROM Rctest where (TestItem='HSU' or TestItem='LSU' or TestItem='Flatspot') and factory='CSTC'  and state=1 and cancel=0 and complete=0 and (spbh=1 or red=1)")
    int findByspbhOneHSU()throws Exception;

    //        去数据库把没有循环到的已经排好的数据变成待排(HSU)
    @Update("update Rctest set state=0,number=0 where number>=#{number} and (TestItem='HSU' or TestItem='LSU' or TestItem='Flatspot') and factory='CSTC' and cancel=0 and complete=0")
    void updateHSUDaiPai(int number)throws Exception;

    //        HSU开始的时候把错误信息删除(先查id)
    @Select("select id from Rctest where number=#{number} and (TestItem='HSU' or TestItem='LSU' or TestItem='Flatspot') and factory='CSTC'")
    String findTestIdHSU(int number)throws Exception;

    //        HSU开始的时候把颜色改成0
    @Update("update Rctest set red=0 where id=#{id} and (TestItem='HSU' or TestItem='LSU' or TestItem='Flatspot') and factory='CSTC'")
    void updateredHSU(String id)throws Exception;

    //       HSU 改变隐藏按钮number
    @Update("update Rctest set spbh=1 where number=#{number} and (TestItem='HSU' or TestItem='LSU' or TestItem='Flatspot') and factory='CSTC'")
    void spbhNumberHSU(int number)throws Exception;

    //   HSU查询已排 并且是查询还没有开始的已排  就是当前按钮number的后面的已经排的
    @Select("select * from  Rctest where number>=#{number} and (TestItem='HSU' or TestItem='LSU' or TestItem='Flatspot') and factory='CSTC' and state=1 and cancel=0 and complete=0 order by number")
    @Results({
            @Result(id = true,column ="id" ,property ="id"),
            @Result(column ="number" ,property ="number"),
            @Result(column ="TestItem" ,property ="TestItem"),
            @Result(column ="sampleID" ,property ="sampleID"),
            @Result(column ="Standard" ,property ="Standard"),
            @Result(column ="UsageRim" ,property ="UsageRim"),
            @Result(column ="pt" ,property ="pt"),
            @Result(column ="Fz" ,property ="Fz"),
            @Result(column ="Vr" ,property ="Vr"),
            @Result(column ="CA" ,property ="CA"),
            @Result(column ="SA" ,property ="SA"),
            @Result(column ="PA" ,property ="PA"),
            @Result(column ="Mileage" ,property ="Mileage"),
            @Result(column ="FRAngleDeg" ,property ="FRAngleDeg"),
            @Result(column ="FFTOrder" ,property ="FFTOrder"),
            @Result(column ="FlatSpotPt" ,property ="FlatSpotPt"),
            @Result(column ="FlatSpotFz" ,property ="FlatSpotFz"),
            @Result(column ="ExpectedDate" ,property ="ExpectedDate"),
            @Result(column ="state" ,property ="state"),
            @Result(column ="complete" ,property ="complete"),
            @Result(column ="cancel" ,property ="cancel"),
            @Result(column ="red" ,property ="red"),
            @Result(column ="spbh" ,property ="spbh"),
            @Result(column ="caozuo" ,property ="caozuo"),
            @Result(column ="kaishi" ,property ="kaishi"),
            @Result(column ="endTime" ,property ="endTime"),
            @Result(column ="cancellationTime" ,property ="cancellationTime"),
            @Result(column ="factory" ,property ="factory"),
            @Result(column = "tyreId",property = "tyre",javaType = Tyre.class,one = @One(select = "com.maxxis.dao.TyreDao.findById"))

    })
    List<Test> findByNoStateNumberHSU(int number)throws Exception;

    //        HSU把数据库里面的操作更改数据
    @Update("update Rctest set caozuo=#{spbhOne} where (TestItem='HSU' or TestItem='LSU' or TestItem='Flatspot') and factory='CSTC'")
    void updateCaoZuoHSU(int spbhOne)throws Exception;

    //   HSU实验室取消开始
    @Update("update Rctest set spbh=0,caozuo=0 where (TestItem='HSU' or TestItem='LSU' or TestItem='Flatspot') and factory='CSTC' and state='1'and complete='0'and cancel='0' ")
    void quxiaokaishiHSU()throws Exception;

    //    HSU需求编号搜索(待排)
    @Select("select number,\n" +
            "       t1.id  as idRctest,TestItem,sampleID,Standard,UsageRim,pt,Fz,Vr,CA,SA,PA,Mileage,FRangleDeg,FFTOrder,FlatSpotPt,FlatSpotFz,ExpectedDate,state,complete,cancel,red,spbh,caozuo,kaishi,endTime,cancellationTime,factory,t2.id  as idRctyre,Size,t3.id as idRccliet,RequestId,Project,Purpose,ProjectEngineer,PriorityDes,RequestTime,TimeA,TestTime,ContactExt,Remark,PriorityDesNumber,sampleDisposal\n" +
            "  from Rctest t1,Rctyre t2,Rccliet t3 where t1.tyreId =t2.id and t2.clietId=t3.id and (TestItem='HSU' or TestItem='LSU' or TestItem='Flatspot') and factory='CSTC' and state='0'and cancel='0' and complete='0' and RequestId like '%${value}%'")
    List<TestClietTyre> findByTestRequestIdDaiPaiHSU(String requestId)throws Exception;

    //    HSU实验室待排批量操作（插位排程）
    @Update("update Rctest set number=number+1 where number>=#{numberS} and (TestItem='HSU' or TestItem='LSU' or TestItem='Flatspot') and factory='CSTC';update Rctest set number=#{numberS} where id=#{idA};update Rctest set state='1' where id=#{idA}")
    void TestNumberChaHSU(@Param("numberS") int numberS, @Param("idA") String idA);


    //    3D实验室完成按钮
    @Update("update Rctest set endTime=#{result1a} where id=#{id};update Rctest set complete=1 where id=#{id};update Rctest set spbh=0 where id=#{id};update Rctest set number=0 where id=#{id};update Rctest set number=number-1 where number>#{number} and state=1 and complete='0'and (TestItem='Profile Scan(Unload)' or TestItem='Profile Scan(Rim)' or TestItem='Profile Scan(NonRim)'  or TestItem='Profile Scan(Loaded)' or TestItem='Profile Scan(Wear)') and factory='CSTC'")
  public void updateComplete3D(@Param("id") String id,@Param("number") int number, @Param("result1a") String result1a)throws  Exception;

    //  3D实验室向上
    @Update("update Rctest set number=number+1 where (TestItem='Profile Scan(Unload)' or TestItem='Profile Scan(Rim)' or TestItem='Profile Scan(NonRim)'  or TestItem='Profile Scan(Loaded)' or TestItem='Profile Scan(Wear)') and factory='CSTC' and number>=#{numberS} and number < #{number};update Rctest set number=#{numberS} where id=#{id}")
    void TestNumber3D(@Param("numberS") int number, @Param("number") int numberS, @Param("id") String id);

    //  3D实验室向下
    @Update("update Rctest set number=number-1 where (TestItem='Profile Scan(Unload)' or TestItem='Profile Scan(Rim)' or TestItem='Profile Scan(NonRim)'  or TestItem='Profile Scan(Loaded)' or TestItem='Profile Scan(Wear)') and factory='CSTC' and number<=#{numberS} and number > #{number};update Rctest set number=#{numberS} where id=#{id}")
    void TestNumberDown3D(@Param("numberS") int number, @Param("number") int numberS, @Param("id") String id);

    //    3D实验室已排取消
    @Update("update Rctest set cancellationTime=#{result1a} where id=#{id};update Rctest set cancel='1'where id=#{id};update Rctest set number=number-1 where (TestItem='Profile Scan(Unload)' or TestItem='Profile Scan(Rim)' or TestItem='Profile Scan(NonRim)'  or TestItem='Profile Scan(Loaded)' or TestItem='Profile Scan(Wear)') and factory='CSTC' and number>#{number};update Rctest set number='0'where id=#{id}")
   public   void updateCancel3D(@Param("id") String id, @Param("number")int number,@Param("result1a") String result1a)throws  Exception;

    //    3D实验室待排
    @Select("select number,\n" +
            "       t1.id  as idRctest,TestItem,sampleID,Standard,UsageRim,pt,Fz,Vr,CA,SA,PA,Mileage,FRangleDeg,FFTOrder,FlatSpotPt,FlatSpotFz,ExpectedDate,state,complete,cancel,red,spbh,caozuo,kaishi,endTime,cancellationTime,factory,t2.id  as idRctyre,Size,t3.id as idRccliet,RequestId,Project,Purpose,ProjectEngineer,PriorityDes,RequestTime,TimeA,TestTime,ContactExt,Remark,PriorityDesNumber,sampleDisposal\n" +
            "  from Rctest t1,Rctyre t2,Rccliet t3 where t1.tyreId =t2.id and t2.clietId=t3.id and (TestItem='Profile Scan(Unload)' or TestItem='Profile Scan(Rim)' or TestItem='Profile Scan(NonRim)'  or TestItem='Profile Scan(Loaded)' or TestItem='Profile Scan(Wear)') and factory='CSTC' and state='0'and cancel='0' and complete='0' order by RequestTime,PriorityDesNumber,Project,requestId")
    public   List<TestClietTyre> findByState3D()throws  Exception;

    //        查询3D已排现在一共的已排记录
    @Select("select count(*) from Rctest where (TestItem='Profile Scan(Unload)' or TestItem='Profile Scan(Rim)' or TestItem='Profile Scan(NonRim)'  or TestItem='Profile Scan(Loaded)' or TestItem='Profile Scan(Wear)') and factory='CSTC' and state='1'and complete='0'and cancel='0'")
    int findStateCount3D()throws Exception;


    //        查询3D待排现在一共的记录
    @Select("select count(*) from  Rctest t1,Rctyre t2,Rccliet t3 where t1.tyreId =t2.id and t2.clietId=t3.id and  (TestItem='Profile Scan(Unload)' or TestItem='Profile Scan(Rim)' or TestItem='Profile Scan(NonRim)'  or TestItem='Profile Scan(Loaded)' or TestItem='Profile Scan(Wear)') and factory='CSTC' and state='0'and cancel='0' and complete='0'")
    int findCountDai3D()throws Exception;

    //  3D把已经过的时间的past变成1
    @Update("update Rcworktime3D set past=1 where getoffwork<=#{result1a} and past=0")
    void updatePast3D(long result1a)throws Exception;

    //      3D查询出没有过去的工时
    @Select("select * from Rcworktime3D where past=0 order by gotowork")
    List<WorkTime3D> findByRcworktime3D()throws Exception;

    //       3D时间搜索按钮
    List<WorkTime3D> findWorkTimeSouSuo3D(@Param("gotoworkLingChen") long gotoworkLingChen, @Param("gotoworkWanShang") long gotoworkWanShang);

    //    3D实验室完成查询（按时间查询）
    @Select("select * from Rctest where (TestItem='Profile Scan(Unload)' or TestItem='Profile Scan(Rim)' or TestItem='Profile Scan(NonRim)'  or TestItem='Profile Scan(Loaded)' or TestItem='Profile Scan(Wear)') and factory='CSTC'  and complete='1'and endTime > #{start} and endTime < #{end}  order by endTime")
    @Results({
            @Result(id = true,column ="id" ,property ="id"),
            @Result(column ="number" ,property ="number"),
            @Result(column ="TestItem" ,property ="TestItem"),
            @Result(column ="sampleID" ,property ="sampleID"),
            @Result(column ="Standard" ,property ="Standard"),
            @Result(column ="UsageRim" ,property ="UsageRim"),
            @Result(column ="pt" ,property ="pt"),
            @Result(column ="Fz" ,property ="Fz"),
            @Result(column ="Vr" ,property ="Vr"),
            @Result(column ="CA" ,property ="CA"),
            @Result(column ="SA" ,property ="SA"),
            @Result(column ="PA" ,property ="PA"),
            @Result(column ="Mileage" ,property ="Mileage"),
            @Result(column ="FRAngleDeg" ,property ="FRAngleDeg"),
            @Result(column ="FFTOrder" ,property ="FFTOrder"),
            @Result(column ="FlatSpotPt" ,property ="FlatSpotPt"),
            @Result(column ="FlatSpotFz" ,property ="FlatSpotFz"),
            @Result(column ="ExpectedDate" ,property ="ExpectedDate"),
            @Result(column ="state" ,property ="state"),
            @Result(column ="complete" ,property ="complete"),
            @Result(column ="cancel" ,property ="cancel"),
            @Result(column ="red" ,property ="red"),
            @Result(column ="spbh" ,property ="spbh"),
            @Result(column ="caozuo" ,property ="caozuo"),
            @Result(column ="kaishi" ,property ="kaishi"),
            @Result(column ="endTime" ,property ="endTime"),
            @Result(column ="cancellationTime" ,property ="cancellationTime"),
            @Result(column ="factory" ,property ="factory"),
            @Result(column = "tyreId",property = "tyre",javaType = Tyre.class,one = @One(select = "com.maxxis.dao.TyreDao.findById"))

    })
    List<Test> findByendTimeComplete3D(@Param("start") String start, @Param("end") String end);

    //  3D实验室取消查询（按时间查询）
    @Select("select * from Rctest where (TestItem='Profile Scan(Unload)' or TestItem='Profile Scan(Rim)' or TestItem='Profile Scan(NonRim)'  or TestItem='Profile Scan(Loaded)' or TestItem='Profile Scan(Wear)') and factory='CSTC' and cancel='1'and cancellationTime > #{startQuXiao} and cancellationTime < #{endQuXiao} order by cancellationTime")
    @Results({
            @Result(id = true,column ="id" ,property ="id"),
            @Result(column ="number" ,property ="number"),
            @Result(column ="TestItem" ,property ="TestItem"),
            @Result(column ="sampleID" ,property ="sampleID"),
            @Result(column ="Standard" ,property ="Standard"),
            @Result(column ="UsageRim" ,property ="UsageRim"),
            @Result(column ="pt" ,property ="pt"),
            @Result(column ="Fz" ,property ="Fz"),
            @Result(column ="Vr" ,property ="Vr"),
            @Result(column ="CA" ,property ="CA"),
            @Result(column ="SA" ,property ="SA"),
            @Result(column ="PA" ,property ="PA"),
            @Result(column ="Mileage" ,property ="Mileage"),
            @Result(column ="FRAngleDeg" ,property ="FRAngleDeg"),
            @Result(column ="FFTOrder" ,property ="FFTOrder"),
            @Result(column ="FlatSpotPt" ,property ="FlatSpotPt"),
            @Result(column ="FlatSpotFz" ,property ="FlatSpotFz"),
            @Result(column ="ExpectedDate" ,property ="ExpectedDate"),
            @Result(column ="state" ,property ="state"),
            @Result(column ="complete" ,property ="complete"),
            @Result(column ="cancel" ,property ="cancel"),
            @Result(column ="red" ,property ="red"),
            @Result(column ="spbh" ,property ="spbh"),
            @Result(column ="caozuo" ,property ="caozuo"),
            @Result(column ="kaishi" ,property ="kaishi"),
            @Result(column ="endTime" ,property ="endTime"),
            @Result(column ="cancellationTime" ,property ="cancellationTime"),
            @Result(column ="factory" ,property ="factory"),
            @Result(column = "tyreId",property = "tyre",javaType = Tyre.class,one = @One(select = "com.maxxis.dao.TyreDao.findById"))

    })
    List<Test> findByCancellationTimeCancel3D(@Param("startQuXiao") String startQuXiao, @Param("endQuXiao") String endQuXiao);

    //            3D查询工时
    @Select("select * from Rcworktime3D order by gotowork")
    List<WorkTime3D> findworktime3D()throws Exception;

    // 获取3D实验室目前有没有已经排序的 如果已经排了2条数据  那么一键排程的时候number就从3开始
    @Select("SELECT COUNT(*)+1 FROM Rctest  WHERE number IS NOT NULL and number!=0 and (TestItem='Profile Scan(Unload)' or TestItem='Profile Scan(Rim)' or TestItem='Profile Scan(NonRim)'  or TestItem='Profile Scan(Loaded)' or TestItem='Profile Scan(Wear)') and factory='CSTC'")
    int findByTestnumber3D()throws Exception;

    //3D查询出目前有没有已经排好的计划
    @Select("SELECT COUNT(*) FROM Rctest  WHERE number IS NOT NULL and number!=0 and (TestItem='Profile Scan(Unload)' or TestItem='Profile Scan(Rim)' or TestItem='Profile Scan(NonRim)'  or TestItem='Profile Scan(Loaded)' or TestItem='Profile Scan(Wear)') and factory='CSTC'")
    int findNumber3D()throws Exception;

    //      3D实验室一键排程
    @Update("update Rctest set number=#{number3D} where id=#{id};update Rctest set state='1' where id=#{id};update Rctest set ExpectedDate=#{result1a} where id=#{id}")
    void update3DExpectedDate(@Param("id") String id, @Param("result1a") String result1a, @Param("number3D") int number3D);

    //        查出3D已排最后一条数据
    @Select("select * from Rctest where number=#{count} and (TestItem='Profile Scan(Unload)' or TestItem='Profile Scan(Rim)' or TestItem='Profile Scan(NonRim)'  or TestItem='Profile Scan(Loaded)' or TestItem='Profile Scan(Wear)') and factory='CSTC'")
    Test findBycount3D(int count)throws Exception;

    //  查询3D已排并且没有再做的(按照number顺序)
    @Select("select * from Rctest  where (TestItem='Profile Scan(Unload)' or TestItem='Profile Scan(Rim)' or TestItem='Profile Scan(NonRim)'  or TestItem='Profile Scan(Loaded)' or TestItem='Profile Scan(Wear)') and factory='CSTC'  and state=1 and cancel=0 and complete=0 and spbh=0 and red=0 order by number")
    List<Test> findTestByStateNoZuo3D()throws Exception;

    //    3D排程刷新
    @Update("update Rctest set ExpectedDate=#{result1a} where number=#{numberA} and (TestItem='Profile Scan(Unload)' or TestItem='Profile Scan(Rim)' or TestItem='Profile Scan(NonRim)'  or TestItem='Profile Scan(Loaded)' or TestItem='Profile Scan(Wear)') and factory='CSTC'")
    void update3DExpectedDateUP(@Param("numberA") Integer numberA, @Param("result1a") String result1a)throws Exception;

    //       3D去查询数据库有几个已经开始不需要进行循环的
    @Select("select COUNT(*) FROM Rctest where (TestItem='Profile Scan(Unload)' or TestItem='Profile Scan(Rim)' or TestItem='Profile Scan(NonRim)'  or TestItem='Profile Scan(Loaded)' or TestItem='Profile Scan(Wear)') and factory='CSTC'  and state=1 and cancel=0 and complete=0 and (spbh=1 or red=1)")
    int findByspbhOne3D()throws Exception;

    //        去数据库把没有循环到的已经排好的数据变成待排(3D)
    @Update("update Rctest set state=0,number=0 where number>=#{number} and (TestItem='Profile Scan(Unload)' or TestItem='Profile Scan(Rim)' or TestItem='Profile Scan(NonRim)'  or TestItem='Profile Scan(Loaded)' or TestItem='Profile Scan(Wear)') and factory='CSTC' and cancel=0 and complete=0")
    void update3DDaiPai(int number)throws Exception;

    //        3D开始的时候把错误信息删除(先查id)
    @Select("select id from Rctest where number=#{number} and (TestItem='Profile Scan(Unload)' or TestItem='Profile Scan(Rim)' or TestItem='Profile Scan(NonRim)'  or TestItem='Profile Scan(Loaded)' or TestItem='Profile Scan(Wear)') and factory='CSTC'")
    String findTestId3D(int number)throws Exception;

    //      3D开始的时候把颜色改成0
    @Update("update Rctest set red=0 where id=#{id}")
    void updatered3D(String id)throws Exception;

    //     3D改变隐藏按钮number
    @Update("update Rctest set spbh=1 where number=#{number} and (TestItem='Profile Scan(Unload)' or TestItem='Profile Scan(Rim)' or TestItem='Profile Scan(NonRim)'  or TestItem='Profile Scan(Loaded)' or TestItem='Profile Scan(Wear)') and factory='CSTC'")
    void spbhNumber3D(int number)throws Exception;

    //   查询已排 并且是查询还没有开始的已排  就是当前按钮number的后面的已经排的 3D
    @Select("select * from  Rctest where number>=#{number} and (TestItem='Profile Scan(Unload)' or TestItem='Profile Scan(Rim)' or TestItem='Profile Scan(NonRim)'  or TestItem='Profile Scan(Loaded)' or TestItem='Profile Scan(Wear)') and factory='CSTC'  and state=1 and cancel=0 and complete=0 order by number")
    @Results({
            @Result(id = true,column ="id" ,property ="id"),
            @Result(column ="number" ,property ="number"),
            @Result(column ="TestItem" ,property ="TestItem"),
            @Result(column ="sampleID" ,property ="sampleID"),
            @Result(column ="Standard" ,property ="Standard"),
            @Result(column ="UsageRim" ,property ="UsageRim"),
            @Result(column ="pt" ,property ="pt"),
            @Result(column ="Fz" ,property ="Fz"),
            @Result(column ="Vr" ,property ="Vr"),
            @Result(column ="CA" ,property ="CA"),
            @Result(column ="SA" ,property ="SA"),
            @Result(column ="PA" ,property ="PA"),
            @Result(column ="Mileage" ,property ="Mileage"),
            @Result(column ="FRAngleDeg" ,property ="FRAngleDeg"),
            @Result(column ="FFTOrder" ,property ="FFTOrder"),
            @Result(column ="FlatSpotPt" ,property ="FlatSpotPt"),
            @Result(column ="FlatSpotFz" ,property ="FlatSpotFz"),
            @Result(column ="ExpectedDate" ,property ="ExpectedDate"),
            @Result(column ="state" ,property ="state"),
            @Result(column ="complete" ,property ="complete"),
            @Result(column ="cancel" ,property ="cancel"),
            @Result(column ="red" ,property ="red"),
            @Result(column ="spbh" ,property ="spbh"),
            @Result(column ="caozuo" ,property ="caozuo"),
            @Result(column ="kaishi" ,property ="kaishi"),
            @Result(column ="endTime" ,property ="endTime"),
            @Result(column ="cancellationTime" ,property ="cancellationTime"),
            @Result(column ="factory" ,property ="factory"),
            @Result(column = "tyreId",property = "tyre",javaType = Tyre.class,one = @One(select = "com.maxxis.dao.TyreDao.findById"))

    })
    List<Test> findByNoStateNumber3D(int number)throws Exception;

    //        3D把数据库里面的操作更改数据
    @Update("update Rctest set caozuo=#{spbhOne} where  (TestItem='Profile Scan(Unload)' or TestItem='Profile Scan(Rim)' or TestItem='Profile Scan(NonRim)'  or TestItem='Profile Scan(Loaded)' or TestItem='Profile Scan(Wear)') and factory='CSTC'")
    void updateCaoZuo3D(int spbhOne)throws Exception;

    //   3D实验室取消开始
    @Update("update Rctest set spbh=0,caozuo=0 where (TestItem='Profile Scan(Unload)' or TestItem='Profile Scan(Rim)' or TestItem='Profile Scan(NonRim)'  or TestItem='Profile Scan(Loaded)' or TestItem='Profile Scan(Wear)') and factory='CSTC' and state='1'and complete='0'and cancel='0' ")
    void quxiaokaishi3D()throws Exception;

    //  3D实验室已排批量操作
    @Update("update Rctest set number= number-1 where (TestItem='Profile Scan(Unload)' or TestItem='Profile Scan(Rim)' or TestItem='Profile Scan(NonRim)'  or TestItem='Profile Scan(Loaded)' or TestItem='Profile Scan(Wear)') and factory='CSTC' and number<=#{numberS} and number >#{nubmerOld};update Rctest set number=#{numberS} where id=#{idID}")
    void PiLiangOperateDown3D(@Param("numberS")int numberS, @Param("nubmerOld")int nubmerOld,  @Param("idID")String idID);
    @Update("update Rctest set number=number+1 where (TestItem='Profile Scan(Unload)' or TestItem='Profile Scan(Rim)' or TestItem='Profile Scan(NonRim)'  or TestItem='Profile Scan(Loaded)' or TestItem='Profile Scan(Wear)') and factory='CSTC' and number>=#{numberS} and number < #{nubmerOld};update Rctest set number=#{numberS} where id=#{idID}")
    void PiLiangOperateUp3D(@Param("numberS")int numberS, @Param("nubmerOld")int nubmerOld,  @Param("idID")String idID);

    //3D取消已排  进入待排
    @Update("update Rctest set number=0 where id=#{aid};update Rctest set state=0 where id=#{aid};update Rctest set number=number-1 where number>#{anumber} and (TestItem='Profile Scan(Unload)' or TestItem='Profile Scan(Rim)' or TestItem='Profile Scan(NonRim)'  or TestItem='Profile Scan(Loaded)' or TestItem='Profile Scan(Wear)') and factory='CSTC'")
    void JinRuDaiPai3D(@Param("anumber") String anumber, @Param("aid") String aid)throws Exception;

    //   3D实验室顺位排程
    @Update("update Rctest set number=(SELECT COUNT(*)+1 FROM Rctest  WHERE number IS NOT NULL and number!=0 and (TestItem='Profile Scan(Unload)' or TestItem='Profile Scan(Rim)' or TestItem='Profile Scan(NonRim)'  or TestItem='Profile Scan(Loaded)' or TestItem='Profile Scan(Wear)') and factory='CSTC') WHERE id=#{id};update Rctest set state='1' where id=#{id}")
    void updateState3D(String id)throws Exception;

    //  3D实验室待排插位
    @Update("update Rctest set number=number+1 where number>=#{numberB} and (TestItem='Profile Scan(Unload)' or TestItem='Profile Scan(Rim)' or TestItem='Profile Scan(NonRim)'  or TestItem='Profile Scan(Loaded)' or TestItem='Profile Scan(Wear)') and factory='CSTC' ;update Rctest set number=#{numberB} where id=#{id};update Rctest set state='1' where id=#{id}")
    void TestNumberDaiCha3D(@Param("numberB") int numberB, @Param("id") String id)throws Exception;

    //    3D需求编号搜索(待排)
    @Select("select number,\n" +
            "       t1.id  as idRctest,TestItem,sampleID,Standard,UsageRim,pt,Fz,Vr,CA,SA,PA,Mileage,FRangleDeg,FFTOrder,FlatSpotPt,FlatSpotFz,ExpectedDate,state,complete,cancel,red,spbh,caozuo,kaishi,endTime,cancellationTime,factory,t2.id  as idRctyre,Size,t3.id as idRccliet,RequestId,Project,Purpose,ProjectEngineer,PriorityDes,RequestTime,TimeA,TestTime,ContactExt,Remark,PriorityDesNumber,sampleDisposal\n" +
            "  from Rctest t1,Rctyre t2,Rccliet t3 where t1.tyreId =t2.id and t2.clietId=t3.id and (TestItem='Profile Scan(Unload)' or TestItem='Profile Scan(Rim)' or TestItem='Profile Scan(NonRim)'  or TestItem='Profile Scan(Loaded)' or TestItem='Profile Scan(Wear)') and factory='CSTC' and state='0'and cancel='0' and complete='0' and RequestId like '%${value}%'")
    List<TestClietTyre> findByTestRequestIdDaiPai3D(String requestId)throws Exception;

    //    SP实验室完成
    @Update("update Rctest set endTime=#{result1a} where id=#{id};update Rctest set complete=1 where id=#{id};update Rctest set spbh=0 where id=#{id};update Rctest set number=0 where id=#{id};update Rctest set number=number-1 where number>#{number} and state=1 and complete='0'and TestItem='Footprint' and factory='CSTC'")
   public void updateCompleteSP(@Param("number") int number, @Param("id") String id , @Param("result1a") String result1a)throws  Exception;

    //        查询SP已排现在一共的已排记录
    @Select("select count(*) from Rctest where TestItem='Footprint' and factory='CSTC' and state='1'and complete='0'and cancel='0'")
      public   int findStateCount()throws  Exception;


    //        查询出SP待排一共多少条记录
    @Select("select count(*) from  Rctest t1,Rctyre t2,Rccliet t3 where t1.tyreId =t2.id and t2.clietId=t3.id and TestItem='Footprint' and factory='CSTC' and state='0'and cancel='0' and complete='0'")
    public    int findCountDai()throws  Exception;

    //   查出sp已排最后一条数据
    @Select("select * from Rctest where number=#{count} and TestItem='Footprint' and factory='CSTC'")
    public Test findBycount(int count)throws  Exception;

    //   SP实验室向上
    @Update("update Rctest set number=number+1 where TestItem='Footprint' and factory='CSTC' and number>=#{numberS} and number < #{number};update Rctest set number=#{numberS} where id=#{id}")
    public  void TestNumberSPP(@Param("numberS") int number, @Param("number") int numberS, @Param("id") String id);


    //   SP实验室向下
    @Update("update Rctest set number=number-1 where TestItem='Footprint' and factory='CSTC' and number<=#{numberS} and number > #{number};update Rctest set number=#{numberS} where id=#{id}")
 public    void TestNumberDownSPP(@Param("numberS") int number, @Param("number") int numberS, @Param("id") String id);
    //       SP 向下调整批量处理
    @Update("update Rctest set number= number-1 where TestItem='Footprint' and factory='CSTC' and number<=#{numberS} and number >#{nubmerOld};update Rctest set number=#{numberS} where id=#{idID}")
    public void PiLiangOperateDown(@Param("numberS")int numberS, @Param("nubmerOld")int nubmerOld, @Param("idID")String idID)throws  Exception;

    //      SP  向上调整批量处理
    @Update("update Rctest set number=number+1 where TestItem='Footprint' and factory='CSTC' and number>=#{numberS} and number < #{nubmerOld};update Rctest set number=#{numberS} where id=#{IdID}")
  public   void PiLiangOperateUp(@Param("numberS") int numberS, @Param("nubmerOld")int nubmerOld,@Param("IdID") String IdID)throws Exception;

    //SP取消已排  进入待排
    @Update("update Rctest set number=0 where id=#{aid};update Rctest set state=0 where id=#{aid};update Rctest set number=number-1 where number>#{anumber} and TestItem='Footprint' and factory='CSTC'")
    public void JinRuDaiPai(@Param("anumber")String anumber, @Param("aid")String aid)throws  Exception;

    //SP已排取消
    @Update("update Rctest set cancellationTime=#{result1a} where id=#{id};update Rctest set cancel='1'where id=#{id};update Rctest set number='0'where id=#{id};update Rctest set number=number-1 where TestItem='Footprint' and factory='CSTC' and number>#{number}")
 public  void updateCancelSP(@Param("id") String id, @Param("number")int number, @Param("result1a")String result1a)throws  Exception;


    //    SP实验室待排计划
    @Select("select number,\n" +
            "       t1.id  as idRctest,TestItem,sampleID,Standard,UsageRim,pt,Fz,Vr,CA,SA,PA,Mileage,FRangleDeg,FFTOrder,FlatSpotPt,FlatSpotFz,ExpectedDate,state,complete,cancel,red,spbh,caozuo,kaishi,endTime,cancellationTime,factory,t2.id  as idRctyre,Size,t3.id as idRccliet,RequestId,Project,Purpose,ProjectEngineer,PriorityDes,RequestTime,TimeA,TestTime,ContactExt,Remark,PriorityDesNumber,sampleDisposal\n" +
            "  from Rctest t1,Rctyre t2,Rccliet t3 where t1.tyreId =t2.id and t2.clietId=t3.id and  TestItem='Footprint' and factory='CSTC' and state='0'and cancel='0' and complete='0' order by RequestTime,PriorityDesNumber,Project,requestId")
    public  List<TestClietTyre> findByStateSP();

    //        先把紧急程度赋予数字  便于排序
    @Update("update Rccliet set PriorityDesNumber=1 where PriorityDes='Urgent';update Rccliet set PriorityDesNumber=2 where PriorityDes='Normal'")
    public   void updatePriorityDesNumber()throws  Exception;

    //    SP实验室顺位排程
    @Update("update Rctest set number=(SELECT COUNT(*)+1 FROM Rctest  WHERE number IS NOT NULL and number!=0 and TestItem='Footprint' ) WHERE id=#{id};update Rctest set state='1' where id=#{id}")
    public   void updateStateSP(String id);


    //    SP实验室插位
    @Update("update Rctest set number=number+1 where number>=#{numberB} and TestItem='Footprint' and factory='CSTC';update Rctest set number=#{numberB} where id=#{id};update Rctest set state='1' where id=#{id}")
   public void TestNumberSP(@Param("numberB")int numberB, @Param("id") String id)throws  Exception;

    //  实验室查询法规对应时间
    @Select("select * from RctimeTest where standard=#{standard} and TestItem=#{TestItem}")
     public List<TimeTest> findDataStandard(@Param("standard") String standard, @Param("TestItem") String TestItem)throws  Exception;

    //查询工时
    @Select("select * from RcworktimeHSU order by gotowork")
   public List<WorkTimeHSU> findworktime()throws Exception;


    //   获取HSU实验室目前有没有已经排序的 如果已经排了2条数据  那么一键排程的时候number就从3开始
    @Select("SELECT COUNT(*)+1 FROM Rctest  WHERE number IS NOT NULL and number!=0 and (TestItem='HSU' or TestItem='LSU' or TestItem='Flatspot') and factory='CSTC'")
  public  int findByTestnumber()throws Exception;

//    一键排程
    @Update("update Rctest set number=#{numberHSU} where id=#{id};update Rctest set state='1' where id=#{id};update Rctest set ExpectedDate=#{result1a} where id=#{id}")
    void updateHSUExpectedDate(@Param("id")String id, @Param("result1a") String result1a, @Param("numberHSU") int numberHSU);


    //    HSU实验室时间查询
    @Select("select * from RcworktimeHSU")
    public   List<WorkTimeHSU> findWorkTime();

    //        清除SHU已排数据
    @Update("update Rctest  set number=0,state='0' where (TestItem='HSU'or TestItem='LSU'or TestItem='Flatspot') and factory='CSTC'")
    public   void HSUclear()throws  Exception;


    //  查询hsu已排(按照number顺序)
    @Select("select * from Rctest  where (TestItem='HSU'or TestItem='LSU'or TestItem='Flatspot') and factory='CSTC' and state=1 and cancel=0 and complete=0 order by number")
      public   List<Test> findTestByState()throws  Exception;


    //HSU一键排程向上调整  （调整时间）
    @Update("update Rctest set ExpectedDate=#{result1a} where number=#{numberA} and (TestItem='HSU'or TestItem='LSU'or TestItem='Flatspot') and factory='CSTC'")
   public void updateHSUExpectedDateUP(@Param("numberA") Integer numberA, @Param("result1a") String result1a);

    //HSU查询出目前有没有已经排好的计划
    @Select("SELECT COUNT(*) FROM Rctest  WHERE number IS NOT NULL and number!=0 and (TestItem='HSU' or TestItem='LSU' or TestItem='Flatspot') and factory='CSTC'")
     public int findNumber();


    // HSU 把待排全部变成已排
    @Update("update Rctest set number=#{nuMBer} where id=#{lid};update Rctest set state='1' where id=#{lid}")
     public   void UpdateTest(@Param("nuMBer") int nuMBer, @Param("lid") String lid)throws  Exception;


    //HSU开始   先把开始时间改成现在
    @Update("update RcworktimeHSU set gotowork=#{time1} where id=#{idd}")
   public void UpdateBeginHSU(@Param("idd") int idd,@Param("time1") long time1)throws  Exception;


    //      HSU    根据这个时间去查询数据库对应的id     然后在去把时间改成当前的时间
    @Select("select id from RcworktimeHSU where gotowork=#{day}")
   public int findByGotoworkId(long day)throws  Exception;


    //SP查询出目前有没有已经排好的计划
    @Select("SELECT COUNT(*) FROM Rctest  WHERE number IS NOT NULL and number!=0 and TestItem='Footprint' and factory='CSTC'")
   public int findNumberSP()throws  Exception;



    //  点击开始的时候把数据库里面的caozuo字段全部变成1   这样的话  前端可以控制不操作第一条已经开始的数据
  @Update("update Rctest set caozuo=#{spbhOne} where TestItem='Footprint' and factory='CSTC'")
    public  void updateCaoZuo(int spbhOne)throws  Exception;

    //  点击完成的时候把数据库里面的caozuo字段全部变成0   这样的话  前端可以操作第一条数据
    @Update("update Rctest set caozuo='0' where factory='CSTC'")
    public void updateCaoZuoZoo()throws  Exception;

    //    SP查询工时
    @Select("select * from RcworktimeSP order by gotowork")
  public   List<WorkTimeSp> findworktimeSP()throws  Exception;


    //   SP一键排程
    @Update("update Rctest set number=#{numberSP} where id=#{id};update Rctest set state='1' where id=#{id};update Rctest set ExpectedDate=#{result1a} where id=#{id}")
  public    void updateHSUExpectedDateSP(@Param("id") String id, @Param("result1a") String result1a, @Param("numberSP") int numberSP);

    //    SP实验室取消开始
    @Update("update Rctest set spbh=0,caozuo=0 where TestItem='Footprint' and factory='CSTC' and state='1'and complete='0'and cancel='0' ")
    void quxiaokaishiSP()throws Exception;

    //  把SP待排全部变成已排
    @Update("update Rctest set number=#{nuMBer} where id=#{lid};update Rctest set state='1' where id=#{lid}")
   public void UpdateTestSP(@Param("nuMBer") int nuMBer, @Param("lid") String lid)throws  Exception;

    //       SP 去查询数据库有几个已经开始不需要进行循环的
    @Select("select COUNT(*) FROM Rctest where TestItem='Footprint' and factory='CSTC'  and state=1 and cancel=0 and complete=0 and (spbh=1 or red=1)")
    public     int findByspbhOne()throws  Exception;

    //        去数据库把没有循环到的已经排好的数据变成待排(SP)
    @Update("update Rctest set state=0,number=0 where number>=#{number} and TestItem='Footprint' and factory='CSTC' and cancel=0 and complete=0")
   public void updateSPnumberDaiPai(int number)throws  Exception;

    //  查询sp已排(按照number顺序)
    @Select("select * from Rctest  where TestItem='Footprint' and factory='CSTC'  and state=1 and cancel=0 and complete=0 order by number")
   public List<Test> findTestByStateSP()throws  Exception;

    //  查询sp已排并且没有再做的(按照number顺序)
    @Select("select * from Rctest  where TestItem='Footprint' and factory='CSTC'  and state=1 and cancel=0 and complete=0 and spbh=0 and red=0 order by number")
    public List<Test> findTestByStateNoZuoSP()throws  Exception;


    //SP一键排程向上调整  （调整时间）
    @Update("update Rctest set ExpectedDate=#{result1a} where number=#{numberA} and  TestItem='Footprint' and factory='CSTC'")
  public   void updateHSUExpectedDateUPSP(@Param("numberA") Integer numberA, @Param("result1a") String result1a) throws  Exception;

    //        SP实验室 根据这个时间去查询数据库对应的id     然后在去把时间改成当前的时间
    @Select("select id from RcworktimeSP where gotowork=#{day}")
  public   int findByGotoworkIdSP(long day)throws Exception;

    //        SP更改开始时间
    @Update("update RcworktimeSP set gotowork=#{time1} where id=#{idd}")
    public  void UpdateBeginSP(@Param("idd") int idd, @Param("time1") long time1)throws Exception;

    //   SP试验取消查询
    @Select("select * from Rctest where TestItem='Footprint' and factory='CSTC' and cancel='1'")
    @Results({
            @Result(id = true,column ="id" ,property ="id"),
            @Result(column ="number" ,property ="number"),
            @Result(column ="TestItem" ,property ="TestItem"),
            @Result(column ="sampleID" ,property ="sampleID"),
            @Result(column ="Standard" ,property ="Standard"),
            @Result(column ="UsageRim" ,property ="UsageRim"),
            @Result(column ="pt" ,property ="pt"),
            @Result(column ="Fz" ,property ="Fz"),
            @Result(column ="Vr" ,property ="Vr"),
            @Result(column ="CA" ,property ="CA"),
            @Result(column ="SA" ,property ="SA"),
            @Result(column ="PA" ,property ="PA"),
            @Result(column ="Mileage" ,property ="Mileage"),
            @Result(column ="FRAngleDeg" ,property ="FRAngleDeg"),
            @Result(column ="FFTOrder" ,property ="FFTOrder"),
            @Result(column ="FlatSpotPt" ,property ="FlatSpotPt"),
            @Result(column ="FlatSpotFz" ,property ="FlatSpotFz"),
            @Result(column ="ExpectedDate" ,property ="ExpectedDate"),
            @Result(column ="state" ,property ="state"),
            @Result(column ="complete" ,property ="complete"),
            @Result(column ="cancel" ,property ="cancel"),
            @Result(column ="red" ,property ="red"),
            @Result(column ="spbh" ,property ="spbh"),
            @Result(column ="caozuo" ,property ="caozuo"),
            @Result(column ="kaishi" ,property ="kaishi"),
            @Result(column ="endTime" ,property ="endTime"),
            @Result(column ="cancellationTime" ,property ="cancellationTime"),
            @Result(column ="factory" ,property ="factory"),
            @Result(column = "tyreId",property = "tyre",javaType = Tyre.class,one = @One(select = "com.maxxis.dao.TyreDao.findById"))

    })
    List<Test> TestcancelledSP();


    //        开始的时候把颜色改成0
    @Update("update Rctest set red=0 where id=#{id}")
  public   void updatered(String id)throws  Exception;

    //        开始的时候把错误信息删除(先查id)
    @Select("select id from Rctest where number=#{number} and TestItem='Footprint' and factory='CSTC'")
  public   String findTestId(int number)throws  Exception;

    //        开始的时候把错误信息删除(再去删除数据)
    @Delete("delete from Rcabnormal where id=#{id}")
   public void deleteAbnormal(String id)throws  Exception;

    //        SP改变隐藏按钮number
    @Update("update Rctest set spbh=1 where number=#{number} and TestItem='Footprint' and factory='CSTC'")
    public   void spbhNumber(int number)throws  Exception;

    //   查询SP已排 并且是查询还没有开始的已排  就是当前按钮number的后面的已经排的
    @Select("select * from  Rctest where number>=#{number} and TestItem='Footprint' and factory='CSTC'  and state=1 and cancel=0 and complete=0 order by number")
    @Results({
            @Result(id = true,column ="id" ,property ="id"),
            @Result(column ="number" ,property ="number"),
            @Result(column ="TestItem" ,property ="TestItem"),
            @Result(column ="sampleID" ,property ="sampleID"),
            @Result(column ="Standard" ,property ="Standard"),
            @Result(column ="UsageRim" ,property ="UsageRim"),
            @Result(column ="pt" ,property ="pt"),
            @Result(column ="Fz" ,property ="Fz"),
            @Result(column ="Vr" ,property ="Vr"),
            @Result(column ="CA" ,property ="CA"),
            @Result(column ="SA" ,property ="SA"),
            @Result(column ="PA" ,property ="PA"),
            @Result(column ="Mileage" ,property ="Mileage"),
            @Result(column ="FRAngleDeg" ,property ="FRAngleDeg"),
            @Result(column ="FFTOrder" ,property ="FFTOrder"),
            @Result(column ="FlatSpotPt" ,property ="FlatSpotPt"),
            @Result(column ="FlatSpotFz" ,property ="FlatSpotFz"),
            @Result(column ="ExpectedDate" ,property ="ExpectedDate"),
            @Result(column ="state" ,property ="state"),
            @Result(column ="complete" ,property ="complete"),
            @Result(column ="cancel" ,property ="cancel"),
            @Result(column ="red" ,property ="red"),
            @Result(column ="spbh" ,property ="spbh"),
            @Result(column ="caozuo" ,property ="caozuo"),
            @Result(column ="kaishi" ,property ="kaishi"),
            @Result(column ="endTime" ,property ="endTime"),
            @Result(column ="cancellationTime" ,property ="cancellationTime"),
            @Result(column ="factory" ,property ="factory"),
            @Result(column = "tyreId",property = "tyre",javaType = Tyre.class,one = @One(select = "com.maxxis.dao.TyreDao.findById"))

    })
    public   List<Test> findByNoStateNumber(int number)throws  Exception;

    //    查询RR1.7已排
    @Select("select * from Rctest  where  state=1   and (TestItem='RR' or TestItem='RPK' or TestItem='CDRR') and factory='CSTC' and cancel=0 and complete=0 order by number")
    List<Test> findTestByStateRR()throws Exception;

    //    RR1.7 获取设定的时间
    @Select("select * from RcworktimeRR order by gotowork")
  public  List<WorkTimeRR> findworktimeRR()throws  Exception;


    //    RR1.7 排程 （一键排程时候上面有已经排好的数据时）
    @Update("update Rctest set ExpectedDate=#{result1a} where number=#{numberA} and (TestItem='RR' or TestItem='RPK' or TestItem='CDRR') and factory='CSTC'")
   public void updateRRExpectedDateUP(@Param("numberA") Integer numberA, @Param("result1a") String result1a)throws  Exception;

    //    SP需求编号搜索(待排)
    @Select("select number,\n" +
            "       t1.id  as idRctest,TestItem,sampleID,Standard,UsageRim,pt,Fz,Vr,CA,SA,PA,Mileage,FRangleDeg,FFTOrder,FlatSpotPt,FlatSpotFz,ExpectedDate,state,complete,cancel,red,spbh,caozuo,kaishi,endTime,cancellationTime,factory,t2.id  as idRctyre,Size,t3.id as idRccliet,RequestId,Project,Purpose,ProjectEngineer,PriorityDes,RequestTime,TimeA,TestTime,ContactExt,Remark,PriorityDesNumber,sampleDisposal\n" +
            "  from Rctest t1,Rctyre t2,Rccliet t3 where t1.tyreId =t2.id and t2.clietId=t3.id and TestItem='Footprint' and factory='CSTC' and state='0'and cancel='0' and complete='0' and RequestId like '%${value}%'")
  public   List<TestClietTyre> findByTestRequestIdDaiPai(String requestId)throws  Exception;


    //            根据时间去查  SP  已经完成的
    @Select("select * from Rctest where TestItem='Footprint' and factory='CSTC' and complete='1'and endTime > #{start} and endTime < #{end}  order by endTime")
    @Results({
            @Result(id = true,column ="id" ,property ="id"),
            @Result(column ="number" ,property ="number"),
            @Result(column ="TestItem" ,property ="TestItem"),
            @Result(column ="sampleID" ,property ="sampleID"),
            @Result(column ="Standard" ,property ="Standard"),
            @Result(column ="UsageRim" ,property ="UsageRim"),
            @Result(column ="pt" ,property ="pt"),
            @Result(column ="Fz" ,property ="Fz"),
            @Result(column ="Vr" ,property ="Vr"),
            @Result(column ="CA" ,property ="CA"),
            @Result(column ="SA" ,property ="SA"),
            @Result(column ="PA" ,property ="PA"),
            @Result(column ="Mileage" ,property ="Mileage"),
            @Result(column ="FRAngleDeg" ,property ="FRAngleDeg"),
            @Result(column ="FFTOrder" ,property ="FFTOrder"),
            @Result(column ="FlatSpotPt" ,property ="FlatSpotPt"),
            @Result(column ="FlatSpotFz" ,property ="FlatSpotFz"),
            @Result(column ="ExpectedDate" ,property ="ExpectedDate"),
            @Result(column ="state" ,property ="state"),
            @Result(column ="complete" ,property ="complete"),
            @Result(column ="cancel" ,property ="cancel"),
            @Result(column ="red" ,property ="red"),
            @Result(column ="spbh" ,property ="spbh"),
            @Result(column ="caozuo" ,property ="caozuo"),
            @Result(column ="kaishi" ,property ="kaishi"),
            @Result(column ="endTime" ,property ="endTime"),
            @Result(column ="cancellationTime" ,property ="cancellationTime"),
            @Result(column ="factory" ,property ="factory"),
            @Result(column = "tyreId",property = "tyre",javaType = Tyre.class,one = @One(select = "com.maxxis.dao.TyreDao.findById"))

    })
  public  List<Test> findByendTimeComplete(@Param("start") String start,@Param("end") String end)throws  Exception;


    //            根据时间去查  SP  已经取消的
    @Select("select * from Rctest where TestItem='Footprint' and factory='CSTC' and cancel='1'and cancellationTime > #{startQuXiao} and cancellationTime < #{endQuXiao} order by cancellationTime")
    @Results({
            @Result(id = true,column ="id" ,property ="id"),
            @Result(column ="number" ,property ="number"),
            @Result(column ="TestItem" ,property ="TestItem"),
            @Result(column ="sampleID" ,property ="sampleID"),
            @Result(column ="Standard" ,property ="Standard"),
            @Result(column ="UsageRim" ,property ="UsageRim"),
            @Result(column ="pt" ,property ="pt"),
            @Result(column ="Fz" ,property ="Fz"),
            @Result(column ="Vr" ,property ="Vr"),
            @Result(column ="CA" ,property ="CA"),
            @Result(column ="SA" ,property ="SA"),
            @Result(column ="PA" ,property ="PA"),
            @Result(column ="Mileage" ,property ="Mileage"),
            @Result(column ="FRAngleDeg" ,property ="FRAngleDeg"),
            @Result(column ="FFTOrder" ,property ="FFTOrder"),
            @Result(column ="FlatSpotPt" ,property ="FlatSpotPt"),
            @Result(column ="FlatSpotFz" ,property ="FlatSpotFz"),
            @Result(column ="ExpectedDate" ,property ="ExpectedDate"),
            @Result(column ="state" ,property ="state"),
            @Result(column ="complete" ,property ="complete"),
            @Result(column ="cancel" ,property ="cancel"),
            @Result(column ="red" ,property ="red"),
            @Result(column ="spbh" ,property ="spbh"),
            @Result(column ="caozuo" ,property ="caozuo"),
            @Result(column ="kaishi" ,property ="kaishi"),
            @Result(column ="endTime" ,property ="endTime"),
            @Result(column ="cancellationTime" ,property ="cancellationTime"),
            @Result(column ="factory" ,property ="factory"),
            @Result(column = "tyreId",property = "tyre",javaType = Tyre.class,one = @One(select = "com.maxxis.dao.TyreDao.findById"))

    })
   public List<Test> findByCancellationTimeCancel(@Param("startQuXiao") String startQuXiao,@Param("endQuXiao") String endQuXiao)throws  Exception;

    // SP  把已经过的时间的past变成1
    @Update("update RcworktimeSP set past=1 where getoffwork<=#{result1a} and past=0")
     public void updatePast(long result1a)throws  Exception;

    // RR 把已经过的时间的past变成1
    @Update("update RcworktimeRR set past=1 where getoffwork<=#{result1a} and past=0")
    public void updatePastRR(long result1a)throws  Exception;

    // SStiffness 把已经过的时间的past变成1
    @Update("update RcworktimeSStiffness set past=1 where getoffwork<=#{result1a} and past=0")
    public void updatePastSStiffness(long result1a)throws  Exception;

  //  HSU 把已经过的时间的past变成1
  @Update("update RcworktimeHSU set past=1 where getoffwork<=#{result1a} and past=0")
  void updatePastHSU(long result1a)throws Exception;


    //  SP 查询出没有过去的工时
    @Select("select * from RcworktimeSP where past=0 order by gotowork")
   public List<WorkTimeSp> findByRcworktimeSP()throws  Exception;

    //  RR 查询出没有过去的工时
    @Select("select * from RcworktimeRR where past=0 order by gotowork")
    List<WorkTimeRR> findByRcworktimeRR()throws  Exception;

    //  SStiffness查询出没有过去的工时
    @Select("select * from RcworktimeSStiffness where past=0 order by gotowork")
    List<WorkTimeSStiffness> findByRcworktimeSStiffness()throws  Exception;


  //        HSU查询出没有过去的工时
  @Select("select * from RcworktimeHSU where past=0 order by gotowork")
      List<WorkTimeHSU> findByRcworktimeHSU()throws Exception;

    //        SP时间搜索按钮
    @Select("select * from RcworktimeSP where gotowork >=#{gotoworkLingChen} and gotowork <=#{gotoworkWanShang} order by gotowork")
   public List<WorkTimeSp> findWorkTimeSouSuo(@Param("gotoworkLingChen")long gotoworkLingChen,@Param("gotoworkWanShang")long gotoworkWanShang)throws  Exception;

    //        RR时间搜索按钮
    @Select("select * from RcworktimeRR where gotowork >=#{gotoworkLingChen} and gotowork <=#{gotoworkWanShang} order by gotowork")
    List<WorkTimeRR> findWorkTimeSouSuoRR(@Param("gotoworkLingChen")long gotoworkLingChen, @Param("gotoworkWanShang")long gotoworkWanShang)throws  Exception;

    //        SStiffness时间搜索按钮
    @Select("select * from RcworktimeSStiffness where gotowork >=#{gotoworkLingChen} and gotowork <=#{gotoworkWanShang} order by gotowork")
    List<WorkTimeSp> findWorkTimeSStiffnessSouSuo(@Param("gotoworkLingChen")long gotoworkLingChen, @Param("gotoworkWanShang")long gotoworkWanShang)throws  Exception;

  //       HSU时间搜索按钮
  @Select("select * from RcworktimeHSU where gotowork >=#{gotoworkLingChen} and gotowork <=#{gotoworkWanShang} order by gotowork")
  List<WorkTimeHSU> findWorkTimeHSUSouSuo(@Param("gotoworkLingChen") long gotoworkLingChen, @Param("gotoworkWanShang") long gotoworkWanShang);

    //       BC时间搜索按钮
    @Select("select * from RcworktimeBC where gotowork >=#{gotoworkLingChen} and gotowork <=#{gotoworkWanShang} order by gotowork")
    List<WorkTimeBC> findWorkTimeSouSuoBC(@Param("gotoworkLingChen") long gotoworkLingChen, @Param("gotoworkWanShang") long gotoworkWanShang);

    //          委托方  查询  未完成查询
    @Select("<script>"+
            "select case when number=0 then '9999' else number end 'number2', number,\n" +
            "       t1.id  as idRctest,TestItem,sampleID,Standard,UsageRim,pt,Fz,Vr,CA,SA,PA,Mileage,FRangleDeg,FFTOrder,FlatSpotPt,FlatSpotFz,ExpectedDate,state,complete,cancel,red,spbh,caozuo,kaishi,endTime,cancellationTime,factory,t2.id  as idRctyre,Size,t3.id as idRccliet,RequestId,Project,Purpose,ProjectEngineer,PriorityDes,RequestTime,TimeA,TestTime,ContactExt,Remark,PriorityDesNumber,sampleDisposal\n" +
            "  from Rctest t1,Rctyre t2,Rccliet t3 where t1.tyreId =t2.id and t2.clietId=t3.id and TestItem in " +
            "<foreach collection='fruitA' item='a' index='index' open='(' close=')' separator=','>"+
            "#{a}"+
            "</foreach>"+
            " and  cancel='0' and complete='0' and factory='CSTC' order by number2,RequestTime"+
            "</script>"
    )
    public  List<TestClietTyre> findByWeiTuoWei(@Param("fruitA")List<String> fruitA)throws Exception;
    @Select(  "<script> select count(*)as ccc from Rctest t1,Rctyre t2,Rccliet t3 where t1.tyreId =t2.id and t2.clietId=t3.id and TestItem in  <foreach collection='fruitA' item='a' index='index' open='(' close=')' separator=','> #{a} </foreach> and   cancel='0' and complete='0' and factory='CSTC' </script>")
   public long countSql(@Param("fruitA")List<String> fruitA)throws  Exception;

    //          委托方  查询  完成查询
    @Select("<script>"+
            "select  number,\n" +
            "       t1.id  as idRctest,TestItem,sampleID,Standard,UsageRim,pt,Fz,Vr,CA,SA,PA,Mileage,FRangleDeg,FFTOrder,FlatSpotPt,FlatSpotFz,ExpectedDate,state,complete,cancel,red,spbh,caozuo,kaishi,endTime,cancellationTime,factory,t2.id  as idRctyre,Size,t3.id as idRccliet,RequestId,Project,Purpose,ProjectEngineer,PriorityDes,RequestTime,TimeA,TestTime,ContactExt,Remark,PriorityDesNumber,sampleDisposal\n" +
            "  from Rctest t1,Rctyre t2,Rccliet t3 where t1.tyreId =t2.id and t2.clietId=t3.id and TestItem in " +
            "<foreach collection='fruitAA' item='a' index='index' open='(' close=')' separator=','>"+
            "#{a}"+
            "</foreach>"+
            " and  complete='1' and factory='CSTC' and endTime &gt; #{startWT} and endTime &lt; #{endWT} order by endTime"+
            "</script>"
    )
    List<TestClietTyre> findByWeiTuoWan(@Param("startWT") String startWT, @Param("endWT")String endWT,@Param("fruitAA")List<String> fruitAA);
   @Select( "<script> select count(*)as ccc from Rctest t1,Rctyre t2,Rccliet t3 where t1.tyreId =t2.id and t2.clietId=t3.id and TestItem in  <foreach collection='fruitAA' item='a' index='index' open='(' close=')' separator=','> #{a} </foreach> and   complete='1' and factory='CSTC' and endTime &gt; #{startWT} and endTime &lt; #{endWT} </script>")
    public long findByWeiTuoWanCount(@Param("startWT")String startWT, @Param("endWT")String endWT, @Param("fruitAA")List<String> fruitAA)throws Exception;

    //          委托方  查询  取消查询
    @Select("<script>"+
            "select  number,\n" +
            "       t1.id  as idRctest,TestItem,sampleID,Standard,UsageRim,pt,Fz,Vr,CA,SA,PA,Mileage,FRangleDeg,FFTOrder,FlatSpotPt,FlatSpotFz,ExpectedDate,state,complete,cancel,red,spbh,caozuo,kaishi,endTime,cancellationTime,factory,t2.id  as idRctyre,Size,t3.id as idRccliet,RequestId,Project,Purpose,ProjectEngineer,PriorityDes,RequestTime,TimeA,TestTime,ContactExt,Remark,PriorityDesNumber,sampleDisposal\n" +
            "  from Rctest t1,Rctyre t2,Rccliet t3 where t1.tyreId =t2.id and t2.clietId=t3.id and TestItem in " +
            "<foreach collection='fruitAA' item='a' index='index' open='(' close=')' separator=','>"+
            "#{a}"+
            "</foreach>"+
            " and  cancel='1' and factory='CSTC' and cancellationTime &gt; #{startWT} and cancellationTime &lt; #{endWT} order by cancellationTime ; \n" +
            "</script>"
    )
    List<TestClietTyre> findByWeiTuoQu(@Param("startWT")String startWT, @Param("endWT")String endWT, @Param("fruitAA")List<String> fruitAA);

      @Select("<script> select count(*)as ccc from Rctest t1,Rctyre t2,Rccliet t3 where t1.tyreId =t2.id and t2.clietId=t3.id and TestItem in  <foreach collection='fruitAA' item='a' index='index' open='(' close=')' separator=','> #{a} </foreach> and  cancel='1' and factory='CSTC' and cancellationTime &gt; #{startWT} and cancellationTime &lt; #{endWT} </script>")
      public long findByWeiTuoQuCount(@Param("startWT")String startWT, @Param("endWT")String endWT,@Param("fruitAA") List<String> fruitAA)throws Exception;

    /******************************************/
    @Update("<script>" +
            "<foreach collection='odAdjustOrderObjList' item='odAdjustOrderObj' index='index'>" +
            "UPDATE RcTestInfoOD " +
            "SET TestOrder = #{odAdjustOrderObj.purposeOrder} " +
            "WHERE ODTestInfoId = #{odAdjustOrderObj.adjustId}" +
            "</foreach>" +
            "</script>")
    public void adjustOrderByUserOD(@Param("odAdjustOrderObjList")List<ODAdjustOrderObj> odAdjustOrderObjList);

    @Select("<script>" +
            "SELECT * FROM RcTestInfoOD " +
            "WHERE State IN ('wait','testing','pause') " +
            "ORDER BY TestOrder" +
            "</script>")
    public List<ODTestInfo> getAllSortInfoOD();

    @Update("<script>" +
            "<foreach collection='testInfoIds' item='eachId' index='index'>" +
            "UPDATE RcTestInfoOD SET State = 'testing' WHERE ODTestInfoId = #{eachId}" +
            "</foreach>" +
            "</script>")
    public void sortedSatrtTestOD(@Param("testInfoIds")List<String> testInfoIds);

    @Update("<script>" +
            "<foreach collection='testInfoIds' item='eachId' index='index'>" +
            "UPDATE RcTestInfoOD SET State = 'finish',TestOrder = '0' WHERE ODTestInfoId = #{eachId}" +
            "</foreach>" +
            "</script>")
    public void sortedFinishTestOD(@Param("testInfoIds")List<String> testInfoIds);

    @Update("<script>" +
            "<foreach collection='orgOrders' item='orgOrder' index='index'>" +
            "UPDATE RcTestInfoOD SET TestOrder = TestOrder - 1 " +
            "WHERE TestOrder > #{orgOrder}" +
            "</foreach></script>")
    public void updateOrderAfterOrgOrdersOD(@Param("orgOrders")List<String> orgOrders);

    @Update("<script>UPDATE RcTestInfoOD SET TestOrder = 0 ,State = 'cancel' WHERE ODTestInfoId IN" +
            "<foreach collection='testInfoIds' item='eachId' index='index' open='(' close=')' separator=','>" +
            "#{eachId}" +
            "</foreach>" +
            "</script>")
    public void unSortTestCancelOD(@Param("testInfoIds") List<String> testInfoIds);

    @Update("<script>" +
            "<foreach collection='testInfoIds' item='infoId' index='index'>" +
            "UPDATE RcTestInfoOD SET " +
            "TestOrder = '0' ,State = 'unsorted' " +
            "WHERE ODTestInfoId = #{infoId}" +
            "</foreach>" +
            "</script>")
    public void sortedToUnSortOD(@Param("testInfoIds")List<String> testInfoIds);

    @Update("<script>" +
            "UPDATE RcTestInfoOD " +
            "SET TestOrder = TestOrder + #{operateNum} " +
            "WHERE TestOrder >= #{purposeOrder};" +
            "<foreach collection='testInfoIds' item='eachId' index='index'>" +
            "UPDATE RcTestInfoOD " +
            "SET TestOrder = #{purposeOrder} + #{index} ," +
            "State = 'wait' " +
            "WHERE ODTestInfoId = #{eachId}" +
            "</foreach>" +
            "</script>")
    public void unSortToSortByPurposeOrderOD(@Param("purposeOrder")int purposeOrder,@Param("testInfoIds") List<String> testInfoIds,@Param("operateNum") int operateNum);

    @Update("<script>" +
            "<foreach collection='orgOrders' item='eachOrgOrder' index='index'>" +
            "UPDATE RcTestInfoOD " +
            "SET TestOrder = TestOrder +1 " +
            "WHERE TestOrder > #{purposeOrder} AND #{eachOrgOrder} > TestOrder" +
            "</foreach>;" +
            "UPDATE RcTestInfoOD " +
            "SET TestOrder = TestOrder + #{operateNum} " +
            "WHERE TestOrder = #{purposeOrder};" +
            "<foreach collection='testInfoIds' item='eachOrgid' index='index2'>" +
            "UPDATE RcTestInfoOD SET " +
            "TestOrder = #{purposeOrder} + #{index2} " +
            "WHERE ODTestInfoId = #{eachOrgid}" +
            "</foreach>" +
            "</script>")
    public void sortedReSortByOrderOD(@Param("purposeOrder")int purposeOrder,@Param("testInfoIds") List<String> testInfoIds,@Param("operateNum") int operateNum,@Param("orgOrders") List<String> orgOrders);

    @Update("<script>" +
            "<foreach collection='testInfoIds' item='eachId' index='index'>" +
            "UPDATE RcTestInfoOD SET TestOrder = #{maxOrder}+#{index}+1,State='wait'" +
            "WHERE ODTestInfoId = #{eachId}" +
            "</foreach>" +
            "</script>")
    public void unSortToSortByOriginalOrderOD(@Param("maxOrder")int maxOrder,@Param("testInfoIds")List<String> testInfoIds);

    @Select("<script>" +
            "SELECT COUNT(*) AS COUNTNUM FROM RcTestInfoOD WHERE state IN" +
            "<foreach collection='states' item='state' index='index' open='(' close=')' separator=','>" +
            "#{state}" +
            "</foreach>" +
            "</script>")
    public int getUnSortCountOD(@Param("states")List<String> states);

    @Select("<script>" +
            "SELECT T.* FROM (SELECT *,ROW_NUMBER() OVER (ORDER BY TestOrder,ODTestInfoId) AS rn FROM RcTestInfoOD WHERE STATE IN" +
            "<foreach collection='states' item='state' index='index' open='(' close=')' separator=','>" +
            "#{state}" +
            "</foreach>" +
            ")T WHERE rn BETWEEN #{fromNo} AND #{toNo}" +
            "</script>")
    public List<ODTestInfo> getOnePageDataOD(@Param("states")List<String> states,@Param("fromNo") int fromNo,@Param("toNo") int toNo);

    @Select("SELECT MAX(TestOrder) from RcTestInfoOD WHERE State IN ('wait','testing','pause')")
    public int getMaxOrderOD();

    @Update("<script>UPDATE RcWorkTimeOD SET StartTime = #{startTime},EndTime = #{endTime} WHERE ODWorkTimeId = #{workTimeID}</script>")
    public void updateWorkTimeOD(@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("workTimeID")String workTimeID);

    @Delete("<script>DELETE FROM RcWorkTimeOD WHERE ODWorkTimeId = #{workTimeID}</script>")
    public void deleteUnFinishedWorkTimeOD(@Param("workTimeID")int workTimeID);

    @Select("<script>SELECT * FROM RcWorkTimeOD WHERE EndTime > #{nowTime} ORDER BY StartTime</script>")
    public List<ODWorkTime> getUnFinishedWorkTimeOD(@Param("nowTime")String nowTime);

    @Select("<script>" +
            "SELECT * FROM RcWorkTimeOD WHERE " +
            "StartTime > #{nowDate} AND ODWorkTimeId != #{operateID} AND" +
            "((#{startDate} >= StartTime AND EndTime >= #{endDate}) " +
            "OR (StartTime >= #{startDate} AND #{endDate} >= EndTime) " +
            "OR (StartTime > #{startDate} AND #{endDate} > StartTime) " +
            "OR (EndTime > #{startDate} AND #{endDate} > EndTime))" +
            "</script>")
    public List<ODWorkTime> isTimeConflictOD(@Param("startDate")String startDate, @Param("endDate")String endDate,@Param("nowDate")String nowDate,@Param("operateID")String operateID);

    @Insert("<script>INSERT INTO RcWorkTimeOD (StartTime,EndTime) VALUES (#{startDate},#{endDate})</script>")
    public void insertWorkTimeOD(@Param("startDate")Date startDate,@Param("endDate")Date endDate);


}










