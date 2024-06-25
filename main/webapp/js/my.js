//窗口中时间标签的内容改变时执行
function cg() {
    $("#savemsg").attr("disabled", false);
    var rt = $("#exitTime").val().split("-");
    var ny = new Date().getFullYear();
    var nm = new Date().getMonth() + 1;
    var nd = new Date().getDate();
    if (rt[0] < ny) {
        alert("日期不能早于今天")
        $("#savemsg").attr("disabled", true);
    } else if (rt[0] == ny) {
        if (rt[1] < nm) {
            alert("日期不能早于今天")
            $("#savemsg").attr("disabled", true);
        } else if (rt[1] == nm) {
            if (rt[2] < nd) {
                alert("日期不能早于今天")
                $("#savemsg").attr("disabled", true);
            } else {
                $("#savemsg").attr("disabled", false);
            }
        }
    }
}
//点击借阅图书时执行
function berth() {
    var url =getProjectPath()+ "/ship/berthShip";
    $.post(url, $("#berthShip").serialize(), function (response) {
        alert(response.message)
        if (response.success == true) {
            window.location.href = getProjectPath()+"/ship/search";
        }
    })
}

//重置添加和编辑窗口中输入框的内容
function resetFrom() {
    $("#aoe").attr("disabled",true)
    var $vals=$("#addOrEditShip input");
    $vals.each(function(){
        $(this).attr("style","").val("")
    });
}
//重置添加和编辑窗口中输入框的样式
function resetStyle() {
    $("#aoe").attr("disabled",false)
    var $vals=$("#addOrEditShip input");
    $vals.each(function(){
        $(this).attr("style","")
    });
}
//查询id对应的图书信息，并将图书信息回显到编辑或借阅的窗口中
function findShipById(id,doname) {
    resetStyle()
    var url = getProjectPath()+"/ship/findById?id=" + id;
    $.get(url, function (response) {
        //如果是编辑图书，将获取的图书信息回显到编辑的窗口中
        if(doname=='edit'){
            $("#eid").val(response.data.id);
            $("#esin").val(response.data.sin);
            $("#emodel").val(response.data.model);
            $("#etonnage").val(response.data.tonnage);
            $("#enetWeight").val(response.data.netWeight);
            $("#ecaptainId").val(response.data.captainId);
            $("#ecaptainName").val(response.data.captainName);
            $("#estatus").val(response.data.status);
        }
        //如果是船只停泊，将获取的船只信息回显到停泊的窗口中
        if(doname=='berth'){
            $("#savemsg").attr("disabled",true)
            $("#id").val(response.data.id);
            $("#sin").val(response.data.sin);
            $("#model").val(response.data.model);
            $("#tonnage").val(response.data.tonnage);
            $("#netWeight").val(response.data.netWeight);
            $("#captainId").val(response.data.captainId);
            $("#captainName").val(response.data.captainName);
            $("#status").val(response.data.status);
            $("#exitTime").val("");
        }
    })
}
//点击添加或编辑的窗口的确定按钮时，提交图书信息
function addOrEdit() {
    //获取表单中图书id的内容
    var eid = $("#eid").val();
    //如果表单中有图书id的内容，说明本次为编辑操作
    if (eid > 0) {
        var url = getProjectPath()+"/ship/editShip";
        $.post(url, $("#addOrEditShip").serialize(), function (response) {
            alert(response.message)
            if (response.success == true) {
                window.location.href = getProjectPath()+"/ship/search";
            }
        })
    }
    //如果表单中没有图书id，说明本次为添加操作
    else {
        var url = getProjectPath()+"/ship/addShip";
        $.post(url, $("#addOrEditShip").serialize(), function (response) {
            alert(response.message)
            if (response.success == true) {
                window.location.href = getProjectPath()+"/ship/search";
            }
        })
    }
}
//船只离港时执行
function exitShip(id) {
    var r = confirm("Exit??");
    if (r) {
        var url = getProjectPath()+"/ship/exitShip?id=" + id;
        $.get(url, function (response) {
            alert(response.message)
            //还书成功时，刷新当前借阅的列表数据
            if (response.success == true) {
                window.location.href = getProjectPath()+"/ship/searchBerthing";
            }
        })
    }
}
//确认图书已经归还
function exitConfirm(id) {
    var r = confirm("Confirm??"+id);
    if (r) {
        var url = getProjectPath()+"/ship/exitConfirm?id=" + id;
        $.get(url, function (response) {
            alert(response.message)
            //还书确认成功时，刷新当前借阅的列表数据
            if (response.success == true) {
                window.location.href = getProjectPath()+"/ship/searchBerthing";
            }
        })
    }
}
//检查图书信息的窗口中，图书信息填写是否完整
function checkval(){
    var $inputs=$("#addOrEditTab input")
    var count=0;
    $inputs.each(function () {
        if($(this).val()==''||$(this).val()=="不能为空！"){
            count+=1;
        }
    })
    //如果全部输入框都填写完整，解除确认按钮的禁用状态
    if(count==0){
        $("#aoe").attr("disabled",false)
    }
}
//页面加载完成后，给图书模态窗口的输入框绑定失去焦点和获取焦点事件
$(function() {
    var $inputs=$("#addOrEditShip input")
    var esin="";
    $inputs.each(function () {
        //给输入框绑定失去焦点事件
        $(this).blur(function () {
            if($(this).val()==''){
                $("#aoe").attr("disabled",true)
                $(this).attr("style","color:red").val("不能为空！")
            }
            else if($(this).attr("name")=="sin"&&esin!==$(this).val()){
                if($(this).val().length==0){
                    $("#aoe").attr("disabled",true)
                    alert("船只识别号不能为空！")
                    $(this).val("")
                }
            }else{
                checkval()
            }
        }).focus(function () {
            if($(this).val()=='不能为空！'){
                $(this).attr("style","").val("")
            }else{
                $(this).attr("style","")
            }
            if($(this).attr("name")=="sin"){
                esin=$(this).val();
            }
        })
    })
});
//获取当前项目的名称
function getProjectPath() {
    //获取主机地址之后的目录，如： cloudlibrary/admin/ships.jsp
    var pathName = window.document.location.pathname;
    //获取带"/"的项目名，如：/cloudlibrary
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return  projectName;
}

/**
 * 数据展示页面分页插件的参数
 * cur 当前页
 * total 总页数
 * len   显示多少页数
 * pagesize 1页显示多少条数据
 * gourl 页码变化时 跳转的路径
 * targetId 分页插件作用的id
 */
var pageargs = {
    cur: 1,
    total: 0,
    len: 5,
    pagesize:10,
    gourl:"",
    targetId: 'pagination',
    callback: function (total) {
        var oPages = document.getElementsByClassName('page-index');
        for (var i = 0; i < oPages.length; i++) {
            oPages[i].onclick = function () {
                changePage(this.getAttribute('data-index'), pageargs.pagesize);
            }
        }
        var goPage = document.getElementById('go-search');
        goPage.onclick = function () {
            var index = document.getElementById('yeshu').value;
            if (!index || (+index > total) || (+index < 1)) {
                return;
            }
            changePage(index, pageargs.pagesize);
        }
    }
}
/**
 *船只查询栏的查询参数
 * sin 船舶识别号
 * captainName 船长名称
 * tonnage 吨位
 */
var shipVO={
    sin:'',
    captainName:'',
    tonnage:''
}
/**
 *记录查询栏的查询参数
 * sin 船舶识别号
 * captainName 图书作者
 */
var recordVO={
    shipSin:'',
    shipCaptain:'',
}
//数据展示页面分页插件的页码发送变化时执行
function changePage(pageNo,pageSize) {
    pageargs.cur=pageNo;
    pageargs.pagesize=pageSize;
    document.write("<form action="+pageargs.gourl +" method=post name=form1 style='display:none'>");
    document.write("<input type=hidden name='pageNum' value="+pageargs.cur+" >");
    document.write("<input type=hidden name='pageSize' value="+pageargs.pagesize+" >");
    //如果跳转的是船只信息查询的相关链接，提交查询栏中的参数
    if(pageargs.gourl.indexOf("ship")>=0){
        document.write("<input type=hidden name='sin' value="+shipVO.sin+" >");
        document.write("<input type=hidden name='captainName' value="+shipVO.captainName+" >");
        document.write("<input type=hidden name='tonnage' value="+shipVO.tonnage+" >");
    }
    //如果跳转的是船只记录查询的相关链接，提交记录查询栏中的参数
    if(pageargs.gourl.indexOf("record")>=0){
        document.write("<input type=hidden name='sin' value="+recordVO.shipSin+" >");
        document.write("<input type=hidden name='captainName' value="+recordVO.shipCaptain+" >");
    }
    document.write("</form>");
    document.form1.submit();
    pagination(pageargs);
}

