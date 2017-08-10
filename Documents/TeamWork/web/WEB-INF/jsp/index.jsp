<%-- 
    Document   : index
    Created on : 26-Jul-2017, 18:43:49
    Author     : sumukh.r
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.Paladion.teamwork.beans.UserDataBean"%>
<!DOCTYPE html>
<html>
    <head>
  <title>Code Review Knowledge Base</title>
  <meta name="description" content="website description" />
  <meta name="keywords" content="website keywords, website keywords" />
  <meta http-equiv="content-type" content="text/html; charset=windows-1252" />
  
<style>
html
{ height: 100%;
  
}

*
{ margin: 0;
  padding: 0;}

body
{ font: normal .80em 'trebuchet ms', arial, sans-serif;
  background: #FFF;
  color: #555;
}

p
{ padding: 0 0 20px 0;
  line-height: 1.7em;}

img
{ border: 0;}

h1, h2, h3, h4, h5, h6 
{ color: #E7746F;
  letter-spacing: 0em;
  padding: 0 0 5px 0;}

h1, h2, h3
{ font: normal 170% 'century gothic', arial;
  margin: 0 0 15px 0;
  padding: 15px 0 5px 0;}

h2
{ font-size: 160%;
  padding: 9px 0 5px 0;}

h3
{ font-size: 140%;
  padding: 5px 0 0 0;}

h4, h6
{ color: #009FBC;
  padding: 0 0 5px 0;
  font: normal 110% arial;
  text-transform: uppercase;}

h5, h6
{ color: #888;
  font: normal 95% arial;
  letter-spacing: normal;
  padding: 0 0 15px 0;}

a, a:hover
{ outline: none;
  text-decoration: underline;
  color: #009FBC;}

a:hover
{ text-decoration: none;}

blockquote
{ margin: 20px 0; 
  padding: 10px 20px 0 20px;
  border: 1px solid #E5E5DB;
  background: #FFF;}

ul
{ margin: 2px 0 22px 17px;}

ul li
{ list-style-type: circle;
  margin: 0 0 6px 0; 
  padding: 0 0 4px 5px;
  line-height: 1.5em;}

ol
{ margin: 8px 0 22px 20px;}

ol li
{ margin: 0 0 11px 0;}

.left
{ float: left;
  width: auto;
  margin-right: 10px;}

.right
{ float: right; 
  width: auto;
  margin-left: 10px;}

.center
{ display: block;
  text-align: center;
  margin: 20px auto;}

#main, #logo, #menubar, #site_content, #footer
{ margin-left: auto; 
  margin-right: auto;}

#header
{ background: #1d1d1d url(pattern.png) repeat;
  border-bottom: 2px solid #E7746F;
  height: 177px;}
  
#banner
{ background: transparent url(banner.jpg) no-repeat;
  width: 870px;
  height: 180px;
  margin-bottom: 20px;
  border: 5px solid #E7746F;}

#logo
{ width: 880px;
  position: relative;
  height: 140px;
  background: transparent;}

#logo #logo_text 
{ position: absolute; 
  top: 10px;
  left: 0;}

#logo h1, #logo h2
{ font: normal 300% 'century gothic', arial, sans-serif;
  border-bottom: 0;
  text-transform: none;
  margin: 0 0 0 9px;}

#logo_text h1, #logo_text h1 a, #logo_text h1 a:hover 
{ padding: 22px 0 0 0;
  color: #FFF;
  letter-spacing: 0.1em;
  text-decoration: none;}

#logo_text h1 a .logo_colour
{ color: #E7746F;}

#logo_text a:hover .logo_colour
{ color: #FFF;}

#logo_text h2
{ font-size: 120%;
  padding: 4px 0 0 0;
  color: #FFF;}

#menubar
{ width: 880px;
  height: 46px;} 

ul#menu
{ float: right;
  margin: 0;}

ul#menu li
{ float: left;
  padding: 0 0 0 9px;
  list-style: none;
  margin: 1px 2px 0 0;
  background: transparent;}

ul#menu li a
{ font: normal 100% 'trebuchet ms', sans-serif;
  display: block; 
  float: left; 
  height: 20px;
  padding: 6px 35px 5px 28px;
  text-align: center;
  color: #FFF;
  text-decoration: none;
  background: transparent;} 

ul#menu li.selected a
{ height: 20px;
  padding: 6px 35px 5px 28px;}

ul#menu li.selected
{ margin: 1px 2px 0 0;
  background: #E7746F;}

ul#menu li.selected a, ul#menu li.selected a:hover
{ background: #E7746F;
  color: #FFF;}

ul#menu li a:hover
{ color: #FFF;
  background: #E7746F;}

#site_content
{ width: 880px;
  overflow: hidden;
  margin: 20px auto 0 auto;
  padding: 0 0 10px 0;} 

#sidebar_container
{ float: right;
  width: 224px;}

.sidebar_top
{ width: 222px;
  height: 14px;
  background: transparent url(side_top.png) no-repeat;}

.sidebar_base
{ width: 222px;
  height: 14px;
  background: url(side_base.png) no-repeat;}

.sidebar
{ float: right;
  width: 222px;
  padding: 0;
  margin: 0 0 16px 0;}

.sidebar_item
{ background: url(side_back.png) repeat-y;
  padding: 0 15px;
  width: 192px;}

.sidebar li a.selected
{ color: #444;} 

.sidebar ul
{ margin: 0;} 

#content
{ text-align: left;
  width: 620px;
  padding: 0 0 0 5px;
  float: left;}
  
#content ul
{ margin: 2px 0 22px 0px;}

#content ul li, .sidebar ul li
{ list-style-type: none;
  background: url(bullet.png) no-repeat;
  margin: 0 0 0 0; 
  padding: 0 0 4px 25px;
  line-height: 1.5em;}

#footer
{ width: 100%;
  font-family: 'trebuchet ms', sans-serif;
  font-size: 100%;
  height: 80px;
  padding: 28px 0 5px 0;
  text-align: center; 
  background: transparent url(pattern.png) repeat;
  border-top: 2px solid #E7746F;
  color: #FFF;}

#footer p
{ line-height: 1.7em;
  padding: 0 0 10px 0;}

#footer a
{ color: #E7746F;
  text-decoration: none;}

#footer a:hover
{ color: #FFF;
  text-decoration: none;}

.search
{ color: #5D5D5D; 
  border: 1px solid #BBB; 
  width: 134px; 
  padding: 4px; 
  font: 100% arial, sans-serif;}

.form_settings
{ margin: 15px 0 0 0;}

.form_settings p
{ padding: 0 0 4px 0;}

.form_settings span
{ float: left; 
  width: 200px; 
  text-align: left;}
  
.form_settings input, .form_settings textarea
{ padding: 5px; 
  width: 299px; 
  font: 100% arial; 
  border: 1px solid #E5E5DB; 
  background: #FFF; 
  color: #47433F;}
  
.form_settings .submit
{ font: 100% arial; 
  border: 0; 
  width: 99px; 
  margin: 0 0 0 212px; 
  height: 33px;
  padding: 2px 0 3px 0;
  cursor: pointer; 
  background: #E7746F; 
  color: #FFF;}

.form_settings textarea, .form_settings select
{ font: 100% arial; 
  width: 299px;}

.form_settings select
{ width: 310px;}

.form_settings .checkbox
{ margin: 4px 0; 
  padding: 0; 
  width: 14px;
  border: 0;
  background: none;}

.separator
{ width: 100%;
  height: 0;
  border-top: 1px solid #D9D5CF;
  border-bottom: 1px solid #FFF;
  margin: 0 0 20px 0;}
  
table
{ margin: 10px 0 30px 0;}

table tr th, table tr td
{ background: #E7746F;
  color: #FFF;
  padding: 7px 4px;
  text-align: left;}
  
table tr td
{ background: #E5E5DB;
  color: #47433F;
  border-top: 1px solid #FFF;}

</style>
    <body>
        <% 
        if (session==null||null==session.getAttribute("Luser"))
           {
               response.sendRedirect("Login.do");
               return;
           }
    %> <%-- Session Code Ends --%>
    
     
    <%-- Fetch the UserName from the Session --%>
    <%! UserDataBean b; String name; String role;%>
    <% 
        b=(UserDataBean)session.getAttribute("Luser"); 
        name=b.getUsername().toString();
        role=b.getRole().toString();
    %>
    
    <div id="main">
    <div id="header">
      <div id="logo">
        <div id="logo_text">
          <!-- class="logo_colour", allows you to change the colour of the text -->
          <h1><a href="index.do">Code Review <span class="logo_colour">Knowledge Base</span></a></h1>
          <h2>Code reviews made simple…</h2>
        </div>
      </div>
      <div id="menubar">
        <ul id="menu">
          <!-- put class="selected" in the li tag for the selected page - to highlight which page you're on -->
          <li class="selected"><a href="threatProfile.do">Threat Profile</a></li>
          <li><a href="CodeUnderstanding.do">Code Understanding</a></li>
          <li><a href="Info.do">Information Gathering Sheet</a></li>
          <li><a href="another_page.html">Test plans</a></li>
          <li><a href="Logout.do">Logout</a></li>
          <li><a href="contact.html">Code review guidelines</a></li>
        </ul>
      </div>
    </div>
    <div id="content_header"></div>
    <div id="site_content">
      
	  <div id="sidebar_container">
        <div class="sidebar">
          <div class="sidebar_top"></div>
          <div class="sidebar_item">
            <!-- insert your sidebar items here -->
            <h3>Latest News</h3>
            
            <p>Scanner videos here.<br /><a href="Videos.do">Click here</a></p>
            <p>Code Review Documents.<br /><a href="Documents.do">Click here</a></p>
          </div>
          <div class="sidebar_base"></div>
        </div>
        <div class="sidebar">
          <div class="sidebar_top"></div>
          <div class="sidebar_item">
            <h3>Useful Links</h3>
            <ul>
              <li><a href="https://connect.plynt.com/">Client Connect</a></li>
              <li><a href="#">Checkmarx Server</a></li>
              <li><a href="#">Zing HR</a></li>
              <li><a href="#">Outlook</a></li>
            </ul>
          </div>
          <div class="sidebar_base"></div>
        </div>
        
      </div>
      <div id="content">
        <!-- insert the page content here -->
        <h1>What is a Security Code Review?</h1>
        <p>Source Code review discovers hidden vulnerabilities, design flaws, and verifies if key security controls are implemented. Paladion uses a combination of scanning tools and manual review to detect insecure coding practices, backdoors, injection flaws, cross site scripting flaws, insecure handling of external resources, weak cryptography, etc. </p>
        
        <p><b style="color: #E7746F">Preparation:</b> The first step of a security code review is to conduct a through study of the application followed by the creation of a comprehensive threat profile.</p>
        <p><b style="color: #E7746F">Analysis:</b> Our experts study the code layout to develop a specific code reviewer plan, and uses a hybrid approach where automated scans are verified and a custom manual review is performed.</p>
        <p><b style="color: #E7746F">Solutions:</b> Once the code is analyzed, the next step in the security code review process is to verify existing flaws and generate reports that provide solutions.</p>
        
        <ul>
            <li ><b style="color: #E7746F">Faster Results:</b>Easily detect flaws through code analysis and avoid the need to send test data to the application or software
since access to the entire code base of the application is available.</li>
          <li><b style="color: #E7746F">Thorough Analysis:</b>Evaluate the entire code layout of the application including areas that wouldn’t be analyzed in an application
security test such as entry points for different inputs, internal interfaces and integrations, data handling and
validation logic, and the use of external API’s and frameworks.</li>
          <li><b style="color: #E7746F">Overcome Testing Limitations:</b>Uncover vulnerabilities and detect attack surfaces that automated code scans miss using security code
reviews to detect weak algorithms, identify design flaws, find insecure configurations and spot insecure
coding practices.</li>
          <li><b style="color: #E7746F">Create Reports:</b>Produce security code review reports that include an executive summary that lists strengths and
weaknesses and provides detailed findings that include precise code based solutions and fixes.</li>
          <li><b style="color: #E7746F">Provide Solutions</b>Secure sensitive data storage and suggest precise solutions customized for your developers with code
level suggestions that include more exhaustive checks to find all instances of common vulnerabilities.</li>
          <li><b style="color: #E7746F">Meet Compliance Standards</b>Satisfy industry regulations and compliance standards including PCI DSS standards.</li>
        </ul>
      </div>
    </div>
    <div id="content_footer"></div>
    <div id="footer">
     
      <p>Copyright &copy; <a href="https://www.paladion.net">Paladion Networks | </a></p>
    </div>
  </div>
</body>
    </body>
</html>
