//package com.atguigu.hibernate.entities.n21.both;
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
//	@Test
//	public void testCascade(){
//		Customer customer = (Customer) session.get(Customer.class, 3);
//		customer.getOrders().clear();
//	}
//	@Test
//	public void testDelete(){
//		//在不设定级联关系的情况下,且 1 这一端的对象有 n 的对象在引用,不能直接删除 1 这一端的对象
//		Customer customer = (Customer) session.get(Customer.class, 1);
//		session.delete(customer);
//	}
//	@Test
//	public void testUpdat2(){
//		Customer customer = (Customer) session.get(Customer.class, 1);
//		customer.getOrders().iterator().next().setOrderName("GGG");
//	}
//	@Test
//	public void testUpdate(){
//		Order order = (Order) session.get(Order.class, 1);
//		order.getCustomer().setCustomerName("AAA");
//	}
//	@Test
//	public void testOne2ManyGet(){
//		//1. 对n的一端的集合使用延迟加载
//		Customer customer = (Customer)session.get(Customer.class, 7);
//		System.out.println(customer.getCustomerName());
//		//2. 返回的多的一端的集合时 Hibernate 内置的集合类型。该类型具有延迟加载和存放代理对象的功能
//		System.out.println(customer.getOrders().getClass());
//		//session.close();
//		//3. 可能会抛出 LazyInitializationException 异常
//		System.out.println(customer.getOrders().size());
//		//4. 再需要使用集合中元素的时候进行初始化
//	}
//	@Test
//	public void testMany2OneGet(){
//		//1.若查询多的一端的一个对象,则默认情况下,只查询了多的一端的对象,而没有查询关联的1 的那一端的对象!
//		Order order = (Order) session.get(Order.class, 1);
//		System.out.println(order.getOrderName());
//		System.out.println(order.getCustomer().getClass().getName());
//		session.close();
//		//2. 在需要使用到关联的对象时,才发送对应的 SQL 语句
//		Customer customer = order.getCustomer();
//		System.out.println(customer.getCustomerName());
//		//3.在查询Customer对象时,由多的一端导航到1的一端时,若此时session已被关闭,则默认情况下会发生LazyInitializationException异常
//		//4.获取 Order 对象时, 默认情况下, 其关联的 Customer 对象是一个代理对象!
//	}
//	@Test
//	public void testMany2OneSave(){
//		Customer customer = new Customer();
//		customer.setCustomerName("AA");
//		Order order1 = new Order();
//		order1.setOrderName("ORDER-1");
//		Order order2 = new Order();
//		order2.setOrderName("ORDER-2");
//		//设定关联关系
//		order1.setCustomer(customer);
//		order2.setCustomer(customer);
//		customer.getOrders().add(order1);
//		customer.getOrders().add(order2);
//		//执行save操作:先插入Customer,再插入Order,3条INSERT,2条UPDATE因为1的一端和n的一端都维护关联关系,所以会多出UPDATE
//		//可以在1的一端的set节点指定inverse=true,来使1的一端放弃维护关联关系!建议设定set的inverse=true
//		//建议先插入1的一端,后插入多的一端,好处是不会多出UPDATE语句
//		session.save(customer);
////		session.save(order1);
////		session.save(order2);
//		//先插入Order, 再插入Cusomer,3条INSERT,4条UPDATE
////		session.save(order1);
////		session.save(order2);
////		session.save(customer);
//	}
//}
