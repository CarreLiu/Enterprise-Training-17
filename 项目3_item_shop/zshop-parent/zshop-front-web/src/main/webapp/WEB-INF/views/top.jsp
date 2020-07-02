<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="${pageContext.request.contextPath}/js/template.js"></script>
<script id="welcome" type="text/html">
    <li class="userName">
        您好：{{name}}
    </li>
    <li class="dropdown">
        <a href="#" class="dropdown-toggle user-active" data-toggle="dropdown" role="button">
            <img class="img-circle" src="${pageContext.request.contextPath}/images/user.jpeg" height="30" />
            <span class="caret"></span>
        </a>
        <ul class="dropdown-menu">
            <li>
                <a href="#" data-toggle="modal" data-target="#modifyPasswordModal">
                    <i class="glyphicon glyphicon-cog"></i>修改密码
                </a>
            </li>
            <li>
                <a href="#" onclick="loginOut()">
                    <i class="glyphicon glyphicon-off"></i> 退出
                </a>
            </li>
        </ul>
    </li>
</script>
<script id="loginOut" type="text/html">
    <li>
        <a href="#" data-toggle="modal" data-target="#loginModal">登陆</a>
    </li>
    <li>
        <a href="#" data-toggle="modal" data-target="#registModal">注册</a>
    </li>
</script>
<html>
<head>
    <title></title>
    <script>
        $(function(){
            //正确高亮显示导航栏
            //获取当前页面的索引
            let curIndex = ${index};
            $('ul#nav1 li').each(function(i){
                //将所有导航栏的背景清除
                $(this).removeClass("active");
                //如果就是当前页，其背景高亮显示
                if(curIndex == i){
                    $(this).addClass("active");
                }
            });

            //客户端校验
            $('#frmAddCustomer').bootstrapValidator({
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
                    loginName:{
                        validators:{
                            notEmpty:{
                                message:'登录账号不能为空'
                            },
                            remote:{
                                url:'${pageContext.request.contextPath}/front/customer/checkName'
                            }
                        }
                    },
                    password:{
                        validators:{
                            notEmpty:{
                                message:'密码不能为空'
                            }
                        }
                    },
                    phone:{
                        validators:{
                            notEmpty:{
                                message:'联系电话不能为空'
                            }
                        }
                    },
                    address:{
                        validators:{
                            notEmpty:{
                                message:'联系地址不能为空'
                            }
                        }
                    }
                }
            });

            //客户端校验
            $('#frmModifyPassword').bootstrapValidator({
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields:{
                    oldPassword: {
                        validators: {
                            notEmpty: {
                                message: '原密码不能为空'
                            }
                        }
                    },
                    password: {
                        validators: {
                            notEmpty: {
                                message: '新密码不能为空'
                            }
                        }
                    },
                    rePassword: {
                        validators: {
                            notEmpty: {
                                message: '重复密码不能为空'
                            },
                            identical: {
                                field: 'password',
                                message: '两次输入的密码不一致'
                            }
                        }
                    }
                }
            });
        });

        //登录
        function loginByAccount() {
            $.post('${pageContext.request.contextPath}/front/customer/loginByAccount',
                $('#frmLoginByAccount').serialize(),
                function (result) {
                    //登录成功
                    if (result.status == 1) {
                        //局部刷新页面
                        //通过template引擎获取整段html页面
                        let content = template('welcome', result.data);
                        //将登录模态框关闭
                        $('#loginModal').modal('hide');
                        //将该html片段写到对应节点
                        $('#navInfo').html(content);
                    }
                    //登陆失败
                    else {
                        $('#loginInfo').html(result.message);
                    }
                });
        }

        //修改密码
        function modifyPassword() {
            $('#id').val('${customer.id}');
            //进行表单验证
            //获取需要校验的表单
            let bv = $('#frmModifyPassword').data('bootstrapValidator');
            //开始校验，返回boolean值
            bv.validate();
            //如果校验通过，执行post请求完成添加动作
            if (bv.isValid()) {
                $.post('${pageContext.request.contextPath}/front/customer/modifyPassword',
                    $('#frmModifyPassword').serialize(),
                    function (result) {
                        if (result.status == 1) {
                            layer.msg(result.message,
                                {
                                    time:2000,
                                    skin:'successMsg'
                                }, function() {
                                    location.href='${pageContext.request.contextPath}/front/product/main';
                                });
                        }
                        else {
                            layer.msg(result.message,
                                {
                                    time:2000,
                                    skin:'errorMsg'
                                });
                        }
                    });
            }
        }

        //注册客户
        function addCustomer() {
            //进行表单验证
            //获取需要校验的表单
            let bv = $('#frmAddCustomer').data('bootstrapValidator');
            //开始校验，返回boolean值
            bv.validate();
            //如果校验通过，执行post请求完成添加动作
            if (bv.isValid()) {
                $.post('${pageContext.request.contextPath}/front/customer/add',
                    $('#frmAddCustomer').serialize(),
                    function (result) {
                        if (result.status == 1) {
                            layer.msg(result.message,
                                {
                                    time:2000,
                                    skin:'successMsg'
                                }, function() {
                                    location.href='${pageContext.request.contextPath}/front/product/main';
                                });
                        }
                        else {
                            layer.msg(result.message,
                                {
                                    time:2000,
                                    skin:'errorMsg'
                                });
                        }
                    });
            }
        }

        //退出
        function loginOut(){
            $.post('${pageContext.request.contextPath}/front/customer/loginOut',
                    function(result){
                        if (result.status == 1){
                            //局部刷新页面
                            let content = template('loginOut');

                            //将该html片段写到对应节点
                            $('#navInfo').html(content);
                        }
                    });
        }
    </script>
</head>
<body>
    <!-- navbar start -->
    <div class="navbar navbar-default clear-bottom">
        <div class="container">
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav" id="nav1">
                    <li class="active">
                        <a href="${pageContext.request.contextPath}/front/product/main">商城主页</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/front/order/toOrder">我的订单</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/front/cart/toCart">购物车</a>
                    </li>
                    <li class="dropdown">
                        <a href="${pageContext.request.contextPath}/front/customer/toCenter">会员中心</a>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right" id="navInfo">
                    <c:choose>
                        <c:when test="${empty customer}">
                            <li>
                                <a href="#" data-toggle="modal" data-target="#loginModal">登陆</a>
                            </li>
                            <li>
                                <a href="#" data-toggle="modal" data-target="#registModal">注册</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="userName">
                                您好：${customer.name}！
                            </li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle user-active" data-toggle="dropdown" role="button">
                                    <img class="img-circle" src="${pageContext.request.contextPath}/images/user.jpeg" height="30" />
                                    <span class="caret"></span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a href="#" data-toggle="modal" data-target="#modifyPasswordModal">
                                            <i class="glyphicon glyphicon-cog"></i>修改密码
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#" onclick="loginOut()">
                                            <i class="glyphicon glyphicon-off"></i> 退出
                                        </a>
                                    </li>
                                </ul>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </div>
    </div>
    <!-- navbar end -->

    <!-- 修改密码模态框 start -->
    <div class="modal fade" id="modifyPasswordModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">修改密码</h4>
                </div>
                <form id="frmModifyPassword" class="form-horizontal" method="post">
                    <input type="hidden" name="id" id="id">
                    <div class="modal-body">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">原密码：</label>
                            <div class="col-sm-6">
                                <input class="form-control" type="password" name="oldPassword">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">新密码：</label>
                            <div class="col-sm-6">
                                <input class="form-control" type="password" name="password">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">重复密码：</label>
                            <div class="col-sm-6">
                                <input class="form-control" type="password" name="rePassword">
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-warning" data-dismiss="modal" aria-label="Close">关&nbsp;&nbsp;闭</button>
                        <button type="reset" class="btn btn-warning">重&nbsp;&nbsp;置</button>
                        <button type="button" class="btn btn-warning" onclick="modifyPassword()">修&nbsp;&nbsp;改</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- 修改密码模态框 end -->

    <!-- 登录模态框 start  -->
    <div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <!-- 用户名密码登陆 start -->
            <div class="modal-content" id="login-account">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">用户名密码登录&nbsp;&nbsp;<small class="text-danger" id="loginInfo"></small></h4>
                </div>
                <form action="" class="form-horizontal" method="post" id="frmLoginByAccount">
                    <div class="modal-body">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">用户名：</label>
                            <div class="col-sm-6">
                                <input class="form-control" type="text" placeholder="请输入用户名" name="loginName">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">密&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
                            <div class="col-sm-6">
                                <input class="form-control" type="password" placeholder="请输入密码" name="password">
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer" style="text-align: center">
                        <a class="btn-link">忘记密码？</a> &nbsp;
                        <button type="button" class="btn btn-warning" data-dismiss="modal" aria-label="Close">关&nbsp;&nbsp;闭</button>
                        <button type="button" class="btn btn-warning" onclick="loginByAccount()">登&nbsp;&nbsp;陆</button> &nbsp;&nbsp;
                        <a class="btn-link" id="btn-sms-back">短信快捷登录</a>
                    </div>
                </form>
            </div>
            <!-- 用户名密码登陆 end -->
            <!-- 短信快捷登陆 start -->
            <div class="modal-content" id="login-sms" style="display: none;">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">短信快捷登陆</h4>
                </div>
                <form class="form-horizontal" method="post">
                    <div class="modal-body">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">手机号：</label>
                            <div class="col-sm-6">
                                <input class="form-control" type="text" placeholder="请输入手机号">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">验证码：</label>
                            <div class="col-sm-4">
                                <input class="form-control" type="text" placeholder="请输入验证码">
                            </div>
                            <div class="col-sm-2">
                                <button class="pass-item-timer" type="button">发送验证码</button>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer" style="text-align: center">
                        <a class="btn-link">忘记密码？</a> &nbsp;
                        <button type="button" class="btn btn-warning" data-dismiss="modal" aria-label="Close">关&nbsp;&nbsp;闭</button>
                        <button type="submit" class="btn btn-warning">登&nbsp;&nbsp;陆</button> &nbsp;&nbsp;
                        <a class="btn-link" id="btn-account-back">用户名密码登录</a>
                    </div>
                </form>
            </div>
            <!-- 短信快捷登陆 end -->
        </div>
    </div>
    <!-- 登录模态框 end  -->

    <!-- 注册模态框 start -->
    <div class="modal fade" id="registModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">会员注册</h4>
                </div>
                <form id="frmAddCustomer" class="form-horizontal" method="post">
                    <div class="modal-body">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">用户姓名:</label>
                            <div class="col-sm-6">
                                <input class="form-control" type="text" name="name">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">登陆账号:</label>
                            <div class="col-sm-6">
                                <input class="form-control" type="text" name="loginName">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">登录密码:</label>
                            <div class="col-sm-6">
                                <input class="form-control" type="password" name="password">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">联系电话:</label>
                            <div class="col-sm-6">
                                <input class="form-control" type="text" name="phone">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">联系地址:</label>
                            <div class="col-sm-6">
                                <input class="form-control" type="text" name="address">
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-warning" data-dismiss="modal" aria-label="Close">关&nbsp;&nbsp;闭</button>
                        <button type="reset" class="btn btn-warning">重&nbsp;&nbsp;置</button>
                        <button type="button" class="btn btn-warning" onclick="addCustomer()">注&nbsp;&nbsp;册</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- 注册模态框 end -->
</body>
</html>
