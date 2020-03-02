<%@include file="../common/IncludeTop.jsp"%>

<div id="Content" style="padding-bottom: 100px;">
    <div id="Order1">

        <form action="confirmAccountInfo" method="post" >

            <table class="table table-striped">
                <tr>
                    <th colspan=2>Payment Details</th>
                </tr>
                <tr>
                    <td>Card Type:</td>
                    <td>
                        <select name="cardType">
                            <option value="Visa">Visa</option>
                            <option value="MasterCard">MasterCard</option>
                            <option value="American Express">American Express</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Card Number:</td>
                    <td>
                        <input type="text" name="creditCard" value="${sessionScope.order.creditCard}">
                        * Use a fake number!
                    </td>
                </tr>
                <tr>
                    <td>Expiry Date (MM/YYYY):</td>
                    <td>
                        <input type="text" name="expiryDate" value="${sessionScope.order.expiryDate}">
                    </td>
                </tr>
                <tr>
                    <th colspan=2>Billing Address</th>
                </tr>

                <tr>
                    <td>First name:</td>
                    <td>
                        <input type="text" name="firstName" value="${sessionScope.order.billToFirstName}">
                    </td>
                </tr>
                <tr>
                    <td>Last name:</td>
                    <td>
                        <input type="text" name="lastName" value="${sessionScope.order.billToLastName}">
                    </td>
                </tr>
                <tr>
                    <td>Address 1:</td>
                    <td>
                        <input type="text" name="address1" value="${sessionScope.order.billAddress1}">
                    </td>
                </tr>
                <tr>
                    <td>Address 2:</td>
                    <td>
                        <input type="text" name="address2" value="${sessionScope.order.billAddress2}">
                    </td>
                </tr>
                <tr>
                    <td>City:</td>
                    <td>
                        <input type="text" name="city" value="${sessionScope.order.billCity}">
                    </td>
                </tr>
                <tr>
                    <td>State:</td>
                    <td>
                        <input type="text" name="state" value="${sessionScope.order.billState}">
                    </td>
                </tr>
                <tr>
                    <td>Zip:</td>
                    <td>
                        <input type="text" name="zip" value="${sessionScope.order.billZip}">
                    </td>
                </tr>
                <tr>
                    <td>Country:</td>
                    <td>
                        <input type="text" name="country" value="${sessionScope.order.billCountry}">
                    </td>
                </tr>

                <tr>
                    <td colspan=2>
                        <input id="shippingAddressRequired" type="checkbox" name="shippingAddressRequired" value="shippingAddressRequired">
                        <label for="shippingAddressRequired">Ship to different address...</label>
                    </td>
                </tr>

            </table>

            <input type="submit" value="Continue">

        </form>
    </div>
</div>

<script>
    $(".containerFont > h1").text("VIEW ORDER FORM");
</script>

<%@include file="../common/IncludeBottom.jsp"%>
