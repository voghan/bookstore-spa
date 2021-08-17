package com.voghan.bookstorespa.angular.core.servlets;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageFilter;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

@Component(service = { Servlet.class })
@SlingServletResourceTypes(
        resourceTypes="bookstore-spa/components/structure/page",
        methods= HttpConstants.METHOD_GET,
        selectors = "sitemap",
        extensions="xml")
@ServiceDescription("Simple Demo Servlet")
public class SitemapServlet extends SlingSafeMethodsServlet {
    private static final long serialVersionUID = -536784266512058118L;

    private static final Logger log = LoggerFactory.getLogger(SitemapServlet.class);

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void doGet(SlingHttpServletRequest request,
                         SlingHttpServletResponse response) throws IOException {
        log.info("...professing request");
        response.setHeader("Content-Type", "text/xml");
        response.getWriter().print(generateSitemap(request));
    }

    private String generateSitemap(SlingHttpServletRequest request) {
        String rootPath = "/content/bookstore-spa/us/en/home";
        String hostPath = "http://" + request.getServerName().replaceAll("www","");
        if (request.getServerPort()!=80) {
            hostPath = hostPath + ":" + request.getServerPort();
        }

        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">";

        PageManager pageManager = request.getResourceResolver().adaptTo(PageManager.class);
        Page rootPage = pageManager.getPage(rootPath);

        // rootpage
        xml = xml + generateUrlNode(rootPath, rootPage, hostPath, "");

        // generate children
        xml = xml + childUrlNodes(rootPath, rootPage, hostPath, ".html");


        xml = xml + "</urlset>";

        return xml;
    }

    private String childUrlNodes(String rootPath, Page page, String hostPath, String extension) {
        StringBuilder childXml = new StringBuilder();
        // filter the pages to exclude hidden pages
        PageFilter filter = new PageFilter(false, true);

        Iterator<Page> pagesIter = page.listChildren(filter);

        while (pagesIter.hasNext()) {
            Page child = pagesIter.next();

            childXml.append(generateUrlNode(rootPath, child, hostPath, extension));

            Iterator<Page> childIter = child.listChildren(filter);
            if (childIter.hasNext()) {
                childXml.append(childUrlNodes(rootPath, child, hostPath, extension));
            }
        }

        return childXml.toString();
    }

    private String generateUrlNode(String rootPath, Page page, String hostPath, String extension) {
        String URL_BEGIN = "<url>";
        String URL_END = "</url>";

        String pagePath = page.getPath();
        pagePath = pagePath.replace(rootPath, "");

        String urlXml = URL_BEGIN + "<loc>" + hostPath + pagePath + extension + "</loc>";

        Date lastmod = page.getLastModified().getTime();

        urlXml = urlXml + "<lastmod>" + dateFormat.format(lastmod) + "</lastmod>";

        urlXml += "<priority>.5</priority><changefreq>monthly</changefreq>";


        return urlXml + URL_END;
    }

}

