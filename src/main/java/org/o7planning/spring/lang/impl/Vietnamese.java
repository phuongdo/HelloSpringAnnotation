package org.o7planning.spring.lang.impl;

import org.o7planning.spring.lang.Language;

/**
 * Created by phuongdv on 5/11/17.
 */
public class Vietnamese  implements Language {

    public String getGreeting() {
        return "Xin chao";
    }

    public String getBye() {
        return "Tam biet";
    }

}
