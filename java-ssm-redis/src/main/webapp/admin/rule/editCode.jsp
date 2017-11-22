<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%@ page isELIgnored="false" %> --%>
<!-- <div class="div_form">
	<form role="form">
		<table class="from_table">
			<tr>
				<td>
					<span class="form_span"><font class="span_font">编码</font></span><input type="text" class="form_input"  id="inputSuccess" >
				</td>
				<td>
					<span class="form_span"><font class="span_font">编码</font></span><input type="text" class="form_input"  id="inputSuccess" >
				</td>
			</tr>
		</table>
	</form>
</div> -->

<script type="text/javascript">
	
	//添加编码规则
 	function checkAddRuleFrom() {
 		$.ajax({
            type: "POST",
            url:'${pageContext.request.contextPath }/rule/addRule.do',
            data:$('#ruleForm').serialize(),// 你的formId
            dataType:"json",
  	      	contentType:"application/x-www-form-urlencoded",
            success: function(result) {
                if (result.error) {
              		 alert(result.error);
				}else {
					 alert(result.success);
					 $('#addCodeSegmentDiv').load('admin/segment/add.jsp').show();	
				}
            } 
		});
		/* if (isEmptyCodeRuleForm()) {
			
		} 	 */	
	} 
	
	
	function isEmptyCodeRuleForm() {
		if ($('#rulesName').val()=='') {
			alert('编码规则名称不能为空!');
			return false;
		}else if ($('#segmentQuantity').val()=='') {
			alert('编码段位数量不能为空!');
			return false;
		}else if (!isUnsignedInterger($('#segmentQuantity').val())) {
			alert('编码段位数量只能为正整数!');
			return false;
		}else if (isChinese($('#rulesSeparator').val())) {
			alert('编码分隔符不能为汉字!');
			return false;
		}else if (isUnsignedInterger($('#rulesSeparator').val())) {
			alert('编码分隔符不能为数字!');
			return false;
		}else if ($('#minLength').val()=='') {
			alert('编码最小长度不能为空!');
			return false;
		}else if (!isUnsignedInterger($('#minLength').val())) {
			alert('编码最小长度只能为正整数!');
			return false;
		}else if ($('#maxLength').val()=='') {
			alert('编码最大长度不能为空!');
			return false;
		}else if ($('#minLength').val()>$('#maxLength').val()) {
			alert('编码最小长度不能大于最大长度!');
			return false;
		}else if (!isUnsignedInterger($('#maxLength').val())) {
			alert('编码最大长度只能为正整数!');
			return false;
		}else if ($('#rulesStatus').val()=='') {
			alert(' 编码规则状态不能为空!');
			return false;
		}else if ($('#validateSwitch').val()=='') {
			alert('是否高级校验不能为空!');
			return false;
		}else if ($('#formatSwitch').val()=='') {
			alert('是否高级格式化不能为空!');
			return false;
		}else{ 
			return true;
		}
	}
	
	/* function checkAddRuleFrom() {
		$.ajax({
	        type: "POST",
	        url:'${pageContext.request.contextPath }/rule/addRule.do',
	        data:$('#ruleForm').serialize(),// 你的formId
	        dataType:"json",
		      contentType:"application/x-www-form-urlencoded",
	        success: function(result) {
	            if (result.error) {
	          		 alert(result.error);
				}else {
					 alert(result.success);
					 // alert(${segmentQuantity}); 
					 $('#addCodeSegmentDiv').load('admin/segment/add.jsp').show();	
					 
				}
	        } 
		}); */
		
		/* $('#addCodeSegmentDiv').html('< jsp:include page="admin/segment/add.jsp">< jsp:param name="param name" value="paramvalue" />< /jsp:include> ');
		 */
	
		
	function clearForm() {
		$('#rulesName').val('');
		$('#segmentQuantity').val("");
		$('#rulesSeparator').val("");
		$('#minLength').val("");
		$('#maxLength').val("");
		$('#rulesStatus').val("Enable");
		$('#codeSource').val("系统生成");
		$('#validateSwitch').val("否");
		$('#formatSwitch').val('否');
		$('#description').val('');
		$('#version').val('');
		$('#creator').val(''); 
	}
	
	function isUnsignedInterger(str){
		var reg= /^[0-9]*[1-9][0-9]*$/;
		return reg.test(str);
	}
	
	function isChinese(str){
		if (str==''||str==null) {
			return false;
		}else {
			var reg=/^[\u4e00-\u9fa5]{0,}$/;
			return reg.test(str);
		}
	}
	
	$(function() {
		$('#codeRulesCategoryId').val();
		
	});
</script>

<div class="div_form" id="addCodeRulesDiv">
	<div class="panel panel-default">
		<div class="panel-heading">
	       	新建编码规则
	    </div>
	</div>
	<form id="ruleForm" method="post">
		<table>
			<tr>
				<td><span>编码规则名称:</span><input type="text" name="rulesName" id="rulesName"></td>
				<td><span>编码段位数量:</span><input type="text" name="segmentQuantity" id="segmentQuantity"></td>
				<td><span style="margin-left: 52px; margin-right: 10px;">&nbsp;&nbsp;&nbsp;分隔符:&nbsp;&nbsp;</span><input
					type="text" name="rulesSeparator" id="rulesSeparator"></td>
			</tr>
			<tr>
				<td><span>编码最小长度:</span><input type="text" name="minLength" id="minLength"></td>
				<td><span>编码最大长度:</span><input type="text" name="maxLength" id="maxLength"></td>
				<td><span>&nbsp;&nbsp;编码规则状态:</span> 
				<select id="rulesStatus" name="rulesStatus">
						<option>Enable</option>
						<option>Disable</option>
				</select></td>
			</tr>
			<tr>
				<td><span>是否高级校验:</span>
				<select id="validateSwitch" name="validateSwitch">
						<option>否</option>
						<option>是</option>
				</select></td>
				<td><span style="margin-left: 42px;">编码来源:</span>
				<select id="codeSource" name="codeSource">
						<option>系统生成</option>
						<option>外部生成</option>
						<option>都可以</option>
				</select></td>
				<td><span>是否高级格式化:</span>
				<select id="formatSwitch" name="formatSwitch">
						<option>否</option>
						<option>是</option>
				</select></td>
			</tr>
			<tr>
				<td><span style="margin-left: 72px; margin-right: 10px;">描述:</span><input
					type="text" id="description" name="description"></td>
				<td><span style="margin-left: 56px; margin-right: 12px;">版本号:</span><input
					type="text" id="version" name="version"></td>
				<td><span style="margin-left: 72px; margin-right: 12px;">创建人:</span><input
					type="text" id="creator" name="creator">
					<input type="hidden"  id="codeRulesCategoryId" name="codeRulesCategoryId">
					</td>
			</tr>
		</table>
		<div class="form_button">
			<button type="button" class="btn btn-outline btn-primary" onclick="checkAddRuleFrom()">完成</button>
			<button type="button" class="btn btn-outline btn-danger" onclick="clearForm()">清空</button>
		</div>
	</form>
</div>
<!-- <div id="addCodeSegmentDiv" class="div_form segment" style="display: none;"> -->
<div id="addCodeSegmentDiv" class="div_form segment" style="display: none;">
</div>

<!-- 
		<div class="form-group has-success">
		       <label class="control-label" for="inputSuccess">Input with success</label>
		       <input type="text" class="form-control" id="inputSuccess">
		</div>
		<div class="form-group has-warning">
			<label class="control-label" for="inputWarning">Input with
				warning</label> <input type="text" class="form-control" id="inputWarning">
		</div>
		<div class="form-group has-error">
			<label class="control-label" for="inputError">Input with error</label>
			<input type="text" class="form-control" id="inputError">
		</div> -->