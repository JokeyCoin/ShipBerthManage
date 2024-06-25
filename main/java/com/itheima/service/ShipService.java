package com.itheima.service;
import com.itheima.domain.Ship;
import entity.PageResult;
/**
 * 船只接口
 */
public interface ShipService {
//查询最近停泊的船只
PageResult selectNewShips(Integer pageNum, Integer pageSize);
//根据id查询船只信息
Ship findById(String id);
//停泊船只
Integer shipBerth(Ship ship);
//分页查询船只
PageResult search(Ship ship, Integer pageNum, Integer pageSize);
//新增船只
Integer addShip(Ship ship);
//编辑船只信息
Integer editShip(Ship ship);
//查询当前停泊的船只
PageResult searchBerthing(Ship ship, Integer pageNum, Integer pageSize);
//船只出港
boolean exitShip(String  id);
//离港确认
Integer exitConfirm(String id);

}
