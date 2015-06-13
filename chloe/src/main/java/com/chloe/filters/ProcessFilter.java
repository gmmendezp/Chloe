package com.chloe.filters;

import com.chloe.classifier.EventClassifier;
import com.chloe.classifier.SolrEventClassifier;
import com.chloe.entities.Event;
import com.chloe.services.BackcountryItemProvider;
import com.chloe.services.EventProvider;
import com.chloe.services.FacebookEventProvider;
import com.chloe.services.ItemProvider;
import java.io.IOException;
import java.util.Collection;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

@WebFilter(filterName = "ProcessFilter", urlPatterns = {"/widget.jsp"})
public class ProcessFilter extends CustomFilter {

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
        Collection<Event> events;
        if (StringUtils.isNotBlank(code)) {
            EventProvider eventProvider = new FacebookEventProvider();
            eventProvider.login(code);
            events = eventProvider.getEvents();

            EventClassifier sec = new SolrEventClassifier();
            sec.classify(events);

            ItemProvider itemProvider = new BackcountryItemProvider();
            itemProvider.getItems(events);

            request.setAttribute("events", events);
        }
        chain.doFilter(request, response);
    }
}
