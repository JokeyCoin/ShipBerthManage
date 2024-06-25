package com.itheima.mapper;
import com.github.pagehelper.Page;
import com.itheima.domain.Record;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface RecordMapper {
//新增停泊记录
Integer addRecord(Record record);
@Select({"<script>" +
        "SELECT * FROM record " +
        "where 1=1" +
        "<if test=\"shipSin != null\">AND ship_sin like  CONCAT('%',#{shipSin},'%')</if>" +
        "<if test=\"areaIban != null\">AND area_iban  like  CONCAT('%',#{areaIban},'%') </if>" +
        "order by exit_time DESC" +
        "</script>"
})
@Results(id = "recordMap",value = {
        //id字段默认为false，表示不是主键
        //column表示数据库表字段，property表示实体类属性名。
        @Result(id = true,column = "id",property = "id"),
        @Result(column = "ship_model",property = "shipModel"),
        @Result(column = "ship_sin",property = "shipSin"),
        @Result(column = "ship_captain",property = "shipCaptain"),
        @Result(column = "ship_net_weight",property = "shipNetWeight"),
        @Result(column = "area_iban",property = "areaIban"),
        @Result(column = "berth_time",property = "berthTime"),
        @Result(column = "exit_time",property = "exitTime"),
        @Result(column = "fee",property = "fee")
})
//查询借阅记录
Page<Record> searchRecords(Record record);
}
