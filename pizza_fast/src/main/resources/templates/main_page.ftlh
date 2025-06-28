<#import "product_card.ftlh" as pc>
<!DOCTYPE html>
<html>
<head>
    <title>PIZZA FAST</title>
    <link rel="stylesheet" href="/styles/index.css">
</head>
<body>
<#include "header.ftlh">

<main class="main">
    <div class="banner">
        <img class="banner-image" src="/images/pictures/banner.jpg" alt="">
        <div class="marque">
            <div class="marque-content">
                <div class="collection">
                    <#list 1..5 as i>
                        <span>PIZZA FAST</span>
                        <img src="/images/vector/pizza_mini.svg" alt="">
                        <span>ЗАКАЗАТЬ ОНЛАЙН</span>
                        <img src="/images/vector/pizza_mini.svg" alt="">
                    </#list>
                </div>
            </div>
        </div>
    </div>

    <div class="catalog">
        <#list products as item>
            <@pc.render item=item />
        </#list>
    </div>
</main>

<#include "footer.ftlh">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="/js/cart.js"></script>
</body>
</html>
