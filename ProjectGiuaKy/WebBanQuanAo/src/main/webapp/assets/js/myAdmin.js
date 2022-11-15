const myToggleIcon = document.querySelector('#admin-section .menu .icon-title');
const myLis = document.querySelectorAll('#admin-section .menu li span');
const myMenu = document.querySelector('.menu');
const myContent = document.querySelector('.content');
const myPageTitle = document.querySelector('.menu .page-title');
myToggleIcon.onclick = () => {
    myMenu.classList.toggle("active");
    myPageTitle.classList.toggle("none-display");
    for (let i = 0; i < myLis.length; i++) {
        myLis[i].classList.toggle("none-display");
    }
    if (myMenu.classList.contains("col-md-3")) {
        myMenu.classList.remove("col-md-3");
        myMenu.classList.add("col-md-1");
        myContent.classList.remove('col-md-9');
        myContent.classList.add("col-md-11");
    } else {
        myMenu.classList.remove("col-md-1");
        myMenu.classList.add("col-md-3");
        myContent.classList.remove('col-md-11');
        myContent.classList.add("col-md-9");
    }
}