/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.controllers;

import com.Paladion.teamwork.beans.LoginBean;
import com.Paladion.teamwork.beans.TaskBean;
import com.Paladion.teamwork.beans.UserBean;
import com.Paladion.teamwork.services.LoginService;
import com.Paladion.teamwork.services.TaskService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Administrator
 */
@Controller
public class TaskController {

@ModelAttribute("TaskM")
public TaskBean populate()
{
	   return new TaskBean();
}
	
@RequestMapping(value="/CreateTask",method=RequestMethod.GET)
     public String CreateTask()
    {    return "CreateTask";
    }
	
@RequestMapping(value="CreateTask",method=RequestMethod.POST)
    public String updateTask(@ModelAttribute("TaskM")TaskBean TB,HttpServletRequest req) 
    {return "";}
    public String deleteTask(String id){return "";}
    
}
