package com.mkyong.bo;

/**
 * Created by phuongdv on 5/12/17.
 */
public interface CustomerBo {
    int a = 4;// final static
    void addCustomer();

    String addCustomerReturnValue();

    void addCustomerThrowException() throws Exception;

    void addCustomerAround(String[] args);


}
