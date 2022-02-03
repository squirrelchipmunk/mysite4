<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>
</head>

<body>
	<div id="wrap">

		<!-- header + nav -->>
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>

		<div id="container" class="clearfix">
			<!-- aside(guestbook) -->
			<c:import url="/WEB-INF/views/include/aside_guestbook.jsp"></c:import>

			<div id="content">
				<div id="content-head" class="clearfix">
					<h3>일반방명록</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>방명록</li>
							<li class="last">ajax방명록</li>
						</ul>
					</div>
				</div>
				<!-- //content-head -->

				<div id="guestbook">
					<!-- <form action="" method=""> -->
					
					<table id="guestAdd">
						<colgroup>
							<col style="width: 70px;">
							<col>
							<col style="width: 70px;">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th><label class="form-text" for="input-uname">이름</label>
								</td>
								<td><input id="input-uname" type="text" name="name"></td>
								<th><label class="form-text" for="input-pass">패스워드</label>
								</td>
								<td><input id="input-pass" type="password" name="pass"></td>
							</tr>
							<tr>
								<td colspan="4"><textarea id="input-cont" name="content" cols="72" rows="5"></textarea></td>
							</tr>
							<tr class="button-area">
								<td colspan="4" class="text-center"><button id="btnSubmit" type="submit">등록</button> <button id="btnSubmit2" type="submit">등록2</button></td>
							</tr>
						</tbody>

					</table>
					<!-- //guestWrite -->
					<input type="hidden" name="action" value="add">

					<!-- </form> -->

					<div id="listArea">
					
					</div>
			<%--	<c:forEach items="${gList}" var="vo">
						<table class="guestRead">
							<colgroup>
								<col style="width: 10%;">
								<col style="width: 40%;">
								<col style="width: 40%;">
								<col style="width: 10%;">
							</colgroup>
							<tr>
								<td>${vo.no}</td>
								<td>${vo.name}</td>
								<td>${vo.regDate}</td>
								<td><a href="${pageContext.request.contextPath}/guest/deleteForm?no=${vo.no}">[삭제]</a></td>
							</tr>
							<tr>
								<td colspan=4 class="text-left">${vo.content}</td>
							</tr>
						</table>
					</c:forEach> --%>
					


				</div>
				<!-- //guestbook -->

			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->

		<!-- footer -->
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
	</div>
	<!-- //wrap -->

	<!-- 삭제 모달창 -->
	<div id="delModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">비밀번호 입력 모달창</h4>
				</div>
				<div class="modal-body">
					비밀번호: 
					<input id="modalPassword" type="password" name="" value=""> <br>
					<input id="modalNo" type="text">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
					<button id="modalBtnDel" type="button" class="btn btn-primary">삭제</button>	
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!-- /삭제 모달창 -->
</body>

<script type="text/javascript">
	//로딩되기 전 요청
	$(document).ready(function(){
		console.log("리스트 요청");
		fetchList();
	});
	
	// 등록버튼 눌렀을때 - 파라미터 요청
	$("#btnSubmit").on("click",function(){
		console.log("클릭");
		// 폼 데이터 모으기
		var name= $('#input-uname').val();
		var password= $('#input-pass').val();
		var content = $('[name="content"]').val();
		
		// 객체 만들기
		var guestbookVo = {
				name: name,
				password: password,
				content: content
		};
		
		// 확인
		console.log(guestbookVo);
		
		
		$.ajax({
			url: "${pageContext.request.contextPath}/api/guest/write",
			type : "post",
			
			data: guestbookVo,
			
			dataType: "json",
			success : function(guestbookVo){	// json > js
				console.log(guestbookVo)
				render(guestbookVo,"prepend");
				$('#input-uname').val("");
				$('#input-pass').val("");
				$('#input-cont').val("");
				alert('등록됐습니다');
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
	});
	
	// 등록버튼 눌렀을 때 - json 방식 요청
	$("#btnSubmit2").on("click",function(){
		console.log("클릭2");
		// 폼 데이터 모으기
		var name= $('#input-uname').val();
		var password= $('#input-pass').val();
		var content = $('[name="content"]').val();
		
		// 객체 만들기
		var guestbookVo = {
				name: name,
				password: password,
				content: content
		};
		
		// 확인
		console.log(guestbookVo);
		
		$.ajax({
			url: "${pageContext.request.contextPath}/api/guest/write2",
			type : "post",
			
			contentType: "application/json",	// json으로 요청할 때 필요한 코드
			data:JSON.stringify(guestbookVo), // 자바스크립트 객체를 json 형식으로 변경
			
			dataType: "json",
			success : function(guestbookVo){	// json > js
				console.log(guestbookVo)
				render(guestbookVo,"prepend");
				$('#input-uname').val("");
				$('#input-pass').val("");
				$('#input-cont').val("");
				alert('등록됐습니다');
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
	});
	
	
	// 삭제버튼 눌렀을 때
	$("#listArea").on("click", ".btnDelPop" ,function(){
	
		var $this = $(this);
		console.log("btnDelPop 버튼 클릭"+ $this);
		var no = $this.data("no");
		// 회색 바탕
		// 회색 바탕 위에 팝업창 만들기
		$("#modalPassword").val("");
		$("#modalNo").val(no);
		$("#delModal").modal('show');
		
	});
	
	// 모달창의 삭제버튼 클릭했을 때
	$("#modalBtnDel").on("click",function(){
		console.log("모달창 삭제버튼 클릭");
		
		var no = $("#modalNo").val();
		var password = $("#modalPassword").val();
		var delInfo = {
			no: no,
			password: password
		};
		
		//console.log(delInfo);
		
		//ajax 요청 no password
		$.ajax({
			url: "${pageContext.request.contextPath}/api/guest/remove",
			type : "get",
			
			data: delInfo,
			
			dataType: "json",
			success : function(result){	// json > js
				console.log(result);
				if(result == "success"){
					// -해당 테이블 html 삭제
					$("#t"+no).remove();
					// -모달창 닫기
					$("#delModal").modal('hide');
				}
				else{
					// -모달창 닫기
					$("#delModal").modal('hide');
					alert("비밀번호를 확인하세요");
				}
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
	});
	
	function fetchList(){
		$.ajax({
			url: "${pageContext.request.contextPath}/api/guest/list",
			type : "post",
			//contentType: "application/json",
			//data : {name: ”홍길동"},
			
			dataType: "json",
			success : function(guestbookList){	// json > js
				/*성공시처리해야될코드작성*/
				console.log(guestbookList);
			
				/* for(var i=0; i<guestbookList.length; i++){
					render(guestbookList[i]);	// 방명록리스트 그리기
				} */
				for(var vo of guestbookList){
					render(vo, "append");	// 방명록리스트 그리기
				} 
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	}
	
	function render(guestbookVo, location){
		var str = '';
		str += '<table id="t'+guestbookVo.no+'" class="guestRead">';
		str += '	<colgroup>';
		str += '		<col style="width: 10%;">';
		str += '		<col style="width: 40%;">';
		str += '		<col style="width: 40%;">';
		str += '		<col style="width: 10%;">';
		str += '	</colgroup>';
		str += '	<tr>';
		str += '		<td>'+guestbookVo.no+'</td>';
		str += '		<td>'+guestbookVo.name+'</td>';
		str += '		<td>'+guestbookVo.regDate+'</td>';
		str += '		<td><button class="btnDelPop" type="button" data-no="'+guestbookVo.no+'">삭제</button></td>'; // data-소문자이름 = "~" ★☆★☆★☆★☆★☆★☆★☆★
		str += '	</tr>';
		str += '	<tr>';
		str += '		<td colspan=4 class="text-left">'+guestbookVo.content+'</td>';
		str += '	</tr>';
		str += '</table>';
		if(location=='prepend')
			$('#listArea').prepend(str);
		else
			$('#listArea').append(str);
	};
	
</script>


</html>