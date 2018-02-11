// JavaScript Document
$(function(){
	//加载关键字搜索
	loadKeyWordSearch();
	
	
	
})
	
	
	
//加载关键字热搜
function loadKeyWordSearch(){
	var url=basePath+'eshop/keyword/loadKeyWordSearch.do';
	jQuery.ajax({
        type: "post",
        data: "",
        url: url,
        dataType: "json",
        cache: false,
        success: function(req){
			if(req!=null){
				if(req.varList!=null && req.varList.length>0){
					var str='';
					$.each(req.varList, function(i,obj){
						if(i==req.varList.length-1){
							str+='<a href="###">'+obj.TITLE+'</a>';
						}else{
							str+='<a href="###">'+obj.TITLE+'</a>&nbsp; ';
						}
					});
					$("#search").html(str);
				}
			}
            
        }
    });
}

//加载关键字热搜
function searchBar(){
	if($("#keywords").val()){
		$("#keywordForm").submit();
		return true;
	}else{
		location.reload(true);
		return false;
	}
}




