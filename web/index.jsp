<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="Dashboard">
  <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

  <title>Movie-ARQL</title>

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
  <section id="main-content">


    <section class="wrapper">

      <div class="row">
        <div class="main-chart">
            <div class="col-md-4 col-sm-4 mb">
                <p><b>Single queries:</b></p><br>
                <p>Choose an actor and see his filmography</p>



                <div class="dropdown">
                    <!-- Link o pulsante per l'attivazione del dropdown -->
                    <a data-toggle="dropdown" href="#" class="btn btn-default">Actors</a>
                    <!-- Menu dropdown -->
                    <ul class="dropdown-menu scrollable-menu">
                        <% out.println(sparql.createListActors());%>
                    </ul>
                </div>

                <br>
                <p>Choose a director and see his filmography</p>
                <div class="dropdown">
                    <!-- Link o pulsante per l'attivazione del dropdown -->
                    <a data-toggle="dropdown" href="#" class="btn btn-default">Directors</a>
                    <!-- Menu dropdown -->
                    <ul class="dropdown-menu scrollable-menu">
                        <% out.println(sparql.createListDirectors());%>
                    </ul>
                </div>
                <br>
                <p>Choose a genre and see all movies</p>
                <div class="dropdown">
                    <!-- Link o pulsante per l'attivazione del dropdown -->
                    <a data-toggle="dropdown" href="#" class="btn btn-default">Genres</a>
                    <!-- Menu dropdown -->
                    <ul class="dropdown-menu scrollable-menu">
                        <% out.println(sparql.createListGenre());%>
                    </ul>
                </div>
            </div><!-- /col-md-4 -->

            <div class="col-md-4 mb">
                <p><b>Single queries:</b></p><br>
                <p>Choose an year and see all the movies produced</p>
                <div class="dropdown">
                    <!-- Link o pulsante per l'attivazione del dropdown -->
                    <a data-toggle="dropdown" href="#" class="btn btn-default">Year</a>
                    <!-- Menu dropdown -->
                    <ul class="dropdown-menu scrollable-menu">
                        <% out.println(sparql.createListYear());%>
                    </ul>
                </div>
                <br>
                <p>Choose a language to see all the movies with this spoken language</p>
                <div class="dropdown">
                    <!-- Link o pulsante per l'attivazione del dropdown -->
                    <a data-toggle="dropdown" href="#" class="btn btn-default">Language</a>
                    <!-- Menu dropdown -->
                    <ul class="dropdown-menu scrollable-menu">
                        <% out.println(sparql.createListLanguage());%>
                    </ul>
                </div>
                <br>

                <p>Choose a country to see all the movies with produced there</p>
                <div class="dropdown">
                    <!-- Link o pulsante per l'attivazione del dropdown -->
                    <a data-toggle="dropdown" href="#" class="btn btn-default">Country</a>
                    <!-- Menu dropdown -->
                    <ul class="dropdown-menu scrollable-menu">
                        <% out.println(sparql.createListCountry());%>
                    </ul>
                </div>


          </div><!-- /row mt -->





        </div><!-- /row -->


        </div><!-- /col-lg-9 END SECTION MIDDLE -->



        </div><!-- /col-lg-3 -->
      </div><! --/row -->
        <div class="row">
            <div class="main-chart">
        <p><b>Custom query</b></p>

            <div class="col-md-4 col-sm-4 mb">
                <FORM  ACTION="result.jsp" METHOD="POST">
                <textarea name="textquery" rows=10 cols=50>SPARQL query</textarea><br><br>
                <input class="btn btn-primary" type="submit" value="Run query"/>
                </FORM>
            </div><!-- /col-md-4-->
            <div class="col-md-4 mb">
                <p><b>PREFIXES</b></p>
                <p>PREFIX movie: &lthttp://localhost:3030/movies/&gt<br>
                    PREFIX vcard: &lthttp://www.w3.org/2001/vcard-rdf/3.0#&gt<br>
                    PREFIX mo: &lthttp://purl.org/ontology/mo/&gt<br>
                    PREFIX xsd: &lthttp://www.w3.org/2001/XMLSchema#&gt<br>
                    PREFIX rdfs: &lthttp://www.w3.org/2000/01/rdf-schema#&gt<br>
                    PREFIX owl: &lthttp://www.w3.org/2002/07/owl#&gt<br></p>
                </div>
</div>


        </div><!-- /col-md-4 -->


        <div class="row">
            <div class="main-chart">
                <p><b>Graph query DEMO</b></p>

                <div class="col-md-4 col-sm-4 mb">
                    <p>PREFIX movie: &lthttp://localhost:3030/movies/&gt
                        PREFIX vcard: &lthttp://www.w3.org/2001/vcard-rdf/3.0#&gt
                        PREFIX mo: &lthttp://purl.org/ontology/mo/&gt
                        prefix rdfs: &lthttp://www.w3.org/2000/01/rdf-schema#&gt
                        prefix owl: &lthttp://www.w3.org/2002/07/owl#&gt</p><br>
                       <p> SELECT ?actor ?name ?title WHERE { <br>
                        ?x movie:playRoleIn ?movie .<br>
                        ?movie movie:Title ?title.<br>
                        ?x vcard:FN ?actor .<br>
                        ?director movie:directedBy ?movie .<br>
                        ?director vcard:FN ?name .<br>
                        }</p>
                </div><!-- /col-md-4-->
                <div class="col-md-4 mb">
                    <FORM  ACTION="resultGraphQuery.jsp" METHOD="POST">
                        <input class="btn btn-primary" type="submit" value="Run query and show graph"/>
                    </FORM>
                </div>
            </div>
            <jsp:useBean id="graphexec" scope="session" class="mainpkg.GraphQueryExecutor"/>
            <jsp:setProperty name="graphexec" property="*"/>
            <% graphexec.runGraphQuery();%>
        </div><!-- /col-md-4 -->

    </section>
  </section>

  <!--main content end-->
  <!--footer start-->
  <footer class="site-footer">
    <div class="text-center">
      2016 - Simone Tritini- Semantic Technologies - unibz
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
