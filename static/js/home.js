/**
 * 
 */

 const welcomeUser = document.querySelector(".welcomeUser");
 const deactivate =document.querySelector(".deactivate");
 const deleteIdForm = document.querySelector("#deleteIdForm");
 const pageButton = document.querySelectorAll(".pageButton");
 const nowPage = document.querySelector("#nowPage");
 const searchForm = document.querySelector("#searchForm");
 const previousButton = document.querySelector("#previousButton");
 const firstPageButton = document.querySelector("#firstPageButton");
 const nextButton = document.querySelector("#nextButton");
 const lastPageButton = document.querySelector("#lastPageButton");
 
 
 welcomeUser.addEventListener("click",function(e){
	if(deactivate.style.visibility == "" || deactivate.style.visibility == "hidden") {
		deactivate.style.visibility = "visible";
	} else {
		deactivate.style.visibility = "hidden";
	}
 });
 
 deactivate.addEventListener("click",function(e){
	 const result = confirm("모든 데이터가 사라집니다 정말 삭제하시겠습니가?");
	 if(result == true){
		 deleteIdForm.submit();
	 } else if(result == false) {
		 return;
	 }
 })
 
 
 pageButton.forEach( function(pageButton) {
	pageButton.addEventListener("click", function(e){
		nowPage.value = e.target.value;
		searchForm.submit();
	});
});

 previousButton.addEventListener("click",function(e){
	 nowPage.value = e.target.value;
	 searchForm.submit();
 }) 
 
  firstPageButton.addEventListener("click",function(e){
	 nowPage.value = e.target.value;
	 searchForm.submit();
 })
  
  nextButton.addEventListener("click",function(e){
	 nowPage.value = e.target.value;
	 searchForm.submit();
 }) 
 
  lastPageButton.addEventListener("click",function(e){
	 nowPage.value = e.target.value;
	 searchForm.submit();
 }) 

 
 /******어드민*********/
 const adminForm = document.querySelector("#adminForm");
 const adminNowPaeg = document.querySelector("#adminNowPage");
 const adminPageButton = document.querySelectorAll(".adminPageButton");
 const adminPreviousButton = document.querySelector("#previousButton");
 const adminFirstPageButton = document.querySelector("#firstPageButton");
 const adminNextButton = document.querySelector("#nextButton");
 const adminLastPageButton = document.querySelector("#lastPageButton");
 
 adminPageButton.forEach(function(adminPageButton){
	 adminPageButton.addEventListener("click",function(e){
		adminNowPaeg.value = e.target.valeu;
		adminForm.submit();
	 })
 })
 
  adminPreviousButton.addEventListener("click",function(e){
	 adminNowPaeg.value = e.target.value;
	 adminForm.submit();
 }) 
 
  adminFirstPageButton.addEventListener("click",function(e){
	 adminNowPaeg.value = e.target.value;
	 adminForm.submit();
 })
  
  adminNextButton.addEventListener("click",function(e){
	 adminNowPaeg.value = e.target.value;
	 adminForm.submit();
 }) 
 
  adminLastPageButton.addEventListener("click",function(e){
	 adminNowPaeg.value = e.target.value;
	 adminForm.submit();
 }) 