package de.iotacb.homepi2.gui;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import de.iotacb.cloud.core.gui.Button;
import de.iotacb.cloud.core.window.Window;
import de.iotacb.cloud.utilities.math.Vec;

public class ShopSendButton extends Button {

	List<String> items;
	
	public ShopSendButton(Window window, String title, Vec location, List<String> items) {
		super(window, title);
		
		this.items = items;
		this.location = location;
	}

	@Override
	public void click() {
        String username = "treyofficialmc@gmail.com";
//      String password = "jsbmssrhqyznxulj"; // windows
      String password = "hxmmxnshegvbkjey"; // linux

      Properties props = new Properties();
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.host", "smtp.gmail.com");
      props.put("mail.smtp.port", "587");

      Session session = Session.getInstance(props,
        new javax.mail.Authenticator() {
          protected PasswordAuthentication getPasswordAuthentication() {
              return new PasswordAuthentication(username, password);
          }
        });

      try {

          Message message = new MimeMessage(session);
          message.setFrom(new InternetAddress("treyofficialmc@gmail.com"));
          message.setRecipients(Message.RecipientType.TO,
              InternetAddress.parse("christopher.brandt09@gmail.com, Saivnach@gmail.com, summerangel90@gmail.com"));
          message.setSubject("Einkaufsliste");
          String msg = "";
          
          for (String item : items) {
          	msg += item + "\n";
          }
          
          message.setText(msg);

          Transport.send(message);

      } catch (MessagingException e) {
          throw new RuntimeException(e);
      }
	}

}
