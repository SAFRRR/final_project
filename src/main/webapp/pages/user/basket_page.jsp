<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>


<fmt:message key="user.continue.shopping" var="locale_continue_shopping"/>
<fmt:message key="user.order.check" var="locale_order_check"/>
<fmt:message key="main.dessert.list" var="locale_dessert_list"/>
<fmt:message key="admin.price.name" var="locale_admin_price_name"/>
<fmt:message key="user.order.count" var="locale_order_count"/>
<fmt:message key="user.storage.dessert" var="locale_storage_dessert"/>
<fmt:message key="user.storage.only" var="locale_storage_only"/>
<fmt:message key="user.dessert.unavailable" var="locale_dessert_unavailable"/>
<fmt:message key="admin.operation.delete" var="locale_operation_delete"/>
<fmt:message key="common.update" var="locale_common_update"/>
<fmt:message key="user.total.price" var="locale_total_price"/>

<!doctype html>
<html>
<body>

<jsp:include page="../common/header.jsp"/>
<div class="container">
<%--    <a href="${pageContext.request.contextPath}/controller?command=go_to_item_page_command">${locale_continue_shopping}</a>--%>

    <form action="controller" method="post">
        <input type="hidden" name="command" value="go_to_check_out_page_command">
        <input type="hidden" name="id" value="${basket.id}">
        <button class="btn btn-default" type="submit">${locale_order_check}</button>
    </form>

    <div class="container2" style=" display: flex; flex-direction: row; flex-wrap: wrap;  align-items: center; justify-content: center;">

    <c:forEach items="${basketDessertList}" var="basketDessert">
        <div class="my">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="remove_dessert_command"/>
            <input type="hidden" name="basketDessertId" value="${basketDessert.id}"/>
            <button type="submit" style="margin-left: 243px"  class="btn btn-outline-danger"><i class="fas fa-times"></i></button>
        </form>
        <form class="form-horizontal" style="width: 280px; height:500px; margin-top: 5px; margin-left: 0;margin-right: 20px; " >
<%--            <input hidden="hidden" name="id" value="${basketDessert.id}"/>--%>
            <img style="width: 200px" class="img-responsive dessert" src="./static/images/${basketDessert.dessert.dessertImage}">
            <h4 style="text-align: center; margin-top: 15px">${basketDessert.dessert.name}</h4>
            <p style="text-align: center;margin-top: 10px">${basketDessert.dessert.price} BYN</p>
            <c:if test="${basketDessert.dessert.storage.count < 11 && basketDessert.dessert.storage.count > 0}">
            <p style="color: red; text-align: center">
                <span>${locale_storage_only}  ${basketDessert.dessert.storage.count} ${locale_storage_dessert}</span>
            </p>
            </c:if>
            <input id="count" style="width: 70px; margin-left: 50px;" name="count" type="number" min="1" max="${basketDessert.dessert.storage.count}"
           <c:if test="${basketDessert.dessert.storage.count==0}">disabled</c:if>
           class="form-control basketDessertCount" value="${basketDessert.count}"/>

            <form action="controller" method="post">
                <input type="hidden" name="command" value="update_basket_command"/>
                <input hidden="hidden" name="id" value="${basketDessert.id}"/>
                <button type="submit" class="btn btn-warning btn-xs">${locale_common_update}</button>
            </form>
        </form>
        </div>

    </c:forEach>


                <%--                <c:if test="${notEnoughStorage}">--%>
                <%--                    <div class="alert alert-warning">--%>
                <%--                        Oooops, some of the products don't have enough count in storage. Please update product quantity--%>
                <%--                    </div>--%>
                <%--                </c:if>--%>
                <%--                --%>
                <%--                <c:if test="${emptyBasket}">--%>
                <%--                    <div class="alert alert-warning">--%>
                <%--                        Oooops, your basket is empty. See if you can find what you like in the Dessert List--%>
                <%--                    </div>--%>
                <%--                </c:if>--%>
                <%--                --%>
                <%--                <c:if test="${removeItem}">--%>
                <%--                    <div class="alert alert-success">--%>
                <%--                        Item is successfully removed--%>
                <%--                    </div>--%>
                <%--                </c:if>--%>



                <%--                <div class="row">--%>
                <%--                    <div class="col-xs-8"><h4 class="center-block">${locale_dessert_list}</h4></div>--%>
                <%--                    <div class="col-xs-2"><h4>${locale_admin_price_name}</h4></div>--%>
                <%--                    <div class="col-xs-2"><h4>${locale_order_count}</h4></div>--%>
                <%--                </div>--%>

<%--                <c:forEach items="${basketDessertList}" var="basketDessert">--%>

<%--                    <form class="form-horizontal" style="width: 400px; height:600px; margin-top: 50px; margin-left: 0;margin-right: 20px; " >--%>
<%--                        <input type="hidden" value="${dessert.id}" name="dessertId">--%>

<%--                        <input hidden="hidden" name="id" value="${basketDessert.id}"/>--%>
<%--                        <img style="width: 200px" class="img-responsive dessert" src="./static/images/${basketDessert.dessert.dessertImage}">--%>

<%--                        <a href="${pageContext.request.contextPath}/controller?command=go_to_dessert_detail_page_command&dessertId=${dessert.id}">--%>
<%--                            <img class="img-responsive" src="./static/images/${dessert.dessertImage}" style="height: 313px;" alt=""/>--%>
<%--                        </a>--%>

<%--                        <h4 style="text-align: center; margin-top: 15px">${basketDessert.dessert.name}</h4>--%>
<%--                        <p style="text-align: center;margin-top: 10px">${basketDessert.dessert.price} BYN</p>--%>
                            <%--            <p style="text-align: center;">${dessert.weight} gm</p>--%>

<%--                        <c:if test="${storage.count < 11 && storage.count > 0}">--%>
<%--                            <p style="color: red">--%>
<%--                                <span>${locale_storage_only}  ${storage.count} ${locale_storage_dessert}</span>--%>
<%--                             </p>--%>
<%--                        </c:if>--%>





<%--                    <div class="col-xs-2">--%>
<%--                        <form action="controller" method="post">--%>
<%--                            <input type="hidden" name="command" value="update_basket_command"/>--%>

<%--                            <input hidden="hidden" name="id" value="${basketDessert.id}"/>--%>

<%--                            <input id="count" style="width: 100px" name="count" type="number" min="1" max="${basketDessert.dessert.storage.count}"--%>
<%--                                   <c:if test="${basketDessert.dessert.storage.count==0}">disabled</c:if>--%>
<%--                                   class="form-control basketDessertCount" value="${basketDessert.count}"/>--%>

<%--                            <button style="display: inline-block;" id="update-item-${basketDessert.id}"--%>
<%--                                    type="submit" class="btn btn-warning btn-xs">${locale_common_update}</button>--%>
<%--                        </form>--%>

<%--                        <form action="controller" method="post">--%>
<%--                            <input type="hidden" name="command" value="remove_dessert_command"/>--%>
<%--                            <input type="hidden" name="basketDessertId" value="${basketDessert.id}"/>--%>
<%--                            <button class="btn btn-info" type="submit">${locale_operation_delete}</button>--%>
<%--                        </form>--%>
<%--                    </div>--%>


<%--                    <div class="row">--%>

<%--                        <div class="col-xs-2">--%>
<%--                            <img style="width: 200px" class="img-responsive dessert" src="./static/images/${basketDessert.dessert.dessertImage}">--%>
<%--                        </div>--%>

<%--                        <div class="col-xs-6">--%>
<%--                            <div class="center-block">--%>
<%--                                <h4>${basketDessert.dessert.name}</h4>--%>

<%--                                <h5 style="color: orangered; font-size: large"><span--%>
<%--                                        style="<c:if test="${basketDessert.dessert.storage.count} == 0">text-decoration: line-through</c:if>">${basketDessert.dessert.price} BYN</span>--%>
<%--                                </h5>--%>

                                    <%--                                <c:if test="${basketDessert.dessert.storage.count >= 11}">--%>
                                    <%--                                    <h4 style="color: green">${locale_storage_dessert}</h4>--%>
                                    <%--                                </c:if>--%>

<%--                                <c:if test="${basketDessert.dessert.storage.count < 11 && basketDessert.dessert.storage.count > 0}">--%>
<%--                                    <h4 style="color: green">--%>
<%--                                        <span>${locale_storage_only}  ${basketDessert.dessert.storage.count} ${locale_storage_dessert}</span>--%>
<%--                                    </h4>--%>
<%--                                </c:if>--%>


                                    <%--                                <c:if test="${basketDessert.dessert.storage.count == 0}">--%>
                                    <%--                                    <h4 style="color: darkred">${locale_dessert_unavailable}</h4>--%>
<%--                                    &lt;%&ndash;                                </c:if>&ndash;%&gt;--%>
<%--                            </div>--%>
<%--                        </div>--%>


<%--                            <form action="controller" method="post">--%>
<%--                                <input type="hidden" name="command" value="update_basket_command"/>--%>
<%--                                <input hidden="hidden" name="id" value="${basketDessert.id}"/>--%>
<%--                                <input id="count" style="width: 100px" name="count" type="number" min="1" max="${basketDessert.dessert.storage.count}"--%>
<%--                                       <c:if test="${basketDessert.dessert.storage.count==0}">disabled</c:if>--%>
<%--                                       class="form-control basketDessertCount" value="${basketDessert.count}"/>--%>

<%--                                <button style="display: inline-block;" id="update-item-${basketDessert.id}"--%>
<%--                                        type="submit" class="btn btn-warning btn-xs">${locale_common_update}</button>--%>
<%--                            </form>--%>

<%--                            <form action="controller" method="post">--%>
<%--                                <input type="hidden" name="command" value="remove_dessert_command"/>--%>
<%--                                <input type="hidden" name="basketDessertId" value="${basketDessert.id}"/>--%>
<%--                                <button class="btn btn-info" type="submit">${locale_operation_delete}</button>--%>
<%--                            </form>--%>
<%--                        </form>--%>
<%--                    </c:forEach>--%>

    </div>

                <div class="row">
                    <h4 class="col-xs-12 text-right"><strong style="font-size: large">${locale_total_price}</strong>
                        <span style="color: orangered; font-size: large"><span>${basket.totalCost}</span>BYN</span></h4>
                </div>
</div>
</body>
</html>


<%--<script>--%>
<%--    $(document).ready(function () {--%>
<%--        $(".basketDessertCount").on('change', function () {--%>
<%--            const id = this.id;--%>
<%--            console.log(id);--%>
<%--            $('#update-item-' + id).css('display', 'inline-block');--%>
<%--        });--%>
<%--    });--%>
<%--</script>--%>
