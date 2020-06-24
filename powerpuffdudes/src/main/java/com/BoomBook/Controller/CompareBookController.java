package com.BoomBook.Controller;


import com.BoomBook.DAO.BookDAO;
import com.BoomBook.DAO.CategoryDAO;
import com.BoomBook.Exceptions.AvgRate;
import com.BoomBook.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("boombook")
public class CompareBookController {

    BookDAO bookDAO;
    BookComparison bookComparison;
    CategoryDAO categoryDAO;

    @Autowired
    public CompareBookController(BookDAO bookDAO,CategoryDAO categoryDAO) {
        this.bookDAO = bookDAO;
        this.categoryDAO=categoryDAO;
        bookComparison=new BookComparison(true);
    }

    @GetMapping("/comparebooks")
    public String compareBooks(Model model,HttpSession session){

        List<Book> books = bookDAO.findAll();
        BookComparisonForm bookComparisonForm = new BookComparisonForm();

        model.addAttribute("BookComparisonStage",bookComparison);
        model.addAttribute("BookComparisonForm",bookComparisonForm);
        model.addAttribute("compareBooks",books);

        return "user/compare";
    }

    @PostMapping("/compareprocess")
    public String ProcessCompareBooks(@ModelAttribute BookComparisonForm bookComparisonForm,Model theModel){

        Book firstBook = bookDAO.findById(Integer.parseInt(bookComparisonForm.getFirstBook()));
        Book secondBook = bookDAO.findById(Integer.parseInt(bookComparisonForm.getSecondBook()));
        bookComparison.setFirstBook(firstBook);
        bookComparison.setSecondBook(secondBook);
        List<Category> categories = new ArrayList<>();
        categories.add(firstBook.getSubcategory().getCategory());
        categories.add(secondBook.getSubcategory().getCategory());
        bookComparison.setCategories(categories);
        List<Integer> ratings = new ArrayList<>();
        AvgRate avgRate = new AvgRate();
        ratings.add(avgRate.findAverageRateofBook(firstBook.getComments()));
        ratings.add(avgRate.findAverageRateofBook(secondBook.getComments()));
        System.out.println(ratings.toString());

        if (ratings.get(0)>0){
            bookComparison.setShowFirstRate(true);
        }else{
            bookComparison.setShowFirstRate(false);
        }
        if(ratings.get(1)>0){
            bookComparison.setShowSecondRate(true);
        }else {
            bookComparison.setShowSecondRate(false);
        }
        bookComparison.setRatings(ratings);
        bookComparison.setFirstRequest(false);

        System.out.println(bookComparison.getRatings().get(0));

        return "redirect:/boombook/comparebooks";
    }



}
