package com.arneam;

import javax.jws.WebService;

@WebService(serviceName = Calc.SERVICE_NAME, portName = Calc.PORT_NAME)
public class CalcImpl implements Calc {

    public int add (int x, int y) {
        return x + y;
    }

    public int sub(int i, int j) {
        return i - j;
    }

    public int multi(int i, int j) {
        return i * j;
    }

    public int divide(int i, int j) {
        return i / j;
    }
}
