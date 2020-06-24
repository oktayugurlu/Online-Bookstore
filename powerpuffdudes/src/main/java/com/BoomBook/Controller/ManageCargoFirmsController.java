package com.BoomBook.Controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.BoomBook.DAO.*;
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
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("managecargofirms")
public class ManageCargoFirmsController {

    private CourierCompanyDAO courierCompanyDAO;
    private boolean nameError = false;
    private boolean CRUD = false;

    @Autowired
    public ManageCargoFirmsController(@Qualifier("courierCompanyDAO") CourierCompanyDAO theCouriercompanyDAO, HttpSession session ) {
        courierCompanyDAO = theCouriercompanyDAO;
    }
    @GetMapping("/listcargofirms")
    public String manageCargoMainPage(Model theModel,  HttpSession session,@RequestParam(defaultValue = "0") int page ){

        session.setAttribute("CRUD",CRUD);

        session.setAttribute("nameError",nameError);


        CRUD = false;
        nameError = false;

        theModel.addAttribute("thecargo", courierCompanyDAO.findAll(PageRequest.of(page, 6)));

        theModel.addAttribute("totalCount",courierCompanyDAO.findAll().size());


        return "admin/manage-cargo-firms";
    }

    @PostMapping( "/deletecargofirm" )
    public String deletecargo(@RequestParam("cargoId") int theCargoId, Model theModel, HttpSession session) {
        int deletingId = theCargoId;
        courierCompanyDAO.deleteById(deletingId);
        return "redirect:/managecargofirms/listcargofirms";
    }

    @GetMapping("/addCargo")
    public String addCargoButton(Model theModel, HttpSession session) {
        CourierCompany courcomp = new CourierCompany();
        theModel.addAttribute("cargoforSave", courcomp);

        return "admin/add-cargo-firm";
    }

    @PostMapping("/saveCargo")
    public String submitCargo(@ModelAttribute("cargoforSave") CourierCompany cc, Model theModel, HttpSession session) {
        CRUD = true;
        CourierCompany forsaveeCC = new CourierCompany();
        forsaveeCC.setCompanyName(cc.getCompanyName());
        forsaveeCC.setUrl(cc.getUrl());
        forsaveeCC.setPhone(cc.getPhone());
        forsaveeCC.setPrice(cc.getPrice());
        for(CourierCompany cargo : courierCompanyDAO.findAll()){
            if(cargo.getCompanyName().equals(forsaveeCC.getCompanyName())){
                nameError = true;
                theModel.addAttribute("nameError",nameError);
                return "redirect:/managecargofirms/listcargofirms";
            }
        }
        courierCompanyDAO.save(forsaveeCC);
        return "redirect:/managecargofirms/listcargofirms";
    }

    @PostMapping("/updateCargo")
    public String showUpdateCargo(@RequestParam("cargoId") int theCargoId, Model theModel, HttpSession session) {
        // get the employee from the service
        CourierCompany cc = courierCompanyDAO.findById(theCargoId);


        theModel.addAttribute("cargoforSave", cc);

        return "admin/update-cargo-firm";
    }

    @PostMapping("/saveCargoforUpdate")
    public String submitCargoUpdate(@ModelAttribute("cargoforSave") CourierCompany cc, Model theModel, HttpSession session) {
        CRUD = true;
        CourierCompany forsaveeCC = new CourierCompany();
        forsaveeCC.setId(cc.getId());
        forsaveeCC.setCompanyName(cc.getCompanyName());
        forsaveeCC.setUrl(cc.getUrl());
        forsaveeCC.setPhone(cc.getPhone());
        forsaveeCC.setPrice(cc.getPrice());

        for(CourierCompany cargo : courierCompanyDAO.findAll()){
            if(cargo.getCompanyName().equals(forsaveeCC.getCompanyName())){
                if(cargo.getId() != forsaveeCC.getId()){
                    nameError = true;
                    theModel.addAttribute("nameError",nameError);
                    return "redirect:/managecargofirms/listcargofirms";
                }
            }
        }
        courierCompanyDAO.save(forsaveeCC);
        return "redirect:/managecargofirms/listcargofirms";
    }

}
