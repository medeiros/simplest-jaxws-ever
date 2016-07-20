package com.arneam;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface Calc {

    String SERVICE_NAME = "calcService";
    String PORT_NAME = "calcPort";

    @WebMethod
    int add(int x, int y);

    @WebMethod
    int sub(int i, int j);

    int multi(int i, int j);

    int divide(int i, int j);

}
