package org.o7planning.beans;

import org.o7planning.spring.lang.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by phuongdv on 5/11/17.
 */

// indicate this is "Service"
// an operation offered as an interface that stands alone in the model, with no encapsulated state
@Service
public class GreetingService {

    //cơ chế liên kết tự động byName được chỉ ra bằng cách thêm thuộc tính autowire=”byName”.
    // Lien ket tu dong :)
    // When @Autowired is used on properties, it is equivalent to autowiring by ‘byType‘ in configuration file.
    // Dependency injection, autowire relationships between collaborating beans
    @Autowired
    private Language language;

    public GreetingService() {

    }

    public void sayGreeting() {

        String greeting = language.getGreeting();

        System.out.println("Greeting: " + greeting);
    }

}
