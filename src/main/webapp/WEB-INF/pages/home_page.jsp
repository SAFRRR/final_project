<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cake House</title>
</head>

<body>
<jsp:include page="common/shared/header.jsp"/>
<div class="container">
<div class="safr">
        <div id="carouselExampleControls" class="carousel slide"  data-bs-ride="carousel">
        <div class="carousel-inner">

            <div class="carousel-item active">
                <img src="static/images/bakery1.jpg" class="d-block w-100" style="height: 400px;" alt="...">
            </div>
            <div class="carousel-item">
                <img src="static/images/bread1.jpg" class="d-block w-100" alt="...">
            </div>
            <div class="carousel-item">
                <img src="static/images/candy1.jpg" class="d-block w-100" alt="...">
            </div>
            <div class="carousel-item">
                <img src="static/images/cheesecake1.jpg" class="d-block w-100" alt="...">
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>

</div>


<%--    <div class="image-block">--%>
<%--        <div class="first-line">--%>
<%--            <img class="img-home" src="static/images/bakery.jpg" >--%>
<%--            <img class="img-responsive" src="static/images/bread.jpg" >--%>
<%--        </div>--%>
<%--        <div class="second_line">--%>
<%--            <img class="img-responsive" src="static/images/candy1.jpg" >--%>
<%--            <img class="img-responsive" src="static/images/cheesecake.jpg" >--%>
<%--        </div>--%>
<%--    </div>--%>


    <jsp:include page="common/shared/footer.jsp"/>
</div>
</body>
</html>
