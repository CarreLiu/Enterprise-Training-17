<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ITing金融-产品</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css" type="text/css"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/FlatUI/dist/css/flat-ui.css" type="text/css"></link>
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/plugins/FlatUI/dist/img/favicon.ico">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/css.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/FlatUI/dist/js/flat-ui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/base.js"></script>
	<!-- art-template -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/template-web.js"></script>
	
	<script type="text/javascript">
		//页面加载完成后调用的函数
		$(document).ready(function(){
			//当前时间
			getCurrentTime();
			loadData("${product.productId}");
			
			//校验申请人身份证号码
			$('#applyPersonIdCard').blur(function() {
				$('#idCardMsg').text('');
				let idCard = $(this).val();
				let msg = checkNull(idCard);
				$('#idCardMsg').text(msg);
				if(!msg && !(/(^\d{15}$)|(^\d{17}([0-9]|x|X)$)/.test(idCard))){
					$('#idCardMsg').text('身份证格式不正确');
				}
			});
			
			//校验申请人是否为空
			$('#applyPerson').blur(function() {
				$('#personMsg').text('');
				let name = $(this).val();
				let msg = checkNull(name);
				$('#personMsg').text(msg);
			});
			
			//校验申请数额
			$("#applyNum").blur(function() {
				$('#numMsg').text('');
				let num = $(this).val();
				let msg1 = checkNull(num);
				let msg2 = checkNumber(num);
				//有值,返回error
				if (msg1) {
					$('#numMsg').text(msg1);
				}
				else if (msg2) {
					$('#numMsg').text('请输入数字');
				}
				else {
					let financingAmountFrom = ${product.financingAmountFrom};
					let financingAmountTo = ${product.financingAmountTo};
					//获取整型值
					num = Number(num);
					//如果不在放贷金额范围之内,输出提示消息
					if (num < financingAmountFrom || num > financingAmountTo) {
						$('#numMsg').text('输入的金额不在放贷金额范围之内');
					}
				}
				
			});
		});
		
		function submitForm() {
			let idCardMsg = $('#idCardMsg').text();
			let personMsg = $('#personMsg').text();
			let numMsg = $('#numMsg').text();
			
			if (idCardMsg || personMsg || numMsg) {
				//不ajax提交
				return false;
			}
			else {
				//ajax同步方式提交数据
				$.ajaxSettings.async = false;			
				$.post(
					'${pageContext.request.contextPath}/front/applyAdd.do',
					{'applyProductId':$('#productId').val(),
					'applyProductName':$('#productName').val(),
					'applyPersonIdCard':$('#applyPersonIdCard').val(),
					'applyPerson':$('#applyPerson').val(),
					'applyNum':$('#applyNum').val()},
					function(msg) {
						//console.log("1");
					}
				);
				//隐藏form表单
				$('#applyModal').modal('hide');
				//console.log("2");
				loadData("${product.productId}");
				return false;
			}
		}
		
		//判断是否是数字
		function checkNumber(data) {
			let num = Number(data) + '';
			if (num == 'NaN') {
				return 'error';
			}
			return '';
		}
		
		//判断是否为null
		function checkNull(data) {
			if (!data) {
				return '不能为空';
			}
			return '';
		}
		
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
		
		
		/*发送ajax请求获取数据*/
	    function loadData(productId) {
	    	var applyProductId = productId;
	        $.ajax({
	            url: '${pageContext.request.contextPath}/front/ajaxApplyList.do',
	            type: 'post',
	            data : {"applyProductId":applyProductId},
	            dataType:'json',
	            success: function(data){
	                var list = {
	                    applyList: data,
	                    totalApply: data.length
	                };
	               	//console.log(list);
	               	//console.log(list.products);
	                var html = template('data-template', list);
	                $('#data-box').html(html);
	            }
	        });
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
        <li><a href="${pageContext.request.contextPath}/front/index_ajax1.do" class="cur">融资产品</a></li>
        <li><a href="${pageContext.request.contextPath}/front/news.do">每日新闻</a></li>
    </ul>
</div>

<div class="wrap font16">首页 >> 融资产品 >> ${product.productName}</div>
<div class="wrap clear">
	<div class="fl">
    	<div class="detail_detail">
        	<div class="success_tp clear">
            	<div class="fl">${product.productName}</div>
            </div>
            <div class="detail_detail_bt">
            	<div class="detail_detail_bt1">
            	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td>产品名称：${product.productName}</td>
                      <td>放贷利率：${product.primeLendingRateTo}%（年化利率）</td>
                    </tr>
                    <tr>
                      <td>发行单位：${product.company.companyName}</td>
                      <td>放贷金额：${product.financingAmountFrom}-${product.financingAmountTo}万</td>
                    </tr>
                    <tr>
                      <td>产品类型：${product.productType.productTypeName}</td>
                      <td>放贷期限：${product.lendingPeriod.period}</td>
                    </tr>
                  </table>
            	</div>
                <div class="detail_detail_bt3 clear">
                	<div class="fl">
                		<img src="${pageContext.request.contextPath}/images/logo.png" />
                		经理：${product.linkMan}<br />
                		银行：${product.ownedBank}</div>
                    <div class="fr">
                    	<a href="javascript:;" data-toggle="modal" data-target="#applyModal" class="apply-btn" />
                    		申请
                    	</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="detail_detail_title title clear"><h1 class="fl">产品详情</h1></div>
        <div class="detail_detail_in">
        	<h1><span class="logo1"></span>产品描述</h1>
            <p>${product.productDescription}</p>
            <h1><span class="logo1"></span>功能特点</h1>
            <p>利用创投机构对科技型中小企业经营状况以及发展潜力的专业判断，有效控制客户准入风险；
            （2）股权融资和债权融资相结合，在不稀释股权的基础上有效解决企业流动资金需求；
            （3）建立起银行、创投机构与科技型中小企业的合作平台。</p>
            <h1><span class="logo1"></span>相关要求</h1>
            <p>（1）贷款金额：根据借款人的实际资金需求、偿债能力及我行认可的授信方案综合确定银投联贷的贷款金额，该品种项下的最高金额不得超过创投公司实际投入到借款人的创投（风投）资金的80％。
            （2）贷款期限：由贷款人根据借款人实际生产周期、借款用途、借款人风险承受能力、经营特点等情况具体确定，不得超过创投公司和借款人约定的退出投资期限。
            （3）贷款利率：贷款利率按照中国人民银行利率政策及我行的贷款定价政策执行。</p>
        </div>
    </div>
    <div class="fr">
    	<div id="data-box" class="success">
        	<script type="text/html" id="data-template">
        	<div class="success_tp">
        		产品成功申请<span class="fe4800">（{{totalApply}}）</span>人</div>
            <div class="success_bt">
            	<ul>
					{{each applyList as apply index}}
                		<li><h1>{{index+1}}</h1><span>{{apply.applyPerson}}</span>成功申请（{{apply.applyNum}}万元）</li>
					{{/each}}
                </ul>
            </div>
            </script>
        </div>
    </div>
</div>


		<!-- 申请 -->
			<div class="modal fade" id="applyModal" tabindex="-1" role="dialog" aria-labelledby="applyModal" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			        <form class="form-horizontal" role="form" method="post"
			        	onsubmit="return submitForm()">
					      <div class="modal-header">
					        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
					        <h4 class="modal-title" id="myModalLabel">申请</h4>
					      </div>
					      <div class="modal-body">
					      			<div class="form-group">
						              <label for="productId" class="col-lg-2 control-label">产品ID</label>
						              <div class="col-lg-10">
						                <input type="text" class="form-control" id="productId"
						                	readonly="readonly" value="${product.productId}"
						                	name="productId"/>
						              </div>
						            </div>
						            <div class="form-group">
						              <label for="productName" class="col-lg-2 control-label">产品名称</label>
						              <div class="col-lg-10">
						                <input type="text" class="form-control" id="productName"
						                	readonly="readonly" value="${product.productName}"
						                	name="productName" />
						              </div>
						            </div>
						            <div class="form-group">
						              <label for="applyPersonIdCard" class="col-lg-2 control-label">申请人身份证号</label>
						              <div class="col-lg-10">
						                <input type="text" class="form-control" id="applyPersonIdCard"
						                	placeholder="申请人身份证号" name="applyPersonIdCard"/>
						              </div>
						              <span id="idCardMsg" style="color:red;"/>
						            </div>
						            <div class="form-group">
						              <label for="applyPerson" class="col-lg-2 control-label">申请人</label>
						              <div class="col-lg-10">
						                <input type="text" class="form-control" id="applyPerson"
						                	placeholder="请使用上面身份证上的姓名" name="applyPerson"/>
						              </div>
						              <span id="personMsg" style="color:red;"/>
						            </div>
						            
						             <div class="form-group">
						              <label for="applyNum" class="col-lg-2 control-label">申请金额</label>
						              <div class="col-lg-10">
						                <input type="text" class="form-control" id="applyNum"
						                placeholder="单位：万元" name="applyNum"/>
						              </div>
						              <span id="numMsg" style="color:red;"/>
						            </div>
					      </div>
					      <div class="modal-footer">
					        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					        <input type="submit" class="btn btn-primary" value="申请" />
					      </div>
           			</form>
			    </div>
			  </div>
			</div>
			<!-- 申请 -->

<!--end-->
<div class="footer">Copyright &copy; 2020 中兴软件技术  版权所有  </div>

</body>
</html>
