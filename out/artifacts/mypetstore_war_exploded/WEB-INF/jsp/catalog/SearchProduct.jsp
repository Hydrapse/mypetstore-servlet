<%@include file="../common/IncludeTop.jsp" %>
<div id="Content">
    <div id="BackLink">
        <a href="main">
            Return to Main Menu
        </a>
    </div>

    <div id="Search">

        <table class="table table-bordered" style="margin: 0;">
            <tr>
                <th></th>
                <th>Product ID</th>
                <th>Name</th>
            </tr>

            <!-- list -->
            <c:forEach var="product" items="${sessionScope.productList}">
                <tr>
                    <td style=" background-color: rgb(245,245,245); font-size: 14px;">
                        <a href="viewProduct?productId=${product.productId}#navigation">${product.description}</a>
                    </td>
                    <td><b>${product.productId}</b></td>
                    <td>${product.name}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<%@include file="../common/IncludeBottom.jsp" %>
