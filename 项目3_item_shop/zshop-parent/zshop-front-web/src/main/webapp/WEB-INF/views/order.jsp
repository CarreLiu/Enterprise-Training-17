<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>确认订单</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/layer/layer.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/zshop.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrapValidator.min.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrapValidator.min.js"></script>
    <script>
        function toCart() {
            location.href = '${pageContext.request.contextPath}/front/cart/toCart';
        }

        //生成订单
        function generateOrder(){
            $.post('${pageContext.request.contextPath}/front/order/generateOrder',
                {'customerId':'${customer.id}', 'price':'${shoppingCart.totalMoney}'},
                function(result){
                    if(result.status==1){
                        $('#no').html(result.data.no);
                        $('#buildOrder').modal('show');
                    }
                    else{
                        layer.msg(result.message,{
                            time:2000,
                            skin:'errorMsg'
                        });
                    }
                });
        }
    </script>
</head>

<body>
<% request.setAttribute("index", 2);%>
<jsp:include page="top.jsp"/>
<!-- content start -->
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <div class="page-header" style="margin-bottom: 0px;">
                <h3>我的购物车</h3>
            </div>
        </div>
    </div>
    <table class="table table-hover table-striped table-bordered">
        <tr>
            <th>序号</th>
            <th>商品名称</th>
            <th>商品图片</th>
            <th>商品数量</th>
            <th>商品总价</th>
        </tr>
        <c:forEach items="${shoppingCart.items}" var="item" varStatus="s">
        <tr>
            <td>${s.count}</td>
            <td>${item.product.name}</td>
            <td><img src="${pageContext.request.contextPath}/front/product/showPic?image=${item.product.image}" alt="" width="60" height="60"></td>
            <td>${item.quantity}</td>
            <td>${item.itemMoney}</td>
        </tr>
        </c:forEach>
        <tr>
            <td colspan="5" class="foot-msg">
                总计：<b><span><fmt:formatNumber type="number" value="${shoppingCart.totalMoney}" pattern="#.00"/></span></b>元
                <a href="#">
                    <button class="btn btn-warning pull-right " onclick="toCart()">返回</button>
                </a>
                <button class="btn btn-warning pull-right margin-right-15" data-toggle="modal" onclick="generateOrder()">生成订单</button>
            </td>
        </tr>
    </table>
</div>
<!-- content end-->
<div class="modal fade" id="buildOrder" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">提示消息</h4>
            </div>
            <div class="orderMsg">
                <p>
                    订单生成成功！！
                </p>
                <p>
                    订单号：<span id="no"></span>
                </p>
            </div>
        </div>
    </div>
</div>
<!-- footers start -->
<div class="footers">
    版权所有：中兴软件技术
</div>
<!-- footers end -->
</body>

</html>
