package com.BoomBook.Controller;

import com.BoomBook.DAO.*;
import com.BoomBook.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("managecategories")
public class ManageCategoriesController {
    private CategoryDAO categoryDAO;
    private SubcategoryDAO subcategoryDAO;
    private CustomerDAO customerDAO;
    private BookDAO bookDAO;
    private CampaignDAO campaignDAO;
    private boolean CRUD = false;
    private boolean nameError = false;
    private boolean subcategoryNameError = false;
    private boolean areSubcategoryAndCategoryNamesSame = false;
    private boolean areSubcategoryAndCategoryNamesSameForCategory = false;
    int deletingId;


    @Autowired
    public ManageCategoriesController(@Qualifier("categoryDAO") CategoryDAO theCategoryDAO, @Qualifier("subcategoryDAO") SubcategoryDAO theSubcategoryDAO, HttpSession session ) {
        categoryDAO = theCategoryDAO;
        subcategoryDAO = theSubcategoryDAO;
    }

    @GetMapping("/listcategories")
    public String manageCategoryMainPage( Model theModel, @RequestParam(defaultValue = "0") int page, HttpSession session){
        session.setAttribute("CRUD",CRUD);
        CRUD = false;

        session.setAttribute("nameError" , nameError);
        nameError = false;

        session.setAttribute("subcategoryNameError" , subcategoryNameError);
        subcategoryNameError = false;

        session.setAttribute("areSubcategoryAndCategoryNamesSame", areSubcategoryAndCategoryNamesSame);
        areSubcategoryAndCategoryNamesSame = false;

        session.setAttribute("areSubcategoryAndCategoryNamesSameForCategory" , areSubcategoryAndCategoryNamesSameForCategory);
        areSubcategoryAndCategoryNamesSameForCategory = false;

        CategoryButton categoryButton = new CategoryButton();
        theModel.addAttribute("categoryButton", categoryButton);
        SubcategoryButton subcategoryButton = new SubcategoryButton();
        theModel.addAttribute("subcategoryButton", subcategoryButton);

        Category category = new Category();
        theModel.addAttribute("categoryForSave" , category);

        SubcategoryButton addSubcategoryButton = new SubcategoryButton();
        theModel.addAttribute("addSubcategoryButton",addSubcategoryButton);

        List<Category> theCategories = categoryDAO.findAll();
        for(int i=0;i<theCategories.size();i++){
            if(theCategories.get(i).getId() == -1){
                System.out.println(theCategories.get(i).getTitle());
                theCategories.remove(theCategories.get(i));
               // categoryDAO.deleteById(theCategories.get(i).getId());
            }
        }

        theModel.addAttribute("thecategories", theCategories);
        /*
        theModel.addAttribute("thecategories",
                categoryDAO.findAll(PageRequest.of(page, 6)));

         */
        List<Category> categories = categoryDAO.findAll();

        int totalCategory = categories.size();
        theModel.addAttribute("totalCategory",totalCategory);

        return "admin/manage-categories";
    }

    @PostMapping("/update-category")
    public String showUpdateCategory(@RequestParam("categoryId") int theId, @ModelAttribute("categoryButton") CategoryButton theCategoryButton, Model theModel, HttpSession session) {
        CRUD = true;
        Category theCategory = categoryDAO.findById(theId);
        System.out.println("Update clicked" + "id is: " + theId);

        theCategory.setTitle(theCategoryButton.getTitle());

        for(Subcategory subcategory: theCategory.getSubcategoryList()){
            if(subcategory.getTitle().equals(theCategory.getTitle())){
                areSubcategoryAndCategoryNamesSameForCategory = true;
                return "redirect:/managecategories/listcategories";
            }
        }

        for(Category category: categoryDAO.findAll()){
            if(category.getTitle().equals(theCategory.getTitle()) && category.getId() != theCategory.getId()){
                nameError = true;
                return "redirect:/managecategories/listcategories";
            }
        }

        System.out.println(theCategoryButton.getTitle());
        categoryDAO.save(theCategory);

        return "redirect:/managecategories/listcategories";
    }

    @PostMapping("/saveCategory")
    public String submitCategory(@ModelAttribute("categoryForSave") Category theCategory,Model theModel) {
        CRUD = true;
        Category savedCategory = new Category();
        savedCategory.setSubcategoryList(theCategory.getSubcategoryList());
        savedCategory.setTitle(theCategory.getTitle());

        System.out.println(theCategory.getTitle());


        for(Category category: categoryDAO.findAll()){
            if(category.getTitle().equals(savedCategory.getTitle()) && category.getId() != savedCategory.getId()) {
                nameError = true;
                theModel.addAttribute("nameError" , nameError);
                return "redirect:/managecategories/listcategories";
            }
        }

        categoryDAO.save(savedCategory);
        return "redirect:/managecategories/listcategories";
    }

    @PostMapping("/saveSubcategory")
    public String submitsubcategory(@RequestParam("categoryId") int theId, @ModelAttribute("addSubcategoryButton") SubcategoryButton addSubcategoryButton) {
        CRUD = true;
        Category theCategory = categoryDAO.findById(theId);
        System.out.println("Add clicked" + " id is: " + theId);

        if(addSubcategoryButton.getTitle().equals(theCategory.getTitle())){
            areSubcategoryAndCategoryNamesSame = true;
            return "redirect:/managecategories/listcategories";
        }

        for(Subcategory subcategory: theCategory.getSubcategoryList()){
            if(addSubcategoryButton.getTitle().equals(subcategory.getTitle())){
                subcategoryNameError = true;
                System.out.println("true");
                return "redirect:/managecategories/listcategories";
            }
        }
        theCategory.getSubcategoryList().add(new Subcategory(addSubcategoryButton.getTitle(), theCategory));

        //System.out.println("AAAAAAAAAAAA " + theCategory.getTitle() + " " + addSubcategoryButton.getId());

        categoryDAO.save(theCategory);
        return "redirect:/managecategories/listcategories";
    }

    @PostMapping("/update-subcategory")
    public String showUpdateSubCategory(@RequestParam("subcategoryId") int theId, @ModelAttribute("subcategoryButton") SubcategoryButton theSubcategoryButton, Model theModel, HttpSession session) {
        CRUD = true;
        Subcategory theSubCategory = subcategoryDAO.findById(theId);
        System.out.println("Update clicked" + "id is: " + theId);

        theSubCategory.setTitle(theSubcategoryButton.getTitle());
        System.out.println(theSubCategory.getId());

        Category category = categoryDAO.findById(theSubCategory.getCategory().getId());

        if(category.getTitle().equals(theSubCategory.getTitle())){
            areSubcategoryAndCategoryNamesSame = true;
            return "redirect:/managecategories/listcategories";
        }
        for(Subcategory subcategory: category.getSubcategoryList()){
            if(theSubCategory.getTitle().equals(subcategory.getTitle()) && theSubCategory.getId() != subcategory.getId()){
                subcategoryNameError = true;
                return "redirect:/managecategories/listcategories";
            }
        }

        System.out.println(theSubcategoryButton.getTitle());
        subcategoryDAO.save(theSubCategory);

        return "redirect:/managecategories/listcategories";
    }


    @PostMapping("/deletecategory")
    public String deletecategory(@RequestParam("categoryId") int theCategoryId, Model theModel, HttpSession session) {

        deletingId = theCategoryId;
        categoryDAO.deleteById(deletingId);
        System.out.println("Deleted");
        System.out.println("id is : " + theCategoryId);
        System.out.println("AAAAAAAAAAAAAAAAAAAAA");
        return "redirect:/managecategories/listcategories";
    }

    @PostMapping("/deletesubcategory")
    public String deletesubcategory(@RequestParam("subcategoryId") int theSubCategoryId) {


        subcategoryDAO.deleteById(theSubCategoryId);
        System.out.println("Deleted");
        System.out.println("id is : " + theSubCategoryId);
        return "redirect:/managecategories/listcategories";
    }




}
