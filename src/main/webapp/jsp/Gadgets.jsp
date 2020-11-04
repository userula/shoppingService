<%@ page import="java.util.Queue" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="java.util.PriorityQueue" %>
<%@include file="jsp-components/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
    <%@include file="../js/regJs.js"%>
</script>
<div class="list">
    <div class="container">
        <div style="display: flex; flex-direction: row; justify-content: space-between;">
            <h1>Gadgets</h1>
            <h4 style="color: red">${sessionScope.msg}</h4>
        </div>
        <hr>
        <div class="row">
            <table cellpadding="2" cellspacing="2" border="1">
                <tr colspan="2">
                    <th>Name</th>
                    <th>Photo</th>
                    <th>Price</th>
                    <th>Buy</th>
                </tr>
                <c:forEach var="product" items="${sessionScope.gadj }">
                    <tr>
                        <td>${product.name }</td>
                        <td>
                            <img src="${product.photo }" width="120">
                        </td>
                        <td>${product.price }$</td>
                        <td align="center">
                            <button class="btn btn-primary buy" id="${product.id}">Add to cart</button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>

<%@include file="jsp-components/footer.jsp"%>
