//package com.atguigu.hibernate.one2one.primary;
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
//
//	@Test
//	public void testGet2() {
//		// 在查询没有外键的实体对象时, 使用的左外连接查询, 一并查询出其关联的对象
//		// 并已经进行初始化.
//		Manager mgr = (Manager) session.get(Manager.class, 1);
//		System.out.println(mgr.getMgrName());
//		System.out.println(mgr.getDept().getDeptName());
//	}
//
//	@Test
//	public void testGet() {
//		// 1. 默认情况下对关联属性使用懒加载
//		Department dept = (Department) session.get(Department.class, 1);
//		System.out.println(dept.getDeptName());
//		// 2. 所以会出现懒加载异常的问题.
//		Manager mgr = dept.getMgr();
//		System.out.println(mgr.getMgrName());
//	}
//	@Test
//	public void testSave() {
//		Department department = new Department();
//		department.setDeptName("DEPT-DD");
//		Manager manager = new Manager();
//		manager.setMgrName("MGR-DD");
//		// 设定关联关系
//		manager.setDept(department);
//		department.setMgr(manager);
//		// 保存操作
//		// 先插入哪一个都不会有多余的 UPDATE
//		session.save(department);
//		session.save(manager);
//	}
//}
