//package com.atguigu.hibernate.union.subclass;
//import java.util.List;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.hibernate.cfg.Configuration;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//public class HibernateTest {
//	private SessionFactory sessionFactory;
//	private Session session;
//	private Transaction transaction;
//	@Before
//	public void init() {
//		Configuration configuration = new Configuration().configure();
//		// ServiceRegistry serviceRegistry =
//		// new
//		// ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
//		// sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//		sessionFactory = configuration.buildSessionFactory();
//		session = sessionFactory.openSession();
//		transaction = session.beginTransaction();
//	}
//	@After
//	public void destroy() {
//		transaction.commit();
//		session.close();
//		sessionFactory.close();
//	}
//	@Test
//	public void testUpdate() {
//		String hql = "UPDATE Person p SET p.age = 20";
//		session.createQuery(hql).executeUpdate();
//	}
//	/**
//	 * 优点: 1. 无需使用辨别者列. 2. 子类独有的字段能添加非空约束.
//	 * 缺点: 1. 存在冗余的字段 2. 若更新父表的字段, 则更新的效率较低
//	 */
//	/**
//	 * 查询: 1. 查询父类记录, 需把父表和子表记录汇总到一起再做查询. 性能稍差. 2. 对于子类记录, 也只需要查询一张数据表
//	 */
//	@Test
//	public void testQuery() {
//		List<Person> persons = session.createQuery("FROM Person").list();
//		System.out.println(persons.size());
//		List<Student> stus = session.createQuery("FROM Student").list();
//		System.out.println(stus.size());
//	}
//	/**
//	 * 插入操作: 1. 对于子类对象只需把记录插入到一张数据表中.
//	 */
//	@Test
//	public void testSave() {
//		Person person = new Person();
//		person.setAge(11);
//		person.setName("AA");
//		session.save(person);
//		Student stu = new Student();
//		stu.setAge(22);
//		stu.setName("BB");
//		stu.setSchool("ATGUIGU");
//		session.save(stu);
//	}
//}
