<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>newsModify.html</title>

	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="this is my page">
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css"
		type="text/css"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/FlatUI/dist/css/flat-ui.css"
		type="text/css"></link>
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/plugins/FlatUI/dist/img/favicon.ico">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap-fileupload.css" type="text/css"></link>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap-fileupload.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/FlatUI/dist/js/flat-ui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/fwb/bootstrap-wysiwyg.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/fwb/external/jquery.hotkeys.js" type="text/javascript"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/productAdd.js"></script>
	
	<script type="text/javascript">
		function checkData() {
			$("input[name='content']").val($('#content').html());
			
			//新闻标题
			let titleMsg = $('#titleMsg').text();
			
			//新闻正文
			let contentMsg = $('#contentMsg').text();
			
			//获取所有的异常返回，如果有，不能提交表单
			if (titleMsg || contentMsg) {
				//不提交表单
				return false;
			}
		
			//提交表单
			return true;
		}
		
		$(function() {
			//校验新闻标题是否为空
			$('#newsName').blur(function() {
				$('#titleMsg').text('');
				let name = $(this).val();
				let msg = checkNull(name);
				$('#titleMsg').text(msg);
				//校验修改后的新闻标题是否已经存在
				checkTitleExist();
			});
			
			//检查新闻正文是否为空
			$('#content').blur(function() {
				$('#contentMsg').text('');
				let name = $(this).text();
				let msg = checkNull(name);
				$('#contentMsg').text(msg);
			});
		});
		
		//校验修改后的新闻标题是否已经存在
		function checkTitleExist() {
			let newsId = $('#newsId').val();
			let newsTitle = $('#newsName').val();
			if (newsTitle) {
				$.post(
					'${pageContext.request.contextPath}/news/modifyCheck.do',
					{"newsTitle":newsTitle, "newsId":newsId},
					function(msg) {
						$('#titleMsg').text(msg);
					}
				);
			}
		}
		
		//判断是否为null
		function checkNull(data) {
			if (!data) {
				return '不能为空';
			}
			return '';
		}
	</script>
	
</head>

<body style="padding: 5px 10px">

	<div class="panel panel-primary">
	  <div class="panel-heading">
	    <h3 class="panel-title">新闻修改</h3>
	  </div>
	  <div class="panel-body">
	  		<form class="form-horizontal" role="form"
	  			action="${pageContext.request.contextPath}/news/newsModifyPost.do"
	  			enctype="multipart/form-data"
	  			method="post" onsubmit="return checkData()">
            	<input type="hidden" name="content" id="hidden">
            	<input type="hidden" name="pageNo" value="${pageNo}">
            
            <div class="form-group">
              <label for="newsId" class="col-lg-2 control-label">新闻编号</label>
              <div class="col-lg-10">
                <input type="text" class="form-control" id="newsId" value="${news.newsId}" name="newsId"  readonly="readonly"/>
              </div>
            </div>
            
            
            <div class="form-group">
              <label for="newsName" class="col-lg-2 control-label">新闻标题</label>
              <div class="col-lg-10">
                <input type="text" class="form-control" id="newsName" placeholder="新闻标题"
                	value="${news.newsTitle}" name="newsTitle">
              </div>
              <span id="titleMsg" style="color:red;"></span>
            </div>
            
            <div class="form-group">
              <label for="content" class="col-lg-2 control-label">新闻正文</label>
              <div class="col-lg-10">
			<div class="btn-toolbar" data-role="editor-toolbar"	data-target="#content">
				<div class="btn-group">
					<a class="btn" data-edit="bold" title="Bold (Ctrl/Cmd+B)">
						<b>加粗</b>
					</a> 
					<a class="btn" data-edit="italic" title="Italic (Ctrl/Cmd+I)">
						<i>倾斜</i>
					</a> 
				</div>
				<div class="btn-group">
					<a class="btn" data-edit="insertunorderedlist" title="Bullet list">
						列表
					</a>
					<a class="btn" data-edit="insertorderedlist" title="Number list">
						有序列表
					</a> 
					<a class="btn" data-edit="indent" title="Indent (Tab)">
						Tab缩进
					</a>
				</div>
				<div class="btn-group">
					<a class="btn" data-edit="justifyleft"
						title="Align Left (Ctrl/Cmd+L)">
						左对齐
					</a> 
					<a class="btn" data-edit="justifycenter" title="Center (Ctrl/Cmd+E)">
						居中
					</a> 
					<a class="btn" data-edit="justifyright"
						title="Align Right (Ctrl/Cmd+R)">
						右对齐
					</a> 
					<a class="btn" data-edit="justifyfull" title="Justify (Ctrl/Cmd+J)">
						自动调整
					</a>
				</div>
		
		 		<div class="btn-group">
					<a class="btn" data-edit="undo" title="Undo (Ctrl/Cmd+Z)">
						撤销
					</a> 
					<a class="btn" data-edit="redo" title="Redo (Ctrl/Cmd+Y)">
						恢复
					</a>
				</div>
				
			</div>
              	
            <div id="content" style="overflow:scroll; max-height:300px;border: 1px solid;height: 1400px;">${news.content}</div>
         </div>
         <span id="contentMsg" style="color:red;"></span>
     </div>
            
            <div class="form-group">
    			<label class="col-lg-2 control-label">缩略图</label>
                <div class="col-lg-10">
                        <div class="fileupload fileupload-new" data-provides="fileupload">
                            <div class="input-group">
                                 <div class="fileupload-new thumbnail" style="width: 130px; height: 139px;">
                                     <img
                                     	<c:choose>
                                     		<c:when test="${empty news.image}">src="${pageContext.request.contextPath}/images/nopic.gif"</c:when>
                                     		<c:otherwise>src="${pageContext.request.contextPath}/${news.image}"</c:otherwise>
                                     	</c:choose>
                                     alt=""/>
                                 </div>
                                 <div class="fileupload-preview fileupload-exists thumbnail"
                                                     style="max-width: 200px; max-height: 150px; line-height: 20px;">
                                 </div>
                                 <div>
                                    <span class="btn btn-default btn-file">
                                         <span class="fileupload-new">
                                         	<i class="fa fa-paper-clip"></i> 
                                         	选择文件
                                         </span>
                                         <span class="fileupload-exists">
                                         	<i class="fa fa-undo"></i>
											替换
										 </span>
                                         <input name="imagefile" type="file" class="default" />
                                   </span>
                                   <a href="#" class="btn btn-danger fileupload-exists" data-dismiss="fileupload">
                                   		<i class="fa fa-trash-o"></i>
                                   		移除
                                   </a>
                          		</div>
                            </div>
                       </div>
                 </div>
				</div>
            	<div class="form-group">
	              <label for="heading" class="col-lg-2 control-label">头条设置</label>
	              <div class="col-lg-10">
	                <input type="radio" name="heading" value="0" <c:if test="${news.heading==0}">checked</c:if>>不设为头条新闻&nbsp;&nbsp;
	                <input type="radio" name="heading" value="1" <c:if test="${news.heading==1}">checked</c:if>>设为头条新闻
	              </div>
	            </div>
            
            <div class="form-group">
              <div class="col-lg-offset-2 col-lg-10">
               <button type="submit" class="btn btn-lg btn-info" style="padding: 15px 60px;border-radius: 0px;">修改</button>
              </div>
            </div>
          </form>
	  		
	  		
	  </div>
	</div>
	
</body>
</html>
    