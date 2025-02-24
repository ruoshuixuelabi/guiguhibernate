//package com.atguigu.hibernate.strategy;
//import java.util.List;
//import org.hibernate.Hibernate;
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
//		// ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
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
//	public void testMany2OneStrategy() {
//		// Order order = (Order) session.get(Order.class, 1);
//		// System.out.println(order.getCustomer().getCustomerName());
//		@SuppressWarnings("unchecked")
//		List<Order> orders = session.createQuery("FROM Order o").list();
//		for (Order order : orders) {
//			if (order.getCustomer() != null) {
//				System.out.println(order.getCustomer().getCustomerName());
//			}
//		}
//		// 1.lazy取值为proxy和false分别代表对应对应的属性采用延迟检索和立即检索
//		// 2.fetch取值为join,表示使用迫切左外连接的方式初始化n关联的1的一端的属性忽略lazy属性
//		// 3.batch-size,该属性需要设置在 1 那一端的 class 元素中:
//		// <class name="Customer" table="CUSTOMERS" lazy="true" batch-size="5">
//		// 作用:一次初始化1的这一端代理对象的个数
//	}
//	@Test
//	public void testSetFetch2() {
//		Customer customer = (Customer) session.get(Customer.class, 1);
//		System.out.println(customer.getOrders().size());
//	}
//	@Test
//	public void testSetFetch() {
//		@SuppressWarnings("unchecked")
//		List<Customer> customers = session.createQuery("FROM Customer").list();
//		System.out.println(customers.size());
//		for (Customer customer : customers) {
//			if (customer.getOrders() != null)
//				System.out.println(customer.getOrders().size());
//		}
//		// set 集合的 fetch 属性:确定初始化 orders 集合的方式
//		// 1.默认值为select。通过正常的方式来初始化 set 元素
//		// 2.可以取值为subselect。通过子查询的方式来初始化所有的 set 集合
//		//子查询作为 where 子句的 in 的条件出现, 子查询查询所有 1 的一端的 ID. 此时 lazy 有效,但 batch-size 失效.
//		// 3. 若取值为 join. 则
//		// 3.1 在加载 1 的一端的对象时,使用迫切左外连接(使用左外链接进行查询,且把集合属性进行初始化)的方式检索 n 的一端的集合属性
//		// 3.2 忽略 lazy 属性
//		// 3.3 HQL 查询忽略 fetch=join 的取值
//	}
//	@Test
//	public void testSetBatchSize() {
//		@SuppressWarnings("unchecked")
//		List<Customer> customers = session.createQuery("FROM Customer").list();
//		System.out.println(customers.size());
//		for (Customer customer : customers) {
//			if (customer.getOrders() != null)
//				System.out.println(customer.getOrders().size());
//		}
//		// set 元素的 batch-size 属性: 设定一次初始化 set 集合的数量.
//	}
//	@Test
//	public void testOne2ManyLevelStrategy() {
//		Customer customer = (Customer) session.get(Customer.class, 1);
//		System.out.println(customer.getCustomerName());
//		System.out.println(customer.getOrders().size());
//		Order order = new Order();
//		order.setOrderId(1);
//		System.out.println(customer.getOrders().contains(order));
//		Hibernate.initialize(customer.getOrders());//通过 Hibernate.initialize() 静态方法显式初始化
//		// ---------------set 的 lazy 属性------------------
//		// 1. 1-n 或 n-n 的集合属性默认使用懒加载检索策略.
//		// 2. 可以通过设置 set 的 lazy 属性来修改默认的检索策略. 默认为 true,并不建议设置为 false.
//		// 3. lazy 还可以设置为 extra. 增强的延迟检索. 该取值会尽可能的延迟集合初始化的时机!
//	}
//	@Test
//	public void testClassLevelStrategy() {
//		Customer customer = (Customer) session.load(Customer.class, 1);
//		System.out.println(customer.getClass());
//		System.out.println(customer.getCustomerId());
//		System.out.println(customer.getCustomerName());
//	}
//}
