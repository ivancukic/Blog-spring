<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- VIEWS -->
<%
	Integer viewCounter = (Integer)application.getAttribute("Page visited");

	if(viewCounter==null || viewCounter==0){
		
		viewCounter = 1;
	}
	else{
		
		viewCounter = viewCounter + 1;
	}

	application.setAttribute("Page visited", viewCounter);
%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Bootstrap Blog - B4 Template by Bootstrap Temple</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="robots" content="all,follow">
    <!-- Bootstrap CSS-->
    <link rel="stylesheet" href="vendor/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome CSS-->
    <link rel="stylesheet" href="vendor/font-awesome/css/font-awesome.min.css">
    <!-- Custom icon font-->
    <link rel="stylesheet" href="css/fontastic.css">
    <!-- Google fonts - Open Sans-->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
    <!-- Fancybox-->
    <link rel="stylesheet" href="vendor/@fancyapps/fancybox/jquery.fancybox.min.css">
    <!-- theme stylesheet-->
    <link rel="stylesheet" href="css/style.default.css" id="theme-stylesheet">
    <!-- Custom stylesheet - for your changes-->
    <link rel="stylesheet" href="css/custom.css">
    <!-- Favicon-->
    <link rel="shortcut icon" href="favicon.png">
    <!-- Tweaks for older IEs--><!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
  </head>
  <body>
    <header class="header">
      <!-- Main Navbar-->
      <nav class="navbar navbar-expand-lg">
        <div class="search-area">
          <div class="search-area-inner d-flex align-items-center justify-content-center">
            <div class="close-btn"><i class="icon-close"></i></div>
            <div class="row d-flex justify-content-center">
              <div class="col-md-8">
                <form action="blog-search.html">
                  <div class="form-group">
                    <input type="search" name="search" id="search" placeholder="What are you looking for?">
                    <button type="submit" class="submit"><i class="icon-search-1"></i></button>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
        <div class="container">
          <!-- Navbar Brand -->
          <div class="navbar-header d-flex align-items-center justify-content-between">
            <!-- Navbar Brand --><a href="index.html" class="navbar-brand">Bootstrap Blog</a>
            <!-- Toggle Button-->
            <button type="button" data-toggle="collapse" data-target="#navbarcollapse" aria-controls="navbarcollapse" aria-expanded="false" aria-label="Toggle navigation" class="navbar-toggler"><span></span><span></span><span></span></button>
          </div>
          <!-- Navbar Menu -->
          <div id="navbarcollapse" class="collapse navbar-collapse">
            <ul class="navbar-nav ml-auto">
              <li class="nav-item"><a href="index-page" class="nav-link">Home</a>
              </li>
              <li class="nav-item"><a href="blog-page" class="nav-link active">Blog</a>
              </li>
              <li class="nav-item"><a href="contact-page" class="nav-link">Contact</a>
              </li>
            </ul>
            <div class="navbar-text"><a href="#" class="search-btn"><i class="icon-search-1"></i></a></div>
          </div>
        </div>
      </nav>
    </header>
    <div class="container">
      <div class="row">
        <!-- Latest Posts -->
        <main class="post blog-post col-lg-8"> 
          <div class="container">
            <div class="post-single">
              <div class="post-thumbnail"><img src="img/blog-post-3.jpeg" alt="..." class="img-fluid"></div>
              <div class="post-details">
                <div class="post-meta d-flex justify-content-between">
                  <div class="category"><a href="blog-category.html">${post.category.name }</a></div>
                </div>
                <h1>${post.title }<a href="#"><i class="fa fa-bookmark-o"></i></a></h1>
                <div class="post-footer d-flex align-items-center flex-column flex-sm-row"><a href="author-page?author=${post.author }" class="author d-flex align-items-center flex-wrap">
                    <div class="avatar"><img src="dist/${post.authorImage }" alt="..." class="img-fluid"></div>
                    <div class="title"><span>${post.author }</span></div></a>             
                  <div class="d-flex align-items-center flex-wrap">       
                    <div class="date"><i class="icon-clock"></i>${post.date }</div>
                    <div class="views"><i class="icon-eye"></i> <%= viewCounter %></div>
                    <div class="comments meta-last"><a href="#post-comments"><i class="icon-comment"></i>${post.commentCounter }</a></div>
                  </div>
                </div>
                <div class="post-body">
                  <p class="lead">${post.text }</p>
                  <p>${post.text }</p>
                  <p> <img src="${post.image }" alt="..." class="img-fluid"></p>
                  <h3>${post.title }</h3>
                  <p>${post.text }</p>
                  <blockquote class="blockquote">
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip.</p>
                    <footer class="blockquote-footer">Someone famous in
                      <cite title="Source Title">Source Title</cite>
                    </footer>
                  </blockquote>
                  <p>${post.text }</p>
                </div>
                <div class="post-tags">
                  <c:forEach var="tag" items="${tagList }">
                	<a href="post-page-filter?id=${tag.id }&type=tag" class="tag">#${tag.name }</a>
                  </c:forEach>
                </div>  <!--  --> 
                <div class="posts-nav d-flex justify-content-between align-items-stretch flex-column flex-md-row">
                	<a href="previous-next-page?id=${previousPost}" class="prev-post text-left d-flex align-items-center">
                    <div class="icon prev"><i class="fa fa-angle-left"></i></div>
                    <div class="text"><strong class="text-primary">Previous Post </strong>
                      <h6>${previousName }</h6>
                    </div></a>
                    <a href="previous-next-page?id=${nextPost}" class="next-post text-right d-flex align-items-center justify-content-end">
                    <div class="text"><strong class="text-primary">Next Post </strong>
                     <h6>${nextName }</h6>
                    </div>
                    <div class="icon next"><i class="fa fa-angle-right">   </i></div></a>
                </div>
                <div class="post-comments" id="post-comments">
                  <header>
                    <h3 class="h6">Post Comments<span class="no-of-comments">(3)</span></h3>
                  </header>
				  <c:forEach var="i" begin="0" end="${commentsList.size() }">
                  	<c:if test="${commentsList[i].isApproved != 'false' }">
	                  <div class="comment">
	                    <div class="comment-header d-flex justify-content-between">
	                      <div class="user d-flex align-items-center">
	                        <div class="image"><img src="img/user.svg" alt="..." class="img-fluid rounded-circle"></div>
	                        <div class="title"><strong>${commentsList[i].name }</strong><span class="date">${commentsList[i].date }</span></div>
	                      </div>
	                    </div>
	                    <div class="comment-body">
	                      <p>${commentsList[i].text }</p>
	                    </div>
	                  </div>
	                 </c:if>  
	               </c:forEach>  
                </div>
                <div class="add-comment">
                  <header>
                    <h3 class="h6">Leave a reply</h3>
                  </header>
                  <form:form action="comments-save?postId=${postId.id }" modelAttribute="comments" class="commenting-form" method="post">
                    <div class="row">
                      <div class="form-group col-md-6">
                        <form:input path="name" type="text" name="username" id="username" placeholder="Name" class="form-control"/>
                      </div>
                      <div class="form-group col-md-6">
                        <form:input path="email" type="email" name="username" id="useremail" placeholder="Email Address (will not be published)" class="form-control"/>
                      </div>
                      <div class="form-group col-md-6"> <!-- DATUM -->
                        <form:hidden path="date" class="form-control"/>
                      </div>
                      <div class="form-group col-md-12">
                        <form:textarea path="text" name="usercomment" id="usercomment" placeholder="Type your comment" class="form-control"></form:textarea>
                      </div>
                      
                      <div class="form-group col-md-12">
                      	<form:button type="submit"  class="btn btn-secondary" path="idPost" value="${postId.id }">Submit Comment</form:button>
                      </div> <!-- name="idPost" value="${postId.id }"  -->
                    </div>
                  </form:form>
                </div>
              </div>
            </div>
          </div>
        </main>
        <aside class="col-lg-4">
          <!-- Widget [Search Bar Widget]-->
          <div class="widget search">
            <header>
              <h3 class="h6">Search the blog</h3>
            </header>
            <form action="blog-search.html" class="search-form">
              <div class="form-group">
                <input type="search" placeholder="What are you looking for?">
                <button type="submit" class="submit"><i class="icon-search"></i></button>
              </div>
            </form>
          </div>
          <!-- Widget [Latest Posts Widget]        -->
          <div class="widget latest-posts">
            <header>
              <h3 class="h6">Latest Posts</h3>
            </header>
            <div class="blog-posts">
            
             <c:forEach items="${reversePostList }" var="reverse" begin="0" end="2">
              <a href="blog-post.html">
                <div class="item d-flex align-items-center">
                  <div class="image"><img src="${reverse.image }" alt="..." class="img-fluid"></div>
                  <div class="title"><strong>${reverse.title }</strong>
                    <div class="d-flex align-items-center">
                      <div class="views"><i class="icon-eye"></i><%= viewCounter %></div>
                      <div class="comments"><i class="icon-comment"></i>${reverse.commentCounter }</div>
                    </div>
                  </div>
                </div>
               </a>
               </c:forEach>

              </div>
          </div>
          <!-- Widget [Categories Widget]-->
          <div class="widget categories">
            <header>
              <h3 class="h6">Categories</h3>
            </header>
            	<c:forEach var="category" items="${categoryList }">
            		<div class="item d-flex justify-content-between"><a href="post-page-filter?id=${category.id }&type=category">${category.name }</a><span>${category.counter }</span></div>
            	</c:forEach>	
          </div>
          <!-- Widget [Tags Cloud Widget]-->
          <div class="widget tags">       
            <header>
              <h3 class="h6">Tags</h3>
            </header>
            <ul class="list-inline">
            	<c:forEach var="tag" items="${tagList }">
              		<li class="list-inline-item"><a href="post-page-filter?id=${tag.id }&type=tag" class="tag">#${tag.name }</a></li>
              	</c:forEach>	
            </ul>
          </div>
        </aside>
      </div>
    </div>
    <!-- Page Footer-->
    <footer class="main-footer">
      <div class="container">
        <div class="row">
          <div class="col-md-4">
            <div class="logo">
              <h6 class="text-white">Bootstrap Blog</h6>
            </div>
            <div class="contact-details">
              <p>53 Broadway, Broklyn, NY 11249</p>
              <p>Phone: (020) 123 456 789</p>
              <p>Email: <a href="mailto:info@company.com">Info@Company.com</a></p>
              <ul class="social-menu">
                <li class="list-inline-item"><a href="#"><i class="fa fa-facebook"></i></a></li>
                <li class="list-inline-item"><a href="#"><i class="fa fa-twitter"></i></a></li>
                <li class="list-inline-item"><a href="#"><i class="fa fa-instagram"></i></a></li>
                <li class="list-inline-item"><a href="#"><i class="fa fa-behance"></i></a></li>
                <li class="list-inline-item"><a href="#"><i class="fa fa-pinterest"></i></a></li>
              </ul>
            </div>
          </div>
          <div class="col-md-4">
            <div class="menus d-flex">
              <ul class="list-unstyled">
                <li> <a href="index-page">Home</a></li>
                <li> <a href="blog-page">Blog</a></li>
                <li> <a href="contact-page">Contact</a></li>
                <li> <a href="#">Login</a></li>
              </ul>
              <ul class="list-unstyled">
                <li> <a href="blog-category.html">Growth</a></li>
                <li> <a href="blog-category.html">Local</a></li>
                <li> <a href="blog-category.html">Sales</a></li>
                <li> <a href="blog-category.html">Tips</a></li>
              </ul>
            </div>
          </div>
          <div class="col-md-4">
            <div class="latest-posts"><a href="blog-post.html">
                <div class="post d-flex align-items-center">
                  <div class="image"><img src="img/small-thumbnail-1.jpg" alt="..." class="img-fluid"></div>
                  <div class="title"><strong>Hotels for all budgets</strong><span class="date last-meta">October 26, 2016</span></div>
                </div></a><a href="blog-post.html">
                <div class="post d-flex align-items-center">
                  <div class="image"><img src="img/small-thumbnail-2.jpg" alt="..." class="img-fluid"></div>
                  <div class="title"><strong>Great street atrs in London</strong><span class="date last-meta">October 26, 2016</span></div>
                </div></a><a href="blog-post.html">
                <div class="post d-flex align-items-center">
                  <div class="image"><img src="img/small-thumbnail-3.jpg" alt="..." class="img-fluid"></div>
                  <div class="title"><strong>Best coffee shops in Sydney</strong><span class="date last-meta">October 26, 2016</span></div>
                </div></a></div>
          </div>
        </div>
      </div>
      <div class="copyrights">
        <div class="container">
          <div class="row">
            <div class="col-md-6">
              <p>&copy; 2017. All rights reserved. Your great site.</p>
            </div>
            <div class="col-md-6 text-right">
              <p>Template By <a href="https://bootstrapious.com/p/bootstrap-carousel" class="text-white">Bootstrapious</a>
                <!-- Please do not remove the backlink to Bootstrap Temple unless you purchase an attribution-free license @ Bootstrap Temple or support us at http://bootstrapious.com/donate. It is part of the license conditions. Thanks for understanding :)                         -->
              </p>
            </div>
          </div>
        </div>
      </div>
    </footer>
    <!-- JavaScript files-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/popper.js/umd/popper.min.js"> </script>
    <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
    <script src="vendor/jquery.cookie/jquery.cookie.js"> </script>
    <script src="vendor/@fancyapps/fancybox/jquery.fancybox.min.js"></script>
    <script src="js/front.js"></script>
  </body>
</html>