package wxx.java.appraise.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;


@Configuration
public class DruidConfig {

    @Bean
    public ServletRegistrationBean druidServlet() { // 主要实现WEB监控的配置处理
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*"); // 进行druid监控的配置处理操作
        servletRegistrationBean.addInitParameter("allow",
                "127.0.0.1"); // 白名单
//        servletRegistrationBean.addInitParameter("deny", "192.168.1.200"); // 黑名单
        servletRegistrationBean.addInitParameter("loginUsername", "admin"); // 用户名
        servletRegistrationBean.addInitParameter("loginPassword", "123456"); // 密码
        servletRegistrationBean.addInitParameter("resetEnable", "false"); // 是否可以重置数据源
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*"); // 所有请求进行监控处理
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.css,/druid/*");
        return filterRegistrationBean;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
      DruidDataSource druidDataSource = new DruidDataSource();
      List<Filter> filterList = new ArrayList<>();
      filterList.add(wallFilter());
      druidDataSource.setProxyFilters(filterList);
      return druidDataSource;
    }

  @Bean
  public WallFilter wallFilter() {
    WallFilter wallFilter = new WallFilter();
    wallFilter.setConfig(wallConfig());
    return wallFilter;
  }

  @Bean
  public WallConfig wallConfig() {
    WallConfig config = new WallConfig();
    config.setMultiStatementAllow(true);//允许一次执行多条语句
    config.setNoneBaseStatementAllow(true);//允许非基本语句的其他语句
    return config;
  }


    @Bean
    public DruidStatInterceptor druidStatInterceptor() {
      DruidStatInterceptor dsInterceptor = new DruidStatInterceptor();
      return dsInterceptor;
    }

    @Bean
    @Scope("prototype")
    public JdkRegexpMethodPointcut druidStatPointcut() {
      JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
      pointcut.setPattern("wxx.java.appraise.service.impl.*");//根据实际包名
      return pointcut;
    }

    @Bean
    public DefaultPointcutAdvisor druidStatAdvisor(DruidStatInterceptor druidStatInterceptor, JdkRegexpMethodPointcut druidStatPointcut) {
      DefaultPointcutAdvisor defaultPointAdvisor = new DefaultPointcutAdvisor();
      defaultPointAdvisor.setPointcut(druidStatPointcut);
      defaultPointAdvisor.setAdvice(druidStatInterceptor);
      return defaultPointAdvisor;
    }
  }
