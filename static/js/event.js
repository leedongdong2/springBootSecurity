/**
 * 
 */
let today = new Date();
today.setHours(today.getHours() + 9);
const insertAccountDate = document.querySelector("#insertAccountDate");
insertAccountDate.value = today.toISOString().slice(0,10);

const addButton = document.querySelector("#addButton");
const insertForm = document.querySelector("#insertForm");
const modifyButton = document.querySelector("#modifyButton");
const modifyForm = document.querySelector("#modifyForm");
const deleteButton = document.querySelector("#deleteButton");
const deleteForm = document.querySelector("#deleteForm");

addButton.addEventListener("click", function(e){
			const result = confirm("입력 하시겠습니가?");
			
			if( result == true ) {
				insertForm.submit();
			} else if( result == false ) {
				
			}
});

modifyButton.addEventListener("click", function(e){
			const result = confirm("입력 하시겠습니가?");
			
			if( result == true ) {
				modifyForm.submit();
			} else if( result == false ) {
				
			}
});

deleteButton.addEventListener("click", function(e){
			const result = confirm("입력 하시겠습니가?");
			
			if( result == true ) {
				deleteForm.submit();
			} else if( result == false ) {
				
			}
});


welcomeUser.addEventListener("click",function(e){
	console.log("ld");
});


