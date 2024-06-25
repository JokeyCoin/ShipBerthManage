package com.itheima.mapper;

import com.github.pagehelper.Page;
import com.itheima.domain.BerthArea;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface BerthAreaMapper {

    @Select("SELECT * FROM bertharea order by iban DESC")
    @Results(id = "berthAreaMap",value = {
            //id字段默认为false，表示不是主键
            //column表示数据库表字段，property表示实体类属性名。
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "iban",property = "iban"),
            @Result(column = "size",property = "size"),
            @Result(column = "damaged",property = "damaged"),
            @Result(column = "repairs_cost",property = "repairsCost")
    })
    Page<BerthArea> selectBerthArea();
}
