<#macro render item>
    <div class="cart-item">
        <div class="item-photo">
            <img src="/api/files/download/${item.pizza.photo}" alt="">
        </div>
        <div class="item-content">
            <div class="main-info">
                <div class="name-and-price">
                    <div class="name">
                        ${item.pizza.name}
                    </div>
                    <div class="price">
                        ${item.pizza.price * item.quantity} ₽
                    </div>
                </div>
                <div class="weight">
                    ${item.pizza.weight} г
                </div>
            </div>
            <div class="quantity-container">
                <button id="buttonDelete_${item.pizza.id}" class="buttonDelete">
                    <img src="/images/vector/basket.svg" alt="delete from cart">
                </button>
                <div class="correct-quantity">
                    <button id="buttonMinus_${item.pizza.id}" class="buttonMinus">
                        <img src="/images/vector/minus.svg" alt="reduce the number">
                    </button>
                    <div class="quantity">
                        ${item.quantity}
                    </div>
                    <button id="buttonPlus_${item.pizza.id}" class="buttonPlus">
                        <img src="/images/vector/plus.svg" alt="increase the number">
                    </button>
                </div>
            </div>
        </div>
    </div>
    <hr>
</#macro>