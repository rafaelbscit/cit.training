package br.com.cit.contacts.view.config.mdc;

import ch.qos.logback.classic.ClassicConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * Filler Keys into MDC logger.
 * <p/>
 * Created by rafaelbs on 20/01/16.
 */
public abstract class MDCKeysFiller {

    private static final String UUID_MDC_KEY = "uuid";
    private static final String SEPARATOR_UUID_MDC = "-";
    private static final Logger LOGGER = LoggerFactory.getLogger(MDCKeysFiller.class);

    public static void fillUuidIntoMDC() {
        if (MDC.get(UUID_MDC_KEY) == null) {
            String uuidMDC = generateNewUuidMDC();
            MDC.put(UUID_MDC_KEY, uuidMDC);
            LOGGER.debug("New UUID key with value: {}", uuidMDC);
        } else {
            LOGGER.warn("UUID key already defined, value: {}", MDC.get(UUID_MDC_KEY));
        }
    }

    public static void fillServletKeysIntoMDC(ServletRequest request) {

        MDC.put(ClassicConstants.REQUEST_REMOTE_HOST_MDC_KEY, request
                .getRemoteHost());

        if (request != null && request instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            MDC.put(ClassicConstants.REQUEST_REQUEST_URI, httpServletRequest
                    .getRequestURI());

            StringBuffer requestURL = httpServletRequest.getRequestURL();
            if (requestURL != null) {
                MDC.put(ClassicConstants.REQUEST_REQUEST_URL, requestURL.toString());
            }

            MDC.put(ClassicConstants.REQUEST_METHOD, httpServletRequest.getMethod());
            MDC.put(ClassicConstants.REQUEST_QUERY_STRING, httpServletRequest.getQueryString());
            MDC.put(ClassicConstants.REQUEST_USER_AGENT_MDC_KEY, httpServletRequest
                    .getHeader("User-Agent"));
            MDC.put(ClassicConstants.REQUEST_X_FORWARDED_FOR, httpServletRequest
                    .getHeader("X-Forwarded-For"));

            LOGGER.debug("MDC Keys: {}.", MDC.getCopyOfContextMap().toString());
        }
    }

    public static void clearServletKeysMDC() {
        MDC.remove(ClassicConstants.REQUEST_REMOTE_HOST_MDC_KEY);
        MDC.remove(ClassicConstants.REQUEST_REQUEST_URI);
        MDC.remove(ClassicConstants.REQUEST_QUERY_STRING);

        // removing possibly inexistent item is OK
        MDC.remove(ClassicConstants.REQUEST_REQUEST_URL);
        MDC.remove(ClassicConstants.REQUEST_METHOD);
        MDC.remove(ClassicConstants.REQUEST_USER_AGENT_MDC_KEY);
        MDC.remove(ClassicConstants.REQUEST_X_FORWARDED_FOR);
    }

    public static void clearUuidKeysMDC() {
        MDC.remove(UUID_MDC_KEY);
    }

    public static String generateNewUuidMDC() {
        StringBuilder uuid = new StringBuilder();
        uuid.append(System.currentTimeMillis());
        return uuid.toString();
    }

}
