/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chloe.filters;

import com.chloe.services.EventProvider;
import com.chloe.services.FacebookEventProvider;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Martín
 */
@WebFilter(filterName = "GetEventsFilter", urlPatterns = {"/events"})
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
        
    }
}
