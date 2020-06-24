package com.BoomBook.Controller;

import com.BoomBook.DAO.*;
import com.BoomBook.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("managemails")
public class ManageMailController {

    private CustomerDAO customerDAO;
    private MailDAO mailDAO;
    private PageAdminDAO pageAdminDAO;

    @Autowired
    public ManageMailController(@Qualifier("customerDAO") CustomerDAO theCustomerDAO,  PageAdminDAO thePageAdminDAO , @Qualifier("mailDAO") MailDAO theMailDAO) {
        customerDAO = theCustomerDAO;
        pageAdminDAO = thePageAdminDAO;
        mailDAO = theMailDAO;
    }

    @GetMapping("/listmails")
    public String manageMailsMainPage(Model theModel, @RequestParam(defaultValue = "0") int page, HttpSession session){

        theModel.addAttribute("themail", mailDAO.findAll(PageRequest.of(page, 10, Sort.by("sendDate").descending())));

        theModel.addAttribute("totalMails",mailDAO.findAll().size());

        theModel.addAttribute("customerDAO",customerDAO );

        return "admin/manage-mail";
    }

    @PostMapping("/deletemail")
    public String deletemail(@RequestParam("mailId") int theMailId, Model theModel, HttpSession session) {
        int deletingId;
        deletingId = theMailId;
        mailDAO.deleteById(deletingId);
        return "redirect:/managemails/listmails";
    }

    @PostMapping("/maildetail")
    public String viewDetailsButton( @RequestParam("mailId") int theMailId, @RequestParam(defaultValue = "0") int page, Model theModel, HttpSession session){

        theModel.addAttribute("themail", mailDAO.findById(theMailId));


        return "admin/mail-detail";
    }


    @PostMapping("/back")
    public String backMainpage(   Model theModel, HttpSession session){

        return "redirect:/managemails/listmails";
    }

}
