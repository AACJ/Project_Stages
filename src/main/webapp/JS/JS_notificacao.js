var $btnactpedido = document.querySelectorAll("#btn-act-pedido");
var $btnrempedido = document.querySelectorAll("#btn-rem-pedido");
var $msgconfirm = document.querySelectorAll("#msg-confirm");
var $bodyNotificacao =document.querySelector(".corpo");
var $avisoAtarefa = document.getElementById("aviso-atarefa");

$bodyNotificacao.addEventListener('click', function(e){
	for(var i = 0; i < $btnactpedido.length ;i++){
		if($btnactpedido[i].contains(e.target)){
			$.ajax({
			       url: '/projectstages_mvc/add/amigo',
			       data:{emailAmigo : $btnactpedido[i].value},
			       success : function(data){
			       
			       }
			   });
			$btnactpedido[i].style.display = "none";
			   $btnrempedido[i].style.display = "none";
			$msgconfirm[i].style.display="block";
		}
	}
});

/*
$btnactpedido.addEventListener('click', function(){
	
});

$btnrempedido.addEventListener('click', function(){

});
*/

function ExibirBotoes(){
	$.ajax({
	       url: '/projectstages_mvc/verificacao/notificacao/amigo',
	       success : function(response){
	    	   for(var i = 0; i < response.length;i++){
	    	   if(response[i] == true){
	    		   $btnactpedido[i].style.display = "none";
	    		   $btnrempedido[i].style.display = "none";
	    		   $msgconfirm[i].style.display = "block";
	    	   }else{
	    		   $btnactpedido[i].style.display = "block";
	    		   $btnrempedido[i].style.display = "block";
	    		   $msgconfirm[i].style.display = "none";
	    	   }
	    	   }
	       }
		});
	
}

ExibirBotoes();

function modoNoturnoAtivadoNotificacao(ativado){
	   if(ativado){	    
	   //Geral Padrão
	   var corpo = document.querySelector(".corpo").classList.toggle("bodyNigth"); 
	   var menu = document.querySelector(".menu").classList.toggle("menuNigth"); 
	   var botao = document.querySelector(".botao").classList.toggle("botaoNigth");
	   var principal = document.querySelector(".principal").classList.toggle("principalNigth"); 
	   var opcao = document.querySelector(".opcao").classList.toggle("opcaoNigth");
	   var statusNigth = document.querySelector(".status").classList.toggle("statusNigth");
	   var caixaAddProjetoInterno = document.querySelector(".caixa-add-projeto-interno").classList.toggle("caixa-add-projeto-internoNigth");
	   var fechaCaixaProjeto = document.querySelector(".fecha-caixa-projeto").classList.toggle("fecha-caixa-projetoNigth");
	   var textCaixaAddProjeto = document.querySelectorAll(".text-caixa-add-projeto");
	   
	   for (var i = 0; i < textCaixaAddProjeto.length; i++) {
		   textCaixaAddProjeto[i].classList.toggle("text-caixa-add-projetoNigth");
		 }
	   
	  //Add perfil
	   var headerNotificacao = document.querySelector(".header-notificacao").classList.toggle("header-notificacaoNigth"); 
	   var pedidoDeAmizade = document.querySelectorAll(".pedido-de-amizade");
	   
	   for (var i = 0; i < pedidoDeAmizade.length; i++) {
		   pedidoDeAmizade[i].classList.toggle("pedido-de-amizadeNigth");
		 }
	   
	  }else{
		  
	   //Remove Geral Padrão
	   var corpo = document.querySelector(".corpo").classList.remove("bodyNigth");
	   var menu = document.querySelector(".menu").classList.remove("menuNigth"); 
	   var principal = document.querySelector(".principal").classList.remove("principalNigth"); 
	   var botao = document.querySelector(".botao").classList.remove("botaoNigth");
	   var opcao = document.querySelector(".opcao").classList.remove("opcaoNigth");
	   var statusNigth = document.querySelector(".status").classList.remove("statusNigth");
	   var caixaAddProjetoInterno = document.querySelector(".caixa-add-projeto-interno").classList.remove("caixa-add-projeto-internoNigth");
	   var fechaCaixaProjeto = document.querySelector(".fecha-caixa-projeto").classList.remove("fecha-caixa-projetoNigth");
	   var textCaixaAddProjeto = document.querySelectorAll(".text-caixa-add-projeto");
	   
	   for (var i = 0; i < textCaixaAddProjeto.length; i++) {
		   textCaixaAddProjeto[i].classList.remove("text-caixa-add-projetoNigth");
		 }
	   
	   //Remove perfil
	   var headerNotificacao = document.querySelector(".header-notificacao").classList.remove("header-notificacaoNigth"); 
	   var pedidoDeAmizade = document.querySelectorAll(".pedido-de-amizade");
	   
	   for (var i = 0; i < pedidoDeAmizade.length; i++) {
		   pedidoDeAmizade[i].classList.remove("pedido-de-amizadeNigth");
		 }
	   
	 }
}    

(function(){
	'use strict'; 
	
	$.ajax({
       url: '/projectstages_mvc/retorna/modo-noturno/notificacao',
       success : function(data){
       	modoNoturnoAtivadoNotificacao(data);
       }
   });

	
})();