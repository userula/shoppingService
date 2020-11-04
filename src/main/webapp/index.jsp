
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="jsp/jsp-components/header.jsp"></jsp:include>
<style>
    <%@include file="style.css"%>
</style>
<script src="${pageContext.request.contextPath}/js/regJs.js"></script>

<div class="rggroups">
    <div class="login" id="login">
        <br>
        <div id="h1">
            <h1>Login form</h1>
            <p id="logerr">${requestScope.message}</p>
            <br>
            <form action="${pageContext.request.contextPath }/login" method="post">
                <input class="loginp" type="email" id="logEmail" placeholder="Email Address" name="email" required autocomplete="on">
                <br>
                <br>
                <input class="loginp" type="password" id="logPass" name="pass" placeholder="Password">
                <br>
                <br>
                <button class="button button-block" id="logbtn" />Log In</button>
            </form>
        </div>
    </div>
</div>

<jsp:include page="jsp/jsp-components/footer.jsp"></jsp:include>

