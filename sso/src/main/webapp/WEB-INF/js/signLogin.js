// JavaScript Document
$(function(){
	
	$(".signLogin-title li").click(function(){
		 var ind=$(this).index();
		 $(this).addClass("active").siblings().removeClass("active");
		 $(".signLogin-main").children().eq(ind).show().siblings().hide();
		})
	
		if(type==1){
			$("#login").addClass("active").siblings().removeClass("active");
			$("#loginForm").show();
			$("#registerForm").hide();
		}else{
			$("#register").addClass("active").siblings().removeClass("active");
			$("#loginForm").hide();
			$("#registerForm").show();
		}
	
	
	 //获取短信验证码的上一次发送时间
	var lastSendTime = $("#idCodeSSS").val();
	if(lastSendTime){
		var currentTime = $("#currentTime").val();
		var passTime = (currentTime -  lastSendTime) / 1000;
		passTime = parseInt(passTime);
		if( passTime < 60){
			wait = 60 - passTime;
			waitBoolean = false;
			idCodeTime(document.getElementById("code_sendmsg"));
		}
	}
		
	})
	
//登录	
function login(){
	var USERNAME=$("#USERNAME").val();
	if(USERNAME==null || USERNAME==''){
		$("#USERNAME").tips({
			side:3,
            msg:'请输入用户名',
            bg:'#F4A043',
            time:2
        });
		//alert("用户名不能为空!");
		$("#USERNAME").focus();
		return ;
	}
	
	var PASSWORD=$("#PASSWORD").val();
	if(PASSWORD==null || PASSWORD==''){
		$("#PASSWORD").tips({
			side:3,
            msg:'请输入密码',
            bg:'#F4A043',
            time:2
        });
		//alert("密码不能为空!");
		$("#PASSWORD").focus();
		return ;
	}



	console.log("USERNAME:"+USERNAME);
	console.log("PASSWORD:"+PASSWORD);
	console.log("autoLogin:"+autoLogin);

	$(".login-btn-one a").attr('disabled',true);

    $.post("/user/login", $("#loginForm").serialize(),function(data){
        if (data.status == 200) {
            alert("登录成功！");
            if (redirectUrl == "") {
                location.href = "http://localhost:8082/zxjShop.html";
            } else {
                location.href = "http://localhost:8082"+redirectUrl;
            }
        } else {
            alert("登录失败，原因是：" + data.msg);
            $("#loginname").select();
        }
    });

}

var wait=0; 
var waitBoolean = true;
function getIdCode(idCode){
	if(!waitBoolean){
		return false;
	}
	var PHONE=$("#PHONE").val();
	if(PHONE==null || PHONE==''){
		$("#PHONE").tips({
			side:3,
            msg:'请输入手机号码!',
            bg:'#AE81FF',
            time:2
        });
		$("#PHONE").focus();
		return ;
	}

	wait = 60;
	waitBoolean= false;
	idCodeTime(idCode);

}
function idCodeTime(o) { 
	if (wait <= 0) { 
		o.removeAttribute("disabled"); 
		o.innerHTML ="发送短信验证码"; 
		wait = 60; 
		waitBoolean= true;
	} else { 
		//o.setAttribute("disabled", "disabled"); 
		//o.attr("disabled",true);
		o.innerHTML ="重新发送(" + wait + ")"; 
		//o.attr("style","color:#888888");
		wait--; 	
		setTimeout(function(){ 
			idCodeTime(o) 
		}, 1000) 
    }	
} 
function toRegister(){

		var PHONE=$("#PHONE").val();
		var name = "DW"+PHONE;
		if(PHONE==null || PHONE==''){
			$("#PHONE").tips({
				side:3,
				msg:'请输入手机号码',
				bg:'#F4A043',
				time:2
			});

			$("#PHONE").focus();
			return ;
		}
	 	var reg0 = /^1[3|4|5|6|7|8]\d{9}$/;
		if(!reg0.test(PHONE)){
			$("#PHONE").tips({
				side:3,
	            msg:'手机号码格式不正确',
	            bg:'#F4A043',
	            time:2
	        });
			$("#PHONE").focus();
			//alert("手机号码格式不正确!");
			return;
		}
		//进行验证码判断
		var idCode2="1111";//发送手机上的验证码
		var idCode=$("#idCode").val();//用户输入的验证码
		if(idCode==null || idCode==''){
			$("#idCode").tips({
				side:3,
	            msg:'请输入验证码',
	            bg:'#F4A043',
	            time:2
	        });
			//alert('验证码不能为空!');
			$("#idCode").focus();
			return ;
		}
		if(idCode!=idCode2){
			$("#idCode").tips({
				side:3,
	            msg:'验证码错误',
	            bg:'#F4A043',
	            time:2
	        });
			$("#idCode").focus();
			return ;
		}
		
				
		
		var PASSWORD=$("#PASSWORD2").val();
		if(PASSWORD==null || PASSWORD==''){
			$("#PASSWORD2").tips({
				side:3,
				msg:'请输入密码',
				bg:'#F4A043',
				time:2
			});
			$("#PASSWORD2").focus();
			return ;
		}
		var biganPASSWORD=$("#biganPASSWORD").val();
		if(biganPASSWORD==null || biganPASSWORD==''){
			$("#biganPASSWORD").tips({
				side:3,
				msg:'请输入确认密码',
				bg:'#F4A043',
				time:2
			});
			//alert('重复密码不能为空');
			$("#biganPASSWORD").focus();
			return;
		}
		if(biganPASSWORD!=PASSWORD){
			$("#biganPASSWORD").tips({
				side:3,
				msg:'密码不一致',
				bg:'#F4A043',
				time:2
			});
			//alert("密码不一致!");
			$("#biganPASSWORD").focus();
			return ;
		}

		//var a=$("#sign-promise").val();
		var a=$('input[name="xieyi"]:checked').val();
		if(a==null || a==''){
			alert("请阅读协议!");
			return ;
		}
		//执行注册
		$.post("/user/register",$("#registerForm").serialize(), function(data){
			if(data.status == 200){
				alert('用户注册成功，请登录！');
				location.href = "/page/login";
				return false;
			} else {
				alert("注册失败！");
			}
		});

     
}


	//验证用户名是否存在
	function checkName(){
		
		var name=$("#NAME_input input").val().trim();
		console.log('name:'+name);
		var testName=/^[a-zA-Z][a-zA-Z0-9_]{2,20}$/;
		if(name==''){
			$("#NAME_input input").tips({
				side:3,
	            msg:'用户名不能为空',
	            bg:'#F4A043',
	            time:2
			});
			$("#NAME_input input").focus();
			return;
		}else{
			if(!testName.test(name)){
				$("#NAME_input input").tips({
					side:3,
		            msg:'用户名必须以字母开头,至少3位,最多20位',
		            bg:'#F4A043',
		            time:2
				});
				$("#NAME_input input").focus();
				return;
			}else{//后台检查用户名
				var url=basePath+'eshop/loginController/checkUserName.do';
				var params={
						USERNAME:name
				};
				$(function(){
					$.ajax({
						type: "post",
				        data: params,
				        url: url,
				        dataType: "json",
				        cache: false,
				        success: function(result){
				        	var errorInfo=result['result'];
				        	if('usererror'==errorInfo){
				        		$("#NAME_input input").tips({
				    				side:3,
				    	            msg:'用户名已经存在',
				    	            bg:'#F4A043',
				    	            time:2
				    			});
				    			$("#NAME_input input").val('');
				    			$("#NAME_input input").focus();
				    			return;
				        	}else if('error'==errorInfo){//操作不合法
				        		
				        	}else if(errorInfo='success'){
				        		$("#NAME_input input").tips({
				    				side:3,
				    	            msg:'用户名可以使用',
				    	            bg:'#006400',
				    	            time:2
				    			});
				        	}
				        }
					});
				});
			}
		}
	}




function checkPhone(){
	var PHONE=$("#PHONE").val();
	if(PHONE==null || PHONE==''){
		$("#PHONE").tips({
			side:3,
            msg:'请输入手机号码',
            bg:'#F4A043',
            time:2
        });
		//alert("手机号码不能为空!");
		$("#PHONE").focus();
		return ;
	}
	var reg0 = /^1[3|4|5|6|7|8]\d{9}$/;
	if(!reg0.test(PHONE)){
		$("#PHONE").tips({
			side:3,
            msg:'手机号码格式不正确',
            bg:'#F4A043',
            time:2
        });
		$("#PHONE").focus();
		//alert("手机号码格式不正确!");
		return;
	}
}



