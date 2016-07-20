package com.arneam;

import com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

public class CalcImplIT {

    static final String URL = "http://localhost:8088/calc";
    static final String NAMESPACE_URI = "http://arneam.com/";
    static final String ENDPOINT_BINDING_ID = "http://schemas.xmlsoap.org/wsdl/soap/http";
    Endpoint endpoint;
    Calc calcPort;

    @Before
    public void setup() throws MalformedURLException {
        loadSystemProperties();
        loadEndpoint();
        getCalcPort();
    }

    private void loadSystemProperties() {
        /* in order to print request and response data, you can turn
            HttpTransportPipe.dump on. There are some ways to do this:
            use one of the following properties (depending on java version),
            or set the boolean attribute directly */

        // System.setProperty("com.sun.xml.ws.transport.http.client.HttpTransportPipe.dump", "true");
        // System.setProperty("com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump", "true");
        // System.setProperty("com.sun.xml.ws.transport.http.HttpAdapter.dump", "true");
        // System.setProperty("com.sun.xml.internal.ws.transport.http.HttpAdapter.dump", "true");
        HttpTransportPipe.dump = true;
    }

    private void loadEndpoint() {
        this.endpoint = Endpoint.publish(URL, new CalcImpl());
        assertThat(endpoint.isPublished(), is(true));
    }

    private void getCalcPort() throws MalformedURLException {
        this.calcPort = remotePortFrom(Calc.class);
        assertThat(calcPort, notNullValue());
    }

    private <T> T remotePortFrom(Class<T> clazz) throws MalformedURLException {
        QName serviceQN = new QName(NAMESPACE_URI, Calc.SERVICE_NAME);
        QName portQN = new QName(NAMESPACE_URI, Calc.PORT_NAME);

        URL wsdlLocation = new URL(URL + "?wsdl");
        Service service = Service.create(wsdlLocation, serviceQN);
        return (T) service.getPort(portQN, clazz);
    }

    @Test
    public void shouldHaveEndpointPublished() {
        assertThat(endpoint.isPublished(), is(true));
    }

    @Test
    public void shouldValidateBinding() {
        assertThat(endpoint.getBinding().getBindingID(), equalTo(ENDPOINT_BINDING_ID));
    }

    @Test
    public void shouldSumTwoNumbers() {
        assertThat(calcPort.add(1, 2), equalTo(3));
    }

    @Test
    public void shouldSubtractTwoNumbers() {
        assertThat(calcPort.sub(1, 2), equalTo(-1));
    }

    @Test
    public void shouldMultiplyTwoNumbers() {
        assertThat(calcPort.multi(3, 4), equalTo(12));
    }

    @Test
    public void shouldDivideTwoNumbers() {
        assertThat(calcPort.divide(8, 4), equalTo(2));
    }

    @After
    public void tearDown() {
        endpoint.stop();
        assertThat(endpoint.isPublished(), is(false));
    }

}
