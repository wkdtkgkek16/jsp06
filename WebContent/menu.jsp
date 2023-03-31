<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
	
<nav class="navbar navbar-expand-lg bg-primary py-3">
	<div class="container-fluid">
		<a class="navbar-brand" href="/"><img src="/image/home2.png" alt="Bootstrap" width="100px" height="50px"></a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item">
					<a class="nav-link active text-light" aria-current="page" href="/posts">게시글</a>
				</li>
				<li class="nav-item">
					<c:if test="${uid == null}">
						<a class="nav-link active text-light" aria-current="page" href="/login">로그인</a>
					</c:if>
					<c:if test="${uid != null}">
						<a class="nav-link active text-light" aria-current="page" href="/logout">로그아웃</a>
					</c:if>
					<li class="nav-item">
					<c:if test="${uname != null}">
						<a class="nav-link active" aria-current="page" href="/mypage">${uname}(${uid})</a>
					</c:if>
					</li>
				</li>
			</ul>
		</div>
	</div>
</nav>