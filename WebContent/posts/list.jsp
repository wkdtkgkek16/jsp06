<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h1>게시글</h1>
<div class="row justify-content-center m-5">
	<div>
		게시글수 : <span id="total"></span>
	</div>
	<div class="col">
		<div id="posts"></div>
		<ul class="pagination justify-content-center" id="pagination"></ul>
			<script id="temp" type="text/x-handlebars-template">
				<table class="table table-striped">
				{{#each .}}
					<tr>
						<td>{{id}}</td>
						<td>
							<a href="/posts/read?id={{id}}"><div class="ellipsis">{{title}}</div></a>
						</td>
						<td>{{writer}}</td>
						<td><div class="ellipsis">{{fmtDate}}</div></td>
					</tr>
				{{/each}}
				</table>
			</script>
		</div>
	</div>
<script src="/js/pagination.js"></script>
<script>
	let page=1;
	let size=10;
	getList(page);
	getPagination();
	getTotal("/posts/total");
	
	function getList(page){
		$.ajax({
			type:"get",
			url:"/posts.json",
			dataType:"json",
			data: {page:page, size:size},
			success:function(data){
				let temp=Handlebars.compile($("#temp").html());
				$("#posts").html(temp(data));
			}
		});
	}

</script>