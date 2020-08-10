var $btnactpedido = document.querySelectorAll("#btn-act-pedido");
var $btnrempedido = document.querySelectorAll("#btn-rem-pedido");
var $msgconfirm = document.querySelectorAll("#msg-confirm");
var $bodyNotificacao =document.querySelector(".corpo");

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