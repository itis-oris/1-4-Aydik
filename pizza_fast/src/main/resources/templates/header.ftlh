<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<link rel="stylesheet" href="/styles/header.css">

<header class="header">
    <nav class="nav">
        <a class="home" href="/">PIZZA FAST</a>

        <#if username??>
            <div class="header-right">
                <a class="link cart" href="/cart">
                    <span>Корзина</span>
                    <img src="/images/vector/cart.svg" alt="">
                    <#if quantity?? && quantity != 0>
                        <div class="quantity">
                            ${quantity}
                        </div>
                    </#if>
                </a>
                <form id="logoutForm" action="/logout" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <button type="submit" class="link">
                        <span>Выйти</span>
                        <img src="/images/vector/logout.svg" alt="">
                    </button>
                </form>
            </div>
        <#else>
            <div class="header-right">
                <a class="link" href="/login">
                    <span>Войти</span>
                    <img src="/images/vector/login.svg" alt="">
                </a>
            </div>
        </#if>
    </nav>
</header>
