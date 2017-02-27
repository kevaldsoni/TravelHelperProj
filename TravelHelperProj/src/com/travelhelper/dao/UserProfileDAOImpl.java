package com.travelhelper.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.travelhelper.model.FutureTravel;
import com.travelhelper.model.GoogleNotification;
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


	@Override
	public boolean saveGoogleNotificationId(String id) {
		// TODO Auto-generated method stub
		GoogleNotification notification = new GoogleNotification();
		notification.setUserId(2);
		notification.setGcmRegId(id);
		notification.setActive(1);
		Transaction tx = null;
		Session session = this.sessionFactory.openSession();
		try{
			tx=session.beginTransaction();
			session.save(notification);
			tx.commit();
		}catch(HibernateException e){
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return true;
	}


	@Override
	public boolean updatelastUsedGcmId(int userId) {
		System.out.println("Updating Past GCM ID : updatelastUsedGcmId for userid :"+userId);
		Transaction tx = null;
		Session session = this.sessionFactory.openSession();
		try{
			tx=session.beginTransaction();
			Criteria cr = session.createCriteria(GoogleNotification.class);
			cr.add(Restrictions.eq("userId", userId));
			List results = cr.list();
			if(results!=null && results.size()>0){
				for (Iterator iterator = results.iterator(); iterator.hasNext();){
					GoogleNotification pobj = (GoogleNotification) iterator.next();
					pobj.setActive(0);
					session.update(pobj);
				}
			}else{
				System.out.println("updateFinalUserRating :: Result not  found");
			}
			tx.commit();
		}catch(HibernateException e){
			if(tx != null){
				tx.rollback();
				e.printStackTrace();
			}
		}finally{
			session.close();
		}
		return false;
	}


	@Override
	public int fetchUserIdfromUsername(String username) {
		System.out.println("fetchUserIdfromUsername "+username);
		int userid = 0;
		Transaction tx = null;
		Session session = this.sessionFactory.openSession();
		try{
			tx=session.beginTransaction();
			Criteria cr = session.createCriteria(UserProfile.class);
			cr.add(Restrictions.eq("username", username));
			List results = cr.list();
			if(results!=null && results.size()>0){
				UserProfile pobj = (UserProfile) results.get(0); 
				if(pobj != null){
					userid = pobj.getUserId();
				}else{
					System.err.println("Object has no  data");
				}
			}else{
				System.out.println("fetchUserIdfromUsername :: Result not  found");
			}
			tx.commit();
		}catch(HibernateException e){
			if(tx != null){
				tx.rollback();
				e.printStackTrace();
			}
		}finally{
			session.close();
		}
		return userid;
	
	
	}


	@Override
	public List<Integer> fetchUserTobeNotified(Date now) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		List<Integer> ids = new ArrayList<Integer>();
		Session session = this.sessionFactory.openSession();
		try{
			tx=session.beginTransaction();
			Criteria cr = session.createCriteria(FutureTravel.class);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//Date fromDate = df.parse("2012-04-09 00:00:00");
			//Date toDate = df.parse("2012-04-09 23:59:59");
			Calendar cal = Calendar.getInstance();
	    	cal.setTime(now);
	    	cal.add(Calendar.MINUTE, +1);
			cr.add(Restrictions.between("notificationTime", now, cal.getTime()));
			/*String hql = "FROM FutureTravel E WHERE E.notificationTime = :date";
			Query query = session.createQuery(hql);
			query.setParameter("date", now); */
			List results = cr.list();
			if(results!=null && results.size()>0){
				FutureTravel obj = (FutureTravel)results.get(0);
				System.out.println(obj.getRecordId());
				ids.add(obj.getUserId());
			}else{
				System.out.println("fetchUserTobeNotified :: Result not  found");
			}
			tx.commit();
		}catch(HibernateException e){
			if(tx != null){
				tx.rollback();
				e.printStackTrace();
			}
		}finally{
			session.close();
		}
		
		return ids;
	}


	@Override
	public List<String> fetchClientIdForNotification(List<Integer> ids) {
		Transaction tx = null;
		List<String> clientIds = new ArrayList<String>();
		Session session = this.sessionFactory.openSession();
		try{
			tx=session.beginTransaction();
			Criteria cr = session.createCriteria(GoogleNotification.class);
			for(Integer userid : ids){
				cr.add(Restrictions.eq("userId", userid));
				cr.add(Restrictions.eq("active", 1));
				List results = cr.list();
				if(results!=null && results.size()>0){
					GoogleNotification obj = (GoogleNotification)results.get(0);
					System.out.println(obj.getGcmRegId());
					clientIds.add(obj.getGcmRegId());
				}else{
					System.out.println("fetchClientIdForNotification :: Result not  found");
				}
			}
			
			
			tx.commit();
		}catch(HibernateException e){
			if(tx != null){
				tx.rollback();
				e.printStackTrace();
			}
		}finally{
			session.close();
		}
		
		return clientIds;
	}


	@Override
	public boolean checkUsernameAlreadyExists(String username) {
		
		boolean userExists = false;
		Session session = this.sessionFactory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Criteria ctr = session.createCriteria(UserProfile.class);
			ctr.add(Restrictions.eq("username", username));
			List result = ctr.list();
			if(result != null && result.size() > 0 ){
				userExists = true;
			}
			tx.commit();
		}catch(HibernateException e){
			if(tx != null){
				tx.rollback();
				e.printStackTrace();
			}
		}finally{
			session.close();
		}
		return userExists;
	}
	
	
	
}
