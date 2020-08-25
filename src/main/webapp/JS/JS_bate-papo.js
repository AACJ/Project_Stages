var $bodyBatePapo = document.getElementById('corpo');
var $myMsg = document.getElementById('my-msg');
var $sendMsg = document.getElementById('send-msg');
var $msg = document.getElementById('msg');
var $chatBody = document.getElementById('chatBody');
var $emojiOpenBox = document.getElementById('emoji-open-box');
var $fechaBoxEmoji = document.getElementById('fecha-box-emoji');
var $boxEmojis = document.getElementById('box-emojis');
var $emojis = document.querySelectorAll(".emojis");
var $pesquisaConversa = document.getElementById("pesquisaConversa");

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

function scroll() {
	$chatBody.scrollTop = $chatBody.scrollHeight;
}

$sendMsg.addEventListener("click",function(){
	$.ajax({
	       url: '/projectstages_mvc/salvar/mensagem',
	       data:{idAmigo: $sendMsg.value, msg : $msg.value},
	       success : function(response){
	    	   
	       }
	   });
	$msg.value = ""; 
	scroll();
});

$emojiOpenBox.addEventListener("click",function(){
    $boxEmojis.style.display = "block";
    $fechaBoxEmoji.style.display = "block";
    
    $bodyBatePapo.addEventListener("click", function(e){
        if(!($emojiOpenBox.contains(e.target)) && !($boxEmojis.contains(e.target))){
            $fechaBoxEmoji.style.display = "none";
            $boxEmojis.style.display = "none";
        }
    });
});

$bodyBatePapo.addEventListener("click", function(e){
	for(var i = 0; i < $emojis.length ;i++){
		if($emojis[i].contains(e.target)){
			$.ajax({
			       url: '/projectstages_mvc/salvar/emoji',
			       data:{idAmigo: $sendMsg.value, emoji : $emojis[i].value},
			       success : function(response){
			    	   
			       }
			   });
		}
	}
});

function exibirMensagensDandoScroll(){
	$.ajax({
	       url: '/projectstages_mvc/retorna/mensagem',
	       success : function(response){
	    		   $chatBody.innerHTML = response;  
	    		   scroll();
	       }
	   });
}
exibirMensagensDandoScroll();

function modoNoturnoAtivadoBatePapo(ativado){
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
	   var menuchat = document.querySelector(".menu-chat").classList.toggle("menu-chatNigth");
	   var perfilchat = document.querySelectorAll(".menu-perfil-chat");
	   
	   for (var i = 0; i < perfilchat.length; i++) {
		   perfilchat[i].classList.toggle("menu-perfil-chatNigth");
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
	   var menuchat = document.querySelector(".menu-chat").classList.remove("menu-chatNigth");
	   var perfilchat = document.querySelectorAll(".menu-perfil-chat");
	  
	   for (var i = 0; i < perfilchat.length; i++) {
		   perfilchat[i].classList.remove("menu-perfil-chatNigth");
		 }
	   
	   }
}    


(function(){
	'use strict'; 
	
	$.ajax({
 url: '/projectstages_mvc/retorna/modo-noturno/bate-papo',
 success : function(data){
 	modoNoturnoAtivadoBatePapo(data);
 }
});
	
	exibirChats();
	
})();

function exibirMensagens(){
	$.ajax({
	       url: '/projectstages_mvc/retorna/mensagem',
	       success : function(response){
	    		   $chatBody.innerHTML = response;  
	       }
	   });
}

setInterval(exibirMensagens, 1000);

