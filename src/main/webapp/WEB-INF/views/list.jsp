<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<link rel="stylesheet" href="resources/css/common.css" type="text/css">
<style>
#footer {
    height: 100px;
}
</style>
</head>
<body>
	게시판 리스트
	<hr />
	<table>
		<thead>
			<tr>
				<th>글번호</th>
				<th>이미지</th>
				<th>제목</th>
				<th>작성자</th>
				<th>조회수</th>
				<th>날짜</th>
			</tr>
		</thead>
		<tbody id="list">

		</tbody>
	</table>
	<button onclick="callList()">d</button>
	<div id='footer'></div>
</body>
<script>
var start = 0;
var max = 10;

callList();

function callList(){
	$.ajax({
		
		type:'post'
		,url:'./callList.ajax'
		,data:{'max':max}
		,dataType:'JSON'
		,success:function(data){
			console.log(data.list);
			max+=5;
			showList(data.list);
		},error:function(error){
			console.log(error);
		}
	});
}

function showList(list) {
	var content = "";
	for(item of list){
//		console.log(item);
		var img = item.img_cnt > 0 ? 'image.png':'no_image.png';
		var date = new Date(item.reg_date);
		content += 
			'<tr>'
			+'<td>'+item.idx+'</td>'
			+'<td>'
			+'<img class="icon"  src="resources/img/'+img+'" alt="ImageCheck">'
			+'</td>'
			+'<td>'+item.subject+'</td>'
			+'<td>'+item.user_name+'</td>'
			+'<td>'+item.bHit+'</td>'
			+'<td>'+date.toLocaleDateString("ko-KR")+'</td>'
			+'</tr>';
	}
	$('#list').html(content);
}


</script>
</html>