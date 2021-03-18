package com.example.biddera;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebController {

    private int userID ;
    private int pId;

    WebController(){
        userID = -1;
    }
    @GetMapping("/")
    public String LandingPage(Model model) {
        return "landing";
    }


    @GetMapping("/registerproduct")
    public String productRegistrationForm(Model model) {
        if ( userID == -1 ){
            model.addAttribute("Login", new Login());
            return "Login";
        }
        model.addAttribute("registerproduct", new RegisterProduct());
        return "registerproduct";
    }

    @PostMapping("/registerproduct")
    public String greetingSubmit(@ModelAttribute RegisterProduct registerProduct, Model model) {
        DBHandler dbh = new DBHandler();
        model.addAttribute("registerproduct", registerProduct);
        registerProduct.setUserid(userID);
        dbh.registerProduct(registerProduct);
        System.out.println(registerProduct.getCategory());
        return ShowAllPOD(model);
    }

    @PostMapping("/Id")
    public String showDetails(@ModelAttribute Id id,Model model) {
        System.out.println("id: " +id.getMid());
        pId = Integer.parseInt(id.getMid()) ;
        DBHandler dbHandler = new DBHandler();
        Details d = dbHandler.getProductDEtails(id.getMid());
        model.addAttribute("details", d);
        //Article article = new Article();
        Bid b = new Bid();
        b.setB((int)d.getMaxbid());
        model.addAttribute("Bid", b);
        return "productdetails";

    }
    @PostMapping("/mybid")
    public String getBid(@ModelAttribute Bid b,Model model) {
        DBHandler dbh = new DBHandler();
        dbh.addBid(pId, userID, b.getB());
        return ShowAllPOD(model);
    }


    @GetMapping("/allproducts")
    public String ShowAllPOD(Model model) {
        if ( userID == -1 ){
            model.addAttribute("Login", new Login());
            return "Login";
        }
        DBHandler dbh = new DBHandler();
        model.addAttribute("continents", dbh.getAllPODs());
        model.addAttribute("Id", new Id());
        return "continents";
    }
   
    
    @GetMapping("/register")
    public String AccountRegistrationForm(Model model) {
        model.addAttribute("register", new Register());
        return "Register";
    }

    @PostMapping("/register")
    public String SccountRegisterSubmit(@ModelAttribute Register register, Model model) {
        model.addAttribute("register", register);
        DBHandler dbHandler = new DBHandler();
        dbHandler.AddNewUser(register);
        model.addAttribute("Login", new Login());
        return "Login";
    }

    @GetMapping("/Login")
    public String UserLoginForm(Model model) {
        model.addAttribute("Login", new Login());
        return "Login";
    }

    @PostMapping("/Login")
    public String UserLoginFormAction(@ModelAttribute Login login, Model model) {
        model.addAttribute("Login", login);
        DBHandler dbHandler = new DBHandler();
        userID = dbHandler.VerifyUser(login);

        if ( userID == -1 ){
            System.out.println("Login failed");
            model.addAttribute("message", "Login failed! invalid username or password.");
            return "Login";

        }
        model.addAttribute("message", "Login successful!.");
       
        model.addAttribute("continents", dbHandler.getAllPODs());
        model.addAttribute("Id", new Id());
        return "continents";
    }
    
    @GetMapping("/myregisteredproducts")
    public String showMyRegisteredProducts(Model model) {
        if ( userID == -1 ){
            model.addAttribute("Login", new Login());
            return "Login";
        }
        DBHandler dbh = new DBHandler();
        model.addAttribute("allregisteredproducts", dbh.getMyRegisteredProducrs(userID));
        return "trackproducts";
    }


      
    @GetMapping("/mybids")
    public String showMyBids(Model model) {
        if ( userID == -1 ){
            model.addAttribute("Login", new Login());
            return "Login";
        }
        DBHandler dbh = new DBHandler();
        model.addAttribute("allregisteredproducts", dbh.getMyBids(userID));
        return "myBids";
    }
    
    //Edit profile
    @GetMapping("/editprofile")
    public String AccountUpdateForm(Model model) {
        if ( userID == -1 ){
            model.addAttribute("Login", new Login());
            return "Login";
        }
        DBHandler dbHandler = new DBHandler();
        model.addAttribute("register", dbHandler.getRegistrationDetails(userID));
        return "updateprofile";
    }

    @PostMapping("/editprofilesubmit")
    public String AccountUpdateSubmit(@ModelAttribute Register register, Model model) {
        register.setId(userID);
        model.addAttribute("register", register);
        System.out.println(register.getCardnumber());
        DBHandler dbHandler = new DBHandler();
        dbHandler.updateRegistrationDetails(register);
        return "continents";
    }

    @GetMapping("/logout")
    public String UserLogout( Model m ){
        userID = -1;
        pId = -1;
        return UserLoginForm(m);
    }



    //controllers for admin
    @GetMapping("/admin")
    public String AdminLoginForm(Model model) {
        model.addAttribute("Login", new Login());
        return "admin";
    }

    @PostMapping("/admin")
    public String AdminLoginFormAction(@ModelAttribute Login login, Model model) {
        model.addAttribute("Login", login);
        DBHandler dbHandler = new DBHandler();
        if ( dbHandler.VerifyAdmin(login) == -1 ){
            System.out.println("Login failed");
            model.addAttribute("message", "Login failed! invalid username or password.");
            return "admin";
        }
       
        return AdminallAccounts(model);
    }


    @GetMapping("/allaccounts")
    public String AdminallAccounts(Model model) {
        DBHandler dbHandler  = new DBHandler();
        model.addAttribute("accounts", dbHandler.getAllUserAccounts());
        return "allaccounts";
    }

    @GetMapping("/allregisteredproducts")
    public String AdminallProducts(Model model) {
        DBHandler dbHandler  = new DBHandler();
        model.addAttribute("products", dbHandler.getAllRegisteredProducrs());
        return "allregisteredproducts";
    }
    
    @GetMapping("/allbids")
    public String AdminallBids(Model model) {
        DBHandler dbHandler  = new DBHandler();
        model.addAttribute("bids", dbHandler.getAllbids());
        return "allbids";
    }

    @GetMapping("/adminlogout")
    public String AdminLogout( Model m ){
        return AdminLoginForm(m);
    }




}
