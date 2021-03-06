<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${contextPath}/resources/css/style.css"> 
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="${contextPath}/resources/ibsheet/ibsheetinfo.js"></script>
<script src="${contextPath}/resources/ibsheet/ibsheet.js"></script>
<script src="${contextPath}/resources/ibsheet/ibleaders.js"></script>
<style>
.ib_product{
width: 100%;
	}
#content{

    margin-left: 0px;
}

</style>

<script language="javascript">
	//시트 높이 계산용
	var pageheightoffset = 200;
	
	/*Sheet 기본 설정 */
	function LoadPage() {
		mySheet.RemoveAll();
		//아이비시트 초기화
		var initSheet = {};
		initSheet.Cfg = {SearchMode:smLazyLoad,ToolTip:1};
		initSheet.HeaderMode = {Sort:1,ColMove:1,ColResize:1,HeaderCheck:1};
		initSheet.Cols = [
			{Header:"상태",Type:"Status",SaveName:"STATUS",MinWidth:50, Align:"Center"},
			{Header:"삭제",Type:"DelCheck",SaveName:"DEL_CHK",MinWidth:50},
			{Header:"채팅ID",Type:"Text",SaveName:"chat_Id",MinWidth:50 ,KeyField:1, Edit:false},
			{Header:"발신자",Type:"Text",SaveName:"mem_Id",MinWidth:200 ,MultiLineText:1,UpdateEdit:false},			
			{Header:"수신자",Type:"Text",SaveName:"target_Id",MinWidth:150 ,MultiLineText:1, Wrap:1,UpdateEdit:false},
			{Header:"메세지내용",Type:"Text",SaveName:"message",MinWidth:100,MultiLineText:1, Wrap:1,UpdateEdit:false},
			{Header:"발신날짜",Type:"Date",SaveName:"chat_Date",MinWidth:100},
			{Header:"확인유무",Type:"Text",SaveName:"chat_Stat",MinWidth:100},
		];   
		
		IBS_InitSheet( mySheet , initSheet);

		mySheet.SetEditableColorDiff(1); // 편집불가능할 셀 표시구분
        //mySheet.ShowSubSum([{StdCol:"Release",SumCols:"price",Sort:"asc"}]);
		//doAction('search');
	}
	
	/*Sheet 각종 처리*/
	function doAction(sAction) {
		switch(sAction) {
			case "search": //조회
			    var param = FormQueryStringEnc(document.frm);
				mySheet.DoSearch("${contextPath}/manager/chat/searchList", param);
				//mySheet.DoSearch("transaction_data2.json");
				break;
			case "reload": //초기화
				mySheet.RemoveAll();
				break;
			case "save": // 저장
				//var tempStr = mySheet.GetSaveString();
				//alert("서버로 전달되는 문자열 확인 :"+tempStr);
				mySheet.DoSave("${contextPath}/manager/chat/saveData");
				break;			
			case "insert": //신규행 추가
				var row = mySheet.DataInsert();
				break;
		}
	}
	
	// 조회완료 후 처리할 작업
	function mySheet_OnSearchEnd() {
      
	}
	
	// 저장완료 후 처리할 작업
	// code: 0(저장성공), -1(저장실패)
	function mySheet_OnSaveEnd(code,msg){
		if(msg != ""){
			alert(msg);	
			//번호 다시 매기기
            //mySheet.ReNumberSeq();
		}	
	}	
	
</script>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper" >
	<!-- Content Header (Page header) -->
	<!-- Main content -->
		
		
<body onload="LoadPage()">
  <div class="page_title">
    <span class="title"><b>채팅 관리</b></span>
  </div>
  <div class="main_content">

			<div class="row">
    <div class="exp_product" style="width: 100%;">
      <form name='frm'>
        메세지내용: <input type='text' id="message" name="message" /> 
        발신자: <input type='text' id="mem_Id" name="mem_Id" /> 
        수신자: <input type='text' id="target_Id" name="target_Id" /> 
      </form>
    </div>
    <div class="ib_function float_right">
	  <a href="javascript:doAction('reload')" class="f1_btn_gray lightgray" style="color: black;">초기화</a>
	  <a href="javascript:doAction('insert')" class="f1_btn_gray lightgray" style="color: black; margin-right: 527px;">추가</a>
	  <a href="javascript:doAction('search')" class="f1_btn_white gray">조회</a>
	  <a href="javascript:doAction('save')" class="f1_btn_white gray">저장</a>
	</div>

	<div class="clear hidden"></div>
	<div class="ib_product"><script>createIBSheet("mySheet", "100%", "100%");</script></div>
  </div>
  </div>
</body>
		
		
		</div>

    <!-- /.content -->
  

