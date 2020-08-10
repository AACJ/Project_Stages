var $pass2 = document.getElementById("btnPass2");
var $pass = document.getElementById("btnPass");
var $pass3 = document.querySelector("#btnPass3");
var $card = document.querySelector(".card-interno");
var $passo01 = document.getElementById("passo01");
var $passo02 = document.getElementById("passo02");
var $passo03 = document.getElementById("passo03");
var $form1 = document.querySelector("#form1");
var $form2 = document.querySelector("#form2");

$pass.addEventListener('click', function(){
    var x = -400;
    $card.style.transform = "translateX(" + (x) + "px)";
    $passo01.style.background = 'white';
    $passo02.style.background = 'rgb(59, 168, 248)';
    $passo03.style.background = 'white';
});

$pass2.addEventListener('click', function(){
    var y = -800;
    $card.style.transform = "translateX(" + (y) + "px)";
    $passo01.style.background = 'white';
    $passo02.style.background = 'white';
    $passo03.style.background = 'rgb(59, 168, 248)';
});

$pass3.addEventListener('click', function(){
   

});