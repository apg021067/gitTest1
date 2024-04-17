<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/common.css" type="text/css">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<style>
input[name="id"] {
	width: 70%;
}
</style>
</head>
<body>
	<h3>회원가입</h3>

	<hr />
	<form action="join.do" method="post">
		<table>
			<tr>
				<th>ID</th>
				<td>
					<input type="text" name="id" value="${id}" onchange="idChanged()"/> 
					<input type="button" value="중복체크"/>
				</td>
			</tr>
			<tr>
				<th>PW</th>
				<td><input type="text" name="pw" /></td>
			</tr>
			<tr>
				<th>NAME</th>
				<td><input type="text" name="name" /></td>
			</tr>
			<tr>
				<th>AGE</th>
				<td><input type="text" name="age" /></td>
			</tr>
			<tr>
				<th>GENDER</th>
				<td>
					<input type="radio" name="gender" value="남" />남자
					&nbsp;&nbsp;&nbsp;&nbsp; 
					<input type="radio" name="gender" value="여" />여자
				</td>
			</tr>
			<tr>
				<th>EMAIL</th>
				<td><input type="text" name="email" /></td>
			</tr>
			<tr>
				<th colspan="2"><input type="button" value="회원가입"/></th>
			</tr>
		</table>
	</form>
</body>
<script>
	var msg = '${msg}'; // 쿼터 빠지면 넣은 문구가 변수로 인식됨.
	var checkIdFlag = false;
	if (msg != '') {
		alert(msg);
	}
	$('input[type="button"][value="회원가입"]').on('click',function(){
		console.log('click');
		var regExp = new RegExp('^[0-9]+$');
		var id = $('input[name="id"]');
		var pw = $('input[name="pw"]');
		var name = $('input[name="name"]');
		var age = $('input[name="age"]');
		var gender = $('input[type="radio"][name="gender"]:checked');
		var email = $('input[name="email"]');
		console.log(gender);
		var alertMsg = '';
		if(checkIdFlag == false){
			alert('중복 체크 먼저');
		}else{
			if(id.val() == '' || pw.val() == '' || name.val() == '' || age.val() == '' || gender.val() == null || email.val() == ''){
				alert('정보가 비었습니다. \n');
			}else{
				if(regExp.test(age.val()) == false){
					age.val('');
					alert('나이는 숫자만');
				}else{
					$('form').submit();
				}
			}
		}
		
	});
	function idChanged(){
		checkIdFlag = false;
	}
	$('input[type="button"][value="중복체크"]').on('click',function(){
		var id = $('input[name="id"]').val();
		// 		console.log(id);
		$.ajax({
			type:'post'
			,url:'./checkDuplicateID.ajax'
			,dataType:'JSON'
			,data:{'id':id}
			,success:function(data){
				console.log(data.result);
				if(data.result){
					alert('중복 체크 통과');
					checkIdFlag = true;
				}else{
					alert('중복된 아이디');
					$('input[name="id"]').val('');
				}
			},error:function(error){
				console.log(error);
			}
		});
	});

/* 	$('input[name="age"]').on('keydown',function(evt){
		var age = $(this).val();
		var regExp = new RegExp('\d');
		$(this).val('');
		
		if (regExp.test(evt.key)) {
        	age = evt.key;
    	}

    	$(this).val(age);

    	console.log(evt.key + " " + $(this).val());
	}); */
</script>
</html>