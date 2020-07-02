<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>backend</title>
    <link rel="stylesheet"  href="${pageContext.request.contextPath}/css/bootstrap.css" />
    <link rel="stylesheet"  href="${pageContext.request.contextPath}/css/index.css" />
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/userSetting.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-paginator.js"></script>
    <script src="${pageContext.request.contextPath}/layer/layer.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/zshop.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrapValidator.min.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrapValidator.min.js"></script>
    <script type="text/javascript">
        $(function () {

            //服务器端校验
            let successMsg = '${successMsg}';
            let errorMsg = '${errorMsg}';
            if (successMsg != '') {
                layer.msg(successMsg,{
                    time:2000,
                    skin:'successMsg'
                });
            }
            if (errorMsg != '') {
                layer.msg(errorMsg, {
                    time:2000,
                    skin:'errorMsg'
                });
            }

            //分页
            $('#pagination').bootstrapPaginator({
                //主版本号
                bootstrapMajorVersion: 3,
                //当前页
                currentPage:${pageInfo.pageNum},
                //总页数
                totalPages:${pageInfo.pages},
                onPageClicked:function(event, originalEvent, type, page) {
                    //给隐藏表单域赋值
                    $('#pageNum').val(page);
                    //重新提交表单
                    $('#frmQuery').submit();
                },
                //显示分页条信息
                itemTexts: function (type, page, current) {
                    switch (type) {
                        case "first":
                            return "首页";
                        case "prev":
                            return "上一页";
                        case "next":
                            return "下一页";
                        case "last":
                            return "尾页";
                        case "page":
                            return page;
                    }
                }

            });

            //客户端校验
            $('#frmModifyCustomer').bootstrapValidator({
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields:{
                    name: {
                        validators: {
                            notEmpty: {
                                message: '用户姓名不能为空'
                            }
                        }

                    },
                    loginName: {
                        validators: {
                            notEmpty: {
                                message: '用户账号不能为空'
                            },
                            remote: {
                                url: '${pageContext.request.contextPath}/backend/customer/checkName',
                                type: 'post',
                                data: {
                                    primeLoginName: function () {
                                        return $('#primeLoginName').val();
                                    }
                                }
                            }
                        }
                    },
                    phone:{
                        validators:{
                            notEmpty:{
                                message:'电话号码不能为空'
                            }
                        }
                    },
                    address:{
                        validators:{
                            notEmpty:{
                                message:'地址不能为空'
                            }
                        }
                    }
                }
            });

        });

        //显示修改系统用户模态框
        function showCustomer(id) {
            $.post("${pageContext.request.contextPath}/backend/customer/findById",
                {"id":id},
                function(result) {
                    //如果成功，将值直接写入模态框对应元素中
                    if (result.status == 1) {
                        $('#id').val(result.data.id);
                        $('#name').val(result.data.name);
                        $('#loginName').val(result.data.loginName);
                        $('#primeLoginName').val(result.data.loginName);
                        $('#phone').val(result.data.phone);
                        $('#adrees').val(result.data.address);
                    }
                }
            );

        }

        //按条件查询
        function doQuery() {
            //重置当前页为第一页
            $('#pageNum').val(1);
            //提交表单
            $('#frmQuery').submit();
        }

        //修改顾客状态
        function modifyStatus(id, btn) {
            $.post('${pageContext.request.contextPath}/backend/customer/modifyStatus',
                {"id":id},function(result) {
                    if (result.status == 1) {
                        let $td = $(btn).parent().parent().children().eq(5);
                        if ($td.text().trim() == '有效') {
                            $td.text('无效');
                            $(btn).val('启用').removeClass('btn-danger').addClass('btn-success');
                        }
                        else {
                            $td.text('有效');
                            $(btn).val('禁用').removeClass('btn-success').addClass('btn-danger');
                        }
                    }
                });
        }

    </script>
</head>

<body>
<div class="panel panel-default" id="userInfo" id="homeSet">
    <div class="panel-heading">
        <h3 class="panel-title">客户管理</h3>
    </div>
    <div class="panel-body">
        <div class="showusersearch">
            <form class="form-inline" action="${pageContext.request.contextPath}/backend/customer/findByParams" method="post" id="frmQuery">
                <input type="hidden" name="pageNum" value="${pageInfo.pageNum}" id="pageNum">
                <div class="form-group">
                    <label for="customer_name">姓名:</label>
                    <input type="text" class="form-control"id="customer_name" name="name" placeholder="请输入姓名" size="15px" value="${customerParam.name}">
                </div>
                <div class="form-group">
                    <label for="customer_loginName">帐号:</label>
                    <input type="text" class="form-control" id="customer_loginName" name="loginName" placeholder="请输入帐号" size="15px" value="${customerParam.loginName}">
                </div>
                <div class="form-group">
                    <label for="customer_phone">电话:</label>
                    <input type="text" class="form-control" id="customer_phone" name="phone" placeholder="请输入电话" size="15px" value="${customerParam.phone}">
                </div>
                <div class="form-group">
                    <label for="customer_address">地址:</label>
                    <input type="text" class="form-control" id="customer_address" name="address" placeholder="请输入地址" value="${customerParam.address}">
                </div>
                <div class="form-group">
                    <label for="customer_isValid">状态:</label>
                    <select class="form-control" id="customer_isValid" name="isValid">
                        <option value="-1">全部</option>
                        <option value="1" <c:if test="${customerParam.isValid == 1}">selected</c:if>>---有效---</option>
                        <option value="0" <c:if test="${customerParam.isValid == 0}">selected</c:if>>---无效---</option>
                    </select>
                </div>
                <input type="button" value="查询" class="btn btn-primary" id="doSearch" onclick="doQuery()">
            </form>
        </div>

        <div class="show-list text-center" style="position: relative;top: 30px;">
            <table class="table table-bordered table-hover" style='text-align: center;'>
                <thead>
                <tr class="text-danger">
                    <th class="text-center">序号</th>
                    <th class="text-center">姓名</th>
                    <th class="text-center">帐号</th>
                    <th class="text-center">电话</th>
                    <th class="text-center">地址</th>
                    <th class="text-center">状态</th>
                    <th class="text-center">操作</th>
                </tr>
                </thead>
                <tbody id="tb">
                <c:forEach items="${pageInfo.list}" var="customer">
                    <tr>
                        <td>${customer.id}</td>
                        <td>${customer.name}</td>
                        <td>${customer.loginName}</td>
                        <td>${customer.phone}</td>
                        <td>${customer.address}</td>
                        <td>
                            <c:if test="${customer.isValid == 1}">有效</c:if>
                            <c:if test="${customer.isValid == 0}">无效</c:if>
                        </td>
                        <td class="text-center">
                            <input type="button" class="btn btn-warning btn-sm doModify" value="修改" onclick="showCustomer(${customer.id})">
                            <c:if test="${customer.isValid == 1}">
                                <input type="button" class="btn btn-danger btn-sm doDisable" value="禁用" onclick="modifyStatus(${customer.id},this)">
                            </c:if>
                            <c:if test="${customer.isValid == 0}">
                                <input type="button" class="btn btn-success btn-sm doDisable" value="启用" onclick="modifyStatus(${customer.id},this)">
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <ul id="pagination"></ul>
        </div>
    </div>
</div>

<!-- 修改客户信息 start -->
<div class="modal fade" tabindex="-1" id="myModal">
    <!-- 窗口声明 -->
    <div class="modal-dialog">
        <!-- 内容声明 -->
        <form action="${pageContext.request.contextPath}/backend/customer/modify" class="form-horizontal" method="post" id="frmModifyCustomer">
            <input type="hidden" name="pageNum" value="${data.pageNum}">
            <input type="hidden" name="id" id="id">
            <input type="hidden" name="primeLoginName" id="primeLoginName">
            <div class="modal-content">
                <!-- 头部、主体、脚注 -->
                <div class="modal-header">
                    <button class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">修改客户</h4>
                </div>
                <div class="modal-body text-center">
                    <div class="row text-right">
                        <label for="name" class="col-sm-4 control-label">姓名：</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="name" name="name">
                        </div>
                    </div>
                    <br>
                    <div class="row text-right">
                        <label for="loginName" class="col-sm-4 control-label">帐号：</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="loginName" name="loginName">
                        </div>
                    </div>
                    <br>
                    <div class="row text-right">
                        <label for="phone" class="col-sm-4 control-label">电话：</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="phone" name="phone">
                        </div>
                    </div>
                    <br>
                    <div class="row text-right">
                        <label for="adrees" class="col-sm-4 control-label">地址：</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="adrees" name="address">
                        </div>
                    </div>
                    <br>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary updateOne" type="submit">修改</button>
                    <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                </div>
            </div>
        </form>
    </div>
</div>
<!-- 修改客户信息 end -->
</body>

</html>
