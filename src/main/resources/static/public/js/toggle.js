const buttonToggle = document.querySelector('.menu__toggle-burger');
const popUp = document.querySelector('.menu__burger');
const burgerOpen = document.querySelector('.burger__svg');
const burgerClose = document.querySelector('.burger__svg--close');

buttonToggle.addEventListener('click', function (e) {
    e.preventDefault();
   popUp.classList.toggle('visually-hidden');
   burgerOpen.classList.toggle('burger__svg--close');
   burgerClose.classList.toggle('burger__svg--close');
});