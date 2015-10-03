<%-- 
    Document   : forgot
    Created on : Aug 25, 2015, 8:26:06 PM
    Author     : GOHENGCHI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Merlion Mall Asia | Forgot Password</title>

  <!-- Stylesheets -->
  <link rel="stylesheet" href="../assets/css/bootstrap.min.css">
  <link rel="stylesheet" href="../assets/css/bootstrap-extend.min.css">
  <link rel="stylesheet" href="../assets/css/site.min.css">
  <link rel="stylesheet" href="../assets/css/forgot.css">
  
  <!-- Plugins -->
  <link rel="stylesheet" href="../assets/vendor/animsition/animsition.min.css">
  
</head>
<body class="page-forgot layout-full page-dark">

  <!-- Page -->
  <div class="page animsition" data-animsition-in="fade-in" data-animsition-out="fade-out">
    <div class="page-content">
      <div class="page-brand-info">
        <div class="brand">
          <h2 style="font-size: 40px; color: #fff">Our Mission</h2>
        </div>
        <p class="font-size-20">To become the leading shopping mall in asia.  </p>
      </div>

      <div class="page-register-main">

        <form method="post" role="form" action="login">
          <div class="form-group">
          <h3 class="font-size-20">Forgot Your Password?</h3>
            <label class="sr-only" for="inputName">Full Name</label>
            <input type="text" class="form-control" id="inputName" name="name" placeholder="Your Employee ID">
          </div>
          <div class="form-group">
            <label class="sr-only" for="inputEmail">Email</label>
            <input type="email" class="form-control" id="inputEmail" name="email" placeholder="Your Company Email">
          </div>
         
          <button type="submit" class="btn btn-primary btn-block">Reset Your Password</button>
          <button type="submit" class="btn btn-primary btn-block">Back to Login</button>
        </form>

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

  <!-- Scripts For This Page -->
  <script src="../assets/js/components/jquery-placeholder.min.js"></script>

  <script>
    (function(document, window, $) {
      'use strict';

      var Site = window.Site;
      $(document).ready(function() {
        Site.run();
      });
    })(document, window, jQuery);
  </script>

</body>
</html>