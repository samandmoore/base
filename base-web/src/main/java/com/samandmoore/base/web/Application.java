package com.samandmoore.base.web;

import com.samandmoore.base.web.config.WebAppConfig;
import org.springframework.boot.SpringApplication;

/**
 * @author Sam Moore
 * @since 4/19/14 10:17 PM
 */
public class Application {

    public static void main(String[] args) throws Exception {

        SpringApplication.run(WebAppConfig.class, args);
    }
}
