<%@page import="com.bit.model.Guest02Dto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style tpye="text/css">
body>div {
	width: 800px;
}

#content>form {
	width:600px;
	height: 400px;
	margin: 0px auto;
	border: 1px solid gray;
}
</style>
<script
  src="https://code.jquery.com/jquery-1.12.4.min.js"
  integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
  crossorigin="anonymous"></script>
<script type="text/javascript">
	$(function(){
		$('button').hide().eq(0).show().click(function(){
			$('#content>h2').text("edit page");
			$('button').show();
			$(this).hide();	
			$('input[type=hidden]').each(function(idx,ele){
				if(idx>0){
					$(ele).attr('type','text');
					$('label+span').hide().first().show();	
				}
			});
		}).next().next().next().click(function(){
			$('#content>h2').text("detail page");
			$('button').hide().eq(0).show();
			$('input[type=text]').attr('type','hidden');
			$('label+span').show();
		});
		
		
	});
</script>
</head>
<body>
	<div>
		<div id="header">
			<h1>detail page</h1>
		</div>

		<div id="menu">
			<ul>
				<li><a href="/mvc04/">HOME</a></li>
				<li><a href="/mvc04/INTRO">INTRO</a></li>
				<li><a href="/mvc04/bbs/list.bit">BBS</a></li>
				<li><a href="/mvc04/LOGIN">LOGIN</a></li>
			</ul>
		</div>

		<div id="content">
		<%
			Guest02Dto bean=(Guest02Dto)request.getAttribute("bean");//bean으로 보내서 bean으로 가져옴
		
		%>
			<h2>insert page</h2>
			<form action="detail.bit" method="post">
				<div>
					<label for="num">num</label>
					<span><%=bean.getNum() %></span>
					<input type="hidden" name="num" id="num" value="<%=bean.getNum() %>"/>
				</div>
				<div>
					<label for="sub">sub</label>
					<span><%=bean.getSub() %></span>
					<input type="hidden" name="sub" id="sub" value="<%=bean.getSub() %>"/>
				</div>
				<div>
					<label for="unum">unum</label>
					<span><%=bean.getUnum() %></span>
					<input type="hidden" name="unum" id="unum" value="<%=bean.getUnum() %>" disabled="disabled"/>
				</div>
				<div>
					<label for="pay">pay</label>
					<span><%=bean.getPay() %></span>
					<input type="hidden" name="pay" id="pay" value="<%=bean.getPay() %>"/>
				</div>
				<div>
					<button type="button">edit</button>
					<button type="submit">insert</button>
					<button type="reset">cancle</button>
					<button type="button">back</button>
				</div>
			</form>
		</div>


		<div id="footer">Copyright &copy; 비트캠프 All rights reserved.</div>

	</div>
</body>
</html>