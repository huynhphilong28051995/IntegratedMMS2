<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%
    String message = "";
    String query = request.getQueryString();
    if ("false".equals(query)) {
        System.out.println("Login Incorrect");
        message = "Please check your credentials and try again.";
    }
%>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

    <meta http-equiv="content-type" content="text/html;charset=utf-8" />
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Merlion Mall Asia | Employee Portal</title>

        <!-- Stylesheets -->
        <link rel="stylesheet" href="../assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="../assets/css/bootstrap-extend.min.css">
        <link rel="stylesheet" href="../assets/css/site.min.css">
        <link rel="stylesheet" href="../assets/css/login.css">

        <!-- Plugins -->
        <link rel="stylesheet" href="../assets/vendor/animsition/animsition.min.css">

    </head>
    <body class="page-login-v2 layout-full page-dark">

        <!-- Page -->
        <div class="page animsition" data-animsition-in="fade-in" data-animsition-out="fade-out">
            <div class="page-content">
                <div class="page-brand-info">
                    <div class="brand">
                        <h2 style="font-size: 40px; color: #fff">Our Vision</h2>
                    </div>
                    <p class="font-size-20" style="width: 480px;">At Merlion, we are aspired in creating the best shopping experience for all our customers. </p>
                </div>

                <div class="page-login-main">
                    <% if (session.getAttribute("Session") == null) { %>
                    <form method="post" action="pageRedirect">
                        <div class="form-group">
                            <h3 class="font-size-20">Employee Sign In</h3>
                            <label class="sr-only" for="inputEmail">Email</label>
                            <input type="email" class="form-control" id="inputEmail" name="email" placeholder="Employee Email / Employee ID">
                        </div>
                        <div class="form-group">
                            <label class="sr-only" for="inputPassword">Password</label>
                            <input type="password" class="form-control" id="inputPassword" name="password"
                                   placeholder="Password">
                        </div>
                        <div class="form-group clearfix">
                            <div class="pull-left">
                                <input type="checkbox" name="remembercheckbox" id="remembercheckbox" class="css-checkbox" /><label for="remembercheckbox" class="css-label">Remember me</label>
                            </div>
                            <a class="pull-right" href="forgot">Forgot password?</a>
                        </div>
                        <button type="submit" class="btn btn-primary btn-block">Sign in</button>
                    </form>
                    <% } else { %>
                    <form method="post" action="pageRedirect?continue">
                        <h3 style="padding-top: 40px;" class="font-size-20"><center>You are already logged in.</center></h3>
                        <button type="submit" class="btn btn-primary btn-block">Proceed</button>
                    </form>
                    <% }%>
                    <div style="font-weight: bold; color: red;"><center><%= message%></center></div>

                    <footer class="page-copyright">
                        <p>Copyright© 2015 Melion Mall Asia.<br />
                            Employee Portal v1.01.
                        </p>
                        <img class="" src="../assets/images/logo_small.png" alt="Merlion Mall Asia">
                    </footer>
                </div>

            </div>
        </div>
        <!-- End Page -->

        <!-- Core  -->
        <script src="../assets/vendor/jquery/jquery.min.js"></script>
        <script src="../assets/vendor/bootstrap/bootstrap.min.js"></script>
        <script src="../assets/vendor/animsition/jquery.animsition.min.js"></script>

        <!-- Scripts -->
        <script src="../assets/js/core.min.js"></script>
        <script src="../assets/js/site.min.js"></script>
        <script src="../assets/js/components/animsition.min.js"></script>

        <script>
            (function (document, window, $) {
                'use strict';

                var Site = window.Site;
                $(document).ready(function () {
                    Site.run();
                });
            })(document, window, jQuery);
        </script>

    </body>
</html>