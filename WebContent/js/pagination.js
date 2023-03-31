
	//getTotalPages();	//total함수구하기
	function getTotal(url, data){
		$.ajax({
			type:"get",
			url: url,
			data:data,
			success:function(data){
				$("#total").html(data);
				$("#pagination").twbsPagination("changeTotalPages", Math.ceil(data/size),1);
			}
		});
	}
	
		function getPagination(){
		   //pagination 출력
		   $('#pagination').twbsPagination({
		       startPage:1,
		       totalPages:1,
		       visiblePages: 5,
		       first: '<span sris-hidden="true">«</span>',
		       last: '<span sris-hidden="true">»</span>',
		       prev: "&lt;",
		       next: "&gt;",
		       onPageClick: function(event, page) {
		           getList(page);
		       }
		    });
		}