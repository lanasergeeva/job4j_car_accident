package ru.job4j.accident;
import javax.sql.DataSource;
import java.util.Properties;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@ComponentScan("ru.job4j.accident")
@EnableWebMvc
@EnableTransactionManagement
public class WebConfig {
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver bean =
                new InternalResourceViewResolver();
        bean.setViewClass(JstlView.class);
        bean.setPrefix("./WEB-INF/views/");
        bean.setSuffix(".jsp");
        return bean;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/car_accident");
        dataSource.setUsername("postgres");
        dataSource.setPassword("lana90max");
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactor() {
        LocalSessionFactoryBean sessionFactoryBean =
                new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan("ru.job4j.accident");
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.connection.characterEncoding", "utf8");
        sessionFactoryBean.setHibernateProperties(properties);
        return sessionFactoryBean;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager manager =
                new HibernateTransactionManager();
        manager.setSessionFactory(sessionFactor().getObject());
        return manager;
    }
}
