package vn.ntqsolution.smart_shop.configuration;

import io.sentry.Sentry;
import io.sentry.spring.SentryExceptionResolver;
import io.sentry.spring.SentryServletContextInitializer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.annotation.PostConstruct;

/**
 * @author truongbb
 * @see "https://docs.sentry.io/clients/java/integrations/#spring"
 */

@Configuration
public class SentryConfiguration {

  @PostConstruct
  public void init() {
    Sentry.init("http://a89b39b43ffa448fa5a5856f95600cca@localhost:9000/2?environment=staging");
  }

  @Bean
  public HandlerExceptionResolver sentryExceptionResolver() {
    return new SentryExceptionResolver();
  }

  @Bean
  public ServletContextInitializer sentryServletContextInitializer() {
    return new SentryServletContextInitializer();
  }

}
