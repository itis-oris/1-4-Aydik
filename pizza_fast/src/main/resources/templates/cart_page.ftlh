<#import 'cart_item.ftlh' as ci>
<!DOCTYPE html>
<html>
<head>
    <title>PIZZA FAST</title>
    <link rel="stylesheet" href="/styles/cart.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"/>
</head>
<body>
<#include "header.ftlh">
<main class="main">
    <div class="main-div">
        <div class="title">
            Корзина
            <#if cartItems?size != 0>
                <button id="clearCartButton">
                    <span>Очистить корзину</span>
                    <img src="/images/vector/basket.svg" alt="delete from cart">
                </button>
            </#if>
        </div>
        <hr>
        <#if cartItems?size != 0>
            <div class="not-empty-cart">
                <div class="products">
                    <#list cartItems as item>
                        <@ci.render item=item/>
                    </#list>
                </div>
                <div class="totalPrice">${totalPrice} ₽</div>
                <div class="title">Детали доставки</div>
                <form method="post" action="/orders/create">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <p class="detail_title">Адрес доставки:</p>
                    <div class="input_box">
                        <input type="text" placeholder="Адрес доставки" required name="address">
                        <div class="icon"><i class="fas fa-map-pin"></i></div>
                    </div>
                    <p class="detail_title">Email для отправки чека:</p>
                    <div class="input_box">
                        <input type="email" placeholder="Email" required name="email">
                        <div class="icon"><i class="fas fa-envelope"></i></div>
                    </div>
                    <#if error?? && error="email">
                        <div class="error">
                            <div class="error-background"></div>
                            <p class="error-text">
                                Неверный адрес email
                            </p>
                        </div>
                    </#if>
                    <div class="input_box button">
                        <input type="submit" value="Создать заказ">
                    </div>
                </form>
            </div>
        <#else>
            <div class="empty-cart">
                <div class="empty-cart-title">Корзина пуста</div>
                <a href="/">Добавим пиццу</a>
            </div>
        </#if>
    </div>
</main>
<#include "footer.ftlh">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="/js/cart.js"></script>
</body>
</html> 