<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="now" class="java.util.Date" scope="page"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ITing金融-每日新闻</title>
<link href="${pageContext.request.contextPath}/css/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<!-- art-template -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/template-web.js"></script>
  
<!-- jqueryPage -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jqueryPage.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jqueryPage.css">
	
<script type="text/javascript">
	$(document).ready(function(){
		//当前时间
		getCurrentTime();
	});
	
	//当前时间
	function getCurrentTime() {
		var date = new Date();
		var dateForm = dateFormat(date, "yyyy年MM月dd日 hh:mm:ss");
		$("#currentTime").html(dateForm);
		window.setTimeout("getCurrentTime()", 1000);
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
			url : '${pageContext.request.contextPath}/front/commonNews.do',
			type : 'post',
			data : {
				"pageNo" : pageNo,
				"pageSize" : pageSize
			},
			dataType : 'json',
			success : function(data) {
				var pageNo = data.pageNum;
				var totalPage = data.pages;
				var list = {
					commonNewsList : data.list,
					pageNo : pageNo
				};
				//console.log(list);
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
			current : pageNo,
			pageCount : totalPage,
			backFn : function(p) {
				loadData(p, '');
			}
		});

	}

	$(function() {
		loadData('${pageNo}');
	});
	
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
	
	//新闻七天即结束
	function isOver(date) {
		let now = new Date();
		let before = new Date(date);
		dist = (now.getTime() - before.getTime())/1000/60/60/24;
		if (dist < 7)
			return "(成功举办)";
		return "(已结束)";
	}
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
        <li><a href="${pageContext.request.contextPath}/front/index_ajax1.do">融资产品</a></li>
        <li><a href="${pageContext.request.contextPath}/front/news.do" class="cur">每日新闻</a></li>
    </ul>
</div>


<div class="wrap font16">首页 >> 每日新闻</div>
<div class="wrap">
<div class="title clear"><h1 class="fl">今日头条</h1></div>
	<c:forEach items="${headingNewsList}" var="headingNews">
		<div class="salon_top clear">
	    	<div class="fl">
	        	<ul id="foo1">
	            	<li><img src="${pageContext.request.contextPath}/${headingNews.image}" width="613" height="459" /></li>
	            </ul>
	            <div id="pager1" class="pager"></div>
	        </div>
	        <div class="fr">
	        	<div class="bt"><h1><a href="" title="${headingNews.newsTitle}">${headingNews.newsTitle}</a></h1>
	            	<div class="hudongsalon_success">
	            		<span class="fe4800">
	            			<c:choose>
	            				<%-- 新闻七天即结束 --%>
	            				<c:when test="${(now.time-headingNews.createAt.time)/1000/60/60/24 < 7}">(成功举办)</c:when>
	            				<c:otherwise>(已结束)</c:otherwise>
	            			</c:choose>
	            		</span>
	                </div>
	            </div>
	            <div class="in clear">
	                <div class="time">时间：<fmt:formatDate value="${headingNews.createAt}" pattern="yyyy年MM月dd日"/></div>
	            </div>
	            <div class="txt">${headingNews.content}</div>
	        </div>
	    </div>
    </c:forEach>
</div>
<div class="wrap">
	<div class="title clear"><h1 class="fl">其他新闻</h1></div>
    <div class="salon" id="data-box">
    	<script type="text/html" id="data-template">
			{{each commonNewsList as commonNews}}
    		<ul>
        		<li class="clear">
            		<div class="fl"><img src="${pageContext.request.contextPath}/{{commonNews.image}}" alt="此新闻无图片" /></div>
                	<div class="fl">
                		<div class="salon_title clear">
							<div class="fl">
								<a href="" title="{{commonNews.newsTitle}}">{{commonNews.newsTitle}}</a>
								<span class="fe4800">{{commonNews.createAt| isOver}}</span>
							</div>
							<div class="fr">日期：{{commonNews.createAt| dateFormat:'yyyy年MM月dd日'}}</div>
						</div>
                    	<div class="salon_in">{{commonNews.content}}</div>
                	</div>
            	</li>
        	</ul>
			{{/each}}
		</script>
    </div>
    <!-- jqueryPage, class值固定 -->
		<div class="tcdPageCode"></div>
</div>
<div class="footer">Copyright &copy; 2020 中兴软件技术  版权所有 </div>

</body>
</html>
