<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>在线购物商城</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/iconfont/iconfont.css">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/zshop.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-paginator.js"></script>
    <script src="${pageContext.request.contextPath}/layer/layer.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/zshop.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrapValidator.min.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrapValidator.min.js"></script>
    <script type="text/javascript">
        $(function () {

            if ('${customer}' == '') {
                layer.msg("请先登录再进行操作", {
                    time: 2000,
                    skin: 'errorMsg'
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
                //单击条目执行的请求url
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
        });

        //按条件查询
        function doQuery() {
            //重置当前页为第一页
            $('#pageNum').val(1);
            //提交表单
            $('#frmQuery').submit();
        }

        //添加商品到购物车
        function addToCart(id){
            $.post('${pageContext.request.contextPath}/front/cart/addToCart',
                {'id':id},
                function (result){
                    if (typeof(result.status) == "undefined") {
                        layer.msg("请先登录，再进行购物", {
                            time:2000,
                            skin:'errorMsg'
                        })
                    }
                    else if(result.status == 1){
                        layer.msg(result.message,{
                            time:2000,
                            skin:'successMsg'
                        });

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
<% request.setAttribute("index",0);%>
    <div id="wrapper">
        <jsp:include page="top.jsp"/>
        <!-- content start -->
        <div class="container">
            <div class="row">
                <div class="col-xs-12">
                    <div class="page-header" style="margin-bottom: 0px;">
                        <h3>商品列表</h3>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-xs-12">
                    <form class="form-inline hot-search" action="${pageContext.request.contextPath}/front/product/main" method="post" id="frmQuery">
                        <input type="hidden" name="pageNum" value="${pageInfo.pageNum}" id="pageNum">
                        <div class="form-group">
                            <label class="control-label">商品：</label>
                            <input type="text" class="form-control" placeholder="商品名称" name="name" value="${productParam.name}">
                        </div>
                        &nbsp;
                        <div class="form-group">
                            <label class="control-label">价格：</label>
                            <input type="text" class="form-control" placeholder="最低价格" name="minPrice" value="${productParam.minPrice}"> --
                            <input type="text" class="form-control" placeholder="最高价格" name="maxPrice" value="${productParam.maxPrice}">
                        </div>
                        &nbsp;
                        <div class="form-group">
                            <label class="control-label">种类：</label>
                            <select class="form-control input-sm" name="productTypeId">
                                <option value="-1" selected="selected">查询全部</option>
                                <c:forEach items="${productTypes}" var="productType">
                                    <option value="${productType.id}"
                                            <c:if test="${productType.id == productParam.productTypeId}">selected</c:if>
                                    >${productType.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        &nbsp;
                        <div class="form-group">
                            <button type="button" class="btn btn-warning" id="doSearch" onclick="doQuery()">
                                <i class="glyphicon glyphicon-search"></i> 查询
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="content-back">
            <div class="container text-center" id="a">
                <div class="row">
                    <c:forEach items="${pageInfo.list}" var="product">
                    <div class="col-xs-3  hot-item">
                        <div class="panel clear-panel">
                            <div class="panel-body">
                                <div class="art-back clear-back">
                                    <div class="add-padding-bottom">
                                        <img src="${pageContext.request.contextPath}/front/product/showPic?image=${product.image}" class="shopImg">
                                    </div>
                                    <h4><a href="">${product.name}</a></h4>
                                    <div class="user clearfix pull-right">
                                        ￥${product.price}
                                    </div>
                                    <div class="desc">${product.info}
                                    </div>
                                    <div class="attention pull-right" onclick="addToCart(${product.id})">
                                        加入购物车 <i class="icon iconfont icon-gouwuche"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    </c:forEach>
                </div>
                <ul id="pagination"></ul>
            </div>
        </div>
        <!-- content end-->
        <!-- footers start -->
        <div class="footers">
            版权所有：中兴软件技术
        </div>
        <!-- footers end -->
    </div>
</body>

</html>