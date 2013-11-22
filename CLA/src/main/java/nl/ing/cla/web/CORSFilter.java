package nl.ing.cla.web;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Filter to allow cross origin resource sharing.
 *
 * Source: http://padcom13.blogspot.nl/2011/09/cors-filter-for-java-applications.html
 */
public class CORSFilter implements Filter {

	public CORSFilter() { }

	public void init(FilterConfig fConfig) throws ServletException { }

	public void destroy() {	}

	public void doFilter(
			ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		((HttpServletResponse)response).addHeader(
				"Access-Control-Allow-Origin", "*"
		);
		((HttpServletResponse)response).addHeader(
				"Access-Control-Allow-Methods", "GET,POST,DELETE,PUT"
		);
		((HttpServletResponse)response).addHeader(
				"Access-Control-Allow-Headers", "Content-Type"
		);
		chain.doFilter(request, response);
	}
}