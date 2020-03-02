<%@ include file="../common/IncludeTop.jsp"%>

<div id="Content" style="padding-bottom: 100px;">

<ul class="message">
    <li>Thank you, your order has been submitted.</li>
</ul>

<div id="BackLink" style="">
    <a href="checkOrderList">Return to my orders</a>
</div>

<div id="Catalog" style="padding-bottom: 0px;">

    <div id="Order3">

    <table class="table table-bordered">
        <tr>
            <th align="center" colspan="2">
                Order #${sessionScope.reviewOrder.orderId}
                ${sessionScope.reviewOrder.orderDateString}
<%--                <fmt:formatDate value="${sessionScope.reviewOrder.orderDate}" pattern="yyyy/MM/dd hh:mm:ss" type="date"/>--%>
            </th>
        </tr>
        <tr class="active">
            <th colspan="2">Payment Details</th>
        </tr>
        <tr>
            <td>Card Type:</td>
            <td><c:out value="${sessionScope.reviewOrder.cardType}" /></td>
        </tr>
        <tr>
            <td>Card Number:</td>
            <td><c:out value="${sessionScope.reviewOrder.creditCard}" /></td>
        </tr>
        <tr>
            <td>Expiry Date (MM/YYYY):</td>
            <td><c:out value="${sessionScope.reviewOrder.expiryDate}" /></td>
        </tr>
        <tr class="active">
            <th colspan="2">Billing Address</th>
        </tr>
        <tr>
            <td>First name:</td>
            <td><c:out value="${sessionScope.reviewOrder.billToFirstName}" /></td>
        </tr>
        <tr>
            <td>Last name:</td>
            <td><c:out value="${sessionScope.reviewOrder.billToLastName}" /></td>
        </tr>
        <tr>
            <td>Address 1:</td>
            <td><c:out value="${sessionScope.reviewOrder.billAddress1}" /></td>
        </tr>
        <tr>
            <td>Address 2:</td>
            <td><c:out value="${sessionScope.reviewOrder.billAddress2}" /></td>
        </tr>
        <tr>
            <td>City:</td>
            <td><c:out value="${sessionScope.reviewOrder.billCity}" /></td>
        </tr>
        <tr>
            <td>State:</td>
            <td><c:out value="${sessionScope.reviewOrder.billState}" /></td>
        </tr>
        <tr>
            <td>Zip:</td>
            <td><c:out value="${sessionScope.reviewOrder.billZip}" /></td>
        </tr>
        <tr>
            <td>Country:</td>
            <td><c:out value="${sessionScope.reviewOrder.billCountry}" /></td>
        </tr>
        <tr class="active">
            <th colspan="2">Shipping Address</th>
        </tr>
        <tr>
            <td>First name:</td>
            <td><c:out value="${sessionScope.reviewOrder.shipToFirstName}" /></td>
        </tr>
        <tr>
            <td>Last name:</td>
            <td><c:out value="${sessionScope.reviewOrder.shipToLastName}" /></td>
        </tr>
        <tr>
            <td>Address 1:</td>
            <td><c:out value="${sessionScope.reviewOrder.shipAddress1}" /></td>
        </tr>
        <tr>
            <td>Address 2:</td>
            <td><c:out value="${sessionScope.reviewOrder.shipAddress2}" /></td>
        </tr>
        <tr>
            <td>City:</td>
            <td><c:out value="${sessionScope.reviewOrder.shipCity}" /></td>
        </tr>
        <tr>
            <td>State:</td>
            <td><c:out value="${sessionScope.reviewOrder.shipState}" /></td>
        </tr>
        <tr>
            <td>Zip:</td>
            <td><c:out value="${sessionScope.reviewOrder.shipZip}" /></td>
        </tr>
        <tr>
            <td>Country:</td>
            <td><c:out value="${sessionScope.reviewOrder.shipCountry}" /></td>
        </tr>
        <tr>
            <td>Courier:</td>
            <td><c:out value="${sessionScope.reviewOrder.courier}" /></td>
        </tr>
        <tr>
            <td colspan="2">Status: <c:out value="${sessionScope.reviewOrder.status}" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <table class="table" style="background-color: rgb(249,250,252);">
                    <tr class="active">
                        <th>Item ID</th>
                        <th>Description</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Total Cost</th>
                    </tr>
                    <c:forEach var="lineItem" items="${sessionScope.reviewOrder.lineItems}">
                        <tr>
                            <td>
                                <a href="viewItem?itemId=${lineItem.item.itemId}">
                                        ${lineItem.item.itemId}
                                </a>
                            </td>
                            <td>
                                <c:if test="${lineItem.item != null}">
                                    ${lineItem.item.attribute1}
                                    ${lineItem.item.attribute2}
                                    ${lineItem.item.attribute3}
                                    ${lineItem.item.attribute4}
                                    ${lineItem.item.attribute5}
                                    ${lineItem.item.product.name}
                                </c:if>
                                <c:if test="${lineItem.item == null}">
                                    <i>
                                        {description unavailable}
                                    </i>
                                </c:if>
                            </td>
                            <td>
                                    ${lineItem.quantity}
                            </td>
                            <td>
                                <fmt:formatNumber value="${lineItem.unitPrice}" pattern="$#,##0.00" />
                            </td>
                            <td>
                                <fmt:formatNumber value="${lineItem.total}" pattern="$#,##0.00" />
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <th colspan="5">
                            Total:
                            <fmt:formatNumber value="${sessionScope.reviewOrder.totalPrice}" pattern="$#,##0.00" />
                        </th>
                    </tr>
                </table>
            </td>
        </tr>

    </table>

    </div>

</div>

</div>

<script>
    $(".containerFont > h1").text("REVIEW CONFIRMED ORDER");
</script>

<%@ include file="../common/IncludeBottom.jsp"%>
