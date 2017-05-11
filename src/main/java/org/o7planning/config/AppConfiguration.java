package org.o7planning.config;

import org.o7planning.spring.lang.Language;
import org.o7planning.spring.lang.impl.Vietnamese;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"org.o7planning.beans"})

//@ComponentScan - Nói cho Spring các package để tìm kiếm các Spring BEAN khác, Spring sẽ quét (scan) các package đó để tìm kiếm.
public class AppConfiguration {

    @Bean(name ="language")
    public Language getLanguage() {

        return new Vietnamese();
    }

}