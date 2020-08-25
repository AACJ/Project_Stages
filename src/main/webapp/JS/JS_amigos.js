var $lupaAmigos = document.getElementById("lupa-amigos");
var $pesquisaAddAmigo = document.getElementById("pesquisaAddAmigo");

$pesquisaAddAmigo.addEventListener("keydown", function(e){

	if(e.keyCode > 46 && e.keyCode < 91){
	$.ajax({
	       url: '/projectstages_mvc/retorna/pesquisa',
	       data:{nome : $pesquisaAddAmigo.value},
	       success : function(response){
	    	   $.ajax({
	    	       url: '/projectstages_mvc/clear/pesquisa',
	    	       success : function(result){
	    	       
	    	    	   for(var i = 0; i < result.length;i++ ){
	    	    	
	    	    	   var $perfilAmigo = document.getElementById("perfil-amigo-" + result[i]);
	    	    	   
	    	    	   $perfilAmigo.style.display = "none";
	    	    	   }
	    	    	   
	    	    	   for(var i = 0; i < response.length;i++ ){
	    		    	   var $perfilAmigo = document.getElementById("perfil-amigo-" + response[i]);
	    		    	   
	    		    	   $perfilAmigo.style.display = "flex";
	    		    	   }
	    	    	   
	    	    
	    	       }
	    	   });
	    	  
	       }
	   });
	}
});

$pesquisaAddAmigo.addEventListener("keyup", function(e){

	if(e.keyCode > 46 && e.keyCode < 91){
	$.ajax({
	       url: '/projectstages_mvc/retorna/pesquisa',
	       data:{nome : $pesquisaAddAmigo.value},
	       success : function(response){
	    	   $.ajax({
	    	       url: '/projectstages_mvc/clear/pesquisa',
	    	       success : function(result){
	    	       
	    	    	   for(var i = 0; i < result.length;i++ ){
	    	    	
	    	    	   var $perfilAmigo = document.getElementById("perfil-amigo-" + result[i]);
	    	    	   
	    	    	   $perfilAmigo.style.display = "none";
	    	    	   }
	    	    	   
	    	    	   for(var i = 0; i < response.length;i++ ){
	    		    	   var $perfilAmigo = document.getElementById("perfil-amigo-" + response[i]);
	    		    	   
	    		    	   $perfilAmigo.style.display = "flex";
	    		    	   }
	    	    	   
	    	    
	    	       }
	    	   });
	    	  
	       }
	   });
	}
});

$lupaAmigos.addEventListener('click', function(){
	alert($pesquisaAddAmigo.value)
	$.ajax({
	       url: '/projectstages_mvc/amigos',
	       data:{nome : $pesquisaAddAmigo.value},
	       success : function(response){

	       }
	   });
});

function modoNoturnoAtivadoAmigos(ativado){
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
		   
	   //Amigos tags
	   var headeramigos = document.querySelector(".header-amigos").classList.toggle("header-amigosNigth");
	   var headeramigos = document.querySelector(".card-addAmigo").classList.toggle("card-addAmigoNigth");
	   var amigosPerfils = document.querySelectorAll(".amigos-perfils");
	   var meusAmigosPerfil = document.querySelectorAll(".meus-amigos-perfil");
	   
	   for (var i = 0; i < amigosPerfils.length; i++) {
		   amigosPerfils[i].classList.toggle("amigos-perfilsNigth");
		 }
	   
	   for (var i = 0; i < meusAmigosPerfil.length; i++) {
		   meusAmigosPerfil[i].classList.toggle("meus-amigos-perfilNigth");
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
	   
	   //Remove Amigos tags	   
	   var headeramigos = document.querySelector(".header-amigos").classList.remove("header-amigosNigth");
	   var headeramigos = document.querySelector(".card-addAmigo").classList.remove("card-addAmigoNigth");
	   var amigosPerfils = document.querySelectorAll(".amigos-perfils");
	   
	   for (var i = 0; i < amigosPerfils.length; i++) {
		   amigosPerfils[i].classList.remove("amigos-perfilsNigth");
		 }
	   
	   }
}    


(function(){
	'use strict'; 
	
	$.ajax({
       url: '/projectstages_mvc/retorna/modo-noturno/amigos',
       success : function(data){
       	modoNoturnoAtivadoAmigos(data);
       }
   });
})();