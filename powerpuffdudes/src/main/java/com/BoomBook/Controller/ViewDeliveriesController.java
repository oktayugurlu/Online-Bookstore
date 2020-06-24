package com.BoomBook.Controller;

import com.BoomBook.DAO.*;
import com.BoomBook.Model.Cart;
import com.BoomBook.Model.PurchaseRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("viewdeliveries")
public class ViewDeliveriesController {
    private CartDAO cartDAO;
    private CourierCompanyDAO courierCompanyDAO;
    private CustomerDAO customerDAO;
    private InCargoDAO inCargoDAO;
    private PurchaseRequestDAO purchaseRequestDAO;

    public ViewDeliveriesController(CartDAO cartDAO, CourierCompanyDAO courierCompanyDAO, CustomerDAO customerDAO, InCargoDAO inCargoDAO, PurchaseRequestDAO purchaseRequestDAO) {
        this.cartDAO = cartDAO;
        this.courierCompanyDAO = courierCompanyDAO;
        this.customerDAO = customerDAO;
        this.inCargoDAO = inCargoDAO;
        this.purchaseRequestDAO = purchaseRequestDAO;
    }

    @GetMapping("/listdeliveriessituations")
    public String manageDeliveriesMainPage(Model model, @RequestParam(defaultValue = "0") int page, HttpSession session){

        List<Boolean> isReceived = new ArrayList<>();    // True if order is arrived, false if order is in delivery
        List<PurchaseRequest> orders = purchaseRequestDAO.findAll();
        List<Float> totalOfOrders = new ArrayList<>();

        orders.removeIf(p -> p.getIsConfirmed() != 1);

        for(PurchaseRequest p:orders){
            float total=0;
            for(Cart c:p.getCarts()){
                total+=c.getBookId().getPrice();
            }
            totalOfOrders.add(total);

            // getting information about confirmation
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime arriveTime = p.getInCargo().getArrivalDate();
            isReceived.add(now.isBefore(arriveTime) || (now).isEqual(arriveTime));
            System.out.println(now.isAfter(arriveTime)+" ");
        }

        model.addAttribute("confirmationSituation", isReceived);
        model.addAttribute("theTotalOfOrders",totalOfOrders);
        model.addAttribute("theorders",orders);

        return "admin/delivery-situations";
    }


}
