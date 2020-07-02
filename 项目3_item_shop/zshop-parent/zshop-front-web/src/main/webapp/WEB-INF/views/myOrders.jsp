<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>我的订单</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/layer/layer.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/zshop.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrapValidator.min.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrapValidator.min.js"></script>
    <script>
        function removeOrder(id) {
            $.post('${pageContext.request.contextPath}/front/order/removeOrder',
                {'id':id},
                function(result) {
                    if (result.status == 1) {
                        layer.msg(result.message, {
                            time:2000,
                            skin:'successMsg'
                        },function () {
                            location.href = '${pageContext.request.contextPath}/front/order/toOrder';
                        });
                    }
                    else {
                        layer.msg(result.message, {
                            time:2000,
                            skin:'errorMsg'
                        });
                    }
                });
        }
    </script>
</head>

<body>
<% request.setAttribute("index",1);%>
<jsp:include page="top.jsp"/>
<!-- content start -->
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <div class="page-header" style="margin-bottom: 0px;">
                <h3>我的订单</h3>
            </div>
        </div>
    </div>
    <table class="table table-hover   orderDetail">
        <c:forEach items="${myOrders}" var="myOrder">
            <tr>
                <td colspan="5">
                    <span>订单编号：<a href="${pageContext.request.contextPath}/front/order/toOrderDetail?id=${myOrder.order.id}">${myOrder.order.no}</a></span>
                    <span>成交时间：<fmt:formatDate value="${myOrder.order.createDate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></span>
                </td>
            </tr>
            <c:forEach items="${myOrder.itemList}" var="item">
                <tr>
                    <td><img src="${pageContext.request.contextPath}/front/product/showPic?image=${item.product.image}" alt=""></td>
                    <td class="order-content">
                        <p>
                            ${item.product.name}
                        </p>
                        <p>${item.product.info}</p>
                    </td>
                    <td>
                        ￥<fmt:formatNumber type="number" value="${item.price}" pattern="#.00"/>
                    </td>
                    <td>
                        ${item.num}
                    </td>
                    <td class="text-color">
                        ￥<fmt:formatNumber type="number" value="${item.price * item.num}" pattern="#.00"/>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="5">
                    <span class="pull-right"><button class="btn btn-danger" onclick="removeOrder(${myOrder.order.id})">删除订单</button></span>
                    <span class="">总计:<span class="text-color">￥<fmt:formatNumber type="number" value="${myOrder.order.price}" pattern="#.00"/></span></span>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<!-- content end-->
<!-- footers start -->
<div class="footers">
    版权所有：中兴软件技术
</div>
<!-- footers end -->
</body>
</html>
