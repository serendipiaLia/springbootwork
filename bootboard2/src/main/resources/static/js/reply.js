/* 

댓글목록 구현 : 댓글 등록 / 댓글 삭제 / 댓글 수정

 */
let replyObject = {
	// 댓글 등록
	init: function(){
		$("#btn-save-reply").click(() => {
			this.insertReply(); //this는 초기화 > this(클릭한 대상 #btn-save-reply)
		});
	}, // 객체 json형식이므로 {}후 , 필수
	insertReply: function(){
	//	alert("댓글 등록 요청됨"); // #btn-save-reply클릭 시, alert작동
		
		let boardId = $("#boardId").val();
		let replyContent = $("#replyContent").val(); // jquery로 메서드 작성 > ajax활용
		// document.getElementById(replyContent).value > json형식
			if(replyContent == ""){
				alert("댓글을 입력해 주세요");
				$("#replyContent").focus();
				return false;
			}
		
		let reply = {
			content: replyContent //replyContent - controller로 넘겨주는 데이터
		}
		console.log(reply);
		
		let header = $("meta[name='_csrf_header']").attr('content');
		let token = $("meta[name='_csrf']").attr('content');
		
		$.ajax({
			type: "POST",
			url: "/reply/" + boardId,
			beforeSend: function(xhr){
      		xhr.setRequestHeader(header, token);
   			},
			data: JSON.stringify(reply), // 자바스크립트 객체(reply)를 문자화(String)하여 json형식으로 바꿔줌
			contentType: "application/json; charset=utf-8" 
		}).done(function(response){
			console.log(response);
			location.href = "/board/" + boardId;
		}).fail(function(error){
			alert("에러 발생 : " + error);
		});
	},
	// 댓글 삭제
	deleteReply: function(boardId, replyId){
		alert("댓글 삭제 요청")
		
		let header = $("meta[name='_csrf_header']").attr('content');
		let token = $("meta[name='_csrf']").attr('content');
		
		$.ajax({
			type: "DELETE",
			beforeSend: function(xhr){
      		xhr.setRequestHeader(header, token);
      		},
			url: "/reply/" + replyId
		//  data: 삭제하기 때문에 data는 필요없음
		}).done(function(response){
			console.log(response);
			
			location.href = "/board/" + boardId;
		}).fail(function(error){
			alert("에러 발생 : " + error);
		});
		
	}
	
	
}

replyObject.init(); //init 함수 호출
 
 