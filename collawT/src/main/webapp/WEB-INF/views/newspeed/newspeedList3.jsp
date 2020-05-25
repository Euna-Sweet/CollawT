<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />

<style>
@font-face {
	font-family: 'Recipekorea';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/Recipekorea.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}


</style>

<script type="text/javascript">



	$(document).ready(function(){
		
		
		$('#myform').click(function(event) {
			
			var button_Id = event.target.getAttribute('id');
			var mem_Id = $('#mem_Id').val();
			var c_Id = $('#c_Id').val();
			console.log(button_Id)
			//초대 수락을 위한 ajax
			if (button_Id == 'acceptsubmit') {
				$.ajax({
				url : '${contextPath}/news/accept?mem_Id='+mem_Id+'&c_Id='+c_Id,
				type : 'get',
				success : function(data) {
					if (data == 1) {
					alert('수락하셨습니다.');
					setCookie('apply', 'active', 1)
					location.reload();
							}
						}
					})
			
			}else if(button_Id == 'rejectsubmit'){//초대 거절을 위한 ajax
				$.ajax({
					url : '${contextPath}/news/reject?mem_Id='+mem_Id+'&c_Id='+c_Id,
					type : 'get',
					success : function(data) {
						if (data == 1) {
						alert('거절하셨습니다.');
						setCookie('apply', 'active', 1)
						location.reload();
							}
						}
					}) 
			}
		
		})
	});

				
</script>

<div class="content-wrapper">
	<div class="content-header" style="height: 100px">
		<div class="container-fluid">
			<div class="row mb-2">
				<div class="col-sm-6">
					<div>
						<h1 class="m-0 text-dark"
							style="font-family: Recipekorea; max-width: 80%; display: contents;">새로운 소식</h1>
					</div>
					<div>
					<p class="breadcrumb float-sm-left" style="position: absolute; margin-top: 10px">새로운 소식을 한번에 관리하세요 :D</p>
					</div>
					</div>
			</div>
		</div>
	</div>
	<div class="col-md-9" style="max-width: 100%;">
		<div class="card">
			<div class="card-header p-2">
				<ul class="nav nav-pills">
					<li class="nav-item" ><a class="nav-link active"
						href="#activity" data-toggle="tab" id="activityMenu">새로운 소식</a></li>
					<li class="nav-item" id = "applymenu"><a class="nav-link" href="#timeline"
						data-toggle="tab" id= "timelineMenu">내가 받은 초대</a></li>
					<li class="nav-item"><a class="nav-link" href="#settings"
						data-toggle="tab">댓글</a></li>
					<li class="nav-item"><a class="nav-link" href="#vote"
						data-toggle="tab">투표</a></li>
				</ul>
			</div>
			<!-- /.card-header -->
			
			<!--  <c:forEach var="applylist" items="${applylist}" >	
                  <tr>
                      <td> ${applylist.c_Id} </td>
                      <td> ${applylist.ap_Date} </td>
                      <td><button>수락</button><button>거절</button></td>
                   </tr>
                   </c:forEach> -->
			<div class="card-body">
				<div class="tab-content">
					<div class="active tab-pane" id="activity">
						<!-- Post -->
						<c:forEach var="item" items = "${notifyList}">
						<div class="post">						
							<div class="user-block">
								<%-- <img class="img-circle img-bordered-sm"
									src="${contextPath}/resources/dist/img/user1-128x128.jpg" alt="user image"> --%>	
								
								<span class="username"  id = "test11" > <a href="/notify/update?c_Id=${item.c_Id}&i_Num=${item.i_Num}">${item.mem_Id}
										</a> <a href="#" class="float-right btn-tool"><i
										class="fas fa-times"></i></a>
								</span> <span class="description">${item.i_Date}
									</span>
							</div>
							<!-- /.user-block -->
							<p>${item.i_Content}</p>

							
						</div>
						</c:forEach>
						<!-- /.post -->

						<%-- <!-- Post -->
						<div class="post clearfix">
							<div class="user-block">
								<img class="img-circle img-bordered-sm"
									src="${contextPath}/resources/dist/img/user7-128x128.jpg" alt="User Image">
								<span class="username"> <a href="#">Sarah Ross</a> <a
									href="#" class="float-right btn-tool"><i
										class="fas fa-times"></i></a>
								</span> <span class="description">Sent you a message - 3 days
									ago</span>
							</div>
							<!-- /.user-block -->
							<p>Lorem ipsum represents a long-held tradition for
								designers, typographers and the like. Some people hate it and
								argue for its demise, but others ignore the hate as they create
								awesome tools to help create filler text for everyone from bacon
								lovers to Charlie Sheen fans.</p>

							<form class="form-horizontal">
								<div class="input-group input-group-sm mb-0">
									<input class="form-control form-control-sm"
										placeholder="Response">
									<div class="input-group-append">
										<button type="submit" class="btn btn-danger">Send</button>
									</div>
								</div>
							</form>
						</div>
						<!-- /.post -->

						<!-- Post -->
						<div class="post">
							<div class="user-block">
								<img class="img-circle img-bordered-sm"
									src="${contextPath}/resources/dist/img/user6-128x128.jpg" alt="User Image">
								<span class="username"> <a href="#">Adam Jones</a> <a
									href="#" class="float-right btn-tool"><i
										class="fas fa-times"></i></a>
								</span> <span class="description">Posted 5 photos - 5 days ago</span>
							</div>
							<!-- /.user-block -->
							<div class="row mb-3">
								<div class="col-sm-6">
									<img class="img-fluid" src="${contextPath}/resources/dist/img/photo1.png"
										alt="Photo">
								</div>
								<!-- /.col -->
								<div class="col-sm-6">
									<div class="row">
										<div class="col-sm-6">
											<img class="img-fluid mb-3"
												src="${contextPath}/resources/dist/img/photo2.png" alt="Photo"> <img
												class="img-fluid" src="${contextPath}/resources/dist/img/photo3.jpg"
												alt="Photo">
										</div>
										<!-- /.col -->
										<div class="col-sm-6">
											<img class="img-fluid mb-3"
												src="${contextPath}/resources/dist/img/photo4.jpg" alt="Photo"> <img
												class="img-fluid" src="${contextPath}/resources/dist/img/photo1.png"
												alt="Photo">
										</div>
										<!-- /.col -->
									</div>
									<!-- /.row -->
								</div>
								<!-- /.col -->
							</div>
							<!-- /.row -->

							<p>
								<a href="#" class="link-black text-sm mr-2"><i
									class="fas fa-share mr-1"></i> Share</a> <a href="#"
									class="link-black text-sm"><i class="far fa-thumbs-up mr-1"></i>
									Like</a> <span class="float-right"> <a href="#"
									class="link-black text-sm"> <i class="far fa-comments mr-1"></i>
										Comments (5)
								</a>
								</span>
							</p>

							<input class="form-control form-control-sm" type="text"
								placeholder="Type a comment">
						</div>
						<!-- /.post --> --%>
					</div>
					<!-- /.tab-pane -->
					<div class="tab-pane" id="timeline">
						<!-- The timeline -->
						<div class="timeline timeline-inverse">
							<!-- timeline time label -->
							<!-- <div class="time-label">
								<span class="bg-danger"> 10 Feb. 2014 </span>
							</div> -->
							<!-- /.timeline-label -->
							<!-- timeline item -->
							 <c:forEach var="applylist1" items="${applylist}">
							 <c:choose>
							 <c:when  test= "${applylist1.ap_Yn eq 'waiting'}">
							<div>
								<i class="fas fa-envelope bg-primary"></i>

								<div class="timeline-item">
									<span class="time"><i class="far fa-clock"></i> ${applylist1.ap_Date}</span>

									<h3 class="timeline-header">
										<a href="#">${applylist1.c_Name}</a>에서 가입요청이 왔습니다
									</h3>
									<div class="timeline-body">${applylist1.mem_Name}님께서 회원님을  ${applylist1.c_Name}에 초대하셨습니다. </div>
									<div class="timeline-footer" id="timeline-footer">
									<form  id = "myform" action="/news/accept" method="post" style="display: inline;  margin: 5;">
									<input type="hidden" name="c_Id" id="c_Id" value="${applylist1.c_Id}">
									<input type="hidden" name="mem_Id" id="mem_Id" value="${applylist1.mem_Id}">
										<a id = "acceptsubmit" href="#" class="btn btn-primary btn-sm" style="color: white;">수락</a>
										<a id = "rejectsubmit" href="#" class="btn btn-danger btn-sm" style="color: white;">거절</a>
										</form>
									</div>
								</div>
							</div>
							</c:when>
							</c:choose>
							  </c:forEach>
							<!-- END timeline item -->
							<!-- timeline item -->
							<!-- <div>
								<i class="fas fa-user bg-info"></i>

								<div class="timeline-item">
									<span class="time"><i class="far fa-clock"></i> 5 mins
										ago</span>

									<h3 class="timeline-header border-0">
										<a href="#">Sarah Young</a> accepted your friend request
									</h3>
								</div>
							</div> -->
							<!-- END timeline item -->
							<!-- timeline item -->
							<!-- <div>
								<i class="fas fa-comments bg-warning"></i>

								<div class="timeline-item">
									<span class="time"><i class="far fa-clock"></i> 27 mins
										ago</span>

									<h3 class="timeline-header">
										<a href="#">Jay White</a> commented on your post
									</h3>

									<div class="timeline-body">Take me to your leader!
										Switzerland is small and neutral! We are more like Germany,
										ambitious and misunderstood!</div>
									<div class="timeline-footer">
										<a href="#" class="btn btn-warning btn-flat btn-sm">View
											comment</a>
									</div>
								</div>
							</div>
							END timeline item
							timeline time label
							<div class="time-label">
								<span class="bg-success"> 3 Jan. 2014 </span>
							</div>
							/.timeline-label
							timeline item
							<div>
								<i class="fas fa-camera bg-purple"></i>

								<div class="timeline-item">
									<span class="time"><i class="far fa-clock"></i> 2 days
										ago</span>

									<h3 class="timeline-header">
										<a href="#">Mina Lee</a> uploaded new photos
									</h3>

									<div class="timeline-body">
										<img src="http://placehold.it/150x100" alt="..."> <img
											src="http://placehold.it/150x100" alt="..."> <img
											src="http://placehold.it/150x100" alt="..."> <img
											src="http://placehold.it/150x100" alt="...">
									</div>
								</div>
							</div> -->
							<!-- END timeline item -->
							<!-- <div>
								<i class="far fa-clock bg-gray"></i>
							</div> -->
						</div>
					</div>
					<!-- /.tab-pane -->

					<div class="tab-pane" id="settings">
					<c:forEach var="reply" items = "${replyList}">
						<div class="post">						
							<div class="user-block">
								<img class="img-circle img-bordered-sm"
									src="${contextPath}/resources/dist/img/user1-128x128.jpg" alt="user image">	
								
								<span class="username"  id = "test44" > <a href="/notify/replyupdate?c_Id=${reply.c_Id}&i_Num=${reply.i_Num}">${reply.mem_Id}
										</a> <a href="#" class="float-right btn-tool"><i
										class="fas fa-times"></i></a>
								</span> <span class="description">
									</span>
							</div>
							<!-- /.user-block -->
							<p>${reply.i_Content}</p>

							
						</div>
						</c:forEach> 
						<%-- <form class="form-horizontal">
							<div class="form-group row">
								<label for="inputName" class="col-sm-2 col-form-label">Name</label>
								<div class="col-sm-10">
									<input type="email" class="form-control" id="inputName"
										placeholder="Name">
								</div>
							</div>
							<div class="form-group row">
								<label for="inputEmail" class="col-sm-2 col-form-label">Email</label>
								<div class="col-sm-10">
									<input type="email" class="form-control" id="inputEmail"
										placeholder="Email">
								</div>
							</div>
							<div class="form-group row">
								<label for="inputName2" class="col-sm-2 col-form-label">Name</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inputName2"
										placeholder="Name">
								</div>
							</div>
							<div class="form-group row">
								<label for="inputExperience" class="col-sm-2 col-form-label">Experience</label>
								<div class="col-sm-10">
									<textarea class="form-control" id="inputExperience"
										placeholder="Experience"></textarea>
								</div>
							</div>
							<div class="form-group row">
								<label for="inputSkills" class="col-sm-2 col-form-label">Skills</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inputSkills"
										placeholder="Skills">
								</div>
							</div>
							<div class="form-group row">
								<div class="offset-sm-2 col-sm-10">
									<div class="checkbox">
										<label> <input type="checkbox"> I agree to the
											<a href="#">terms and conditions</a>
										</label>
									</div>
								</div>
							</div>
							<div class="form-group row">
								<div class="offset-sm-2 col-sm-10">
									<button type="submit" class="btn btn-danger">Submit</button>
								</div>
							</div>
						</form> --%>
					</div>
					
					<div class="active tab-pane" id="vote">
						<!-- Post -->
						<c:forEach var="vote" items = "${voteList}">
						<div class="post">						
							<div class="user-block">
								<%-- <img class="img-circle img-bordered-sm"
									src="${contextPath}/resources/dist/img/user1-128x128.jpg" alt="user image"> --%>	
								
								<span class="username"  id = "test11" > <a href="/notify/voteUpdate?c_Id=${vote.c_Id}&v_Num=${vote.v_Num}">${vote.c_Id}
										</a> <a href="#" class="float-right btn-tool"><i
										class="fas fa-times"></i></a>
								</span> <span class="description">
									</span>
							</div>
							<!-- /.user-block -->
							<p>${vote.v_Num}</p>

							
						</div>
						</c:forEach>
					
					</div>
					<!-- /.tab-pane -->
				</div>
				<!-- /.tab-content -->
			</div>
			<!-- /.card-body -->
		</div>
		<!-- /.nav-tabs-custom -->
	</div>
</div>
