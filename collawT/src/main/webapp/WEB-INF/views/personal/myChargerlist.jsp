<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="contextPath"  value="${pageContext.request.contextPath}" />



<style>
.accent-teal .btn-link, .accent-teal a:not(.dropdown-item):not(.btn-app):not(.nav-link):not(.brand-link) {
    color: #343a40;
}
@font-face {
	font-family: 'Recipekorea';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/Recipekorea.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}
.nav-pills .nav-link.active{
		    background-color: #dc3545;
}


</style>
<!-- jsGrid -->
<script src="${contextPath}/resources/plugins/jsgrid/demos/db.js"></script>
<script src="${contextPath}/resources/plugins/jsgrid/jsgrid.min.js"></script>
<!-- DataTables -->
<script src="${contextPath}/resources/plugins/datatables/jquery.dataTables.js"></script>
<script src="${contextPath}/resources/plugins/datatables-bs4/js/dataTables.bootstrap4.js"></script>

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
				<ol class="nav nav-pills" style="font-family: Spoqa Han Sans; font-size:15px; margin:0px; border:none; padding-inline-start: 0px;  float:left;  ">
					<li class="nav-item" ><a class="nav-link " 
						href="/main"  id="activityMenu" >홈</a></li>
					<li class="nav-item" ><a class="nav-link"
						href="/personal/list?mem_Id=${member.mem_Id}" >내 이슈</a></li>
					<li class="nav-item" ><a class="nav-link active" style="background-color:#DC3545;"
						href="/personal/search/myChargerlist?mem_Id=${member.mem_Id}" id="activityMenu"><b>내 담당 이슈</b></a></li>
					<li class="nav-item" ><a class="nav-link"   
						href="/personal/search/myBoardlist?mem_Id=${member.mem_Id}" id="activityMenu">내가 쓴 글</a></li>
					<li class="nav-item" ><a class="nav-link"
						href="/personal/search/myReplylist?mem_Id=${member.mem_Id}" id="activityMenu">내가 쓴 댓글</a></li>

				</ol>
				
								
			</div>
			
						<!-- Main content -->
	<section class="content">
		
			
			
			       <!-- 리스트 부분 -->
        <div class="card-body p-0">
           <div id="example2_wrapper" class="dataTables_wrapper dt-bootstrap4" role="grid" aria-describedby="example2_info">
           
           <table id="example2" class="table table-striped table-bordered table-hover dataTable" role="grid" aria-describedby="example2_info">
            <c:choose>
			<c:when test="${fn:length(myChargerlist)!=0}">
              <thead style="font-size:13px;">
                <tr role="row">
                <th>협업공간명</th>
                <th>이슈명</th>
               	<th>상태</th>
                <th>기간</th>
               </tr>
            
              </thead>
              <tbody>
              

                 <c:forEach var="myChargerlistitem" items="${myChargerlist}" >	
                  <tr role="row" class="odd">

				  <!-- 협업공간명 -->
				  <td style="width:15%;">
				   <font size="3em">
				  ${myChargerlistitem.c_Name}</font>
					 
				  </td>
				  

				  
               	<!-- 제목&링크 -->
				<td style="width:55%">	
				<a href="/project/issue/read?c_Id=${myChargerlistitem.c_Id}&i_Num=${myChargerlistitem.i_Num}"> <font size="3em"><b>${myChargerlistitem.i_Name}</b></font></a>
				  <br>
				  </td>
				 
				 <!-- 상태 -->
				  <td style="width:10%; ">
				   <font size="3em">
					<span class="badge badge-success" style="background-color:${myChargerlistitem.ig_Color}">${myChargerlistitem.ig_Name}</span>
				  </font>
					 
				  </td>
				  
				   <!-- 작성일 -->
                      <td style="width:20%; vertical-align:top"> <font size="2em" color="#6c757d"><i class="far fa-calendar-alt"></i>&nbsp;&nbsp;<c:if test="${myChargerlistitem.i_Start == null}">기간 미설정</c:if>
                      <c:if test="${myChargerlistitem.i_Start != null}">${myChargerlistitem.i_Start} ~ ${myChargerlistitem.i_End}</c:if> 
                        </font>  </td>
                    
                     
                   </tr>
                   
                   </c:forEach>
                 </c:when>
                 <c:otherwise>
                 <p style="text-align:center;"><small><br><br>담당하고 있는 이슈가 없습니다.</small></p>
                 </c:otherwise>
                 </c:choose>
              </tbody>
          </table>
          
          				        <!-- 페이징 -->
 <div class="form-group" style="margin-left:10px; margin-right:10px">
 	<div class="row">
   <div class="col-md-4">

								</div>
	<div class="col-md-8">
   <ul class="pagination pagination-sm m-0" style="float:right">
    <c:if test="${pageMaker.prev}">
    	<li class="page-item" id="liStyle"><a class="page-link" href="myChargerlist${pageMaker.makeQuery(pageMaker.startPage - 1)}">&laquo;</a></li>
    </c:if> 

    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
    <c:choose>
    <c:when test="${pageMaker.page==idx}">
    	<li class="page-item" id="liStyle"><a class="page-link" href="myChargerlist${pageMaker.makeQuery(idx)}" style="background-color:#DC3545; color:white">${idx}</a></li>
    </c:when>
    <c:otherwise>
    <li class="page-item" id="liStyle"><a class="page-link" href="myChargerlist${pageMaker.makeQuery(idx)}">${idx}</a></li>
    </c:otherwise>
    </c:choose>
    </c:forEach>

    <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
    	<li class="page-item" id="liStyle"><a class="page-link" href="myChargerlist${pageMaker.makeQuery(pageMaker.endPage + 1)}">&raquo;</a></li>
    </c:if> 
  </ul>
  </div>
</div>
  </div>
  
 
        <!--/페이징  -->
        
        

          </div>

        
        
        </div>
        <!-- /리스트 부분 -->
       
         <br>
       
	</section>
	<!-- /.content -->
					
				
				</div>

			</div>
			<!-- /.card-body -->
		
	</div>	
<!-- /.content-wrapper -->

