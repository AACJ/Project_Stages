var $formAtivadoNoturno = document.getElementById("formAtivadoNoturno");
var $ativadoNoturno = document.getElementById("ativadoNoturno");
var $desativadoNoturno = document.getElementById("desativadoNoturno");

function modoNoturnoAtivadoConfiguracoes(ativado){
   if(ativado){
   $ativadoNoturno.checked = true;
   var gallery = document.querySelector(".gallery").classList.toggle("galleryNigth");
   var configuracoes = document.querySelector(".configuracoes").classList.toggle("configuracoesNigth"); ;
   var menu = document.querySelector(".menu").classList.toggle("menuNigth"); 
   var principal = document.querySelector(".principal").classList.toggle("principalNigth"); 
   var botao = document.querySelector(".botao").classList.toggle("botaoNigth"); 
   var body = document.querySelector(".corpo").classList.toggle("bodyNigth"); 
   var opcao = document.querySelector(".opcao").classList.toggle("opcaoNigth");
   var statusNigth = document.querySelector(".status").classList.toggle("statusNigth");
   var configsenha = document.querySelector(".config-senha").classList.toggle("config-senhaNigth");
}else{
   $desativadoNoturno.checked = true;
   var gallery = document.querySelector(".gallery").classList.remove("galleryNigth");
   var configuracoes = document.querySelector(".configuracoes").classList.remove("configuracoesNigth"); ;
   var menu = document.querySelector(".menu").classList.remove("menuNigth"); 
   var principal = document.querySelector(".principal").classList.remove("principalNigth"); 
   var botao = document.querySelector(".botao").classList.remove("botaoNigth"); 
   var body = document.querySelector(".corpo").classList.remove("bodyNigth"); 
   var opcao = document.querySelector(".opcao").classList.remove("opcaoNigth");
   var statusNigth = document.querySelector(".status").classList.remove("statusNigth");
   var configsenha = document.querySelector(".config-senha").classList.remove("config-senhaNigth");
}
}
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


 


