package com.voghan.bookstorespa.angular.core.authentication;

import com.voghan.bookstorespa.angular.core.servlets.AbstractJsonSlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.jetbrains.annotations.NotNull;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component(service = { Servlet.class })
@SlingServletPaths("/bin/authenticate")
@ServiceDescription("Mock Login Servlet")
public class MockLoginServlet extends AbstractJsonSlingServlet {

    private final Logger logger = LoggerFactory.getLogger(MockLoginServlet.class);
    static Map<String,User> USERS = new HashMap<>();

    static {
        USERS.put("bill", new User("1001", "bill", "p12345", "Bill", "Walsh"));
        USERS.put("scott", new User("1002", "scott", "p12345", "Scott", "Allen"));
        USERS.put("jane", new User("1003", "jane", "p12345", "Jane", "Smith"));
        USERS.put("mark", new User("1004", "mark", "p12345", "Mark", "Jone"));
    }

    @Override
    protected void doGet(@NotNull SlingHttpServletRequest request, @NotNull SlingHttpServletResponse response) throws ServletException, IOException {

        try {
            logger.info("....... servlet get call received...");
            User user = USERS.get("scott");
            writeJson(response, user);
        } catch (IOException e) {
            logger.error("error writing JSON response", e);
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(@NotNull SlingHttpServletRequest request, @NotNull SlingHttpServletResponse response) throws ServletException, IOException {
        try {
            logger.info("....... servlet post call received...");
            User user = USERS.get("scott");
            writeJson(response, user);
        } catch (IOException e) {
            logger.error("error writing JSON response", e);
            throw new ServletException(e);
        }
    }
}
