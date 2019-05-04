<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Creative - Bootstrap 3 Responsive Admin Template">
    <meta name="author" content="GeeksLabs">
    <meta name="keyword" content="Creative, Dashboard, Admin, Template, Theme, Bootstrap, Responsive, Retina, Minimal">
    <link rel="shortcut icon" href="img/favicon.png">

    <title>404</title>

    <!-- Bootstrap CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <!-- bootstrap theme -->
    <link href="${pageContext.request.contextPath}/css/bootstrap-theme.css" rel="stylesheet">
    <!--external css-->
    <!-- font icon -->
    <link href="${pageContext.request.contextPath}/css/elegant-icons-style.css" rel="stylesheet" />
    <!-- Custom styles -->
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style-responsive.css" rel="stylesheet" />

    <style>
        * {
            margin: 0;
            padding: 0;
        }
        #pageError{
            height: 30%;
            width:50%;
            position: absolute;
            z-index: 2;
            top: 10%;
            left: 50%;
            -moz-transform: translate(-50%, -50%);
            -ms-transform: translate(-50%, -50%);
            -webkit-transform: translate(-50%, -50%);
            transform: translate(-50%, -50%);
        }

        #world {
            z-index: 1;
            position: absolute;
            width: 100%;
            height: 100%;
            overflow: hidden;
            background: linear-gradient(#e4e0ba, #f7d9aa);
        }
    </style>



    <!-- HTML5 shim and Respond.js IE8 support of HTML5 -->
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<script src="/js/three.min.js"></script>
<script src="${pageContext.request.contextPath}/js/pmsJS/common/404_page_js.js"></script>
<div id="world">
    <div class="page-404" id="pageError">
        <p class="text-404">404</p>
        <h2>页面找不到了</h2>
        <p>返回主页 <br><a href="${pageContext.request.contextPath}/index.jsp">Return Home</a></p>
    </div>
</div>

</body>
</html>
