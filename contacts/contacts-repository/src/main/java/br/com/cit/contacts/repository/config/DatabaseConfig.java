package br.com.cit.contacts.repository.config;

import br.com.cit.contacts.repository.constants.DatabaseConstant;
import br.com.cit.contacts.repository.exception.RepositoryException;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;
import java.util.Properties;

@Configuration
@MapperScan("br.com.cit.contacts.repository.mapper")
@ComponentScan("br.com.cit.contacts.repository")
@PropertySource("classpath:config.properties")
public class DatabaseConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() throws RepositoryException {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        try {
            dataSource.setDriverClass((Class<? extends Driver>) Class.forName(env.getProperty(DatabaseConstant.DB_PROVIDER_DRIVER)));
            dataSource.setUsername(env.getProperty(DatabaseConstant.DB_PROVIDER_USERNAME));
            dataSource.setPassword(env.getProperty(DatabaseConstant.DB_PROVIDER_PASSWORD));
            dataSource.setUrl(env.getProperty(DatabaseConstant.DB_PROVIDER_URL));

            // Fix to warn Mysql Establishing SSL connection
            Properties connectionProperties = new Properties();
            connectionProperties.setProperty("useSSL", "false");
            dataSource.setConnectionProperties(connectionProperties);
        } catch (ClassNotFoundException e) {
            throw new RepositoryException(e);
        }

        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() throws RepositoryException {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setTypeAliasesPackage(DatabaseConstant.TYPE_ALIASES_PACKAGE);
        return sessionFactory;
    }

}
