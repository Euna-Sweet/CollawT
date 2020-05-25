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


</style>

<script type="text/javascript">
$(document).ready(function() {
	 var c_Id_1 = $("#c_Id_1").val();
	 $("#c_Id").val(c_Id_1).prop("selected",true);
	 
	 var order_1 = $("#order_1").val();
	 $("#order").val(order_1).prop("selected",true);
	 
	 var wr_mem_Id_1 = $("#wr_mem_Id_1").val();
	 $("#wr_mem_Id").val(wr_mem_Id_1).prop("selected",true);
	 
	 var a_NameEx_1 = $("#a_NameEx_1").val();
	 $("#a_NameEx").val(a_NameEx_1).prop("selected",true);
	 

	$("#c_Id").change(function(){
		var c_Id = $("#c_Id").val();
		var keyword = $("#keyword_1").val();
		
		var url = "${contextPath}/search/fileresult";
		url = url + "?keyword="+keyword;
		url = url + "&c_Id="+c_Id;

		location.href=url;

	});
	
	$("#order").change(function(){
		var c_Id = $("#c_Id").val();
		var keyword = $("#keyword_1").val();
		var order = $("#order").val();
		var wr_mem_Id = $("#wr_mem_Id").val();
		var a_NameEx = $("#a_NameEx").val();

		var url = "${contextPath}/search/fileresult";
		url = url + "?keyword="+keyword;
		url = url + "&c_Id="+c_Id;
		url = url + "&order="+order;
		url = url + "&wr_mem_Id="+wr_mem_Id;
		url = url + "&a_NameEx="+a_NameEx;

		location.href=url;

	});
	
	$("#wr_mem_Id").change(function(){
		var c_Id = $("#c_Id").val();
		var keyword = $("#keyword_1").val();
		var order = $("#order").val();
		var wr_mem_Id = $("#wr_mem_Id").val();
		var a_NameEx = $("#a_NameEx").val();
		
		var url = "${contextPath}/search/fileresult";
		url = url + "?keyword="+keyword;
		url = url + "&c_Id="+c_Id;
		url = url + "&order="+order;
		url = url + "&wr_mem_Id="+wr_mem_Id;
		url = url + "&a_NameEx="+a_NameEx;

		location.href=url;

	});
	
	
	$("#a_NameEx").change(function(){
		var c_Id = $("#c_Id").val();
		var keyword = $("#keyword_1").val();
		var order = $("#order").val();
		var a_NameEx = $("#a_NameEx").val();

		
		var url = "${contextPath}/search/fileresult";
		url = url + "?keyword="+keyword;
		url = url + "&c_Id="+c_Id;
		url = url + "&order="+order;
		url = url + "&a_NameEx="+a_NameEx;

		location.href=url;

	});
})
				
</script>

<!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header" style="height: 20px">
     
    </section>

	<!-- Main content -->
    <section class="content">
    <input type="hidden" value="${keyword}" id="keyword_1" name="keyword_1">
     <input type="hidden" value="${c_Id}" id="c_Id_1" name="c_Id_1">
    <input type="hidden" value="${order}" id="order_1" name="order_1">
   <input type="hidden" value="${a_NameEx}" id="a_NameEx_1" name="a_NameEx_1">
    <input type="hidden" value="${wr_mem_Id}" id="wr_mem_Id_1" name="wr_mem_Id_1">
      <div class="row">
        <div class="col-md-2">

          <div class="card">
            
            <div class="card-body p-0">
              <ul class="nav nav-pills flex-column">
                  <li class="nav-item active">
                  <a href="/search/wholeresult?keyword=${keyword}" class="nav-link">
                    <i class="fas fa-th-large"></i>&nbsp;전체
                  </a>
                </li>
                <li class="nav-item active">
                  <a href="/search/issueresult?keyword=${keyword}" class="nav-link">
                     <i class="far fa-file-alt"></i>&nbsp;이슈
                  </a>
                </li>
                <li class="nav-item">
                  <a href="/search/voteresult?keyword=${keyword}" class="nav-link">
                    <i class="fas fa-vote-yea"></i>&nbsp;투표
                  </a>
                </li>
                <li class="nav-item" style="background-color:#dc3545;">
                  <a href="/search/fileresult?keyword=${keyword}" class="nav-link" style="color:white">
                    <i class="fas fa-inbox"></i>&nbsp;<b>파일</b>
                  </a>
                </li>
                <li class="nav-item">
                  <a href="/search/replyresult?keyword=${keyword}" class="nav-link">
                    <i class="fas fa-comment-dots"></i>&nbsp;댓글 및 메모
                  </a>
                </li>
               
              </ul>
            </div>
            <!-- /.card-body -->
          </div>
          <!-- /.card -->
          <div class="card">
            
            <div class="card-body p-0">
            <select class="form-control custom-select" id="c_Id" name="c_Id">
		      	<option value="">전체 협업공간</option>
		      	<option value="myspace">내 공간</option>
		         <c:forEach var="coworkitem" items="${coworkList}" >
		         <option id="${coworkitem.C_ID}" name="${coworkitem.C_ID}" value="${coworkitem.C_ID}">${coworkitem.C_NAME}</option>
		        </c:forEach>
		      </select>
		      
     			<select class="form-control custom-select" id="wr_mem_Id" name="wr_mem_Id">
			         <option value="">전체 작성자</option>
			         <c:forEach var="fileitem" items="${fileWriter}" >
			         <option id="${fileitem.wr_mem_Id}" name="${fileitem.wr_mem_Id}" value="${fileitem.wr_mem_Id}">${fileitem.mem_Name}(${fileitem.wr_mem_Id})</option>
			        </c:forEach>
		      </select>	
		      
				<select class="form-control custom-select" id="order" name="order">
			         <option id="" name="" value="">최신 등록 순</option>
			       	 <option id="asc" name="asc" value="asc">오래된 등록 순</option>
			     </select>
			     
			  <select class="form-control custom-select" id="a_NameEx" name="a_NameEx">
			         <option id="" name="" value="">전체 확장자</option>
			         <c:forEach var="exitem" items="${fileEx}" >
			         <option id="${exitem.a_NameEx}" name="${exitem.a_NameEx}" value="${exitem.a_NameEx}">${exitem.a_NameEx}</option>
			        </c:forEach>
			     </select>
			     
            </div>
            <!-- /.card-body -->
          </div>
          <!-- /.card -->
        </div>
        <!-- /.col -->
        <div class="col-md-10">
          <div class="card card-danger card-outline">
            <div class="card-header">
              <h4 class="card-title"><b>검색 결과 총 ${fileCount}건</b></h4>

              <div class="card-tools">
                <div class="input-group input-group-sm">
                  <div class="input-group-append">

                  </div>
                </div>
              </div>
              <!-- /.card-tools -->
            </div>
            <!-- /.card-header -->
            <div class="card-body p-0">
            
              <div class="table-responsive mailbox-messages">
                <table class="table table-hover table-striped">
                  <tbody>
                   <c:choose>
			<c:when test="${fn:length(fileList)!=0}">
                  <c:forEach var="item" items="${fileList}" >
                  <tr>
                  
                 	<td class="mailbox-name" style="width:20%">
                 	<div style="padding-left:10px">
                 	<c:choose>
                 	<c:when test="${item.a_NameEx == '7z' || item.a_NameEx == 'apk' || item.a_NameEx == 'app' || item.a_NameEx == 'avi' 
                 	|| item.a_NameEx == 'css' || item.a_NameEx == 'csv' || item.a_NameEx == 'doc' || item.a_NameEx == 'docx' 
                 	|| item.a_NameEx == 'exe' || item.a_NameEx == 'gif' || item.a_NameEx == 'html' || item.a_NameEx == 'jar' 
                 	|| item.a_NameEx == 'jpg' || item.a_NameEx == 'js' || item.a_NameEx == 'jsp' || item.a_NameEx == 'm4a'
                 	|| item.a_NameEx == 'mp3' || item.a_NameEx == 'mp4' || item.a_NameEx == 'mpa' || item.a_NameEx == 'mpg' 
                 	|| item.a_NameEx == 'otf' || item.a_NameEx == 'pdf' || item.a_NameEx == 'png' || item.a_NameEx == 'pps' 
                 	|| item.a_NameEx == 'ppt' || item.a_NameEx == 'pptx' || item.a_NameEx == 'rar' || item.a_NameEx ==	'tiff' 
                 	|| item.a_NameEx == 'ttf' || item.a_NameEx == 'txt' || item.a_NameEx == 'wav' || item.a_NameEx == 'wma' 
                 	|| item.a_NameEx == 'wmv' || item.a_NameEx == 'xhtml' || item.a_NameEx == 'xlr' || item.a_NameEx == 'xls' 
                 	|| item.a_NameEx == 'xlsx' || item.a_NameEx == 'xml' || item.a_NameEx == 'zip'}">
                 		<img src="${contextPath}/resources/fileimage/${item.a_NameEx}.png" style="width:40px; height:40px"
								 alt="User Image">
					 </c:when>
					 <c:otherwise>
					 <img src="${contextPath}/resources/fileimage/blank.png" style="width:40px; height:40px"
								 alt="User Image">
					 </c:otherwise>
					 </c:choose>
					
					 </div>
					
                 	<small>
                 	<c:if test="${item.c_Name != null}">
                 	<a href="/appendix/download?a_Num=${item.a_Num}"><i class="fas fa-download"></i>&nbsp;&nbsp;<b>${item.a_Realname}</b></a>
              		</c:if>
              		<c:if test="${item.c_Name == null}">
              		<a href="/personal/appendix/download?p_a_Num=${item.a_Num}"><i class="fas fa-download"></i>&nbsp;&nbsp;<b>${item.a_Realname}</b></a>
              		</c:if>
              		</small>
              		
              		</td>
                    <td class="mailbox-name">
                    <div class="row" style="padding-bottom:10px;">
                     <span>
	                    <c:if test="${item.c_Name == null}">
						<a  style="color:black;" href="/personal/read?mem_Id=${member.mem_Id}&p_Num=${item.i_Num}">
	                   <b>${item.i_Name}</b>
	                    </a>
	                    </c:if>
	                    <c:if test="${item.c_Name != null}">
						<a  style="color:black;" href="/project/issue/read?c_Id=${item.c_Id}&i_Num=${item.i_Num}">
	                    <b>${item.i_Name}</b>
	                    </a>
	                    </c:if>
	                   
	                   &nbsp;&nbsp;
	                   <c:if test="${item.ig_Name != null}">
			                   <span style= "height : 17px; font-size : 10px; vertical-align : text-bottom; padding: .4em .4em; background-color:${item.ig_Color};"
									id="ig_Name" name="ig_Name" class="badge badge-success"><c:out  value="${item.ig_Name}" />
									
								</span>
						</c:if>
                     
                  	</span>

								
                    </div>
                      <div class="row" style= "vertical-align:text-bottom; height : 34px">
                    
                  
                    <span>
                   <img class="img-fluid img-circle img-sm"
								src="/member/getByteImage?mem_Id=${item.wr_mem_Id}" alt="Alt Text" width="30" height="30" onError="this.src='${contextPath}/resources/dist/img/profile.jpg';">
								 <small>&nbsp;${item.mem_Name}(${item.wr_mem_Id})</small></span>
								&nbsp;&nbsp;
								
								<span><small>from&nbsp;&nbsp;</small></span>
								
								<span><small><b>
								<c:if test="${item.c_Name == null}">
								<a href="/personal/list?mem_Id=${item.wr_mem_Id}">
								내 공간</a></c:if>
								<c:if test="${item.c_Name != null}">
								<a href="/project/main?c_Id=${item.c_Id}">
								${item.c_Name}</a>
								</c:if>
								</b></small>
								</span>
					</div>
                    </td>
                    <td class="mailbox-subject">
                    </td>
                    <td class="mailbox-attachment"></td>
                    <td class="mailbox-date">
                    <span><small>${item.i_Date}</small></span>
                    <!-- 5 mins ago --></td>
                  </tr>
              </c:forEach>
               </c:when>
                 <c:otherwise>
                 <p style="text-align:center;"><small><br><br>검색 결과가 없습니다.<br><br></small></p>
                 </c:otherwise>
                 </c:choose>
                  </tbody>
                </table>
                <!-- /.table -->
              </div>
              <!-- /.mail-box-messages -->
            </div>
            <!-- /.card-body -->
            <div class="card-footer p-0">
              <div class="mailbox-controls">
                <!-- Check all button -->
                
                <div class="btn-group">
                  
                </div>
                
                <div class="float-left">
                 	      <div class="form-group" style="margin-left:20px">

   <ul class="pagination pagination-sm m-0">
    <c:if test="${filepageMaker.prev}">
    	<li class="page-item" id="liStyle"><a class="page-link" href="fileresult?${filepageMaker.makeQuery(filepageMaker.startPage - 1)}">&laquo;</a></li>
    </c:if> 

    <c:forEach begin="${filepageMaker.startPage}" end="${filepageMaker.endPage}" var="idx">
    <c:choose>
    <c:when test="${filepageMaker.page==idx}">
    	<li class="page-item" id="liStyle"><a class="page-link" href="fileresult${filepageMaker.makeQuery(idx)}" style="background-color:#DC3545; color:white">${idx}</a></li>
    </c:when>
    <c:otherwise>
    <li class="page-item" id="liStyle"><a class="page-link" href="fileresult${filepageMaker.makeQuery(idx)}">${idx}</a></li>
    </c:otherwise>
    </c:choose>
    </c:forEach>

    <c:if test="${filepageMaker.next && filepageMaker.endPage > 0}">
    	<li class="page-item" id="liStyle"><a class="page-link" href="fileresult${filepageMaker.makeQuery(filepageMaker.endPage + 1)}">&raquo;</a></li>
    </c:if> 
  </ul>
  
  </div>
                </div>
                <!-- /.float-right -->
              </div>
            </div>
          </div>
          <!-- /.card -->
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->

    </section>
	<!-- /.content -->
	</div>
		
<!-- /.content-wrapper -->

<script>
  $(function () {
    //Enable check and uncheck all functionality
   

  })
</script>