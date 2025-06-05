package top.xym.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class RequestLoggingFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        long startTime = System.currentTimeMillis();

        try {
            filterChain.doFilter(requestWrapper, response);
        } finally {
            long duration = System.currentTimeMillis() - startTime;
            logRequestDetails(requestWrapper, response, duration);
        }
    }

    private void logRequestDetails(ContentCachingRequestWrapper request, HttpServletResponse response, long duration) {
        StringBuilder logMessage = new StringBuilder();
        logMessage.append("HTTP Request: ");
        logMessage.append("Method=[").append(request.getMethod()).append("], ");
        logMessage.append("URI=[").append(request.getRequestURI()).append("], ");

        String queryString = request.getQueryString();
        if (queryString != null) {
            logMessage.append("QueryString=[").append(queryString).append("], ");
        }

        logMessage.append("Headers=[").append(getHeaders(request)).append("], ");

        String requestBody = getRequestBody(request);
        if (!requestBody.isEmpty()) {
            logMessage.append("Body=[").append(requestBody).append("], ");
        }

        logMessage.append("ResponseStatus=[").append(response.getStatus()).append("], ");
        logMessage.append("Duration=[").append(duration).append("ms]");

        logger.info(logMessage.toString());
    }

    private String getHeaders(HttpServletRequest request) {
        return Collections.list(request.getHeaderNames())
                .stream()
                .map(headerName -> headerName + ":" + Collections.list(request.getHeaders(headerName)))
                .collect(Collectors.joining(", "));
    }

    private String getRequestBody(ContentCachingRequestWrapper request) {
        byte[] content = request.getContentAsByteArray();
        if (content.length > 0) {
            try {
                return new String(content, request.getCharacterEncoding());
            } catch (UnsupportedEncodingException e) {
                logger.warn("Failed to read request body due to encoding issue", e);
                return "[Could not read body due to encoding issue]";
            }
        }
        return "";
    }
}
