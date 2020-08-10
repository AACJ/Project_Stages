var $btnChat01 = document.getElementById("btnChat01");
var $btnChat02 = document.getElementById("btnChat02");
var $contentChat = document.querySelector(".chat-body-interno");

$btnChat01.addEventListener("click", function(){
  $contentChat.style.transform = "translateX(" + (0) + "px)";
   $btnChat01.style.textDecoration ="underline";
   $btnChat02.style.textDecoration ="none"; 
});

$btnChat02.addEventListener("click", function(){
   $contentChat.style.transform = "translateX(" + (-410) + "px)";
   $btnChat02.style.textDecoration ="underline";
   $btnChat01.style.textDecoration ="none"; 
});

function modoNoturnoAtivadoChat(ativado){
	   if(ativado){	  
		   
	   //Geral Padrão
	   var corpo = document.querySelector(".corpo").classList.toggle("bodyNigth"); 
	   var menu = document.querySelector(".menu").classList.toggle("menuNigth"); 
	   var principal = document.querySelector(".principal").classList.toggle("principalNigth"); 
	   var botao = document.querySelector(".botao").classList.toggle("botaoNigth");
	   var opcao = document.querySelector(".opcao").classList.toggle("opcaoNigth");
	   var statusNigth = document.querySelector(".status").classList.toggle("statusNigth");
	   
	   //Chat tags
	   var chat = document.querySelector(".chat").classList.toggle("chatNigth");
	   }else{
	   //Remove Geral Padrão
	   var corpo = document.querySelector(".corpo").classList.remove("bodyNigth");
	   var menu = document.querySelector(".menu").classList.remove("menuNigth"); 
	   var principal = document.querySelector(".principal").classList.remove("principalNigth"); 
	   var botao = document.querySelector(".botao").classList.remove("botaoNigth");
	   var opcao = document.querySelector(".opcao").classList.remove("opcaoNigth");
	   var statusNigth = document.querySelector(".status").classList.remove("statusNigth");
	   
	   //Chat tags
	   var chat = document.querySelector(".chat").classList.remove("chatNigth");
	   }
}    


(function(){
	'use strict'; 
	
	$.ajax({
       url: '/projectstages_mvc/retorna/modo-noturno/chat',
       success : function(data){
       	modoNoturnoAtivadoChat(data);
       }
   });
})();