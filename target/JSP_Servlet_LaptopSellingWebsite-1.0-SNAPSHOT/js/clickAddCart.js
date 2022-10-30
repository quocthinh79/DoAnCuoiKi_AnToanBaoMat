let btnAddCart = document.getElementById('them-vao-gio');
var iconNum = document.getElementById('icon-cart')
var countCart = 0
if (iconNum === null || iconNum === undefined) {
    countCart = 0;
} else {
    countCart = Number.parseInt(iconNum.innerHTML)
}

btnAddCart.onclick = function () {
    countCart++;
    iconNum.innerHTML = countCart;
    iconNum.style.display = 'flex'
}



