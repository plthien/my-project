package com.c0721g2srsrealestatebe.service.account.impl;

import com.c0721g2srsrealestatebe.Exception.AppUserException;
import com.c0721g2srsrealestatebe.dto.AppUserDTO;
import com.c0721g2srsrealestatebe.model.account.AppUser;
import com.c0721g2srsrealestatebe.model.account.Role;
import com.c0721g2srsrealestatebe.model.customer.Customer;
import com.c0721g2srsrealestatebe.model.employee.Employee;
import com.c0721g2srsrealestatebe.model.image.Image;
import com.c0721g2srsrealestatebe.payload.request.CustomerSocial;
import com.c0721g2srsrealestatebe.repository.account.IAppUserRepository;
import com.c0721g2srsrealestatebe.service.account.IAppUserService;
import com.c0721g2srsrealestatebe.service.customer.impl.CustomerServiceImpl;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class AppUserServiceImpl implements IAppUserService {

    @Autowired
    private IAppUserRepository appUserRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private RoleServiceImpl roleService;

    @Autowired
    private CustomerServiceImpl customerService;


    @Override
    public AppUser getAppUserByEmail(String email) {
        return appUserRepository.getAppUserByEmailCustomer(email);
    }

    @Override
    public boolean existsUserByEmail(String email) {
        return appUserRepository.existsUserByEmail(email) != null;
    }

    @Override
    public void addVerificationCode(String email) throws MessagingException, UnsupportedEncodingException {
        String username = appUserRepository.existsUserByEmail(email);
        String code = RandomString.make(64);
        appUserRepository.addVerificationCode(code, username);
        this.sendVerificationEmailForResetPassWord(username, code, email);
        CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.MINUTES.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IllegalStateException(e);
            }
            appUserRepository.deleteVerificationCode(username);
        });
    }

    @Override
    public void saveNewPassword(String passwordEncode, String code) {
        appUserRepository.saveNewPassword(passwordEncode, code);
        appUserRepository.deleteVerificationCode(code);
    }

    @Override
    public Boolean findUserByVerificationCode(String code) {
        return appUserRepository.findUserByVerificationCode(code) != null;
    }

    @Override
    public AppUser createCustomerSocial(CustomerSocial customerSocial) {
        Customer customer = new Customer();
        customer.setName(customerSocial.getName());
        customer.setEmail(customerSocial.getEmail());

        Image image = new Image();
        image.setUrl(customerSocial.getUrlImg());
        customer.setImage(image);

        AppUser appUser = new AppUser();
        appUser.setUsername(customerSocial.getEmail());
        appUser.setPassword(customerSocial.getPassword());
        appUser.setEnabled(true);
        Set<Role> roles = new HashSet<>();
        Role role = roleService.findRoleByName("ROLE_CUSTOMER");
        roles.add(role);

        appUser.setRoles(roles);

        customer.setAppUser(appUser);
        this.customerService.saveCustomerSocial(customer);
        return appUser;
    }


    public void sendVerificationEmailForResetPassWord(String userName, String randomCode, String email) throws MessagingException, UnsupportedEncodingException {
        String subject = "Email xác thực!";
        String mailContent = "";
        String confirmUrl = "http://localhost:4200/security/verify-reset-password?code=" + randomCode;


        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
        helper.setFrom("plthienbkdn@gmail.com", "Bất động sản Hưng Thịnh Group");
        helper.setTo(email);
        helper.setSubject(subject);
        mailContent = "<p style='color:red;font-size: 20px'>Xin chào " + userName + " ,<p>" + "<p style='font-size: 16px'> Nhấn vào link sau để thay đổi mật khẩu của bạn:</p>" +
                "<h3><a href='" + confirmUrl + "'>Link Xác thực( nhấn vào đây)!</a></h3>" +
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
        javaMailSender.send(message);

    }

    // Tùng kiểm tra username
    public boolean existsByUserName(String username) {
        return appUserRepository.existsByUsername(username);
    }


    @Override
    public String findPasswordByUsername(String username) {
        return appUserRepository.findPasswordByUsername(username);
    }


    @Override
    public void updatePassword(AppUserDTO appUserDTO) {
        AppUser appUser = this.findAppUserByUserName(appUserDTO.getUsernameChange());
        appUser.setPassword(appUserDTO.getPassword());
        appUserRepository.save(appUser);
//        appUserRepository.saveNewPassword(appUserDTO.getPassword(), appUserDTO.getUsernameChange());
    }

    @Override
    public AppUser findAppUserByUserName(String id) {
        return appUserRepository.findAppUserByUsername(id).orElseThrow(() -> new AppUserException(
                "không thể tìm thấy id " + id + ""));
    }

    @Override
    public AppUser getAppUserByEmployee(String id) {
        return appUserRepository.getAppUserByEmployee(id);
    }

}