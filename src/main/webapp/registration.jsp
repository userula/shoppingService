<%@include file="jsp/jsp-components/header.jsp"%>
<script src="${pageContext.request.contextPath}/js/regJs.js"></script>
<style>
    <%@include file="style.css"%>
</style>
<div class="rggroups">
<div class="signup" id="signup">
    <br>
    <div id="h1">
        <h1>Register form</h1>
        <p id="errormsg"></p>
        <br>
        <form action="${pageContext.request.contextPath }/register" method="post">
            <input class="loginp" type="name" id="fnameInput" placeholder="First Name" name="fname">
            <br>
            <br>
            <input class="loginp" type="surname" id="lnameInput" placeholder="Last Name" name="lname">
            <br>
            <p id="checkemail"></p>
            <input class="loginp" type="email" name="email" id="emailInput"  placeholder="Email Address" >
            <br>
            <br>
            <div style="display: flex;">
                <input id="signupinput" class="loginp" type="password" name="pass" placeholder="Set Password">

                <div id="Oeye" onclick="pass('o')">
                    <i class='fas fa-eye' style='margin: 11px 5px 5px 5px; font-size: 24px; cursor: pointer;'></i>
                </div>

                <div id="Ceye" onclick="pass('c')">
                    <i class='fas fa-eye-slash' style='margin: 11px 5px 5px 5px; font-size: 22px; cursor: pointer;'></i>
                </div>
            </div>
            <br>
            <button class="button button-block" type="submit" name="regbtn" id="regbtn">Sign up</button>
        </form>
    </div>
</div>
</div>
<script>
    function pass(a) {

        if (a == 'o') {
            document.getElementById("Oeye").style.display = 'none';
            document.getElementById("Ceye").style.display = 'block';
            document.getElementById("signupinput").type = "text";
        } else {
            document.getElementById("Oeye").style.display = 'block';
            document.getElementById("Ceye").style.display = 'none';
            document.getElementById("signupinput").type = "password";
        }
    }

</script>

<%@include file="jsp/jsp-components/footer.jsp"%>