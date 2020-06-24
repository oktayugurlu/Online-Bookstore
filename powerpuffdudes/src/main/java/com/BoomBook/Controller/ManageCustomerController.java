package com.BoomBook.Controller;


import com.BoomBook.DAO.*;
import com.BoomBook.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("managecustomers")
public class ManageCustomerController {

    private CategoryDAO categoryDAO;
    private SubcategoryDAO subcategoryDAO;
    private CustomerDAO customerDAO;
    private BookDAO bookDAO;
    private PurchaseRequestDAO purchaseRequestDAO;
    private MailDAO mailDAO;
    private PageAdminDAO pageAdminDAO;
    private CartDAO cartDAO;
    private boolean CRUD = false;
    private boolean emailNameError = false;
    private boolean isCustomerDeleted = false;

    int deletingId;
    Customer theCustomerForSentMail;

    @Autowired

    public ManageCustomerController(@Qualifier("customerDAO") CustomerDAO theCustomerDAO, @Qualifier("purchaseRequestDAO") PurchaseRequestDAO thePurchaseRequestDAO ,@Qualifier("mailDAO") MailDAO theMailDAO, @Qualifier("cartDAO") CartDAO theCartDAO, @Qualifier("bookDAO") BookDAO theBookDAO) {
        customerDAO = theCustomerDAO;
        purchaseRequestDAO = thePurchaseRequestDAO;
        mailDAO = theMailDAO;
        cartDAO = theCartDAO;
        bookDAO = theBookDAO;
    }

    @GetMapping("/listcustomers")
    public String manageCustomerMainPage(Model theModel, @RequestParam(defaultValue = "0") int page, HttpSession session){

        session.setAttribute("CRUD",CRUD);
        CRUD = false;
        session.setAttribute("emailNameError" , emailNameError);
        emailNameError = false;
        session.setAttribute("isCustomerDeleted",isCustomerDeleted);
        isCustomerDeleted = false;


        SearchForm searchForm = new SearchForm();
        theModel.addAttribute("searchForm", searchForm);

        theModel.addAttribute("thecustomers",
                customerDAO.findAll(PageRequest.of(page, 6)));

        List<Customer> customers = customerDAO.findAll();
        int totalCustomer = customers.size();
        theModel.addAttribute("totalCustomer",totalCustomer);

        return "admin/manage-customer";
    }

    @PostMapping("/searchcustomer")
    public String searchCustomer(@ModelAttribute("searchForm") SearchForm searchForm, @RequestParam(defaultValue = "0") int page, Model theModel){
        Page<Customer> customers = customerDAO.findByCustomerNameOrEmail(searchForm.getSearchKey(),PageRequest.of(page,6));

        theModel.addAttribute("thecustomers", customers);

        List<Customer> customerss = customerDAO.findAll();
        int totalCustomers = customerss.size();
        theModel.addAttribute("totalCustomer",totalCustomers);
        return "admin/manage-customer";
    }

    @PostMapping("/deletecustomer")
    public String deletecustomer(@RequestParam("customerId") int theCustomerId, Model theModel, HttpSession session) {
        isCustomerDeleted = true;
        theModel.addAttribute("isCustomerDeleted",isCustomerDeleted);
        deletingId = theCustomerId;
        customerDAO.deleteById(deletingId);
        System.out.println("Deleted");
        System.out.println("id is : " + theCustomerId);

        return "redirect:/managecustomers/listcustomers";
    }

    @PostMapping("/send-mail")
    public String sentMail(@RequestParam("customerId") int theCustomerId, Model theModel, HttpSession session) {

        System.out.println("send-mail clicked");
        System.out.println("id is : " + theCustomerId);

        theCustomerForSentMail = customerDAO.findById(theCustomerId);


        Mail mail = new Mail();
        System.out.println("clicked");


        theModel.addAttribute("mailForSave", mail);
        theModel.addAttribute("user",theCustomerForSentMail);

        return "admin/send-mail";
    }

    @PostMapping("/saveMail")
    public String sendMail(@ModelAttribute("mailForSave") Mail theMail, Model theModel, HttpSession session) {

        Mail sentMail = new Mail();
        sentMail.setTitle(theMail.getTitle());
        sentMail.setContent(theMail.getContent());
        sentMail.setSendDate(LocalDateTime.now());

        //sentMail.setPageAdmin();
        if(theCustomerForSentMail != null)
            sentMail.setCustomer(theCustomerForSentMail);


        sentMail.setPageAdmin(new PageAdmin(1,"1234","admin@admin"));
        mailDAO.save(sentMail);
        return "redirect:/managecustomers/listcustomers";
    }



    @PostMapping("/update-customer")
    public String showUpdateCustomer(@RequestParam("customerId") int theId, Model theModel, HttpSession session) {

        Customer theCustomer = customerDAO.findById(theId);
        Customer updatedCustomer = new Customer();
        updatedCustomer.setId(theCustomer.getId());
        updatedCustomer.setAddress(theCustomer.getAddress());
        updatedCustomer.setCustomerName(theCustomer.getCustomerName());
        updatedCustomer.setCustomerPassword(theCustomer.getCustomerPassword());
        updatedCustomer.setEmail(theCustomer.getEmail());
        updatedCustomer.setPhone(theCustomer.getPhone());

        theModel.addAttribute("customerForSave",updatedCustomer);

        System.out.println("Update clicked" + "id is: " + theId);
        return "admin/update-customer";
    }

    @PostMapping("/saveCustomer")
    public String submitCustomer(@ModelAttribute("customerForSave") Customer theCustomer, Model theModel, HttpSession session) {
        CRUD = true;
        Customer savedCustomer = new Customer();
        savedCustomer.setPhone(theCustomer.getPhone());
        savedCustomer.setEmail(theCustomer.getEmail());
        savedCustomer.setCustomerPassword(theCustomer.getCustomerPassword());
        savedCustomer.setAddress(theCustomer.getAddress());
        savedCustomer.setCustomerName(theCustomer.getCustomerName());
        savedCustomer.setId(theCustomer.getId());

        for(Customer customer: customerDAO.findAll()){
            if(customer.getEmail().equals(savedCustomer.getEmail() )){
                if(customer.getId() != savedCustomer.getId()) {
                    emailNameError = true;
                    theModel.addAttribute("emailNameError",emailNameError);
                    return "redirect:/managecustomers/listcustomers";
                }
            }
        }

        customerDAO.save(savedCustomer);
        return "redirect:/managecustomers/listcustomers";
    }


    @GetMapping("/add-customer")
    public String addCustomerButton(Model theModel, HttpSession session){

        Customer customer = new Customer();
        System.out.println("clicked");

        theModel.addAttribute("customerForSave", customer);
        return "admin/add-customer";
    }

    @PostMapping("/customer-detail")
    public String viewDetailsButton( @RequestParam("customerId") int theCustomerId, @RequestParam(defaultValue = "0") int page, Model theModel, HttpSession session){

        theModel.addAttribute("thecustomer",
                customerDAO.findById(theCustomerId));
        System.out.println("detail clicked" + " id is : "+  theCustomerId );

        Customer customer = customerDAO.findById(theCustomerId);


        //System.out.println(cart.getPurchaseRequest().getCarts().size());
        //System.out.println(cart.getPurchaseRequest().getCarts());

        List<Cart> carts = cartDAO.findCartsByCustomer(customer);
        if(carts.size() != 0){

            theModel.addAttribute("thecards" , carts);
            ArrayList<Book> books = new ArrayList<>();
            List<PurchaseRequest> purchaseRequests = purchaseRequestDAO.findPurchaseRequestByCustomerId(theCustomerId);

            theModel.addAttribute("purchaseRequests",purchaseRequests);
           // System.out.println("----->" + purchaseRequests);

            ArrayList<Integer> counts = new ArrayList<>();

            for(PurchaseRequest purchaseRequest: purchaseRequests){
                int total = 0;
                for(Cart cart: purchaseRequest.getCarts()){
                    if(cart.getPurchaseRequest().getId() == purchaseRequest.getId() && cart.getCustomer().getId() == theCustomerId) {
                       // System.out.println(cart.getId() + " " + cart.getBookId().getId() + " " + cart.getCustomer().getId() + " " + cart.getCount() + " " + cart.getPurchaseRequest().getId());
                        total += cart.getCount();
                    }
                }
                counts.add(total);
            }
            theModel.addAttribute("counts",counts);

            for(int i=0;i<carts.size();i++){
                //System.out.println("id : " + carts.get(i).getBookId().getId());
                Book book = bookDAO.findById(carts.get(i).getBookId().getId());
                if(theCustomerId == carts.get(i).getCustomer().getId() && book.getId() == carts.get(i).getBookId().getId())
                    books.add(book);
                //System.out.println(books);

            }
            theModel.addAttribute("thebooks" , books);
        }
        else {
            ArrayList<String> value = new ArrayList<>();
            value.add("none");
            value.add("0");
            theModel.addAttribute("theNullCard",value);
        }

        //System.out.println("Carrrrds: " + carts);

        //System.out.println(cart);

        //List<PurchaseRequest> orders = purchaseRequestDAO.f


        return "admin/customer-detail";
    }



}
