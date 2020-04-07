<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>NewsManage.html</title>

<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="this is my page">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css"
	type="text/css"></link>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/FlatUI/dist/css/flat-ui.css"
	type="text/css"></link>
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/plugins/FlatUI/dist/img/favicon.ico">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/plugins/FlatUI/dist/js/flat-ui.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/plugins/bootstrap/js/messager.js"></script>

<!-- art-template -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/template-web.js"></script>

<!-- jqueryPage -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jqueryPage.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/jqueryPage.css">

<script type="text/javascript">
	function deleteNews(id, pageNo, curSize) {
		//删除页只剩1个数据
		if (curSize == 1)
			pageNo = pageNo - 1;
		$.messager.model = {
			ok : {
				text : "确认",
				classed : 'btn-default'
			},
			cancel : {
				text : "取消",
				classed : 'btn-error'
			}
		};
		$.messager.confirm("确认删除", "确认要删除吗?删除后不能恢复", function() {
			window.location.href = "${pageContext.request.contextPath}/news/newsDelete.do?newsId="+id+"&pageNo="+pageNo;
		});
	}

	function desmethod() {
		alert(1);
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
			url : '${pageContext.request.contextPath}/news/ajaxNewsList.do',
			type : 'post',
			data : {
				"pageNo" : pageNo,
				"pageSize" : pageSize
			},
			dataType : 'json',
			success : function(data) {
				var pageNo = data.pageNum;
				var totalPage = data.pages;
				var curSize = data.size;
				var list = {
					newss : data.list,
					pageNo : pageNo,
					curSize : curSize
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
</script>
</head>

<body style="padding: 5px 10px">
	<span style="color:red">${errMsg}</span>
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title">新闻管理</h3>
		</div>
		<div class="panel-body">
			<a href="${pageContext.request.contextPath}/news/newsAdd.do"
				class="btn btn-primary btn-default"> 添加 <span
				class="glyphicon glyphicon-plus"></span>
			</a>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>新闻标题</th>

						<th>创建时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="data-box">
					<script type="text/html" id="data-template">
						{{each newss as news index}}
		  				<tr> 	 	 	 	 	 
		  					<td>{{news.newsTitle}}</td>
		  				
		  					<td>{{news.createAt| dateFormat:'yyyy年MM月dd日'}}</td>
		  					<td>
		  						<a class="btn btn-primary  btn-xs btn-warning" href="${pageContext.request.contextPath}/news/newsModify.do?newsId={{news.newsId}}&pageNo={{pageNo}}">
		  							修改
		  							<span class="glyphicon glyphicon-pencil"></span>
		  						</a>
		  						<a class="btn btn-primary btn-xs btn-success" href="${pageContext.request.contextPath}/news/newsDetail.do?newsId={{news.newsId}}">
		  							详情
		  							<span class="glyphicon glyphicon-list-alt"></span>
		  						</a>
		  						<a class="btn btn-primary btn-xs btn-danger" href="javascript:;" onclick="deleteNews({{news.newsId}}, {{pageNo}}, {{curSize}})">
		  							删除
		  							<span class="glyphicon glyphicon-remove"></span>
		  						</a>
		  					</td>
		  				</tr>
		  				{{/each}}
					</script>
				</tbody>
			</table>
		</div>
		<!-- jqueryPage, class值固定 -->
		<div class="tcdPageCode"></div>
	</div>

</body>
</html>
