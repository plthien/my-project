package com.c0721g2srsrealestatebe.service.realestatenews.impl;

import com.c0721g2srsrealestatebe.service.realestatenews.IApprovalMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class ApprovalMailServiceImpl implements IApprovalMailService {
    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendApprovalMail(String customerEmail, String status, String reason) throws MessagingException, UnsupportedEncodingException {
        String mailContent = "";
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
        helper.setFrom("plthienbkdn@gmail.com", "Bất động sản Hưng Thịnh Group");
        helper.setTo(customerEmail);
        helper.setSubject("Thông báo phê duyệt Bài đăng");
        mailContent = "<h3><p>Chào bạn </p></h3> \n" +
                "<h4>Bài đăng của bạn :</h4> " + status + " " + reason + "\n" +
                "<p>Thanks and Regards</p>" +
                "<hr>" +
                " <div style=' text-size-adjust: none !important; -ms-text-size-adjust: none !important; -webkit-text-size-adjust: none !important; '> " +
                " <span style=' margin: 0px; padding: 0px; line-height: 100%; display: block; font-family: Helvetica, Arial, sans-serif;' > </span> " +
                " <span style=' margin:0; padding:0; font-family: Helvetica, Arial, sans-serif; font-size: 15px; line-height:20px; color: #212121; display:block;' > " +
                " <span style=' font-weight: bold; color: rgb(33, 33, 33); font-family: Helvetica, Arial, sans-serif; display: inline; '>Phan Lê Thanh Hiền</span> " +
                " <span style=' display: inline; color: rgb(33, 33, 33); font-family: Helvetica, Arial, sans-serif; '> /  </span>" +
                " <span style=' color: rgb(33, 33, 33); font-family: Helvetica, Arial, sans-serif; display: inline;' >Chuyên viên</span>" +
                " <span style=' display: inline; font-family: Helvetica, Arial, sans-serif;' ><br></span>" +
                " <a href='mailto:plthienbkdn@gmail.com' " +
                " style=' color: rgb(71, 124, 204); text-decoration: none; display: inline;' >plthienbkdn@gmail.com</a>" +
                "<span style='display: inline; color: rgb(33, 33, 33); font-family: Helvetica, Arial, sans-serif;' > / </span>" +
                "<span style=' color: rgb(33, 33, 33); font-family: Helvetica, Arial, sans-serif; display: inline;' >0794197483</span>" +
                "</span> " +
                " <p style=' margin:0; padding:0; line-height:20px; display:block;width:100%; font-size:1;'><img src='https://s3.amazonaws.com/htmlsig-assets/spacer.gif' width=508 height=10 style='display: block; width: 100%; height: 5px;'></p>" +
                " <span style=' margin: 0px; padding: 0px; font-family: Helvetica, Arial, sans-serif; font-size: 15px; line-height: 25px; display: block;'>" +
                " <span style =' font-weight: bold; color: rgb(33, 33, 33); font-family: Helvetica, Arial, sans-serif; display: inline;' > Bất Động Sản C0721 - G2 Group</span >" +
                " <span style =' display: inline; font-family: Helvetica, Arial, sans-serif;' ><br ></span >" +
                " <span style =' color: rgb(33, 33, 33); display: inline; font-family: Helvetica, Arial, sans-serif;' > Office: </span>" +
                " <span style =' color: rgb(33, 33, 33); font-family: Helvetica, Arial, sans-serif; display: inline;' > (0292) 3 837 838 </span>" +
                " <span style = 'color: rgb(33, 33, 33); display: inline; font-family: Helvetica, Arial, sans-serif;' > / </span>" +
                " <span style = 'color: rgb(33, 33, 33); display: inline; font-family: Helvetica, Arial, sans-serif;' > Fax: </span >" +
                " <span style = 'color: rgb(33, 33, 33); font-family: Helvetica, Arial, sans-serif; display: inline;' > 999 - 9999 - 999 </span>" +
                " <span style = 'display: inline; font-family: Helvetica, Arial, sans-serif;' ><br ></span > " +
                " <span style = 'color: rgb(33, 33, 33); font-family: Helvetica, Arial, sans-serif; display: inline;' > Chi nhánh Đà Nẵng Tầng 9, tòa nhà Vĩnh Trung Plaza, số 255 –257 Hùng Vương, phường Vĩnh Trung, quận Thanh Khê, TP. Đà Nẵng</span > " +
                " <span style = 'display: inline; font-family: Helvetica, Arial, sans-serif;' ><br ></span >" +
                " <span style = 'color: rgb(33, 33, 33); font-family: Helvetica, Arial, sans-serif; display: inline;' > Chi nhánh TP.Hồ Chí Minh Tầng 3, Tòa nhà Viettel Complex, 285 Cách Mạng Tháng Tám, Phường 12, Quận 10, TP.Hồ Chí Minh</span >" +
                " <span style = 'display: inline; font-family: Helvetica, Arial, sans-serif;' ><br ></span >" +
                " <span style = 'display: block; color: rgb(33, 33, 33); font-family: Helvetica, Arial, sans-serif;' > <a href = 'http://localhost:4200/' style = 'color: rgb(71, 124, 204); text-decoration: none; display: inline;' > http://localhost:4200/home/</a></span>" +
                " </div >" ;
        helper.setText(mailContent, true);
        emailSender.send(message);
    }

}
