package com.BoomBook.Controller;

import com.BoomBook.DAO.CartDAO;
import com.BoomBook.DAO.InCargoDAO;
import com.BoomBook.DAO.MailDAO;
import com.BoomBook.DAO.PurchaseRequestDAO;
import com.BoomBook.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("viewandconfirmsales")
public class ViewAndConfirmSalesController {

    private PurchaseRequestDAO purchaseRequestDAO;
    private CartDAO cartDAO;
    private MailDAO mailDAO;
    private InCargoDAO inCargoDAO;
    int deniedId;
    private boolean isConfirmed = false;
    private boolean isDenied = false;

    @Autowired
    public ViewAndConfirmSalesController(PurchaseRequestDAO purchaseRequestDAO, CartDAO cartDAO, @Qualifier("mailDAO") MailDAO theMailDAO, InCargoDAO inCargoDAO) {
        this.purchaseRequestDAO = purchaseRequestDAO;
        this.cartDAO = cartDAO;
        this.mailDAO = theMailDAO;
        this.inCargoDAO = inCargoDAO;
    }


    @PostMapping("/denyorder")
    public String deleteOrder(@RequestParam("orderId") int theOrderId, Model theModel, HttpSession session) {
        isDenied = true;

        Mail mail = new Mail();
        mail.setSendDate(LocalDateTime.now());
        mail.setPageAdmin(new PageAdmin(1,"1234","admin@admin"));
        mail.setTitle("ORDER IS DENIED");
        mail.setCustomer(purchaseRequestDAO.findById(theOrderId).getCarts().get(0).getCustomer());
        mail.setContent("The order with id: " + theOrderId + " is denied");
        mailDAO.save(mail);

        deniedId = theOrderId;
        purchaseRequestDAO.deleteById(theOrderId);

        return "redirect:/viewandconfirmsales/listorders";
    }

    @PostMapping("/confirmorder")
    public String confirmOrder(@RequestParam("orderId") int theOrderId, Model theModel, HttpSession session) {
        isConfirmed = true;

        Mail mail = new Mail();
        mail.setSendDate(LocalDateTime.now());
        mail.setPageAdmin(new PageAdmin(1,"1234","admin@admin"));
        mail.setTitle("ORDER IS CONFIRMED");
        mail.setCustomer(purchaseRequestDAO.findById(theOrderId).getCarts().get(0).getCustomer());
        mail.setContent("The order with id: " + theOrderId + " is confirmed");
        mailDAO.save(mail);

        PurchaseRequest purchaseRequest = purchaseRequestDAO.findById(theOrderId);
        purchaseRequest.setIsConfirmed(1);
        purchaseRequestDAO.save(purchaseRequest);
        InCargo inCargo = new InCargo();
        inCargo.setPurchaseRequest(purchaseRequest);
        inCargoDAO.save(inCargo);
        return "redirect:/viewandconfirmsales/listorders";
    }

    @GetMapping("listorders")
    public String viewOrders(Model theModel, HttpSession session){

        session.setAttribute("isConfirmed", isConfirmed);
        session.setAttribute("isDenied", isDenied);
        isConfirmed = false;
        isDenied = false;

        List<PurchaseRequest> orders = purchaseRequestDAO.findAll();
        List<Float> totalOfOrders = new ArrayList<>();

        orders.removeIf(p -> p.getIsConfirmed() != 0);


        for(PurchaseRequest p:orders){
            // Calculating total price
            float total=0;
            for(Cart c:p.getCarts()){
                Book book = c.getBookId();
                total+=(book.getPrice()*c.getCount());
            }
            totalOfOrders.add(total);
        }

        theModel.addAttribute("theTotalOfOrders",totalOfOrders);
        theModel.addAttribute("theorders",orders);

        return "admin/orders";
    }





}
