package com.itheima.controller;
import com.itheima.domain.Administrator;
import com.itheima.domain.Ship;
import com.itheima.service.ShipService;
import entity.PageResult;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/*
船只信息Controller
 */
@Controller
@RequestMapping("/ship")
public class ShipController {
    //注入ShipService对象
    @Autowired
    private ShipService shipService;
    /**
     * 查询最近停泊的船只
     */
    @RequestMapping("/selectNewShips")
    public ModelAndView selectNewShips() {
        //查询最近停泊的5艘船只的信息
        int pageNum = 1;
        int pageSize = 5;
        PageResult pageResult = shipService.selectNewShips(pageNum, pageSize);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ships_new");
        modelAndView.addObject("pageResult", pageResult);
        return modelAndView;
    }
/**
 * 根据船只id查询船只信息
 * @param id 查询的船只id
 */
@ResponseBody
@RequestMapping("/findById")
public Result<Ship> findById(String id) {
    try {
        Ship ship = shipService.findById(id);
        if(ship ==null){
            return new Result(false,"查询船只失败！");
        }
        return new Result(true,"查询船只成功", ship);
    }catch (Exception e){
        e.printStackTrace();
        return new Result(false,"查询船只失败！");
    }
}
/**
 * 停泊船只
 * @param ship 停泊的船只
 * @return
 */
@ResponseBody
@RequestMapping("/berthShip")
public Result berthShip(Ship ship, HttpSession session) {
    //获取当前登录的用户名称
    try {
        //根据船只的id和用户进行船只停泊
        Integer count = shipService.shipBerth(ship);
        if (count != 1) {
            return new Result(false, "进港停泊失败!");
        }
        return new Result(true, "进港停泊成功!");
    } catch (Exception e) {
        e.printStackTrace();
        return new Result(false, "进港停泊失败!");
    }
}

/**
 * 分页查询符合条件且未下架船只信息
 * @param ship 查询的条件封装到ship中
 * @param pageNum  数据列表的当前页码
 * @param pageSize 数据列表1页展示多少条数据
 */
@RequestMapping("/search")
public ModelAndView search(Ship ship, Integer pageNum, Integer pageSize, HttpServletRequest request) {
    if (pageNum == null) {
        pageNum = 1;
    }
    if (pageSize == null) {
        pageSize = 10;
    }
    //查询到的船只信息
    PageResult pageResult = shipService.search(ship, pageNum, pageSize);
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("ships");
    //将查询到的数据存放在 ModelAndView的对象中
    modelAndView.addObject("pageResult", pageResult);
    //将查询的参数返回到页面，用于回显到查询的输入框中
    modelAndView.addObject("search", ship);
    //将当前页码返回到页面，用于分页插件的分页显示
    modelAndView.addObject("pageNum", pageNum);
    //将当前查询的控制器路径返回到页面，页码变化时继续向该路径发送请求
    modelAndView.addObject("gourl", request.getRequestURI());
    return modelAndView;
}

/**
 * 新增船只
 * @param ship 页面表单提交的船只信息
 * 将新增的结果和向页面传递信息封装到Result对象中返回
 */
@ResponseBody
@RequestMapping("/addShip")
public Result addShip(Ship ship) {
    try {
        Integer count= shipService.addShip(ship);
        if(count!=1){
            return new Result(false, "新增船只失败!!!");
        }
        return new Result(true, "新增船只成功!");
    }catch (Exception e){
        e.printStackTrace();
        return new Result(false, "新增船只失败!!!!!");
    }
}

/**
 * 编辑船只信息
 * @param ship 编辑的船只信息
 */
@ResponseBody
@RequestMapping("/editShip")
public Result editShip(Ship ship) {
    try {
        Integer count= shipService.editShip(ship);
        if(count!=1){
            return new Result(false, "编辑失败!");
        }
        return new Result(true, "编辑成功!");
    }catch (Exception e){
        e.printStackTrace();
        return new Result(false, "编辑失败!");
    }
}

/**
 *分页停泊中的船只信息
 * @param pageNum  数据列表的当前页码
 * @param pageSize 数据列表1页展示多少条数据
 */
@RequestMapping("/searchBerthing")
public ModelAndView searchBerthing(Ship ship, Integer pageNum, Integer pageSize, HttpServletRequest request) {
    if (pageNum == null) {
        pageNum = 1;
    }
    if (pageSize == null) {
        pageSize = 10;
    }
    PageResult pageResult = shipService.searchBerthing(ship, pageNum, pageSize);
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("berthing_ship");//berthing_ship.jsp→berthing_ship.jsp
    //将查询到的数据存放在 ModelAndView的对象中
    modelAndView.addObject("pageResult", pageResult);
    //将查询的参数返回到页面，用于回显到查询的输入框中
    modelAndView.addObject("search", ship);
    //将当前页码返回到页面，用于分页插件的分页显示
    modelAndView.addObject("pageNum", pageNum);
    //将当前查询的控制器路径返回到页面，页码变化时继续向该路径发送请求
    modelAndView.addObject("gourl", request.getRequestURI());
    return modelAndView;
}
/**
 * 船只出港
 * @param id 登记出港船只的id
 */
@ResponseBody
@RequestMapping("/exitShip")
public Result exitShip(String id, HttpSession session) {
    //获取当前登录的用户信息
    Administrator administrator = (Administrator) session.getAttribute("USER_SESSION");
    try {
        boolean flag = shipService.exitShip(id);
        if (!flag) {
            return new Result(false, "登记失败!");
        }
        return new Result(true, "船只登记确认中，请先到服务台支付停泊费用");
    }catch (Exception e){
        e.printStackTrace();
        return new Result(false, "登记失败!");
    }
}

/**
 * 确认出港
 * @param id 确认离港的船只的id
 */
@ResponseBody
@RequestMapping("/exitConfirm")
public Result exitConfirm(String id) {
    try {
        Integer count= shipService.exitConfirm(id);
        if(count!=1){
            return new Result(false, "确认失败!");
        }
        return new Result(true, "确认成功!");
    }catch (Exception e){
        e.printStackTrace();
        return new Result(false, "确认失败(!)");
    }
}

}

