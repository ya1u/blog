let index={
	init: function() {
		$("#btn-save").on("click",()=>{
			this.save();
		});
	},
	
	save: function() {
		// alert('user의 save함수 호출됨');
		let data = {
			username: $('#username').val(),
			password: $('#password').val(),
			email: $('#email').val()
		}
		// console.log(data); 자바스크립트 오브젝스
		// console.log(JSON.stringfy(data)) json 오브젝트
		// ajax 호출 시 디폴트가 비동기 호출
		$.ajax({
			// 회원가입 수행 요청
			// (100초과 가정한다면 도중에 done이나 fail 실행)
			type: "POST",
			url: "/blog/api/user",
			data: JSON.stringify(data), //http body 데이터
			contentType: "application/json; charset = utf-8",
			dataType: "json" // 응답의 결과가 문자열이 아닌 json으로 반환
		}).done(function(resp){
			alert("회원가입이 완료되었습니다.");
			location.href = "/blog";
			// 응답이 정상
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
		// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청!
	}
}
index.init();