<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- <div class="col-lg-6"> -->
<script type="text/javascript">

	$(function() {
		 //获取最后一个段位的顺序号
		 var lastSegmentQuantity=${segmentQuantity};
		 console.log('cccccc'+lastSegmentQuantity);
		 for (var i = 1; i <=lastSegmentQuantity; i++) {
			/*  清除tab绑定点击事件开始 */
			 var clearSegment="";
			 clearSegment='#clearsegmentForm'+i;
			 $(clearSegment).click(function(event){
				var reg=new RegExp('^[0-9]*$');
				//获取tab序号
				var number='#segment'+$(this).attr('id').replace(/[^0-9]/ig,""); 
				var segmentName=number+'-segmentName';
				var segmentSource=number+'-segmentSource';
				var sourceContent=number+'-sourceContent';
				var segmentMinLength=number+'-segmentMinLength';
				var segmentMaxLength=number+'-segmentMaxLength';
				var formatSwitch=number+'-formatSwitch';
				var statusSwitch=number+'-statusSwitch';
				var validateSwitch=number+'-validateSwitch';
				var segmentCreate=number+'-segmentCreate';
				var description=number+'-description';
				var version=number+'-version';
				var creator=number+'-creator';
				//console.log(segmentSource);
				//console.log($(this).parent().siblings());
				$(segmentName).val('');
				$(segmentSource).val('系统自动生成');
				$(sourceContent).val('');
				$(segmentMinLength).val('');
				$(segmentMaxLength).val('');
				$(formatSwitch).val('否'); 
				$(validateSwitch).val("否");
				$(statusSwitch).val("否");
				$(segmentCreate).val('');
				$(description).val('');
				$(version).val('');
				$(creator).val(''); 
			 });
			 /*  清除tab绑定点击事件结束 */
			 /* 下一段位开始 */
			 var nextSegmentButton="";
			 var nextSegment="";
			 SegmentButton='#nextSegment'+i;
			 nextSegment='#segment'+i;
			 //console.log(SegmentButton);
			 if (i==lastSegmentQuantity) {
				 //console.log(i);
				 //console.log(SegmentButton);
				 $(SegmentButton).html('确定').click(function(event){
					 //alert('最后一个按钮了!');
					 var segmentData=[];
					 segmentData=checkAllForm();
					 console.log(JSON.stringify(segmentData));
					 $.ajax({
					        type: "POST",
					        url:'${pageContext.request.contextPath }/rule/addSegment.do',
					        data:JSON.stringify(segmentData) ,// 你的formId
					        dataType:"json",
						    contentType:"application/json;charset=utf-8",
					        success: function(result) {
					            if (result.error) {
					          		 alert(result.error);
								}else {
									 alert(result.success);
									 // alert(${segmentQuantity}); 
								}
					        } 
						});
				 }); 
			} else {
			 $(SegmentButton).click(function(event){
				    //event就是点击对象
				    //获取数字正则表达式
				    var reg=new RegExp('^[0-9]*$');
				    //获取段位顺序号
				    //var segmentSequence=$(this).parent().parent().parent().attr('id');
				    var segmentSequence=$(this).attr('id');
				    //console.log('当前顺序号'+segmentSequence);
				    //获取顺序号
				    var sequence=segmentSequence.replace(/[^0-9]/ig,"");
				    // console.log(parseInt(sqquence)+1); 
					//获取下一个tab的id
					var segmentId= parseInt(sequence);
					var nextSegmentId= segmentId+1;
					var nextSegment='#segment'+nextSegmentId;
					//console.log(nextSegment); 
					// console.log(segmentSequence.split('-')[0]); 
					$(nextSegment).click();  
				}); 
			}
			 /* 下一段位结束 */ 
		}
	}); 
	
	function checkAllForm() {
		//定义全局临时变量SegmentList,储存每个segment时使用
		var segmentList={};  
		//定义全局参数segmentJson集合.提交所有segment时使用
		var segmentData=[];
		var size=${segmentQuantity};
		// 储存segment开始 
		//获取segment字段的值
		if (size==null||size==''||size=='undefind') {
			return;
		}
		for(var i=1;i<=size;i++){
			var number='#segment'+i;
			var segmentName=number+'-segmentName';
			var segmentSource=number+'-segmentSource';
			var sourceContent=number+'-sourceContent';
			var segmentMinLength=number+'-segmentMinLength';
			var segmentMaxLength=number+'-segmentMaxLength';
			var formatSwitch=number+'-formatSwitch';
			var statusSwitch=number+'-statusSwitch';
			var validateSwitch=number+'-validateSwitch';
			var segmentCreate=number+'-segmentCreate';
			var description=number+'-description';
			var version=number+'-version';
			var creator=number+'-creator';
			 if ($(segmentName).val()=='') {
					alert('第'+i+'段,段位规则名称不能为空!');
					return;
				}else if ($(segmentSource).val()=='') {
					alert('第'+i+'段,编码段位来源不能为空!');
					return;
				}else if (!isUnsignedInterger($(segmentMinLength).val())) {
					alert('第'+i+'段,段位最小长度只能为正整数!');
					return;
				}else if (!isUnsignedInterger($(segmentMaxLength).val())) {
					alert('第'+i+'段,段位最大长度只能为正整数!');
					return;
				}else if ($(segmentMinLength).val()>$(segmentMaxLength).val()) {
					alert('第'+i+'段,段位最小长度不能大于段位最大长度!');
					return;
				}else if ($(formatSwitch).val()=='') {
					alert('第'+i+'段,段位是否高级格式化不能为空!');
					return;
				}else if (($(statusSwitch).val()=='')) {
					alert('第'+i+'段,段位是否高级状态不能为空!');
					return;
				}else if ($(validateSwitch).val()=='') {
					alert('第'+i+'段,段位是否高级校验不能为空!');
					return;
				}else if($(segmentCreate).val()=='') {
					alert('第'+i+'段,段位创建人不能为空!');
					return;
				}
				segmentList['segmentName']=$(segmentName).val();
				//console.log(segmentData);
				segmentList['segmentSequence']=i;
				segmentList['segmentSource']=$(segmentSource).val();
				segmentList['sourceContent']=$(sourceContent).val();
				segmentList['segmentMinLength']=$(segmentMinLength).val();
				segmentList['segmentMaxLength']=$(segmentMaxLength).val();
				segmentList['formatSwitch']=$(formatSwitch).val(); 
				segmentList['validateSwitch']=$(validateSwitch).val();
				segmentList['statusSwitch']=$(statusSwitch).val();
				segmentList['segmentCreate']=$(segmentCreate).val();
				segmentList['description']=$(description).val();
				segmentList['version']=$(version).val();
				segmentList['creator']=$(creator).val();  
				//转化为json数组
				segmentData.push(segmentList);
				//储存segment结束  */
		} 
		return segmentData;
	}
		
	 	
	
</script>

<div class="panel panel-default">
	<div class="panel-heading">新建编码段位规则</div>
	<!-- /.panel-heading -->
	<div class="panel-body">
		<!-- Nav tabs -->
		<ul class="nav nav-pills">
			<c:if test="${segmentQuantity=='1'}">
				<li class="active"><a href="#segment1-pills" data-toggle="tab"
					aria-expanded="true" id="segment1">第一段编码规则</a></li>
			</c:if>
			<c:if test="${segmentQuantity=='2'}">
				<li class="active"><a href="#segment1-pills" data-toggle="tab"
					aria-expanded="true" id="segment1">第一段编码规则</a></li>
				<li class=""><a href="#segment2-pills" data-toggle="tab"
					aria-expanded="false" id="segment2">第二段编码规则</a></li>
			</c:if>
			<c:if test="${segmentQuantity=='3'}">
				<li class="active"><a href="#segment1-pills" data-toggle="tab"
					aria-expanded="true" id="segment1">第一段编码规则</a></li>
				<li class=""><a href="#segment2-pills" data-toggle="tab"
					aria-expanded="false" id="segment2">第二段编码规则</a></li>
				<li class=""><a href="#segment3-pills" data-toggle="tab"
					aria-expanded="false" id="segment3">第三段编码规则</a></li>
			</c:if>
			<c:if test="${segmentQuantity=='4'}">
				<li class="active"><a href="#segment1-pills" data-toggle="tab"
					aria-expanded="true" id="segment1">第一段编码规则</a></li>
				<li class=""><a href="#segment2-pills" data-toggle="tab"
					aria-expanded="false" id="segment2">第二段编码规则</a></li>
				<li class=""><a href="#segment3-pills" data-toggle="tab"
					aria-expanded="false" id="segment3">第三段编码规则</a></li>
				<li class=""><a href="#segment4-pills" data-toggle="tab"
					aria-expanded="false" id="segment4">第四段编码规则</a></li>
			</c:if>
			<c:if test="${segmentQuantity=='5'}">
				<li class="active"><a href="#segment1-pills" data-toggle="tab"
					aria-expanded="true" id="segment1">第一段编码规则</a></li>
				<li class=""><a href="#segment2-pills" data-toggle="tab"
					aria-expanded="false" id="segment2">第二段编码规则</a></li>
				<li class=""><a href="#segment3-pills" data-toggle="tab"
					aria-expanded="false" id="segment3">第三段编码规则</a></li>
				<li class=""><a href="#segment4-pills" data-toggle="tab"
					aria-expanded="false" id="segment4">第四段编码规则</a></li>
				<li class=""><a href="#segment5-pills" data-toggle="tab"
					aria-expanded="false" id="segment5">第无段编码规则</a></li>
			</c:if>
			<c:if test="${segmentQuantity=='6'}">
				<li class="active"><a href="#segment1-pills" data-toggle="tab"
					aria-expanded="true" id="segment1">第一段编码规则</a></li>
				<li class=""><a href="#segment2-pills" data-toggle="tab"
					aria-expanded="false" id="segment2">第二段编码规则</a></li>
				<li class=""><a href="#segment3-pills" data-toggle="tab"
					aria-expanded="false" id="segment3">第三段编码规则</a></li>
				<li class=""><a href="#segment4-pills" data-toggle="tab"
					aria-expanded="false" id="segment4">第四段编码规则</a></li>
				<li class=""><a href="#segment5-pills" data-toggle="tab"
					aria-expanded="false" id="segment5">第五段编码规则</a></li>
				<li class=""><a href="#segment6-pills" data-toggle="tab"
					aria-expanded="false" id="segment6">第六段编码规则</a></li>
			</c:if>
			<c:if test="${segmentQuantity=='7'}">
				<li class="active"><a href="#segment1-pills" data-toggle="tab"
					aria-expanded="true" id="segment1">第一段编码规则</a></li>
				<li class=""><a href="#segment2-pills" data-toggle="tab"
					aria-expanded="false" id="segment2">第二段编码规则</a></li>
				<li class=""><a href="#segment3-pills" data-toggle="tab"
					aria-expanded="false" id="segment3">第三段编码规则</a></li>
				<li class=""><a href="#segment4-pills" data-toggle="tab"
					aria-expanded="false" id="segment4">第四段编码规则</a></li>
				<li class=""><a href="#segment5-pills" data-toggle="tab"
					aria-expanded="false" id="segment5">第五段编码规则</a></li>
				<li class=""><a href="#segment6-pills" data-toggle="tab"
					aria-expanded="false" id="segment6">第六段编码规则</a></li>
				<li class=""><a href="#segment7-pills" data-toggle="tab"
					aria-expanded="false" id="segment7">第七段编码规则</a></li>
			</c:if>
			<c:if test="${segmentQuantity=='8'}">
				<li class="active"><a href="#segment1-pills" data-toggle="tab"
					aria-expanded="true" id="segment1">第一段编码规则</a></li>
				<li class=""><a href="#segment2-pills" data-toggle="tab"
					aria-expanded="false" id="segment2">第二段编码规则</a></li>
				<li class=""><a href="#segment3-pills" data-toggle="tab"
					aria-expanded="false" id="segment3">第三段编码规则</a></li>
				<li class=""><a href="#segment4-pills" data-toggle="tab"
					aria-expanded="false" id="segment4">第四段编码规则</a></li>
				<li class=""><a href="#segment5-pills" data-toggle="tab"
					aria-expanded="false" id="segment5">第五段编码规则</a></li>
				<li class=""><a href="#segment6-pills" data-toggle="tab"
					aria-expanded="false" id="segment6">第六段编码规则</a></li>
				<li class=""><a href="#segment7-pills" data-toggle="tab"
					aria-expanded="false" id="segment7">第七段编码规则</a></li>
				<li class=""><a href="#segment8-pills" data-toggle="tab"
					aria-expanded="false" id="segment8">第八段编码规则</a></li>
			</c:if>
			<c:if test="${segmentQuantity=='9'}">
				<li class="active"><a href="#segment1-pills" data-toggle="tab"
					aria-expanded="true" id="segment1">第一段编码规则</a></li>
				<li class=""><a href="#segment2-pills" data-toggle="tab"
					aria-expanded="false" id="segment2">第二段编码规则</a></li>
				<li class=""><a href="#segment3-pills" data-toggle="tab"
					aria-expanded="false" id="segment3">第三段编码规则</a></li>
				<li class=""><a href="#segment4-pills" data-toggle="tab"
					aria-expanded="false" id="segment4">第四段编码规则</a></li>
				<li class=""><a href="#segment5-pills" data-toggle="tab"
					aria-expanded="false" id="segment5">第五段编码规则</a></li>
				<li class=""><a href="#segment6-pills" data-toggle="tab"
					aria-expanded="false" id="segment6">第六段编码规则</a></li>
				<li class=""><a href="#segment7-pills" data-toggle="tab"
					aria-expanded="false" id="segment7">第七段编码规则</a></li>
				<li class=""><a href="#segment8-pills" data-toggle="tab"
					aria-expanded="false" id="segment8">第八段编码规则</a></li>
				<li class=""><a href="#segment9-pills" data-toggle="tab"
					aria-expanded="false" id="segment9">第九段编码规则</a></li>
			</c:if>
			<c:if test="${segmentQuantity=='10'}">
				<li class="active"><a href="#segment1-pills" data-toggle="tab"
					aria-expanded="true" id="segment1">第一段编码规则</a></li>
				<li class=""><a href="#segment2-pills" data-toggle="tab"
					aria-expanded="false" id="segment2">第二段编码规则</a></li>
				<li class=""><a href="#segment3-pills" data-toggle="tab"
					aria-expanded="false" id="segment3">第三段编码规则</a></li>
				<li class=""><a href="#segment4-pills" data-toggle="tab"
					aria-expanded="false" id="segment4">第四段编码规则</a></li>
				<li class=""><a href="#segment5-pills" data-toggle="tab"
					aria-expanded="false" id="segment5">第五段编码规则</a></li>
				<li class=""><a href="#segment6-pills" data-toggle="tab"
					aria-expanded="false" id="segment6">第六段编码规则</a></li>
				<li class=""><a href="#segment7-pills" data-toggle="tab"
					aria-expanded="false" id="segment7">第七段编码规则</a></li>
				<li class=""><a href="#segment8-pills" data-toggle="tab"
					aria-expanded="false" id="segment8">第八段编码规则</a></li>
				<li class=""><a href="#segment9-pills" data-toggle="tab"
					aria-expanded="false" id="segment9">第九段编码规则</a></li>
				<li class=""><a href="#segment10-pills" data-toggle="tab"
					aria-expanded="false" id="segment10">第十段编码规则</a></li>
			</c:if>
			<c:if test="${segmentQuantity=='11'}">
				<li class="active"><a href="#segment1-pills" data-toggle="tab"
					aria-expanded="true" id="segment1">第一段编码规则</a></li>
				<li class=""><a href="#segment2-pills" data-toggle="tab"
					aria-expanded="false" id="segment2">第二段编码规则</a></li>
				<li class=""><a href="#segment3-pills" data-toggle="tab"
					aria-expanded="false" id="segment3">第三段编码规则</a></li>
				<li class=""><a href="#segment4-pills" data-toggle="tab"
					aria-expanded="false" id="segment4">第四段编码规则</a></li>
				<li class=""><a href="#segment5-pills" data-toggle="tab"
					aria-expanded="false" id="segment5">第五段编码规则</a></li>
				<li class=""><a href="#segment6-pills" data-toggle="tab"
					aria-expanded="false" id="segment6">第六段编码规则</a></li>
				<li class=""><a href="#segment7-pills" data-toggle="tab"
					aria-expanded="false" id="segment7">第七段编码规则</a></li>
				<li class=""><a href="#segment8-pills" data-toggle="tab"
					aria-expanded="false" id="segment8">第八段编码规则</a></li>
				<li class=""><a href="#segment9-pills" data-toggle="tab"
					aria-expanded="false" id="segment9">第九段编码规则</a></li>
				<li class=""><a href="#segment10-pills" data-toggle="tab"
					aria-expanded="false" id="segment10">第十段编码规则</a></li>
				<li class=""><a href="#segment11-pills" data-toggle="tab"
					aria-expanded="false" id="segment11">第十一段编码规则</a></li>
			</c:if>
			<c:if test="${segmentQuantity=='12'}">
				<li class="active"><a href="#segment1-pills" data-toggle="tab"
					aria-expanded="true" id="segment1">第一段编码规则</a></li>
				<li class=""><a href="#segment2-pills" data-toggle="tab"
					aria-expanded="false" id="segment2">第二段编码规则</a></li>
				<li class=""><a href="#segment3-pills" data-toggle="tab"
					aria-expanded="false" id="segment3">第三段编码规则</a></li>
				<li class=""><a href="#segment4-pills" data-toggle="tab"
					aria-expanded="false" id="segment4">第四段编码规则</a></li>
				<li class=""><a href="#segment5-pills" data-toggle="tab"
					aria-expanded="false" id="segment5">第五段编码规则</a></li>
				<li class=""><a href="#segment6-pills" data-toggle="tab"
					aria-expanded="false" id="segment6">第六段编码规则</a></li>
				<li class=""><a href="#segment7-pills" data-toggle="tab"
					aria-expanded="false" id="segment7">第七段编码规则</a></li>
				<li class=""><a href="#segment8-pills" data-toggle="tab"
					aria-expanded="false" id="segment8">第八段编码规则</a></li>
				<li class=""><a href="#segment9-pills" data-toggle="tab"
					aria-expanded="false" id="segment9">第九段编码规则</a></li>
				<li class=""><a href="#segment10-pills" data-toggle="tab"
					aria-expanded="false" id="segment10">第十段编码规则</a></li>
				<li class=""><a href="#segment11-pills" data-toggle="tab"
					aria-expanded="false" id="segment11">第十一段编码规则</a></li>
				<li class=""><a href="#segment12-pills" data-toggle="tab"
					aria-expanded="false"  id="segment12">第十二段编码规则</a></li>
			</c:if>
		</ul>
		<hr>
		<!-- Tab panes -->
		<div class="tab-content">
			<div class="tab-pane fade active in" id="segment1-pills">
				<form id="setgmentForm1" method="post">
					<table>
						<tr>
							<td><span>编码段位名称:</span><input type="text"
								name="segment1-segmentName" id="segment1-segmentName"></td>
							<td><span>编码段位来源:</span> <select id="segment1-segmentSource"
								name="segment1-segmentSource">
									<option>系统自动生成</option>
									<option>自定义值选择</option>
									<option>固定值输入</option>
									<option>自定义值输入</option>
									<option>系统属性绑定</option>
							</select></td>
							<td><span style="padding-left: 12px;">来源对应内容:</span><input
								type="text" name="segment1-sourceContent" id="segment1-sourceContent"></td>
						</tr>
						<tr>
							<td><span>段位最小长度:</span><input type="text" name="segment1-segmentMinLength"
								id="segment1-segmentMinLength"></td>
							<td><span>段位最大长度:</span><input type="text" name="segment1-segmentMaxLength"
								id="segment1-segmentMaxLength"></td>
							<td><span>是否高级格式化:</span> <select id="segment1-formatSwitch"
								name="segment1-formatSwitch">
									<option>否</option>
									<option>是</option>
							</select></td>
						</tr>
						<tr>
							<td><span>是否高级状态:</span><select id="segment1-statusSwitch"
								name="segment1-statusSwitch">
									<option>否</option>
									<option>是</option>
							</select></td>
							<td><span >是否高级校验:</span> <select
								id="segment1-validateSwitch" name="segment1-validateSwitch">
									<option>否</option>
									<option>是</option>
							</select></td>
							<td><span style="padding-left: 56px;">创建人:</span><input
								type="text" name="segment1-segmentCreate" id="segment1-segmentCreate"></td>
						</tr>
					</table>
					<div class="form_button segment_button">
						<button type="button" class="btn btn-outline btn-danger nextSegment"
							id="nextSegment1">下一段位</button>
						<button type="button" class="btn btn-outline btn-primary"
							id="clearsegmentForm1">清空</button>
					</div>
				</form>
			</div>
			<div class="tab-pane fade " id="segment2-pills">
				<form id="setgmentForm2" method="post">
					<table>
						<tr>
							<td><span>编码段位名称:</span><input type="text"
								name="segment2-segmentName" id="segment2-segmentName"></td>
							<td><span>编码段位来源:</span> <select id="segment2-segmentSource"
								name="segment2-segmentSource">
									<option>系统自动生成</option>
									<option>自定义值选择</option>
									<option>固定值输入</option>
									<option>自定义值输入</option>
									<option>系统属性绑定</option>
							</select></td>
							<td><span style="padding-left: 12px;">来源对应内容:</span><input
								type="text" name="segment2-sourceContent" id="segment2-sourceContent"></td>
						</tr>
						<tr>
							<td><span>段位最小长度:</span><input type="text" name="segment2-segmentMinLength"
								id="segment2-segmentMinLength"></td>
							<td><span>段位最大长度:</span><input type="text" name="segment2-segmentMaxLength"
								id="segment2-segmentMaxLength"></td>
							<td><span>是否高级格式化:</span> <select id="segment2-formatSwitch"
								name="segment2-formatSwitch">
									<option>否</option>
									<option>是</option>
							</select></td>
						</tr>
						<tr>
							<td><span>是否高级状态:</span><select id="segment2-statusSwitch"
								name="segment2-statusSwitch">
									<option>否</option>
									<option>是</option>
							</select></td>
							<td><span >是否高级校验:</span> <select
								id="segment2-validateSwitch" name="segment2-validateSwitch">
									<option>否</option>
									<option>是</option>
							</select></td>
							<td><span style="padding-left: 56px;">创建人:</span><input
								type="text" name="segment2-segmentCreate" id="segment2-segmentCreate"></td>
						</tr>
					</table>
					<div class="form_button segment_button">
						<button type="button" class="btn btn-outline btn-danger nextSegment"
							id="nextSegment2">下一段位</button>
						<button type="button" class="btn btn-outline btn-primary"
							id="clearsegmentForm2">清空</button>
					</div>
				</form>
			</div>
			<div class="tab-pane fade " id="segment3-pills">
				<form id="setgmentForm3" method="post">
					<table>
						<tr>
							<td><span>编码段位名称:</span><input type="text"
								name="segment3-segmentName" id="segment3-segmentName"></td>
							<td><span>编码段位来源:</span> <select id="segment3-segmentSource"
								name="segment3-segmentSource">
									<option>系统自动生成</option>
									<option>自定义值选择</option>
									<option>固定值输入</option>
									<option>自定义值输入</option>
									<option>系统属性绑定</option>
							</select></td>
							<td><span style="padding-left: 12px;">来源对应内容:</span><input
								type="text" name="segment3-sourceContent" id="segment3-sourceContent"></td>
						</tr>
						<tr>
							<td><span>段位最小长度:</span><input type="text" name="segment3-segmentMinLength"
								id="segment3-segmentMinLength"></td>
							<td><span>段位最大长度:</span><input type="text" name="segment3-segmentMaxLength"
								id="segment3-segmentMaxLength"></td>
							<td><span>是否高级格式化:</span> <select id="segment3-formatSwitch"
								name="segment3-formatSwitch">
									<option>否</option>
									<option>是</option>
							</select></td>
						</tr>
						<tr>
							<td><span>是否高级状态:</span><select id="segment3-statusSwitch"
								name="segment3-statusSwitch">
									<option>否</option>
									<option>是</option>
							</select></td>
							<td><span >是否高级校验:</span> <select
								id="segment3-validateSwitch" name="segment3-validateSwitch">
									<option>否</option>
									<option>是</option>
							</select></td>
							<td><span style="padding-left: 56px;">创建人:</span><input
								type="text" name="segment3-segmentCreate" id="segment3-segmentCreate"></td>
						</tr>
					</table>
					<div class="form_button segment_button">
						<button type="button" class="btn btn-outline btn-danger nextSegment"
							id="nextSegment3">下一段位</button>
						<button type="button" class="btn btn-outline btn-primary"
							id="clearsegmentForm3">清空</button>
					</div>
				</form>
			</div>
			<div class="tab-pane fade " id="segment4-pills">
				<form id="setgmentForm4" method="post">
					<table>
						<tr>
							<td><span>编码段位名称:</span><input type="text"
								name="segment4-segmentName" id="segment4-segmentName"></td>
							<td><span>编码段位来源:</span> <select id="segment4-segmentSource"
								name="segment4-segmentSource">
									<option>系统自动生成</option>
									<option>自定义值选择</option>
									<option>固定值输入</option>
									<option>自定义值输入</option>
									<option>系统属性绑定</option>
							</select></td>
							<td><span style="padding-left: 12px;">来源对应内容:</span><input
								type="text" name="segment4-sourceContent" id="segment4-sourceContent"></td>
						</tr>
						<tr>
							<td><span>段位最小长度:</span><input type="text" name="segment4-segmentMinLength"
								id="segment4-segmentMinLength"></td>
							<td><span>段位最大长度:</span><input type="text" name="segment4-segmentMaxLength"
								id="segment4-segmentMaxLength"></td>
							<td><span>是否高级格式化:</span> <select id="segment4-formatSwitch"
								name="segment4-formatSwitch">
									<option>否</option>
									<option>是</option>
							</select></td>
						</tr>
						<tr>
							<td><span>是否高级状态:</span><select id="segment4-statusSwitch"
								name="segment4-statusSwitch">
									<option>否</option>
									<option>是</option>
							</select></td>
							<td><span >是否高级校验:</span> <select
								id="segment4-validateSwitch" name="segment4-validateSwitch">
									<option>否</option>
									<option>是</option>
							</select></td>
							<td><span style="padding-left: 56px;">创建人:</span><input
								type="text" name="segment4-segmentCreate" id="segment4-segmentCreate"></td>
						</tr>
					</table>
					<div class="form_button segment_button">
						<button type="button" class="btn btn-outline btn-danger nextSegment"
							id="nextSegment4">下一段位</button>
						<button type="button" class="btn btn-outline btn-primary"
							id="clearsegmentForm4">清空</button>
					</div>
				</form>
			</div>
			<div class="tab-pane fade " id="segment5-pills">
				<form id="setgmentForm5" method="post">
					<table>
						<tr>
							<td><span>编码段位名称:</span><input type="text"
								name="segment5-segmentName" id="segment5-segmentName"></td>
							<td><span>编码段位来源:</span> <select id="segment5-segmentSource"
								name="segment5-segmentSource">
									<option>系统自动生成</option>
									<option>自定义值选择</option>
									<option>固定值输入</option>
									<option>自定义值输入</option>
									<option>系统属性绑定</option>
							</select></td>
							<td><span style="padding-left: 12px;">来源对应内容:</span><input
								type="text" name="segment5-sourceContent" id="segment5-sourceContent"></td>
						</tr>
						<tr>
							<td><span>段位最小长度:</span><input type="text" name="segment5-segmentMinLength"
								id="segment5-segmentMinLength"></td>
							<td><span>段位最大长度:</span><input type="text" name="segment5-segmentMaxLength"
								id="segment5-segmentMaxLength"></td>
							<td><span>是否高级格式化:</span> <select id="segment5-formatSwitch"
								name="segment5-formatSwitch">
									<option>否</option>
									<option>是</option>
							</select></td>
						</tr>
						<tr>
							<td><span>是否高级状态:</span><select id="segment5-statusSwitch"
								name="segment5-statusSwitch">
									<option>否</option>
									<option>是</option>
							</select></td>
							<td><span >是否高级校验:</span> <select
								id="segment5-validateSwitch" name="segment5-validateSwitch">
									<option>否</option>
									<option>是</option>
							</select></td>
							<td><span style="padding-left: 56px;">创建人:</span><input
								type="text" name="segment5-segmentCreate" id="segment5-segmentCreate"></td>
						</tr>
					</table>
					<div class="form_button segment_button">
						<button type="button" class="btn btn-outline btn-danger nextSegment"
							id="nextSegment5">下一段位</button>
						<button type="button" class="btn btn-outline btn-primary"
							id="clearsegmentForm5">清空</button>
					</div>
				</form>
			</div>
			<div class="tab-pane fade " id="segment6-pills">
				<form id="setgmentForm6" method="post">
					<table>
						<tr>
							<td><span>编码段位名称:</span><input type="text"
								name="segment6-segmentName" id="segment6-segmentName"></td>
							<td><span>编码段位来源:</span> <select id="segment6-segmentSource"
								name="segment6-segmentSource">
									<option>系统自动生成</option>
									<option>自定义值选择</option>
									<option>固定值输入</option>
									<option>自定义值输入</option>
									<option>系统属性绑定</option>
							</select></td>
							<td><span style="padding-left: 12px;">来源对应内容:</span><input
								type="text" name="segment6-sourceContent" id="segment6-sourceContent"></td>
						</tr>
						<tr>
							<td><span>段位最小长度:</span><input type="text" name="segment6-segmentMinLength"
								id="segment6-segmentMinLength"></td>
							<td><span>段位最大长度:</span><input type="text" name="segment6-segmentMaxLength"
								id="segment6-segmentMaxLength"></td>
							<td><span>是否高级格式化:</span> <select id="segment6-formatSwitch"
								name="segment6-formatSwitch">
									<option>否</option>
									<option>是</option>
							</select></td>
						</tr>
						<tr>
							<td><span>是否高级状态:</span><select id="segment6-statusSwitch"
								name="segment6-statusSwitch">
									<option>否</option>
									<option>是</option>
							</select></td>
							<td><span >是否高级校验:</span> <select
								id="segment6-validateSwitch" name="segment6-validateSwitch">
									<option>否</option>
									<option>是</option>
							</select></td>
							<td><span style="padding-left: 56px;">创建人:</span><input
								type="text" name="segment6-segmentCreate" id="segment6-segmentCreate"></td>
						</tr>
					</table>
					<div class="form_button segment_button">
						<button type="button" class="btn btn-outline btn-danger nextSegment"
							id="nextSegment6">下一段位</button>
						<button type="button" class="btn btn-outline btn-primary"
							id="clearsegmentForm6">清空</button>
					</div>
				</form>
			</div>
			<div class="tab-pane fade " id="segment7-pills">
				<form id="setgmentForm7" method="post">
					<table>
						<tr>
							<td><span>编码段位名称:</span><input type="text"
								name="segment7-segmentName" id="segment7-segmentName"></td>
							<td><span>编码段位来源:</span> <select id="segment7-segmentSource"
								name="segment7-segmentSource">
									<option>系统自动生成</option>
									<option>自定义值选择</option>
									<option>固定值输入</option>
									<option>自定义值输入</option>
									<option>系统属性绑定</option>
							</select></td>
							<td><span style="padding-left: 12px;">来源对应内容:</span><input
								type="text" name="segment7-sourceContent" id="segment7-sourceContent"></td>
						</tr>
						<tr>
							<td><span>段位最小长度:</span><input type="text" name="segment7-segmentMinLength"
								id="segment7-segmentMinLength"></td>
							<td><span>段位最大长度:</span><input type="text" name="segment7-segmentMaxLength"
								id="segment7-segmentMaxLength"></td>
							<td><span>是否高级格式化:</span> <select id="segment7-formatSwitch"
								name="segment7-formatSwitch">
									<option>否</option>
									<option>是</option>
							</select></td>
						</tr>
						<tr>
							<td><span>是否高级状态:</span><select id="segment7-statusSwitch"
								name="segment7-statusSwitch">
									<option>否</option>
									<option>是</option>
							</select></td>
							<td><span >是否高级校验:</span> <select
								id="segment7-validateSwitch" name="segment7-validateSwitch">
									<option>否</option>
									<option>是</option>
							</select></td>
							<td><span style="padding-left: 56px;">创建人:</span><input
								type="text" name="segment7-segmentCreate" id="segment7-segmentCreate"></td>
						</tr>
					</table>
					<div class="form_button segment_button">
						<button type="button" class="btn btn-outline btn-danger nextSegment"
							id="nextSegment7">下一段位</button>
						<button type="button" class="btn btn-outline btn-primary"
							id="clearsegmentForm7">清空</button>
					</div>
				</form>
			</div>
			<div class="tab-pane fade " id="segment8-pills">
				<form id="setgmentForm8" method="post">
					<table>
						<tr>
							<td><span>编码段位名称:</span><input type="text"
								name="segment8-segmentName" id="segment8-segmentName"></td>
							<td><span>编码段位来源:</span> <select id="segment8-segmentSource"
								name="segment8-segmentSource">
									<option>系统自动生成</option>
									<option>自定义值选择</option>
									<option>固定值输入</option>
									<option>自定义值输入</option>
									<option>系统属性绑定</option>
							</select></td>
							<td><span style="padding-left: 12px;">来源对应内容:</span><input
								type="text" name="segment8-sourceContent" id="segment8-sourceContent"></td>
						</tr>
						<tr>
							<td><span>段位最小长度:</span><input type="text" name="segment8-segmentMinLength"
								id="segment8-segmentMinLength"></td>
							<td><span>段位最大长度:</span><input type="text" name="segment8-segmentMaxLength"
								id="segment8-segmentMaxLength"></td>
							<td><span>是否高级格式化:</span> <select id="segment8-formatSwitch"
								name="segment8-formatSwitch">
									<option>否</option>
									<option>是</option>
							</select></td>
						</tr>
						<tr>
							<td><span>是否高级状态:</span><select id="segment8-statusSwitch"
								name="segment8-statusSwitch">
									<option>否</option>
									<option>是</option>
							</select></td>
							<td><span >是否高级校验:</span> <select
								id="segment8-validateSwitch" name="segment8-validateSwitch">
									<option>否</option>
									<option>是</option>
							</select></td>
							<td><span style="padding-left: 56px;">创建人:</span><input
								type="text" name="segment8-segmentCreate" id="segment8-segmentCreate"></td>
						</tr>
					</table>
					<div class="form_button segment_button">
						<button type="button" class="btn btn-outline btn-danger nextSegment"
							id="nextSegment8">下一段位</button>
						<button type="button" class="btn btn-outline btn-primary"
							id="clearsegmentForm8">清空</button>
					</div>
				</form>
			</div>
			<div class="tab-pane fade " id="segment9-pills">
				<form id="setgmentForm9" method="post">
					<table>
						<tr>
							<td><span>编码段位名称:</span><input type="text"
								name="segment9-segmentName" id="segment9-segmentName"></td>
							<td><span>编码段位来源:</span> <select id="segment9-segmentSource"
								name="segment9-segmentSource">
									<option>系统自动生成</option>
									<option>自定义值选择</option>
									<option>固定值输入</option>
									<option>自定义值输入</option>
									<option>系统属性绑定</option>
							</select></td>
							<td><span style="padding-left: 12px;">来源对应内容:</span><input
								type="text" name="segment9-sourceContent" id="segment9-sourceContent"></td>
						</tr>
						<tr>
							<td><span>段位最小长度:</span><input type="text" name="segment9-segmentMinLength"
								id="segment9-segmentMinLength"></td>
							<td><span>段位最大长度:</span><input type="text" name="segment9-segmentMaxLength"
								id="segment9-segmentMaxLength"></td>
							<td><span>是否高级格式化:</span> <select id="segment9-formatSwitch"
								name="segment9-formatSwitch">
									<option>否</option>
									<option>是</option>
							</select></td>
						</tr>
						<tr>
							<td><span>是否高级状态:</span><select id="segment9-statusSwitch"
								name="segment9-statusSwitch">
									<option>否</option>
									<option>是</option>
							</select></td>
							<td><span >是否高级校验:</span> <select
								id="segment9-validateSwitch" name="segment9-validateSwitch">
									<option>否</option>
									<option>是</option>
							</select></td>
							<td><span style="padding-left: 56px;">创建人:</span><input
								type="text" name="segment9-segmentCreate" id="segment9-segmentCreate"></td>
						</tr>
					</table>
					<div class="form_button segment_button">
						<button type="button" class="btn btn-outline btn-danger nextSegment"
							id="nextSegment9">下一段位</button>
						<button type="button" class="btn btn-outline btn-primary"
							id="clearsegmentForm9">清空</button>
					</div>
				</form>
			</div>
			<div class="tab-pane fade " id="segment10-pills">
				<form id="setgmentForm10" method="post">
					<table>
						<tr>
							<td><span>编码段位名称:</span><input type="text"
								name="segment10-segmentName" id="segment10-segmentName"></td>
							<td><span>编码段位来源:</span> <select id="segment10-segmentSource"
								name="segment10-segmentSource">
									<option>系统自动生成</option>
									<option>自定义值选择</option>
									<option>固定值输入</option>
									<option>自定义值输入</option>
									<option>系统属性绑定</option>
							</select></td>
							<td><span style="padding-left: 12px;">来源对应内容:</span><input
								type="text" name="segment10-sourceContent" id="segment10-sourceContent"></td>
						</tr>
						<tr>
							<td><span>段位最小长度:</span><input type="text" name="segment10-segmentMinLength"
								id="segment10-segmentMinLength"></td>
							<td><span>段位最大长度:</span><input type="text" name="segment10-segmentMaxLength"
								id="segment10-segmentMaxLength"></td>
							<td><span>是否高级格式化:</span> <select id="segment10-formatSwitch"
								name="segment10-formatSwitch">
									<option>否</option>
									<option>是</option>
							</select></td>
						</tr>
						<tr>
							<td><span>是否高级状态:</span><select id="segment10-statusSwitch"
								name="segment10-statusSwitch">
									<option>否</option>
									<option>是</option>
							</select></td>
							<td><span >是否高级校验:</span> <select
								id="segment10-validateSwitch" name="segment10-validateSwitch">
									<option>否</option>
									<option>是</option>
							</select></td>
							<td><span style="padding-left: 56px;">创建人:</span><input
								type="text" name="segment10-segmentCreate" id="segment10-segmentCreate"></td>
						</tr>
					</table>
					<div class="form_button segment_button">
						<button type="button" class="btn btn-outline btn-danger nextSegment"
							id="nextSegment10">下一段位</button>
						<button type="button" class="btn btn-outline btn-primary"
							id="clearsegmentForm10">清空</button>
					</div>
				</form>
			</div>
			<div class="tab-pane fade " id="segment11-pills">
				<form id="setgmentForm11" method="post">
					<table>
						<tr>
							<td><span>编码段位名称:</span><input type="text"
								name="segment11-segmentName" id="segment11-segmentName"></td>
							<td><span>编码段位来源:</span> <select id="segment11-segmentSource"
								name="segment11-segmentSource">
									<option>系统自动生成</option>
									<option>自定义值选择</option>
									<option>固定值输入</option>
									<option>自定义值输入</option>
									<option>系统属性绑定</option>
							</select></td>
							<td><span style="padding-left: 12px;">来源对应内容:</span><input
								type="text" name="segment11-sourceContent" id="segment11-sourceContent"></td>
						</tr>
						<tr>
							<td><span>段位最小长度:</span><input type="text" name="segment11-segmentMinLength"
								id="segment11-segmentMinLength"></td>
							<td><span>段位最大长度:</span><input type="text" name="segment11-segmentMaxLength"
								id="segment11-segmentMaxLength"></td>
							<td><span>是否高级格式化:</span> <select id="segment11-formatSwitch"
								name="segment11-formatSwitch">
									<option>否</option>
									<option>是</option>
							</select></td>
						</tr>
						<tr>
							<td><span>是否高级状态:</span><select id="segment11-statusSwitch"
								name="segment11-statusSwitch">
									<option>否</option>
									<option>是</option>
							</select></td>
							<td><span >是否高级校验:</span> <select
								id="segment11-validateSwitch" name="segment11-validateSwitch">
									<option>否</option>
									<option>是</option>
							</select></td>
							<td><span style="padding-left: 56px;">创建人:</span><input
								type="text" name="segment11-segmentCreate" id="segment11-segmentCreate"></td>
						</tr>
					</table>
					<div class="form_button segment_button">
						<button type="button" class="btn btn-outline btn-danger nextSegment"
							id="nextSegment11">下一段位</button>
						<button type="button" class="btn btn-outline btn-primary"
							id="clearsegmentForm11">清空</button>
					</div>
				</form>
			</div>
			<div class="tab-pane fade" id="segment12-pills">
				<form id="setgmentForm12" method="post">
					<table>
						<tr>
							<td><span>编码段位名称:</span><input type="text"
								name="segment12-segmentName" id="segment12-segmentName"></td>
							<td><span>编码段位来源:</span> <select id="segment12-segmentSource"
								name="segment12-segmentSource">
									<option>系统自动生成</option>
									<option>自定义值选择</option>
									<option>固定值输入</option>
									<option>自定义值输入</option>
									<option>系统属性绑定</option>
							</select></td>
							<td><span style="padding-left: 12px;">来源对应内容:</span><input
								type="text" name="segment12-sourceContent" id="segment12-sourceContent"></td>
						</tr>
						<tr>
							<td><span>段位最小长度:</span><input type="text" name="segment12-segmentMinLength"
								id="segment12-segmentMinLength"></td>
							<td><span>段位最大长度:</span><input type="text" name="segment12-segmentMaxLength"
								id="segment12-segmentMaxLength"></td>
							<td><span>是否高级格式化:</span> <select id="segment12-formatSwitch"
								name="segment12-formatSwitch">
									<option>否</option>
									<option>是</option>
							</select></td>
						</tr>
						<tr>
							<td><span>是否高级状态:</span><select id="segment12-statusSwitch"
								name="segment12-statusSwitch">
									<option>否</option>
									<option>是</option>
							</select></td>
							<td><span >是否高级校验:</span> <select
								id="segment12-validateSwitch" name="segment12-validateSwitch">
									<option>否</option>
									<option>是</option>
							</select></td>
							<td><span style="padding-left: 56px;">创建人:</span><input
								type="text" name="segment12-segmentCreate" id="segment12-segmentCreate"></td>
						</tr>
					</table>
					<div class="form_button segment_button">
						<button type="button" class="btn btn-outline btn-danger nextSegment"
							id="nextSegment12">下一段位</button>
						<button type="button" class="btn btn-outline btn-primary"
							id="clearsegmentForm12">清空</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- /.panel-body -->
</div>
<!-- /.panel -->
<!--  </div> -->


