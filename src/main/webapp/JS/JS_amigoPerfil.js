var $btnaddperfilamigo = document.getElementById("btn-add-perfil-amigo");
var $btnsolicitacaoperfilamigo = document.getElementById("btn-solicitacao-perfil-amigo");
var $btnremoveperfilamigo = document.getElementById("btn-remove-perfil-amigo");
var $textIdAmigo = document.getElementById("textIdAmigo");
var $btnCopyAmigo = document.getElementById("btn-copyAmigo");

$btnCopyAmigo.addEventListener("click", function(){
	$textIdAmigo.select();
	document.execCommand("Copy");
});

$btnaddperfilamigo.addEventListener('click', function(){
	$.ajax({
	       url: '/projectstages_mvc/add/notificacao',
	       data:{emailAmigo : $btnaddperfilamigo.value},
	       success : function(data){
	       
	       }
	   }); 
	$btnaddperfilamigo.style.display = "none";
	$btnsolicitacaoperfilamigo.style.display = "block";
	$btnremoveperfilamigo.style.display = "none";
});

$btnsolicitacaoperfilamigo.addEventListener('click', function(){
	$.ajax({
	       url: '/projectstages_mvc/remove/notificacao',
	       data:{email : $btnaddperfilamigo.value},
	       success : function(data){
	       
	       }
	   }); 
	$btnaddperfilamigo.style.display = "block";
	$btnsolicitacaoperfilamigo.style.display = "none";
	$btnremoveperfilamigo.style.display = "none";
});

$btnremoveperfilamigo.addEventListener('click', function(){
	$.ajax({
	       url: '/projectstages_mvc/remove/amigo',
	       data:{emailAmigo : $btnremoveperfilamigo.value},
	       success : function(data){
	       
	       }
	   }); 
	 $btnaddperfilamigo.style.display = "block";
	 $btnsolicitacaoperfilamigo.style.display = "none";
	 $btnremoveperfilamigo.style.display = "none";
});

function verificacaoDeAmizade(){
	$.ajax({
	       url: '/projectstages_mvc/verificacao/amigo',
	       success : function(response){
	    	   $.ajax({
	    	       url: '/projectstages_mvc/verificacao/notificacao',
	    	       success : function(result){
	    	    	   if(response == true){
	    	    		   $btnaddperfilamigo.style.display = "none";
	    	    		   $btnsolicitacaoperfilamigo.style.display = "none";
	    	    		   $btnremoveperfilamigo.style.display = "block";
	    	    	   }else if(response == false){
	    	    		   $btnaddperfilamigo.style.display = "block";
	    	    		   $btnsolicitacaoperfilamigo.style.display = "none";
	    	    		   $btnremoveperfilamigo.style.display = "none";
	    	    	   } 
	    	    	   if(result == true){
	    	    		   $btnaddperfilamigo.style.display = "none";
	    	    			$btnsolicitacaoperfilamigo.style.display = "block";
	    	    			$btnremoveperfilamigo.style.display = "none";
	    	    	   }
	    	       }
	    		});
	       }
		});
}

verificacaoDeAmizade();


function modoNoturnoAtivadoAmigosPerfil(ativado){
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
	   var gallery = document.querySelector(".gallery").classList.toggle("galleryNigth"); 
	   var informacao = document.querySelector(".informacao").classList.toggle("informacaoNigth"); 
	   var updatenameuser = document.querySelector(".update-name-user").classList.toggle("update-name-userNigth"); 
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
	   var gallery = document.querySelector(".gallery").classList.remove("galleryNigth"); 
	   var informacao = document.querySelector(".informacao").classList.remove("informacaoNigth"); 
	   var updatenameuser = document.querySelector(".update-name-user").classList.remove("update-name-userNigth"); 
	  }
}    

(function(){
	'use strict'; 
	
	$.ajax({
       url: '/projectstages_mvc/retorna/modo-noturno/amigos-perfil',
       success : function(data){
    	   modoNoturnoAtivadoAmigosPerfil(data);
       }
   });

	
})();

