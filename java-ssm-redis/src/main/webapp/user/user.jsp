<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/20
  Time: 21:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MDM主数据管理</title>
    
    <!-- jQuery -->
    <script src="${pageContext.request.contextPath}/static/vendor/jquery/jquery.min.js"></script>
    
</head>
<body>
<div style="text-align: center">
    <h1>登陆成功</h1>
</div>
欢迎${user.userName}
<shiro:hasRole name="user">
    <button>query</button>
</shiro:hasRole>
<div id="menuTree" class="menuTree"></div>
  <script type="text/javascript">
var json = [{
    "name": "菜单栏效果",
    "list": [{
      "name": "-菜单栏效果",
      "url": "#a1"
    }, {
      "name": "-菜单栏效果a",
      "list": [{
        "name": "--菜单栏效果",
        "url": "#a11"
      }, {
        "name": "--菜单栏效果a",
        "list": [{
          "name": "---菜单栏效果",
          "url": "#a111"
        }, {
          "name": "---菜单栏效果a",
          "list": [{
            "name": "----菜单栏效果",
            "url": "#a1111"
          }, {
            "name": "----菜单栏效果a",
            "url": "#a1112"
          }]
        }]
      }, {
        "name": "--菜单栏效果aa",
        "url": "#a13"
      }, {
        "name": "--菜单栏效果aaa",
        "url": "#a14"
      }]
    }, {
      "name": "-菜单栏效果",
      "url": "#a3"
    }]
  }, {
    "name": "-b",
    "list": [{
      "name": "--b",
      "url": "#b1"
    }, {
      "name": "--bb",
      "list": [{
        "name": "----b",
        "url": "#b111"
      }, {
        "name": "----bb",
        "url": "#b112"
      }]
    }, ]
  }, {
    "name": "-c",
    "list": [{
      "name": "--c",
      "url": "#c1"
    }, {
      "name": "--cc",
      "url": "#c2"
    }]
  }, {
    "name": "-d"
  }]
  /*递归实现获取无级树数据并生成DOM结构*/
var str = "";
var forTree = function(o) {
    for (var i = 0; i < o.length; i++) {
      var urlstr = "";
      try {
        if (typeof o[i]["url"] == "undefined") {
          urlstr = "<div><span>" + o[i]["name"] + "</span><ul>";
        } else {
          urlstr = "<div><span><a href=" + o[i]["url"] + ">" + o[i]["name"] + "</a></span><ul>";
        }
        str += urlstr;
        if (o[i]["list"] != null) {
          forTree(o[i]["list"]);
        }
        str += "</ul></div>";
      } catch (e) {}
    }
    return str;
  }
   /*添加无级树*/
   console.log(forTree(json));
document.getElementById("menuTree").innerHTML = forTree(json);
/*树形菜单*/
/*var menuTree = function() {
  //给有子对象的元素加[+-]
  $("#menuTree ul").each(function(index, element) {
    var ulContent = $(element).html();
    var spanContent = $(element).siblings("span").html();
    if (ulContent) {
      $(element).siblings("span").html("<strong>[+]</strong> " + spanContent)
    }
  });
  $("#menuTree").find("div span").click(function() {
    var ul = $(this).siblings("ul");
    var spanStr = $(this).html();
    var spanContent = spanStr.substr(20, spanStr.length);
    if (ul.find("div").html() != null) {
      if (ul.css("display") == "none") {
        ul.show(300);
        $(this).html("<strong>[-]</strong>" + spanContent);
      } else {
        ul.hide(300);
        $(this).html("<strong>[+]</strong> " + spanContent);
      }
    }
  })
}() */
</script>
</body>
</html>
</body>
</html>
