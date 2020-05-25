<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<meta name="viewport" content="width=device-width, initial-scale=1">

<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script type="text/javascript">
	var wsocket;
	var userId = '${member.mem_Id}';
	var userName = '${member.mem_Name}';
	var target_Id
	var target_Name
	function connect(obj) {
		//타겟 아이디 가져오기
		target_Id = $(obj).data('targetmember');
		target_Name = $(obj).data('targetnames');
		
		$.ajax({
			url : '${contextPath}/chatmsg/list?target_Id='+ target_Id,
			type : 'get',
			success : function(data) {
				var itemString = "";
				var itemcount = data.length;
				if (data != 0) {
					for ( var i in data) {
				itemString = data[i].mem_Name + ' : ' + data[i].message;
				appendMessage(itemString);
			}}else{
				var notitem = "채팅내역이 없습니다."
				appendMessage(notitem);
			}
				
			}})
		
		wsocket = new WebSocket("ws://collawt.site/chat-ws");
		wsocket.onopen = onOpen;
		wsocket.onmessage = onMessage;
		wsocket.onclose = onClose;
		
		//채팅알림 카운트를 확인시 삭제 및 총 카운트에서 확인한 채팅 카운트를 빼기위한 스크립트
		var countSpan = $(obj).children('span');
		var musCount = countSpan.html();
		if (musCount != null) {
		var totalCountNum = $('#totalCountNum').html();
		var changeNum = totalCountNum - musCount;
		$('#totalCountNum').html(changeNum);
		}
		$('#inputTarget').html(target_Name);
		$('#chatarea').css('visibility','visible');
		countSpan.empty();
	}
	function disconnect() {
		if(wsocket != null){wsocket.close();}
		$('#chatarea').css('visibility','hidden');
	}
	function onOpen(evt) {
		
		
	}
	function onMessage(evt) {
		var data = evt.data;
		
		if (data.substring(0, 4) == "msg:") {
			appendMessage(data.substring(4));
		}
			appendMessage(data);
			
	}
	function onClose(evt) {
		$("#chatMessageArea").empty();
		
	}
	
	function send() {
		/* var name = '${member.mem_Name}';
		var msg = $("#message").val();
		wsocket.send("msg:"+name+":" + msg); */
		
		var msg = {
				inId : userId,
				name : userName,
				target : target_Id,
				message : $("#message").val()
		};
		wsocket.send(JSON.stringify(msg));
		$("#message").val("");
	}

	function appendMessage(msg) {
		$("#chatMessageArea").append(msg+"<br>");
		var chatAreaHeight = $("#chatArea").height();
		var maxScroll = $("#chatMessageArea").height() - chatAreaHeight;
		$("#chatArea").scrollTop(maxScroll);
	}

	$(document).ready(function() {
		$('#message').keypress(function(event){
			var keycode = (event.keyCode ? event.keyCode : event.which);
			if(keycode == '13'){
				send();	
			}
			event.stopPropagation();
		});
		$('#sendBtn').click(function() { send(); });

		$(document).on("click","#enterBtn",function(){ disconnect(),connect(this);});
		//$('#enterBtn').click(function(event) { connect(event); }); 이벤트 버블링때문에 안됨
		$('#exitBtn').click(function() { disconnect(); });
	});
</script>

<style>
#chatArea {
	width: 100%; height: 200px; overflow-y: auto; border: 1px solid black;
}
</style>
	
	<footer class="main-footer" >
    <strong>Copyright &copy; 2020.04 - 2020.5 <a href="/main">Collaw T</a>.</strong>
    All rights reserved.
    <div class="float-right d-none d-sm-inline-block">

      <b>Version</b> 1.0.1-pre
    </div>
  </footer>

  <!-- Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark" style="width: 350px;">
 <div class="sidebar" style ="margin-bottom: 150px;">
 <nav class="mt-2">
 <li class="nav-item has-treeview" style="margin: 10px;">파트너 채팅</li>
					<c:forEach var="partnerList" items="${coworklist}">
				<ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
					<li class="nav-item has-treeview" style="border-bottom: 1px solid #4f5962;">
					<a href="#" class="nav-link active" style="background-color: ${partnerList.color};"> <i class="nav-icon far fa-address-book"></i>
					<b>${partnerList.c_Name}</b><i class="right fas fa-angle-left"></i>
					</a>
						<ul class="nav nav-treeview" >
					
						<c:forEach var="partnermember" items="${partnerList.memberList}">
						<c:if test="${partnermember.mem_Id != member.mem_Id}">
								<li class="nav-item"><a href="#" class="nav-link" data-targetmember="${partnermember.mem_Id}"
								data-targetnames="${partnermember.mem_Name}" id="enterBtn"
								 style="padding-top: 5px; padding-bottom: 5px; padding-left: 10px; white-space: none;">
								 		<c:if test="${partnermember.mem_Log == '1'}">
										<b style="font-size: 14px; color: white;">
										</c:if>
								 		<c:if test="${partnermember.mem_Log != '1'}">
										<b style="font-size: 14px;">
										</c:if>
										${partnermember.mem_Name}<font size="2">
										(${partnermember.mem_Id})</font></b>
										<c:forEach var="msgcountItem" items="${msgCount}">
										<c:if test="${partnermember.mem_Id == msgcountItem.mem_Id}">
											<span id="countNum" class="badge badge-danger navbar-badge">${msgcountItem.chat_Count}</span>
										</c:if>
										</c:forEach>
								</a></li>	
								</c:if>
							</c:forEach>
							</ul>
				</li></ul>
							</c:forEach>
						
				</nav>
				
				<div  id = "chatarea" class="col-md-6" style="max-width: 100%; visibility: hidden;">
	
    
    <li class="nav-item has-treeview" id = "inputTarget" style="margin: 10px;"></li>
    <div id="chatArea"><div id="chatMessageArea"></div></div>
    <br/>
    <input type="text" id="message">
    <input type="button" id="sendBtn" value="전송">
	<input type="button" id="exitBtn" value="나가기">

	</div>
 </div>
    <!-- Control sidebar content goes here -->
  </aside>
  <!-- /.control-sidebar -->
</div>
<!-- ./wrapper -->



<div id='chatroom-popup' class="needpopup">

	
</div>

<script type="text/javascript">
needPopup.config.custom = {
		'removerPlace' : 'outside',
		'closeOnOutside' : false,
		onShow : function() {
			console.log('needPopup is shown');
		},
		onHide : function() {
			console.log('needPopup is hidden');
		}
	};
	needPopup.init();
	</script>

<!-- jQuery -->
<script src="${contextPath}/resources/plugins/jquery/jquery.min.js"></script>
<!-- jQuery UI 1.11.4 -->
<script src="${contextPath}/resources/plugins/jquery-ui/jquery-ui.min.js"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
  $.widget.bridge('uibutton', $.ui.button)
</script>
<!-- Bootstrap 4 -->
<script src="${contextPath}/resources/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- ChartJS -->
<script src="${contextPath}/resources/plugins/chart.js/Chart.min.js"></script>
<!-- Sparkline -->
<script src="${contextPath}/resources/plugins/sparklines/sparkline.js"></script>
<!-- JQVMap -->
<script src="${contextPath}/resources/plugins/jqvmap/jquery.vmap.min.js"></script>
<script src="${contextPath}/resources/plugins/jqvmap/maps/jquery.vmap.usa.js"></script>
<!-- jQuery Knob Chart -->
<script src="${contextPath}/resources/plugins/jquery-knob/jquery.knob.min.js"></script>
<!-- daterangepicker -->
<script src="${contextPath}/resources/plugins/moment/moment.min.js"></script>
<script src="${contextPath}/resources/plugins/daterangepicker/daterangepicker.js"></script>
<!-- Tempusdominus Bootstrap 4 -->
<script src="${contextPath}/resources/plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
<!-- Summernote -->
<script src="${contextPath}/resources/plugins/summernote/summernote-bs4.min.js"></script>
<!-- overlayScrollbars -->
<script src="${contextPath}/resources/plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
<!-- AdminLTE App -->
<script src="${contextPath}/resources/dist/js/adminlte.js"></script>
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<script src="${contextPath}/resources/dist/js/pages/dashboard.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="${contextPath}/resources/dist/js/demo.js"></script>
</body>
</html>