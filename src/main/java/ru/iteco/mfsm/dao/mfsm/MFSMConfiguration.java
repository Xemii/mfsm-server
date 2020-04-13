package ru.iteco.mfsm.dao.mfsm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriTemplateHandler;

@Configuration
@PropertySource("classpath:mfsm.properties")
public class MFSMConfiguration {
    @Value("${mfsm.url}")
    private String url;

    @Value("${mfsm.username}")
    private String username;

    @Value("${mfsm.pwd}")
    private String pwd;

    @Bean("BEAN_mfsmRestTemplate")
    public RestTemplate mfsmRestTemplate() {
        System.out.println("MFSMConfiguration.mfsmRestTemplate");
        System.out.println("url=" + url);
        System.out.println("username=" + username);
        System.out.println("pwd=" + pwd);

        DefaultUriTemplateHandler uriTemplateHandler = new DefaultUriTemplateHandler();
        uriTemplateHandler.setBaseUrl(url);

        return new RestTemplateBuilder()
                .uriTemplateHandler(uriTemplateHandler)
                .basicAuthentication(username, pwd)
                .build();
    }
}
