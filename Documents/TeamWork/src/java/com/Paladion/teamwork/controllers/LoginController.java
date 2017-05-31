/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.controllers;

import com.Paladion.teamwork.DAO.ProjectDAO;
import com.Paladion.teamwork.DAO.ProjectDAOImpl;
import com.Paladion.teamwork.beans.LoginBean;
import com.Paladion.teamwork.beans.ProjectBean;
import com.Paladion.teamwork.beans.UserBean;
import com.Paladion.teamwork.services.LoginService;
import com.Paladion.teamwork.services.ProjectService;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.catalina.tribes.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
/**
 *
 * @author Administrator
 */
@Controller
public class LoginController {
    
 public LoginController() 
 {   }
 
 @Autowired
 @Qualifier(value="LoginService")
 LoginService LS;
 
 
 UserBean ub=null;
 LoginBean lb=null;
 
 
 @ModelAttribute("LoginM")
 public LoginBean PopulateLoginBean() 
{
   return new LoginBean(); // populates form for the first time if its null
}
 
 
 
@RequestMapping(value="/Login",method=RequestMethod.GET)
public String Login()
{
return "Login";
}

@RequestMapping(value="/ForgotPassword",method=RequestMethod.GET)
public String forgot()
{
 
return "ForgotPassword";
}

//forgot password starts
@RequestMapping(value="/ForgotPassword",method=RequestMethod.POST)
public ModelAndView Forgot(@ModelAttribute("ForgotM")LoginBean LB,HttpServletRequest req )
    {
        System.out.println("forgotPassword");
       
	  lb=LS.ForgotPassword(LB);
        if (lb!=null) {
            HttpSession LoginSess=req.getSession(true);
            LoginSess.setAttribute("Luser", ub);
            return new ModelAndView("redirect:/ForgotPassword.do");}
        else {
           return new ModelAndView("ForgotPassword","Lerror", "If your Email id is valid you will receive a email from us");
        }
        }

//forgot password ends
 
@RequestMapping(value="/Login",method=RequestMethod.POST)
public ModelAndView Login(@ModelAttribute("LoginM")LoginBean LB,HttpServletRequest req )
    {
        System.out.println("in login");
        lb=LS.Login(LB);
        if (lb!=null) {
            HttpSession LoginSess=req.getSession(true);
            LoginSess.setAttribute("Luser", lb);
           
	    return new ModelAndView("redirect:/Welcome.do");
	}
        else {
           return new ModelAndView("Login","Lerror", "Login Failed");
        }
        }

@RequestMapping(value="/Welcome",method=RequestMethod.GET)
public ModelAndView Welcome()
{
return new ModelAndView("Welcome");
}

@RequestMapping(value="/Logout",method=RequestMethod.GET)
public String Logout(HttpServletRequest req)
{
 LS.Logout(req.getSession());
return "redirect:Login.do";
}
    }

   
