package vn.ntqsolution.smart_shop.service.mail;

public interface MailService {

  void sendActivationEmail(String toEmail, String username, String activeToken);

}
