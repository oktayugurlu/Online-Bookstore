package com.BoomBook.Controller;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;
import com.BoomBook.DAO.*;
import com.BoomBook.Exceptions.AddToCartForm;
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
@RequestMapping("searchpage")
public class SearchPageController {

    private BookDAO bookDAO;
    private CategoryDAO categoryDAO;
    private SubcategoryDAO subcategoryDAO;
    private SearchForm searchForm;

    // CART

    @Autowired
    public SearchPageController(BookDAO bookDAO, CategoryDAO categoryDAO, SubcategoryDAO subcategoryDAO ) {
        this.bookDAO = bookDAO;
        this.categoryDAO = categoryDAO;
        this.subcategoryDAO = subcategoryDAO;
    }

    @GetMapping("/listbooks")
    public String searchBookPage2(@RequestParam(defaultValue = "title") String sortedBy,
                                @RequestParam(defaultValue = "0") int clickedGeneralCategory,
                                @RequestParam(defaultValue = "0") int clickedCampaignCategory,
                                @RequestParam(defaultValue = "0") int clickedCategoryId,
                                 @RequestParam(defaultValue = "0") int clickedSubcategoryId,
                                 @RequestParam(defaultValue = "0") int page, HttpSession session,Model theModel){
        //Add to cart successfully messsage
        boolean addCardSuccessfull = (session.getAttribute("addCardSuccessfull") !=null) ? (boolean) session.getAttribute("addCardSuccessfull") : false;
        theModel.addAttribute("addCardSuccessfull",addCardSuccessfull);


        // int totalPages=0;
        System.out.println("Clicked is: "+subcategoryDAO.findById(clickedSubcategoryId));
        System.out.println("Clicked is: "+searchForm);
        List<Category> categories = categoryDAO.findAll();
        categories.remove(0);
        theModel.addAttribute("theCategories",categories);


        // to get books sorted

        String[] arrOfStr = sortedBy.split("-");
        System.out.println(arrOfStr[0]);
        if(arrOfStr.length >1 ){ // if sorted by price is wanted
                if(arrOfStr[1].compareTo("low_to_high") == 0){ // if price is low to high
                    if(clickedGeneralCategory==1){ // General category is a subcategory so its searched as subcategory
                        theModel.addAttribute("thebooks",  bookDAO.findAll(PageRequest.of(page, 12, Sort.by(arrOfStr[0])) ));
                        theModel.addAttribute("sizeOfTheBooks", bookDAO.findAll(PageRequest.of(page, 12, Sort.by(arrOfStr[0]))).getContent().size());
                    }
                    else if(clickedCampaignCategory==1){ // Campaign category is a subcategory so its searched as subcategory
                        theModel.addAttribute("thebooks",  bookDAO.findCampaign(PageRequest.of(page, 12, Sort.by(arrOfStr[0] ))) );
                        theModel.addAttribute("sizeOfTheBooks", bookDAO.findCampaign(PageRequest.of(page, 12, Sort.by(arrOfStr[0] ))).getContent().size());
                    }
                    else if(clickedCategoryId!=0){
                        theModel.addAttribute("thebooks", bookDAO.findByCategory(clickedCategoryId, PageRequest.of(page, 12, Sort.by(arrOfStr[0]))) );
                        theModel.addAttribute("sizeOfTheBooks", bookDAO.findByCategory(clickedCategoryId, PageRequest.of(page, 12, Sort.by(arrOfStr[0]))).getContent().size());
                    }
                    else if(clickedSubcategoryId!=0){
                        theModel.addAttribute("thebooks", bookDAO.findBySubcategory(clickedSubcategoryId, PageRequest.of(page, 12, Sort.by(arrOfStr[0]))));
                        theModel.addAttribute("sizeOfTheBooks", bookDAO.findBySubcategory(clickedSubcategoryId, PageRequest.of(page, 12, Sort.by(arrOfStr[0]))).getContent().size());
                    }
                    else{
                        theModel.addAttribute("thebooks", bookDAO.findAll(PageRequest.of(page, 12, Sort.by(arrOfStr[0]))));
                        theModel.addAttribute("sizeOfTheBooks", bookDAO.findAll(PageRequest.of(page, 12, Sort.by(arrOfStr[0]))).getContent().size());

                    }

                }
                else{  // if price is high to low
                    if(clickedGeneralCategory==1){ // General category is a subcategory so its searched as subcategory
                        theModel.addAttribute("thebooks",  bookDAO.findAll(PageRequest.of(page, 12, Sort.by(arrOfStr[0]).descending() ) ));
                        theModel.addAttribute("sizeOfTheBooks", bookDAO.findAll(PageRequest.of(page, 12, Sort.by(arrOfStr[0]).descending() ) ).getContent().size());
                    }
                    else if(clickedCampaignCategory==1){ // Campaign category is a subcategory so its searched as subcategory
                        theModel.addAttribute("thebooks",  bookDAO.findCampaign(PageRequest.of(page, 12, Sort.by(arrOfStr[0]).descending() ) ));
                        theModel.addAttribute("sizeOfTheBooks", bookDAO.findCampaign(PageRequest.of(page, 12, Sort.by(arrOfStr[0]).descending() ) ).getContent().size());
                    }
                    else if(clickedCategoryId!=0){
                        theModel.addAttribute("thebooks",  bookDAO.findByCategory(clickedCategoryId, PageRequest.of(page, 12, Sort.by(arrOfStr[0]).descending())));
                        theModel.addAttribute("sizeOfTheBooks", bookDAO.findByCategory(clickedCategoryId, PageRequest.of(page, 12, Sort.by(arrOfStr[0]).descending())).getContent().size());
                    }
                    else if(clickedSubcategoryId!=0){
                        theModel.addAttribute("thebooks",  bookDAO.findBySubcategory(clickedSubcategoryId, PageRequest.of(page, 12, Sort.by(arrOfStr[0]).descending())));
                        theModel.addAttribute("sizeOfTheBooks", bookDAO.findBySubcategory(clickedSubcategoryId, PageRequest.of(page, 12, Sort.by(arrOfStr[0]).descending())).getContent().size());
                    }
                    else{
                        theModel.addAttribute("thebooks",  bookDAO.findAll(PageRequest.of(page, 12, Sort.by(arrOfStr[0]).descending())));
                        theModel.addAttribute("sizeOfTheBooks", bookDAO.findAll(PageRequest.of(page, 12, Sort.by(arrOfStr[0]).descending())).getContent().size());
                    }

                }


        }
        else {                  // if sorted by price is not wanted

                if(clickedGeneralCategory==1){ // General category is a subcategory so its searched as subcategory
                    System.out.println("hel");
                    theModel.addAttribute("thebooks",  bookDAO.findAll(PageRequest.of(page, 12, Sort.by(sortedBy)) ));
                    theModel.addAttribute("sizeOfTheBooks", bookDAO.findAll(PageRequest.of(page, 12, Sort.by(sortedBy)) ).getContent().size());
                }
                else if(clickedCampaignCategory==1){ // Campaign category is a subcategory so its searched as subcategory
                    System.out.println("case1: "+ bookDAO.findCampaign(PageRequest.of(page, 12 ) ).getContent());
                    theModel.addAttribute("thebooks",  bookDAO.findCampaign(PageRequest.of(page, 12 ) ));
                    theModel.addAttribute("sizeOfTheBooks", bookDAO.findCampaign(PageRequest.of(page, 12 ) ).getContent().size());
                }
                else if(clickedCategoryId!=0){ // category is clicked
                    System.out.println("hello");
                    theModel.addAttribute("thebooks",  bookDAO.findByCategory(clickedCategoryId, PageRequest.of(page, 12, Sort.by(sortedBy)) ));
                    theModel.addAttribute("sizeOfTheBooks", bookDAO.findByCategory(clickedCategoryId, PageRequest.of(page, 12, Sort.by(sortedBy)) ).getContent().size());
                }
                else if(clickedSubcategoryId!=0){  // subcategory is clicked
                    System.out.println("hello2");
                    theModel.addAttribute("thebooks",  bookDAO.findBySubcategory(clickedSubcategoryId, PageRequest.of(page, 12, Sort.by(sortedBy)) ));
                    theModel.addAttribute("sizeOfTheBooks", bookDAO.findBySubcategory(clickedSubcategoryId, PageRequest.of(page, 12, Sort.by(sortedBy)) ).getContent().size());
                }
                else{  // nor subcategory neither category is clicked, all books are sorted ascending order
                    System.out.println("hello3");
                    theModel.addAttribute("thebooks",  bookDAO.findAll(PageRequest.of(page, 12, Sort.by(sortedBy)) ));
                    theModel.addAttribute("sizeOfTheBooks", bookDAO.findAll(PageRequest.of(page, 12, Sort.by(sortedBy)) ).getContent().size());
                }


        }

        theModel.addAttribute("searchForm", new SearchForm());
        theModel.addAttribute("sortedBy", new SortedBy());

        theModel.addAttribute("addToCartForm", new AddToCartForm());

        searchForm = null;

        // count of books
        /*BookForm totalCount = new BookForm();
        List<Book> books = bookDAO.findAll();
        int count = 0;
        for(Book book: books){
            count += book.getCount();
        }
        totalCount.setCount(count);*/
        // theModel.addAttribute("totalCount",totalCount);

        return "user/search-page";
    }


    @PostMapping("/searchbutton")
    private String searchButton(//@ModelAttribute("searchForm") SearchForm searchForm
                                @RequestParam(defaultValue = "") String searchForm){
/*        sortedBy = new SortedBy();
        sortedBy.setName("title");*/

        if(searchForm.isEmpty()){
            return "redirect:/searchpage/listbooks/?clickedGeneralCategory=1";
        }
        System.out.println("searchButton: "+searchForm);
        return "redirect:/searchpage/searchkeyword/?searchForm="+searchForm;
    }

    @GetMapping("/searchkeyword")
    private String searchButton(Model theModel, @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "title") String sortedBy,
                                @RequestParam String searchForm, HttpSession session){

        System.out.println("sort form: "+sortedBy);
        System.out.println("search form: "+searchForm);

        String[] arrOfStr = sortedBy.split("-");
        int year=0;
        float number=0;

        Pattern integer = Pattern.compile("[1-9][0-9]*");//. represents single character
        Pattern floatType = Pattern.compile("[0-9]+.[0-9]+");//. represents single character


        if(integer.matcher(searchForm).matches()){
            year = Integer.parseInt(searchForm);
        }
        if(floatType.matcher(searchForm).matches() || integer.matcher(searchForm).matches()){
            number = Float.parseFloat(searchForm);
        }


        if(arrOfStr.length >1 ){ // if sorted by price is wanted
            if(arrOfStr[1].compareTo("low_to_high") == 0){ // if price is low to high
                theModel.addAttribute("thebooks",  bookDAO.findByTitle(searchForm,year,number,PageRequest.of(page, 12, Sort.by(arrOfStr[0]))));
                theModel.addAttribute("sizeOfTheBooks",  bookDAO.findByTitle(searchForm,year,number,PageRequest.of(page, 12, Sort.by(arrOfStr[0]))).getContent().size());
            }
            else{  // if price is high to low
                theModel.addAttribute("thebooks",   bookDAO.findByTitle(searchForm,year,number,PageRequest.of(page, 12, Sort.by(arrOfStr[0]).descending())));
                theModel.addAttribute("sizeOfTheBooks",  bookDAO.findByTitle(searchForm,year,number,PageRequest.of(page, 12, Sort.by(arrOfStr[0]).descending())).getContent().size());
            }
        }
        else {                  // if sorted by price is not wanted
            System.out.println("search form buraya geliyor mu????");
            theModel.addAttribute("thebooks",   bookDAO.findByTitle(searchForm,year,number,PageRequest.of(page, 12, Sort.by(sortedBy)) ));
            theModel.addAttribute("sizeOfTheBooks", bookDAO.findByTitle(searchForm,year,number,PageRequest.of(page, 12, Sort.by(sortedBy)) ).getContent().size());
        }
        //System.out.println("year: "+year+" number ="+number);
        List<Category> categories = categoryDAO.findAll();
        categories.remove(0);
        theModel.addAttribute("theCategories",categories);
        theModel.addAttribute("searchForm", new SearchForm());
        theModel.addAttribute("sortedBy", new SortedBy());

        boolean addCartSuccessfull = (session.getAttribute("addCardSuccessfull")!=null) ? (boolean) session.getAttribute("addCardSuccessfull"):false;
        theModel.addAttribute("addCartSuccessfull",addCartSuccessfull);
        return "user/search-page";
    }

    @GetMapping("/searchkeywordhandler")
    public String searchkeywordhandler(@RequestParam() int page, @RequestParam() String sortedBy,@RequestParam() String searchForm){
        String parameters="?";
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
    }

    @GetMapping("/listbookshandler")
    public String listbookshandler(@RequestParam() int page, @RequestParam() int clickedGeneralCategory,
                                   @RequestParam() int clickedCampaignCategory, @RequestParam() int clickedCategoryId,
                                   @RequestParam() String sortedBy,@RequestParam() int clickedSubcategoryId){
        String parameters="?";
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
        System.out.println("parameters"+ parameters);
        return "redirect:/searchpage/listbooks/"+parameters;
    }


    @PostMapping("/sortbutton")
    private String sortButton(@ModelAttribute("sortedBy") SortedBy sortedBy){
        return "redirect:/searchpage/listbooks/";
    }
}
