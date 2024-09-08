//package com.atguigu.hibernate.entities;
//import java.io.InputStream;
//import java.sql.Blob;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.Date;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.hibernate.cfg.Configuration;
//import org.hibernate.jdbc.Work;
//public class HibernateTest {
//	private SessionFactory sessionFactory;
//	private Session session;
//	private Transaction transaction;
//	@Before
//	public void init() {
//		Configuration configuration = new Configuration().configure();
//		// ServiceRegistry serviceRegistry =new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
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
//	/**
//	 * 测试组合属性
//	 */
//	@Test
//	public void testComponent() {
//		Worker worker = new Worker();
//		Pay pay = new Pay();
//		pay.setMonthlyPay(1000);
//		pay.setYearPay(80000);
//		pay.setVocationWithPay(5);
//		worker.setName("ABCD");
//		worker.setPay(pay);
//		session.save(worker);
//	}
//	/**
//	 * Java 大对象类型的 Hiberante 映射
//	 */
//	@Test
//	public void testBlob() throws Exception {
//		// News news = new News();
//		// news.setAuthor("cc");
//		// news.setContent("CONTENT");
//		// news.setDate(new Date());
//		// news.setDesc("DESC");
//		// news.setTitle("CC");
//		// InputStream stream = new FileInputStream("Hydrangeas.jpg");
//		// Blob image = Hibernate.getLobCreator(session)
//		// .createBlob(stream, stream.available());
//		// news.setImage(image);
//		// session.save(news);
//		News news = (News) session.get(News.class, 1);
//		Blob image = news.getImage();
//		InputStream in = image.getBinaryStream();
//		System.out.println(in.available());
//	}
//	@Test
//	public void testPropertyUpdate() {
//		News news = (News) session.get(News.class, 1);
//		news.setTitle("aaaa");
//		System.out.println(news.getDesc());
//		System.out.println(news.getDate().getClass());
//	}
//	@Test
//	public void testIdGenerator() throws InterruptedException {
//		News news = new News("AA", "aa", new java.sql.Date(new Date().getTime()));
//		session.save(news);
//		// Thread.sleep(5000);
//	}
//	@Test
//	public void testDynamicUpdate() {
//		News news = (News) session.get(News.class, 1);
//		news.setAuthor("AABCD");
//	}
//	/**
//	 * 测试调用存储过程
//	 */
//	@Test
//	public void testDoWork() {
//		session.doWork(new Work() {
//			@Override
//			public void execute(Connection connection) throws SQLException {
//				System.out.println(connection);
//				// 调用存储过程
//			}
//		});
//	}
//	/**
//	 * evict: 从 session 缓存中把指定的持久化对象移除
//	 */
//	@Test
//	public void testEvict() {
//		News news1 = (News) session.get(News.class, 1);
//		News news2 = (News) session.get(News.class, 2);
//		news1.setTitle("AA");
//		news2.setTitle("BB");
//		//使news1变成游离的对象
//		session.evict(news1);
//	}
//	/**
//	 * delete:执行删除操作,只要OID和数据表中一条记录对应,就会准备执行delete操作。
//	 * 若OID在数据表中没有对应的记录,则抛出异常。
//	 * 可以通过设置Hibernate配置文件hibernate.use_identifier_rollback为 true,使删除对象后,把其OID置为null
//	 */
//	@Test
//	public void testDelete() {
//		// News news = new News();
//		// news.setId(11);
//		News news = (News) session.get(News.class, 163840);
//		session.delete(news);
//		System.out.println(news);
//	}
//	/**
//	 * 注意:
//	 * 1.若 OID 不为 null,但数据表中还没有和其对应的记录,会抛出一个异常。
//	 * 2.了解:OID 值等于 id 的unsaved-value 属性值的对象,也被认为是一个游离对象。
//	 */
//	@Test
//	public void testSaveOrUpdate() {
//		News news = new News("FFF", "fff", new Date());
//		news.setId(11);//有了id就是
//		session.saveOrUpdate(news);
//	}
//	/**
//	 * update:
//	 * 1.若更新一个持久化对象,不需要显示的调用update方法,因为在调用Transaction的commit()方法时,会先执行session的flush方法。
//	 * 2.更新一个游离对象,需要显式的调用session的update方法,可以把一个游离对象变为持久化对象。
//	 * 需要注意的:
//	 * 1.无论要更新的游离对象和数据表的记录是否一致,都会发送UPDATE语句。如何能让update方法不再盲目的出发update语句呢?
//	 * 在 .hbm.xml 文件的 class 节点设置 select-before-update=true (默认为false),但通常不需要设置该属性。
//	 * 2. 若数据表中没有对应的记录,但还调用了update 方法,会抛出异常。
//	 * 3. 当update()方法关联一个游离对象时,如果在Session的缓存中已经存在相同OID的持久化对象,会抛出异常,因为在
//	 * Session 缓存中不能有两个OID相同的对象!
//	 */
//	@Test
//	public void testUpdate() {
//		News news = (News)session.get(News.class,1);
//		transaction.commit();
//		session.close();//关闭掉session,使news对象变成游离对象
//		// news.setId(100);
//		session = sessionFactory.openSession();
//		transaction = session.beginTransaction();
//		// news.setAuthor("SUN");
//		News news2 = (News) session.get(News.class, 1);
//		session.update(news);
//	}
//	/**
//	 * get VS load:
//	 * 1.执行get方法:会立即加载对象,执行load方法,若不使用该对象,则不会立即执行查询操作,而返回一个代理对象。
//	 * get是立即检索,load是延迟检索。
//	 * 2.load方法可能会抛出LazyInitializationException异常:在需要初始化代理对象之前已经关闭了Session。
//	 * 3.若数据表中没有对应的记录,且Session也没有被关闭,
//	 * get返回null,load若不使用该对象的任何属性,没问题;若需要初始化了,抛出异常。
//	 */
//	@Test
//	public void testLoad() {
//		News news = (News) session.load(News.class, 10);
//		System.out.println(news.getClass().getName());
//		// session.close();
//		// System.out.println(news);
//	}
//	@Test
//	public void testGet() {
//		News news = (News) session.get(News.class, 1);
//		// session.close();
//		System.out.println(news);
//	}
//	/**
//	 * persist():也会执行 INSERT 操作
//	 * 和save()的区别:在调用persist()方法之前,若对象已经有id了,则不会执行 INSERT,而是抛出异常
//	 */
//	@Test
//	public void testPersist() {
//		News news = new News();
//		news.setTitle("EE");
//		news.setAuthor("ee");
//		news.setDate(new Date());
//		news.setId(200);
//		session.persist(news);
//	}
//	/**
//	 * 1. save() 方法
//	 * 1).使一个临时对象变为持久化对象。
//	 * 2).为对象分配 ID。
//	 * 3).在 flush 缓存时会发送一条 INSERT语句。
//	 * 4).在 save 方法之前的 id 是无效的。
//	 * 5).持久化对象的 ID 是不能被修改的!
//	 */
//	@Test
//	public void testSave() {
//		News news = new News();
//		news.setTitle("CC");
//		news.setAuthor("cc");
//		news.setDate(new Date());
//		//save()方法之前设置的id是无效的
//		news.setId(100);
//		System.out.println(news);
//		session.save(news);
//		System.out.println(news);
//		//save()方法之后持久化对象的ID是不可以修改的
//		// news.setId(101);
//	}
//	/**
//	 * clear():清理缓存
//	 */
//	@SuppressWarnings("unused")
//	@Test
//	public void testClear() {
//		News news1 = (News) session.get(News.class, 1);
//		session.clear();
//		News news2 = (News) session.get(News.class, 1);
//	}
//	/**
//	 * refresh():会强制发送SELECT语句,以使 Session 缓存中对象的状态和数据表中对应的记录保持一致!
//	 */
//	@Test
//	public void testRefresh() {
//		News news = (News) session.get(News.class, 1);
//		System.out.println(news);
//		session.refresh(news);
//		System.out.println(news);
//	}
//	/**
//	 * flush:使数据表中的记录和Session缓存中的对象的状态保持一致。为了保持一致,则可能会发送对应的 SQL 语句。
//	 * 1.在Transaction的commit()方法中:先调用session的flush()方法,再提交事务。
//	 * 2.flush()方法可能会发送SQL语句,但不会提交事务。
//	 * 3.注意:在未提交事务或显式的调用session.flush()方法之前,也有可能会进行flush()操作。
//	 * 1).执行HQL或QBC查询,会先进行flush()操作,以得到数据表的最新的记录。
//	 * 2).若记录的ID是由底层数据库使用自增的方式生成的,则在调用save()方法时,就会立即发送 INSERT 语句。
//	 * 因为 save方法后,必须保证对象的 ID 是存在的!
//	 */
//	@Test
//	public void testSessionFlush2() {
//		News news = new News("Java", "SUN", new Date());
//		session.save(news);
//	}
//	@Test
//	public void testSessionFlush() {
//		News news = (News) session.get(News.class, 1);
//		news.setAuthor("Oracle");//从数据库获取到的数据设置一个属性
//		// session.flush();//自然我们可以手动调用flush()方法刷新数据,这样会发送sql语句,不过提交事务之前数据库的记录不会改变
//		// System.out.println("flush");
//		//Hibernate 5.2版本开始已经废弃了Session的Criteria方法,建议使用JPA的Criteria
//		News news2 = (News) session.createCriteria(News.class).uniqueResult();
//		System.out.println(news2);
//	}
//	/**
//	 * 测试一下Session的缓存(一级缓存)
//	 */
//	@Test
//	public void testSessionCache() {
//		News news = (News) session.get(News.class, 1);
//		System.out.println(news);
//		//第二次查询不需要再查询数据库,而是从缓存中获取数据
//		News news2 = (News) session.get(News.class, 1);
//		System.out.println(news2);
//	}
//}
