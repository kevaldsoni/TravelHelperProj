package com.travelhelper.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.travelhelper.model.UserProfile;


@Repository
@EnableTransactionManagement
public class UserProfileDAOImpl implements UserProfileDAO{
	
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	
	@Override
	public UserProfile findById(int id) {
		// TODO Auto-generated method stub
		System.out.println("Find ID");
		Session session = this.sessionFactory.getCurrentSession();
		UserProfile profile = (UserProfile) session.load(UserProfile.class, new Integer(id));
		return profile;
	}


	@Override
	public List<UserProfile> listProfile() {
		// TODO Auto-generated method stub
		System.out.println("In UserProfileDAOImpl :: listProfile");
		List<UserProfile> users = null;
		try{
			Session session = this.sessionFactory.getCurrentSession();
			users = session.createQuery("from UserProfile").list();
			for (UserProfile p : users){
				System.out.println("First Name :"+p.getFirstName());
				System.out.println("Last Name :"+p.getLastName());
				System.out.println("Zip Code :"+p.getAddrZip());
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return users;
	}
	
	public int addNewUserDetailsToAccount(UserProfile account){
		System.out.println("Creating new profile in Database : addNewUserDetailsToAccount : "+account.getUsername());
		int id=0;
		Transaction tx = null;
		Session session = this.sessionFactory.openSession();
		try{
			tx=session.beginTransaction();
			account.setActive(1);
			id = (Integer)session.save(account);			
			if(id > 0){
				System.out.println("Account Updated for new user !! with id :: "+id);
			}else{
				System.out.println("Account update Failed");
			}
			tx.commit();
		}catch(HibernateException e){
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return id;
	}
	
	
	
}
