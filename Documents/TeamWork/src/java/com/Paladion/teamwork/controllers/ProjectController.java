/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.controllers;

import com.Paladion.teamwork.beans.EmailBean;
import com.Paladion.teamwork.beans.UserDataBean;
import com.Paladion.teamwork.beans.MapTemplateTaskBean;
import com.Paladion.teamwork.beans.ProjectBean;
import com.Paladion.teamwork.beans.TemplateBean;
import com.Paladion.teamwork.beans.ProjectTransactionBean;
import com.Paladion.teamwork.beans.ProjectTransactionWrapper;
import com.Paladion.teamwork.services.ProjectService;
import com.Paladion.teamwork.services.TemplateService;
import com.Paladion.teamwork.services.UserService;
import com.Paladion.teamwork.utils.CommonUtil;
import com.Paladion.teamwork.utils.EmailUtil;
import java.sql.Time;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Administrator
 */
@Controller
public class ProjectController {
	

@Autowired
@Qualifier(value="TemplateService")
TemplateService TS;

@Autowired
@Qualifier(value="CommonUtil")
CommonUtil CU;
    
@Autowired
@Qualifier(value="ProjectService")
ProjectService PS;

@Autowired
@Qualifier(value="UserService")
UserService US;
	
@ModelAttribute("ProjectM")
public ProjectBean populate()
{
	   return new ProjectBean();
}

	
@RequestMapping(value="/CreateProject",method=RequestMethod.GET)
public ModelAndView CreateProject(HttpServletRequest req)
{    
	HttpSession sess=req.getSession(false);
	List <TemplateBean> TemplateList;
        List <UserDataBean> LeadList;
	ModelAndView model=new ModelAndView("CreateProject");
	System.out.println("Inside Project controller for get method");
	try{
	        TemplateList=TS.getAllTemplates();
                LeadList=CU.getUsersByRole("lead",sess);
		model.addObject("AllTemplates", TemplateList);
                model.addObject("AllLeads", LeadList);
	    }catch(Exception ex){}
	return model;
}


@RequestMapping(value="/AddProject",method=RequestMethod.POST)
    public Object CreateNewProject(@ModelAttribute("ProjectM")ProjectBean PB,HttpServletRequest req,Model E) throws Exception
    {
        HttpSession sess= req.getSession(false);
           ModelAndView result = null;
           try{
	            System.out.println("\n inside create Project POST method ");
                    PB.setMandays(CU.getWorkingDays(PB.getStartdate(),PB.getEnddate()));
                    PB.setStatus("New");
                    PB.setLead(CU.getUsernameFromSession(PB.getLeadid(), sess));
                    PS.addProject(PB);
                    //send mail to lead                    
                    EmailUtil EU=new EmailUtil();
                    EmailBean ebean=new EmailBean();
                    ebean.setTo(CU.getUserMailById(PB.getLeadid(), req.getSession(false)));
                    ebean.setSubject("Project Scheduling Mail");
                    String message="Dear "+PB.getLead()+"\n\nYou have been assigned to "+PB.getProjectname()+" as lead. Please find the below projet details.\n\n\nOPID:    "+PB.getOpid()+"\nStart Date:"+PB.getStartdate()+"\nEnd Date: "+PB.getEnddate()+"\n\n\nRegards\nTeam Paladion";
                    ebean.setMessage(message);
                    EU.sendEmail(ebean);
	            System.out.println("Project Created with Project id"+PB.getProjectid());
	            System.out.println("Man days :"+PB.getMandays());
                }
           catch(Exception ex){
                      List <TemplateBean> TemplateList;
                      TemplateList=TS.getAllTemplates();
	              result = new ModelAndView("CreateProject","Message","Project Creation failed");
                      result.addObject("AllTemplates", TemplateList);
                      return result;
            }
           
           ProjectTransactionWrapper PTW=new ProjectTransactionWrapper();
           List<ProjectTransactionBean> PSBList;
           ProjectBean PRDATA=PS.getProjectById(PB.getProjectid());
           List<MapTemplateTaskBean> MTTB=TS.getAllWeights(PRDATA.getTemplateid());
           PSBList=  CU.setTaskHours(PRDATA, MTTB);
           PTW.setProjectlist(PSBList);
           result=new ModelAndView("AssignTaskToUsers");
           List<UserDataBean> Alleng=CU.getUsersByRole("engineer", sess);
           result.addObject("AllEngineers",Alleng);
        
           result.addObject("ProjectW",PTW);
           return result;
	    
    }
    
    
    
    public String updateProject(ProjectBean pBean){return "";}
    public String deleteProject(String id){return "";}
    
    @RequestMapping(value="/showAllProject",method=RequestMethod.GET)
    public ModelAndView showAllProject(HttpServletRequest req)
    {
        HttpSession sess= req.getSession(false);
        UserDataBean sessuser=(UserDataBean) sess.getAttribute("Luser");
	ModelAndView result=new ModelAndView("DisplayProjects");
        List<ProjectBean> PBList=(List<ProjectBean>)PS.getAllProjects(sessuser.getUserid(), sessuser.getRole());
        
	result.addObject("AllProjects",PBList );
	return  result;
    }
      @RequestMapping(value="/AddProject", method=RequestMethod.GET)
      public ModelAndView AssignTask(){
          
           ModelAndView result= new ModelAndView("AssignTaskToUsers");
           result.addObject("AllEngineers",null);
           result.addObject("ProjectW",null);
           result.addObject("Message","Please Select a Project From the Project List");
           return result;
      }
    
    @RequestMapping(value="/AssignTaskToEngineers", method=RequestMethod.POST)
    public ModelAndView AssignTaskToEngineer(@ModelAttribute("ProjectW")ProjectTransactionWrapper ProjectW,HttpServletRequest req) throws Exception
    {
        String projid=req.getParameter("projectid");
        int projectid=Integer.parseInt(projid);
        ProjectBean PRDATA=PS.getProjectById(projectid);
	List <ProjectTransactionBean> PTBList=ProjectW.getProjectlist();
        List <ProjectTransactionBean> PTBList1=new ArrayList<ProjectTransactionBean>();
        CommonUtil cu=new CommonUtil();
        PTBList1= cu.updateProjectTransaction(PTBList, PRDATA,req.getSession(false));
        PS.insertProjectTransaction(PTBList1);
        ModelAndView result=new ModelAndView("DisplayProjectProgress");
        result.addObject("TaskDetails",PTBList1);
        result.addObject("ProjectData",PRDATA);
        return result;
    }
    

    @RequestMapping(value="/showProgress",method=RequestMethod.GET)
    public ModelAndView showProjectProgress(@RequestParam int id,HttpServletRequest req) throws ParseException
    {
           ModelAndView result;
           List<ProjectTransactionBean> PSBList;
           ProjectBean PRDATA=PS.getProjectById(id);
           PSBList = PS.getProjectTransaction(id);
           if(PSBList.size()==0){
           HttpSession sess=req.getSession(false);
           ProjectTransactionWrapper PTW=new ProjectTransactionWrapper();
           List<MapTemplateTaskBean> MTTB=TS.getAllWeights(PRDATA.getTemplateid());
           PSBList=  CU.setTaskHours(PRDATA, MTTB);
           PTW.setProjectlist(PSBList);
           result=new ModelAndView("AssignTaskToUsers");
           List<UserDataBean> Alleng=CU.getUsersByRole("engineer", sess);
           result.addObject("AllEngineers",Alleng);
           result.addObject("ProjectW",PTW);
           return result;
           }
           else{
           result=new ModelAndView("DisplayProjectProgress");
           result.addObject("ProjectData",PRDATA);
           result.addObject("TaskDetails",PSBList);
           return result;
           }
    }
    
     @RequestMapping(value="/updateTaskStatus",method=RequestMethod.GET)
    public ModelAndView updateTaskStatus(@RequestParam int pid,@RequestParam int tid, @RequestParam String status) throws ParseException
    {
        boolean value= PS.updateTaskStatus(tid,status);
        if(value==true){
           List<ProjectTransactionBean> PSBList;
           ProjectBean PRDATA=PS.getProjectById(pid);
           PSBList = PS.getProjectTransaction(pid);
 
           ModelAndView result=new ModelAndView("DisplayProjectProgress");
           result.addObject("ProjectData",PRDATA);
           result.addObject("TaskDetails",PSBList);
           return result;
        }
        
        else{
            ModelAndView result=new ModelAndView("Customerror");
            result.addObject("Message","Something Went Wrong");
            return result;
        }
    }
    
    
    
    
    @RequestMapping(value="/updateProjectStatus",method=RequestMethod.GET)
    public ModelAndView updateProjectStatus(@RequestParam int pid,@RequestParam String status,HttpServletRequest req) throws ParseException
    {
        HttpSession sess= req.getSession(false);
        UserDataBean sessuser=(UserDataBean) sess.getAttribute("Luser");
        boolean value= PS.updateProjectStatus(pid,status);
        if(status.equalsIgnoreCase("completed")){
          //  PS.updateTaskStatus(pid);
        }
        if(value==true){
          ModelAndView result=new ModelAndView("DisplayProjects");
	  result.addObject("AllProjects", PS.getAllProjects(sessuser.getUserid(), sessuser.getRole()));
	  return  result;
        }
        
        else{
            ModelAndView result=new ModelAndView("Customerror");
            result.addObject("Message","Something Went Wrong");
            return result;
        }
    }
    
    
    
    
     @RequestMapping(value="/updateTaskDelay",method=RequestMethod.POST)
    public ModelAndView updateTaskDelay(HttpServletRequest req) throws ParseException
    {
        //under progress
        String transid=req.getParameter("transId");
        String delay=req.getParameter("taskDelayTime");
        
        
        
        return new ModelAndView("Welcome");
    }
    
}
