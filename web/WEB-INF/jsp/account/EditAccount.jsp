<%@ include file="../common/IncludeTop.jsp" %>

<form id="account-info-form" action="confirmEdit" method="post">

<div class="container marketing edit-account">

<%--    <!-- Three columns of text below the carousel -->--%>
<%--    <div class="row">--%>
<%--        <div class="col-lg-4">--%>
<%--            <img class="img-circle" src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" alt="Generic placeholder image" width="140" height="140">--%>
<%--            <h2>Heading</h2>--%>
<%--            <p>Donec sed odio dui. Etiam porta sem malesuada magna mollis euismod. Nullam id dolor id nibh ultricies vehicula ut id elit. Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Praesent commodo cursus magna.</p>--%>
<%--            <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>--%>
<%--        </div><!-- /.col-lg-4 -->--%>
<%--        <div class="col-lg-4">--%>
<%--            <img class="img-circle" src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" alt="Generic placeholder image" width="140" height="140">--%>
<%--            <h2>Heading</h2>--%>
<%--            <p>Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit. Cras mattis consectetur purus sit amet fermentum. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh.</p>--%>
<%--            <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>--%>
<%--        </div><!-- /.col-lg-4 -->--%>
<%--        <div class="col-lg-4">--%>
<%--            <img class="img-circle" src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" alt="Generic placeholder image" width="140" height="140">--%>
<%--            <h2>Heading</h2>--%>
<%--            <p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula porta felis euismod semper. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>--%>
<%--            <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>--%>
<%--        </div><!-- /.col-lg-4 -->--%>
<%--    </div><!-- /.row -->--%>


<%--    <!-- START THE FEATURETTES -->--%>

<%--    <hr class="featurette-divider">--%>

    <div class="row featurette ed-row-1">
        <div class="col-md-7">
            <h1 class="featurette-heading">User <span class="text-muted">Information </span></h1>
            <table class="table ed-table">
                <tr>
                    <td>User ID:</td>
                    <td>${sessionScope.account.username}</td>
                </tr>
                <tr>
                    <td>New password:</td>
                    <td><input type="password" name="password"/></td>
                </tr>
                <tr>
                    <td>Repeat password:</td>
                    <td><input type="password" name="repeatedPassword"/></td>
                </tr>
                <tr><td></td></tr>
                <tr>
                    <td colspan="2">
                        <button class="btn btn-default submit-edit-account" type="button"
                                data-toggle="modal" data-target="#myVerificationModal">
                            Save
                        </button>&nbsp;
                    </td>
                </tr>
            </table>
        </div>
        <div class="col-md-5">
            <img class=" img-rounded img-responsive center-block ed-img" style="filter: grayscale(30%);" src="images/sitting.jpg" alt="Generic placeholder image">
        </div>
    </div>

    <hr class="featurette-divider">

    <div class="row featurette ed-row-2">
        <div class="col-md-5 col-md-push-7" >
            <h2 class="featurette-heading" style="margin-top: 0; text-align: right;" >Account <span class="text-muted">Information</span></h2>

            <table class="table ed-table" style="margin-left: auto;">
                <tr>
                    <td>First name:</td>
                    <%--名--%>
                    <td><input type="text" name="firstName" value="${sessionScope.account.firstName}"></td>
                </tr>
                <tr>
                    <td>Last name:</td>
                    <%--姓--%>
                    <td><input type="text" name="lastName" value="${sessionScope.account.lastName}"></td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td><input type="email" name="email" value="${sessionScope.account.email}"></td>
                </tr>
                <tr>
                    <td>Phone:</td>
                    <td><input type="text" name="phone" value="${sessionScope.account.phone}"></td>
                </tr>
                <tr>
                    <td>Address 1:</td>
                    <td><input type="text" name="address1" value="${sessionScope.account.address1}"></td>
                </tr>
                <tr>
                    <td>Address 2:</td>
                    <td><input type="text" name="address2" value="${sessionScope.account.address2}"></td>
                </tr>
                <tr>
                    <td>City:</td>
                    <td><input type="text" name="city" value="${sessionScope.account.city}"></td>
                </tr>
                <tr>
                    <td>State:</td>
                    <td><input type="text" name="state" value="${sessionScope.account.state}"></td>
                </tr>
                <tr>
                    <td>Zip:</td>
                    <td><input type="text" name="zip" value="${sessionScope.account.zip}"></td>
                </tr>
                <tr>
                    <td>Country:</td>
                    <td><input type="text" name="country" value="${sessionScope.account.country}"></td>
                </tr>
            </table>

        </div>
        <div class="col-md-7 col-md-pull-5" >
            <video class="img-rounded" id="videoAccount" width="90%" style="margin-top: 30px; filter: grayscale(20%);" loop muted>
                <source src="images/muti1.mp4" type="video/mp4">
            </video>
            <script></script>
        </div>
    </div>

    <hr class="featurette-divider ">

    <div class="row featurette ed-row-3">
        <div class="col-md-7">
            <h2 class="featurette-heading" style="margin-top: 20px;"> Profile <span class="text-muted">Information</span></h2>

            <table class="table ed-table">
                <tr>
                    <td>Language Preference:</td>
                    <td>
                        <select name="languages">
                            <option value="Chinese"
                                    <c:if test="${sessionScope.account.languagePreference == 'Chinese'}">selected</c:if> >Chinese
                            </option>
                            <option value="English"
                                    <c:if test="${sessionScope.account.languagePreference == 'English'}">selected</c:if> >English
                            </option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Favourite Category:</td>
                    <td>
                        <select name="categories" id="favCategory">
                            <option value="BIRDS"
                                    <c:if test="${sessionScope.account.favouriteCategoryId == 'BIRDS'}">selected</c:if> >BIRDS
                            </option>
                            <option value="CATS"
                                    <c:if test="${sessionScope.account.favouriteCategoryId == 'CATS'}">selected</c:if> >CATS
                            </option>
                            <option value="DOGS"
                                    <c:if test="${sessionScope.account.favouriteCategoryId == 'DOGS'}">selected</c:if> >DOGS
                            </option>
                            <option value="FISH"
                                    <c:if test="${sessionScope.account.favouriteCategoryId == 'FISH'}">selected</c:if> >FISH
                            </option>
                            <option value="REPTILES"
                                    <c:if test="${sessionScope.account.favouriteCategoryId == 'REPTILES'}">selected</c:if> >REPTILES
                            </option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="isMyListEnabled">Enable MyList</label></td>
                    <td>
                        <input type="checkbox" name="isMyListEnabled" id="isMyListEnabled"
                               <c:if test="${sessionScope.account.listOption}">checked</c:if>>
                    </td>
                </tr>
                <tr>
                    <td><label for="isMyBannerEnabled">Enable MyBanner</label></td>
                    <td>
                        <input type="checkbox" name="isMyBannerEnabled" id="isMyBannerEnabled"
                               <c:if test="${sessionScope.account.bannerOption}">checked</c:if>>
                    </td>
                </tr>
                <tr><td></td></tr>
                <tr>
                    <td>
                        <button  class="btn btn-default submit-edit-account" type="button"
                                data-toggle="modal" data-target="#myVerificationModal">
                            Confirm
                        </button>&nbsp;
<%--                        <button type="submit" class="btn btn-default">Confirm</button>&nbsp;--%>
                        <a class="btn btn-primary" href="checkOrderList" role="button">View Order</a>
                    </td>
                </tr>
            </table>

        </div>
        <div class="col-md-5">
            <img id="profileGif" class=" img-rounded img-responsive center-block ed-img" style="filter: grayscale(20%);" src="images/birds_walk.gif" alt="FavCategory">
        </div>
    </div>

    <hr class="featurette-divider" style="margin-bottom: 20px;">

</div>

</form>


<%--<div id="Content">--%>
<%--    <div id="Catalog">--%>
<%--        <form id="account-info-form" action="confirmEdit" method="post">--%>

<%--            <h3>User Information</h3>--%>

<%--            <table>--%>
<%--                <tr>--%>
<%--                    <td>User ID:</td>--%>
<%--                    <td>${sessionScope.account.username}</td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <td>New password:</td>--%>
<%--                    <td><input type="password" name="password"/></td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <td>Repeat password:</td>--%>
<%--                    <td><input type="password" name="repeatedPassword"/></td>--%>
<%--                </tr>--%>
<%--            </table>--%>

<%--            <%@ include file="IncludeAccountFields.jsp" %>--%>

<%--&lt;%&ndash;            <input style="margin-top: 5px; margin-bottom: 5px;" type="submit" name="editAccount"&ndash;%&gt;--%>
<%--&lt;%&ndash;                   value="Save Account Information"/>&ndash;%&gt;--%>
<%--            <button id="submit-edit-account" class="btn btn-primary" type="button"--%>
<%--                    data-toggle="modal" data-target="#myVerificationModal">--%>
<%--                Save Account Information--%>
<%--            </button>--%>

<%--        </form>--%>

<%--        <a href="checkOrderList">My Orders</a>--%>

<%--    </div>--%>
<%--</div>--%>

<!-- Verify Modal -->
<div class="modal fade" id="myVerificationModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div id="verify-content" class="modal-content center-block">

            <%--验证码区域--%>
            <div id="slideBar" class="verify-body"></div>

            <div class="verify-footer">
                <a id="close-verify-btn" data-dismiss="modal"><span class="glyphicon glyphicon-remove-circle" style="font-size: 21px;padding-right: 5px;"></span></a>
                <a id="refresh-verify-btn"><span class="glyphicon glyphicon-repeat" style="font-size: 19px;"></span></a>
            </div>

        </div>
    </div>
</div>

<script type="text/javascript" src="js/editAccount.js"></script>

<%@ include file="../common/IncludeBottom.jsp" %>
