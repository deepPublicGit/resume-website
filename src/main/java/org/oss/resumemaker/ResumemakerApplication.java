package org.oss.resumemaker;

import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@Theme("default")
public class ResumemakerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResumemakerApplication.class, args);
    }

}
