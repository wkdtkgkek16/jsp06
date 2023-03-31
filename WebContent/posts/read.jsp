<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<div class="row justify-content-center m-5">
	<div class="col-md-10">
		<h1>게시글정보</h1>
		<div class="card m-5">
			<div class="card-body">
				<p>[${post.id}] ${post.title}</p>
				<hr/>
				<p>${post.body}</p>
			</div>
			<div class="card-footer">
				Posted on ${post.fmtDate} by ${post.writer}
			</div>
		</div>
		<c:if test="${uid != null}">
			<h1>댓글등록</h1>
			<div class="card my-5">
				<div class="card-body">
					<form name="frm">
						<textarea rows="3" class="form-control" name="body" placeholder="내용을 입력하세요"></textarea>
						<button class="btn btn-primary btn-sm mt-2">등록</button>
					</form>
				</div>
			</div>
		</c:if>
		
		<h1>댓글목록</h1>
		<div>
			댓글수 : <span id="total"></span>
		</div>
		<div id="comments"></div>
		<script id="temp" type="text/x-handlebars-template">
			{{#each .}}
				<div class="card">
					<div class="card-body">
						<p>{{body}}</p>
						<div class="text-end" style="{{style writer}}">
						<button cid="{{id}}" class="btn btn-danger">삭제</button>
						</div>
					</div>
					<div class="card-footer text-muted">
						Posted on {{date}} by {{writer}}
					</div>
				</div>
			{{/each}}
		</script>
		<script>
			Handlebars.registerHelper("style", function(writer){
				if(writer != "${uid}") return "display:none";
			});
		</script>
	</div>
	<ul class="pagination justify-content-center" id="pagination"></ul>
</div>
<script src="/js/pagination.js"></script>
<script>
	let page=1;
	let postid="${post.id}";
	let size=3;
	getList(page);
	getTotal("/comments/total", {postid:postid});	//total함수구하기
	getPagination();
	
	
	//댓글삭제
	$("#comments").on("click", ".btn-danger", function(){
		let id=$(this).attr("cid");
		$("#confirm").modal("show");
		$("#confirm .modal-body").html(`${id}번 댓글을 삭제하실래요?`);
		$("#confirm").on("click", "#yes", function(){
			$.ajax({
				type:"post",
				url:"/comments/delete",
				data:{id:id},
				success:function(){
					getList(1);
					getTotal("/comments/total", {postid:postid});
				}
			})
		})
	})
	//댓글등록
	$(frm).on("submit",function(e){
		e.preventDefault();
		let body=$(frm.body).val();
		if(body==""){
			$("#alert").modal("show");
			$("#alert .modal-body").html("내용을 입력하세요");
		}else{
			$.ajax({
				type:"post",
				url:"/comments/insert",
				data:{postid:postid, body:body, writer:"${uid}"},
				success:function(){
					getList(1);
					getTotal("/comments/total", {postid:postid});
					$(frm.body).val("");
				}				
			})
		}
	})
	
	
	function getList(page){
		$.ajax({
			type:"get",
			url:"/comments.json",
			data:{postid:postid,page:page,size:size},
			dataType:"json",
			success:function(data){
				let temp=Handlebars.compile($("#temp").html());
				$("#comments").html(temp(data));
			}
		})
	}
	


</script>