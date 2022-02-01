<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/user.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-1.12.4.js"></script>

</head>

<body>
	<div id="wrap">

		<!-- header + nav -->
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>

		<div id="container" class="clearfix">
			<!-- aside(user) -->
			<c:import url="/WEB-INF/views/include/aside_user.jsp"></c:import>

			<div id="content">
			
				<div id="content-head">
					<h3>회원가입</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>회원</li>
							<li class="last">회원가입</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->
	
				<div id="user">
					<div id="joinForm">
						<form id="join-form" action="${pageContext.request.contextPath}/user/join" method="get">
							
							<!-- 아이디 -->
							<div class="form-group">
								<label class="form-text" for="input-uid">아이디</label> 
								<input type="text" id="input-uid" name="id" value="" placeholder="아이디를 입력하세요">
								<button type="button" id="dup-check">중복체크</button>
								<p id="dup-msg"></p>
							</div>
	
							<!-- 비밀번호 -->
							<div class="form-group">
								<label class="form-text" for="input-pass">패스워드</label> 
								<input type="text" id="input-pass" name="password" value="" placeholder="비밀번호를 입력하세요"	>
							</div>
	
							<!-- 이름 -->
							<div class="form-group">
								<label class="form-text" for="input-name">이름</label> 
								<input type="text" id="input-name" name="name" value="" placeholder="이름을 입력하세요">
							</div>
	
							<!-- 성별 -->
							<div class="form-group">
								<span class="form-text">성별</span> 
								
								<label for="rdo-male">남</label> 
								<input type="radio" id="rdo-male" name="gender" value="male" > 
								
								<label for="rdo-female">여</label> 
								<input type="radio" id="rdo-female" name="gender" value="female" > 
	
							</div>
	
							<!-- 약관동의 -->
							<div class="form-group">
								<span class="form-text">약관동의</span> 
								
								<input type="checkbox" id="chk-agree" value="" name="">
								<label for="chk-agree">서비스 약관에 동의합니다.</label> 
							</div>
							
							<!-- 버튼영역 -->
							<div class="button-area">
								<button type="button" id="btn-submit">회원가입</button>
							</div>
							
						</form>
					</div>
					<!-- //joinForm -->
				</div>
				<!-- //user -->
			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->
		
		<!-- footer -->
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>

	</div>
	<!-- //wrap -->

</body>

<script type="text/javascript">
	var dup;
	$("#dup-check").on("click",function(){
		var id = $("#input-uid").val();
		//console.log(inputUid);
		
		$.ajax({
			url: "${pageContext.request.contextPath}/user/dupCheck",
			type : "post",
			data : {id:id}, // 입력한 id를 보내 db에서 검색하고 같은 id가 있으면 true, 없으면 false 반환
			
			dataType: "json",
			success : function(result){	// json > js
				/*성공시처리해야될코드작성*/
				//console.log(result);
				dup = result;
				if(result){
					$("#dup-msg").html("<span style='color:red;'>사용 중인 id입니다.</span>");
				}
				else{
					$("#dup-msg").html("사용하실 수 있는 id입니다.");
				}
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});
	
	$("#btn-submit").on("click",function(){
		if(typeof dup == "undefined" || dup == true )
			alert('아이디 중복체크 해주세요');
		else if(! $('#chk-agree').is(":checked"))
			alert('약관에 동의해주세요.');
		else
			$("#join-form").submit();
	});
	
	$("#chk-agree").change(function(){
		if (this.checked) {
	       	open('https://www.naver.com/', 'agree-window', 'width=400, height=800');
	    }
	});
	
	
</script>

</html>