<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<div class="col-md-2">
	<div id="showEditCodeMenu">
		<div class="sider-menu code-menu">
			<ul class="nav" id="code-menu">
				<!--  <li class=""><a href="#"><i class="fa fa-sitemap fa-fw"></i>
				编码大类<span class="fa arrow"></span></a>
			<ul class="nav nav-second-level collapse" aria-expanded="false"
				style="height: 0px;">
				<li><a href="#">测试一</a></li>
				<li><a href="#">测试二</a></li>
			</ul></li>  -->
			</ul>
		</div>
	</div>
</div>
<div class="col-md-10">
	<div id="showEditCodeDiv" style="display: none;">
		 <jsp:include page="/admin/rule/editCode.jsp" /> 
	</div>
</div>
<!-- /.sidebar-collapse -->
<script type="text/javascript">
	var codeMenuStr="";
	var count=0;
	//生成权限树
	var genCodeMenu=function(o){
		if(o==''||o==null){
			return;
		}
		$(o).each(function() {
			var codeMenuEachStr="";
			if (this.children==null) {
				codeMenuEachStr='<li class=""><a href="" >'+this.categoryName+'</a><input type="hidden" value="'+this.codeRulesCategoryId+'">';
			}else{
				console.log(count);
				if (count==1) {
					codeMenuEachStr='<li class=""><a href="#">'+this.categoryName+'<span class="fa arrow"></span></a><ul class="nav nav-third-level collapse" aria-expanded="false" >';
					count=0;
				}else {
					codeMenuEachStr='<li class=""><a href="#">'+this.categoryName+'<span class="fa arrow"></span></a><ul class="nav nav-second-level collapse" aria-expanded="false" >';
					count++;
				}
			}
			codeMenuStr+=codeMenuEachStr;
			if (this.children!=null) {
				genCodeMenu(this.children);
			}
		});
		codeMenuStr+='</ul></li>';
		return codeMenuStr;
	}
	
	$(function() {
		$('#code-menu').append(genCodeMenu(${codeRulesTree}));
		$('#code-menu li a').on('click',function(event){
			event.preventDefault();
			var href=this.href;
			if (href.indexOf('#')==-1) {
				$('#showEditCodeDiv').show();
				//隐藏编码段位
				$('#addCodeSegmentDiv').css("display","none");
				if ($(this).next().val()!='') {
					/* alert($(this).next().val()); */
					//将分类Id插入编码规则form隐藏域
					$('#codeRulesCategoryId').val($(this).next().val());
				}
			}else {
				$('#showEditCodeDiv').hide();
			}
			//实现权限树动态效果
			if ($(this).siblings('ul').attr('class')=='nav nav-second-level collapse') {
				$(this).parent('li').prop('class','active');
				$(this).siblings('ul').prop('class','nav nav-second-level collapse in').prop('aria-expanded','true');
				if ($(this).siblings('ul').find('a').html().indexOf('&nbsp')==-1) {
					$(this).siblings('ul').find('a').prepend('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;');
				}
			}else if (($(this).siblings('ul').attr('class')=='nav nav-third-level collapse')) {
				$(this).siblings('ul').prop('class','nav nav-third-level collapse in').prop('aria-expanded','true');
				$(this).siblings('ul').find('a').prepend('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;');
			}else {
				$(this).parent('li').prop('class','');
				$(this).siblings('ul').prop('class','nav nav-second-level collapse').prop('aria-expanded','false');
			}
		});
			 
	});

</script>

