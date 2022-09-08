package com.spring.annotation.tx;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * @author cyl
 * @date 2022-09-05 8:54
 * @description 声明式事务主配置类
 */
// 标注配置类
@Configuration
// 开启包扫描
@ComponentScan(basePackages = { "com.spring.annotation.tx" })
// 开启事务管理器
@EnableTransactionManagement
public class MainConfigOfTX {

    /**
     * 声明式事务环境搭建：
     *  1.导入相关依赖
     *      数据库驱动、数据源、Spring-JDBC 模块
     *  2.配置数据源、JdbcTemplate(Spring 提供的简化数据库操作的工具)操作数据
     *  3.给方法标注 @Transactional 注解（表示当前方法是一个事务方法）
     *  4.@EnableTransactionManagement 开启基于注解的事务管理功能
     *  5.配置事务管理器来控制事务
     */

    /**
     * 原理分析：
     *  1.@EnableTransactionManagement
     *      使用 TransactionManagementConfigurationSelector 给容器中导入组件：AutoProxyRegistrar、ProxyTransactionManagementConfiguration
     *  2.AutoProxyRegistrar
     *      1.给容器中注册一个 InfrastructureAdvisorAutoProxyCreator 组件
     *      2.InfrastructureAdvisorAutoProxyCreator
     *          利用后置处理器机制在对象创建以后，包装对象，返回一个代理对象（增强器），代理对象执行方法利用拦截器链进行调用
     *  3.ProxyTransactionManagementConfiguration
     *      1.给容器中注册事务增强器 BeanFactoryTransactionAttributeSourceAdvisor
     *          1.AnnotationTransactionAttributeSource:事务增强器要用事务注解的信息，解析事务注解
     *          2.TransactionInterceptor:事务拦截器
     *              1.保存了事务属性信息，实现了 MethodInterceptor
     *              2.在执行目标方法的时候：执行拦截器链
     *              3.事务拦截器：
     *                  1.先获取事务相关的属性
     *                  2.再获取 PlatformTransactionManager,如果事先没有指定任何 TransactionManager 最终会从容器中按照类型获取一个 PlatformTransactionManager
     *                  3.执行目标方法：
     *                      如果异常，获取到事务管理器，利用事务管理回滚操作
     *                      如果正常，利用事务管理器，提交事务
     *          3.
     *      2.
     *  4.
     */

    // 注册数据源
    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("root");
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");

        return dataSource;
    }

    // 注册 jdbcTemplate
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    // 注入事务管理器
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


}
