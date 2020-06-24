package com.BoomBook.Controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.BoomBook.DAO.BookDAO;
import com.BoomBook.DAO.CampaignDAO;
import com.BoomBook.DAO.CategoryDAO;
import com.BoomBook.DAO.SubcategoryDAO;
import com.BoomBook.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Hello

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("managebooks")
public class ManageBooksController {

    private CategoryDAO categoryDAO;
    private SubcategoryDAO subcategoryDAO;
    private BookDAO bookDAO;
    private CampaignDAO campaignDAO;
    public ModelISBN isbnBool;

    int deletingId;
    @Autowired
    public ManageBooksController(@Qualifier("categoryDAO") CategoryDAO theCategoryDAO, @Qualifier("bookDAO") BookDAO theBookDAO, CampaignDAO theCampaignDAO, @Qualifier("subcategoryDAO") SubcategoryDAO theSubcategoryDAO, HttpSession session ) {
        categoryDAO = theCategoryDAO;
        bookDAO = theBookDAO;
        campaignDAO = theCampaignDAO;
        subcategoryDAO = theSubcategoryDAO;
        isbnBool = new ModelISBN();
    }


    @GetMapping("/add-book")
    public String addBookButton(Model theModel, HttpSession session){



        makeFalseIsbnBool();
        // theModel.addAttribute("Currentisbn", isbnBool);
        session.setAttribute("Currentisbn", isbnBool);
        // to turn off error message for same ISBN number

        Book book = new Book();
        BookForm bookForm = new BookForm();

        Subcategory generalSubcategory = subcategoryDAO.findById(1);
        List<Category> categories = categoryDAO.findAll();
        theModel.addAttribute("bookForSave", bookForm);
        theModel.addAttribute("theCategories", categories);
        theModel.addAttribute("theGeneralSubcategory", generalSubcategory);
        return "admin/add-book";
    }


    @PostMapping( "/add-campaign" )
    public String addCampaign(@RequestParam("abookId") int theBookId, @ModelAttribute("campaignButton") CampaignButton theCampaignButton, HttpSession session) {
        makeFalseIsbnBool();
        session.setAttribute("Currentisbn", isbnBool);
        // to turn off error message for same ISBN number

        List<Campaign> isCampaign = campaignDAO.findBooksCampaign(theBookId);

        if(isCampaign.size()!=0){
            if(theCampaignButton.getCampaignPercentage()==0){
                campaignDAO.deleteById(isCampaign.get(0).getId());
            }
            else{
                Campaign thereCampaign = isCampaign.get(0);
                thereCampaign.setDiscountPercentage(theCampaignButton.getCampaignPercentage());
                thereCampaign.setImageURL(theCampaignButton.getImageUrl());
                thereCampaign.setNote(theCampaignButton.getNote());
                campaignDAO.save(thereCampaign);
            }
        }

        else{
            if(theCampaignButton.getCampaignPercentage()==0){
                return "redirect:/managebooks/listbooks";
            }
            Book campaignBook = bookDAO.findById(theBookId);
            Campaign campaign = new Campaign(campaignBook,theCampaignButton.getCampaignPercentage(), theCampaignButton.getNote(), theCampaignButton.getImageUrl()) ;
            campaignDAO.save(campaign);
        }
        return "redirect:/managebooks/listbooks";
    }

    // Changed
    @PostMapping("/saveBook")
    public String submitBook(@ModelAttribute("bookForSave") BookForm theBook, Model theModel, HttpSession session) {
        System.out.println(theBook.toString());
        List<Book> isbnBooks = bookDAO.findByisbn(theBook.getIsbn());
        Book savedBook = new Book();
        System.out.println(theBook.getId());
        if(isbnBooks.size() > 0){
            if(theBook.getId()==0){ // Come from add book screen and isbn error

                System.out.println(theBook.getId());
                isbnBool.setCurrent_isbn(false);
                isbnBool.setComeFromAddBook(true);
                session.setAttribute("Currentisbn", isbnBool);
                // to turn on error message for same ISBN number. Print error message because of same ISBN number.
            }
            else{ // Come from update screen
                savedBook.setSubcategory(subcategoryDAO.findById(theBook.getSubcategory()));
                System.out.println(theBook.getSubcategory());
                savedBook.setId(theBook.getId());
                savedBook.setTitle(theBook.getTitle());
                savedBook.setIsbn(theBook.getIsbn());
                savedBook.setImageURL(theBook.getImageURL());
                savedBook.setPublisherName(theBook.getPublisherName());
                savedBook.setSubject(theBook.getSubject());
                savedBook.setCount(theBook.getCount());
                savedBook.setPrice(theBook.getPrice());
                savedBook.setAuthorName(theBook.getAuthorName());
                savedBook.setYear(theBook.getYear());
                savedBook.setPriceWithCampaign(theBook.getPrice());

                session.setAttribute("Currentisbn", isbnBool);
                // to turn on error message for same ISBN number

                bookDAO.save(savedBook);
            }
        }
        else{
            if(theBook.getId()==0){
                isbnBool.setComeFromAddBook(true);
                isbnBool.setCurrent_isbn(true);

                savedBook.setSubcategory(subcategoryDAO.findById(theBook.getSubcategory()));
                savedBook.setTitle(theBook.getTitle());
                savedBook.setIsbn(theBook.getIsbn());
                savedBook.setImageURL(theBook.getImageURL());
                savedBook.setPublisherName(theBook.getPublisherName());
                savedBook.setSubject(theBook.getSubject());
                savedBook.setCount(theBook.getCount());
                savedBook.setPrice(theBook.getPrice());
                savedBook.setAuthorName(theBook.getAuthorName());
                savedBook.setYear(theBook.getYear());
                savedBook.setPriceWithCampaign(theBook.getPrice());
                // theModel.addAttribute("Currentisbn",isbnBool);
                session.setAttribute("Currentisbn", isbnBool);
                // to turn off error message for same ISBN number

                bookDAO.save(savedBook);
                // use a redirect to prevent duplicate submissions
            }
            else{ // ISBN is changed in update book screen

                savedBook.setSubcategory(subcategoryDAO.findById(theBook.getSubcategory()));
                savedBook.setId(theBook.getId());
                savedBook.setTitle(theBook.getTitle());
                savedBook.setIsbn(theBook.getIsbn());
                savedBook.setImageURL(theBook.getImageURL());
                savedBook.setPublisherName(theBook.getPublisherName());
                savedBook.setSubject(theBook.getSubject());
                savedBook.setCount(theBook.getCount());
                savedBook.setPrice(theBook.getPrice());
                savedBook.setAuthorName(theBook.getAuthorName());
                savedBook.setYear(theBook.getYear());
                savedBook.setPriceWithCampaign(theBook.getPrice());
                // theModel.addAttribute("Currentisbn",isbnBool);
                session.setAttribute("Currentisbn", isbnBool);
                // to turn off error message for same ISBN number

                bookDAO.save(savedBook);
                // use a redirect to prevent duplicate submissions
            }
        }
        return "redirect:/managebooks/listbooks";
    }


    // Added for add-book form to dynamic category option
    @GetMapping("/subcategories")
    public @ResponseBody
    Subcategory[] findAllSubcategories(
            @RequestParam(value = "selectedCatedoryId", required = true) int selectedCatedoryId, Model theModel, HttpSession session)
    {


        makeFalseIsbnBool();
        // theModel.addAttribute("Currentisbn", isbnBool);
        session.setAttribute("Currentisbn", isbnBool);
        // to turn off error message for same ISBN number
        System.out.println("HI");
        Category category = categoryDAO.findById(selectedCatedoryId);
        Subcategory[] myArray = new Subcategory[category.getSubcategoryList().size()];

        category.getSubcategoryList().toArray(myArray);
        System.out.println("HI-1");
        return myArray;
    }



    // Added for add-book form

    @PostMapping("/deletebook")
    public String deletebook(@RequestParam("bookId") int theBookId, Model theModel, HttpSession session) {
        makeFalseIsbnBool();
        // theModel.addAttribute("Currentisbn", isbnBool);
        session.setAttribute("Currentisbn", isbnBool);
        // to turn off error message for same ISBN number

        deletingId = theBookId;
        bookDAO.deleteById(deletingId);
        return "redirect:/managebooks/listbooks";
    }


// Changed

    @PostMapping("/update-book")
    public String showUpdateBook(@RequestParam("bookId") int theId, Model theModel, HttpSession session) {
        makeFalseIsbnBool();
        // theModel.addAttribute("Currentisbn", isbnBool);
        session.setAttribute("Currentisbn", isbnBool);
        // to turn off error message for same ISBN number

        // get the employee from the service
        Book theBook = bookDAO.findById(theId);

        BookForm bookForm = new BookForm();

        bookForm.setAuthorName(theBook.getAuthorName());
        bookForm.setCount(theBook.getCount());
        bookForm.setId(theBook.getId());
        bookForm.setImageURL(theBook.getImageURL());
        bookForm.setIsbn(theBook.getIsbn());
        bookForm.setPrice(theBook.getPrice());
        bookForm.setPublisherName(theBook.getPublisherName());

        bookForm.setSubcategory(theBook.getSubcategory().getId());
        bookForm.setCategory(theBook.getSubcategory().getCategory().getId());
        bookForm.setCategoryForUpdate(theBook.getSubcategory().getCategory().getTitle());
        bookForm.setSubcategoryForUpdate(theBook.getSubcategory().getTitle());

        bookForm.setSubject(theBook.getSubject());
        bookForm.setTitle(theBook.getTitle());
        bookForm.setYear(theBook.getYear());


        // set employee as a model attribute to pre-populate the form
        Subcategory generalSubcategory = subcategoryDAO.findById(1);
        List<Category> categories = categoryDAO.findAll();
        theModel.addAttribute("bookForSave", bookForm);
        theModel.addAttribute("theCategories", categories);
        theModel.addAttribute("theGeneralSubcategory", generalSubcategory);
        theModel.addAttribute("theGeneralSubcategory", generalSubcategory);

        // send over to our form
        return "admin/update-book";
    }


    @GetMapping("/listbooks")
    public String manageBooksMainPage(Model theModel, @RequestParam(defaultValue = "0") int page, HttpSession session){
        // theModel.addAttribute("Currentisbn", isbnBool);


        session.setAttribute("Currentisbn", isbnBool);
        // to turn off error message for same ISBN number

        SearchForm searchForm = new SearchForm();

        CampaignButton campaignButton = new CampaignButton();
        theModel.addAttribute("campaignButton", campaignButton);
        theModel.addAttribute("searchForm", searchForm);

        theModel.addAttribute("thebooks",
                bookDAO.findAll(PageRequest.of(page, 6)));

        //Koray
        BookForm totalCount = new BookForm();
        //Koray

        //Koray
        List<Book> books = bookDAO.findAll();
        int count = 0;
        for(Book book: books){
            count += book.getCount();
        }
        totalCount.setCount(count);
        theModel.addAttribute("totalCount",totalCount);
        //Koray


        return "admin/manage-books";
    }






    //// ---SEARCH SCREEN METHOD---- ////

    @PostMapping("/searchbook")
    public String searchBook(@ModelAttribute("searchForm") SearchForm searchForm, Model theModel){

        CampaignButton campaignButton = new CampaignButton();
        List<Book> books = bookDAO.findAll();
        List<Book> searchedBook = new ArrayList<Book>();

        for(Book b: books){
            if(b.getAuthorName().compareTo(searchForm.getSearchKey()) == 0){
                searchedBook.add(b);
            }
            else if(b.getTitle().compareTo(searchForm.getSearchKey()) == 0){
                searchedBook.add(b);
            }
            else if(b.getPublisherName().compareTo(searchForm.getSearchKey()) == 0){
                searchedBook.add(b);
            }
            else if(b.getSubject().compareTo(searchForm.getSearchKey()) == 0){
                searchedBook.add(b);
            }
            else if(b.getSubcategory().getTitle().compareTo(searchForm.getSearchKey()) == 0){
                searchedBook.add(b);
            }
            else if(b.getSubcategory().getCategory().getTitle().compareTo(searchForm.getSearchKey()) == 0){
                searchedBook.add(b);
            }
            else if(b.getIsbn().compareTo(searchForm.getSearchKey()) == 0){
                searchedBook.add(b);
            }
            else if(Integer.class.isInstance(b)
                        ? String.valueOf(b.getId()).compareTo(searchForm.getSearchKey()) == 0
                        : false){
                searchedBook.add(b);
            }
            else if(searchForm.getSearchKey().isEmpty()){
                searchedBook = new ArrayList<>(books);
                break;
            }
        }

        theModel.addAttribute("campaignButton", campaignButton);
        theModel.addAttribute("searchedBook", searchedBook);
        return "admin/search-screen";
    }

    private void makeFalseIsbnBool(){
        isbnBool.setComeFromAddBook(false);
    }
}
