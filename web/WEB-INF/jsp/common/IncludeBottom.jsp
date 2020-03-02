<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="navBottom" class="footer bottom">
    <div class="container-fluid">
        <div id="footerRow" class="row">
            <div class="col-sm-5">
                <p>SUBSCRIBE TO OUR<br><br></p>
                <h2>emailAddress</h2>
                <form>
                    <div class="input-group" style="max-width: 400px; opacity:0.7;">
                     <input type="email" id="inputEmail" class="form-control" placeholder="Email address" autocomplete="off" required>
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="submit"><i class="fa fa-send"></i></button>
                        </span>
                    </div>
                </form>
                <p style="padding-top: 10px;">This field must be a valid e-mail!</p>
            </div>
            <div class="col-sm-3">
                <p>SHOP<br></p>
                <a href="help.html"><h5 class="lightBlue">FAQ</h5></a>
                <a href="help.html#Catalog"><h5 class="lightBlue">Product Catalog</h5></a>
                <a href="help.html#ShoppingCart"><h5 class="lightBlue">Shopping Cart</h5></a>
                <a href="help.html#OrderReview"><h5 class="lightBlue">Review Order</h5></a>
            </div>
            <div class="col-sm-2">
                <p>GET INSPIRED<br></p>
                <a href="viewCategory?categoryId=BIRDS#navigation"><h5 class="lightBlue">Bird</h5></a>
                <a href="viewCategory?categoryId=CATS#navigation"><h5 class="lightBlue">Cat</h5></a>
                <a href="viewCategory?categoryId=DOGS#navigation"><h5 class="lightBlue">Dog</h5></a>
                <a href="viewCategory?categoryId=FISH#navigation"><h5 class="lightBlue">Fish</h5></a>
            </div>
            <div class="col-sm-2">
                <p>MY PROFILE<br></p>
                <c:if test="${sessionScope.account == null}">
                    <a href="viewLoginForm"><h5 class="lightBlue">Login</h5></a>
                </c:if>
                <c:if test="${sessionScope.account != null}">
                <a href="viewAccountForm?#navigation"> <h5 class="lightBlue">Account</h5></a>
                <a href="#" onclick="signOut()"><h5 class="lightBlue">Sign out</h5></a>
                </c:if>

            </div>
        </div>
        <br><br><br>
        <div class="row">
            <div class="col-sm-3 col-sm-offset-9">© Retail 710 - MA TRUE CANNABIS</div>
        </div>

    </div>
</div>

<%--侧边广告： MyBanner--%>
<c:if test="${sessionScope.account == null}">
    <a id="MyBanner" href="viewCategory?categoryId=BIRDS#navigation"></a>
</c:if>
<c:if test="${sessionScope.account != null}">
    <c:if test="${sessionScope.account.bannerOption}">
        <a id="MyBanner" style="background-image: url('${sessionScope.account.bannerName}')"
           href="viewCategory?categoryId=${sessionScope.account.favouriteCategoryId}#navigation"></a>
    </c:if>
    <c:if test="${!sessionScope.account.bannerOption}">
        <div id="MyBanner" style="display: none;"></div>
    </c:if>
</c:if>


<!----------------------------------------------HYDRA SCRIPT----------------------------------------------->

<script type="text/javascript" src="js/commonBottom.js"></script>

<!----------------------------------------------HYDRA SCRIPT----------------------------------------------->
</body>

</html>
