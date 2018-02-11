// JavaScript Document
//搜索延迟。
var outTimeSerach=1000;//延迟时间
var serachTimeOutBoolean = false;
var serachTime = 0;
var serachTimeBoolean = true;
var ajaxRe=null; 
var sttvF=false;
//正在搜索的内容
var serachValue= null;
var event_public;
var inputText_public;
$(function(){
	 //加载商品类型
	 loadShopCMType();
	 $("#keywords").focus(function(event) {
		 sttvF = true;
		 var inputText= $.trim(this.value);
		 if(!inputText){//检测键盘输入的内容是否为空，为空就不发出请求  
        	 //并且将其内容置空
        	 return;
         }
		 sd_search(inputText);
     });
	 $("#keywords").blur(function(){
		 sttvF =false;
		//延迟0.5秒执行。不然无法点击
		 setTimeout(search_clear,500); 
	 });
	 var $searchInput  =  $('#keywords');
	 //
	 $searchInput.keyup(function(event){
		 //先判断是否在两秒钟内已经请求过。如果已经请求过。则延迟到两秒钟之后
		 event_public=event ;
		 inputText_public= $.trim(this.value);
		 //是否之前没有搜索过
		 if(serachTimeBoolean){
			 isCheckSearch();
		 //表示正在延时中	 
		 }else if(serachTimeOutBoolean){
			return;
		 }else{
			 //代表已经采用延时加载
			 serachTimeOutBoolean= true;
			 setTimeout(isCheckSearch,outTimeSerach); 
		 }
     })  
     //
     $searchInput.keypress(function(event){ 
		//enter键 
		if(event.keyCode == 13) { 
			searchBar();
			return false;
		} 
	 })
});

function isCheckSearch(){
	if(serachTimeOutBoolean){
		serachTimeOutBoolean = false;
	}
	var inputText = inputText_public;
	var event=event_public;
    if(inputText==""){//检测键盘输入的内容是否为空，为空就不发出请求  
	   	 //并且将其内容置空
	   	return;
    }else{
	   	if(serachValue == null  || inputText !=serachValue){
	   		 serachValue = inputText;
	   	}else if(inputText ==serachValue){
	   		 return;
	   	}
    }
    //字母数字，退格，空格 
    if(event.keyCode > 40 || event.keyCode == 8 || event.keyCode ==32) { 
	   	if(ajaxRe){  
             // ajaxRe.abort();//如果存在ajax的请求，就放弃请求  
             return false;
	    }else{
	    	 serachTimeBoolean = false;//已经搜索过了
	       	 sd_search(inputText);
	    }  
    }
}


function search_clear(){
	// $(".nf").html("");
	//延迟0.5秒执行。不然无法点击
	$(".nf").css("display","none");
}




	


