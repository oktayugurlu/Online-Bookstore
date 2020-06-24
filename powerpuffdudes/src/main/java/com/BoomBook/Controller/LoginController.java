package com.BoomBook.Controller;

 
import com.BoomBook.DAO.BookDAO;
import com.BoomBook.DAO.CampaignDAO;
import com.BoomBook.DAO.CategoryDAO;
import com.BoomBook.DAO.CustomerDAO;
 
import com.BoomBook.DAO.*;
import com.BoomBook.Exceptions.TreeMapSorting;
 
import com.BoomBook.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.security.NoSuchAlgorithmException;
 

 
import java.sql.Array;
import java.sql.SQLException;
import java.util.*;
 

@Controller
@RequestMapping("home")
public class LoginController {

    private CustomerDAO customerDAO;
    public UserAuthantication userAuthantication;
    private CategoryDAO categoryDAO;
    private CampaignDAO campaignDAO;
    private BookDAO bookDAO;
    private CartDAO cartDAO;
    private PageAdminDAO pageAdminDAO;


    @Autowired
    public LoginController(CustomerDAO customerDAO, CategoryDAO categoryDAO,CampaignDAO campaignDAO,BookDAO bookDAO,CartDAO cartDAO,PageAdminDAO pageAdminDAO) {
        this.customerDAO = customerDAO;
        this.categoryDAO = categoryDAO;
        this.campaignDAO = campaignDAO;
        this.cartDAO= cartDAO;
        this.bookDAO = bookDAO;
        this.pageAdminDAO = pageAdminDAO;
        userAuthantication = new UserAuthantication();
    }

    @GetMapping("/login")
    public String showLogin(Model model,HttpSession session){
        boolean cartIsSuccessfull=false;
        model.addAttribute("cartIsSuccessfull",cartIsSuccessfull);

        model.addAttribute("registerForm",new RegisterForm());
        model.addAttribute("loginForm",new LoginForm());

        System.out.println(userAuthantication.toString());


        if (!userAuthantication.isComeFromRegister() && !userAuthantication.isComeFromLogin() ){

            userAuthantication.setAllAttributesFalse();
            model.addAttribute("currentUserAut",userAuthantication);

            return "user/login";
        }
        else if (userAuthantication.isComeFromLogin()){

            if(userAuthantication.isAdminLogin()){

                session.setAttribute("username",userAuthantication);
                return "redirect:/managebooks/listbooks";

            }
            else if (userAuthantication.isLoggedin()){

               // session.setAttribute("username",userAuthantication.getUserEmail());
                return "redirect:/home/boombook";
            }
            else if (userAuthantication.isLoginWrongPassword()){

                model.addAttribute("currentUserAut",userAuthantication);
                userAuthantication.setComeFromLogin(false);
                return "user/login";
            }
            else {
                model.addAttribute("currentUserAut",userAuthantication);
                userAuthantication.setComeFromLogin(false);

                return "user/login";
            }
        }

        else {
            System.out.println(userAuthantication.toString());
            model.addAttribute("currentUserAut",userAuthantication);
            userAuthantication.setComeFromRegister(false);
            return "user/login";
        }
    }


    @GetMapping("/boombook")
    public String showHome(Model theModel, HttpSession session) throws SQLException {
        boolean cartIsSuccessfull=false;
        theModel.addAttribute("cartIsSuccessfull",cartIsSuccessfull);

        theModel.addAttribute("theCategories", categoryDAO.findAll());
        System.out.println(userAuthantication.toString());
        session.setAttribute("username",userAuthantication);
        theModel.addAttribute("searchForm",new SearchForm());
        theModel.addAttribute("theCampaigns",campaignDAO.findAll());
        theModel.addAttribute("theNewBooks",campaignDAO.findNewArrivals());
        theModel.addAttribute("theBestSellerBooks",bestSellerBooks());
        List<Customer> customer = customerDAO.findByEmail(userAuthantication.getUserEmail());
        if(customer.size() == 0){
            return "user/index";
        }

        Customer currentCustomer = customer.get(0);

        theModel.addAttribute("theRecommendedBooks",recommendedBooks(currentCustomer.getId()));


        return "user/index";
    }

    @GetMapping("/logout")
    public String logout(Model theModel, HttpSession session){

        System.out.println(userAuthantication.toString());
        userAuthantication.setAllAttributesFalse();
        userAuthantication.setUserEmail(null);
        session.setAttribute("username",userAuthantication);
        theModel.addAttribute("searchForm",new SearchForm());

        return "redirect:/home/boombook";
    }

    @PostMapping("/registerCustomer")
    public String addCustomer(@ModelAttribute RegisterForm registerForm,  Model theModel, HttpSession session) throws NoSuchAlgorithmException {

        List<Customer> customers = customerDAO.findAll();
        for(Customer c:customers){
            if(registerForm.getEmail().equals(c.getEmail()) ){
                //email exists don't allow register.
                userAuthantication.setRegisterExist(true);
                userAuthantication.setWrongCredentials(false);
                userAuthantication.setSuccesfulRegister(false);
                userAuthantication.setComeFromRegister(true);
                userAuthantication.setLoginWrongPassword(false);
                userAuthantication.setComeFromLogin(false);
                userAuthantication.setLoggedin(false);
                userAuthantication.setAdminLogin(false);
                userAuthantication.setLoginWrongEmail(false);

                theModel.addAttribute("currentUserAut",userAuthantication);
                return "redirect:/home/login";
            }
        }

        String plaintName =  registerForm.getName().replaceAll("\\s+","");

        if(!(plaintName.length() != 0 && plaintName.matches("^[a-zA-Z]*$"))){
            userAuthantication.adjustRegisterWrongCredentials();
            theModel.addAttribute("currentUserAut",userAuthantication);
            System.out.println("plainname");
            return "redirect:/home/login";
        }
        if(registerForm.getPassword().contains(" ")){
            userAuthantication.adjustRegisterWrongCredentials();

            theModel.addAttribute("currentUserAut",userAuthantication);
            System.out.println("parola boşluk");
            return "redirect:/home/login";
        }


        if(registerForm.getAddress().length() < 5){
            userAuthantication.adjustRegisterWrongCredentials();
            theModel.addAttribute("currentUserAut",userAuthantication);
            return "redirect:/home/login";

        }

        SHA256 hashObject = new SHA256();
        String hashed_password =  hashObject.toHexString(hashObject.getSHA(registerForm.getPassword()));

        Customer customer= new Customer();
        customer.setEmail(registerForm.getEmail());
        customer.setCustomerPassword(hashed_password);
        customer.setAddress(registerForm.getAddress());
        customer.setCustomerName(registerForm.getName());
        customer.setPhone(registerForm.getPhone());
        customerDAO.save(customer);
        userAuthantication.setRegisterExist(false);
        userAuthantication.setWrongCredentials(false);
        userAuthantication.setLoginWrongPassword(false);
        userAuthantication.setComeFromLogin(false);
        userAuthantication.setLoggedin(false);
        userAuthantication.setAdminLogin(false);
        userAuthantication.setLoginWrongEmail(false);
        userAuthantication.setSuccesfulRegister(true);
        userAuthantication.setComeFromRegister(true);
        theModel.addAttribute("currentUserAut",userAuthantication);

        return "redirect:/home/login";
    }

    @PostMapping("/loginCustomer")
    public String loginCustomer(@ModelAttribute LoginForm loginForm,Model model,HttpSession session) throws NoSuchAlgorithmException {

        SHA256 hashObject = new SHA256();
        String hashed_password =  hashObject.toHexString(hashObject.getSHA(loginForm.getPassword()));
        List<PageAdmin> pageAdmins = pageAdminDAO.findByEmail(loginForm.getEmail());

        if (pageAdmins.size() > 0){
            if(pageAdmins.get(0).getPassword().equals(hashed_password)){

                userAuthantication.setAdminLogin(true);
                userAuthantication.setRegisterExist(false);
                userAuthantication.setWrongCredentials(false);
                userAuthantication.setSuccesfulRegister(false);
                userAuthantication.setComeFromRegister(false);
                userAuthantication.setLoginWrongPassword(false);
                userAuthantication.setComeFromLogin(true);
                userAuthantication.setLoggedin(false);
                userAuthantication.setLoginWrongEmail(false);
                userAuthantication.setUserEmail(loginForm.getEmail());
                model.addAttribute("currentUserAut",userAuthantication);
                session.setAttribute("username",userAuthantication);
                return "redirect:/home/login";
            }

        }

        List<Customer> customers = customerDAO.findByEmail(loginForm.getEmail());

        if ((customers.size() == 0)){

            userAuthantication.setRegisterExist(false);
            userAuthantication.setWrongCredentials(false);
            userAuthantication.setSuccesfulRegister(false);
            userAuthantication.setComeFromRegister(false);
            userAuthantication.setLoginWrongPassword(false);
            userAuthantication.setLoggedin(false);
            userAuthantication.setAdminLogin(false);
            userAuthantication.setComeFromLogin(true);
            userAuthantication.setLoginWrongEmail(true);
            model.addAttribute("currentUserAut",userAuthantication);
            return "redirect:/home/login";
        }

        Customer dbCustomer = customers.get(0);


        if(hashed_password.equals(dbCustomer.getCustomerPassword())){
            // login başarılı
            userAuthantication.setRegisterExist(false);
            userAuthantication.setWrongCredentials(false);
            userAuthantication.setSuccesfulRegister(false);
            userAuthantication.setComeFromRegister(false);
            userAuthantication.setLoginWrongPassword(false);
            userAuthantication.setComeFromLogin(true);
            userAuthantication.setLoggedin(true);
            userAuthantication.setAdminLogin(false);
            userAuthantication.setLoginWrongEmail(false);
            userAuthantication.setUserEmail(loginForm.getEmail());

            //get customer cart that includes not bought but added to cart books.
            userAuthantication.setUserCart(cartDAO.getCartsByUser(dbCustomer.getId()));

            userAuthantication.setTotalCart((cartDAO.getTotalCart(dbCustomer.getId())==null) ? 0 : cartDAO.getTotalCart(dbCustomer.getId()));

            model.addAttribute("currentUserAut",userAuthantication);
            return "redirect:/home/login";
        }

        else {
            userAuthantication.setRegisterExist(false);
            userAuthantication.setWrongCredentials(false);
            userAuthantication.setSuccesfulRegister(false);
            userAuthantication.setComeFromRegister(false);
            userAuthantication.setLoggedin(false);
            userAuthantication.setAdminLogin(false);
            userAuthantication.setLoginWrongEmail(false);
            userAuthantication.setComeFromLogin(true);
            userAuthantication.setLoginWrongPassword(true);
            model.addAttribute("currentUserAut",userAuthantication);
            return "redirect:/home/login";
        }

    }


    @GetMapping("comeFromSuccessfullPurchase")
    String comeFromSuccessfullPurchase(HttpSession session, Model theModel){
        boolean cartIsSuccessfull=false;
        theModel.addAttribute("cartIsSuccessfull",cartIsSuccessfull);

        boolean successfull = (boolean) session.getAttribute("purchaseIsSuccessfull");
        theModel.addAttribute("purchaseIsSuccessfull",true);

        theModel.addAttribute("theCategories", categoryDAO.findAll());
        System.out.println(userAuthantication.toString());
        session.setAttribute("username",userAuthantication);
        theModel.addAttribute("searchForm",new SearchForm());
        theModel.addAttribute("theCampaigns",campaignDAO.findAll());
        theModel.addAttribute("theNewBooks",campaignDAO.findNewArrivals());
        theModel.addAttribute("theBestSellerBooks",bestSellerBooks());
        List<Customer> customer = customerDAO.findByEmail(userAuthantication.getUserEmail());
        if(customer.size() == 0){
            return "user/index";
        }

        Customer currentCustomer = customer.get(0);

        theModel.addAttribute("theRecommendedBooks",recommendedBooks(currentCustomer.getId()));
        return "user/index";
}
    public List<Book> recommendedBooks(int customerID){

        List<Book> books = bookDAO.findAll();
        Map<Integer,Integer> subcategoryStatistics = new TreeMap<>();

        for (Book b: books){

            if (!b.getCarts().isEmpty()){

                for(Cart c: b.getCarts()){

                    if (c.getPurchaseRequest() != null){

                        if (c.getCustomer().getId() == customerID){

                            if (subcategoryStatistics.containsKey(b.getSubcategory().getId())){
                                int oldCount = subcategoryStatistics.get(b.getSubcategory().getId());
                                subcategoryStatistics.put(b.getSubcategory().getId(),oldCount+1);
                            }
                            else {

                                subcategoryStatistics.put(b.getSubcategory().getId(),1);
                            }

                        }
                    }

                }

            }

        }

        for(Book b: books){

            if (!b.getComments().isEmpty()){

                for (BookComment comment : b.getComments()){

                    if (comment.getRate()>2 && comment.getCustomer().getId() == customerID){


                        if (subcategoryStatistics.containsKey(comment.getBook().getSubcategory().getId())){
                            int oldCount = subcategoryStatistics.get(comment.getBook().getSubcategory().getId());
                            subcategoryStatistics.put(comment.getBook().getSubcategory().getId(),oldCount+1);
                        }
                        else {

                            subcategoryStatistics.put(comment.getBook().getSubcategory().getId(),1);
                        }

                    }
                }
            }
        }

        for (Book b:books){

            if (!b.getComments().isEmpty()){

                for (BookComment comment: b.getComments()){

                    if(comment.getRate() != null && comment.getRate() > 3){

                        if (subcategoryStatistics.containsKey(comment.getBook().getSubcategory().getId())){
                            int oldCount = subcategoryStatistics.get(comment.getBook().getSubcategory().getId());
                            subcategoryStatistics.put(comment.getBook().getSubcategory().getId(),oldCount+1);
                        }
                        else {

                            subcategoryStatistics.put(comment.getBook().getSubcategory().getId(),1);
                        }
                    }
                }
            }
        }

        TreeMapSorting treeMapSorting = new TreeMapSorting();
        subcategoryStatistics= treeMapSorting.sortByValues(subcategoryStatistics);

        int recommendedSubCategoryId = -1;
        int currentCounter =0;
        for(Map.Entry<Integer,Integer> entry : subcategoryStatistics.entrySet()) {

            if(currentCounter == subcategoryStatistics.size()-1){
                int key = entry.getKey();
                recommendedSubCategoryId = key;
            }
            currentCounter++;
        }


        List<Book> recommendedBooks = new ArrayList<>();

        for(Book b: books){
            if (b.getSubcategory().getId() == recommendedSubCategoryId){
                if (!recommendedBooks.contains(b)){
                    recommendedBooks.add(b);
                }
            }
        }

        return recommendedBooks;
    }

    public List<Book> bestSellerBooks(){

        Map<Integer,Integer> bookNumbers = new TreeMap<>();
        List<Book> books = bookDAO.findAll();
        List<Book> bestSellers = new ArrayList<>();

        for(Book b: books){
            int total = 0;
            if(!b.getCarts().isEmpty()){

                for (Cart c : b.getCarts()){

                    if(c.getPurchaseRequest() != null ){

                        if (c.getPurchaseRequest().getInCargo() != null){
                            total+= c.getCount();

                        }
                    }

                }

            }

            bookNumbers.put(b.getId(),total);
        }

        TreeMapSorting treeMapSorting = new TreeMapSorting();
        bookNumbers= treeMapSorting.sortByValues(bookNumbers);

        if(bookNumbers.size()<= 8){

            for(Map.Entry<Integer,Integer> entry : bookNumbers.entrySet()) {

                int key = entry.getKey();
                int value = entry.getValue();
                if (value>0){

                    bestSellers.add(bookDAO.findById(key));
                }
            }
        }
        else {
            int limit = 8;
            int counter = 0;
            for(Map.Entry<Integer,Integer> entry : bookNumbers.entrySet()) {

                int key = entry.getKey();
                int value = entry.getValue();
                if (value>0 && (counter >= bookNumbers.size()-limit)){

                    bestSellers.add(bookDAO.findById(key));
                }
                counter++;
            }
        }

        Collections.reverse(bestSellers);
        return bestSellers;


    }

    @GetMapping("cartIsSuccessfull")
    public String addCart(Model theModel, HttpSession session){
        boolean cartIsSuccessfull=true;
        theModel.addAttribute("cartIsSuccessfull",cartIsSuccessfull);

        theModel.addAttribute("purchaseIsSuccessfull",false);

        theModel.addAttribute("theCategories", categoryDAO.findAll());
        System.out.println(userAuthantication.toString());
        session.setAttribute("username",userAuthantication);
        theModel.addAttribute("searchForm",new SearchForm());
        theModel.addAttribute("theCampaigns",campaignDAO.findAll());
        theModel.addAttribute("theNewBooks",campaignDAO.findNewArrivals());
        theModel.addAttribute("theBestSellerBooks",bestSellerBooks());
        List<Customer> customer = customerDAO.findByEmail(userAuthantication.getUserEmail());
        if(customer.size() == 0){
            return "user/index";
        }

        Customer currentCustomer = customer.get(0);

        theModel.addAttribute("theRecommendedBooks",recommendedBooks(currentCustomer.getId()));
        return "user/index";
    }

}
