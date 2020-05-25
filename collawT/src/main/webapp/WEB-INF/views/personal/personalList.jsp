<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


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
.accent-teal .btn-link, .accent-teal a:not(.dropdown-item):not(.btn-app):not(.nav-link):not(.brand-link) {
    color: #343a40;
}
.nav-pills .nav-link.active{
		    background-color: #dc3545;

</style>

<script type="text/javascript">

				
</script>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<div class="content-header" style="height: 80px">
		<div class="container-fluid">
			<div class="row mb-2">
				<div class="col-sm-6">
					<div>
						<h1 class="m-0 text-dark"
							style="font-family: Recipekorea; max-width: 80%; display: contents;">내 공간</h1>
					</div>
					</div>
			</div>
		</div>
	</div>
	<div class="col-md-9" style="max-width: 100%;">
		<div class="card">
			<div class="card-header p-2">
				<ol class="nav nav-pills" style="font-family: Spoqa Han Sans; font-size:15px; margin:0px; border:none; padding-inline-start: 0px; float:left;  ">
					<li class="nav-item" ><a class="nav-link " 
						href="/main"  id="activityMenu" >홈</a></li>
					<li class="nav-item" ><a class="nav-link active" style="background-color:#DC3545;"
						href="/personal/list?mem_Id=${member.mem_Id}" ><b>내 이슈</b></a></li>
					<li class="nav-item" ><a class="nav-link"
						href="/personal/search/myChargerlist?mem_Id=${member.mem_Id}" id="activityMenu">내 담당 이슈</a></li>
					<li class="nav-item" ><a class="nav-link"   
						href="/personal/search/myBoardlist?mem_Id=${member.mem_Id}" id="activityMenu">내가 쓴 글</a></li>
					<li class="nav-item" ><a class="nav-link"
						href="/personal/search/myReplylist?mem_Id=${member.mem_Id}" id="activityMenu">내가 쓴 댓글</a></li>
				</ol>
				
				<button type="button" class="btn btn-success float-right" onclick="location.href='/personal/insert?mem_Id=${member.mem_Id}'"style="text-align:center; float:right; background-color:#DC3545; border-color:#DC3545; font-family: Spoqa Han Sans; font-size:13px;">
								<i class="fas fa-edit"></i> <b>이슈 작성</b></button>
								
			</div>
			
						<!-- Main content -->
	<section class="content">
		
			
			
			       <!-- 리스트 부분 -->
        <div class="card-body p-0">
           
          <table class="table table-striped table-bordered projects">
              <thead>
              <tr>
              <td style="font-size:13px; text-align:left"><b>글쓴이</b></td>
              <td style="font-size:13px; text-align:left"><b>제목</b></td>
              <td style="font-size:13px; text-align:left"><b>작성일</b></td>
              </tr>
              </thead>
              
              <tbody>
			<c:choose>
			<c:when test="${fn:length(personalList)!=0}">
                 <c:forEach var="personalList" items="${personalList}" >	
                  <tr>
                  
                  	  <td style="width:15%;"><font size="3em" color="#6c757d">${personalList.mem_Name}</font></td>
               
					  <td style="width:55%">	
					  <a href="/personal/read?mem_Id=${personalList.mem_Id}&p_Num=${personalList.p_Num}"> <font size="3em"><b>${personalList.p_i_Name}</b></font></a>
					  <br>
			
				  	  
				  </td>
                      
                      <td style="width:30%; vertical-align:top"> <font size="2em" color="#6c757d"><i class="far fa-calendar-alt"></i>&nbsp;&nbsp;<c:if test="${personalList.p_i_Start == null}">기간 미설정</c:if>
                      <c:if test="${personalList.p_i_Start != null}">${personalList.p_i_Start} ~ ${personalList.p_i_End}</c:if> 
                        </font>  </td>
                     
                   </tr>
                   
                   </c:forEach>
                 </c:when>
                 <c:otherwise>
                 <p style="text-align:center;"><small><br><br>작성된 글이 없습니다.</small></p>
                 </c:otherwise>
                 </c:choose>
              </tbody>
          </table>
          
        </div>
        
        <!-- /리스트 부분 -->
        <br>
				        <!-- 페이징 -->
 <div class="form-group" style="margin-right:20px; float:right">

   <ul class="pagination pagination-sm m-0">
    <c:if test="${pageMaker.prev}">
    	<li class="page-item" id="liStyle"><a class="page-link" href="list${pageMaker.makeQuery(pageMaker.startPage - 1)}">&laquo;</a></li>
    </c:if> 

    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
    <c:choose>
    <c:when test="${pageMaker.page==idx}">
    	<li class="page-item" id="liStyle"><a class="page-link" href="list${pageMaker.makeQuery(idx)}" style="background-color:#DC3545; color:white">${idx}</a></li>
    </c:when>
    <c:otherwise>
    <li class="page-item" id="liStyle"><a class="page-link" href="list${pageMaker.makeQuery(idx)}">${idx}</a></li>
    </c:otherwise>
    </c:choose>
    </c:forEach>

    <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
    	<li class="page-item" id="liStyle"><a class="page-link" href="list${pageMaker.makeQuery(pageMaker.endPage + 1)}">&raquo;</a></li>
    </c:if> 
  </ul>
  <br>
  </div>
  
 
        <!--/페이징  -->
       

	</section>
	<!-- /.content -->
					
				
				</div>

			</div>
			<!-- /.card-body -->
		
		
<!-- /.content-wrapper -->

  <script>
  $(function () {
    $("#example1").DataTable({
      "responsive": true,
      "autoWidth": false,
    })
  });
    
    
</script>