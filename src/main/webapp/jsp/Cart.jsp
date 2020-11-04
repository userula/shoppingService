<%@include file="jsp-components/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="list">
    <div class="container">
        <div style="display: flex; flex-direction: row; justify-content: space-between;">
            <h1>Cart</h1>
            <h4 style="color: red">${sessionScope.msg}</h4>
        </div>
        <hr>
        <div class="row">
            <table cellpadding="2" cellspacing="2" border="1">
                <tr colspan="2">
                    <th>Name</th>
                    <th>Photo</th>
                    <th>Price</th>
                    <th>Option</th>
                </tr>
                <c:forEach var="product" items="${sessionScope.cart}">
                    <tr>
                        <td>${product.name }</td>
                        <td>
                            <img src="${product.photo }" width="120">
                        </td>
                        <td>${product.price }</td>
                        <td align="center">
                            <a href="${pageContext.request.contextPath }/cart?&action=remove&id=${product.id }">Remove</a>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td></td>
                    <td>Total price:</td>
                    <td>${sessionScope.total}$</td>
                    <td><button type="button" class="btn btn-success">Buy</button></td>
                </tr>
            </table>
        </div>
    </div>
</div>

<%@include file="jsp-components/footer.jsp"%>