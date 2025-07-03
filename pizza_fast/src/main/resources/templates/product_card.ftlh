<#macro render item>
    <div class="product-card">
        <div class="item-photo">
            <img src="/api/files/download/${item.pizza().photo}" alt="">
        </div>
        <div class="item-header">
            <div class="title">${item.pizza().name}</div>
            <div class="weight">${item.pizza().weight} г</div>
        </div>
        <hr>
        <div class="item-description">
            ${item.pizza().description}
        </div>
        <div class="item-price">
            <div class="price">${item.pizza().price} ₽</div>

            <#if item.quantity() == 0>
                <button id="buttonAdd_${item.pizza().id}" class="button buttonAdd">
                    <img src="/images/vector/plus.svg" class="plus" alt="add to cart">
                </button>
            <#else>
                <div class="correct-quantity">
                    <button id="buttonDelete_${item.pizza().id}" class="buttonDelete">
                        <img src="/images/vector/basket.svg" alt="delete from cart">
                    </button>
                    <button id="buttonMinus_${item.pizza().id}" class="button buttonMinus">
                        <img src="/images/vector/minus.svg" alt="reduce the number">
                    </button>
                    <div class="quantity">
                        ${item.quantity()}
                    </div>
                    <button id="buttonPlus_${item.pizza().id}" class="button buttonPlus">
                        <img src="/images/vector/plus.svg" alt="increase the number">
                    </button>
                </div>
            </#if>
        </div>
    </div>
</#macro>