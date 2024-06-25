<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- 船只信息的模态窗口，默认是隐藏的 -->
<div class="modal fade" id="berthModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 id="myModalLabel">船只信息</h3>
            </div>
            <div class="modal-body">
                <form id="berthShip">
                    <table class="table table-bordered table-striped" width="800px">
                        <%--船只的id，不展示在页面--%>
                        <span><input type="hidden" id="id" name="id"></span>
                        <tr>
                            <td>船只识别号</td>
                            <td><input class="form-control" readonly name="sin" id="sin"></td>
                            <td>船只型号</td>
                            <td><input class="form-control" readonly name="model" id="model"></td>
                        </tr>
                        <tr>
                            <td>吨位</td>
                            <td><input class="form-control" readonly name="tonnage" id="tonnage"></td>
                            <td>船只船长</td>
                            <td><input class="form-control" readonly name="captainName" id="captainName"></td>
                        </tr>
                        <tr>
                            <td>出港时间<br/><span style="color: red">*</span></td>
                            <%--时间控件中的内容改变时，调用js文件中的cg()方法--%>
                            <td><input class="form-control" type="date" name="exitTime" id="exitTime" onchange="cg()">
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
            <div class="modal-footer">
                <%--点击保存按钮时，隐藏模态窗口并调用js文件中的borrow()方法--%>
                <button class="btn bg-blue-gradient btn-xs" data-dismiss="modal" aria-hidden="true" onclick="berth()"
                        disabled="true" id="savemsg">保存
                </button>
                <button class="btn bg-gray btn-xs" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
        </div>
    </div>
</div>
