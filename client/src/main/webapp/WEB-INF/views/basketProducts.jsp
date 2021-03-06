<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<script>
    <%@include file="/js/pottleProducts.js"%>
</script>

<c:if test="${!empty basket}">

    <div id="content">
        <span>Общая сумма заказа: ${sum}</span>

        <form action="/onlineShop/pottle/issueOrder" method="get">
            <button type="submit">Оформить заказ</button>
        </form>
        <br/>

        <table border="0" cellpadding="0" width="100%">
            <tr>
                <td><b>Название</b></td>
                <td><b>Цена</b></td>
                <td><b>Количество</b></td>
                <td></td>
            </tr>
            <c:forEach items="${basket}" var="basketDto">
                <tr>
                    <td><b> ${basketDto.productDto.name} </b></td>
                    <td><b> ${basketDto.productDto.price} </b></td>
                    <td><b> ${basketDto.countProducts} </b></td>
                    <td>
                        <form action="/onlineShop/product/infoPottle" method="get">
                            <input type="hidden" name="id" value="${basketDto.productDto.productId}">
                            <button type="submit"> Подробнее </button>
                        </form>
                    </td>
                    <td>
                        <button class="deleteFromPottleButton"
                                data-id="${basketDto.productDto.productId}"
                                data-csrf-name="${_csrf.parameterName}"
                                data-csrf-value="${_csrf.token}">
                            Удалить один товар из корзины
                        </button>
                    </td>
                    <td>
                        <button class="deleteFromPottleAllButton"
                                data-id="${basketDto.productDto.productId}"
                                data-csrf-name="${_csrf.parameterName}"
                                data-csrf-value="${_csrf.token}">
                            Удалить все товары из корзины
                        </button>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</c:if>

<c:if test="${empty basket}">
    <h3>Корзина пуста. Чтобы добавить товары в корзину, перейдите на главную страницу</h3>
</c:if>




