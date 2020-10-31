const burger = document.querySelector('.burger');

const iconBurger = document.querySelector(".fa-bars");
const iconX = document.querySelector(".fa-times");
const column = document.querySelector(".header-aside");
const header = document.querySelector(".order-header");

const accountDescription = document.querySelector("#account a");
const subMenu = document.querySelector(".sub-menu-wrap");
const hideSubMenu = document.querySelector(".back-to-main-menu");

burger.addEventListener('click', function () {
    iconBurger.classList.toggle("show");
    iconX.classList.toggle("show");
    column.classList.toggle("show");
    header.classList.toggle("hide");
    
    if(subMenu != null){
        subMenu.classList.remove("show-sub-menu");
    }
});

if (accountDescription != null && window.screen.width < 1024) {
    accountDescription.addEventListener('click', function () {
        subMenu.classList.add("show-sub-menu");
    });
}

if (hideSubMenu != null && window.screen.width < 1024) {
    hideSubMenu.addEventListener('click', function () {
        subMenu.classList.remove("show-sub-menu");
    });
}