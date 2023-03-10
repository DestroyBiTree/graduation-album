package com.nwu.graduationalbum;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
//@EnableSwaggerBootstrapUI
public class NwuGraduationAlbumApplication {

    public static void main(String[] args) {
        SpringApplication.run(NwuGraduationAlbumApplication.class, args);
    }

}
