//package com.atguigu.hibernate.entities.n21;
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
//	public void init(){
//		Configuration configuration = new Configuration().configure();
//		sessionFactory = configuration.buildSessionFactory();
//		session = sessionFactory.openSession();
//		transaction = session.beginTransaction();
//	}
//	@After
//	public void destroy(){
//		transaction.commit();
//		session.close();
//		sessionFactory.close();
//	}
//	/**
//	 * 测试多对一的关联关系的删除操作
//	 */
//	@Test
//	public void testDelete(){
//		//在不设定级联关系的情况下,且1这一端的对象有n的对象在引用,不能直接删除1这一端的对象
//		Customer customer = (Customer)session.get(Customer.class,1);
//		session.delete(customer);
//	}
//	/**
//	 * 测试多对一的关联关系的修改操作
//	 */
//	@Test
//	public void testUpdate(){
//		Order order = (Order)session.get(Order.class,1);
//		//发送update语句
//		order.getCustomer().setCustomerName("AAA");
//	}
//	/**
//	 * 测试多对一的关联关系的查询操作
//	 */
//	@Test
//	public void testMany2OneGet(){
//		//1.若查询多的一端的一个对象,则默认情况下,只查询了多的一端的对象,而没有查询关联的1的那一端的对象!
//		Order order = (Order)session.get(Order.class,1);
//		System.out.println(order.getOrderName());
//		//默认情况下是懒加载,返回代理对象
//		System.out.println(order.getCustomer().getClass().getName());
//		session.close();
//		//2.在需要使用到关联的对象时,才发送对应的 SQL 语句
//		Customer customer = order.getCustomer();
//		System.out.println(customer.getCustomerName());
//		//3.在查询Customer对象时,由多的一端导航到1的一端时,若此时session已被关闭,则默认情况下会发生LazyInitializationException异常
//		//4.获取Order对象时,默认情况下,其关联的Customer对象是一个代理对象!
//	}
//	/**
//	 * 测试多对一的关联关系的添加操作
//	 * 先插入Customer,再插入Order,3条 INSERT,先插入1的一端,再插入n的一端,只有 INSERT 语句
//	 * 先插入Order,再插入Customer,3条 INSERT, 2 条 UPDATE,先插入n的一端,再插入1的一端,会多出UPDATE语句!
//	 * 因为在插入多的一端时,无法确定1的一端的外键值,所以只能等 1 的一端插入后,再额外发送 UPDATE 语句。
//	 * 推荐先插入 1 的一端,后插入 n 的一端。
//	 */
//	@Test
//	public void testMany2OneSave(){
//		Customer customer = new Customer();
//		customer.setCustomerName("BB");
//		Order order1 = new Order();
//		order1.setOrderName("ORDER-3");
//		Order order2 = new Order();
//		order2.setOrderName("ORDER-4");
//		//设定关联关系
//		order1.setCustomer(customer);
//		order2.setCustomer(customer);
////		session.save(customer);
////		session.save(order1);
////		session.save(order2);
//		session.save(order1);
//		session.save(order2);
//		session.save(customer);
//	}
//}
