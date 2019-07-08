package com.blueview.web;


import com.blueview.model.Company;
import com.blueview.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @RequestMapping(value = "/manage")
    public String addCompany(Model model) {
        return  "order/manage";
    }
    @RequestMapping(value = "/company/{id}", method = DELETE, produces = "application/json")
    public String deleteCompany(@RequestParam String id) {
        System.out.println("cloudclient start");
        return id + "===端口：8002被调用了===";
    }
    @RequestMapping(value = "/company/{id}", method = PUT, produces = "application/json")
    public String updateCompany(@RequestParam String id) {
        System.out.println("cloudclient start");
        return id + "===端口：8002被调用了===";
    }
    @RequestMapping(value = "/company/{id}", method = GET, produces = "application/json")
    public Company qureyCompany(@RequestParam String code) {
        Company company = new Company();
        company.setCode(code);
        List<Company> companys = companyService.getCompanysSelective(company);
        if(companys.size() == 1){
            return companys.get(0);
        }else{
            return null;
        }
}

    @RequestMapping(value = "/getcompanys", method = GET, produces = "application/json")
    public  List<Company> getCompanys(Company company) {
        company.setCode("100");
        List<Company> companys = companyService.getCompanysSelective(company);
        return companys;
    }
}

