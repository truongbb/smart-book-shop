package vn.ntqsolution.smart_shop.service.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MailServiceImpl implements MailService {

  @Autowired
  private JavaMailSender javaMailSender;

//  @Value("${}")
//  private String senderEmail;

  @Override
  public void sendActivationEmail(String receiverEmail, String username, String activeToken) {
    SimpleMailMessage message = new SimpleMailMessage();
//    message.setFrom(senderEmail);
    message.setTo(receiverEmail);
    message.setSubject("Smart shop - Activation account email");
    message.setText("You've created account succcessfully! Click here to active your account: http://192.168.0.103:9870/smart_shop/account/active-account/" + username + "/" + activeToken);
    javaMailSender.send(message);
  }
}
