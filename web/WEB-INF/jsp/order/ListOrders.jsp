<%@ include file="../common/IncludeTop.jsp"%>
<div id="Content" style="padding-bottom: 100px;">

<div id="MyOrder">

<h2>My Orders</h2>

<table class="table table-bordered">
    <tr>
        <th>Order ID</th>
        <th>Date</th>
        <th>Total Price</th>
    </tr>
    <c:if test="${sessionScope.orderList == null}">
        <tr>
            <th colspan="3" style="font-weight: bold"><a href="main">Do Not Have Any Order, Let's Buy Some Lovely Pets!</a></th>
        </tr>
    </c:if>
    <c:forEach var="order" items="${sessionScope.orderList}">
        <tr>
            <td>
                <a href="reviewOrder?orderId=${order.orderId}">${order.orderId}</a>
            </td>
            <td>${order.orderDateString}</td>
            <td>
                <fmt:formatNumber value="${order.totalPrice}" pattern="$#,##0.00" />
            </td>
        </tr>
    </c:forEach>
</table>

</div>

</div>

<script>
    $(".containerFont > h1").text("MY ORDER LIST");
</script>

<%@ include file="../common/IncludeBottom.jsp"%>
