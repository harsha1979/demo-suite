/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.1
 * Generated at: 2018-01-11 11:40:21 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.wijesekara.projects.demo.sso.agent.UserManagement;
import org.json.JSONArray;
import org.json.JSONObject;

public final class user_002drole_002dmgt_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("org.json.JSONObject");
    _jspx_imports_classes.add("com.wijesekara.projects.demo.sso.agent.UserManagement");
    _jspx_imports_classes.add("org.json.JSONArray");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("<head>\n");
      out.write("    <meta charset=\"utf-8\">\n");
      out.write("    <title>Lebens - Foods for you</title>\n");
      out.write("\n");
      out.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=Edge\">\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("    <meta name=\"keywords\" content=\"\">\n");
      out.write("    <meta name=\"description\" content=\"\">\n");
      out.write("    <!--\n");
      out.write("\n");
      out.write("    Template 2076 Zentro\n");
      out.write("\n");
      out.write("    http://www.tooplate.com/view/2076-zentro\n");
      out.write("\n");
      out.write("    -->\n");
      out.write("    <link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">\n");
      out.write("    <link rel=\"stylesheet\" href=\"css/animate.min.css\">\n");
      out.write("    <link rel=\"stylesheet\" href=\"css/font-awesome.min.css\">\n");
      out.write("    <link rel=\"stylesheet\" href=\"css/nivo-lightbox.css\">\n");
      out.write("    <link rel=\"stylesheet\" href=\"css/nivo_themes/default/default.css\">\n");
      out.write("    <link rel=\"stylesheet\" href=\"css/style.css\">\n");
      out.write("    <link href='https://fonts.googleapis.com/css?family=Roboto:400,500' rel='stylesheet' type='text/css'>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\n");
      out.write("<!-- preloader section -->\n");
      out.write("<section class=\"preloader\">\n");
      out.write("    <div class=\"sk-spinner sk-spinner-pulse\"></div>\n");
      out.write("</section>\n");
      out.write("\n");
      out.write("<!-- navigation section -->\n");
      out.write("<section class=\"navbar navbar-default navbar-fixed-top\" role=\"navigation\">\n");
      out.write("    <div class=\"container\">\n");
      out.write("        <div class=\"navbar-header\">\n");
      out.write("            <button class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\".navbar-collapse\">\n");
      out.write("                <span class=\"icon icon-bar\"></span>\n");
      out.write("                <span class=\"icon icon-bar\"></span>\n");
      out.write("                <span class=\"icon icon-bar\"></span>\n");
      out.write("            </button>\n");
      out.write("            <a href=\"#\" class=\"navbar-brand\">LEBENS</a>\n");
      out.write("        </div>\n");
      out.write("        <div class=\"collapse navbar-collapse\">\n");
      out.write("            <ul class=\"nav navbar-nav navbar-right\">\n");
      out.write("                <li><a href=\"#home\" class=\"smoothScroll\">HOME</a></li>\n");
      out.write("                <li><a href=\"#gallery\" class=\"smoothScroll\">FOOD GALLERY</a></li>\n");
      out.write("                <li><a href=\"#menu\" class=\"smoothScroll\">SPECIAL MENU</a></li>\n");
      out.write("                <li><a href=\"#contact\" class=\"smoothScroll\">RESERVE </a></li>\n");
      out.write("            </ul>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</section>\n");
      out.write("\n");
      out.write("\n");
      out.write("<!-- home section -->\n");
      out.write("<section id=\"home\" class=\"parallax-section\">\n");
      out.write("    <div class=\"container\">\n");
      out.write("        <div class=\"row\">\n");
      out.write("            <div class=\"col-md-12 col-sm-12\">\n");
      out.write("                <div align=\"center\">\n");
      out.write("                    <table border=\"0\">\n");
      out.write("                        <tr><td>\n");
      out.write("                            <table border=\"0\" cellpadding=\"5\" width=\"300px\">\n");
      out.write("                                <caption><h3>List of Users</h3></caption>\n");
      out.write("                                <tr>\n");
      out.write("                                    <th>User Name</th>\n");
      out.write("                                </tr>\n");
      out.write("                                ");

                                    JSONArray userList = UserManagement.getUserList();
                                    for (int i = 0; i < userList.length(); i++) {
                                        JSONObject jsonObj = (JSONObject)userList.get(i);
                                        String userName = (String)jsonObj.get("userName");
                                
      out.write("\n");
      out.write("                                <tr>\n");
      out.write("                                    <td>");
      out.print(userName);
      out.write("</td>\n");
      out.write("                                </tr>\n");
      out.write("                                ");

                                    }
                                
      out.write("\n");
      out.write("                            </table>\n");
      out.write("                        </td></tr>\n");
      out.write("                        <tr><td>\n");
      out.write("                            <table border=\"0\" cellpadding=\"5\" width=\"300px\">\n");
      out.write("                                <caption><h3>List of Roles</h3></caption>\n");
      out.write("                                <tr>\n");
      out.write("                                    <th>Role Name</th>\n");
      out.write("                                </tr>\n");
      out.write("                                ");

                                    JSONArray roleList = UserManagement.getRoleList();
                                    for (int i = 0; i < roleList.length(); i++) {
                                        JSONObject jsonObj = (JSONObject)roleList.get(i);
                                        String roleName = (String)jsonObj.get("displayName");
                                
      out.write("\n");
      out.write("                                <tr>\n");
      out.write("                                    <td>");
      out.print(roleName);
      out.write("</td>\n");
      out.write("                                </tr>\n");
      out.write("                                ");

                                    }
                                
      out.write("\n");
      out.write("                            </table>\n");
      out.write("                        </td></tr>\n");
      out.write("                    </table>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</section>\n");
      out.write("\n");
      out.write("\n");
      out.write("<!-- gallery section -->\n");
      out.write("<section id=\"gallery\" class=\"parallax-section\">\n");
      out.write("    <div class=\"container\">\n");
      out.write("        <div class=\"row\">\n");
      out.write("            <div class=\"col-md-offset-2 col-md-8 col-sm-12 text-center\">\n");
      out.write("                <h1 class=\"heading\">Food Gallery</h1>\n");
      out.write("                <hr>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"col-md-4 col-sm-4 wow fadeInUp\" data-wow-delay=\"0.3s\">\n");
      out.write("                <a href=\"images/gallery-img1.jpg\" data-lightbox-gallery=\"zenda-gallery\"><img src=\"images/gallery-img1.jpg\" alt=\"gallery img\"></a>\n");
      out.write("                <div>\n");
      out.write("                    <h3>Lemon-Rosemary Prawn</h3>\n");
      out.write("                    <span>Seafood / Shrimp / Lemon</span>\n");
      out.write("                </div>\n");
      out.write("                <a href=\"images/gallery-img2.jpg\" data-lightbox-gallery=\"zenda-gallery\"><img src=\"images/gallery-img2.jpg\" alt=\"gallery img\"></a>\n");
      out.write("                <div>\n");
      out.write("                    <h3>Lemon-Rosemary Vegetables</h3>\n");
      out.write("                    <span>Tomato / Rosemary / Lemon</span>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"col-md-4 col-sm-4 wow fadeInUp\" data-wow-delay=\"0.6s\">\n");
      out.write("                <a href=\"images/gallery-img3.jpg\" data-lightbox-gallery=\"zenda-gallery\"><img src=\"images/gallery-img3.jpg\" alt=\"gallery img\"></a>\n");
      out.write("                <div>\n");
      out.write("                    <h3>Lemon-Rosemary Bakery</h3>\n");
      out.write("                    <span>Bread / Rosemary / Orange</span>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"col-md-4 col-sm-4 wow fadeInUp\" data-wow-delay=\"0.9s\">\n");
      out.write("                <a href=\"images/gallery-img4.jpg\" data-lightbox-gallery=\"zenda-gallery\"><img src=\"images/gallery-img4.jpg\" alt=\"gallery img\"></a>\n");
      out.write("                <div>\n");
      out.write("                    <h3>Lemon-Rosemary Salad</h3>\n");
      out.write("                    <span>Chicken / Rosemary / Green</span>\n");
      out.write("                </div>\n");
      out.write("                <a href=\"images/gallery-img5.jpg\" data-lightbox-gallery=\"zenda-gallery\"><img src=\"images/gallery-img5.jpg\" alt=\"gallery img\"></a>\n");
      out.write("                <div>\n");
      out.write("                    <h3>Lemon-Rosemary Pizza</h3>\n");
      out.write("                    <span>Pasta / Rosemary / Green</span>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</section>\n");
      out.write("\n");
      out.write("\n");
      out.write("<!-- menu section -->\n");
      out.write("<section id=\"menu\" class=\"parallax-section\">\n");
      out.write("    <div class=\"container\">\n");
      out.write("        <div class=\"row\">\n");
      out.write("            <div class=\"col-md-offset-2 col-md-8 col-sm-12 text-center\">\n");
      out.write("                <h1 class=\"heading\">Special Menu</h1>\n");
      out.write("                <hr>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"col-md-6 col-sm-6\">\n");
      out.write("                <h4>Lemon-Rosemary Vegetable ................ <span>$20.50</span></h4>\n");
      out.write("                <h5>Chicken / Rosemary / Lemon</h5>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"col-md-6 col-sm-6\">\n");
      out.write("                <h4>Lemon-Rosemary Meat ........................... <span>$30.50</span></h4>\n");
      out.write("                <h5>Meat / Rosemary / Lemon</h5>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"col-md-6 col-sm-6\">\n");
      out.write("                <h4>Lemon-Rosemary Pork ........................ <span>$40.75</span></h4>\n");
      out.write("                <h5>Pork / Tooplate / Lemon</h5>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"col-md-6 col-sm-6\">\n");
      out.write("                <h4>Orange-Rosemary Salad .......................... <span>$55.00</span></h4>\n");
      out.write("                <h5>Salad / Rosemary / Orange</h5>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"col-md-6 col-sm-6\">\n");
      out.write("                <h4>Lemon-Rosemary Squid ...................... <span>$65.00</span></h4>\n");
      out.write("                <h5>Squid / Rosemary / Lemon</h5>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"col-md-6 col-sm-6\">\n");
      out.write("                <h4>Orange-Rosemary Shrimp ........................ <span>$70.50</span></h4>\n");
      out.write("                <h5>Shrimp / Rosemary / Orange</h5>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"col-md-6 col-sm-6\">\n");
      out.write("                <h4>Lemon-Rosemary Prawn ................... <span>$110.75</span></h4>\n");
      out.write("                <h5>Chicken / Rosemary / Lemon</h5>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"col-md-6 col-sm-6\">\n");
      out.write("                <h4>Lemon-Rosemary Seafood ..................... <span>$220.50</span></h4>\n");
      out.write("                <h5>Seafood / Rosemary / Lemon</h5>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</section>\n");
      out.write("\n");
      out.write("<!-- contact section -->\n");
      out.write("<section id=\"contact\" class=\"parallax-section\">\n");
      out.write("    <div class=\"container\">\n");
      out.write("        <div class=\"row\">\n");
      out.write("            <div class=\"col-md-offset-1 col-md-10 col-sm-12 text-center\">\n");
      out.write("                <h1 class=\"heading\">Reserve your table</h1>\n");
      out.write("                <hr>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"col-md-offset-1 col-md-10 col-sm-12 wow fadeIn\" data-wow-delay=\"0.9s\">\n");
      out.write("                <form action=\"#\" method=\"post\">\n");
      out.write("                    <div class=\"col-md-6 col-sm-6\">\n");
      out.write("                        <input name=\"name\" type=\"text\" class=\"form-control\" id=\"name\" placeholder=\"Name\">\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"col-md-6 col-sm-6\">\n");
      out.write("                        <input name=\"email\" type=\"email\" class=\"form-control\" id=\"email\" placeholder=\"Email\">\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"col-md-12 col-sm-12\">\n");
      out.write("                        <textarea name=\"message\" rows=\"8\" class=\"form-control\" id=\"message\" placeholder=\"Message\"></textarea>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"col-md-offset-3 col-md-6 col-sm-offset-3 col-sm-6\">\n");
      out.write("                        <input name=\"submit\" type=\"submit\" class=\"form-control\" id=\"submit\" value=\"make a reservation\">\n");
      out.write("                    </div>\n");
      out.write("                </form>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"col-md-2 col-sm-1\"></div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</section>\n");
      out.write("\n");
      out.write("\n");
      out.write("<!-- footer section -->\n");
      out.write("<footer class=\"parallax-section\">\n");
      out.write("    <div class=\"container\">\n");
      out.write("        <div class=\"row\">\n");
      out.write("            <div class=\"col-md-4 col-sm-4 wow fadeInUp\" data-wow-delay=\"0.6s\">\n");
      out.write("                <h2 class=\"heading\">Contact Info.</h2>\n");
      out.write("                <div class=\"ph\">\n");
      out.write("                    <p><i class=\"fa fa-phone\"></i> Phone</p>\n");
      out.write("                    <h4>090-080-0760</h4>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"address\">\n");
      out.write("                    <p><i class=\"fa fa-map-marker\"></i> Our Location</p>\n");
      out.write("                    <h4>120 Duis aute irure, California, USA</h4>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"col-md-4 col-sm-4 wow fadeInUp\" data-wow-delay=\"0.6s\">\n");
      out.write("                <h2 class=\"heading\">Open Hours</h2>\n");
      out.write("                <p>Sunday <span>10:30 AM - 10:00 PM</span></p>\n");
      out.write("                <p>Mon-Fri <span>9:00 AM - 8:00 PM</span></p>\n");
      out.write("                <p>Saturday <span>11:30 AM - 10:00 PM</span></p>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"col-md-4 col-sm-4 wow fadeInUp\" data-wow-delay=\"0.6s\">\n");
      out.write("                <h2 class=\"heading\">Follow Us</h2>\n");
      out.write("                <ul class=\"social-icon\">\n");
      out.write("                    <li><a href=\"#\" class=\"fa fa-facebook wow bounceIn\" data-wow-delay=\"0.3s\"></a></li>\n");
      out.write("                    <li><a href=\"#\" class=\"fa fa-twitter wow bounceIn\" data-wow-delay=\"0.6s\"></a></li>\n");
      out.write("                    <li><a href=\"#\" class=\"fa fa-behance wow bounceIn\" data-wow-delay=\"0.9s\"></a></li>\n");
      out.write("                    <li><a href=\"#\" class=\"fa fa-dribbble wow bounceIn\" data-wow-delay=\"0.9s\"></a></li>\n");
      out.write("                    <li><a href=\"#\" class=\"fa fa-github wow bounceIn\" data-wow-delay=\"0.9s\"></a></li>\n");
      out.write("                </ul>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</footer>\n");
      out.write("\n");
      out.write("\n");
      out.write("<!-- copyright section -->\n");
      out.write("<section id=\"copyright\">\n");
      out.write("    <div class=\"container\">\n");
      out.write("        <div class=\"row\">\n");
      out.write("            <div class=\"col-md-12 col-sm-12\">\n");
      out.write("                <h3>LEBENS</h3>\n");
      out.write("                <p>Powered by WSO2 Identity Server\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</section>\n");
      out.write("\n");
      out.write("<!-- JAVASCRIPT JS FILES -->\n");
      out.write("<script src=\"js/jquery.js\"></script>\n");
      out.write("<script src=\"js/bootstrap.min.js\"></script>\n");
      out.write("<script src=\"js/jquery.parallax.js\"></script>\n");
      out.write("<script src=\"js/smoothscroll.js\"></script>\n");
      out.write("<script src=\"js/nivo-lightbox.min.js\"></script>\n");
      out.write("<script src=\"js/wow.min.js\"></script>\n");
      out.write("<script src=\"js/custom.js\"></script>\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
