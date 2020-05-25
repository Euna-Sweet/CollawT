<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<style type="text/css">
			#liStyle {list-style: none; float: left; padding: 6px;}
		</style>
		
		
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <section class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1>새로운 소식!</h1>
            <small>새로운 소식을 한번에 관리하세요 :D</small>
          </div>
          
         
        </div>
      </div>
    </section>

    <!-- Main content -->
    <section class="content">
    	
    

    <!-- card -->
      <div class="card">
      
      
        <!-- 표 윗부분 -->
<div class="card-header" style="background-color:#fff;">
        <ol class="breadcrumb-sm-left" style="margin:0px; border:none; padding-inline-start: 0px;  width:400px; float:left">
          <li id="liStyle" class="breadcrumb-item"><a href="#">새로운 소식</a></li>
          <li id="liStyle" class="breadcrumb-item"><a href="#">내가 받은 초대</a></li>
          <li id="liStyle" class="breadcrumb-item"><a href="#">캘린더</a></li>
         </ol>
         <input type="button" value="이슈 작성" class="btn btn-success float-right" onclick="location.href='insert'" 
         style="width:100px; text-align:center; float:right">
    </div>
    
        <!-- /표 윗부분 -->
        
        <!-- 리스트 부분 -->
        <div class="card-body p-0">
           
          <table id="example1" class="table table-striped projects">
              <tbody>

                 <c:forEach var="applylist" items="${applylist}" >	
                  <tr>
                      <td> ${applylist.c_Id} </td>
                      <td> ${applylist.ap_Date} </td>
                      <td><button>수락</button><button>거절</button></td>
                   </tr>
                   </c:forEach>
              </tbody>
          </table>
        </div>
        <!-- /리스트 부분 -->
        
      </div>
      <!-- /.card -->

    </section>
    <!-- /Main content -->
  </div>
  <!-- /.content-wrapper -->
  
  <script>
  $(function () {
    $("#example1").DataTable({
      "responsive": true,
      "autoWidth": false,

    }) });
    
</script>
