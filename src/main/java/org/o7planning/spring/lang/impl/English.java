package org.o7planning.spring.lang.impl;

import org.o7planning.spring.lang.Language;

/**
 * Created by phuongdv on 5/11/17.
 */
public class English  implements Language {

    public String getGreeting() {
        return "Hello";
    }

    public String getBye() {
        return "Bye bye";
    }

}
