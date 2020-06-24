package com.BoomBook.Controller;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;
import com.BoomBook.DAO.*;
import com.BoomBook.Exceptions.AddToCartForm;
import com.BoomBook.Exceptions.CommentChecker;
import com.BoomBook.Exceptions.SortedBy;
import com.BoomBook.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("product")
public class ProductDetailController {

    private BookDAO bookDAO;
    private CategoryDAO categoryDAO;
    private SubcategoryDAO subcategoryDAO;
    private BookCommentDAO bookCommentDAO;
    private CustomerDAO customerDAO;
    private SearchForm searchForm;
    private SortedBy sortedBy;

    @Autowired
    public ProductDetailController(CustomerDAO customerDAO, BookDAO bookDAO, CategoryDAO categoryDAO, SubcategoryDAO subcategoryDAO, BookCommentDAO bookCommentDAO) {
        this.bookDAO = bookDAO;
        this.categoryDAO = categoryDAO;
        this.subcategoryDAO = subcategoryDAO;
        this.bookCommentDAO = bookCommentDAO;
        this.customerDAO = customerDAO;
    }

    @GetMapping("productdetail")
    String productDetail(Model theModel, @RequestParam int bookId, HttpSession session){

        boolean addCardSuccessfull = (session.getAttribute("addCardSuccessfull") != null) && (boolean) session.getAttribute("addCardSuccessfull");
        theModel.addAttribute("addCardSuccessfull",addCardSuccessfull);

        // To print the rate as a 5 star
        int theBookRate = Math.round((bookDAO.bookRate(bookId)==null) ? 0 : bookDAO.bookRate(bookId));
        List<Boolean> theBoolRateList = new ArrayList<>();
        for(int i = 0; i<6; i++)
            theBoolRateList.add(false);
        switch (theBookRate){
            case 0:
                theBoolRateList.set(0, true);
                break;
            case 1:
                theBoolRateList.set(1, true);
                break;
            case 2:
                theBoolRateList.set(1, true);
                theBoolRateList.set(2, true);
                break;
            case 3:
                theBoolRateList.set(1, true);
                theBoolRateList.set(2, true);
                theBoolRateList.set(3, true);
                break;
            case 4:
                theBoolRateList.set(1, true);
                theBoolRateList.set(2, true);
                theBoolRateList.set(3, true);
                theBoolRateList.set(4, true);
                break;
            case 5:
                theBoolRateList.set(1, true);
                theBoolRateList.set(2, true);
                theBoolRateList.set(3, true);
                theBoolRateList.set(4, true);
                theBoolRateList.set(5, true);
                break;
        }

        // These are essential model attributes for each page controller which is used navbar.
        theModel.addAttribute("searchForm", new SearchForm());
        theModel.addAttribute("addToCartForm", new AddToCartForm());
        List<Category> categories = categoryDAO.findAll();
        categories.remove(0);
        theModel.addAttribute("theCategories",categories);
        // These are essential model attributes for each page controller which is used navbar.

        theModel.addAttribute("theBookDetail", bookDAO.findById(bookId));
        theModel.addAttribute("theBookComments", bookCommentDAO.findByBookId(bookId));
        theModel.addAttribute("theRateOfBook", theBoolRateList);

        // Check the user buy, logged in, or make a comment.
        CommentChecker commentChecker = new CommentChecker();

        //Comment Form and set BookComment's customer by using mail userAuthentication session attribute .
        BookComment bookComment = new BookComment();
        bookComment.setBook(bookDAO.findById(bookId));
        System.out.println(bookComment.getBook().getTitle());
        UserAuthantication authantication = (UserAuthantication) session.getAttribute("username");
        if(authantication.getUserEmail()!=null){
            Customer customer = customerDAO.findByEmail(authantication.getUserEmail()).get(0);
            bookComment.setCustomer(customer);

            //set if customer is made comment before
            commentChecker.setMakeComment(customerDAO.isCustomerMadeComment(customer.getId(), bookId) != null);
            System.out.println("if: "+ customerDAO.isCustomerPurchaseBook(customer.getId(), bookId));
            //set if customer is buy this book
            commentChecker.setMakePurchase(!customerDAO.isCustomerPurchaseBook(customer.getId(), bookId).isEmpty());
        }

        theModel.addAttribute("bookComment", bookComment);
        theModel.addAttribute("commentAbilityChecker", commentChecker);

        System.out.println(theBookRate);
        return "user/product-detail";
    }

    @PostMapping("addcomment")
    String addToCart(@ModelAttribute("userComment") BookComment bookComment, @ModelAttribute("commentAbilityChecker") CommentChecker commentChecker){

        bookCommentDAO.save(bookComment);

        bookCommentDAO.save(bookComment); 
        return "redirect:/product/productdetail/?bookId="+bookComment.getBook().getId();
    }
}