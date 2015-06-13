package com.chloe.filters;

import com.chloe.entities.Event;
import com.chloe.services.EventProvider;
import com.chloe.services.FacebookEventProvider;
import java.io.IOException;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

@WebFilter(filterName = "GetEventsFilter", urlPatterns = {"jsp/events"})
public class GetEventsFilter extends CustomFilter {
    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        String code = request.getParameter("code");
        if(StringUtils.isNotBlank(code)) {
            EventProvider eventProvider = new FacebookEventProvider();
            eventProvider.login(code);
            List<Event> events = eventProvider.getEvents();
            request.setAttribute("events",events);
        }
        chain.doFilter(request, response);
    }
}
