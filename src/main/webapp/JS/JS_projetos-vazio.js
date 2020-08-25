function modoNoturnoAtivadoHomeVazio(ativado){
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
	   
	   var boxnewproject = document.querySelector(".box-new-project").classList.toggle("box-new-projectNigth");
	   
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
	  
	   var boxnewproject = document.querySelector(".box-new-project").classList.remove("box-new-projectNigth");
	  
	   }
}    

(function(){
	'use strict'; 
	
	$.ajax({
       url: '/projectstages_mvc/retorna/modo-noturno/home-vazio',
       success : function(data){
       	modoNoturnoAtivadoHomeVazio(data);
       }
   });

})();