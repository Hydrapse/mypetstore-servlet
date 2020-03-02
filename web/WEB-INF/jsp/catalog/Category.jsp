<%@ include file="../common/IncludeTop.jsp" %>

<div id="Content">

<div id="BackLink">
    <a href="main">
        Return to Main Menu
    </a>
</div>

<div id="Catalog">

    <div id="Category">

    <h2>${sessionScope.category.name}</h2>

    <table class="table table-hover">
        <tr>
            <th>Product ID</th>
            <th>Name</th>
        </tr>
        <c:forEach var="product" items="${sessionScope.productList}">
            <tr>
                <td>
                    <a href="viewProduct?productId=${product.productId}#navigation">${product.productId}</a>
                </td>
                <td>${product.name}</td>
            </tr>
        </c:forEach>

    </table>
    <br>

    </div>

</div>

</div>

<%@ include file="../common/IncludeBottom.jsp" %>


