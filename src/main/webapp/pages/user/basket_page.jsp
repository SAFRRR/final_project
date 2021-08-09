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
    <div class="row" style="margin-top: 20px">
        <div class="col-xs-12">
            <div class="row">

                <a href="${pageContext.request.contextPath}/controller?command=go_to_item_page_command">${locale_continue_shopping}</a>

                <span>${basketDessertList.id} bd id</span>
                <span>>${basket.id} b id</span>

                <form action="controller" method="post">
                    <input type="hidden" name="command" value="go_to_check_out_page_command">
                    <input type="hidden" name="id" value="${basket.id}">

                    <button class="btn btn-default" type="submit">${locale_order_check}</button>
                </form>




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

                <c:forEach items="${basketDessertList}" var="basketDessert">
                    <div class="row">

                        <div class="col-xs-2">
                            <img style="width: 100px" class="img-responsive dessert"
                                 src="./static/images${basketDessert.dessert.dessertImage}">
                        </div>

                        <div class="col-xs-6">
                            <div class="center-block">
                                <h4>${basketDessert.dessert.name}</h4>

                                <h5 style="color: orangered; font-size: large">BYN<span
                                        style="<c:if test="${basketDessert.dessert.storage.count} == 0">text-decoration: line-through</c:if>">${basketDessert.dessert.price}</span>
                                </h5>

                                    <%--                                <c:if test="${basketDessert.dessert.storage.count >= 11}">--%>
                                    <%--                                    <h4 style="color: green">${locale_storage_dessert}</h4>--%>
                                    <%--                                </c:if>--%>

                                <c:if test="${basketDessert.dessert.storage.count < 11 && basketDessert.dessert.storage.count > 0}">
                                    <h4 style="color: green">
                                        <span>${locale_storage_only}  ${basketDessert.dessert.storage.count} ${locale_storage_dessert}</span>
                                    </h4>
                                </c:if>


                                    <%--                                <c:if test="${basketDessert.dessert.storage.count == 0}">--%>
                                    <%--                                    <h4 style="color: darkred">${locale_dessert_unavailable}</h4>--%>
                                    <%--                                </c:if>--%>


                                <form action="controller" method="post">
                                    <input type="hidden" name="command" value="remove_dessert_command"/>
                                    <input type="hidden" name="basketDessertId" value="${basketDessert.id}"/>
                                    <button class="btn btn-info" type="submit">${locale_operation_delete}</button>
                                </form>



                            </div>
                        </div>



                        <div class="col-xs-2">
                            <form action="controller" method="post">
                                <input type="hidden" name="command" value="update_basket_command"/>

                                <input hidden="hidden" name="id" value="${basketDessert.id}"/>


                                <input id="count" name="count" type="number" min="1" <c:if test="${basketDessert.dessert.storage.count==0}">disabled</c:if> class="form-control basketDessertCount" value="${basketDessert.count}"/>
                                <button style="display: none;" id="update-item-${basketDessert.id}" type="submit" class="btn btn-warning btn-xs">${locale_common_update}</button>
                            </form>
                        </div>



                    </div>
                </c:forEach>



                <div class="row">
                    <h4 class="col-xs-12 text-right"><strong style="font-size: large">${locale_total_price}</strong>
                        <span style="color: orangered; font-size: large"><span>${basket.totalCost}</span>BYN</span></h4>
                </div>
                <h5>${basketDessert.id}</h5>

            </div>
        </div>
    </div>
</div>
</body>
</html>

<script>
    $(document).ready(function () {
        $(".basketDessertCount").on('change', function () {
            const id = this.id;
            console.log(id);
            $('#update-item-' + id).css('display', 'inline-block');
        });
    });
</script>
