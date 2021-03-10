package com.maxxis.dao;

import com.maxxis.domain.Tyre;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface TyreDao {
    @Select("select * from Rctyre where id=#{id}")
    @Results({
            @Result(id = true,column ="id" ,property ="id"),
            @Result(column ="Size" ,property ="Size"),
            @Result(column = "clietId",property = "cliet",javaType = Tyre.class,one = @One(select = "com.maxxis.dao.ClietDao.findById"))

    })
    public List<Tyre> findById(String id)throws Exception;
    @Select("select * from Rctyre where clietId=#{clietId}")
    List<Tyre> findByClietId(String clietId);

    @Select("select * from Rctyre where id=#{id}")
    @Results({
            @Result(id = true,column ="id" ,property ="id"),
            @Result(column ="pattern" ,property ="pattern"),
            @Result(column ="loadIndex" ,property ="loadIndex"),
            @Result(column ="speedSymbol" ,property ="speedSymbol"),
            @Result(column ="specNO" ,property ="specNO"),
            @Result(column ="Barcode" ,property ="Barcode"),
            @Result(column ="ManufacturingPlace" ,property ="ManufacturingPlace"),
            @Result(column ="DOT" ,property ="DOT"),
            @Result(column ="Weight" ,property ="Weight"),
            @Result(column = "clietId",property = "cliet",javaType = Tyre.class,one = @One(select = "com.maxxis.dao.ClietDao.findById"))

    })
    List<Tyre> findByTyreIdA(String id);
}
