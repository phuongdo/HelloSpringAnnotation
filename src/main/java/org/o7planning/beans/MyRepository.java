package org.o7planning.beans;

/**
 * Created by phuongdv on 5/11/17.
 */
import java.util.Date;

import org.springframework.stereotype.Repository;
/**
 *
 * "a mechanism for encapsulating storage, retrieval, and search behavior which emulates a collection of objects".
 */

@Repository
public class MyRepository {

    public String getAppName() {
        return "Hello Spring App";
    }

    public Date getSystemDateTime() {
        return new Date();
    }


}