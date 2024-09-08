package com.atguigu.hibernate.helloworld;

import java.sql.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;

/**
 * @author admin
 */
public class HibernateTest {
    @Test
    public void test() {
        System.out.println("test...");
        //1. 创建一个 SessionFactory 对象
        SessionFactory sessionFactory;
        //1). 创建 Configuration 对象: 对应 Hibernate 的基本配置信息和 对象关系映射信息
        Configuration configuration = new Configuration().configure();
        //4.0 之前这样创建,在5.0的版本此方法又恢复了,不再是废弃的方法(4.0中此方法废弃了)
        sessionFactory = configuration.buildSessionFactory();
        //2). 创建一个 ServiceRegistry 对象:Hibernate 4.x 新添加的对象
        //Hibernate的任何配置和服务都需要在该对象中注册后才能有效。
//		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().//
//													applySettings(configuration.getProperties()).buildServiceRegistry();
        //hibernate5用下面的方法代替上面的方法  其实hibernate4的后来的版本就已经废弃了上面的方法 hibernate5干脆删除了
        //但是测试hibernate5中使用该方法会报错,但是使用上面hibernate3的创建方法没事
//		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()//
//													.applySettings(configuration.getProperties()).build();
        //3).
//		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        //2. 创建一个 Session 对象
        Session session = sessionFactory.openSession();
        //3. 开启事务
        Transaction transaction = session.beginTransaction();
        //4. 执行保存操作
        News news = new News("Java12345", "fuxi", new Date(new java.util.Date().getTime()));
        session.persist(news);
        //可以发起查询的语句,此时返回的对象是反射创建的
        News news2 = session.get(News.class, 1);
        System.out.println(news2);
        //5. 提交事务
        transaction.commit();
        //6. 关闭 Session
        session.close();
        //7. 关闭 SessionFactory 对象
        sessionFactory.close();
    }
}