package com.rookie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author yayee
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.rookie.*")
public class RookieApplication {

    public static void main(String[] args) {
        SpringApplication.run(RookieApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ   ROOKIE-DEVTOOLS 启动成功   ლ(´ڡ`ლ)ﾞ ~ ~");
    }

}
