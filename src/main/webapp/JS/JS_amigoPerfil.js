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
