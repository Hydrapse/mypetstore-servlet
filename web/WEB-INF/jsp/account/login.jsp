<%--<%@ include file="../common/IncludeTop.jsp" %>--%>
<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="Sign in">
    <meta name="author" content="Hydra">
    <link rel="icon" href="images/titleIco.ico" sizes="32x32"/>

    <title>Sign in now!</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css">

    <!-- font-awesome 图标库 -->
    <link href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

    <!-- animate css 动画 -->
    <link rel="stylesheet" href="css/animate.css">

    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，必须放在script前边) -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>

    <!-- 加载 Bootstrap 的所有 JavaScript 插件。 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>

    <!-- Custom styles for this template -->
    <link rel="StyleSheet" href="css/verification.css" type="text/css"
          media="screen"/>
    <link rel="stylesheet" href="css/signin.css" type="text/css"
          media="screen"/>

</head>

<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-7">
            <a href="main">
                <img class="img-responsive center-block" src="images/phonecat2.gif">
            </a>
        </div>
        <div class="col-sm-5" id="form_div">
            <form class="form-signin" action="login" method="post">
                <h2 class="form-signin-heading">Please sign in</h2>
                <input type="text" id="username" name="username" class="form-control" placeholder="username" autocomplete="off" required autofocus>
                <input type="password" id="password"  name="password" class="form-control" placeholder="Password" required>
                <div class="register-text">Not a member? <a href="#" id="register-a" data-toggle="modal" data-target="#registerModal">Register</a></div>

                <button id="goLogin" class="btn btn-lg btn-warning btn-block" type="submit">Sign in</button>

                <button style="display: none;" id="login-submit" class="btn btn-lg btn-warning btn-block" type="button" data-toggle="modal" data-target="#myVerificationModal">Sign in</button>

            </form>
        </div>
    </div>

</div>

<!-- Register Modal -->
<div class="modal fade" id="registerModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="/*z-index: 100;*/">
    <div class="modal-dialog" role="document">
        <div class="modal-content register-content">
            <form id="register-form" action="register" method="post" class="form-horizontal">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Lovely Pets At Hydra Store!</h4>
                </div>
                <div class="modal-body">
                    <%@ include file="register.jsp" %>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal" id="register-close">Close</button>
                    <button type="button" class="btn btn-warning" onclick="checkRegisterForm()" id="register-submit">Sign Up</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Verify Modal -->
<div class="modal fade" id="myVerificationModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div id="verify-content" class="modal-content center-block">

            <%--验证码区域--%>
            <div id="slideBar" class="verify-body"></div>

            <div class="verify-footer">
                <a id="close-verify-btn" data-dismiss="modal"><span class="glyphicon glyphicon-remove-circle" style="font-size: 21px;padding-right: 5px;"></span></a>
                <a id="refresh-verify-btn"><span class="glyphicon glyphicon-repeat" style="font-size: 19px;"></span></a>
            </div>

        </div>
    </div>
</div>


<script type="text/javascript" src="js/login.js"></script>
<script type="text/javascript" src="js/verification.js"></script>

</body>

</html>

<%--<%@ include file="../common/IncludeBottom.jsp" %>--%>