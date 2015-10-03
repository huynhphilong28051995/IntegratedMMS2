<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="mail" scope="session" class="mms2.mail.sendmail.SendMail" />
<jsp:setProperty name="mail" property="to" param="to" />
<jsp:setProperty name="mail" property="from" value="Java.Mail.CA@gmail.com" />
<jsp:setProperty name="mail" property="smtpServ" value="smtp.gmail.com" />
<jsp:setProperty name="mail" property="subject" param="subject" />
<jsp:setProperty name="mail" property="message" param="message" />
<% 
   String query = request.getQueryString();
%>

<!DOCTYPE html>
<html>
    <head>
<meta charset="utf-8"/>
<title>Merlion Mall Asia | Add Employee</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta content="" name="description"/>
<meta content="" name="author"/>
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css">
<link href="../assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="../assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css">
<link href="../assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="../assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="../assets/global/plugins/bootstrap-toastr/toastr.min.css"/>
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" type="text/css" href="../assets/global/plugins/select2/select2.css"/>
<link rel="stylesheet" type="text/css" href="../assets/global/plugins/clockface/css/clockface.css"/>
<link rel="stylesheet" type="text/css" href="../assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css"/>
<link rel="stylesheet" type="text/css" href="../assets/global/plugins/bootstrap-timepicker/css/bootstrap-timepicker.min.css"/>
<link rel="stylesheet" type="text/css" href="../assets/global/plugins/bootstrap-colorpicker/css/colorpicker.css"/>
<link rel="stylesheet" type="text/css" href="../assets/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css"/>
<link rel="stylesheet" type="text/css" href="../assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"/>
<!-- END PAGE LEVEL SCRIPTS -->
<!-- BEGIN STYLES -->
<link href="../assets/global/css/components-rounded.css" id="style_components" rel="stylesheet" type="text/css">
<link href="../assets/global/css/components-rounded.css" id="style_components" rel="stylesheet" type="text/css">
<link href="../assets/global/css/plugins.css" rel="stylesheet" type="text/css">
<link href="../assets/admin/interface/css/layout.css" rel="stylesheet" type="text/css">
<link href="../assets/admin/interface/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color">
<link href="../assets/admin/interface/css/custom.css" rel="stylesheet" type="text/css">
<!-- END STYLES -->
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-menu-fixed">
    <% 
                    //Object generatedPassword = request.getAttribute("data");
                    Object generatedPassword = session.getAttribute("TempEmployeePW");
                    System.out.println("generatedPassword: " + generatedPassword);
                    Object generatedEmployeeID = request.getAttribute("newEmployeeID");
%>
<!-- BEGIN HEADER -->
<div class="page-header">
	<!-- BEGIN HEADER TOP -->
	<div class="page-header-top">
		<div class="container">
			<!-- BEGIN LOGO -->
			<div class="page-logo">
				<a href="adminHome"><img src="../assets/admin/interface/img/logo_small.png" alt="logo" class="logo-default"></a>
			</div>
			<!-- END LOGO -->
			<!-- BEGIN RESPONSIVE MENU TOGGLER -->
			<a href="javascript:;" class="menu-toggler"></a>
			<!-- END RESPONSIVE MENU TOGGLER -->
			<!-- BEGIN TOP NAVIGATION MENU -->
			<div class="top-menu">
				<ul class="nav navbar-nav pull-right">
						<span class="separator"></span>
					</li>
					<!-- BEGIN USER LOGIN DROPDOWN -->
					<li class="dropdown dropdown-user dropdown-dark">
						<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                        
						<span class="username username-hide-mobile">Welcome, <%= session.getAttribute("Session2") %></span>
						</a>
						<ul class="dropdown-menu dropdown-menu-default">
							<li>
								<a href="extra_profile">
								<i class="icon-user"></i> User Settings </a>
							</li>
							<li>
								<a href="logout">
								<i class="icon-key"></i> Log Out </a>
							</li>
						</ul>
					</li>
					<!-- END USER LOGIN DROPDOWN -->
				</ul>
			</div>
			<!-- END TOP NAVIGATION MENU -->
					
		</div>
	</div>
	<!-- END HEADER TOP -->
		<!-- BEGIN HEADER MENU -->
         <div class="page-header-menu">
            <div class="container">
               <!-- BEGIN HEADER SEARCH BOX -->
               <form class="search-form" action="extra_search.html" method="GET">
                  <div class="input-group">
                     <input type="text" class="form-control" placeholder="Search" name="query">
                     <span class="input-group-btn">
                     <a href="javascript:;" class="btn submit"><i class="icon-magnifier"></i></a>
                     </span>
                  </div>
               </form>
               <!-- END HEADER SEARCH BOX -->
               <div class="hor-menu ">
                  <ul class="nav navbar-nav">
                  <li>
                     <a href="adminHome">Admin Home</a>
                  </li>
                  <li class="menu-dropdown classic-menu-dropdown active">
                     <a data-hover="megamenu-dropdown" data-close-others="true" data-toggle="dropdown" href="javascript:;">
                     General Management <i class="fa fa-angle-down"></i>
                     </a>
                     <ul class="dropdown-menu pull-left">
                        <li class=" dropdown-submenu">
                           <a href="#">
                           Department </a>
                           <ul class="dropdown-menu">
                              <li class=" ">
                                 <a href="addDepartment">
                                 Add Department </a>
                              </li>
                              <li class=" ">
                                 <a href="manageDepartment">
                                 Manage Department </a>
                              </li>
                           </ul>
                        </li>
                        <li class=" dropdown-submenu">
                           <a href="#">
                           Employee </a>
                           <ul class="dropdown-menu">
                              <li class=" ">
                                 <a href="addEmployee">
                                 Add Employee </a>
                              </li>
                              <li class=" ">
                                 <a href="manageEmployee">
                                 Manage Employee </a>
                              </li>
                           </ul>
                        </li>
                        <li class=" dropdown-submenu">
                           <a href="#">
                           Position </a>
                           <ul class="dropdown-menu">
                              <li class=" ">
                                 <a href="addPosition">
                                 Add Position </a>
                              </li>
                              <li class=" ">
                                 <a href="managePosition">
                                 Manage Position </a>
                              </li>
                           </ul>
                        </li>
                     </ul>
                  </li>
                  <li class="menu-dropdown classic-menu-dropdown">
                     <a data-hover="megamenu-dropdown" data-close-others="true" data-toggle="dropdown" href="javascript:;">
                     Level Management <i class="fa fa-angle-down"></i>
                     </a>
                     <ul class="dropdown-menu pull-left">
                        <li class=" dropdown-submenu">
                           <a href="#">
                           Merlion Corporate Headquarter(s) </a>
                           <ul class="dropdown-menu">
                              <li class=" ">
                                 <a href="addLevel?corporate">
                                 Add Corporate Headquarter</a>
                              </li>
                              <li class=" ">
                                 <a href="manageLevel">
                                 Manage Headquarter</a>
                              </li>
                           </ul>
                        </li>
                        <li class=" dropdown-submenu">
                           <a href="#">
                           Merlion Office(s) </a>
                           <ul class="dropdown-menu">
                              <li class=" ">
                                 <a href="addLevel?local">
                                 Add Office </a>
                              </li>
                              <li class=" ">
                                 <a href="manageLevel">
                                 Manage Office(s) </a>
                              </li>
                           </ul>
                        </li>
                        <li class=" dropdown-submenu">
                           <a href="#">
                           Merlion Mall(s) </a>
                           <ul class="dropdown-menu">
                              <li class=" ">
                                 <a href="addLevel?mall">
                                 Add Mall </a>
                              </li>
                              <li class=" ">
                                 <a href="manageLevel">
                                 Manage Mall(s) </a>
                              </li>
                           </ul>
                        </li>
                     </ul>
					</li>
               </div>
               <!-- END MEGA MENU -->
            </div>
         </div>
         <!-- END HEADER MENU -->
</div>
<!-- END HEADER -->
<!-- BEGIN PAGE CONTAINER -->
<div class="page-container">
	<!-- BEGIN PAGE HEAD -->
	<div class="page-head">
		<div class="container">
			<!-- BEGIN PAGE TITLE -->
			<div class="page-title">
				<h1>Add Employee </h1>
			</div>
			<!-- END PAGE TITLE -->
		</div>
	</div>
	<!-- END PAGE HEAD -->
	<!-- BEGIN PAGE CONTENT -->
	<div class="page-content">
		<div class="container">
	
			<!-- BEGIN PAGE BREADCRUMB -->
			<ul class="page-breadcrumb breadcrumb">
				<li>
					<a href="#">Home</a><i class="fa fa-circle"></i>
				</li>
				<li>
					<a href="#">General Management</a>
					<i class="fa fa-circle"></i>
				</li>
				<li class="active">
					 Add Employee
				</li>
			</ul>
			<!-- END PAGE BREADCRUMB -->
			<!-- BEGIN PAGE CONTENT INNER -->
			<div class="row">
				<div class="col-md-12">
					<div class="portlet light" id="form_wizard_1">
						<div class="portlet-title">
							<div class="caption">
								<span class="caption-subject font-green-sharp bold uppercase">
								Add New Employee Wizard - <span class="step-title">Step 1 of 4 </span>
								</span>
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse">
								</a>
								<a href="javascript:;" class="remove">
								</a>
							</div>
						</div>
						<div class="portlet-body form">
							<form action="?create=true" class="form-horizontal" id="submit_form" method="post">
								<div class="form-wizard">
									<div class="form-body">
                                                                            <ul class="nav nav-pills nav-justified steps">
                                                                                        <li>
												<a href="#tab1" data-toggle="tab" class="step">
												<span class="number">
												1 </span>
												<span class="desc">
												<i class="fa fa-check"></i> LDP Selection </span>
												</a>
											</li>
											<li>
												<a href="#tab2" data-toggle="tab" class="step">
												<span class="number">
												2 </span>
												<span class="desc">
												<i class="fa fa-check"></i> Profile Setup </span>
												</a>
											</li>
											<li>
												<a href="#tab3" data-toggle="tab" class="step active">
												<span class="number">
												3 </span>
												<span class="desc">
												<i class="fa fa-check"></i> Account Setup </span>
												</a>
											</li>
											<li>
												<a href="#tab4" data-toggle="tab" class="step">
												<span class="number">
												4 </span>
												<span class="desc">
												<i class="fa fa-check"></i> Review and Confirm </span>
												</a>
											</li>
										</ul>
										<div id="bar" class="progress progress-striped" role="progressbar">
											<div class="progress-bar progress-bar-success">
											</div>
										</div>
										<div class="tab-content">
											<div class="alert alert-danger display-none">
												<button class="close" data-dismiss="alert"></button>
												Validation Check: Some fields require your attention.
											</div>
											<div class="alert alert-success display-none">
												<button class="close" data-dismiss="alert"></button>
												Success! User has been created.
											</div>
                                                                                        <div class="tab-pane active" id="tab1">
                                                                                            <h3 class="block">Specify Employee Level, Department and Position (LDP)</h3>

                                        		<div class="form-group">
                                                            <div class="col-md-4">
                                                            <iframe style="border: none; width: 1000px; height: 380px; padding-left: 180px;" src="ldpSelect"></iframe>
                                                            </div>
                                                        </div>     
                                                                                           
                                                                                            
                                                                                        </div>
											<div class="tab-pane active" id="tab2">
												<h3 class="block">Provide Employee Details</h3>
                                                <div class="form-group">
													<label class="control-label col-md-3">First Name <span class="required">
													* </span>
													</label>
													<div class="col-md-4">
														<input type="text" class="form-control" name="firstName" placeholder="Employee First Name"/>
													</div>
												</div>
                                                <div class="form-group">
													<label class="control-label col-md-3">Last Name <span class="required">
													* </span>
													</label>
													<div class="col-md-4">
														<input type="text" class="form-control" name="lastName" placeholder="Employee Last Name"/>
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-md-3">Mobile Number <span class="required">
													* </span>
													</label>
													<div class="col-md-4">
														<input type="text" class="form-control" name="mobileNumber" placeholder="Employee Mobile Number (For Password Reset)"/>
													</div>
												</div>
                                                <div class="form-group">
													<label class="control-label col-md-3">Office Number
													</label>
													<div class="col-md-4">
														<input type="text" class="form-control" name="officeNumber" placeholder="Employee Office Number (Omit Dialing Code)"/>
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-md-3">Gender <span class="required">
													* </span>
													</label>
													<div class="col-md-4">
														<div class="radio-list">
															<label>
															<input type="radio" name="gender" value="M" data-title="Male"/>
															Male </label>
															<label>
															<input type="radio" name="gender" value="F" data-title="Female"/>
															Female </label>
														</div>
														<div id="form_gender_error">
														</div>
													</div>
												</div>
                                                <div class="form-group">
													<label class="control-label col-md-3">Date of Birth</label>
                                                    
                                                    <div class="col-md-4">
                                                        <input name="dateOfBirth" class="form-control form-control-inline input-medium date-picker" data-date-format="dd/mm/yyyy" size="16" type="text" value="" placeholder="DD/MM/YYYY"/>
                                                    </div>
                                            	</div>
                                                <div class="form-group">
													<label class="control-label col-md-3">Nationality <span class="required">
													* </span> </label>
													<div class="col-md-4">
														<select name="country" id="country_list" class="form-control">
															<option value=""></option>
															<option value="AF">Afghanistan</option>
															<option value="AL">Albania</option>
															<option value="DZ">Algeria</option>
															<option value="AS">American Samoa</option>
															<option value="AD">Andorra</option>
															<option value="AO">Angola</option>
															<option value="AI">Anguilla</option>
															<option value="AR">Argentina</option>
															<option value="AM">Armenia</option>
															<option value="AW">Aruba</option>
															<option value="AU">Australia</option>
															<option value="AT">Austria</option>
															<option value="AZ">Azerbaijan</option>
															<option value="BS">Bahamas</option>
															<option value="BH">Bahrain</option>
															<option value="BD">Bangladesh</option>
															<option value="BB">Barbados</option>
															<option value="BY">Belarus</option>
															<option value="BE">Belgium</option>
															<option value="BZ">Belize</option>
															<option value="BJ">Benin</option>
															<option value="BM">Bermuda</option>
															<option value="BT">Bhutan</option>
															<option value="BO">Bolivia</option>
															<option value="BA">Bosnia and Herzegowina</option>
															<option value="BW">Botswana</option>
															<option value="BV">Bouvet Island</option>
															<option value="BR">Brazil</option>
															<option value="IO">British Indian Ocean Territory</option>
															<option value="BN">Brunei Darussalam</option>
															<option value="BG">Bulgaria</option>
															<option value="BF">Burkina Faso</option>
															<option value="BI">Burundi</option>
															<option value="KH">Cambodia</option>
															<option value="CM">Cameroon</option>
															<option value="CA">Canada</option>
															<option value="CV">Cape Verde</option>
															<option value="KY">Cayman Islands</option>
															<option value="CF">Central African Republic</option>
															<option value="TD">Chad</option>
															<option value="CL">Chile</option>
															<option value="CN">China</option>
															<option value="CX">Christmas Island</option>
															<option value="CC">Cocos (Keeling) Islands</option>
															<option value="CO">Colombia</option>
															<option value="KM">Comoros</option>
															<option value="CG">Congo</option>
															<option value="CD">Congo, the Democratic Republic of the</option>
															<option value="CK">Cook Islands</option>
															<option value="CR">Costa Rica</option>
															<option value="CI">Cote d'Ivoire</option>
															<option value="HR">Croatia (Hrvatska)</option>
															<option value="CU">Cuba</option>
															<option value="CY">Cyprus</option>
															<option value="CZ">Czech Republic</option>
															<option value="DK">Denmark</option>
															<option value="DJ">Djibouti</option>
															<option value="DM">Dominica</option>
															<option value="DO">Dominican Republic</option>
															<option value="EC">Ecuador</option>
															<option value="EG">Egypt</option>
															<option value="SV">El Salvador</option>
															<option value="GQ">Equatorial Guinea</option>
															<option value="ER">Eritrea</option>
															<option value="EE">Estonia</option>
															<option value="ET">Ethiopia</option>
															<option value="FK">Falkland Islands (Malvinas)</option>
															<option value="FO">Faroe Islands</option>
															<option value="FJ">Fiji</option>
															<option value="FI">Finland</option>
															<option value="FR">France</option>
															<option value="GF">French Guiana</option>
															<option value="PF">French Polynesia</option>
															<option value="TF">French Southern Territories</option>
															<option value="GA">Gabon</option>
															<option value="GM">Gambia</option>
															<option value="GE">Georgia</option>
															<option value="DE">Germany</option>
															<option value="GH">Ghana</option>
															<option value="GI">Gibraltar</option>
															<option value="GR">Greece</option>
															<option value="GL">Greenland</option>
															<option value="GD">Grenada</option>
															<option value="GP">Guadeloupe</option>
															<option value="GU">Guam</option>
															<option value="GT">Guatemala</option>
															<option value="GN">Guinea</option>
															<option value="GW">Guinea-Bissau</option>
															<option value="GY">Guyana</option>
															<option value="HT">Haiti</option>
															<option value="HM">Heard and Mc Donald Islands</option>
															<option value="VA">Holy See (Vatican City State)</option>
															<option value="HN">Honduras</option>
															<option value="HK">Hong Kong</option>
															<option value="HU">Hungary</option>
															<option value="IS">Iceland</option>
															<option value="IN">India</option>
															<option value="ID">Indonesia</option>
															<option value="IR">Iran (Islamic Republic of)</option>
															<option value="IQ">Iraq</option>
															<option value="IE">Ireland</option>
															<option value="IL">Israel</option>
															<option value="IT">Italy</option>
															<option value="JM">Jamaica</option>
															<option value="JP">Japan</option>
															<option value="JO">Jordan</option>
															<option value="KZ">Kazakhstan</option>
															<option value="KE">Kenya</option>
															<option value="KI">Kiribati</option>
															<option value="KP">Korea, Democratic People's Republic of</option>
															<option value="KR">Korea, Republic of</option>
															<option value="KW">Kuwait</option>
															<option value="KG">Kyrgyzstan</option>
															<option value="LA">Lao People's Democratic Republic</option>
															<option value="LV">Latvia</option>
															<option value="LB">Lebanon</option>
															<option value="LS">Lesotho</option>
															<option value="LR">Liberia</option>
															<option value="LY">Libyan Arab Jamahiriya</option>
															<option value="LI">Liechtenstein</option>
															<option value="LT">Lithuania</option>
															<option value="LU">Luxembourg</option>
															<option value="MO">Macau</option>
															<option value="MK">Macedonia, The Former Yugoslav Republic of</option>
															<option value="MG">Madagascar</option>
															<option value="MW">Malawi</option>
															<option value="MY">Malaysia</option>
															<option value="MV">Maldives</option>
															<option value="ML">Mali</option>
															<option value="MT">Malta</option>
															<option value="MH">Marshall Islands</option>
															<option value="MQ">Martinique</option>
															<option value="MR">Mauritania</option>
															<option value="MU">Mauritius</option>
															<option value="YT">Mayotte</option>
															<option value="MX">Mexico</option>
															<option value="FM">Micronesia, Federated States of</option>
															<option value="MD">Moldova, Republic of</option>
															<option value="MC">Monaco</option>
															<option value="MN">Mongolia</option>
															<option value="MS">Montserrat</option>
															<option value="MA">Morocco</option>
															<option value="MZ">Mozambique</option>
															<option value="MM">Myanmar</option>
															<option value="NA">Namibia</option>
															<option value="NR">Nauru</option>
															<option value="NP">Nepal</option>
															<option value="NL">Netherlands</option>
															<option value="AN">Netherlands Antilles</option>
															<option value="NC">New Caledonia</option>
															<option value="NZ">New Zealand</option>
															<option value="NI">Nicaragua</option>
															<option value="NE">Niger</option>
															<option value="NG">Nigeria</option>
															<option value="NU">Niue</option>
															<option value="NF">Norfolk Island</option>
															<option value="MP">Northern Mariana Islands</option>
															<option value="NO">Norway</option>
															<option value="OM">Oman</option>
															<option value="PK">Pakistan</option>
															<option value="PW">Palau</option>
															<option value="PA">Panama</option>
															<option value="PG">Papua New Guinea</option>
															<option value="PY">Paraguay</option>
															<option value="PE">Peru</option>
															<option value="PH">Philippines</option>
															<option value="PN">Pitcairn</option>
															<option value="PL">Poland</option>
															<option value="PT">Portugal</option>
															<option value="PR">Puerto Rico</option>
															<option value="QA">Qatar</option>
															<option value="RE">Reunion</option>
															<option value="RO">Romania</option>
															<option value="RU">Russian Federation</option>
															<option value="RW">Rwanda</option>
															<option value="KN">Saint Kitts and Nevis</option>
															<option value="LC">Saint LUCIA</option>
															<option value="VC">Saint Vincent and the Grenadines</option>
															<option value="WS">Samoa</option>
															<option value="SM">San Marino</option>
															<option value="ST">Sao Tome and Principe</option>
															<option value="SA">Saudi Arabia</option>
															<option value="SN">Senegal</option>
															<option value="SC">Seychelles</option>
															<option value="SL">Sierra Leone</option>
															<option value="SG">Singapore</option>
															<option value="SK">Slovakia (Slovak Republic)</option>
															<option value="SI">Slovenia</option>
															<option value="SB">Solomon Islands</option>
															<option value="SO">Somalia</option>
															<option value="ZA">South Africa</option>
															<option value="GS">South Georgia and the South Sandwich Islands</option>
															<option value="ES">Spain</option>
															<option value="LK">Sri Lanka</option>
															<option value="SH">St. Helena</option>
															<option value="PM">St. Pierre and Miquelon</option>
															<option value="SD">Sudan</option>
															<option value="SR">Suriname</option>
															<option value="SJ">Svalbard and Jan Mayen Islands</option>
															<option value="SZ">Swaziland</option>
															<option value="SE">Sweden</option>
															<option value="CH">Switzerland</option>
															<option value="SY">Syrian Arab Republic</option>
															<option value="TW">Taiwan, Province of China</option>
															<option value="TJ">Tajikistan</option>
															<option value="TZ">Tanzania, United Republic of</option>
															<option value="TH">Thailand</option>
															<option value="TG">Togo</option>
															<option value="TK">Tokelau</option>
															<option value="TO">Tonga</option>
															<option value="TT">Trinidad and Tobago</option>
															<option value="TN">Tunisia</option>
															<option value="TR">Turkey</option>
															<option value="TM">Turkmenistan</option>
															<option value="TC">Turks and Caicos Islands</option>
															<option value="TV">Tuvalu</option>
															<option value="UG">Uganda</option>
															<option value="UA">Ukraine</option>
															<option value="AE">United Arab Emirates</option>
															<option value="GB">United Kingdom</option>
															<option value="US">United States</option>
															<option value="UM">United States Minor Outlying Islands</option>
															<option value="UY">Uruguay</option>
															<option value="UZ">Uzbekistan</option>
															<option value="VU">Vanuatu</option>
															<option value="VE">Venezuela</option>
															<option value="VN">Viet Nam</option>
															<option value="VG">Virgin Islands (British)</option>
															<option value="VI">Virgin Islands (U.S.)</option>
															<option value="WF">Wallis and Futuna Islands</option>
															<option value="EH">Western Sahara</option>
															<option value="YE">Yemen</option>
															<option value="ZM">Zambia</option>
															<option value="ZW">Zimbabwe</option>
														</select>
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-md-3">Address <span class="required">
													* </span>
													</label>
													<div class="col-md-4">
														<input type="text" class="form-control" name="address" placeholder="Employee Residential Address"/>
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-md-3">City/Town
													</label>
													<div class="col-md-4">
														<input type="text" class="form-control" name="city" placeholder="Employee City/Town (Where Applicable)"/>
													</div>
												</div>
	
                                                <div class="form-group">
													<label class="control-label col-md-3">Contractual Period <span class="required">
													* </span>
                                                    </label>
 												<div class="col-md-4">
                                                    <div class="input-group input-large date-picker input-daterange" data-date-format="dd/mm/yyyy">
                                                        <input type="text" class="form-control" name="beginContract" placeholder="DD/MM/YYYY">
                                                        <span class="input-group-addon">
                                                        to </span>
                                                        <input type="text" class="form-control" name="endContract" placeholder="DD/MM/YYYY">
                                                    </div>
                                                    <!-- /input-group -->
                                                    </div>
                                                </div>
                                                
                                        
                                        
                                        
                                        
											</div>
											
											<div class="tab-pane" id="tab3">
												<h3 class="block">System Generated Account Credentials</h3>
                                                <div class="form-group">
													<label class="control-label col-md-3">Employee ID (Username)
													</label>
													<div class="col-md-4">
														<input type="text" class="form-control" name="employeeID" value="<%= generatedEmployeeID %>" disabled="disabled"/>
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-md-3">Password
													</label>
													<div class="col-md-4">
														<input type="text" class="form-control" name="password" value="<%= generatedPassword %>" disabled="disabled"/>
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-md-3">Employee Email<span class="required">
													* </span>
													</label>
													<div class="col-md-4">
														<input type="text" class="form-control" name="email"/>
													</div>
												</div>
                                                
												
											</div>
											<div class="tab-pane" id="tab4">
												<h3 class="block">Confirm Account Credentials</h3>
												<h4 class="form-section">Account At Glance</h4>
												<div class="form-group">
													<label class="control-label col-md-3">Employee ID (Username):</label>
													<div class="col-md-4">
														<p class="form-control-static" data-display="employeeID">
														</p>
													</div>
												</div>
                                                <div class="form-group">
													<label class="control-label col-md-3">Password:</label>
													<div class="col-md-4">
														<p class="form-control-static" data-display="password">
														</p>
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-md-3">Email:</label>
													<div class="col-md-4">
														<p class="form-control-static" data-display="email">
														</p>
													</div>
												</div>
												<h4 class="form-section">Profile At Glance</h4>
												<div class="form-group">
													<label class="control-label col-md-3">First Name:</label>
													<div class="col-md-4">
														<p class="form-control-static" data-display="firstName">
														</p>
													</div>
												</div>
                                                <div class="form-group">
													<label class="control-label col-md-3">Last Name:</label>
													<div class="col-md-4">
														<p class="form-control-static" data-display="lastName">
														</p>
													</div>
												</div>
                                                <div class="form-group">
													<label class="control-label col-md-3">Mobile Number:</label>
													<div class="col-md-4">
														<p class="form-control-static" data-display="mobileNumber">
														</p>
													</div>
												</div>
                                                <div class="form-group">
													<label class="control-label col-md-3">Office Number:</label>
													<div class="col-md-4">
														<p class="form-control-static" data-display="officeNumber">
														</p>
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-md-3">Gender:</label>
													<div class="col-md-4">
														<p class="form-control-static" data-display="gender">
														</p>
													</div>
												</div>
                                                <div class="form-group">
													<label class="control-label col-md-3">Date of Birth:</label>
													<div class="col-md-4">
														<p class="form-control-static" data-display="dateOfBirth">
														</p>
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-md-3">Country:</label>
													<div class="col-md-4">
														<p class="form-control-static" data-display="country">
														</p>
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-md-3">Address:</label>
													<div class="col-md-4">
														<p class="form-control-static" data-display="address">
														</p>
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-md-3">City/Town:</label>
													<div class="col-md-4">
														<p class="form-control-static" data-display="city">
														</p>
													</div>
												</div>
                                                <div class="form-group">
													<label class="control-label col-md-3">Contract Start:</label>
													<div class="col-md-4">
														<p class="form-control-static" data-display="beginContract">
														</p>
													</div>
												</div>
                                                <div class="form-group">
													<label class="control-label col-md-3">Contract End:</label>
													<div class="col-md-4">
														<p class="form-control-static" data-display="endContract">
														</p>
													</div>
												</div>
                                                                                                <div class="form-group">
													<label class="control-label col-md-3">Level Classification:</label>
													<div class="col-md-4">
														<p class="form-control-static" data-display="level">
                                                                                                                    <% if (session.getAttribute("TempLevelType") != null) { %><%= session.getAttribute("TempLevelType") %><% } %>
														</p>
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-md-3">Level Name</label>
													<div class="col-md-4">
														<p class="form-control-static" data-display="level">
                                                                                                                    <% if (session.getAttribute("TempLevelName") != null) { %><%= session.getAttribute("TempLevelName") %><% } %>
														</p>
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-md-3">Department:</label>
													<div class="col-md-4">
														<p class="form-control-static" data-display="department">
                                                                                                                    <% if (session.getAttribute("TempDepartmentName") != null) { %><%= session.getAttribute("TempDepartmentName") %><% } %>
														</p>
													</div>
												</div>	
												<div class="form-group">
													<label class="control-label col-md-3">Position Applied:</label>
													<div class="col-md-4">
														<p class="form-control-static" data-display="position">
                                                                                                                    <% if (session.getAttribute("TempPositionName") != null) { %><%= session.getAttribute("TempPositionName") %><% } %>
														</p>
													</div>
												</div>
											</div>
										</div>
									</div>
                                                                                                                
									<div class="form-actions">
										<div class="row">
											<div class="col-md-offset-3 col-md-9">
												<a href="javascript:;" class="btn default button-previous">
												<i class="m-icon-swapleft"></i> Back </a>
                                                                                            <a href="javascript:;" class="btn blue button-next" 
                                                                                               <% if (session.getAttribute("TempPositionName") != null) { } else {%>
                                                                                                disabled <% }%>>
												Continue <i class="m-icon-swapright m-icon-white"></i>
												</a>
                                                                                                <button type="submit" class="btn blue button-submit">
                                                                                                    Submit<i class="m-icon-swapright m-icon-white"></i></button>
												
                                                                                                
											</div>
										</div>
									</div>
								</div>
                                                                                                
							</form>
						</div>
					</div>
				</div>
			</div>
			<!-- END PAGE CONTENT INNER -->
		</div>
	</div>
	<!-- END PAGE CONTENT -->
</div>
<!-- END PAGE CONTAINER -->

<!-- BEGIN FOOTER -->
<div class="page-footer">
	<div class="container">
		 2015 &copy; Merlion Mall Asia. 
	</div>
</div>
<div class="scroll-to-top">
	<i class="icon-arrow-up"></i>
</div>
<!-- END FOOTER -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="../assets/global/plugins/respond.min.js"></script>
<script src="../assets/global/plugins/excanvas.min.js"></script> 
<![endif]-->
<script src="../assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="../assets/global/plugins/jquery-migrate.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="../assets/global/plugins/jquery-ui/jquery-ui.min.js" type="text/javascript"></script>
<script src="../assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="../assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
<script src="../assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="../assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="../assets/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="../assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>

<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script type="text/javascript" src="../assets/global/plugins/jquery-validation/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="../assets/global/plugins/jquery-validation/js/additional-methods.min.js"></script>
<script type="text/javascript" src="../assets/global/plugins/bootstrap-wizard/jquery.bootstrap.wizard.min.js"></script>
<script type="text/javascript" src="../assets/global/plugins/select2/select2.min.js"></script>
<script type="text/javascript" src="../assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="../assets/global/plugins/bootstrap-timepicker/js/bootstrap-timepicker.min.js"></script>
<script type="text/javascript" src="../assets/global/plugins/clockface/js/clockface.js"></script>
<script type="text/javascript" src="../assets/global/plugins/bootstrap-daterangepicker/moment.min.js"></script>
<script type="text/javascript" src="../assets/global/plugins/bootstrap-daterangepicker/daterangepicker.js"></script>
<script type="text/javascript" src="../assets/global/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.js"></script>
<script type="text/javascript" src="../assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="../assets/global/plugins/bootstrap-toastr/toastr.min.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="../assets/global/scripts/custom.js" type="text/javascript"></script>
<script src="../assets/admin/interface/scripts/layout.js" type="text/javascript"></script>
<script src="../assets/admin/pages/scripts/form-wizard.js"></script>
<script src="../assets/admin/pages/scripts/components-pickers.js"></script>
<script src="../assets/admin/pages/scripts/ui-toastr.js"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
jQuery(document).ready(function() {       
   // initiate layout and plugins
   Custom.init(); // init custom core components
   Layout.init(); // init current layout
   FormWizard.init();
   ComponentsPickers.init();
   UIToastr.init(); // init Toastr Alert
});
</script>

<% 
        if ("create=true".equals(query)) {
        String message = request.getAttribute("message").toString();
        if (message.equals("new")) {
            //send Email
            String employeeEmailAddress = request.getAttribute("email").toString();
            String firstName = request.getAttribute("firstName").toString();
            String lastName = request.getAttribute("lastName").toString();
            mail.sendMail(employeeEmailAddress, firstName, lastName);
      %>
      <script language="javascript">
        $(document).ready(function() {
            toastr.success('Employee Created Successfully!');
        });
      </script>
      <script language="javascript">
        $(document).ready(function() {
            toastr.info('Employee Welcome Email Sent!');
        });
      </script>
      <% } } %>

<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>
