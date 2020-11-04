
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>MyApp</title>
    <meta charset="UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap">
    <link rel="stylesheet" href="node_modules/mdbootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="node_modules/mdbootstrap/css/mdb.min.css">
    <link rel="stylesheet" href="node_modules/mdbootstrap/css/style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.js" crossorigin="anonymous"></script>
    <link href="https://fonts.googleapis.com/css2?family=Assistant:wght@600&family=Patua+One&display=swap" rel="stylesheet">
    <style>
        .container{
            padding:5%;
        }
        .login-form{
            border: 2px solid lightblue;
            box-shadow: 0 0 1px lightblue, 0 0 2px lightblue,
                        0 0 6px lightblue, 0 0 12px lightblue,
                        inset 0 0 1px lightblue,
                        inset 0 0 2px lightblue,
                        inset 0 0 6px lightblue,
                        inset 0 0 12px lightblue;
        }
        .reg-form{
            border: 2px solid lightgreen;
            box-shadow: 0 0 1px #b3ff51, 0 0 2px #b3ff51,
                        0 0 6px #b3ff51,
                        0 0 12px #b3ff51,
                        inset 0 0 1px #b3ff51,
                        inset 0 0 2px #b3ff51,
                        inset 0 0 6px #b3ff51,
                        inset 0 0 12px #b3ff51;
        }
        *{
            font-family: 'Patua One', cursive;
        }
        .list{
            margin: 20px;
        }
        .list .container{
            box-shadow: 0 0 20px;
            padding: 50px;
            background-color: white;
        }
        .list .col-md-3{
            opacity: .5;
            transition: .5s;
            transform: scale(.9);
            position: relative;
        }
        .list .col-md-3:hover{
            opacity: 1;
            transform: scale(1);
        }
        .col-md-3:hover .centered{
            opacity: 1;
            transform: scale(3);
        }
        #asd
        {
            position: fixed; width: 100%; z-index: +1;
        }
        .searchBook input[type=text] {
            transition: width 0.4s ease-in-out;
        }

        .searchBook input[type=text]:focus {
            width: 100%;
        }
    </style>
    <link rel="stylesheet" href="../style.css">
</head>
<body class="d-flex flex-column h-100">
<nav class="navbar navbar-inverse navbar-expand-lg" id="asd">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="${pageContext.request.contextPath}">Shop</a>
            <button class="navbar-toggle collapsed" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="glyphicon glyphicon-menu-hamburger"></span></button>
        </div>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="nav navbar-nav">

                <li><a href="${pageContext.request.contextPath}/jsp/homepage.jsp">Home</a></li>
                <c:if test="${cookie.user.value != null}">
                    <li><a href="${pageContext.request.contextPath}/jsp/Sport.jsp">Sport</a></li>
                    <li><a href="${pageContext.request.contextPath}/jsp/Gadgets.jsp">Gadgets</a></li>
                    <li><a href="${pageContext.request.contextPath}/jsp/Clothes.jsp">Clothes</a></li>
                </c:if>

            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${cookie.user.value != null}">
                    <li><a href="<%=application.getContextPath()%>/jsp/Cart.jsp"><span class="glyphicon glyphicon-shopping-cart">Cart </a></li>
                </c:if>

                <c:if test="${cookie.user.value.length()>0}">
                    <li class="border border-primary"><a href="#" class="border border-primary"><span class="glyphicon glyphicon-user"></span> ${cookie.user.value}</a>  </li>
                <li><a href="${pageContext.request.contextPath }/logout" type="submit"> <span class="glyphicon glyphicon-user"></span>Logout</a></li>
                </c:if>

                <c:if test="${cookie.user.value == null}">
                    <li><a href="<%=application.getContextPath()%>/registration.jsp"><span class="glyphicon glyphicon-user"></span> Register </a></li>
                    <li><a href="<%=application.getContextPath()%>/index.jsp"><span class="glyphicon glyphicon-user"></span> Login </a></li>
                </c:if>

            </ul>
<%--            <form class="navbar-form navbar-left">--%>
<%--                <div class="form-group searchBook">--%>
<%--                    <input type="text" name="productName" id="spname" class="form-control" placeholder="Search">--%>
<%--                </div>--%>
<%--                <button type="btn" name="search" id="sbtn" class="btn btn-default">Search</button>--%>
<%--            </form>--%>
        </div>
    </div>
</nav>
<br><br><br>

<script type="text/javascript">
    $(document).ready(function(){
        $("#sbtn").on("click", function (){
            event.preventDefault();
            var a = $( "#spname" ).val();
            var url = "${pageContext.request.contextPath}/search?&productName="+a;

            $.ajax({
                url: url,
                type: 'GET',  // http method
                accepts: 'application/json; charset=utf-8',
                //contentType: "application/json",
                success: function (data) {
                    if(data[0].length != 0)
                    {
                        $('#errrr').hide();

                        $("#sname").text(data[0].name);
                        $("#simg").attr('src', data[0].img_url);
                        $("#sauthor").attr('value', data[0].author);
                        $("#errormsg").show();
                    }
                    else{
                        alert("No result find");
                        $("#errrr").text("No result find");
                        $("#errormsg").hide();
                        $("#errrr").show();

                    }

                },
                error: function (errorData, textStatus, errorMessage) {
                    var msg = (errorData.responseJSON != null) ? errorData.responseJSON.errorMessage : '';
                    $("#errormsg").text('Error: ' + msg + ', ' + errorData.status);

                    $("#errormsg").show();
                }
            });

        });
    });
</script>