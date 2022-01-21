package com.c0721g2srsrealestatebe.service.realestatenews.impl;

import com.c0721g2srsrealestatebe.service.realestatenews.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendSimpleMessage(String customerEmail, String name, String phone) throws MessagingException, UnsupportedEncodingException {
        String mailContent = "";
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
        helper.setFrom("plthienbkdn@gmail.com", "Bất động sản Hưng Thịnh Group");
        helper.setTo(customerEmail);
        helper.setSubject("Một khách hàng quan tâm tới bài đăng của bạn");
        mailContent = "<p> Chào bạn!</p>\n" +
                "<p>Khách hàng " + name + " số điện thoại " + phone + " đang quan tâm đến bài đăng của bạn trên trang \n" +
                "hungthinhgroup.com.</p>\n" +
                "<p>Thanks and Regards</p>\n" +
                "<hr>" +
                "<div style=\"text-size-adjust: none !important; -ms-text-size-adjust: none !important; -webkit-text-size-adjust: none !important;\"><span style=\"margin: 0px; padding: 0px; line-height: 100%; display: block; font-family: Helvetica, Arial, sans-serif;\"> </span><span style=\"margin:0; padding:0; font-family: Helvetica, Arial, sans-serif; font-size: 15px; line-height:20px; color: #212121; display:block;\">\n" +
                "        <span style=\"font-weight: bold; color: rgb(33, 33, 33); font-family: Helvetica, Arial, sans-serif; display: inline;\">Phan Lê Thanh Hiền</span><span style=\"display: inline; color: rgb(33, 33, 33); font-family: Helvetica, Arial, sans-serif;\"> / </span><span style=\"color: rgb(33, 33, 33); font-family: Helvetica, Arial, sans-serif; display: inline;\">Chuyên viên</span><span style=\"display: inline; font-family: Helvetica, Arial, sans-serif;\"><br></span><a href=\"mailto:plthienbkdn@gmail.com\"\n" +
                "           style=\"color: rgb(71, 124, 204); text-decoration: none; display: inline;\">plthienbkdn@gmail.com</a><span\n" +
                "                style=\"display: inline; color: rgb(33, 33, 33); font-family: Helvetica, Arial, sans-serif;\"> / </span><span\n" +
                "                style=\"color: rgb(33, 33, 33); font-family: Helvetica, Arial, sans-serif; display: inline;\">0794197483</span></span>\n" +
                "    <p style=\"margin:0; padding:0; line-height:20px; display:block;width:100%; font-size:1;\"><img src=\"https://s3.amazonaws.com/htmlsig-assets/spacer.gif\" width=\"508\" height=\"10\" style=\"display: block; width: 100%; height: 5px;\">\n" +
                "    </p><span style=\"margin: 0px; padding: 0px; font-family: Helvetica, Arial, sans-serif; font-size: 15px; line-height: 25px; display: block;\"> <span\n" +
                "            style=\"font-weight: bold; color: rgb(33, 33, 33); font-family: Helvetica, Arial, sans-serif; display: inline;\">Bất Động Sản C0721-G2 Group</span> <span\n" +
                "            style=\"display: inline; font-family: Helvetica, Arial, sans-serif;\"><br></span> <span\n" +
                "            style=\"color: rgb(33, 33, 33); display: inline; font-family: Helvetica, Arial, sans-serif;\">Office: </span> <span\n" +
                "            style=\"color: rgb(33, 33, 33); font-family: Helvetica, Arial, sans-serif; display: inline;\">(0292) 3 837 838</span> <span\n" +
                "            style=\"color: rgb(33, 33, 33); display: inline; font-family: Helvetica, Arial, sans-serif;\"> / </span><span\n" +
                "            style=\"color: rgb(33, 33, 33); display: inline; font-family: Helvetica, Arial, sans-serif;\">Fax: </span> <span\n" +
                "            style=\"color: rgb(33, 33, 33); font-family: Helvetica, Arial, sans-serif; display: inline;\">999-9999-999</span> <span\n" +
                "            style=\"display: inline; font-family: Helvetica, Arial, sans-serif;\"><br></span> <span\n" +
                "            style=\"color: rgb(33, 33, 33); font-family: Helvetica, Arial, sans-serif; display: inline;\">Chi nhánh Đà Nẵng Tầng 9, tòa nhà Vĩnh Trung Plaza, số 255 – 257 Hùng Vương, phường Vĩnh Trung, quận Thanh Khê, TP. Đà Nẵng</span> <span\n" +
                "            style=\"display: inline; font-family: Helvetica, Arial, sans-serif;\"><br></span> <span\n" +
                "            style=\"color: rgb(33, 33, 33); font-family: Helvetica, Arial, sans-serif; display: inline;\">Chi nhánh TP. Hồ Chí Minh Tầng 3, Tòa nhà Viettel Complex, 285 Cách Mạng Tháng Tám, Phường 12, Quận 10, TP. Hồ Chí Minh</span> <span\n" +
                "            style=\"display: inline; font-family: Helvetica, Arial, sans-serif;\"><br></span> <span\n" +
                "            style=\"display: block; color: rgb(33, 33, 33); font-family: Helvetica, Arial, sans-serif;\"> <a\n" +
                "            href=\"http://localhost:4200/\" style=\"color: rgb(71, 124, 204); text-decoration: none; display: inline;\">http://localhost:4200/home</a></span> <p\n" +
                "            style=\"margin:0; padding:0; line-height:18px; display:block;width:100%; font-size:1;\"> <img\n" +
                "            src=\"https://s3.amazonaws.com/htmlsig-assets/spacer.gif\" width=\"508\" height=\"10\"\n" +
                "            style=\"display: block; width: 100%; height: 5px;\">\n" +
                "</p> </span> <span\n" +
                "        style=\"margin: 0px; padding: 0px; line-height: 100%; font-size: 1px; display: block; font-family: Helvetica, Arial, sans-serif;\"> <a\n" +
                "        style=\"text-decoration: none; display: inline;\" href=\"https://htmlsig.com/t/000001H25Y6K\"><img width=\"25\"\n" +
                "                                                                                                       style=\"margin-bottom:2px; border:none; display:inline;\"\n" +
                "                                                                                                       height=\"25\"\n" +
                "                                                                                                       data-filename=\"twitter.png\"\n" +
                "                                                                                                       src=\"https://s3.amazonaws.com/htmlsig-assets/round/twitter.png\"\n" +
                "                                                                                                       alt=\"Twitter\"></a> <span\n" +
                "        style=\"white-space: nowrap; font-family: Helvetica, Arial, sans-serif; display: inline;\"> <img\n" +
                "        src=\"https://s3.amazonaws.com/htmlsig-assets/spacer.gif\" width=\"2\"> </span> <a\n" +
                "        style=\"text-decoration: none; display: inline;\" href=\"https://htmlsig.com/t/000001H1N6GV\"><img width=\"25\"\n" +
                "                                                                                                       style=\"margin-bottom:2px; border:none; display:inline;\"\n" +
                "                                                                                                       height=\"25\"\n" +
                "                                                                                                       data-filename=\"facebook.png\"\n" +
                "                                                                                                       src=\"https://s3.amazonaws.com/htmlsig-assets/round/facebook.png\"\n" +
                "                                                                                                       alt=\"Facebook\"></a> <span\n" +
                "        style=\"white-space: nowrap; font-family: Helvetica, Arial, sans-serif; display: inline;\"> <img\n" +
                "        src=\"https://s3.amazonaws.com/htmlsig-assets/spacer.gif\" width=\"2\"> </span> <a\n" +
                "        style=\"text-decoration: none; display: inline;\" href=\"https://htmlsig.com/t/000001H2J2VT\"><img width=\"25\"\n" +
                "                                                                                                       style=\"margin-bottom:2px; border:none; display:inline;\"\n" +
                "                                                                                                       height=\"25\"\n" +
                "                                                                                                       data-filename=\"linkedin.png\"\n" +
                "                                                                                                       src=\"https://s3.amazonaws.com/htmlsig-assets/round/linkedin.png\"\n" +
                "                                                                                                       alt=\"LinkedIn\"></a> <span\n" +
                "        style=\"white-space: nowrap; font-family: Helvetica, Arial, sans-serif; display: inline;\"> <img\n" +
                "        src=\"https://s3.amazonaws.com/htmlsig-assets/spacer.gif\" width=\"2\"> </span> <a\n" +
                "        style=\"text-decoration: none; display: inline;\" href=\"https://htmlsig.com/t/000001H13TNJ\"><img width=\"25\"\n" +
                "                                                                                                       style=\"margin-bottom:2px; border:none; display:inline;\"\n" +
                "                                                                                                       height=\"25\"\n" +
                "                                                                                                       data-filename=\"youtube.png\"\n" +
                "                                                                                                       src=\"https://s3.amazonaws.com/htmlsig-assets/round/youtube.png\"\n" +
                "                                                                                                       alt=\"Youtube\"></a>\n" +
                "</span>\n" +
                "</div>";
        helper.setText(mailContent, true);
        emailSender.send(message);
    }
}
