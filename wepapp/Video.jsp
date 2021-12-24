<%@ page language="java" contentType="text/html; charset=UTF8"
    pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/style.css">
        <title>Trang chủ</title>
    </head>
    <body>
        <script src="js/script.js"></script>
        <div class="menu" id="menu">
                <a onclick="closeMenu()">
                    <img src="icon/menu.png" alt="menu-button">
                </a>
                <c:forEach items="${category}" var="cate">
                	<a href="Category?categoryId=${cate.id}">${cate.category}</a>
                </c:forEach>
        </div>
        <header class="header">
            <a>
                <img src="icon/menu.png" alt="menu-button" onclick="openMenu()">
            </a>
            <a href="Home">
                XemVideo
            </a>
            <form class="search-bar" method="get" action="SearchProcess" autocomplete="off">
                <input class="search-input" name="search-input" type="text" placeholder="Tìm kiếm..."/>
                <button type="submit" class="search-button">
                    <img src="icon/magnify.svg">
                </button>
            </form>
            <div class="menu-icons">

            </div>
        </header>
        
        <div class="content-section">
        	<div class="video-player">
        		<!-- <video class="video-stream" style="width=100%; height=720px;" src="/xemphim/VideoProcess?id=${id}"></video> -->
        		<!-- <embed src="/xemphim/VideoProcess?id=${id}" width="100%" height="720"></embed> -->
        		<video width="100%" height="720" controls autoplay>
        			<source src="/xemphim/VideoProcess?id=${id}" type="video/mp4"></source>
        		</video>
        	</div>
        	<!-- <iframe src="/xemphim/VideoProcess?id=${id}" width="100%" height="720" style="border:none;"></iframe> -->
        
	        <div class="videos">
	            <section class="video-section">
	            	<c:forEach items="${list}" var="o">
	            		<article class=video-container>
	            			<a href="VideoLoad?id=${o.id}" class="thumbnail">
	            				<img class="thumbnail-image" src="thumbnail/${o.id}.jpg">
	            			</a>
	            			<div class="video-bottom-section">
	            				<div class="video-details">
	            					<a href="VideoLoad?id=${o.id}" class="video-title">${o.title}</a>
	            					<a href="VideoLoad?id=${o.id}" class="video-channel-name">${o.category}</a>
	            				</div>
	            			</div>
	            		</article>
	            	</c:forEach>
	            </section>
	        </div>
        </div>
    </body>
</html>