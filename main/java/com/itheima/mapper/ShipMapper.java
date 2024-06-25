package com.itheima.mapper;
import com.github.pagehelper.Page;
import com.itheima.domain.Ship;
import org.apache.ibatis.annotations.*;
/**
 * 船只接口
 */
public interface ShipMapper {
    @Select("SELECT * FROM ship order by berth_time DESC")
    @Results(id = "shipMap",value = {
            //id字段默认为false，表示不是主键
            //column表示数据库表字段，property表示实体类属性名。
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "model",property = "model"),
            @Result(column = "sin",property = "sin"),
            @Result(column = "tonnage",property = "tonnage"),
            @Result(column = "net_weight",property = "netWeight"),
            @Result(column = "captain_id",property = "captainId"),
            @Result(column = "captain_name",property = "captainName"),
            @Result(column = "status",property = "status"),
            @Result(column = "current_location",property = "currentLocation"),
            @Result(column = "berth_time",property = "berthTime"),
            @Result(column = "exit_time",property = "exitTime")
    })
    Page<Ship> selectNewShips();

    @Select("SELECT * FROM ship where id=#{id}")
    @ResultMap("shipMap")
    //根据id查询船只信息
    Ship findById(String id);
    @Select({"<script>" +
            "SELECT * FROM ship " +
            "<if test=\"sin != null\"> AND  sin  like  CONCAT('%',#{sin},'%')</if>" +
            "<if test=\"captainName != null\"> AND captain_name like  CONCAT('%', #{captainName},'%')</if>" +
            "<if test=\"model != null\"> AND  model  like  CONCAT('%',#{model},'%')</if>" +
            "order by berth_time" +
            "</script>"
    })
    @ResultMap("shipMap")
    //分页查询船只
    Page<Ship> searchShips(Ship ship);
    //新增船只
    Integer addShip(Ship ship);
//编辑船只信息
    Integer editShip(Ship ship);


@Select(
        {"<script>" +
                "SELECT * FROM ship " +
                "where status ='1'"+
                "<if test=\"sin != null\"> AND  sin  like  CONCAT('%',#{sin},'%')</if>" +
                "<if test=\"captainName != null\"> AND captain_name like  CONCAT('%', #{captainName},'%') </if>" +
                "<if test=\"model != null\"> AND model like  CONCAT('%', #{model},'%')</if>" +
                "or status ='2'"+
                "<if test=\"sin != null\"> AND  sin  like  CONCAT('%',#{sin},'%')</if>" +
                "<if test=\"captainName != null\"> AND captain_name like  CONCAT('%', #{captainName},'%') </if>" +
                "<if test=\"model != null\"> AND model like  CONCAT('%', #{model},'%')</if>" +
                "order by berth_time" +
                "</script>"})
@ResultMap("shipMap")
//查询停泊中以及待结算停泊费用出港的船只
Page<Ship> selectBerthing(Ship ship);
}
