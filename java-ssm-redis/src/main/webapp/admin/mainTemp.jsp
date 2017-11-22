<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>不知道什么系统</title>

    <!-- jQuery -->
    <script src="${pageContext.request.contextPath}/static/vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${pageContext.request.contextPath}/static/vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="${pageContext.request.contextPath}/static/vendor/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="${pageContext.request.contextPath}/static/dist/js/sb-admin-2.js"></script>

    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath}/static/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="${pageContext.request.contextPath}/static/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/static/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="${pageContext.request.contextPath}/static/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <%--common Css--%>
    <link href="${pageContext.request.contextPath}/static/css/common.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->

</head>
<script type="text/javascript">
    function logout() {
        window.location.href='${pageContext.request.contextPath}/logout';
    }
    
    /*递归实现获取无级树数据并生成DOM结构*/
	var urlPathstr = "";
	var parentId='${topTreeNodeAuthId}';
	var count=0;
	var genTree = function(o) {
		if(o==''||o==null){
			return;
		}
	   $(o).each(function() {
		   var urlPath="";
		   if (this.parentId==parentId) {
			   if (this.children==null) {
	  		   		urlPath='<li class=""><a href="${pageContext.request.contextPath}'+this.authPath+'" ><i class="fa fa-sitemap fa-fw"></i>'+this.authName+'<span class="fa arrow"></span></a>';
			   }else{
				    urlPath='<li class=""><a href="#"><i class="fa fa-sitemap fa-fw"></i> '+this.authName+'<span class="fa arrow"></span></a><ul class="nav nav-second-level collapse" aria-expanded="false" >';
			   }
		   }else{
			   if (this.children==null) {
			   		urlPath='<li><a href="${pageContext.request.contextPath}'+this.authPath+'">'+this.authName+'</a>';
			   }else {
				    urlPath='<li class=""><a href="#">'+this.authName+'<span class="fa arrow"></span></a><ul class="nav nav-third-level collapse" aria-expanded="false" >';
			   }
		   }
		   urlPathstr+=urlPath;
		   if (this.children!=null) {
			   genTree(this.children);
		   }
	   });
	   urlPathstr+='</ul></li>';
	   return urlPathstr;
	}
    
	/* var findul=function(menu){
		if (menu.find('ul')) {
			if ($("#side-menu li ul").attr('class')=='nav nav-second-level collapse') {
				$("#side-menu li ul").prop('class','nav nav-second-level collapse in').prop('aria-expanded','true');
				$("#side-menu :not(:first)li").prop('class','active');
			}else {
				$("#side-menu li ul").prop('class','nav nav-second-level collapse').prop('aria-exoanded','false');
				$("#side-menu :not(:first)li").prop('class','');
			}
		}
	} */
	
	$(function() {
		 //生成菜单树
		 $("#side-menu").append(genTree(${menuTree})); 
		 //绑定点击事件,实现动画效果
		 $('#side-menu li a').on('click',function(event){
			 event.preventDefault();
			 var href=this.href;
			 if(href.indexOf("#")==-1){
				 /* alert(this.href);  */
				 $.ajax({
			            url:this.href,
			            type:'post',
			            contentType:'application/json;charset=utf-8', 
			            dataType:'json', 
			            /* data:JSON.stringify(data),  */
			            success:function(result){
			            	/* console.log(result.jsp); */
			            	$('#showDiv').load('/admin/rule/codeMenu.jsp');
			            },
			        });
			 }
			 if ($(this).siblings("ul").attr('class')=='nav nav-second-level collapse') {
					if ($(this).siblings('ul').attr('class')=='nav nav-second-level collapse') {
						$(this).parent('li').prop('class','active');
						$(this).siblings('ul').prop('class','nav nav-second-level collapse in').prop('aria-expanded','true');
					}else {
						$(this).parent('li').prop('class','');
						$(this).siblings('ul').prop('class','nav nav-second-level collapse').prop('aria-expanded','false');
					}
				}else {
					 if ($(this).siblings('ul').attr('class')=='nav nav-third-level collapse') {
						$(this).parent('li').prop('class','active');
						$(this).siblings('ul').prop('class','nav nav-third-level collapse in').prop('aria-expanded','true');
					}else {
						$(this).parent('li').prop('class','');
						$(this).siblings('ul').prop('class','nav nav-third-level collapse').prop('aria-expanded','false');
					} 
				}  
		  });
	});
	
	
	 /* $("#success-alert").fadeTo(2000, 500).slideUp(500, function(){
    $("#success-alert").slideUp(500); 
	  }); */
	
	/*  $(function(){ 
	 var url='${pageContext.request.contextPath}/mainTemp.html';
	  $.ajax({
	      type:"POST",
	      url:url,
	      /*dataType:"json",
	      contentType:"application/json",
	      data:JSON.stringify(data),
	      success:function(result){
		      var result=eval('('+result+')'); 
		      console.log(result);
	      }
	  }); 
 	});   */

</script>
<body>

<div id="wrapper">
    
    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">

        <!-- .navbar-header -->
        <jsp:include page="${pageContext.request.contextPath}/common/head.jsp" />
        <!-- /.navbar-header -->

        <!-- .navbar-static-side -->
        <jsp:include page="${pageContext.request.contextPath}/common/menu.jsp" />
        <!-- /.navbar-static-side -->

    </nav>

    <!-- Page Content -->
        <jsp:include page="${pageContext.request.contextPath}/common/pageContext.jsp" />
    <!-- /#page-wrapper -->

    <%--foot--%>
        <jsp:include page="${pageContext.request.contextPath}/common/foot.jsp" />
    <%--/foot--%>
    <%--退出确认框--%>
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title" id="myModalLabel">系统提示!</h4>
                </div>
                <div class="modal-body">
                   您确定要退出系统吗?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" onclick="logout()" >确认</button>
                    <button type="button" class="btn btn-info" data-dismiss="modal">取消</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    <%--/退出确认框--%>
</div>
<!-- /#wrapper -->
</body>

</html>