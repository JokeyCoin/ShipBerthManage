package com.itheima.mapper;
import com.itheima.domain.Administrator;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
public interface AdministratorMapper {
    @Select("select * from administrator where username=#{username} AND password=#{password}")
    @Results(id = "userMap",value = {
            //id字段默认为false，表示不是主键
            //column表示数据库表字段，property表示实体类属性名。
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "username",property = "username"),
            @Result(column = "password",property = "password"),
            @Result(column = "name",property = "name"),
            @Result(column = "role",property = "role")
    })
    Administrator login(Administrator administrator);
}
