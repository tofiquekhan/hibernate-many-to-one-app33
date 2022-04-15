package myproject.manytoone.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import myproject.manytoone.entity.Branch;
import myproject.manytoone.entity.Student;

public class Test {
	
	public static void main(String[] args) {
		
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		
		try {
			Configuration cfg = new Configuration();
			cfg.configure("/myproject/manytoone/resources/hibernate.cfg.xml");
			StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
			builder = builder.applySettings(cfg.getProperties());
			StandardServiceRegistry registry = builder.build();
			sessionFactory = cfg.buildSessionFactory(registry);
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			Branch branch = new Branch();
			branch.setBid("B-111");
			branch.setBname("CS");
			
			Student std1 = new Student();
			std1.setSid("S-111");
			std1.setSname("AAA");
			std1.setSaddr("IDN");
			std1.setBranch(branch);
			
			Student std2 = new Student();
			std2.setSid("S-222");
			std2.setSname("BBB");
			std2.setSaddr("BHO");
			std2.setBranch(branch);
			
			Student std3 = new Student();
			std3.setSid("S-333");
			std3.setSname("CCC");
			std3.setSaddr("GOA");
			std3.setBranch(branch);
			
			String pk1 = (String) session.save(std1);
			String pk2 = (String) session.save(std2);
			String pk3 = (String) session.save(std3);
			
			System.out.println(pk1);
			System.out.println(pk2);
			System.out.println(pk3);
			tx.commit();
		}catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
			sessionFactory.close();
		}
		
		
		
		
	}

}
