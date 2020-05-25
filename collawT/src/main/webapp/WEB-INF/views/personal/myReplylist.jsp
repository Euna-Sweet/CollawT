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
$(document).ready(function() {
	

    
	$("#checkAll").click(function(){
		if($("#checkAll").is(":checked")==true){
			
			$("input[name='checkbox']").prop("checked", true); 
	
		}else{
			$("input[name='checkbox']").prop("checked", false); 
		}		
	})

  });
  
  function fileDelete(){
	  if(confirm('삭제하시겠습니까?')){
	//체크박스 중 체크된 체크박스만 가져와서 Loop 합니다.
		  $("input:checkbox[name=checkbox]:checked").each(function(i,elements){
			  
		      //해당 index(순서)값을 가져옵니다.
		      index = $(elements).index("input:checkbox[name=checkbox]");
		      //해당 index에 해당하는 체크박스의 ID 속성을 가져옵니다.
		      var boardtype = $("input:hidden[name=boardtype]").eq(index).val();
		      var r_Num = $("input:checkbox[name=checkbox]").eq(index).attr("id")
				//alert(boardtype)
	      
 		      	if(boardtype==1){
			    	location.href="${contextPath}/personal/search/issuereplydelete?r_Num="+r_Num;
			      }else if(boardtype==2){
				    	location.href="${contextPath}/personal/search/votereplydelete?vr_Num="+r_Num;
			      }else if(boardtype==3){
				    	location.href="${contextPath}/personal/search/personalmemodelete?p_m_Num="+r_Num;
			      }else{
			    	  alert("오류가 발생하였습니다.");
			      }
	    	  
	  })
	  alert('삭제되었습니다.');
  	}else{
		return false;
	}
  }
  

  $('#example2').DataTable({
      "paging": true,
      "lengthChange": false,
      "searching": false,
      "ordering": true,
      "info": true,
      "autoWidth": false,
    });
		
  
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
				<ol class="nav nav-pills" style="font-family: Spoqa Han Sans; font-size:15px; margin:0px; border:none; padding-inline-start: 0px;float:left;  ">
					<li class="nav-item" ><a class="nav-link " 
						href="/main"  id="activityMenu" >홈</a></li>
					<li class="nav-item" ><a class="nav-link"
						href="/personal/list?mem_Id=${member.mem_Id}" >내 이슈</a></li>
					<li class="nav-item" ><a class="nav-link"
						href="/personal/search/myChargerlist?mem_Id=${member.mem_Id}" id="activityMenu">내 담당 이슈</a></li>
					<li class="nav-item" ><a class="nav-link"
						href="/personal/search/myBoardlist?mem_Id=${member.mem_Id}" id="activityMenu">내가 쓴 글</a></li>
					<li class="nav-item" ><a class="nav-link active"   style="background-color:#DC3545;"
						href="/personal/search/myReplylist?mem_Id=${member.mem_Id}" id="activityMenu"><b>내가 쓴 댓글</b></a></li>
				</ol>
				
								
			</div>
			
						<!-- Main content -->
	<section class="content">
		
			
			
			       <!-- 리스트 부분 -->
        <div class="card-body p-0">
           <div id="example2_wrapper" class="dataTables_wrapper dt-bootstrap4" role="grid" aria-describedby="example2_info">
           
           <table id="example2" class="table table-striped table-bordered table-hover dataTable" role="grid" aria-describedby="example2_info">
            <c:choose>
			<c:when test="${fn:length(myReplylist)!=0}">
              <thead style="font-size:13px;">
                <tr role="row">
                <th><input type="checkbox" value="" id="checkAll" name="checkAll"></th>
                <th>협업공간명</th>
                <th>유형</th>
                <th>글 제목 및 댓글 내용</th>
               	<th>상태</th>
                <th>작성일</th>
               </tr>
            
              </thead>
              <tbody>
              

                 <c:forEach var="myReplylist" items="${myReplylist}" >	
                  <tr role="row" class="odd">
                  <!-- 체크박스 -->
                  	 <td style="width:5%" class="sorting_1">	
                        <input type="checkbox" value="" id="${myReplylist.r_Num}" name="checkbox">
                        <input type="hidden" value="${myReplylist.r_Num}" id="text${myReplylist.r_Num}" name="checkbox">
				  </td>
				  <!-- 협업공간명 -->
				  <td style="width:13%;">
				   <font size="3em">
				  <c:if test="${myReplylist.c_Name==null}">내 공간</c:if>
				  <c:if test="${myReplylist.c_Name!=null}"> ${myReplylist.c_Name}</c:if></font>
					 
				  </td>
				  
				  <!-- 유형 -->
				  <td style="width:7%;">
				   <font size="3em">
				  <c:if test="${myReplylist.boardtype==1}">이슈<input type="hidden" name = "boardtype" id="${myReplylist.boardtype}" value="${myReplylist.boardtype}"></c:if>
				  <c:if test="${myReplylist.boardtype==2}">투표<input type="hidden" name = "boardtype" id="${myReplylist.boardtype}" value="${myReplylist.boardtype}"></c:if>
				  <c:if test="${myReplylist.boardtype==3}">이슈<input type="hidden" name = "boardtype" id="${myReplylist.boardtype}" value="${myReplylist.boardtype}"></c:if>
				  </font>
					 
				  </td>
				  
               	<!-- 제목&링크&내용 -->
				<td style="width:50%">	
				<c:if test="${myReplylist.boardtype==1}">
				<a href="/project/issue/read?c_Id=${myReplylist.c_Id}&i_Num=${myReplylist.i_Num}"> <font size="3em"><b>${myReplylist.i_Name}</b></font>
				<br><small>${myReplylist.r_Content}</small></a>
				</c:if>
				<c:if test="${myReplylist.boardtype==2}">
				<a href="/project/vote/read?c_Id=${myReplylist.c_Id}&v_Num=${myReplylist.i_Num}"> <font size="3em"><b>${myReplylist.i_Name}</b></font>
				<br><small>${myReplylist.r_Content}</small></a>
				</c:if>
				<c:if test="${myReplylist.boardtype==3}">
				<a href="/personal/read?mem_Id=${myReplylist.mem_Id}&p_Num=${myReplylist.i_Num}"> <font size="3em"><b>${myReplylist.i_Name}</b></font>
				<br><small>${myReplylist.r_Content}</small></a>
				</c:if>
				  </td>
				 
				 <!-- 상태 -->
				  <td style="width:10%; ">
				   <font size="3em">
					<span class="badge badge-success" style="background-color:${myReplylist.ig_Color}">${myReplylist.ig_Name}</span>
				  </font>
					 
				  </td>
				  
				   <!-- 작성일 -->
				  <td style="width:25%; text-align:center">	
					  <font size="3em">${myReplylist.r_Date}</font>
					  <br>
				  </td>
                    
                     
                   </tr>
                   
                   </c:forEach>
                 </c:when>
                 <c:otherwise>
                 <p style="text-align:center;"><small><br><br>작성한 글이 없습니다.</small></p>
                 </c:otherwise>
                 </c:choose>
              </tbody>
          </table>
          
          				        <!-- 페이징 -->
 <div class="form-group" style="margin-left:10px; margin-right:10px">
 	<div class="row">
   <div class="col-md-4">
   <c:if test="${fn:length(myReplylist)!=0}">
        <button type="button" class="btn btn-danger" onclick="javascript:fileDelete()" style="text-align:center; font-family: Spoqa Han Sans; font-size:13px;">
								<b>선택 삭제</b></button></c:if>
								</div>
	<div class="col-md-8">
   <ul class="pagination pagination-sm m-0" style="float:right">
    <c:if test="${pageMaker.prev}">
    	<li class="page-item" id="liStyle"><a class="page-link" href="myReplylist${pageMaker.makeQuery(pageMaker.startPage - 1)}">&laquo;</a></li>
    </c:if> 

    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
    <c:choose>
    <c:when test="${pageMaker.page==idx}">
    	<li class="page-item" id="liStyle"><a class="page-link" href="myReplylist${pageMaker.makeQuery(idx)}" style="background-color:#DC3545; color:white">${idx}</a></li>
    </c:when>
    <c:otherwise>
    <li class="page-item" id="liStyle"><a class="page-link" href="myReplylist${pageMaker.makeQuery(idx)}">${idx}</a></li>
    </c:otherwise>
    </c:choose>
    </c:forEach>

    <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
    	<li class="page-item" id="liStyle"><a class="page-link" href="myReplylist${pageMaker.makeQuery(pageMaker.endPage + 1)}">&raquo;</a></li>
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

