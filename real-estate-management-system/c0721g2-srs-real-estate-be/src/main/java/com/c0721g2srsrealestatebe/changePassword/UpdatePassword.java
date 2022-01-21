//package com.c0721g2srsrealestatebe.changePassword;
//
//import com.c0721g2srsrealestatebe.dto.AppUserDTO;
//import com.c0721g2srsrealestatebe.model.account.AppUser;
//import com.c0721g2srsrealestatebe.service.account.IAppUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//@CrossOrigin(value = "*", allowedHeaders = "*")
//@RestController
//@RequestMapping("/account")
//public class UpdatePassword {
//    @Autowired
//    IAppUserService iAppUserService;
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    @GetMapping("/userName/{userName}")
//    public ResponseEntity<AppUser> update(@PathVariable("userName") String userName) {
//        try {
//            AppUser appUser = iAppUserService.findAppUserByUserName(userName);
//
//            System.out.println(userName);
//            return new ResponseEntity<>(appUser, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//    }
//
//    @PatchMapping(value = "/password")
//    public ResponseEntity<Object> update(@Valid @RequestBody AppUserDTO appUserDTO,
//             BindingResult bindingResult) {
//        try {
//            AppUser appUser1 = iAppUserService.findAppUserByUserName(appUserDTO.getUsername());
//            new AppUserDTO().validate(appUserDTO, bindingResult);
//            if (bindingResult.hasFieldErrors("password")) {
//                System.out.println("mật nhập không đúng form");
//                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//            }
//            System.out.println(appUserDTO.toString());
//            if (passwordEncoder.matches(
//                    appUserDTO.getPassword(),appUser1.getPassword())
//                    && !appUserDTO.getNewPassword().equals(appUserDTO.getPassword())
//                    && appUserDTO.getNewPassword().equals(appUserDTO.getReNewPassword())) {
//
//            appUserDTO.setPassword(passwordEncoder.encode(appUserDTO.getNewPassword()));
//                System.out.println("chưa lưu");
//                System.out.println(appUserDTO.toString());
//                iAppUserService.updatePassword(appUserDTO);
//                System.out.println("đã lưu");
//                return new ResponseEntity<>(HttpStatus.OK);
//            } else {
//                System.out.println("nhập sai password");
//                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//            }
//
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.OK);
//        }
//    }
//
//    @PostMapping("/save")
//    public ResponseEntity<AppUser> update(@RequestBody AppUserDTO password) {
//        try {
//            iAppUserService.updatePassword(password);
//            return new ResponseEntity<>(HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//    }
//}
