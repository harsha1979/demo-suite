<%@ page import="com.wijesekara.projects.demo.sso.agent.UserManagement" %>
<%@ page import="org.json.JSONArray" %>
<%@ page import="org.json.JSONObject" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--
  ~
  ~ Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
  ~
  ~ WSO2 Inc. licenses this file to you under the Apache License,
  ~ Version 2.0 (the "License"); you may not use this file except
  ~ in compliance with the License.
  ~ You may obtain a copy of the License at
  ~
  ~     http://www.apache.org/licenses/LICENSE-2.0
  ~
  ~ Unless required by applicable law or agreed to in writing,
  ~ software distributed under the License is distributed on an
  ~ "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  ~ KIND, either express or implied.  See the License for the
  ~ specific language governing permissions and limitations
  ~ under the License.
  ~
  --%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Lebens - Foods for you</title>

    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <!--

    Template 2076 Zentro

    http://www.tooplate.com/view/2076-zentro

    -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/animate.min.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/nivo-lightbox.css">
    <link rel="stylesheet" href="css/nivo_themes/default/default.css">
    <link rel="stylesheet" href="css/style.css">
    <link href='https://fonts.googleapis.com/css?family=Roboto:400,500' rel='stylesheet' type='text/css'>
</head>
<body>

<!-- preloader section -->
<section class="preloader">
    <div class="sk-spinner sk-spinner-pulse"></div>
</section>

<!-- navigation section -->
<section class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon icon-bar"></span>
                <span class="icon icon-bar"></span>
                <span class="icon icon-bar"></span>
            </button>
            <a href="#" class="navbar-brand">LEBENS</a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#home" class="smoothScroll">HOME</a></li>
                <li><a href="#gallery" class="smoothScroll">FOOD GALLERY</a></li>
                <li><a href="#menu" class="smoothScroll">SPECIAL MENU</a></li>
                <li><a href="#contact" class="smoothScroll">RESERVE </a></li>
            </ul>
        </div>
    </div>
</section>


<!-- home section -->
<section id="home" class="parallax-section">
    <div class="container">
        <div class="row">
            <div class="col-md-12 col-sm-12">
                <div align="center">
                    <table border="0">
                        <tr><td>
                            <table border="0" cellpadding="5" width="300px">
                                <caption><h3>List of Users</h3></caption>
                                <tr>
                                    <th>User Name</th>
                                </tr>
                                <%
                                    JSONArray userList = UserManagement.getUserList();
                                    for (int i = 0; i < userList.length(); i++) {
                                        JSONObject jsonObj = (JSONObject)userList.get(i);
                                        String userName = (String)jsonObj.get("userName");
                                %>
                                <tr>
                                    <td><%=userName%></td>
                                </tr>
                                <%
                                    }
                                %>
                            </table>
                        </td></tr>
                        <tr><td>
                            <table border="0" cellpadding="5" width="300px">
                                <caption><h3>List of Roles</h3></caption>
                                <tr>
                                    <th>Role Name</th>
                                </tr>
                                <%
                                    JSONArray roleList = UserManagement.getRoleList();
                                    for (int i = 0; i < roleList.length(); i++) {
                                        JSONObject jsonObj = (JSONObject)roleList.get(i);
                                        String roleName = (String)jsonObj.get("displayName");
                                %>
                                <tr>
                                    <td><%=roleName%></td>
                                </tr>
                                <%
                                    }
                                %>
                            </table>
                        </td></tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>


<!-- gallery section -->
<section id="gallery" class="parallax-section">
    <div class="container">
        <div class="row">
            <div class="col-md-offset-2 col-md-8 col-sm-12 text-center">
                <h1 class="heading">Food Gallery</h1>
                <hr>
            </div>
            <div class="col-md-4 col-sm-4 wow fadeInUp" data-wow-delay="0.3s">
                <a href="images/gallery-img1.jpg" data-lightbox-gallery="zenda-gallery"><img src="images/gallery-img1.jpg" alt="gallery img"></a>
                <div>
                    <h3>Lemon-Rosemary Prawn</h3>
                    <span>Seafood / Shrimp / Lemon</span>
                </div>
                <a href="images/gallery-img2.jpg" data-lightbox-gallery="zenda-gallery"><img src="images/gallery-img2.jpg" alt="gallery img"></a>
                <div>
                    <h3>Lemon-Rosemary Vegetables</h3>
                    <span>Tomato / Rosemary / Lemon</span>
                </div>
            </div>
            <div class="col-md-4 col-sm-4 wow fadeInUp" data-wow-delay="0.6s">
                <a href="images/gallery-img3.jpg" data-lightbox-gallery="zenda-gallery"><img src="images/gallery-img3.jpg" alt="gallery img"></a>
                <div>
                    <h3>Lemon-Rosemary Bakery</h3>
                    <span>Bread / Rosemary / Orange</span>
                </div>
            </div>
            <div class="col-md-4 col-sm-4 wow fadeInUp" data-wow-delay="0.9s">
                <a href="images/gallery-img4.jpg" data-lightbox-gallery="zenda-gallery"><img src="images/gallery-img4.jpg" alt="gallery img"></a>
                <div>
                    <h3>Lemon-Rosemary Salad</h3>
                    <span>Chicken / Rosemary / Green</span>
                </div>
                <a href="images/gallery-img5.jpg" data-lightbox-gallery="zenda-gallery"><img src="images/gallery-img5.jpg" alt="gallery img"></a>
                <div>
                    <h3>Lemon-Rosemary Pizza</h3>
                    <span>Pasta / Rosemary / Green</span>
                </div>
            </div>
        </div>
    </div>
</section>


<!-- menu section -->
<section id="menu" class="parallax-section">
    <div class="container">
        <div class="row">
            <div class="col-md-offset-2 col-md-8 col-sm-12 text-center">
                <h1 class="heading">Special Menu</h1>
                <hr>
            </div>
            <div class="col-md-6 col-sm-6">
                <h4>Lemon-Rosemary Vegetable ................ <span>$20.50</span></h4>
                <h5>Chicken / Rosemary / Lemon</h5>
            </div>
            <div class="col-md-6 col-sm-6">
                <h4>Lemon-Rosemary Meat ........................... <span>$30.50</span></h4>
                <h5>Meat / Rosemary / Lemon</h5>
            </div>
            <div class="col-md-6 col-sm-6">
                <h4>Lemon-Rosemary Pork ........................ <span>$40.75</span></h4>
                <h5>Pork / Tooplate / Lemon</h5>
            </div>
            <div class="col-md-6 col-sm-6">
                <h4>Orange-Rosemary Salad .......................... <span>$55.00</span></h4>
                <h5>Salad / Rosemary / Orange</h5>
            </div>
            <div class="col-md-6 col-sm-6">
                <h4>Lemon-Rosemary Squid ...................... <span>$65.00</span></h4>
                <h5>Squid / Rosemary / Lemon</h5>
            </div>
            <div class="col-md-6 col-sm-6">
                <h4>Orange-Rosemary Shrimp ........................ <span>$70.50</span></h4>
                <h5>Shrimp / Rosemary / Orange</h5>
            </div>
            <div class="col-md-6 col-sm-6">
                <h4>Lemon-Rosemary Prawn ................... <span>$110.75</span></h4>
                <h5>Chicken / Rosemary / Lemon</h5>
            </div>
            <div class="col-md-6 col-sm-6">
                <h4>Lemon-Rosemary Seafood ..................... <span>$220.50</span></h4>
                <h5>Seafood / Rosemary / Lemon</h5>
            </div>
        </div>
    </div>
</section>

<!-- contact section -->
<section id="contact" class="parallax-section">
    <div class="container">
        <div class="row">
            <div class="col-md-offset-1 col-md-10 col-sm-12 text-center">
                <h1 class="heading">Reserve your table</h1>
                <hr>
            </div>
            <div class="col-md-offset-1 col-md-10 col-sm-12 wow fadeIn" data-wow-delay="0.9s">
                <form action="#" method="post">
                    <div class="col-md-6 col-sm-6">
                        <input name="name" type="text" class="form-control" id="name" placeholder="Name">
                    </div>
                    <div class="col-md-6 col-sm-6">
                        <input name="email" type="email" class="form-control" id="email" placeholder="Email">
                    </div>
                    <div class="col-md-12 col-sm-12">
                        <textarea name="message" rows="8" class="form-control" id="message" placeholder="Message"></textarea>
                    </div>
                    <div class="col-md-offset-3 col-md-6 col-sm-offset-3 col-sm-6">
                        <input name="submit" type="submit" class="form-control" id="submit" value="make a reservation">
                    </div>
                </form>
            </div>
            <div class="col-md-2 col-sm-1"></div>
        </div>
    </div>
</section>


<!-- footer section -->
<footer class="parallax-section">
    <div class="container">
        <div class="row">
            <div class="col-md-4 col-sm-4 wow fadeInUp" data-wow-delay="0.6s">
                <h2 class="heading">Contact Info.</h2>
                <div class="ph">
                    <p><i class="fa fa-phone"></i> Phone</p>
                    <h4>090-080-0760</h4>
                </div>
                <div class="address">
                    <p><i class="fa fa-map-marker"></i> Our Location</p>
                    <h4>120 Duis aute irure, California, USA</h4>
                </div>
            </div>
            <div class="col-md-4 col-sm-4 wow fadeInUp" data-wow-delay="0.6s">
                <h2 class="heading">Open Hours</h2>
                <p>Sunday <span>10:30 AM - 10:00 PM</span></p>
                <p>Mon-Fri <span>9:00 AM - 8:00 PM</span></p>
                <p>Saturday <span>11:30 AM - 10:00 PM</span></p>
            </div>
            <div class="col-md-4 col-sm-4 wow fadeInUp" data-wow-delay="0.6s">
                <h2 class="heading">Follow Us</h2>
                <ul class="social-icon">
                    <li><a href="#" class="fa fa-facebook wow bounceIn" data-wow-delay="0.3s"></a></li>
                    <li><a href="#" class="fa fa-twitter wow bounceIn" data-wow-delay="0.6s"></a></li>
                    <li><a href="#" class="fa fa-behance wow bounceIn" data-wow-delay="0.9s"></a></li>
                    <li><a href="#" class="fa fa-dribbble wow bounceIn" data-wow-delay="0.9s"></a></li>
                    <li><a href="#" class="fa fa-github wow bounceIn" data-wow-delay="0.9s"></a></li>
                </ul>
            </div>
        </div>
    </div>
</footer>


<!-- copyright section -->
<section id="copyright">
    <div class="container">
        <div class="row">
            <div class="col-md-12 col-sm-12">
                <h3>LEBENS</h3>
                <p>Powered by WSO2 Identity Server
            </div>
        </div>
    </div>
</section>

<!-- JAVASCRIPT JS FILES -->
<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.parallax.js"></script>
<script src="js/smoothscroll.js"></script>
<script src="js/nivo-lightbox.min.js"></script>
<script src="js/wow.min.js"></script>
<script src="js/custom.js"></script>

</body>
</html>