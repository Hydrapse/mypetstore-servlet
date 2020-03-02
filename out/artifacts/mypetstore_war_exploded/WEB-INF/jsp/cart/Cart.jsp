<%@ include file="../common/IncludeTop.jsp" %>

<div id="Content">

<div id="BackLink">
    <a href="main">Return to Main Menu</a>
</div>

<div id="Catalog">

    <div id="Cart">

        <h2>Shopping Cart</h2>

        <from action="#" method="post">
            <table class="table table-hover" style="margin-bottom: 3px; background-color: rgb(248,250,252);">
                <tr>
                    <th><b>Item ID</b></th>
                    <th><b>Product ID</b></th>
                    <th><b>Description</b></th>
                    <th><b>In Stock?</b></th>
                    <th><b>Quantity</b></th>
                    <th><b>List Price</b></th>
                    <th><b>Total Cost</b></th>
                    <th>&nbsp;</th>
                </tr>

                <c:if test="${sessionScope.cart.numberOfItems == 0}">
                    <tr>
                        <td colspan="8"><b>Your cart is empty.</b></td>
                    </tr>
                </c:if>

                <c:forEach var="cartItem" items="${sessionScope.cart.cartItemList}">
                    <tr>
                        <td>
                            <a href="viewItem?itemId=${cartItem.item.itemId}">${cartItem.item.itemId}</a>
                        </td>
                        <td>${cartItem.item.product.productId}</td>
                        <td>
                                ${cartItem.item.attribute1} ${cartItem.item.attribute2}
                                ${cartItem.item.attribute3} ${cartItem.item.attribute4}
                                ${cartItem.item.attribute5} ${cartItem.item.product.name}
                        </td>
                        <td>${cartItem.inStock}</td>
                        <td>
                            <input style="width: 62px;" type="number" id="${cartItem.item.itemId}" name="${cartItem.item.itemId}"
                                   value="${cartItem.quantity}" min="1" max="${cartItem.item.quantity}"
                                   onblur="updateCart(this.id, this.value, ${cartItem.quantity}, ${cartItem.item.quantity})"/>
                        </td>
                        <td>
                            <fmt:formatNumber value="${cartItem.item.listPrice}" pattern="$#,##0.00"/>
                        </td>
                        <td id="${cartItem.item.itemId}Total">
                            <fmt:formatNumber value="${cartItem.total}" pattern="$#,##0.00"/>
                        </td>
                        <td>
<%--                            removeItemFromCart?workingItemId=${cartItem.item.itemId}--%>
                            <a class="Button btn-cartItem-remove" style="cursor: pointer;">Remove</a>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="7">
                        Sub Total:
                        <span id="subTotal"><fmt:formatNumber value="${sessionScope.cart.subTotal}"
                                                              pattern="$#,##0.00"/></span>
                    </td>
                    <td>&nbsp;</td>
                </tr>
            </table>
        </from>

        <c:if test="${sessionScope.cart.numberOfItems > 0}">
            <c:if test="${sessionScope.order == null}">
                <a onclick="defaultCheckout()" class="Button" href="#" style="padding: 7px;">Checkout</a>&nbsp;
            </c:if>
            <c:if test="${sessionScope.order != null}">
                <a onclick="continueCheckout()" class="Button" href="#" style="padding: 7px;">Checkout</a>&nbsp;
            </c:if>

            <a id="clearAll" style="cursor: pointer; color: #d9534f;"><i class="fa fa-trash fa-lg"> </i></a>
        </c:if>

        <div class="input-group" style="margin: -10px 20px 0 auto; max-width: 50%;">
            <input type="text" class="form-control" data-provide="typeahead" name="new-item-keyword"
                   id="new-cartItem-box" size="14" autocomplete="off" placeholder="Add New Items To Cart">
            <span class="input-group-btn">
                <button class="btn btn-default" type="button">Add Item</button>
            </span>
        </div>
    </div>

    <%--<div id="MyList">--%>
    <%--  <c:if test="${sessionScope.accountBean != null}">--%>
    <%--	<c:if test="${!sessionScope.accountBean.authenticated}">--%>
    <%--	  <c:if test="${!empty sessionScope.accountBean.account.listOption}">--%>
    <%--	    <%@ include file="IncludeMyList.jsp"%>--%>
    <%--      </c:if>--%>
    <%--	</c:if>--%>
    <%--  </c:if>--%>
    <%--</div>--%>

</div>

</div>

<script type="text/javascript" src="js/cart.js"></script>

<%@ include file="../common/IncludeBottom.jsp" %>