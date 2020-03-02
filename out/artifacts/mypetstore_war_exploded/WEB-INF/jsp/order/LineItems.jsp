<%@include file="../common/IncludeTop.jsp"%>
<script type="text/javascript" src="js/order.js"></script>

<div id="Content" style="padding-bottom: 100px;">
    <div>
    <div id="Order2">
        <h3>Order Information</h3>
        <table class="table table-hover" style="max-width: 49%;">
            <tr>
                <th>Contact</th>
                <td>${sessionScope.account.phone}</td>
            </tr>
            <tr>
                <th>Ship To</th>
                <td>
                    ${sessionScope.order.shipAddress1} ${sessionScope.order.shipAddress2} ${sessionScope.order.shipCity}
                        ${sessionScope.order.shipState} ${sessionScope.order.shipZip}, ${sessionScope.order.shipCountry}
                </td>
            </tr>
            <c:if test="${sessionScope.isShipAddReq == 'true' }">
                <tr>
                    <th>Bill To</th>
                    <td>
                         ${sessionScope.order.billAddress1} ${sessionScope.order.billAddress2} ${sessionScope.order.billCity}
                         ${sessionScope.order.billState} ${sessionScope.order.billZip}, ${sessionScope.order.billCountry}
                    </td>
                </tr>
            </c:if>
            <c:if test="${sessionScope.isShipAddReq == 'false'}">
                <tr class="active">
                    <td colspan="2" style="text-align: center; color: #444444; font-weight: 600;">Billing Address Same As Shipping Address</td>
                </tr>
            </c:if>
            <tr>
                <td colspan="2"><a href="viewOrderForm">CHANGE</a></td>
            </tr>
        </table>

        <br>

        <h3>Take care of your pets!</h3>
        <table  class="table" style="max-width: 73%;">
            <tr>
                <th></th>
                <th>Quantity</th>
                <th>UnitPrice</th>
            </tr>
            <c:forEach var="cartItem" items="${sessionScope.order.lineItems}">
                <tr>
                    <td id="Portrait"><a href="viewProduct?productId=${cartItem.item.product.productId}">${cartItem.item.product.description}</a></td>
                    <td style="text-align: center;">${cartItem.quantity}</td>
                    <td style="text-align: center;"><fmt:formatNumber value="${cartItem.unitPrice}" pattern="$#,##0.00"/></td>
                </tr>
            </c:forEach>
            <tr class="active">
                <td colspan="3">SUB TOTAL: <fmt:formatNumber value="${sessionScope.cart.subTotal}" pattern="$#,##0.00"/></td>
            </tr>
        </table>

        <br>

        <a class="Button" href="#" onclick="confirmOrder()">Confirm Order</a>
    </div>

    </div>

</div>

<script>
    $(".containerFont > h1").text("CONFIRM FINAL ORDER");
</script>

<%@include file="../common/IncludeBottom.jsp"%>
