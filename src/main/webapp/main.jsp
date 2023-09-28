<%--
  Created by IntelliJ IDEA.
  User: hxx
  Date: 2023/7/12
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>首页</title>


    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/style.min.css" rel="stylesheet">
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
<div id="wrapper">
    <!--左侧导航开始-->
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="nav-close"><i class="fa fa-times-circle"></i>
        </div>
        <div class="sidebar-collapse">
            <ul class="nav" id="side-menu">
                <li class="nav-header">
                    <div class="dropdown profile-element">
                        <span><img alt="image" class="img-circle" src="img/profile_small.jpg" /></span>
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                                    <span class="block m-t-xs"><strong class="font-bold">${sessionScope.loginUser.name}</strong></span>
                                    <span class="text-muted text-xs block">${sessionScope.loginUser.notes}<b class="caret"></b></span>
                                </span>
                        </a>
                        <ul class="dropdown-menu animated fadeInRight m-t-xs">
                            <li><a class="J_menuItem" href="/sys/changeServlet?action=saveOrUpdatePage&name=${sessionScope.loginUser.name}">修改密码</a></li>
                            <c:if test="${sessionScope.loginUser.notes!='超级管理员'}">
                                <li><a class="J_menuItem" href="/sys/resetServlet?action=saveOrUpdatePage&name=${sessionScope.loginUser.name}">重置密码</a></li>
                            </c:if>
                           <li class="divider"></li>
                            <li><a href="/sys/logoutServlet">安全退出</a></li>
                        </ul>
                    </div>
                    <div class="logo-element"></div>
                </li>
                <c:forEach items="${sessionScope.loginMenus}" var="sub">

                    <li>
                        <a class="J_menuItem" href="${sub.url}"><i class="fa fa-magic"></i> <span class="nav-label">${sub.name}</span></a>
                    </li>

                </c:forEach>

            </ul>
        </div>
    </nav>
    <!--左侧导航结束-->
    <!--右侧部分开始-->
    <div id="page-wrapper" class="gray-bg dashbard-1">
        <div class="row border-bottom">

        </div>
        <div class="row content-tabs">
            <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
            </button>
            <nav class="page-tabs J_menuTabs">
                <div class="page-tabs-content">
                    <a href="javascript:" class="active J_menuTab" data-id="index_v1.html">首页</a>
                </div>
            </nav>

            <a href="login.jsp" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
        </div>
        <div class="row J_mainContent" id="content-main">
            <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="home.jsp"  data-id="index_v1.html" ></iframe>
        </div>

    </div>
    <!--右侧部分结束-->

</div>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="js/plugins/layer/layer.min.js"></script>
<script src="js/hplus.min.js"></script>
<script type="text/javascript" src="js/contabs.min.js"></script>
<script src="js/plugins/pace/pace.min.js"></script>
</body>

</html>

