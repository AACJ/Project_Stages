var $btnChat01 = document.getElementById("btnChat01");
var $contentChat = document.querySelector(".chat-body-interno");
var $pesquisaConversa = document.getElementById("pesquisaConversa");

$btnChat01.addEventListener("click", function(){
  $contentChat.style.transform = "translateX(" + (0) + "px)";
   $btnChat01.style.textDecoration ="underline";
   $btnChat02.style.textDecoration ="none"; 
});

function exibirChats(){
	$.ajax({
	       url: '/projectstages_mvc/clear/pesquisa-chat',
	       success : function(result){
	       
	    	   for(var i = 0; i < result.length;i++ ){
	    	
	    	   var $perfilChat = document.getElementById("perfil-chat-" + result[i]);
	    	   
	    	   $perfilChat.style.display = "flex";
	    	   }
	       }
	   });
}

$pesquisaConversa.addEventListener("keydown", function(e){

	if($pesquisaConversa.value == ""){
		exibirChats();
	}
	
	if(e.keyCode > 46 && e.keyCode < 91){
	$.ajax({
	       url: '/projectstages_mvc/retorna/pesquisa-chat',
	       data:{nome : $pesquisaConversa.value},
	       success : function(response){
	    	   $.ajax({
	    	       url: '/projectstages_mvc/clear/pesquisa-chat',
	    	       success : function(result){
	    	       
	    	    	   for(var i = 0; i < result.length;i++ ){
	    	    	
	    	    	   var $perfilChat = document.getElementById("perfil-chat-" + result[i]);
	    	    	   
	    	    	   $perfilChat.style.display = "none";
	    	    	   }
	    	    	   
	    	    	   for(var i = 0; i < response.length;i++ ){
	    		    	   var $perfilChat = document.getElementById("perfil-chat-" + response[i]);
	    		    	   
	    		    	   $perfilChat.style.display = "flex";
	    		    	   }
	    	       }
	    	   });
	    	  
	       }
	   });
	}
});

$pesquisaConversa.addEventListener("keyup", function(e){

	if($pesquisaConversa.value == ""){
		exibirChats();
	}
	
	if(e.keyCode > 46 && e.keyCode < 91){
	$.ajax({
	       url: '/projectstages_mvc/retorna/pesquisa-chat',
	       data:{nome : $pesquisaConversa.value},
	       success : function(response){
	    	   $.ajax({
	    	       url: '/projectstages_mvc/clear/pesquisa-chat',
	    	       success : function(result){
	    	       
	    	    	   for(var i = 0; i < result.length;i++ ){
	    	    	
	    	    	   var $perfilChat = document.getElementById("perfil-chat-" + result[i]);
	    	    	   
	    	    	   $perfilChat.style.display = "none";
	    	    	   }
	    	    	   
	    	    	   for(var i = 0; i < response.length;i++ ){
	    		    	   var $perfilChat = document.getElementById("perfil-chat-" + response[i]);
	    		    	   
	    		    	   $perfilChat.style.display = "flex";
	    		    	   }
	    	       }
	    	   });
	    	  
	       }
	   });
	}
});

function modoNoturnoAtivadoChat(ativado){
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

	   //Chat tags
	   var chat = document.querySelector(".chat").classList.toggle("chatNigth");
	   var perfilchat = document.querySelectorAll(".perfil-chat");
	   
	   for (var i = 0; i < perfilchat.length; i++) {
		   perfilchat[i].classList.toggle("perfil-chatNigth");
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
		   
	   //Chat tags
	   var chat = document.querySelector(".chat").classList.remove("chatNigth");
	   var perfilchat = document.querySelectorAll(".perfil-chat");
	  
	   for (var i = 0; i < perfilchat.length; i++) {
		   perfilchat[i].classList.remove("perfil-chatNigth");
		 }
	   
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
	
	exibirChats();
	
})();