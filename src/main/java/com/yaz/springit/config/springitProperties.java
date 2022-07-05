package com.yaz.springit.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("springit")
public class springitProperties {
    /**
    *This is a welcome message
     */
    private String welcomeMsg = "hello world!";


    public String getWelcomeMsg() {
        return welcomeMsg;
    }

    public void setWelcomeMsg(String welcomeMsg) {
        this.welcomeMsg = welcomeMsg;
    }
}
