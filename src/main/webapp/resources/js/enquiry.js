/*문의등록*/
function insertEnquiry(){
	const title =$("#enquiryTitle").val()
	const content = $("#enquiryContent").val()	
	console.log(title);
	if(title.trim().length == 0){
		alert("문의 제목을 입력하세요");
		$("#enquiryTitle").focus();
		return false;
	}
	if(content.trim().length == 0 ){
		alert("문의사항을 등록해주세요");
		$("#enquiryContent").focus();
		return false;
	}
	if(confirm("문의사항을 등록하시겠습니까?")){
		return;
	}else{
		return false;
	}
}
