<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>在线商城-后台管理系统</title>
    <link rel="stylesheet"  href="${pageContext.request.contextPath}/css/bootstrap.css" />
    <link rel="stylesheet"  href="${pageContext.request.contextPath}/css/index.css" />
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/userSetting.js"></script>
    <script src="${pageContext.request.contextPath}/layer/layer.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/zshop.css" />
    <script type="text/javascript">
        $(function(){
            // 点击切换页面
            $("#product-type-set").click(function() {
                $("#frame-id").attr("src", "${pageContext.request.contextPath}/backend/productType/findAll");
            });
            $("#product-set").click(function() {
                $("#frame-id").attr("src", "${pageContext.request.contextPath}/backend/product/findAll");
            });
            $("#user-set").click(function() {
                $("#frame-id").attr("src", "${pageContext.request.contextPath}/backend/customer/findAll");
            });
            $("#manager-set").click(function() {
                $("#frame-id").attr("src", "${pageContext.request.contextPath}/backend/sysuser/findAll");
            });
        });

        //退出
        function loginOut(){
            $.post('${pageContext.request.contextPath}/backend/sysuser/loginOut',
                function(result){
                    if (result.status == 1){
                        layer.msg(result.message,{
                            time:2000,
                            skin:'successMsg'
                        }, function () {
                            location.href = '${pageContext.request.contextPath}/backend/sysuser/toLogin';
                        });
                    }
                });
        }
    </script>
</head>

<body>
<div class="wrapper-cc clearfix">
    <div class="content-cc">
        <!-- header start -->
        <div class="clear-bottom head">
            <div class="container head-cc">
                <p>在线商城<span>后台管理系统</span></p>
                <div class="welcome">
                    <div class="left">欢迎您：&nbsp;&nbsp;</div>
                    <div class="right">${sysuser.name}</div>
                    <div class="exit btn-link" style="color: white; cursor: pointer;" onclick="loginOut()">退出</div>
                </div>
            </div>
        </div>
        <!-- header end -->
        <!-- content start -->
        <div class="container-flude flude-cc" id="a">
            <div class="row user-setting">
                <div class="col-xs-2 user-wrap">
                    <ul class="list-group">
                        <li class="list-group-item active" name="userSet" id="product-type-set" style="cursor: pointer">
                            <i class="glyphicon glyphicon-lock"></i> &nbsp;商品类型管理
                        </li>
                        <li class="list-group-item" name="userPic" id="product-set" style="cursor: pointer">
                            <i class="glyphicon glyphicon-facetime-video"></i> &nbsp;商品管理
                        </li>
                        <li class="list-group-item" name="userInfo" id="user-set" style="cursor: pointer">
                            <i class="glyphicon glyphicon-user"></i> &nbsp;客户管理
                        </li>
                        <li class="list-group-item" name="adminSet" id="manager-set" style="cursor: pointer">
                            <i class="glyphicon glyphicon-globe"></i> &nbsp;系统用户管理
                        </li>
                    </ul>
                </div>
                <div class="col-xs-10" id="userPanel">
                    <iframe id="frame-id" src="${pageContext.request.contextPath}/backend/productType/findAll" width="100%" height="100%" frameborder="0" scrolling="no">
                    </iframe>
                </div>

            </div>
        </div>
        <!-- content end-->
    </div>
</div>
<!-- footers start -->
<div class="footer">
    版权所有：中兴软件技术
</div>
<!-- footers end -->
</body>

</html>