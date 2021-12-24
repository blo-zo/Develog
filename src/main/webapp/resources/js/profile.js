
/* TAB */
const tabArr = document.getElementsByClassName("tab");

for(let i=0; i < tabArr.length ; i++){
    
    tabArr[i].addEventListener("click", function(){

        for(tab of tabArr){
            tab.classList.remove("select-tab");
        }
        this.classList.add("select-tab");

        contentArr = document.getElementsByClassName("content")

        for(content of contentArr){
            content.style.display = "none";
        }
        contentArr[i].style.display = "block";
		
		// 저장 버튼 없애버리기
		if($(".tab").eq(3).hasClass("select-tab")){
			$(".btn-submit").hide();
		}else{
			$(".btn-submit").show();
		}
    });
} 


