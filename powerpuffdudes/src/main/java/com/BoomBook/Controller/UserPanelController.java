package com.BoomBook.Controller;


import com.BoomBook.DAO.*;
import com.BoomBook.Exceptions.AvgRate;
import com.BoomBook.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserPanelController {
    CustomerDAO customerDAO;
    BookDAO bookDAO;
    PurchaseRequestDAO purchaseRequestDAO;
    InCargoDAO inCargoDAO;
    PaymentServiceDAO paymentServiceDAO;
    MailDAO mailDAO;
    UpdateUserStatus updateUserStatus;

    @Autowired
    public UserPanelController(CustomerDAO customerDAO, BookDAO bookDAO, PurchaseRequestDAO purchaseRequestDAO, InCargoDAO inCargoDAO, PaymentServiceDAO paymentServiceDAO, MailDAO mailDAO) {
        this.customerDAO = customerDAO;
        this.bookDAO = bookDAO;
        this.purchaseRequestDAO = purchaseRequestDAO;
        this.inCargoDAO = inCargoDAO;
        this.paymentServiceDAO = paymentServiceDAO;
        this.mailDAO = mailDAO;
        this.updateUserStatus = new UpdateUserStatus();
    }

    @GetMapping("/profile")
    public String userprofile(Model model,HttpSession session){

        UserAuthantication userAuthantication = (UserAuthantication) session.getAttribute("username");

        if (!userAuthantication.isLoggedin()){
            session.setAttribute("username",userAuthantication);
            return "redirect:/home/boombook";
        }

        UpdateUserInfoForm updateUserInfoForm = new UpdateUserInfoForm();
        UserUpdatePasswordForm userUpdatePasswordForm = new UserUpdatePasswordForm();
        List<Customer> customers = customerDAO.findByEmail(userAuthantication.getUserEmail());
        Customer customer = customers.get(0);
        updateUserInfoForm.setName(customer.getCustomerName());
        updateUserInfoForm.setAddress(customer.getAddress());
        updateUserInfoForm.setPhone(customer.getPhone());
        model.addAttribute("updateUserInfoForm",updateUserInfoForm);
        model.addAttribute("userUpdatePasswordForm",userUpdatePasswordForm);

        if(!updateUserStatus.isComeFromManage() && !updateUserStatus.isComeFromPassword()){

            updateUserStatus.setAllFalse();
            model.addAttribute("currentManageStatus",updateUserStatus);
            session.setAttribute("username",userAuthantication);
            return "user/profile";
        }
        else if (updateUserStatus.isComeFromManage()){

            if (updateUserStatus.isManageInvalidField()){

                model.addAttribute("currentManageStatus",updateUserStatus);
                updateUserStatus.setComeFromManage(false);
                session.setAttribute("username",userAuthantication);
                return "user/profile";
            }

            else{
                model.addAttribute("currentManageStatus",updateUserStatus);
                updateUserStatus.setComeFromManage(false);
                session.setAttribute("username",userAuthantication);
                return "user/profile";
            }
        }

        else{
            model.addAttribute("currentManageStatus",updateUserStatus);
            updateUserStatus.setComeFromPassword(false);
            session.setAttribute("username",userAuthantication);
            return "user/profile";
        }
    }

    @PostMapping("/updateInformation")
    public String updateUserInfo(@ModelAttribute UpdateUserInfoForm updateUserInfoForm,Model model,HttpSession session){

        String inputtedName = updateUserInfoForm.getName();
        String inputtedPhone = updateUserInfoForm.getPhone();
        String inputtedAddress = updateUserInfoForm.getAddress();

        String plainPhone = inputtedPhone.replaceAll("\\s+","");
        String plaintName =  inputtedName.replaceAll("\\s+","");

        if(!(plaintName.length() != 0 && plaintName.matches("^[a-zA-Z]*$"))){

            updateUserStatus.setAllFalse();
            updateUserStatus.setComeFromManage(true);
            updateUserStatus.setManageInvalidField(true);
            return "redirect:/user/profile";
        }
        else if (!(plainPhone.length() != 0 && plainPhone.matches("[0-9]+"))){

            updateUserStatus.setAllFalse();
            updateUserStatus.setComeFromManage(true);
            updateUserStatus.setManageInvalidField(true);
            return "redirect:/user/profile";
        }else if (inputtedAddress.length() < 5){
            updateUserStatus.setAllFalse();
            updateUserStatus.setComeFromManage(true);
            updateUserStatus.setManageInvalidField(true);
            return "redirect:/user/profile";
        }
        else {

            UserAuthantication userAuthantication = (UserAuthantication) session.getAttribute("username");
            List<Customer> customers = customerDAO.findByEmail(userAuthantication.getUserEmail());
            Customer customer = customers.get(0);
            customer.setPhone(inputtedPhone);
            customer.setCustomerName(inputtedName);
            customer.setAddress(inputtedAddress);
            customerDAO.save(customer);
            updateUserStatus.setAllFalse();
            updateUserStatus.setComeFromManage(true);
            updateUserStatus.setManageSuccess(true);
            session.setAttribute("username",userAuthantication);
            return "redirect:/user/profile";
        }

    }


    @PostMapping("/updatePassword")
    public String updatePassword(@ModelAttribute UserUpdatePasswordForm userUpdatePasswordForm,Model model,HttpSession session) throws NoSuchAlgorithmException {

        String inputtedCurrentPassword = userUpdatePasswordForm.getCurrentPassword();
        String inputtedNewPassword = userUpdatePasswordForm.getNewPassword();
        String inputtedNewPasswordRepeat = userUpdatePasswordForm.getNewPasswordRepeat();

        if(inputtedCurrentPassword.contains(" ")  || inputtedNewPassword.contains(" ") || inputtedNewPasswordRepeat.contains(" ")){

            updateUserStatus.setAllFalse();
            updateUserStatus.setComeFromPassword(true);
            updateUserStatus.setPasswordInvalidField(true);
            return "redirect:/user/profile";
        }

        UserAuthantication userAuthantication = (UserAuthantication) session.getAttribute("username");
        List<Customer> customers = customerDAO.findByEmail(userAuthantication.getUserEmail());
        Customer customer = customers.get(0);
        SHA256 sha256 = new SHA256();
        String hashed_current_inputted_password =  sha256.toHexString(sha256.getSHA(inputtedCurrentPassword));
        if (hashed_current_inputted_password.equals(customer.getCustomerPassword())){

            String hashed_new_password =  sha256.toHexString(sha256.getSHA(inputtedNewPassword));
            customer.setCustomerPassword(hashed_new_password);
            customerDAO.save(customer);
            Customer deneme = customerDAO.findByEmail(userAuthantication.getUserEmail()).get(0);
            updateUserStatus.setAllFalse();
            updateUserStatus.setComeFromPassword(true);
            updateUserStatus.setPasswordSuccess(true);
            session.setAttribute("username",userAuthantication);
            return "redirect:/user/profile";
        }
        else {
            // current password mismatch
            updateUserStatus.setAllFalse();
            updateUserStatus.setComeFromPassword(true);
            updateUserStatus.setPasswordWrongPassword(true);
            session.setAttribute("username",userAuthantication);
            return "redirect:/user/profile";
        }
    }





    @GetMapping("/currentDeliveries")
    public String currentDeliveries (Model model,HttpSession session){

        UserAuthantication userAuthantication = (UserAuthantication) session.getAttribute("username");
        Customer customer = customerDAO.findByEmail(userAuthantication.getUserEmail()).get(0);
        List<PurchaseRequest> purchaseRequests = purchaseRequestDAO.findPurchaseRequestByCustomerId(customer.getId());
        List<Float> arrivedDeliveryPrice = new ArrayList<>();

        List<PurchaseRequest> arrivedDeliveries = new ArrayList<>();

        if (!purchaseRequests.isEmpty()) {
            for (PurchaseRequest p : purchaseRequests) {
                if (p.getInCargo() != null){
                    if (p.getInCargo().getArrivalDate().isAfter(LocalDateTime.now())){
                        arrivedDeliveries.add(p);
                        float count = 0;
                        for (Cart c : p.getCarts()){
                            count+= (c.getBookId().getPriceWithCampaign()) * c.getCount();
                        }
                        arrivedDeliveryPrice.add(count);
                    }

                }

            }
        }

        model.addAttribute("currentDeliveries",arrivedDeliveries);
        model.addAttribute("currentDeliveryPrize",arrivedDeliveryPrice);
        session.setAttribute("username",userAuthantication);

        return "user/current-orders";
    }

    @GetMapping("/deliveryHistory")
    public String oldDeliveries(Model model,HttpSession session){


        UserAuthantication userAuthantication = (UserAuthantication) session.getAttribute("username");
        Customer customer = customerDAO.findByEmail(userAuthantication.getUserEmail()).get(0);
        List<PurchaseRequest> purchaseRequests = purchaseRequestDAO.findPurchaseRequestByCustomerId(customer.getId());
        List<Float> arrivedDeliveryPrice = new ArrayList<>();

        List<PurchaseRequest> arrivedDeliveries = new ArrayList<>();

        if (!purchaseRequests.isEmpty()) {
            for (PurchaseRequest p : purchaseRequests) {
                if (p.getInCargo() != null){
                    if (p.getInCargo().getArrivalDate().isBefore(LocalDateTime.now())){
                        arrivedDeliveries.add(p);
                        float count = 0;
                        for (Cart c : p.getCarts()){
                            count+= (c.getBookId().getPriceWithCampaign()) * c.getCount();
                        }
                        arrivedDeliveryPrice.add(count);
                    }

                }

            }
        }

        model.addAttribute("oldDeliveries",arrivedDeliveries);
        model.addAttribute("arrivedDeliveryPrize",arrivedDeliveryPrice);
        session.setAttribute("username",userAuthantication);


        return "user/old-orders";
    }




}
