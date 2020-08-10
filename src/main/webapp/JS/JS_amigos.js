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
	   var principal = document.querySelector(".principal").classList.toggle("principalNigth"); 
	   var botao = document.querySelector(".botao").classList.toggle("botaoNigth");
	   var opcao = document.querySelector(".opcao").classList.toggle("opcaoNigth");
	   var statusNigth = document.querySelector(".status").classList.toggle("statusNigth");

	   //Amigos tags
	   var headeramigos = document.querySelector(".header-amigos").classList.toggle("header-amigosNigth");
	   
	   }else{
	   //Remove Geral Padrão
	   var corpo = document.querySelector(".corpo").classList.remove("bodyNigth");
	   var menu = document.querySelector(".menu").classList.remove("menuNigth"); 
	   var principal = document.querySelector(".principal").classList.remove("principalNigth"); 
	   var botao = document.querySelector(".botao").classList.remove("botaoNigth");
	   var opcao = document.querySelector(".opcao").classList.remove("opcaoNigth");
	   var statusNigth = document.querySelector(".status").classList.remove("statusNigth");
	   var headeramigos = document.querySelector(".header-amigos").classList.remove("header-amigosNigth");
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