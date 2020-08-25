var $formAtivadoNoturno = document.getElementById("formAtivadoNoturno");
var $ativadoNoturno = document.getElementById("ativadoNoturno");
var $desativadoNoturno = document.getElementById("desativadoNoturno");
var $btnConfiguracaoSenha = document.getElementById("btnConfiguracaoSenha");
var $senhaConfirmada = document.getElementById("textSenhaConfirmada");
var $senhaNova = document.getElementById("textSenhaNova");
var $senhaAtual = document.getElementById("textSenhaAtual");
var $msgErroSenhaAtual = document.getElementById("msg-erro-senhaAtual");
var $msgErroSenhaNova = document.getElementById("msg-erro-senhaNova");
var $msgSucessoSenhaNova = document.getElementById("msg-sucesso-senhaNova");

function modoNoturnoAtivadoConfiguracoes(ativado){
   if(ativado){
   $ativadoNoturno.checked = true;
   
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
      
   //Add configurações
   var gallery = document.querySelector(".gallery").classList.toggle("galleryNigth");
   var configuracoes = document.querySelector(".configuracoes").classList.toggle("configuracoesNigth"); 
   var configsenha = document.querySelector(".config-senha").classList.toggle("config-senhaNigth");
}else{
   $desativadoNoturno.checked = true;
   
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
   
  //Remove configurações
   var gallery = document.querySelector(".gallery").classList.remove("galleryNigth");
   var configuracoes = document.querySelector(".configuracoes").classList.remove("configuracoesNigth");
   var configsenha = document.querySelector(".config-senha").classList.remove("config-senhaNigth");
}
}

$btnConfiguracaoSenha.addEventListener("click",function(){
	$msgErroSenhaNova.style.display = "none";
	$msgErroSenhaNova.style.display = "none";
	$msgSucessoSenhaNova.style.display = "none";
	$.ajax({
        url: '/projectstages_mvc/retorna/senha-usuario',
        data:{senha : $senhaAtual.value},
        success : function(response){
        	if(response == true){
        		$msgErroSenhaAtual.style.display = "none";
        		if($senhaNova.value == $senhaConfirmada.value){
        			$msgErroSenhaNova.style.display = "none";
        		$.ajax({
        	        url: '/projectstages_mvc/atualiza/senha-usuario',
        	        data: {senhaNova : $senhaNova.value},
        	        success : function(data){
        	        	$senhaAtual.value = "";
        	        	$senhaNova.value = "";
        	        	$senhaConfirmada.value = "";
        	        	$msgSucessoSenhaNova.style.display = "inline-block";
        	        	$msgErroSenhaNova.style.display = "none";
        	        	$msgErroSenhaNova.style.display = "none";
        	        }
        	    });
        		}else{
        			$msgErroSenhaNova.style.display = "inline-block";
        		}
        	}else{
        		$msgErroSenhaAtual.style.display = "inline-block";
        	}
        }
    });
});

$ativadoNoturno.addEventListener("change",function(){
    $formAtivadoNoturno.submit();
});

$desativadoNoturno.addEventListener("change",function(){
    $formAtivadoNoturno.submit();   
});

function modoNotunoConfig(){
	 $.ajax({
	        url: '/projectstages_mvc/configuracoes',
	        data: modoNoturnoAtivado,
	        success : function(data){
	        	modoNoturnoAtivadoConfiguracoes(data);
	        }
	    });
}

(function(){
	'use strict'; 
	$.ajax({
        url: '/projectstages_mvc/retorna/modo-noturno',
        success : function(data){
        	modoNoturnoAtivadoConfiguracoes(data);
        }
    });
})();


 


