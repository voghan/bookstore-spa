package com.voghan.bookstorespa.angular.core.servlets;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.MediaType;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.charset.Charset;

public class AbstractJsonSlingServlet extends SlingAllMethodsServlet {
    private static final JsonFactory FACTORY;
    private static final MediaType MEDIA_TYPE;
    private static final String ENCODING;
    private static final String CONTENT_TYPE;
    private static final long serialVersionUID = 1L;

    static {
        FACTORY = (new JsonFactory()).disable(JsonGenerator.Feature.AUTO_CLOSE_TARGET);
        MEDIA_TYPE = MediaType.JSON_UTF_8;
        ENCODING = ((Charset)MEDIA_TYPE.charset().get()).name();
        CONTENT_TYPE = MEDIA_TYPE.withoutParameters().toString();
    }

    public void writeJson(@NotNull SlingHttpServletResponse response, Object object) throws IOException {
        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(ENCODING);
        JsonGenerator generator = FACTORY.createGenerator(response.getWriter());
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(generator, object);
    }

}
