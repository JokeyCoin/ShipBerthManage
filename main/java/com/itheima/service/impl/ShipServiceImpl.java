package com.itheima.service.impl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.domain.Ship;
import com.itheima.domain.Record;
import com.itheima.mapper.ShipMapper;
import com.itheima.service.ShipService;
import com.itheima.service.RecordService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class ShipServiceImpl implements ShipService {
    @Autowired
    private ShipMapper shipMapper;

    /**
     * 根据当前页码和每页需要展示的数据条数，查询最近停泊的船只信息
     * @param pageNum 当前页码
     * @param pageSize 每页显示数量
     */
    @Override
    public PageResult selectNewShips(Integer pageNum, Integer pageSize) {
        // 设置分页查询的参数，开始分页
        PageHelper.startPage(pageNum, pageSize);
        Page<Ship> page= shipMapper.selectNewShips();
        return new PageResult(page.getTotal(),page.getResult());
    }
/**
 * 根据id查询船只信息
 * @param id 船只id
 */
public Ship findById(String id) {
    return shipMapper.findById(id);
}

/**
 * 停泊船只
 * @param ship
 * @return
 */
@Override
public Integer shipBerth(Ship ship) {
    //根据id查询出需要停泊的完整船只信息
    Ship s = this.findById(ship.getId()+"");
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //设置当天为停泊时间
    ship.setBerthTime(dateFormat.format(new Date()));
    //设停泊的停泊状态为停泊中
    ship.setStatus(1);
    return shipMapper.editShip(ship);
}

/**
 * @param ship 封装查询条件的对象
 * @param pageNum 当前页码
 * @param pageSize 每页显示数量
 */
@Override
public PageResult search(Ship ship, Integer pageNum, Integer pageSize) {
    // 设置分页查询的参数，开始分页
    PageHelper.startPage(pageNum, pageSize);
    Page<Ship> page= shipMapper.searchShips(ship);
    return new PageResult(page.getTotal(),page.getResult());
}

/**
 * 新增船只
 * @param ship 页面提交的新增船只信息
 */
public Integer addShip(Ship ship) {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //设置新增船只的停泊时间
    ship.setBerthTime(dateFormat.format(new Date()));
    return  shipMapper.addShip(ship);
}

/**
 * 编辑船只信息
 * @param ship 船只信息
 */
public Integer editShip(Ship ship) {
    return shipMapper.editShip(ship);
}

/**
 * 查询当前停泊的船只
 * @param ship 封装查询条件的对象
 * @param pageNum 当前页码
 * @param pageSize 每页显示数量
 */
@Override
public PageResult searchBerthing(Ship ship, Integer pageNum, Integer pageSize) {
    // 设置分页查询的参数，开始分页
    PageHelper.startPage(pageNum, pageSize);
    Page<Ship> page;
    //将当前登录的用户放入查询条件中
    /*ship.setBorrower(administrator.getUsername());*/
    //如果是管理员，查询自己离港但未离港的船只和所有待确认离港的船只
    page= shipMapper.selectBerthing(ship);
    return new PageResult(page.getTotal(),page.getResult());
}

/**
 * 船只出港
 *
 * @param id 出港的船只的id
 */
@Override
public boolean exitShip(String id) {
    //根据船只id查询出船只的完整信息
    Ship ship = this.findById(id);

    //将船只状态修改为登记出港状态
    ship.setStatus(2);
    shipMapper.editShip(ship);
    return true;
}
@Autowired
//注入RecordService对象
private RecordService recordService;
/**
 *出港确认
 * @param id 待离港确认的船只id
 */
@Override
public Integer exitConfirm(String id) {
    //根据船只id查询船只的完整信息
    Ship ship = this.findById(id);
    //将船只的状态修改为已出港
    ship.setStatus(0);
    //设置当天为离港时间
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    ship.setExitTime(simpleDateFormat.format(new Date()));
    //根据出港确认的船只信息，设置停泊记录
    Record record = this.setRecord(ship);
    ship.setCurrentLocation("港湾外");
    Integer count= shipMapper.editShip(ship);
    //如果出港确认成功，则新增出港记录
    if(count==1){
        return  recordService.addRecord(record);
    }
    return 0;
}
/**
 * 根据船只信息设置停泊记录的信息
 * @param ship 停泊的船只信息
 */
private Record setRecord(Ship ship){
    Record record=new Record();
    //设置停泊记录的船只型号
    record.setShipModel(ship.getModel());
    //设置停泊记录的船只识别号
    record.setShipSin(ship.getSin());
    //设置停泊记录的船只船长名称
    record.setShipCaptain(ship.getCaptainName());
    //设置停泊记录的船只净重
    record.setShipNetWeight(ship.getNetWeight());
    //设置停泊记录的停泊位置识别号
    record.setAreaIban(ship.getCurrentLocation());
    //设置停泊记录的入港停泊时间
    record.setBerthTime(ship.getBerthTime());
    //设置船只离港确认的当天为船只离港时间
//    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    record.setExitTime(ship.getExitTime());
    //计算停泊费用
    record.setFee(feeCalculator(ship));
    return record;
}
private double feeCalculator(Ship ship){
    String berthTime=ship.getBerthTime();
    String exitTime=ship.getExitTime();
    double fee=0,Weight=ship.getNetWeight();
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    try {
        Date dateBerth = simpleDateFormat.parse(berthTime);
        Date dateExit = simpleDateFormat.parse(exitTime);
        long diffInMillies = Math.abs(dateExit.getTime() - dateBerth.getTime());
        int diffInDays =(int)TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS)+1;
        fee = diffInDays * Weight * 0.02;
    } catch (ParseException e) {
        throw new RuntimeException(e);
    }
    return fee;
    }
}