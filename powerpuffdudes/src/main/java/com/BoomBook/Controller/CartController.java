package com.BoomBook.Controller;


import com.BoomBook.DAO.*;
import com.BoomBook.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("cart")
public class CartController {

    // CART
    private CartDAO cartDAO;
    private CustomerDAO customerDAO;
    private BookDAO bookDAO;

    @Autowired
    public CartController(CartDAO cartDAO, CustomerDAO customerDAO, BookDAO bookDAO) {
        this.bookDAO = bookDAO;
        this.cartDAO = cartDAO;
        this.customerDAO = customerDAO;
    }

    // Add book to Cart
    @GetMapping("/addbooktocartinsearch")
    private String addBookToCartInSearch(@RequestParam(defaultValue = "0") int addedbookID, @RequestParam(defaultValue = "1") int count, HttpSession session,
                                         @RequestParam(defaultValue = "0") int bookId,
                                         @RequestParam(defaultValue = "") String isComeFrom,
                                         @RequestParam(defaultValue = "title") String sortedBy,
                                         @RequestParam(defaultValue = "0") int clickedGeneralCategory,
                                         @RequestParam(defaultValue = "0") int clickedCampaignCategory,
                                         @RequestParam(defaultValue = "0") int clickedCategoryId,
                                         @RequestParam(defaultValue = "0") int clickedSubcategoryId,
                                         @RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "") String searchForm){
        System.out.println("-------------sdfsf-----------------");
        UserAuthantication userAuth = (UserAuthantication) session.getAttribute("username");
        Customer user = customerDAO.findByEmail(userAuth.getUserEmail()).get(0);
        Book book = bookDAO.findById(addedbookID);
        Cart isBook = cartDAO.checkCountOfBook(user.getId(), book.getId());
        String parameters="?";

        if( isBook == null){
            Cart cart = new Cart();
            cart.setCustomer(user);
            cart.setBookId(book);
            cart.setCount(count);
            cartDAO.save(cart);
        }else{
            isBook.setCount(isBook.getCount() + count);
            cartDAO.save(isBook);
        }

        // To set the total of cart
        userAuth.setUserCart(cartDAO.getCartsByUser(user.getId()));
        userAuth.setTotalCart(cartDAO.getTotalCart(user.getId()));
        session.setAttribute("username", userAuth);

        //To check where user clicked the remove cart.
        String[] splittedUrl = isComeFrom.split("/");
        String whichPage = splittedUrl[splittedUrl.length-1];
        switch (whichPage){

            case "searchkeyword":
                if(searchForm.compareTo("")!=0){
                    parameters+="searchForm="+searchForm;
                }
                if(page != 0){
                    if(parameters.compareTo("?")!=0)
                        parameters+="&";
                    parameters+="page="+page;
                }
                if(sortedBy.compareTo("title")!=0){
                    if(parameters.compareTo("?")!=0)
                        parameters+="&";
                    parameters+="sortedBy="+sortedBy;
                }
                boolean addCardSuccessfull = true;
                session.setAttribute("addCardSuccessfull",addCardSuccessfull);
                return "redirect:/searchpage/searchkeyword/"+parameters;

            case "listbooks":
                if(clickedGeneralCategory != 0){
                    parameters+="clickedGeneralCategory=1";
                }
                if(clickedCampaignCategory != 0){
                    if(parameters.compareTo("?")!=0)
                        parameters+="&";
                    parameters+="clickedCampaignCategory=1";
                }
                if(clickedCategoryId != 0){
                    if(parameters.compareTo("?")!=0)
                        parameters+="&";
                    parameters+="clickedCategoryId="+clickedCategoryId;
                }
                if(clickedSubcategoryId != 0){
                    if(parameters.compareTo("?")!=0)
                        parameters+="&";
                    parameters+="clickedSubcategoryId="+clickedSubcategoryId;
                }
                if(page != 0){
                    if(parameters.compareTo("?")!=0)
                        parameters+="&";
                    parameters+="page="+page;
                }
                if(sortedBy.compareTo("title")!=0){
                    if(parameters.compareTo("?")!=0)
                        parameters+="&";
                    parameters+="sortedBy="+sortedBy;
                }
                addCardSuccessfull = true;
                session.setAttribute("addCardSuccessfull",addCardSuccessfull);
                return "redirect:/searchpage/listbooks/"+parameters;

            case "productdetail":
                addCardSuccessfull = true;
                session.setAttribute("addCardSuccessfull",addCardSuccessfull);
                return "redirect:/product/productdetail/?bookId="+ bookId;

            default:
                return "redirect:/home/cartIsSuccessfull";
        }
    }

    @GetMapping("/removebooktocartinsearch")
    private String removeBookToCartInSearch(HttpSession session,
                                            @RequestParam() int deletedCartId,
                                            @RequestParam(defaultValue = "0") int bookId,
                                            @RequestParam(defaultValue = "") String isComeFrom,
                                            @RequestParam(defaultValue = "title") String sortedBy,
                                            @RequestParam(defaultValue = "0") int clickedGeneralCategory,
                                            @RequestParam(defaultValue = "0") int clickedCampaignCategory,
                                            @RequestParam(defaultValue = "0") int clickedCategoryId,
                                            @RequestParam(defaultValue = "0") int clickedSubcategoryId,
                                            @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "") String searchForm){

        String parameters="?";
        UserAuthantication userAuth = (UserAuthantication) session.getAttribute("username");
        Customer user = customerDAO.findByEmail(userAuth.getUserEmail()).get(0);

        cartDAO.deleteById(deletedCartId);

        // To set the total of cart
        userAuth.setUserCart(cartDAO.getCartsByUser(user.getId()));
        userAuth.setTotalCart( (cartDAO.getTotalCart(user.getId())==null) ? 0 : cartDAO.getTotalCart(user.getId()));
        session.setAttribute("username", userAuth);


        //To check where user clicked the remove cart.
        String[] splittedUrl = isComeFrom.split("/");
        String whichPage = splittedUrl[splittedUrl.length-1];
        switch (whichPage){

            case "searchkeyword":
                if(searchForm.compareTo("")!=0){
                    parameters+="searchForm="+searchForm;
                }
                if(page != 0){
                    if(parameters.compareTo("?")!=0)
                        parameters+="&";
                    parameters+="page="+page;
                }
                if(sortedBy.compareTo("title")!=0){
                    if(parameters.compareTo("?")!=0)
                        parameters+="&";
                    parameters+="sortedBy="+sortedBy;
                }
                return "redirect:/searchpage/searchkeyword/"+parameters;

            case "listbooks":
                if(clickedGeneralCategory != 0){
                    parameters+="clickedGeneralCategory=1";
                }
                if(clickedCampaignCategory != 0){
                    if(parameters.compareTo("?")!=0)
                        parameters+="&";
                    parameters+="clickedCampaignCategory=1";
                }
                if(clickedCategoryId != 0){
                    if(parameters.compareTo("?")!=0)
                        parameters+="&";
                    parameters+="clickedCategoryId="+clickedCategoryId;
                }
                if(clickedSubcategoryId != 0){
                    if(parameters.compareTo("?")!=0)
                        parameters+="&";
                    parameters+="clickedSubcategoryId="+clickedSubcategoryId;
                }
                if(page != 0){
                    if(parameters.compareTo("?")!=0)
                        parameters+="&";
                    parameters+="page="+page;
                }
                if(sortedBy.compareTo("title")!=0){
                    if(parameters.compareTo("?")!=0)
                        parameters+="&";
                    parameters+="sortedBy="+sortedBy;
                }
                return "redirect:/searchpage/listbooks/"+parameters;

            case "productdetail":
                return "redirect:/product/productdetail/?bookId="+ bookId;

            case "checkoutstart":
                return "redirect:/checkout/checkoutstart";

            default:
                return "redirect:/home/boombook";
        }
    }

    @PostMapping("/addbooktocartinsearchPOST")
    private String addBookToCartInSearchPOST(@RequestParam(defaultValue = "0") int addedbookID, @RequestParam(defaultValue = "1") int count, HttpSession session,
                                             @RequestParam(defaultValue = "0") int bookId,
                                             @RequestParam(defaultValue = "") String isComeFrom,
                                             @RequestParam(defaultValue = "title") String sortedBy,
                                             @RequestParam(defaultValue = "0") int clickedGeneralCategory,
                                             @RequestParam(defaultValue = "0") int clickedCampaignCategory,
                                             @RequestParam(defaultValue = "0") int clickedCategoryId,
                                             @RequestParam(defaultValue = "0") int clickedSubcategoryId,
                                             @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "") String searchForm){

        UserAuthantication userAuth = (UserAuthantication) session.getAttribute("username");
        Customer user = customerDAO.findByEmail(userAuth.getUserEmail()).get(0);
        Book book = bookDAO.findById(addedbookID);
        Cart isBook = cartDAO.checkCountOfBook(user.getId(), book.getId());
        String parameters="?";

        if( isBook == null){
            Cart cart = new Cart();
            cart.setCustomer(user);
            cart.setBookId(book);
            cart.setCount(count);
            cartDAO.save(cart);
        }else{
            isBook.setCount(isBook.getCount() + count);
            cartDAO.save(isBook);
        }

        // To set the total of cart
        userAuth.setUserCart(cartDAO.getCartsByUser(user.getId()));
        userAuth.setTotalCart(cartDAO.getTotalCart(user.getId()));
        session.setAttribute("username", userAuth);

        //To check where user clicked the remove cart.
        String[] splittedUrl = isComeFrom.split("/");
        String whichPage = splittedUrl[splittedUrl.length-1];
        switch (whichPage){

            case "searchkeyword":
                if(searchForm.compareTo("")!=0){
                    parameters+="searchForm="+searchForm;
                }
                if(page != 0){
                    if(parameters.compareTo("?")!=0)
                        parameters+="&";
                    parameters+="page="+page;
                }
                if(sortedBy.compareTo("title")!=0){
                    if(parameters.compareTo("?")!=0)
                        parameters+="&";
                    parameters+="sortedBy="+sortedBy;
                }
                boolean addCardSuccessfull = true;
                session.setAttribute("addCardSuccessfull",addCardSuccessfull);
                return "redirect:/searchpage/searchkeyword/"+parameters;

            case "listbooks":
                if(clickedGeneralCategory != 0){
                    parameters+="clickedGeneralCategory=1";
                }
                if(clickedCampaignCategory != 0){
                    if(parameters.compareTo("?")!=0)
                        parameters+="&";
                    parameters+="clickedCampaignCategory=1";
                }
                if(clickedCategoryId != 0){
                    if(parameters.compareTo("?")!=0)
                        parameters+="&";
                    parameters+="clickedCategoryId="+clickedCategoryId;
                }
                if(clickedSubcategoryId != 0){
                    if(parameters.compareTo("?")!=0)
                        parameters+="&";
                    parameters+="clickedSubcategoryId="+clickedSubcategoryId;
                }
                if(page != 0){
                    if(parameters.compareTo("?")!=0)
                        parameters+="&";
                    parameters+="page="+page;
                }
                if(sortedBy.compareTo("title")!=0){
                    if(parameters.compareTo("?")!=0)
                        parameters+="&";
                    parameters+="sortedBy="+sortedBy;
                }
                addCardSuccessfull = true;
                session.setAttribute("addCardSuccessfull",addCardSuccessfull);
                return "redirect:/searchpage/listbooks/"+parameters;

            case "checkoutstart":
                return "redirect:/checkout/checkoutstart";

            case "productdetail":
                addCardSuccessfull = true;
                session.setAttribute("addCardSuccessfull",addCardSuccessfull);
                return "redirect:/product/productdetail/?bookId="+ bookId;

            default:
                return "redirect:/home/cartIsSuccessfull";
        }
    }
}