/**
 * @license Copyright (c) 2003-2019, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see https://ckeditor.com/legal/ckeditor-oss-license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	config.language = 'ko';
	// config.uiColor = '#AADC6E';
	
	config.toolbarCanCollapse = true;
	config.enterMode =CKEDITOR.ENTER_BR;
	config.height = '300px';
	config.font_names='맑은 고딕/Malgun Gothic; 나눔고딕/나눔고딕, Nanum Gothic, ng, sans-serif;굴림/굴림, Gulim; 돋움/돋움, Dotum; 바탕/바탕, Batang; 궁서/궁서, Gungsuh/Arial/Arial;Tahoma/Tahoma;Verdana/Verdana';
	config.extraAllowedContent = 'video[*]{*};source[*]{*}';		//video , embed 등 막힌 태그를 허용하게 하는 설정
	
	config.extraPlugins = 'youtube, uploadimage'; //유튜브, 이미지 드래그 앤 드롭 추가
	config.youtube_responsive = true; //유튜브 영상 반응형으로
	config.youtube_controls = true; //유튜브 컨트롤 보이기
	
	
	//엑셀 내용 그대로 복사해 오기.
	config.pasteFromWordPromptCleanup = true;
	config.pasteFromWordRemoveStyles = false;
	config.pasteFromWordRemoveFontStyles = false;
	config.allowedContent=true;
	
	config.filebrowserBrowseUrl = "/ckfinder/ckfinder.html";

	config.filebrowserFlashBrowseUrl = "/ckfinder/ckfinder.html?type=Flash";

	config.filebrowserUploadUrl = "/ckfinder/core/connector/java/connctor.java?command=QuickUpload&type=Files";

	config.filebrowserImageUploadUrl = "/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images";

	config.filebrowserFlashUploadUrl = "/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash"; 
	
	
};
