const addButtons = Array.from(document.getElementsByClassName("buttonAdd"));
const minusButtons = Array.from(document.getElementsByClassName("buttonMinus"));
const plusButtons = Array.from(document.getElementsByClassName("buttonPlus"));
const deleteButtons = Array.from(document.getElementsByClassName("buttonDelete"));
const clearCartButton = document.getElementById("clearCartButton");
// const orderForm = document.querySelector("form[action='/cart/order']");

for (const item of addButtons) {
    const pizza_id = item.id.split('_')[1];
    item.addEventListener('click', function () {
        addCart(pizza_id);
    });
}

for (const item of minusButtons) {
    const pizza_id = item.id.split('_')[1];
    item.addEventListener('click', function () {
        reduceQuantity(pizza_id);
    });
}

for (const item of plusButtons) {
    const pizza_id = item.id.split('_')[1];
    item.addEventListener('click', function () {
        addCart(pizza_id);
    });
}

for (const item of deleteButtons) {
    const pizza_id = item.id.split('_')[1];
    item.addEventListener('click', function () {
        deleteCart(pizza_id);
    });
}

if (clearCartButton) {
    clearCartButton.addEventListener('click', function () {
        clearCart();
    });
}

function getCsrfToken() {
    return document.querySelector('meta[name="_csrf"]').getAttribute('content');
}

function getCsrfHeader() {
    return {
        'X-CSRF-TOKEN': getCsrfToken()
    };
}

function addCart(pizza_id) {
    fetch(`/api/cart/add?pizzaId=${pizza_id}`, {
        method: "POST",
        headers: getCsrfHeader()
    })
        .then(handleResponse)
        .catch(handleError);
}

function reduceQuantity(pizza_id) {
    fetch(`/api/cart/reduce?pizzaId=${pizza_id}`, {
        method: "POST",
        headers: getCsrfHeader()
    })
        .then(handleResponse)
        .catch(handleError);
}

function deleteCart(pizza_id) {
    fetch(`/api/cart/delete?pizzaId=${pizza_id}`, {
        method: "DELETE",
        headers: getCsrfHeader()
    })
        .then(handleResponse)
        .catch(handleError);
}

function clearCart() {
    fetch(`/api/cart/clear`, {
        method: "DELETE",
        headers: getCsrfHeader()
    })
        .then(handleResponse)
        .catch(handleError);
}

// if (orderForm) {
//     orderForm.addEventListener('submit', function (e) {
//         e.preventDefault();
//         const addressInput = orderForm.querySelector('input[name="address"]');
//         if (!addressInput || !addressInput.value) return;
//         fetch(`/api/cart/order`, {
//             method: "POST",
//             headers: {
//                 'Content-Type': 'application/x-www-form-urlencoded',
//                 ...getCsrfHeader()
//             },
//             body: `address=${encodeURIComponent(addressInput.value)}`
//         })
//             .then(response => {
//                 if (response.ok) {
//                     window.location.href = "/";
//                 } else {
//                     return response.text().then(alert);
//                 }
//             })
//             .catch(handleError);
//     });
// }

function handleResponse(response) {
    if (response.redirected) {
        window.location.href = response.url;
    } else if (!response.ok) {
        throw new Error(`Ошибка: ${response.status}`);
    } else location.reload();
}

function handleError(error) {
    console.error("Ошибка запроса:", error);
}
