/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.DAO;

import com.Paladion.teamwork.beans.MapTemplateTaskBean;
import com.Paladion.teamwork.beans.ProjectBean;
import com.Paladion.teamwork.beans.ProjectTransactionBean;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.*;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author user
 */
public class ProjectDAOImpl implements ProjectDAO
{
           @Autowired
           @Qualifier(value="hibernate4AnnotatedSessionFactory")
           private SessionFactory sessionFactory;

           public void setSessionFactory(SessionFactory sessionFactory) {
                       this.sessionFactory = sessionFactory;
           }
	
	@Override
	public void addProjectDao(ProjectBean PB) {
	    Session session1 = sessionFactory.getCurrentSession();
            Transaction tx = null;
	    tx = session1.beginTransaction();
	    session1.save(PB );
	    tx.commit();
            System.out.println("Project created successfully");
      }

	
	@Override
	public List<ProjectBean> getAllProjects() {
		
            Session session1 = sessionFactory.getCurrentSession();
	    Transaction tx = null;
	    tx = session1.beginTransaction();
            Criteria criteria = session1.createCriteria(ProjectBean.class);
	    List <ProjectBean>allProjects = criteria.list();
	    tx.commit();
	    return allProjects;
        }

        @Override
        public ProjectBean getProjectById(int id) {
	   Transaction tx = null;
	   Session session1 = sessionFactory.getCurrentSession();
           tx = session1.beginTransaction();
           String SQL_QUERY1= "from ProjectBean as O where O.projectid=?";
           Query query1 = session1.createQuery(SQL_QUERY1);
           query1.setParameter(0,id);
           List list1 = query1.list();       
           ProjectBean PB = (ProjectBean) list1.get(0);
           tx.commit();
        
           return PB;
      }

    @Override
    public void insertProjectTransaction(List <ProjectTransactionBean> PTBList){
        
        for(ProjectTransactionBean PTBean : PTBList){
                Session session1 = sessionFactory.getCurrentSession();
		Transaction tx = null;
	        tx = session1.beginTransaction();
	        session1.save(PTBean);
	        tx.commit();
		System.out.println("Project transaction updated successfully");
        }
        
    }
    
    
        @Override
       public List<ProjectTransactionBean> getProjectTransaction(int projectid){
        
           List<ProjectTransactionBean> PList;
           Transaction tx = null;
	   Session session1 = sessionFactory.getCurrentSession();
           tx = session1.beginTransaction();
           Criteria criteria = session1.createCriteria(ProjectTransactionBean.class);
         criteria.add(Restrictions.eq("projectid", projectid));
//         criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	   PList = criteria.list();
	 tx.commit();
           return PList;
    }
  
        @Override
       public boolean updateTaskStatus(int transid, String status){
           if(status.equalsIgnoreCase("new")||status.equalsIgnoreCase("progress")||status.equalsIgnoreCase("completed"))
           {
            Session session = this.sessionFactory.openSession();
            Transaction tx;
            tx = session.beginTransaction();
            String sql = "UPDATE projects_transaction SET status=? WHERE transid=?";
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter(0,status);
            query.setParameter(1,transid);
            query.executeUpdate();
            tx.commit();
            return true;
           }           
           
           else{
               System.out.println("Invalid option selected");
               return false;
           }
       }
       
       
             @Override
       public boolean updateTaskStatus(int projid){
            Session session = this.sessionFactory.openSession();
            Transaction tx;
            tx = session.beginTransaction();
            String sql = "UPDATE projects_transaction SET status=? WHERE projectid=?";
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter(0,"Completed");
            query.setParameter(1,projid);
            query.executeUpdate();
            tx.commit();
            return true;
       }
       
       
       
       
       
       
         @Override
       public boolean updateProjectStatus(int projid, String status){
           if(status.equalsIgnoreCase("new")||status.equalsIgnoreCase("progress")||status.equalsIgnoreCase("completed"))
           {
            Session session = this.sessionFactory.openSession();
            Transaction tx;
            tx = session.beginTransaction();
            String sql = "UPDATE projects SET status=? WHERE projectid=?";
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter(0,status);
            query.setParameter(1,projid);
            query.executeUpdate();
            tx.commit();
            return true;
           }           
           
           else{
               System.out.println("Invalid option selected");
               return false;
           }
       }

}