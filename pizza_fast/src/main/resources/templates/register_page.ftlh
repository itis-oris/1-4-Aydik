<!DOCTYPE html>
<html>
<head>
    <title>PIZZA FAST</title>
    <link rel="stylesheet" href="/styles/login.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"/>
</head>
<body>
<#include "header.ftlh">
<main class="main">
    <div class="main_div">
        <div class="title">Регистрация</div>

        <#if error??>
            <div class="error">
                <div class="error-background"></div>
                <p class="error-text">${error}</p>
            </div>
        </#if>

        <form method="post" action="/register">
            <div class="input_box">
                <input type="text"
                       placeholder="Логин"
                       name="username"
                       required maxlength="50"
                       value="${registerForm.username!}">
                <div class="icon"><i class="fas fa-user"></i></div>
            </div>
            <div class="input_box">
                <input id="phone" type="tel"
                       placeholder="+7 (___) ___-__-__"
                       maxlength="18"
                       required
                       name="phone"
                       value="${registerForm.phone!}">
                <div class="icon"><i class="fas fa-phone"></i></div>
            </div>
            <div class="input_box">
                <input type="password"
                       placeholder="Пароль"
                       required name="password">
                <div class="icon"><i class="fas fa-lock"></i></div>
            </div>
            <div class="input_box">
                <input type="password"
                       placeholder="Повторите пароль"
                       required name="passwordCopy">
                <div class="icon"><i class="fas fa-lock"></i></div>
            </div>
            <div class="input_box button">
                <input type="submit" value="Зарегистрироваться">
            </div>
            <div class="sign_up">
                <a class="link" href="/login">Уже есть аккаунт? <span class="red">Войти</span></a>
            </div>
        </form>
    </div>
    <script src="/js/phoneFormatter.js"></script>
</main>
</body>
</html>
