<%@include file="../common/IncludeTop.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="Welcome" class="jumbotron">
    <div class="container">
        <h1>Wanted！</h1>
        <p>They don't understand happiness, but they can read the notes that you laugh. They don't understand sadness, but they can listen to your inner pain. They use the silent guardian to bring you the most beautiful happiness.
            Take care of them, although they are only our pets, but  also need our love.</p>
        <p><a class="btn btn-primary btn-lg" href="main" role="button">Learn more »</a></p>
    </div>
</div>

<div id="main-content" class="container-fluid">
    <div class="row">
        <div id="main-catalog" class="col-md-5">
            <div class="list-group">
                <a href="#main-content" class="list-group-item disabled">
                    <b>Catalog</b>
                </a>
                <a href="viewCategory?categoryId=FISH#navigation" class="list-group-item">
                    <img src="images/fish_icon.gif"/> <span class="badge">4</span>
                </a>
                <a href="viewCategory?categoryId=DOGS#navigation" class="list-group-item">
                    <img src="images/dogs_icon.gif"/> <span class="badge">6</span>
                </a>
                <a href="viewCategory?categoryId=CATS#navigation" class="list-group-item">
                    <img src="images/cats_icon.gif"/> <span class="badge">2</span>
                </a>
                <a href="viewCategory?categoryId=REPTILES#navigation" class="list-group-item">
                    <img src="images/reptiles_icon.gif"/> <span class="badge">2</span>
                </a>
                <a href="viewCategory?categoryId=BIRDS#navigation" class="list-group-item">
                    <img src="images/birds_icon.gif"/> <span class="badge">2</span>
                </a>
                <a class="list-group-item list-group-item-warning" style="size: 14px" data-toggle="popover" data-placement="right" data-trigger="hover">Various Cute Pets!</a>
            </div>

            <div class="panel-group" id="accordion">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseOne" data-toggle="tooltip" data-placement="right" data-trigger="hover">
                                Where are we From？
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse in">
                        <div class="panel-body">
                            New 'chip' Service! You can find where we from by a little
                            chip which has been insert in my body.
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseTwo">
                                Source control
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse">
                        <div class="panel-body">
                            Strictly select CAAC certified breeding houses.
                            Scientific reproduction and strict control of baby quality
                        </div>
                    </div>
                </div>

            </div>

        </div>

        <div id="main-image-box" class="col-md-7 <%--jumbotron--%>">

<%--                <map id="MainMap" name="estoremap">--%>
<%--                    <area id="img-bigBird" alt="Birds" coords="72,2,280,250" href="viewCategory?categoryId=BIRDS#navigation" shape="rect" />--%>
<%--                    <area id="img-fish" alt="Fish" coords="2,180,72,250" href="viewCategory?categoryId=FISH#navigation" shape="rect"/>--%>
<%--                    <area id="img-dog" alt="Dogs" coords="60,250,130,320" href="viewCategory?categoryId=DOGS#navigation" shape="rect"/>--%>
<%--                    <area id="img-reptile" alt="Reptiles" coords="140,270,210,340" href="viewCategory?categoryId=REPTILES#navigation" shape="rect"/>--%>
<%--                    <area id="img-cat" alt="Cats" coords="225,240,295,310" href="viewCategory?categoryId=CATS#navigation" shape="rect"/>--%>
<%--                    <area id="img-bird" alt="Birds" coords="280,180,350,250" href="viewCategory?categoryId=BIRDS#navigation" shape="rect"/>--%>
<%--                </map>--%>
<%--                <img id="MainImage" class="img-responsive &lt;%&ndash;img-thumbnail&ndash;%&gt;" src="images/splash.gif"  usemap="#estoremap" />--%>

            <map id="MainMap" name="estoremap">
                <area id="img-bigBird" alt="Birds" coords="179,154,100" href="viewCategory?categoryId=BIRDS#navigation" shape="circle" />
                <area id="img-fish" alt="Fish" coords="40,214,36" href="viewCategory?categoryId=FISH#navigation" shape="circle"/>
                <area id="img-dog" alt="Dogs" coords="98,282,36" href="viewCategory?categoryId=DOGS#navigation" shape="circle"/>
                <area id="img-reptile" alt="Reptiles" coords="179,305,36" href="viewCategory?categoryId=REPTILES#navigation" shape="circle"/>
                <area id="img-cat" alt="Cats" coords="267,282,36" href="viewCategory?categoryId=CATS#navigation" shape="circle"/>
                <area id="img-bird" alt="Birds" coords="320,213,36" href="viewCategory?categoryId=BIRDS#navigation" shape="circle"/>
            </map>
            <img id="MainImage" class="img-responsive" src="images/splash.gif"  usemap="#estoremap" />

        </div>
    </div><%--row1--%>

    <hr class="featurette-divider" style="margin: 100px 0; ">

    <div class="row" style="padding:  30px 100px;">
        <div class="col-md-5"  style="padding: 20px 0;">
            <a href="viewProduct?productId=FL-DSH-01">
                <img class="img-circle showcase" src="images/kitten2.jpg" alt="Ragdoll kitten" width="200" height="200">
            </a>
        </div>
        <div class="col-md-7 text-center" style="background: rgb(248,250,252); padding: 20px 40px;">
            <h2>Lovely Ragdoll kitten</h2>
            <p style="font-family: 'Apple Braille'; font-weight: 600; font-size: 16px; text-align: left; padding: 0 10px;"><br>
                Ragdoll cats are strict indoor cats. Don't let them go out and raise them outdoors.
                The puppet cat is docile, but it is also a cat. The cat is very tsundere(傲娇),
                and will run away from home when it is angry.
            </p>
            <p><a class="btn btn-default" href="viewProduct?productId=FL-DSH-01" role="button">View details »</a></p>
        </div>
    </div><%--row2--%>
</div>


<script type="text/javascript" src="js/main.js"></script>

<%@include file="../common/IncludeBottom.jsp" %>