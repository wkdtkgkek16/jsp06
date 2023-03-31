<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="row justify-content-center m-5">
	<div class="col-md-5">
		<div class="card">
			<div class="card-title">
				<h1>로그인</h1>
				<div class="card-body">
					<form name="frm">
						<input class="form-control my-1"
						placeholder="아이디" name="uid"
						>
						<input class="form-control my-1"
						placeholder="비밀번호" name="upass"
						type="password"
						>
						<button class="btn btn-primary w-100">로그인</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
	$(frm).on("submit", function(e){
		e.preventDefault();
		let uid=$(frm.uid).val();
		let upass=$(frm.upass).val();
		if(uid==""){
			$("#alert").modal("show");
			$("#alert .modal-body").html("아이디를 입력하세요");
		}else if(upass==""){
			$("#alert").modal("show");
			$("#alert .modal-body").html("비밀번호를 입력하세요");
		}else{
			$.ajax({
				type:"post",
				url:"/login",
				data:{uid:uid, upass:upass},
				success:function(data){
					if(data==0){
						$("#alert").modal("show");
						$("#alert .modal-body").html("아이디가 존재하지 않습니다");
					}else if(data==2){
						$("#alert").modal("show");
						$("#alert .modal-body").html("비밀번호가 일치하지 않습니다");
					}else{
						location.href="/posts";
					}
				}
			})
		}
	})
</script>