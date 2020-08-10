    var $form;
    var $nome; 
    var $userName;
    var $email;
    var $senha;
    var $confSenha;
    var $termos;
    var $btn;
    var $msg

(function(){
    'use strict'; 
    $form = document.querySelector('form');
    $nome = document.getElementById('txtNome');
    $userName = document.getElementById('txtNomeUsuario');
    $email = document.getElementById('txtEmail');
    $senha = document.getElementById('txtSenha');
    $confSenha = document.getElementById('txtConfirmarSenha');
    $termos = document.getElementById('chk');
    $btn = document.getElementById('btn');
    
    $form.addEventListener('submit', function(e){
           if($nome.value == "" || $userName == "" || $email == "" || $senha == "" || $confSenha == ""){
                alert("Preencha todos os campos!");
                e.preventDefault();
           }
        
           if(!$termos.checked){
               alert("Aceite os termos de uso!");
               e.preventDefault();
           }
        
           if($senha.value != $confSenha.value){
               alert("Erro na confirmação de senha!");
               e.preventDefault();
              }
    
    });   

})()