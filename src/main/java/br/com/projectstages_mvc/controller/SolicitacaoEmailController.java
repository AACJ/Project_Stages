package br.com.projectstages_mvc.controller;


import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.projectstages_mvc.dao.CadastroDao;
import br.com.projectstages_mvc.model.Usuario;

@Controller
@Transactional
public class SolicitacaoEmailController {

	@Autowired
	private CadastroDao cadastroDao;
	
	@RequestMapping("/solicitacao-email")
	public ModelAndView solicitacaoEmail(){
		ModelAndView model = new ModelAndView("solicitacao-email");
		return model;
	}
	
	@RequestMapping(value = "/solicitacao-email/email-enviado", method = RequestMethod.GET)
	public String emailEnviado(@RequestParam String email) throws AddressException, MessagingException {
		
		if(cadastroDao.usuarioExistente(email)) {
			
		Usuario usuario = cadastroDao.findUsuario(email);	
			
		Properties props = new Properties();
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.starttls.enable", "true");
		   props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.port", "587");
		   
		   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		      protected PasswordAuthentication getPasswordAuthentication() {
		         return new PasswordAuthentication("seuEmail@gmail.com", "suasenha");
		      }
		   });
		   
		   Message msg = new MimeMessage(session);
		   msg.setFrom(new InternetAddress("seuEmail@gmail.com", false));
		   msg.setHeader("Content-Type", "text/html");
		   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
		   msg.setSubject("Redefinição de senha");
  	
		   String html ="<!doctype html>\r\n" +  
		   		"<html lang=\"pt-br\">\r\n" + 
		   		"    <head>\r\n" + 
		   		"        <meta charset=\"utf-8\">\r\n" + 
		   		"        <meta name=\"author\" content=\"Affonso Ruiz, Aline Alves, Carlos Felipe, Joyce Brandão\">\r\n" + 
		   		"        <meta name=\"description\" content=\"Email de alteração de senha\">\r\n" + 
		   		"        <meta name=\"keywords\" content=\"Project Stages\">\r\n" + 
		   		"        \r\n" + 
		   		"<style>\r\n" + 
		   		"    \r\n" + 
		   		"    .header-email{\r\n" + 
		   		"        display: block;\r\n" + 
		   		"        color: white;\r\n" + 
		   		"        background-color: rgb(59, 168, 248);\r\n" + 
		   		"        padding: 5px;\r\n" + 
		   		"        padding-left: 20px;\r\n" + 
		   		"        padding-right: 20px;\r\n" + 
		   		"        margin: 0px;\r\n" + 
		   		"        font-family: Arial, sans-serif,serif;\r\n" + 
		   		"        text-align: justify;\r\n" + 
		   		"    }\r\n" + 
		   		"    \r\n" + 
		   		"    .img-email{\r\n" + 
		   		"        text-align: center;\r\n" + 
		   		"        margin-bottom: 30px;\r\n" + 
		   		"    }"+
		   		"    \r\n" + 
		   		"    .body-email{\r\n" + 
		   		"        display: block;\r\n" + 
		   		"        text-align: center;\r\n" + 
		   		"        color: white;\r\n" + 
		   		"        margin-top: 50px;\r\n" + 
		   		"        padding-bottom: 50px;\r\n" + 
		   		"        border-bottom: 1px solid black;\r\n" + 
		   		"        font-family: Arial,sans-serif,serif;\r\n" + 
		   		"    }\r\n" + 
		   		"    \r\n" + 
		   		"    .alterar-senha{\r\n" + 
		   		"        width: 200px;\r\n" + 
		   		"        padding: 3px;\r\n" + 
		   		"        color:white;\r\n" + 
		   		"        background-color: rgb(59, 168, 248);\r\n" + 
		   		"        border-radius: 10px; "+
		   		"        border: 1px solid white;\r\n" + 
		   		"        margin: 0px;\r\n" + 
		   		"    }\r\n" + 
		   		"    \r\n" + 
		   		"</style>\r\n" + 
		   		"    </head>\r\n" + 
		   		"    <body>\r\n" + 
		   		"       \r\n" + 
		   		"        <div class=\"header-email\">\r\n" + 
		   		"            <p>Caro "+ usuario.getUserName() +",</p>\r\n" + 
		   		"            <p>\r\n" + 
		   		"            Foi solicitado um pedido para redefinição de senha. Por favor clique no botão abaixo para você ser levado a pagina de redefinição de senha. Caso não seja você que fez a solicitação do email apenas ignore este email que lhe foi enviado.\r\n" + 
		   		"            </p>\r\n" + 
		   		"        </div>\r\n" + 
		   		"        \r\n" + 
		   		"        <div class=\"body-email\">\r\n" + 
		   		"        <form action='http://localhost:8080/projectstages_mvc/alterar-senha' method=\"post\">\r\n" + 
		   		"            <button type=\"submit\" name=\"email\" value='" + email + "' class=\"alterar-senha\"><p><b>Clique aqui para redefinir senha</b></p></button>\r\n" + 
		        "         <input type=\"hidden\" name=\"${_csrf.parameterName}\" value=\"${_csrf.token}\" />" +
		   		"        </form>\r\n" + 
		   		"        </div>\r\n" + 
		   		"        \r\n" + 
		   		"        <p>Atenciosamente,</p>\r\n" + 
		   		"        <p>Project Stages</p>\r\n" + 
		   		"        \r\n" + 
		   		"    </body>\r\n" + 
		   		"</html>";
		   
		   msg.setContent(html, "text/html");
		   msg.setSentDate(new Date());
		   
		   Transport.send(msg);
		   
		return "redirect:/email-enviado?email=" + email;
		}
		
		return "redirect:/email-nao-enviado?email=" + email;
	}
	
	
}
