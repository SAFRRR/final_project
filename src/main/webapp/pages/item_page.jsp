<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>

<fmt:message key="common.items" var="locale_common_items"/>
<fmt:message key="common.create.user" var="locale_create_user"/>
<fmt:message key="main.category" var="locale_main_category"/>
<fmt:message key="main.category.enter" var="locale_main_category_enter"/>
<fmt:message key="main.search" var="locale_main_search"/>
<fmt:message key="main.clear" var="locale_main_clear"/>
<fmt:message key="common.dessert.detail" var="locale_dessert_detail"/>


<!doctype html>
<html lang="en">
<body>
<jsp:include page="common/header.jsp"/>




<div class="container">

    <div class="row" style="margin-bottom: -100px">
        <div class="col-xs-8">
            <h2 class="section-headline">
                <span>${locale_common_items}</span>
            </h2>
        </div>
    </div>






    <div class="row" style="margin-top: 60px;">
        <div class="col-xs-10">
            <c:if test="${emptyList}">
                <h5 style="font-style: italic">Ooops, no result is found. Try something else)</h5>
            </c:if>
            <table border="0" id="myTable">
                <tbody>
                <c:forEach var="dessert" items="${dessertList}">
                    <c:if test="${dessert != null}">
                        <tr class="dessert-item">
                            <td>
                                <div class=" dessert-item-custom">
                                    <div class="col-xs-3" >
                                        <img class="img-responsive"
                                           src="./static/images/${dessert.dessertImage}" style="height: 313px;"/>
                                    </div>
                                    <div class="col-xs-9">
                                        <h4>${dessert.name}</h4>
                                        <span >${dessert.price}<span> BYN</span></span>
                                        <p>${fn:substring(dessert.description, 0, 500)}</p>
                                        <form action="controller">
                                            <input type="hidden" name="command"
                                                   value="go_to_dessert_detail_page_command"/>
                                            <input type="hidden" value="${dessert.id}" name="dessertId">

                                            <button type="submit" class="btn btn-outline-light" style="color:  #e1791a;">${locale_dessert_detail}</button>
                                        </form>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        $('#buttonClear').click(function () {
            window.location = "controller?command=go_to_dessert_page_command";
        })
    })
</script>


</body>
</html>
