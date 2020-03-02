<%--
  Created by IntelliJ IDEA.
  User: synapse
  Date: 2019/10/2
  Time: 1:19 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML>
<html>
<head>

<!----------------------------------------------Bootstrap----------------------------------------------->

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css">

    <!-- font-awesome 图标库 -->
    <link href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

    <!-- animate css 动画 -->
    <link rel="stylesheet" href="css/animate.css">

    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，必须放在script前边) -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>

    <%-- 搜索框自动填充 --%>
    <script src="js/bootstrap3-typeahead.js"></script>

    <!-- 加载 Bootstrap 的所有 JavaScript 插件。 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>

    <!-- jQuery color 插件 -->
    <script src="http://code.jquery.com/color/jquery.color-2.1.2.js" integrity="sha256-1Cn7TdfHiMcEbTuku97ZRSGt2b3SvZftEIn68UMgHC8=" crossorigin="anonymous"></script>

<!----------------------------------------------Bootstrap----------------------------------------------->

<%--    <link rel="StyleSheet" href="css/jpetstore.css" type="text/css" media="screen"/>--%>

<!----------------------------------------------HYDRA CSS----------------------------------------------->

    <link rel="StyleSheet" href="css/HydraStore.css" type="text/css"
          media="screen"/>

    <link rel="icon" href="images/titleIco.ico" sizes="32x32"/>

<!----------------------------------------------HYDRA CSS----------------------------------------------->

    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>Hydra's PetStore</title>
    <meta name="author" content="Hydra">
    <meta content="text/html; charset=UTF-8"
          http-equiv="Content-Type"/>
    <meta http-equiv="Cache-Control" content="max-age=0"/>
    <meta http-equiv="Cache-Control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <meta http-equiv="Expires" content="Tue, 01 Jan 1980 1:00:00 GMT"/>
    <meta http-equiv="Pragma" content="no-cache"/>

</head>

<body>
<!----------------------------------------------HYDRA SCRIPT----------------------------------------------->

<script type="text/javascript" src="js/account.js"></script>
<script type="text/javascript" src="js/commonTop.js"></script>

<!----------------------------------------------HYDRA SCRIPT----------------------------------------------->

<div id="hiddenTop" class="hiddenTop" style="display: none;">
    <p> Complete Personal Information </p>
</div>

<div class="containerFont">
    <h1> Come Here To Find Your Lovely PET!</h1>
</div>

<nav id="navigation" class="navbar navbar-default navbar-static-top top">
    <div class="container-fluid">

        <div class="navbar-header">
            <a class="navbar-brand" href="main" style="font-size: 20px; color: floralwhite;">Hydra Store</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="nav-collapse-1">
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="#" id="nav-catalog-dropdown" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Catalog <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li> <a href="viewCategory?categoryId=FISH#navigation">FISH</a></li>
                        <li><a href="viewCategory?categoryId=DOGS#navigation">DOGS</a></li>
                        <li><a href="viewCategory?categoryId=CATS#navigation">CATS</a></li>
                        <li><a href="viewCategory?categoryId=REPTILES#navigation">REPTILES</a></li>
                        <li><a href="viewCategory?categoryId=BIRDS#navigation">BIRDS</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="viewCart">SHOPPING CART</a></li>
                    </ul>
                </li>
            </ul>

            <form action="searchProduct#navigation" method="post" class="navbar-form navbar-left">
                <div class="form-group">
                    <input  id="search-box" data-provide="typeahead" type="text" name="keyword"
                            size="14" class="form-control" autocomplete="off" placeholder="Search">
                    <input type="hidden" name="reachInfoDisplay">
                </div>
                <button id="search-submit" type="submit" class="btn btn-link">
                    <i class="fa fa-search fa-lg"></i>
                </button>
            </form>

            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="viewCart"><i class="fa fa-shopping-bag"></i></a>
                </li>
                <li>
                    <a href="help.html"><i class="fa fa-info fa-lg"></i></a>
                </li>
                <li>
                    <c:if test="${sessionScope.account == null}">
                        <a href="viewLoginForm"><i class="fa fa-user-plus"></i></a>
                    </c:if>
                    <c:if test="${sessionScope.account != null}">
                        <a href="#" onclick="signOut()"><i class="fa fa-sign-out fa-lg"></i></a>
                    </c:if>
                </li>
                <li class="active">
                    <c:if test="${sessionScope.account == null}">
                        <a href="viewLoginForm">Login</a>
                    </c:if>
                    <c:if test="${sessionScope.account != null}">
                        <a href="viewAccountForm?#navigation">Account</a>
                    </c:if>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->

    </div>



<%--    <div id="Menu">--%>
<%--        <div id="MenuContent">--%>
<%--            <a href="viewCart"><img align="middle" name="img_cart" src="images/cart.gif"/></a>--%>

<%--            <img align="middle" src="images/separator.gif"/>--%>

<%--            <c:if test="${sessionScope.account == null}">--%>
<%--                <a href="viewLoginForm">Sign In</a>--%>
<%--            </c:if>--%>

<%--            <c:if test="${sessionScope.account != null}">--%>
<%--                <a href="#" onclick="signOut()">Sign Out</a>--%>
<%--                <img align="middle" src="images/separator.gif"/>--%>
<%--                <a href="viewAccountForm">My Account</a>--%>
<%--            </c:if>--%>

<%--            <img align="middle" src="images/separator.gif"/>--%>

<%--            <a href="help.html">?</a>--%>

<%--        </div>--%>
<%--    </div>--%>

<%--    <div id="Search">--%>
<%--        <div id="SearchContent">--%>
<%--            <form action="searchProduct" method="post">--%>
<%--                <input type="text" name="keyword" size="14" placeholder="Category/Name"/>--%>
<%--                <input type="submit" name="searchProducts" value="Search"/>--%>
<%--            </form>--%>
<%--        </div>--%>
<%--    </div>--%>

<%--    <div id="QuickLinks">--%>
<%--        <a href="viewCategory?categoryId=FISH">--%>
<%--            <img src="images/sm_fish.gif"/>--%>
<%--        </a>--%>
<%--        <img src="images/separator.gif"/>--%>
<%--        <a href="viewCategory?categoryId=DOGS">--%>
<%--            <img src="images/sm_dogs.gif"/>--%>
<%--        </a>--%>
<%--        <img src="images/separator.gif"/>--%>
<%--        <a href="viewCategory?categoryId=REPTILES">--%>
<%--            <img src="images/sm_reptiles.gif"/>--%>
<%--        </a>--%>
<%--        <img src="images/separator.gif"/>--%>
<%--        <a href="viewCategory?categoryId=CATS">--%>
<%--            <img src="images/sm_cats.gif"/>--%>
<%--        </a>--%>
<%--        <img src="images/separator.gif"/>--%>
<%--        <a href="viewCategory?categoryId=BIRDS">--%>
<%--            <img src="images/sm_birds.gif"/>--%>
<%--        </a>--%>
<%--    </div>--%>
</nav>

