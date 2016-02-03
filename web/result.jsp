<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Dashboard">
    <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

    <title>Query result</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.css" rel="stylesheet">
    <!--external css-->
    <link href="font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="css/zabuto_calendar.css">
    <link rel="stylesheet" type="text/css" href="js/gritter/css/jquery.gritter.css" />
    <link rel="stylesheet" type="text/css" href="lineicons/style.css">

    <!-- Custom styles for this template -->
    <link href="css/style.css" rel="stylesheet">
    <link href="css/style-responsive.css" rel="stylesheet">

    <script src="js/chart-master/Chart.js"></script>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>

    <![endif]-->
</head>

<jsp:useBean id="sparql" scope="session" class="mainpkg.SPARQLInterpreter"/>
<jsp:setProperty name="sparql" property="*" />
<jsp:useBean id="executor" scope="session" class="mainpkg.QueryExecutor"/>
<jsp:setProperty name="executor" property="*" />

<body>

<section id="container" >

    <header class="header black-bg">
        <!--logo start-->
        <a href="index.jsp" class="logo"><b>Movie-ARQL, Semantic Technologies Project</b></a>
        <!--logo end-->
        <div class="nav notify-row" id="top_menu">
            <!--  notification start -->
            <ul class="nav top-menu">
            </ul>
        </div>
        <!--
        <div class="top-menu">
            <ul class="nav pull-right top-menu">
                <li><button class="btn btn-success logout" disabled>Server OK</button></li>
            </ul>
        </div>-->

    </header>
    <!--header end-->

    <!-- **********************************************************************************************************************************************************
    MAIN CONTENT
    *********************************************************************************************************************************************************** -->
    <!--main content start-->



    <section class="wrapper">

            <div class="row">
                <div class="main-chart">

                    <div class="row mtbox">
                        <div class="content-panel">
                            <% if(request.getParameter("attribute")!= null) {%>

                                <h4>Result table <i class="fa fa-angle-right"></i> movie's <%out.print(request.getParameter("attribute"));%>: <b><%out.print(request.getParameter("value"));%></b> </h4>
                                <%out.println(executor.singleQuery(request.getParameter("value"),request.getParameter("attribute")));%>

                            <%} else {%>
                            <h4>Result table</h4>
                        <%
                            out.println(executor.singleQuery(request.getParameter("textquery"),"customquery"));
                            }
                        %>
                            </div>
                    </div>

                </div><!-- /row mt -->





            </div><!-- /col-lg-9 END SECTION MIDDLE -->



            </div><!-- /col-lg-3 -->
            </div><! --/row -->
        </section>


    <!--main content end-->
    <!--footer start-->
    <footer class="site-footer">
        <div class="text-center">
            2016 - Simone Tritini - Semantic Technologies - unibz
        </div>
    </footer>
    <!--footer end-->
</section>



<!-- js placed at the end of the document so the pages load faster -->
<script src="js/jquery.js"></script>
<script src="js/jquery-1.8.3.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script class="include" type="text/javascript" src="js/jquery.dcjqaccordion.2.7.js"></script>
<script src="js/jquery.scrollTo.min.js"></script>
<script src="js/jquery.nicescroll.js" type="text/javascript"></script>
<script src="js/jquery.sparkline.js"></script>


<!--common script for all pages-->
<script src="js/common-scripts.js"></script>

<script type="text/javascript" src="js/gritter/js/jquery.gritter.js"></script>
<script type="text/javascript" src="js/gritter-conf.js"></script>

<!--script for this page-->
<script src="js/sparkline-chart.js"></script>
<script src="js/zabuto_calendar.js"></script>

</body>
</html>
