<%@page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/views/common/jsp/common-doctype-taglib.jspf"%><%--doctype과 taglib 정의 --%>
<html>
<head>
	<%@ include file="/WEB-INF/views/common/jsp/common-javascript-css.jspf"%><%--javascript과 css 정의 --%>
	<%@ include file="/WEB-INF/views/common/jsp/common-ibsheetibchart.jspf"%><%--ibsheet ibchart 정의 --%>
	<script>
		var mySheet = new ibsheetObject();
		
		$(document).ready(function(){
			//ibsheet 객체 생성  ( 시트가 위치할 element  , 시트 이름 ,    너비     ,   높이           )
			ib.comm.createIBSheet( document.getElementById("ibsheet_div"), "mySheet" , "100%" , "100%");

			//시트 초기화 변수
			var initSheet = {};
			initSheet.Cfg = {};
			initSheet.HeaderMode = {};
			initSheet.Cols = [
			            {Header:"상태",Type:"Status",Width:60,SaveName:"sStatus",Align:"Center"},
			            {Header:"삭제",Type:"DelCheck",Width:60,SaveName:"sDelete",Align:"Center"},
			            {Header:"직책",Type:"Text",Width:100,SaveName:"job",Align:"Center"},
			            {Header:"부서코드",Type:"Combo",Width:100,SaveName:"deptno",Align:"Center",ComboText:"${DNAME}",ComboCode:"${DEPTNO}",PopupText:"${DNAME}"},
						
			            {Header:"ID",Type:"Text",Width:60,SaveName:"empno",Align:"Center",KeyField:1,DefaultValue:'',UpdateEdit:0},
// 			            {Header:"사진",Type:"Image",Width:100,SaveName:"pic",Align:"Center"},
			            {Header:"이름",Type:"Text",Width:150,SaveName:"ename",Align:"Center"},
			            {Header:"입사일",Type:"Date",Width:120,SaveName:"hiredate",Format:"Ymd",Align:"Center",EditLen:8},
			            {Header:"책임자",Type:"Text",Width:120,SaveName:"mgr",Align:"Center"},
			            {Header:"급여",Type:"Int",Width:120,SaveName:"sal",Align:"Right",Format:"NullInteger"},
			            {Header:"COMM",Type:"Int",Width:60,SaveName:"comm",Align:"Right",Format:"Integer"}
			            ];
			//시트 초기화
			ib.comm.IBS_InitSheet(mySheet,initSheet);

		});
		
		
		function doAction(str){
			switch(str){
				case "search":
					
					//데이터 조회
					//공통 조회 함수
					var param = {url:"<c:url value='/sheet/SearchSave/empSearch.do'/>"
						,subparam:FormQueryStringEnc(document.frm)   //폼객체 안에 내용을 QueryString으로 바꾼다.
						,sheet:mySheet
						,mapping:{mySheet:"SHEETDATA1"}
					};
					ib.comm.search( param );
					break;
				case "save":
 					var param = {url:"<c:url value='/sheet/SearchSave/empSave.do'/>"
 						,subparam:FormQueryStringEnc(document.frm)+"aaa=1234&aaa=34567"
 						//,callback:function(){var a = 1 + 3 ;alert(a);}
						,sheet:"mySheet"};
 					ib.comm.save( param );

					break;
				case "insert":
					mySheet.DataInsert();					
					break;
				case "exceldown":
					mySheet.Down2Excel({SheetDesign:1,FileName:"myWork.xlsx",KeyFieldMark:0,Multipart:0});
					var param = {
							id:"mySheet"
							,name:"BASIC 화면"
							,title:"BASIC SHEET DOWNLOADED"
					};
					ib.comm.downExcel(param);
					
					break;
				case "excelupload":
					var param = {
							id:"mySheet"
					};
					ib.comm.loadExcel(param);
					break;

				case "pdfdown":
					mySheet.Down2Pdf({FileName : "basicSample.pdf",Paper:"landscape","DPI":2400});
					break;
			}
		}

	</script>
</head>
<body >
<div id="main">
	
	<jsp:include page="../common/jsp/sheetNavi.jsp" flush="true"/>
	<div id="title">기본 조회 / 저장 예제</div>
	<div id="desc">
		페이지 네비게이션을 통한 이동 방법을 확인해 본다.
	</div>
	
	<div id="functionArea">
		<form name="frm">
			<select id="frm_NameOrId"><option value="">--</option><option value="name">이름</option><option value="id">ID</option></select>
	 		<input type="text" name="frm_SearchText">
	 	</form>
	</div>
	<div id="buttonArea">
		<button type="button" onclick="doAction('exceldown')">엑셀다운</button>
		<button type="button" onclick="doAction('excelupload')">엑셀업로드</button>
		<button type="button" onclick="doAction('search')">조회</button>
		<button type="button" onclick="doAction('insert')">추가</button>
		<button type="button" onclick="doAction('save')">저장</button>
	</div>
	
	<!-- ibsheet 객체 -->
	<div class="ib_product" style="height:calc(100% - 215px);">
		<div id="ibsheet_div"></div>
	</div>
</div><!-- main end -->
</body>
</html>