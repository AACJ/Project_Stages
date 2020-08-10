var $bodyBatePapo = document.getElementById('corpo');
var $myMsg = document.getElementById('my-msg');
var $sendMsg = document.getElementById('send-msg');
var $msg = document.getElementById('msg');
var $chatBody = document.getElementById('chatBody');
var $emojiOpenBox = document.getElementById('emoji-open-box');
var $fechaBoxEmoji = document.getElementById('fecha-box-emoji');
var $boxEmojis = document.getElementById('box-emojis');
var $emojis = document.querySelectorAll(".emojis");

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

function exibirMensagens(){
	$.ajax({
	       url: '/projectstages_mvc/retorna/mensagem',
	       success : function(response){
	    		   $chatBody.innerHTML = response;  
	       }
	   });
}

setInterval(exibirMensagens, 1000);