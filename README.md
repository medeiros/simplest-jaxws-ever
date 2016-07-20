
# Simplest JaxWS ever

This is a basic application which implements a calculator in order to demonstrate a simplest use of JaxWS to expose REST services. There is a integrated test to validate it all, making use of Java HTTP Server (com.sun.net.httpserver), in a way that the REST operations can be tested without any additional framework.

Actually, since JaxWS implementation is embedded in JRE (`jre/lib/rt.jar`), there is absolutely no external dependencies to expose the REST services! The only project dependencies are related to tests (`junit` and `hamcrest`). The `javax.xml.ws.Endpoint` class is responsible for the server runnin'. It is handful in a scenario where a JaxWs/JaxB must be validated but there is no need to bootstrap any frameworks.

Nice references regarding this matter can be found [here] (http://java.globinch.com/enterprise-java/web-services/jax-ws/java-jax-ws-tutorial-develop-web-services-clients-consumers/) and [here] (http://www.ibm.com/developerworks/library/ws-whichwsdl/).
