package fr.dawan.cfa2022.services;

import java.util.HashMap;
import java.util.Map;

import javax.mail.Message;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import fr.dawan.cfa2022.dto.UserDto;
import fr.dawan.cfa2022.tools.JwtTokenUtil;
import fr.dawan.cfa2022.tools.TokenSaver;

@Service
@Transactional
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private JavaMailSender emailSender;
	
	@Value("${frontapp.url}")
	private String frontAppUrl;
	
	@Override
	public void sendEmailForResetPwd(UserDto uDto) throws Exception{
		//générer un token
		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put("user_id", uDto.getId());
		claims.put("user_fullName", uDto.getFirstName() + " " + uDto.getLastName());
		claims.put("user_role", uDto.getRole().toString());
		
		String token = jwtTokenUtil.doGenerateToken(claims , uDto.getEmail());
		TokenSaver.tokensByEmail.put(uDto.getEmail(), token);
		
		//envoyer le token par email
		String resetLink= "<a href=\""+frontAppUrl+ "/#/fr/reset-password?token="+token+"\">Réinitialiser</a>";
		
		MimeMessage msg = emailSender.createMimeMessage();
		msg.setRecipients(Message.RecipientType.TO, uDto.getEmail());
		msg.setSubject("Réinitialisation du mot de passe");
		
		msg.setText("Bonjour " + uDto.getFirstName() + ","
				+"<br /> Ce message vous a été envoyé car vous avez oublié votre mot de passe.<br />"
				+ "Pour le réinitialiser, cliquez sur ce lien : " + resetLink, "UTF-8", "html");
		emailSender.send(msg);
	}

}