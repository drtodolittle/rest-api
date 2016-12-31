package de.drtodolittle.http

import javax.servlet.*
import javax.servlet.http.*
import de.drtodolittle.firebase.api.TokenService


public class ValidateTokenFilter implements Filter {

  private def service = null

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    def authorization = request.getHeader("Authorization")
    response.setHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, Authorization")
    response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, CONNECT, PATCH")
    response.setHeader("Access-Control-Allow-Origin", "*")
    response.setHeader("Access-Control-Max-Age", "3600")
    
    if (authorization != null) {
      def authorizationTokens = authorization.split(" ")
      if (authorizationTokens.size() == 2) {
        def token = authorizationTokens[1]
        String user = service.verify(token)

        if (user == null) {
          ((HttpServletResponse) response).sendError(401, "Invalid token: Token could not be verified.")
        }
        else {
          chain.doFilter(request, response);
        }
      }
      else {
        ((HttpServletResponse) response).sendError(401, "Invalid token: Authorization header is not correct.")
      }
    }
    else {
      ((HttpServletResponse) response).sendError(401, "Invalid token: Authorization header is not available.")
    }
  }

  public void destroy() {

  }

  public void init(FilterConfig config) {

  }

  public void setService(TokenService service) {
  	this.service=service
  }
}
