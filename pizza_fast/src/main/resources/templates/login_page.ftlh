<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>PIZZA FAST</title>
    <link rel="stylesheet" href="/styles/login.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"/>
</head>
<body>
<#include "header.ftlh" />

<main class="main">
    <div class="main_div">
        <div class="title">Авторизация</div>
        <#if error??>
            <div class="error">
                <div class="error-background"></div>
                <p class="error-text">
                    Неверное имя пользователя или пароль
                </p>
            </div>
        </#if>
        <form method="post" action="/login">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <div class="input_box">
                <input type="text" placeholder="Логин" required name="username">
                <div class="icon"><i class="fas fa-user"></i></div>
            </div>
            <div class="input_box">
                <input type="password" placeholder="Пароль" required name="password">
                <div class="icon"><i class="fas fa-lock"></i></div>
            </div>
            <div class="input_box button">
                <input type="submit" value="Войти">
            </div>
            <div class="sign_up">
                <a class="link" href="/register">Нет аккаунта? <span class="red">Зарегистрироваться</span></a>
            </div>
        </form>
    </div>
</main>
</body>
</html>
