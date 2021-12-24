<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
                <input class="search-input" name="search-input" type="text" placeholder="Tìm kiếm..." value="${keyword}"/>
                <button type="submit" class="search-button">
                    <img src="icon/magnify.svg">
                </button>
            </form>
            <div class="menu-icons">

            </div>
        </header>
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
    </body>
</html>
