//package com.atguigu.hibernate.joined.subclass;
//import java.util.List;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.hibernate.cfg.Configuration;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
///**
// * @author admin
// */
//public class HibernateTest {
//	private SessionFactory sessionFactory;
//	private Session session;
//	private Transaction transaction;
//	@Before
//	public void init() {
//		Configuration configuration = new Configuration().configure();
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
//	/**
//	 * 优点: 1. 不需要使用了辨别者列。 2. 子类独有的字段能添加非空约束。3. 没有冗余的字段。
//	 * 查询: 1. 查询父类记录,做一个左外连接查询。 2. 对于子类记录,做一个内连接查询。
//	 */
//	@Test
//	public void testQuery() {
//		List<Person> persons = session.createQuery("FROM Person").list();
//		System.out.println(persons.size());
//		List<Student> stus = session.createQuery("FROM Student").list();
//		System.out.println(stus.size());
//	}
//	/**
//	 * 插入操作:1. 对于子类对象至少需要插入到两张数据表中.
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
