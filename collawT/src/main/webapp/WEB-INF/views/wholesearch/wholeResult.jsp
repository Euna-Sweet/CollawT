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



<!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header" style="height: 20px">
     
    </section>

	<!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-md-2">

          <div class="card">
            
            <div class="card-body p-0">
              <ul class="nav nav-pills flex-column">
                  <li class="nav-item active" style="background-color:#dc3545;">
                  
                  <a href="/search/wholeresult?keyword=${keyword}" class="nav-link"  style="color:white">
                    <i class="fas fa-th-large"></i>&nbsp;<b>전체</b>
                     <span class="badge bg-warning float-right" style="margin-top:3px"> ${issueCount+voteCount+fileCount+replyCount}</span>
                  </a>
         </li>
                <li class="nav-item active">
                
                  <a href="/search/issueresult?keyword=${keyword}" class="nav-link">
                     <i class="far fa-file-alt"></i>&nbsp; 이슈
                     <span class="badge bg-success float-right" style="margin-top:3px"> ${issueCount}</span>
                  </a>
                  
                </li>
                <li class="nav-item">
                
                  <a href="/search/voteresult?keyword=${keyword}" class="nav-link">
                    <i class="fas fa-vote-yea"></i>&nbsp; 투표
                    <span class="badge bg-success float-right" style="margin-top:3px"> ${voteCount}</span>
                  </a>
                  
                </li>
                <li class="nav-item">
                
                  <a href="/search/fileresult?keyword=${keyword}" class="nav-link">
                    <i class="fas fa-inbox"></i>&nbsp; 파일
                    <span class="badge bg-success float-right" style="margin-top:3px"> ${fileCount}</span>
                  </a>
                  
                </li>
                <li class="nav-item">
                
                  <a href="/search/replyresult?keyword=${keyword}" class="nav-link">
                    <i class="fas fa-comment-dots"></i>&nbsp;댓글 및 메모
                    <span class="badge bg-success float-right" style="margin-top:3px"> ${replyCount}</span>
                  </a>
                  
                </li>
               
              </ul>
            </div>
            <!-- /.card-body -->
          </div>
          <!-- /.card -->
          
          <div class="card">
            

            <!-- /.card-body -->
          </div>
          <!-- /.card -->
        </div>
        <!-- /.col -->
       
        <div class="col-md-10">
          <div class="card card-danger card-outline">
            <div class="card-header">
              <h4 class="card-title"><b>이슈 검색 결과 총 ${issueCount}건</b></h4>

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
			<c:when test="${fn:length(issueList)!=0}">
                  <c:forEach var="issueitem" items="${issueList}" >
                  <tr>
              
                    <td class="mailbox-name">
                    <div class="row" style="padding-bottom:10px;">
                     <span>
	                    <c:if test="${issueitem.c_Name == null}">
						<a  style="color:black;" href="/personal/read?mem_Id=${member.mem_Id}&p_Num=${issueitem.i_Num}">
	                   <b>${issueitem.i_Name}</b>
	                    </a>
	                    </c:if>
	                    
	                    <c:if test="${issueitem.c_Name != null}">
						<a  style="color:black;" href="/project/issue/read?c_Id=${issueitem.c_Id}&i_Num=${issueitem.i_Num}">
	                    <b>${issueitem.i_Name}</b>
	                    </a>
	                    </c:if>
	                   
	                   &nbsp;&nbsp;
	                   <c:if test="${issueitem.ig_Num != null}">
			                   <span style= "height : 17px; font-size : 10px; vertical-align : text-bottom; padding: .4em .4em; background-color:${issueitem.ig_Color};"
									id="ig_Name" name="ig_Name" class="badge badge-success"><c:out  value="${issueitem.ig_Name}" />
									
								</span>
						</c:if>
                     
                  	</span>

								
                    </div>
                      <div class="row" style= "vertical-align:text-bottom;">
                    
                  
                    <span>
                   <img class="img-fluid img-circle img-sm"
								src="/member/getByteImage?mem_Id=${issueitem.wr_mem_Id}" alt="Alt Text" width="30" height="30" onError="this.src='${contextPath}/resources/dist/img/profile.jpg';">
								 <small>&nbsp;${issueitem.mem_Name}(${issueitem.wr_mem_Id})</small></span>
								&nbsp;&nbsp;
								
								<span><small>from&nbsp;&nbsp;</small></span>
								
								<span><small><b>
								<c:if test="${issueitem.c_Name == null}">
								<a href="/personal/list?mem_Id=${item.wr_mem_Id}">
								내 공간</a></c:if>
								<c:if test="${issueitem.c_Name != null}">
								<a href="/project/main?c_Id=${issueitem.c_Id}">
								${issueitem.c_Name}</a>
								</c:if>
								</b></small>
								</span>
					</div>
                    </td>
                    <td class="mailbox-subject">
                    </td>
                    <td class="mailbox-attachment"></td>
                    <td class="mailbox-date">
                    <span><small>${issueitem.i_Date}</small></span>
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
                 	      <div class="form-group" style="margin-left:10px">
                 	      
<c:if test="${fn:length(issueList)!=0}">
<a href="/search/issueresult?keyword=${keyword}" class="nav-link"><b>이슈 검색 결과 더 보기</b> </a> 
</c:if>  </div>
                </div>
                <!-- /.float-right -->
              </div>
            </div>
          </div>
          <!-- /.card -->
          
         
          
                    <div class="card card-danger card-outline">
            <div class="card-header">
              <h4 class="card-title"><b>투표 검색 결과 총 ${voteCount}건</b></h4>

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
			<c:when test="${fn:length(voteList)!=0}">
                  <c:forEach var="voteitem" items="${voteList}" >
                  <tr>
              
                    <td class="mailbox-name">
                    <div class="row" style="padding-bottom:10px;">
                     <span>
						<a  style="color:black;" href="/project/vote/read?c_Id=${voteitem.c_Id}&v_Num=${voteitem.v_Num}">
	                    <b>${voteitem.v_Name}</b> </a>
	                   
	                   &nbsp;&nbsp;
	                   
			                   <span style= "height : 17px; font-size : 10px; vertical-align : text-bottom; padding: .4em .4em;
									background-color:${voteitem.vs_Color};
									"
									id="vs_Name" name="vs_Name" class="badge badge-success">
									
									<c:out  value="${voteitem.vs_Name}" />
									
									
								</span>
								<br>
								<small>주제 : ${voteitem.v_Subject}</small>
						
                     
                  	</span>

								
                    </div>
                      <div class="row" style= "vertical-align:text-bottom;">
                    
                  
                    <span>
                   <img class="img-fluid img-circle img-sm"
								src="/member/getByteImage?mem_Id=${voteitem.wr_mem_Id}" alt="Alt Text" width="30" height="30" onError="this.src='${contextPath}/resources/dist/img/profile.jpg';">
								 <small>&nbsp;${voteitem.mem_Name}(${voteitem.wr_mem_Id})</small></span>
								&nbsp;&nbsp;
								
								<span><small>from&nbsp;&nbsp;</small></span>
								
								<span><small><b>

								<a href="/project/main?c_Id=${voteitem.c_Id}">
								${voteitem.c_Name}</a>
							
								</b></small>
								</span>
					</div>
                    </td>
                    <td class="mailbox-subject">
                    </td>
                    <td class="mailbox-attachment"></td>
                    <td class="mailbox-date">
                    <span><small>${voteitem.v_Date}</small></span>
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
                 	      <div class="form-group" style="margin-left:10px">

<c:if test="${fn:length(voteList)!=0}">
<a href="/search/voteresult?keyword=${keyword}" class="nav-link"><b>투표 검색 결과 더 보기</b> </a>
 </c:if> 
  </div>
                </div>
                <!-- /.float-right -->
              </div>
            </div>
          </div>
          
          
          
          
                    <div class="card card-danger card-outline">
            <div class="card-header">
              <h4 class="card-title"><b>파일 검색 결과 총 ${fileCount}건</b></h4>

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
                  <c:forEach var="fileitem" items="${fileList}" >
                  <tr>
                  
                 	<td class="mailbox-name" style="width:20%">
                 	<div style="padding-left:10px">
                 	<c:choose>
                 	<c:when test="${fileitem.a_NameEx == '7z' || fileitem.a_NameEx == 'apk' || fileitem.a_NameEx == 'app' || fileitem.a_NameEx == 'avi' 
                 	|| fileitem.a_NameEx == 'css' || fileitem.a_NameEx == 'csv' || fileitem.a_NameEx == 'doc' || fileitem.a_NameEx == 'docx' 
                 	|| fileitem.a_NameEx == 'exe' || fileitem.a_NameEx == 'gif' || fileitem.a_NameEx == 'html' || fileitem.a_NameEx == 'jar' 
                 	|| fileitem.a_NameEx == 'jpg' || fileitem.a_NameEx == 'js' || fileitem.a_NameEx == 'jsp' || fileitem.a_NameEx == 'm4a'
                 	|| fileitem.a_NameEx == 'mp3' || fileitem.a_NameEx == 'mp4' || fileitem.a_NameEx == 'mpa' || fileitem.a_NameEx == 'mpg' 
                 	|| fileitem.a_NameEx == 'otf' || fileitem.a_NameEx == 'pdf' || fileitem.a_NameEx == 'png' || fileitem.a_NameEx == 'pps' 
                 	|| fileitem.a_NameEx == 'ppt' || fileitem.a_NameEx == 'pptx' || fileitem.a_NameEx == 'rar' || fileitem.a_NameEx ==	'tiff' 
                 	|| fileitem.a_NameEx == 'ttf' || fileitem.a_NameEx == 'txt' || fileitem.a_NameEx == 'wav' || fileitem.a_NameEx == 'wma' 
                 	|| fileitem.a_NameEx == 'wmv' || fileitem.a_NameEx == 'xhtml' || fileitem.a_NameEx == 'xlr' || fileitem.a_NameEx == 'xls' 
                 	|| fileitem.a_NameEx == 'xlsx' || fileitem.a_NameEx == 'xml' || fileitem.a_NameEx == 'zip'}">
                 		<img src="${contextPath}/resources/fileimage/${fileitem.a_NameEx}.png" style="width:40px; height:40px"
								 alt="User Image">
					 </c:when>
					 <c:otherwise>
					 <img src="${contextPath}/resources/fileimage/blank.png" style="width:40px; height:40px"
								 alt="User Image">
					 </c:otherwise>
					 </c:choose>
					
					 </div>
					
                 	<small>
                 	<a href="/appendix/download?a_Num=${fileitem.a_Num}"><i class="fas fa-download"></i>&nbsp;&nbsp;<b>${fileitem.a_Realname}</b></a>
              		</small>
              		
              		</td>
                    <td class="mailbox-name" style="width:52%">
                    <div class="row" style="padding-bottom:10px;">
                     <span>
	                    <c:if test="${fileitem.c_Name == null}">
						<a  style="color:black;" href="/personal/read?mem_Id=${member.mem_Id}&p_Num=${fileitem.i_Num}">
	                   <b>${fileitem.i_Name}</b>
	                   
	                    </a>
	                    </c:if>
	                    <c:if test="${fileitem.c_Name != null}">
						<a  style="color:black;" href="/project/issue/read?c_Id=${fileitem.c_Id}&i_Num=${fileitem.i_Num}">
	                    <b>${fileitem.i_Name}</b>
	                    </a>
	                    </c:if>
	                   
	                   &nbsp;&nbsp;
	                   <c:if test="${fileitem.ig_Name != null}">
			                   <span style= "height : 17px; font-size : 10px; vertical-align : text-bottom; padding: .4em .4em; background-color:${fileitem.ig_Color};"
									id="ig_Name" name="ig_Name" class="badge badge-success"><c:out  value="${fileitem.ig_Name}" />
									
								</span>
						</c:if>
                     
                  	</span>

								
                    </div>
                      <div class="row" style= "vertical-align:text-bottom; height : 34px">
                    
                  
                    <span>
                   <img class="img-fluid img-circle img-sm"
								src="/member/getByteImage?mem_Id=${fileitem.wr_mem_Id}" alt="Alt Text" width="30" height="30" onError="this.src='${contextPath}/resources/dist/img/profile.jpg';">
								 <small>&nbsp;${fileitem.mem_Name}(${fileitem.wr_mem_Id})</small></span>
								&nbsp;&nbsp;
								
								<span><small>from&nbsp;&nbsp;</small></span>
								
								<span><small><b>
								<c:if test="${fileitem.c_Name == null}">
								<a href="/personal/list?mem_Id=${fileitem.wr_mem_Id}">
								내 공간</a></c:if>
								<c:if test="${fileitem.c_Name != null}">
								<a href="/project/main?c_Id=${fileitem.c_Id}">
								${fileitem.c_Name}</a>
								</c:if>
								</b></small>
								</span>
					</div>
                    </td>
                    <td class="mailbox-date">
                    <span><small>${fileitem.i_Date}</small></span>
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
                 	      <div class="form-group" style="margin-left:10px">
<c:if test="${fn:length(fileList)!=0}">
<a href="/search/fileresult?keyword=${keyword}" class="nav-link"><b>파일 검색 결과 더 보기</b> </a>
</c:if>
  
  </div>
                </div>
                <!-- /.float-right -->
              </div>
            </div>
          </div>
         
          
          
          
                    <div class="card card-danger card-outline">
            <div class="card-header">
              <h4 class="card-title"><b>댓글 및 메모 검색 결과 총 ${replyCount}건</b></h4>

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
			<c:when test="${fn:length(replyList)!=0}">
                  <c:forEach var="replyitem" items="${replyList}" >
                  <tr>
              
                    <td class="mailbox-name" style="width:63%">
                    <div class="row" style="padding-bottom:10px;">
                     <span>
	                    <c:if test="${replyitem.c_Name == null}">
						<a  style="color:black;" href="/personal/read?mem_Id=${member.mem_Id}&p_Num=${replyitem.i_Num}">
	                   <b>${replyitem.i_Name}</b>
	                   	                   &nbsp;&nbsp;
	                   <c:if test="${replyitem.ig_Num != null}">
			                   <span style= "height : 17px; font-size : 10px; vertical-align : text-bottom; padding: .4em .4em; background-color:${replyitem.ig_Color}"
									id="ig_Name" name="ig_Name" class="badge badge-success"><c:out  value="${replyitem.ig_Name}" />
									
								</span>
						</c:if>
	                   
	                   <br>
	                   <small>${replyitem.r_Content}</small>
	                    </a>
	                    </c:if>
	                    <c:if test="${replyitem.c_Name != null}">
						<a  style="color:black;" href="/project/issue/read?c_Id=${replyitem.c_Id}&i_Num=${replyitem.i_Num}">
	                   <b>${replyitem.i_Name}</b>
	                   	                   &nbsp;&nbsp;
	                   <c:if test="${replyitem.ig_Num != null}">
			                   <span style= "height : 17px; font-size : 10px; vertical-align : text-bottom; padding: .4em .4em; background-color:${replyitem.ig_Color}"
									id="ig_Name" name="ig_Name" class="badge badge-success"><c:out  value="${replyitem.ig_Name}" />
									
								</span>
						</c:if>
	                   <br>
	                   <small>${replyitem.r_Content}</small>
	                    </a>
	                    </c:if>
	                   

                     
                  	</span>

								
                    </div>
                      <div class="row" style= "vertical-align:text-bottom;">
                    
                  
                    <span>
                   <img class="img-fluid img-circle img-sm"
								src="/member/getByteImage?mem_Id=${replyitem.wr_mem_Id}" alt="Alt Text" width="30" height="30" onError="this.src='${contextPath}/resources/dist/img/profile.jpg';">
								 <small>&nbsp;${replyitem.mem_Name}(${replyitem.wr_mem_Id})</small></span>
								&nbsp;&nbsp;
								
								<span><small>from&nbsp;&nbsp;</small></span>
								
								<span><small><b>
								<c:if test="${replyitem.c_Name == null}">
								<a href="/personal/list?mem_Id=${item.wr_mem_Id}">
								내 공간</a></c:if>
								<c:if test="${replyitem.c_Name != null}">
								<a href="/project/main?c_Id=${item.c_Id}">
								${replyitem.c_Name}</a>
								</c:if>
								</b></small>
								</span>
					</div>
                    </td>
                    <td class="mailbox-subject">
                    </td>
                    
                    <td class="mailbox-attachment"></td>
                    <td class="mailbox-date">
                    <span><small>${replyitem.r_Date}</small></span>
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
                 	      <div class="form-group" style="margin-left:10px">
       <c:if test="${fn:length(replyList)!=0}">          	      
						<a href="/search/replyresult?keyword=${keyword}" class="nav-link"><b>댓글 및 메모 검색 결과 더 보기</b> </a>
						</c:if>
						</div>
                </div>
                <!-- /.float-right -->
              </div>
            </div>
          </div>
          
          
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->

    </section>
	<!-- /.content -->
	</div>
		
<!-- /.content-wrapper -->
