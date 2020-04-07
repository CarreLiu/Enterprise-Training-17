<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>ITing金融-后台首页</title>
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css" type="text/css"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/FlatUI/dist/css/flat-ui.css" type="text/css"></link>
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/plugins/FlatUI/dist/img/favicon.ico">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/FlatUI/dist/js/flat-ui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/base.js"></script>
</head>

<body class="page-header-fixed">

		<!-- 	头部logo开始 -->
		<div class="header navbar navbar-inverse navbar-fixed-top">
			<div class="header-inner" style="padding: 2px 40px;">
				<img alt="" src="${pageContext.request.contextPath}/images/logo.png">
				<div class="nav navbar-nav pull-right">
					<div class="btn-group">
		            <button data-toggle="dropdown" class="btn btn-default dropdown-toggle" type="button">
		            	<img alt="" src="${pageContext.request.contextPath}/images/CarreLiu.png">
		            	<span>欢迎您：${user.userName}</span>
		             <span class="caret"></span>
		             </button>
		            <ul role="menu" class="dropdown-menu">
		              <li><a href="javascript:;" data-toggle="modal" data-target="#modifyPwd">修改密码</a></li>
		              <li class="divider"></li>
		              <li><a href="#">退出系统</a></li>
		            </ul>
		          </div> 
				</div>
			</div>
		</div>
		<div class="clearfix">
		</div>	
		<!-- 	头部logo结束 -->
		
		
		<div class="page-container">
			<!-- 菜单导航开始 -->
			<div class="container menu">
				<div class="panel-group" id="accordion">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-toggle="collapse"
									data-parent="#accordion" href="#collapseOne">
									融资产品
								</a>
							</h4>
						</div>
						<div id="collapseOne" class="panel-collapse collapse in">
							<div >
								<ul>
									<li><a href="javascript:;" onclick="changePage('${pageContext.request.contextPath}/product/productManage.do')">融资产品管理</a></li>
									<li><a href="javascript:;" onclick="changePage('${pageContext.request.contextPath}/apply/onlineApply.do')">在线申请管理</a></li>
								</ul>
							</div>
						</div>
					</div>
					
					
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-toggle="collapse"
									data-parent="#accordion" href="#collapseTwo">
									新闻
								</a>
							</h4>
						</div>
						<div id="collapseTwo" class="panel-collapse collapse">
							<div class="panel-body">
								<ul>
									<li><a href="javascript:;" onclick="changePage('${pageContext.request.contextPath}/news/newsManage.do')">新闻管理</a></li>
								</ul>
							</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-toggle="collapse"
									data-parent="#accordion" href="#collapseThree">
									元数据管理
								</a>
							</h4>
						</div>
						<div id="collapseThree" class="panel-collapse collapse">
							<div class="panel-body">
								<ul>
									<li><a href="javascript:;" onclick="changePage('${pageContext.request.contextPath}/productType/productTypeManage.do')">产品类型管理</a></li>
									<li><a href="javascript:;" onclick="changePage('${pageContext.request.contextPath}/lendingPeriod/loanTimeManage.do')">贷款周期管理</a></li>
								</ul>
							</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-toggle="collapse"
									data-parent="#accordion" href="#collapseFour">
									企业管理
								</a>
							</h4>
						</div>
						<div id="collapseFour" class="panel-collapse collapse">
							<div class="panel-body">
								<ul>
									<li><a  href="javascript:;" onclick="changePage('${pageContext.request.contextPath}/company/companyManage.do')">企业管理</a></li>
									<li><a  href="javascript:;" onclick="changePage('${pageContext.request.contextPath}/report/findAll.do')">企业报表</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 菜单导航结束 -->
			
			<!--主界面开始 -->
			<iframe id="mainFrm" style="border: none;width: 73%;height: 60%;" src="${pageContext.request.contextPath}/product/productManage.do"></iframe>
			<!-- 主界面结束 -->
			
			<!-- 文档尾部开始 -->
			<div class="footer">Copyright &copy; 2020 中兴软件技术  版权所有 </div>
			<!-- 文档尾部结束 -->
		</div>
		
		
		
		
		<!-- 修改密码Modal -->
			<div class="modal fade" style="margin-top: 200px;" id="modifyPwd" tabindex="-1" role="dialog" aria-labelledby="modifyPwd" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			        <form class="form-horizontal" role="form" action="">
					      <div class="modal-header">
					        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
					        <h4 class="modal-title" id="myModalLabel">修改密码</h4>
					      </div>
					      <div class="modal-body">
					      			<div class="form-group">
						              <label for="oldPwd" class="col-lg-2 control-label">原密码</label>
						              <div class="col-lg-10">
						                <input type="password" class="form-control" id="oldPwd" />
						              </div>
						            </div>
						            <div class="form-group">
						              <label for="newPwd" class="col-lg-2 control-label">新密码</label>
						              <div class="col-lg-10">
						                <input type="password" class="form-control" id="newPwd"  />
						              </div>
						            </div>
						             <div class="form-group">
						              <label for="reNewPwd" class="col-lg-2 control-label">重复密码</label>
						              <div class="col-lg-10">
						                <input type="password" class="form-control" id="reNewPwd"  />
						              </div>
						            </div>
					      </div>
					      <div class="modal-footer">
					        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					        <input type="submit" class="btn btn-primary" value="修改" />
					      </div>
           			</form>
			    </div>
			  </div>
			</div>
			<!-- 修改密码Modal -->
</body>
</html>

























