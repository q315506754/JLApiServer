package com.jiangli.api;

import com.jiangli.api.conf.ApplicationConfig;
import org.apache.catalina.connector.Connector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@EnableConfigurationProperties(value = ApplicationConfig.class)
@PropertySource({
		//"classpath:application.properties",
		"classpath:xxl-job.properties"
})
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

    @Bean
    public ConfigurableServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addAdditionalTomcatConnectors(createStandardConnector());
        return tomcat;
    }

    private Connector createStandardConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setPort(8010);
        return connector;
    }

    //@Bean
    //public ConfigurableServletWebServerFactory servletContainerSSL() {
    //    TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
    //    tomcat.addAdditionalTomcatConnectors(createStandardConnectorSSL());
    //    return tomcat;
    //}
    //
    //private Connector createStandardConnectorSSL() {
    //    Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
    //    Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
    //    try {
    //        File keystore = new ClassPathResource("keystore").getFile();
    //        File truststore = new ClassPathResource("keystore").getFile();
    //        connector.setScheme("https");
    //        connector.setSecure(true);
    //        connector.setPort(8443);
    //        protocol.setSSLEnabled(true);
    //        protocol.setKeystoreFile(keystore.getAbsolutePath());
    //        protocol.setKeystorePass("123456");
    //        protocol.setTruststoreFile(truststore.getAbsolutePath());
    //        protocol.setTruststorePass("123456");
    //        //protocol.setKeyAlias("apitester");
    //        return connector;
    //    }
    //    catch (IOException ex) {
    //        throw new IllegalStateException("can't access keystore: [" + "keystore"
    //                + "] or truststore: [" + "keystore" + "]", ex);
    //    }
    //    //return connector;
    //}
}
