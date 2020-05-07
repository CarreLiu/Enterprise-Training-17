<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ITing金融</title>
<link href="${pageContext.request.contextPath}/css/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<!-- art-template -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/template-web.js"></script>
       
<!-- jqueryPage -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jqueryPage.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jqueryPage.css">

<script type="text/javascript">	
	//页面加载完成后调用的函数
	$(document).ready(function(){
		//当前时间
		getCurrentTime();
		//找到id为choise的元素下面的所有tr元素
		//遍历这些tr，执行调用函数
		$("#choices tr").each(function() {
		    //给当前tr下所有的a元素绑定click事件，执行调用函数
			$(this).find('a').click(function(){
					//将当前行的所有a去cur样式
					$(this).parent().parent().find('a').removeClass('cur');
					//将当前a元素设置cur样式
					$(this).addClass('cur');
					//TODO: 异步加载数据
			});
		});
	});
	
	//当前时间
	function getCurrentTime() {
		var date = new Date();
		var dateForm = dateFormat(date, "yyyy年MM月dd日 hh:mm:ss");
		$("#currentTime").html(dateForm);
		window.setTimeout("getCurrentTime()", 1000);
	}
	
	//时间戳格式化处理
	function dateFormat(date, format) {
		date = new Date(date);
		var map = {
			"M" : date.getMonth() + 1, //月份
			"d" : date.getDate(), //日
			"h" : date.getHours(), //小时
			"m" : date.getMinutes(), //分
			"s" : date.getSeconds(), //秒
			"q" : Math.floor((date.getMonth() + 3) / 3), //季度
			"S" : date.getMilliseconds() //毫秒
		};
		format = format.replace(/([yMdhmsqS])+/g, function(all, t) {
			var v = map[t];
			if (v !== undefined) {
				if (all.length > 1) {
					v = '0' + v;
					v = v.substr(v.length - 2);
				}
				return v;
			} else if (t === 'y') {
				return (date.getFullYear() + '').substr(4 - all.length);
			}
			return all;
		});
		return format;
	}
	
	function isEmpty(val) {
        if (val == null || val == '' || val.typeOf == 'undefined') {
            return true;
        }
        return false;
    }

    /*发送ajax请求获取数据*/
    function loadData(pageNo, pageSize) {
        pageNo = !isEmpty(pageNo) && !isNaN(pageNo) && pageNo > 0 ? pageNo : 1;
        pageSize = !isEmpty(pageSize) && !isNaN(pageSize) && pageSize > 0 ? pageSize : 2;

        $.ajax({
            url: '${pageContext.request.contextPath}/front/query.do',
            type: 'post',
            data : {"pageNo":pageNo,"pageSize":pageSize},
            dataType:'json',
            success: function(data){
                var pageNo = data.pageNum;
                var totalPage = data.pages;
                var list = {
                    products: data.list,
                };
               	//console.log(list);
               	//console.log(list.products);
                var html = template('data-template', list);
                $('#data-box').html(html);

                // 数据加载完成后进行分页器的初始化
                initPaginator(pageNo, totalPage);
            }
        });

        
    }

    /*初始化分页器*/
    function initPaginator(pageNo, totalPage) {


        // jqueryPage插件
        $('.tcdPageCode').createPage({
            current: pageNo,
            pageCount: totalPage,
            backFn: function(p) {
                loadData(p, '');
            }
        });

    }

    $(function() {
        loadData('${pageNo}');
    });

</script>
</head>

<body>
<div class="wrap header oauto clear">
    <div class="fr">
    	<a href="#" >
    		当前时间：<label id="currentTime"></label>
    	</a>
    </div>
</div>
<div class="logo-nav">
    <div class="wrap oauto">
        <div class="clear">
            <div class="fl"><img src="${pageContext.request.contextPath}/images/logo.png" /></div>
        </div>
    </div>
</div>
<div class="menu">
    <ul class="clear">
        <li><a href="${pageContext.request.contextPath}/front/index_ajax1.do" class="cur">融资产品</a></li>
        <li><a href="${pageContext.request.contextPath}/front/news.do">每日新闻</a></li>
    </ul>
</div>


<div class="wrap bank_list">
	<div class="title clear"><h1 class="fl">产品分类</h1></div>
    <div class="bank_list_tp">
      <table width="100%" border="0" cellpadding="0" cellspacing="0" id="choices">
        <tr class="bank_list_tp1">
          <td width="10%" align="right">产品类型：</td>
          <td width="12%" align="center" valign="top" class="qb"><a href="javascript:;" class="cur">全部</a></td>
          <td width="78%" class="txt clear">
          <c:forEach items="${typeList}" var="type">
          	<a href="javascript:;">${type.productTypeName}</a>
          </c:forEach>
          </td>
        </tr>
        <tr class="bank_list_tp2">
          <td align="right">贷款周期：</td>
          <td align="center" valign="top" class="qb"><a href="javascript:;" class="cur">全部</a></td>
          <td class="txt clear">
          <c:forEach items="${periodList}" var="lend">
          	<a href="javascript:;">${lend.period}</a>
          </c:forEach>
          </td>
        </tr>
        <tr class="bank_list_tp3">
          <td align="right">贷款利率：</td>
          <td align="center" valign="top" class="qb"><a href="javascript:;" class="cur">全部</a></td>
          <td class="txt clear"><a href="javascript:;">6%以下</a><a href="javascript:;">6%-8%</a><a href="javascript:;">8%-10%</a><a href="javascript:;">10%-12%</a><a href="javascript:;">12%-14%</a><a href="javascript:;">14%以上</a></td>
        </tr>
        <tr class="bank_list_tp4">
          <td align="right">贷款规模：</td>
          <td align="center" valign="top" class="qb"><a href="javascript:;" class="cur">全部</a></td>
          <td class="txt clear"><a href="javascript:;">100万以下</a><a href="javascript:;">100万-200万</a><a href="javascript:;">200万-300万</a><a href="javascript:;">300万-500万</a><a href="javascript:;">500万-1000万</a><a href="javascript:;">1000万以上</a></td>
        </tr>
      </table>
    </div>
</div>
<div class="wrap project_release">
	<div class="project_release_tp">融资产品</div>
    <div id="data-box" class="project_release_bt" >
    	<script type="text/html" id="data-template">
    	<ul>
			{{each products as product}}
	    		<li class="clear">
	            	<div class="fl"><img src="${pageContext.request.contextPath}/images/jsyh.png"  width="75px" height="40px" class="pr_logo" /></div>
	                <div class="fl">
	                	<dl>
	                    	<dt>产品名称：<span class="king">{{product.productName}}</span></dt>
	                    	<dt>所属银行：{{product.company.companyName}}</dt>                    	
	                      <dt>产品类别：{{product.productType.productTypeName}}</dt>
	                      <dt>放贷利率：{{product.primeLendingRateFrom}}%-{{product.primeLendingRateTo}}%（年化利率）</dt>
	                      <dt>放贷金额：{{product.financingAmountFrom}}-{{product.financingAmountTo}}万</dt>
	                      <dt>放贷期限：{{product.lendingPeriod.period}}</dt>
	                    </dl>
	                </div>
	                <div class="view_details"><a href="${pageContext.request.contextPath}/front/product.do?productId={{product.productId}}">查看详细</a></div>
	            </li>
			{{/each}}
    	</ul>
    	</script>
    </div>
</div>
<!-- jqueryPage, class值固定 -->
<div class="tcdPageCode"></div>

<div class="footer">Copyright &copy; 2020 中兴软件技术  版权所有 </div>
</body>
</html>
