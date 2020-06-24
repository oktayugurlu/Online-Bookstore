package com.BoomBook.Controller;

import com.BoomBook.DAO.*;

import com.BoomBook.Exceptions.PurchaseChecker;

import com.BoomBook.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;

@Controller
@RequestMapping("checkout")
public class CheckoutController {

    // CART
    private CartDAO cartDAO;
    private CustomerDAO customerDAO;
    private CategoryDAO categoryDAO;
    private PurchaseRequestDAO purchaseRequestDAO;
    private InCargoDAO inCargoDAO;
    private CourierCompanyDAO courierCompanyDAO;
    private PaymentServiceDAO paymentServiceDAO;
    private BillingInformationDAO billingInformationDAO;

    @Autowired
    public CheckoutController(CartDAO cartDAO, CustomerDAO customerDAO,
                              @Qualifier("purchaseRequestDAO") PurchaseRequestDAO purchaseRequestDAO,
                              BillingInformationDAO billingInformationDAO,
                              InCargoDAO inCargoDAO, CourierCompanyDAO courierCompanyDAO,
                              CategoryDAO categoryDAO, PaymentServiceDAO paymentServiceDAO) {
        this.cartDAO = cartDAO;
        this.customerDAO = customerDAO;
        this.purchaseRequestDAO = purchaseRequestDAO;
        this.inCargoDAO = inCargoDAO;
        this.courierCompanyDAO = courierCompanyDAO;
        this.paymentServiceDAO = paymentServiceDAO;
        this.billingInformationDAO = billingInformationDAO;
        this.categoryDAO = categoryDAO;
    }

    @GetMapping("checkoutstart")
    public String getCartBooks(@RequestParam(defaultValue = "1") int courierId, HttpSession session, Model theModel) {

        // Initialize some navbar attributes search page, category and logged in
        theModel.addAttribute("searchForm", new SearchForm());
        List<Category> categories = categoryDAO.findAll();
        categories.remove(0);
        theModel.addAttribute("theCategories",categories);

        UserAuthantication userAuthantication = (UserAuthantication) session.getAttribute("username");
        PurchaseRequest purchaseRequest = new PurchaseRequest();
        BillingInformation billingInformation = new BillingInformation();
        Customer customer = customerDAO.findByEmail(userAuthantication.getUserEmail()).get(0);
        List<CourierCompany> companies = courierCompanyDAO.findAll();
        List<Cart> outOfStockCarts = cartDAO.checkIsCountValid(customer.getId());

        // Set list if there is out of stock cart item
        theModel.addAttribute("theOutOfStockCarts", outOfStockCarts);

        // Set all couriers to show user
        theModel.addAttribute("theCompanies", companies);

        // Save customer information to billing
        billingInformation.setCustomer(customer);
        billingInformation.setAddress(customer.getAddress());
        billingInformation.setCustomerName(customer.getCustomerName());
        billingInformation.setPhone(customer.getPhone());
        billingInformation.setCustomerName(customer.getCustomerName());
        billingInformation.setEmail(customer.getEmail());
        theModel.addAttribute("theBillingInformation", billingInformation);

        // Set total of price with courier company
        userAuthantication.setTotalOrder(userAuthantication.getTotalCart() + courierCompanyDAO.findById(courierId).getPrice());

        // Set purchase request
        theModel.addAttribute("purchaseRequest", purchaseRequest);

        //set user authentication
        theModel.addAttribute("username", userAuthantication);

        boolean expireDateOutOfDate = false;
        session.setAttribute("expireDateOutOfDate",expireDateOutOfDate);

        return "user/shop-checkout";
    }

    @PostMapping("paymentstart")
    public String goPostMapping(
            @RequestParam() int courierId,
            @ModelAttribute("theBillingInformation") BillingInformation theBillingInformation,
            @ModelAttribute("purchaseRequest") PurchaseRequest purchaseRequest,
            HttpSession session, Model theModel) {
        System.out.println("1-theBillingInformation: "+theBillingInformation.toString());
        UserAuthantication userAuthantication = (UserAuthantication) session.getAttribute("username");


        // set purchase request
        purchaseRequest.setCourierCompany(courierCompanyDAO.findById(courierId));
        purchaseRequest.setIsConfirmed(0);

        // remove all purchased items from cart
        userAuthantication.setUserCart(cartDAO.getCartsByUser(customerDAO.findByEmail(userAuthantication.getUserEmail()).get(0).getId()));
        userAuthantication.setTotalOrder(0);
        userAuthantication.setTotalCart(0);

        // Initialize some navbar attributes search page, category and logged in
        theModel.addAttribute("searchForm", new SearchForm());
        theModel.addAttribute("theCategories", categoryDAO.findAll());

        // Purchase form to check after validation of expire date, and get payment service method type
        theModel.addAttribute("purchaseChecker", new PurchaseChecker());

        // Set purchase request
        session.setAttribute("purchaseRequest", purchaseRequest);

        // Set purchase request
        session.setAttribute("theBillingInformation", theBillingInformation);

        return "redirect:/checkout/gotopurchase";
    }

    @GetMapping("gotopurchase")
    public String goToPurchase(Model theModel, HttpSession session){
        // Initialize some navbar attributes search page, category and logged in
        theModel.addAttribute("searchForm", new SearchForm());
        theModel.addAttribute("theCategories", categoryDAO.findAll());

        // set payment methods
        theModel.addAttribute("thePaymentServices", paymentServiceDAO.findAll());
        theModel.addAttribute("purchaseChecker", new PurchaseChecker());

        // set payment methods
        return "user/shop-payment";
    }


    @PostMapping("purchaserequest")
    public String makePurchase(Model theModel, HttpSession session, @RequestParam(defaultValue = "0") int isCart ,
                               @ModelAttribute PurchaseChecker purchaseChecker){
        theModel.addAttribute(new PurchaseChecker());
        UserAuthantication userAuthantication = (UserAuthantication) session.getAttribute("username");
        BillingInformation billingInformation = (BillingInformation) session.getAttribute("theBillingInformation");
        PurchaseRequest purchaseRequestWithId = (PurchaseRequest) session.getAttribute("purchaseRequest");

        System.out.println("local year"+LocalDateTime.now().getYear());
        System.out.println(purchaseChecker.getExpireYear());
        System.out.println("theBillingInformation: "+billingInformation.toString());

        System.out.println("bir");
        if(purchaseChecker.getExpireYear()>LocalDateTime.now().getYear()){ // successfull
                // save bill to database
                billingInformationDAO.save(billingInformation);
                // set purchase request
                purchaseRequestWithId.setBillingInformation(billingInformation);
                purchaseRequestDAO.save(purchaseRequestWithId);
                // save in cargo.
                for(Cart c: userAuthantication.getUserCart()){
                    c.setPurchaseRequest(purchaseRequestDAO.findById(purchaseRequestWithId.getId()));
                    cartDAO.save(c);
                }
                System.out.println("22");

                session.setAttribute("purchaseIsSuccessfull",true);
                userAuthantication.setUserCart(new ArrayList<Cart>());
                session.setAttribute("username",userAuthantication);
                return "redirect:/home/comeFromSuccessfullPurchase";
            }
        else if(purchaseChecker.getExpireYear()==LocalDateTime.now().getYear()){
                if(purchaseChecker.getExpireMonth()> LocalDateTime.now().getMonthValue()){ // No problem
                    // save bill to database
                    billingInformationDAO.save(billingInformation);
                    // set purchase request
                    purchaseRequestWithId.setPaymentServiceID(paymentServiceDAO.findById(-1));
                    purchaseRequestWithId.setBillingInformation(billingInformation);
                    purchaseRequestDAO.save(purchaseRequestWithId);
                    purchaseRequestWithId = purchaseRequestDAO.findAllSorted().get(0);

                    for(Cart c: userAuthantication.getUserCart()){
                        c.setPurchaseRequest(purchaseRequestWithId);
                        cartDAO.save(c);
                    }

                    System.out.println("33");

                    userAuthantication.setUserCart(new ArrayList<Cart>());
                    session.setAttribute("username",userAuthantication);
                    session.setAttribute("purchaseIsSuccessfull",true);
                    return "redirect:/home/comeFromSuccessfullPurchase";
                }
                else{
                    purchaseChecker.setExpireDateOutOfDate(true);

                    // Initialize some navbar attributes search page, category and logged in
                    theModel.addAttribute("searchForm", new SearchForm());
                    theModel.addAttribute("theCategories", categoryDAO.findAll());

                    System.out.println("44");

                    // Set purchase request
                    boolean expireDateOutOfDate = true;
                    session.setAttribute("expireDateOutOfDate",expireDateOutOfDate);

                    session.setAttribute("purchaseRequestWithId", purchaseRequestWithId);
                    return "redirect:/checkout/gotopurchase";
                }
            }
        else if(purchaseChecker.getExpireYear()<LocalDateTime.now().getYear()){
                purchaseChecker.setExpireDateOutOfDate(true);

                // Initialize some navbar attributes search page, category and logged in
                theModel.addAttribute("searchForm", new SearchForm());
                theModel.addAttribute("theCategories", categoryDAO.findAll());

                // Purchase form to check after validation of expire date, and get payment service method type
                session.setAttribute("purchaseChecker", new PurchaseChecker());
                System.out.println("55");

                boolean expireDateOutOfDate = true;
                session.setAttribute("expireDateOutOfDate",expireDateOutOfDate);

                // Set purchase request
                session.setAttribute("purchaseRequestWithId", purchaseRequestWithId);
                return "redirect:/checkout/gotopurchase";
            }

        return "redirect:/home/comeFromSuccessfullPurchase";
    }
}
