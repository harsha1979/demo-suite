/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.1
 * Generated at: 2018-01-11 07:13:41 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import java.util.Map;
import java.util.Iterator;
import org.wso2.carbon.identity.sso.agent.bean.LoggedInSessionBean;
import org.wso2.carbon.identity.sso.agent.bean.SSOAgentConfig;
import org.wso2.carbon.identity.sso.agent.SSOAgentConstants;

public final class home_jsp extends org.apache.jasper.runtime.HttpJspBase
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
    _jspx_imports_classes.add("java.util.List");
    _jspx_imports_classes.add("org.wso2.carbon.identity.sso.agent.SSOAgentConstants");
    _jspx_imports_classes.add("java.util.Iterator");
    _jspx_imports_classes.add("java.util.Map");
    _jspx_imports_classes.add("org.wso2.carbon.identity.sso.agent.bean.SSOAgentConfig");
    _jspx_imports_classes.add("org.wso2.carbon.identity.sso.agent.bean.LoggedInSessionBean");
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

      out.write("SampleContextEventListener<!--\n");
      out.write("~ Copyright (c) WSO2 Inc. (http://www.wso2.org) All Rights Reserved.\n");
      out.write("~\n");
      out.write("~ WSO2 Inc. licenses this file to you under the Apache License,\n");
      out.write("~ Version 2.0 (the \"License\"); you may not use this file except\n");
      out.write("~ in compliance with the License.\n");
      out.write("~ You may obtain a copy of the License at\n");
      out.write("~\n");
      out.write("~    http://www.apache.org/licenses/LICENSE-2.0\n");
      out.write("~\n");
      out.write("~ Unless required by applicable law or agreed to in writing,\n");
      out.write("~ software distributed under the License is distributed on an\n");
      out.write("~ \"AS IS\" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY\n");
      out.write("~ KIND, either express or implied.  See the License for the\n");
      out.write("~ specific language governing permissions and limitations\n");
      out.write("~ under the License.\n");
      out.write("-->\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\n");
      out.write("\"http://www.w3.org/TR/html4/loose.dtd\">\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/cart-styles.css\">\n");
      out.write("    <title>Role Administration</title>\n");
      out.write("</head>\n");

    String claimedId = null;
    String subjectId = null;
    Map<String, List<String>> openIdAttributes = null;
    Map<String, String> saml2SSOAttributes = null;
    if(request.getSession(false) != null &&
            request.getSession(false).getAttribute(SSOAgentConstants.SESSION_BEAN_NAME) == null){
        request.getSession().invalidate();

      out.write("\n");
      out.write("        <script type=\"text/javascript\">\n");
      out.write("        location.href = \"index.jsp\";\n");
      out.write("        </script>\n");

        return;
    }
    SSOAgentConfig ssoAgentConfig = (SSOAgentConfig)getServletContext().getAttribute(SSOAgentConstants.CONFIG_BEAN_NAME);
    LoggedInSessionBean sessionBean = (LoggedInSessionBean)session.getAttribute(SSOAgentConstants.SESSION_BEAN_NAME);
    LoggedInSessionBean.AccessTokenResponseBean accessTokenResponseBean = null;


    if(sessionBean != null){
        if(sessionBean.getOpenId() != null) {
            claimedId = sessionBean.getOpenId().getClaimedId();
            openIdAttributes = sessionBean.getOpenId().getSubjectAttributes();
        } else if(sessionBean.getSAML2SSO() != null) {
            subjectId = sessionBean.getSAML2SSO().getSubjectId();
            saml2SSOAttributes = sessionBean.getSAML2SSO().getSubjectAttributes();
            accessTokenResponseBean = sessionBean.getSAML2SSO().getAccessTokenResponseBean();

        } else {

      out.write("\n");
      out.write("            <script type=\"text/javascript\">\n");
      out.write("                location.href = \"index.jsp\";\n");
      out.write("            </script>\n");

            return;
        }
    } else {

      out.write("\n");
      out.write("        <script type=\"text/javascript\">\n");
      out.write("            location.href = \"index.jsp\";\n");
      out.write("        </script>\n");

        return;
    }

      out.write("\n");
      out.write("<body>\n");
      out.write("<div>\n");
      out.write("    <div id=\"header-area\">\n");
      out.write("        <img src=\"images/cart-logo.gif\" alt=\"Logo\" vspace=\"10\" />\n");
      out.write("    </div>\n");
      out.write("    <div id=\"content-area\">\n");
      out.write("        <div class=\"cart-tabs\">\n");
      out.write("            <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n");
      out.write("                <tr>\n");
      out.write("                    <td class=\"cart-tab-left\">\n");
      out.write("                        <img src=\"images/cart-tab-left.gif\" alt=\"-\">\n");
      out.write("                    </td>\n");
      out.write("                    <td class=\"cart-tab-mid\">\n");
      out.write("                        <a>Home</a>\n");
      out.write("                    </td>\n");
      out.write("                    <td class=\"cart-tab-right\">\n");
      out.write("                        <img src=\"images/cart-tab-right.gif\" alt=\"-\">\n");
      out.write("                    </td>\n");
      out.write("                </tr>\n");
      out.write("            </table>\n");
      out.write("        </div>\n");
      out.write("        <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" class=\"cart-expbox\">\n");
      out.write("            <tr>\n");
      out.write("                <td><img src=\"images/cart-expbox-01.gif\" alt=\"-\"></td>\n");
      out.write("                <td class=\"cart-expbox-02\">&nbsp</td>\n");
      out.write("                <td><img src=\"images/cart-expbox-03.gif\" alt=\"-\"></td>\n");
      out.write("            </tr>\n");
      out.write("            <tr>\n");
      out.write("                <td class=\"cart-expbox-08\">&nbsp</td>\n");
      out.write("                <td class=\"cart-expbox-09\">\n");
      out.write("                    <!--all content for cart and links goes here-->\n");
      out.write("                </td>\n");
      out.write("                <td class=\"cart-expbox-04\">&nbsp</td>\n");
      out.write("            </tr>\n");
      out.write("            <tr>\n");
      out.write("                <td><img src=\"images/cart-expbox-07.gif\" alt=\"-\"></td>\n");
      out.write("                <td class=\"cart-expbox-06\">&nbsp</td>\n");
      out.write("                <td><img src=\"images/cart-expbox-05.gif\" alt=\"-\"></td>\n");
      out.write("            </tr>\n");
      out.write("\n");
      out.write("        </table>\n");
      out.write("        <h1>Hospital Management System - Admin application</h1>\n");
      out.write("        <hr />\n");
      out.write("        <div class=\"product-box\">\n");
      out.write("\n");
      out.write("            ");

                if(subjectId != null){
            
      out.write("\n");
      out.write("                    <h2> Hello! ");
      out.print(subjectId);
      out.write("</h2>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("            ");

                } else if (claimedId != null) {
            
      out.write("\n");
      out.write("                    <h2> You are logged in as ");
      out.print(claimedId);
      out.write("</h2>\n");
      out.write("            ");

                }
            
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("  <a href=\"usermgt.jsp\"> User Administration</a>&nbsp;<form id=\"form3\" action=\"viewRoles\" method=\"post\">  <a href=\"javascript:;\" onclick=\"document.getElementById('form3').submit();\">View Roles</a> <input type=\"hidden\" name=\"viewRoles\" value=\"View Roles\"/> </form>&nbsp;<form id=\"form2\" action=\"deleteUsers\" method=\"post\"> <a href=\"javascript:;\" onclick=\"document.getElementById('form2').submit();\">Delete Users</a> <input type=\"hidden\" name=\"delete\" value=\"Delete Users\"/> </form> &nbsp;<form id=\"form1\" action=\"userroleview\" method=\"post\"> <a href=\"javascript:;\" onclick=\"document.getElementById('form1').submit();\">View Users</a><input type=\"hidden\" name=\"view\" value=\"View Users\"/></form>&nbsp;<form id=\"form4\" action=\"changeUserPword\" method=\"post\"> <a href=\"javascript:;\" onclick=\"document.getElementById('form4').submit();\">Change Password of a User</a> <input type=\"hidden\" name=\"change\" value=\"Change Password\"/> </form>\n");
      out.write("                <h2>Add New Roles</h2>\n");
      out.write("\n");
      out.write("             <div class=\"product-box\">\n");
      out.write("            <form method=\"post\" action=\"rolesubmit\">\n");
      out.write("\n");
      out.write("\n");
      out.write("                <h2>Input details to add the new User Role </h2>\n");
      out.write("                Role Name : <input type=\"text\" name=\"roleName\" id=\"roleName\"/>\n");
      out.write("                 <br/>\n");
      out.write("                  <br/>\n");
      out.write("                User List : <input type=\"text\" name=\"users\" id=\"users\"/>\n");
      out.write("                                  <br/>\n");
      out.write("                                  <br/>\n");
      out.write("                Permission : <input type=\"text\" name=\"permission\" id=\"permission\">\n");
      out.write("                  <br/>\n");
      out.write("                    <input type=\"hidden\" id=\"authorization\" name=\"authorization\" value=\"");
      out.print(session.getAttribute("authorization"));
      out.write("\" />\n");
      out.write("                 <br/>\n");
      out.write("                <input type=\"submit\" value=\"Save\"/>\n");
      out.write("            </form>\n");
      out.write("             </div>\n");
      out.write("\n");
      out.write("               <table>\n");
      out.write("                ");

                    if(saml2SSOAttributes != null){
                        for (Map.Entry<String, String> entry:saml2SSOAttributes.entrySet()) {
                
      out.write("\n");
      out.write("                            <tr>\n");
      out.write("                                <td>");
      out.print(entry.getKey());
      out.write("</td>\n");
      out.write("                                <td>");
      out.print(entry.getValue());
      out.write("</td>\n");
      out.write("                            </tr>\n");
      out.write("                ");

                        }
                    } else if (openIdAttributes != null) {
                        for(Map.Entry<String, List<String>> entry:openIdAttributes.entrySet()) {
                
      out.write("\n");
      out.write("                            <tr>\n");
      out.write("                                <td>");
      out.print(entry.getKey());
      out.write("</td>\n");
      out.write("                                <td>\n");
      out.write("                                    ");

                                        Iterator it = entry.getValue().iterator();
                                        if(it.hasNext()){
                                    
      out.write("\n");
      out.write("                                            ");
      out.print(it.next().toString());
      out.write("\n");
      out.write("                                    ");

                                        }
                                    
      out.write("\n");
      out.write("                                </td>\n");
      out.write("                            </tr>\n");
      out.write("                ");

                        }
                    }
                
      out.write("\n");
      out.write("            </table>\n");
      out.write("            ");

                if (subjectId != null) {
                    if(accessTokenResponseBean != null) {
            
      out.write("\n");
      out.write("                        <u><b>Your OAuth2 Access Token details</b></u>\n");
      out.write("                        <div style=\"text-indent: 50px\">Token Type: ");
      out.print(accessTokenResponseBean.getTokenType());
      out.write(" <br/></div>\n");
      out.write("                        <div style=\"text-indent: 50px\">Access Token: ");
      out.print(accessTokenResponseBean.getAccessToken());
      out.write(" <br/></div>\n");
      out.write("                        <div style=\"text-indent: 50px\">Refresh Token: ");
      out.print(accessTokenResponseBean.getRefreshToken());
      out.write(" <br/></div>\n");
      out.write("                        <div style=\"text-indent: 50px\">Expiry In: ");
      out.print(accessTokenResponseBean.getExpiresIn());
      out.write(" <br/></div>\n");
      out.write("            ");

                    } else {
                        if(ssoAgentConfig.isOAuth2SAML2GrantEnabled()){
            
      out.write("\n");
      out.write("                            <a href=\"token\">Request OAuth2 Access Token</a><br/>\n");
      out.write("            ");


                        }
                    }
                }
            
      out.write("\n");
      out.write("            <hr/>\n");
      out.write("            ");

                if(subjectId != null && ssoAgentConfig.getSAML2().isSLOEnabled()){
            
      out.write("\n");
      out.write("            <a href=\"logout?SAML2.HTTPBinding=HTTP-Redirect\">Logout (HTTPRedirect)\n");
      out.write("            </a>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"logout?SAML2.HTTPBinding=HTTP-POST\">Logout (HTTP Post)</a><br/>\n");
      out.write("\n");
      out.write("            ");

                }
            
      out.write("\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("    <div id=\"footer-area\">\n");
      out.write("        <p>©2014 WSO2</p>\n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("</body>\n");
      out.write("</html>\n");
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
