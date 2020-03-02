<h3>Account Information</h3>

<table>
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

<h3>Profile Information</h3>

<table>
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
            <select name="categories">
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
        <td>Enable MyList</td>
        <td>
            <input type="checkbox" name="isMyListEnabled"
                   <c:if test="${sessionScope.account.listOption}">checked</c:if>>
        </td>
    </tr>
    <tr>
        <td>Enable MyBanner</td>
        <td>
            <input type="checkbox" name="isMyBannerEnabled"
                   <c:if test="${sessionScope.account.bannerOption}">checked</c:if>>
        </td>
    </tr>

</table>