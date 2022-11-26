const addToCard = $("#my-add-to-card");
const baseURL = "http://localhost:8080/WebBanQuanAo";

addToCard.click(function (e) {
    e.preventDefault();
    const productIdElement = $("#my-product-id");
    let productId = productIdElement.text().split(":")[1].trim();
    const color = $("#my-color input[type='radio']:checked").val();
    const size = $("#my-size input[type='radio']:checked").val();
    const quantity = $("#my-quantity").val();

    $.ajax({
        url: baseURL + "/api/get-name",
        type: 'GET',
        dataType: 'text',
        success(username) {
            if (username.trim() === "null") {
                document.cookie = "productID-nu=" + productId;
                document.cookie = "color-nu=" + color;
                document.cookie = "size-nu=" + size;
                document.cookie = "quantity-nu=" + quantity;
                window.location.replace("http://localhost:8080/WebBanQuanAo/login");
            } else {
                addToCart(productId, color, size, quantity, "Thêm sản phẩm thành công", true);
            }
        }
    })
})

const addToCart = (productId, color, size, quantity, notify, updateCartNum) => {
    $.ajax({
        url: baseURL + "/api/card",
        type: 'POST',
        data: {
            productId,
            color,
            size,
            quantity
        }
    }).done(function () {
        if (notify) {
            alert(notify);
        }
        if (updateCartNum) {
            getCardItem();
        }
    })
}

$(document).ready(function () {
    const deleteCard = $(".deleteCard");
    const deleteCards = Array.from(deleteCard);
    if (deleteCard) {
        deleteCards.forEach(function (deleteItem) {
            $(deleteItem).click(function (e) {
                e.preventDefault();
                const idCard = $(deleteItem).attr("idCard");
                const idProduct = $(deleteItem).attr("idProduct");
                const size = $(deleteItem).attr("size");
                const color = $(deleteItem).attr("color");
                $.ajax({
                    url: baseURL + "/api/card?idCard=" + idCard + "&idProduct=" + idProduct + "&size=" + size + "&color=" + color,
                    type: 'DELETE',
                    dataType: 'text',
                    success() {
                        $("#" + idProduct).remove();
                        updatePrice();
                        getCardItem();
                    }
                })
            })
        })
    }
})

const getCardItem = () => {
    $.ajax({
        url: baseURL + "/api/card",
        type: 'GET',
        dataType: "text",
        success(cardItem) {
            $("#all-card-item").text(cardItem);
        }
    })
}
getCardItem();
const updateCard = $("#my-update-cart");
if (updateCard) {
    $(updateCard).click(function () {
        const itemsQuantity = $(".my-item-quantity");
        const arr = Array.from(itemsQuantity);
        const itemChange = [];
        arr.forEach(item => {
            if ($(item).attr("initValue") !== $(item).val()) {
                itemChange.push({
                    idProduct: $(item).attr("idProduct"),
                    quantity: $(item).val(),
                    color: $(item).attr("color"),
                    size: $(item).attr("size"),
                });
            }
        });
        if (itemChange.length !== 0) {
            itemChange.forEach(item => {
                addToCart(item.idProduct, item.color, item.size, item.quantity);
            })
            updatePrice();
            alert("Cập nhật thành công");
        }
    })
}
const updatePrice = () => {
    const totalPriceElement = $(".total-price");
    if (totalPriceElement) {
        const totalPrices = Array.from(totalPriceElement);
        const priceElement = $(".price");
        const prices = Array.from(priceElement);
        const quantity = $(".my-item-quantity");
        let kq = prices.reduce((total, currentValue, currentIndex) => {
            const price = Number($(currentValue).text().split(" ")[0].trim());
            return total += (price * Number($(quantity[currentIndex]).val()));
        }, 0)
        totalPrices.forEach(e=> {
            $(e).text(kq +" VNĐ");
        })
    }
}
updatePrice();

