package br.com.cit.contacts.view.config.mdc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * MDC filter to fill in all web requests
 * <p/>
 * Created by rafaelbs on 20/01/16.
 */
@WebFilter(filterName = "MDCWebFilter",
        urlPatterns = {"/*"})
public class MDCWebFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(MDCWebFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        try {
            MDCKeysFiller.fillUuidIntoMDC();
            MDCKeysFiller.fillServletKeysIntoMDC(request);

            chain.doFilter(request, response);
        } finally {

            MDCKeysFiller.clearUuidKeysMDC();
            MDCKeysFiller.clearServletKeysMDC();
        }
    }

    @Override
    public void destroy() {
    }

}
