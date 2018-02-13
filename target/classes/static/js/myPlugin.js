	/**
	 * web根目录
	 */
	jQuery.extend({
		contextPath:function(){
			return $("input[type='hidden'][name='contextPath']").val();
		}
	});
	
	/**
	 * 扩展分页
	 */
	$.extend($.fn.pagination.defaults, {
		
	});
		
	/**
	 * 消息提醒，默认在中间显示
	 * @param 消息内容
	 */
	function message(msg){
		$.messager.show({
			title:'温馨提示',
			msg:msg,
			showType:'slide',
			timeout:2000,
			style:{
				right:'',
				top:'20px',
				bottom:''
				}
			});
	}
		//确认框
		 $.messager.defaults.ok="确定";
		 $.messager.defaults.cancel="取消";
   		function confirm(title,content,url, callback) { 
   				$.messager.confirm(title, content, function(r){
   					if(callback){
   						//传入了回调函数
   						callback(r);
   					}else{
   						//没有传入回调函数，执行跳转
   						if(r){
   	   			    		window.location=$.contextPath()+url;
   	   			    	}
   					}
   			    	
   			    }); 
	    return false;
   	  }
   		
   		
   		
   	  /*
   	   * 提醒框
   	   */
   		function alert(content,title){
   			if(title == undefined){
   				title="温馨提示"
   			}
   			$.messager.alert(title,"<pre>"+content+"</pre>");
   		}
   		
   		
   		/**
   		 * 提示框
   		 * 
   		 */
   		function show(param){
   			$.messager.show({
   				title:param.title?param.title:'温馨提示',
   				msg:param.msg?param.msg:'默认提示信息',
   				timeout:param.timeout?param.timeout:2000,
   				showType:param.showType?param.showType:'slide'
   			});

   		}

   		
   		
   		/**扩展easyUI校验**/
   		$.extend($.fn.validatebox.defaults.rules, {
   		    minLength : { // 判断最小长度
   		        validator : function(value, param) {
   		            return value.length >= param[0];
   		        },
   		        message : "最少输入 {0} 个字符。"
   		    },
   		    length:{validator:function(value,param){
   		        var len=$.trim(value).length;
   		            return len>=param[0]&&len<=param[1];
   		        },
   		            message:"{2}"
   		        },
   		    phone : {// 验证电话号码
   		        validator : function(value) {
   		            return /^(((d{2,3}))|(d{3}-))?((0d{2,3})|0d{2,3}-)?[1-9]d{6,7}(-d{1,4})?$/i.test(value);
   		        },
   		        message : "格式不正确,请使用下面格式:020-88888888"
   		    },
   		    mobile : {// 验证手机号码
   		        validator : function(value) {
   		            return /^1[3|5|7|8|][0-9]{9}?$/i.test(value);
   		        },
   		        message : "手机号码格式不正确"
   		    },
   		    idcard : {// 验证身份证
   		        validator : function(value) {
   		            return /^d{15}(d{2}[A-Za-z0-9])?$/i.test(value);
   		        },
   		        message : "身份证号码格式不正确"
   		    },
   		    intOrFloat : {// 验证整数或小数
   		        validator : function(value) {
   		            return /^d+(.d+)?$/i.test(value);
   		        },
   		        message : "请输入数字，并确保格式正确"
   		    },
   		    currency : {// 验证货币
   		        validator : function(value) {
   		            return /^d+(.d+)?$/i.test(value);
   		        },
   		        message : "货币格式不正确"
   		    },
   		    qq : {// 验证QQ,从10000开始
   		        validator : function(value) {
   		            return /^[1-9]d{4,9}$/i.test(value);
   		        },
   		        message : "QQ号码格式不正确"
   		    },
   		    integer : {// 验证整数
   		        validator : function(value) {
   		            return /^[+]?[1-9]+d*$/i.test(value);
   		        },
   		        message : "请输入整数"
   		    },
   		    chinese : {// 验证中文
   		        validator : function(value) {
   		            return /^[u0391-uFFE5]+$/i.test(value);
   		        },
   		        message : "请输入中文"
   		    },
   		    english : {// 验证英语
   		        validator : function(value) {
   		            return /^[A-Za-z]+$/i.test(value);
   		        },
   		        message : "请输入英文"
   		    },
   		    unnormal : {// 验证是否包含空格和非法字符
   		        validator : function(value) {
   		            return /.+/i.test(value);
   		        },
   		        message : "输入值不能为空和包含其他非法字符"
   		    },
   		    username : {// 验证用户名
   		        validator : function(value) {
   		            return /^[a-zA-Z][a-zA-Z0-9_]{5,15}$/i.test(value);
   		        },
   		        message : "用户名不合法（字母开头，允许6-16字节，允许字母数字下划线）"
   		    },
   		    faxno : {// 验证传真
   		        validator : function(value) {
//   		            return /^[+]{0,1}(d){1,3}[ ]?([-]?((d)|[ ]){1,12})+$/i.test(value);
   		            return /^(((d{2,3}))|(d{3}-))?((0d{2,3})|0d{2,3}-)?[1-9]d{6,7}(-d{1,4})?$/i.test(value);
   		        },
   		        message : "传真号码不正确"
   		    },
   		    zip : {// 验证邮政编码
   		        validator : function(value) {
   		            return /^[1-9]d{5}$/i.test(value);
   		        },
   		        message : "邮政编码格式不正确"
   		    },
   		    ip : {// 验证IP地址
   		        validator : function(value) {
   		            return /d+.d+.d+.d+/i.test(value);
   		        },
   		        message : "IP地址格式不正确"
   		    },
   		    name : {// 验证姓名，可以是中文或英文
   		            validator : function(value) {
   		                return /^[u0391-uFFE5]+$/i.test(value)|/^w+[ws]+w+$/i.test(value);
   		            },
   		            message : "请输入姓名"
   		    },
   		    carNo:{
   		        validator : function(value){
   		            return /^[u4E00-u9FA5][da-zA-Z]{6}$/.test(value);
   		        },
   		        message : "车牌号码无效（例：粤J12350）"
   		    },
   		    carenergin:{
   		        validator : function(value){
   		            return /^[a-zA-Z0-9]{16}$/.test(value);
   		        },
   		        message : "发动机型号无效(例：FG6H012345654584)"
   		    },
   		    email:{
   		        validator : function(value){
   		        return /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/.test(value);
   		    },
   		    message : "请输入有效的电子邮件账号(例：abc@126.com)"
   		  
   		    },
   		    msn:{
   		        validator : function(value){
   		        return /^w+([-+.]w+)*@w+([-.]w+)*.w+([-.]w+)*$/.test(value);
   		    },
   		    message : "请输入有效的msn账号(例：abc@hotnail(msn/live).com)"
   		    },same:{
   		        validator : function(value, param){
   		            if($("#"+param[0]).val() != "" && value != ""){
   		                return $("#"+param[0]).val() == value;
   		            }else{
   		                return true;
   		            }
   		        },
   		        message : "两次输入的密码不一致！"   
   		    },
   		    
   		 uniqueLoginName:{
		        validator : function(value, param){
		        	var flag = true;
		        	$.ajax({
		        		   type: "POST",
		        		   url: param[0],
		        		   async: false,
		        		   data: {'id':$(":hidden[name='id']").val(),'loginName':value},
		        		   success: function(data){
		        			   flag = data;
		        		   },error:function(data){
		        			   console.dir(data);
		        		   },
		        		   dataType:"json"
		        		});
		        	return flag;
		        },
		        message : "{1}"   
		    },
		    uniqueActivityId:{
		        validator : function(value, param){
		        	var flag = true;
		        	/*$.post(param[0],{"loginName":value},function(data){
		        		flag = data;
		        		isFinish = false;
		        		alert(isFinsh)
		    		},"json");*/
		        	$.ajax({
		        		   type: "POST",
		        		   url: param[0],
		        		   async: false,
		        		   data: {'activityId':value},
		        		   success: function(data){
		        			   flag = data;
		        		   },error:function(data){
		        			   console.dir(data);
		        		   },
		        		   dataType:"json"
		        		});
		        	return flag;
		        },
		        message : "{1}"   
		    },
		    
		    uniquePayGateCode:{
		        validator : function(value, param){
		        	var flag = true;
		        	$.ajax({
		        		   type: "POST",
		        		   url: param[0],
		        		   async: false,
		        		   data: {'code':value,'id':$("input[type='hidden'][name='id']").val()},
		        		   success: function(data){
		        			   flag = data;
		        		   },error:function(data){
		        		   },
		        		   dataType:"json"
		        		});
		        	return flag;
		        },
		        message : "{1}"   
		    },
		    uniqueEventName:{
		        validator : function(value, param){
		        	var flag = true;
		        	$.ajax({
		        		   type: "POST",
		        		   url: "event_checkName.do",
		        		   async: false,
		        		   data: {'name':value},
		        		   success: function(data){
		        			   flag = data.flag;
		        		   },error:function(data){
		        			   console.dir(data);
		        		   },
		        		   dataType:"json"
		        		});
		        	return flag;
		        },
		        message : "事件名已被占用"   
		    },
		    uniqueChannelId:{
		        validator : function(value, param){
		        	var flag = true;
		        	$.ajax({
		        		   type: "POST",
		        		   url: param[0],
		        		   async: false,
		        		   data: {'channelId':value,'id':$("input[type='hidden'][name='id']").val()},
		        		   success: function(data){
		        			   flag = data;
		        		   },error:function(data){
		        		   },
		        		   dataType:"json"
		        		});
		        	return flag;
		        },
		        message : "{1}"   
		    },
		    uniqueGameId:{
		        validator : function(value, param){
		        	var flag = true;
		        	$.ajax({
		        		   type: "POST",
		        		   url: param[0],
		        		   async: false,
		        		   data: {'gameId':value,'id':$("input[type='hidden'][name='id']").val()},
		        		   success: function(data){
		        			   flag = data;
		        		   },error:function(data){
		        		   },
		        		   dataType:"json"
		        		});
		        	return flag;
		        },
		        message : "{1}"   
		    },
		    checkPassword:{
		        validator : function(value, param){
		        	var flag = true;
		        	$.ajax({
		        		   type: "POST",
		        		   url: param[0],
		        		   async: false,
		        		   data: {'password':value},
		        		   success: function(data){
		        			   flag = data;
		        			   if(flag){
		        				   //密码正确
		        				   $("#rightIcon").show("fadeout");
		        				   $("#passwordTip").show("fadeout");
		        			   }else{
		        				   $("#rightIcon").hide("fadein");
		        				   $("#passwordTip").hide("fadein");
		        			   }
		        		   },error:function(data){
		        		   },
		        		   dataType:"json"
		        		});
		        	return flag;
		        },
		        message : "{1}"   
		    },
		    requiredCombobox:{
		        validator : function(value, param){
		        	return value!=param[0];
		        },
		        message : "{1}"   
		    },
		    /**
		     * 接受后缀名
		     */
		    accept:{
		        validator : function(value, param){
		        	var flag = false;
		        	for(var i = 0; i < param.length; i++){
		        		if(value.endWith(param[i])){
		        			flag = true;
		        			break;
		        		}
		        	}
		        	return flag;
		        },
		        message : "不合法的文件类型"   
		    }
   		    
   		    
   		});
   		
   		/**
   		 * 移除/还原验证
   		 * 
   		 */
   		$.extend($.fn.validatebox.methods, {
   			//移除验证
   		    remove: function (jq, newposition) {
   		        return jq.each(function () {
   		            $(this).removeClass("validatebox-text validatebox-invalid").unbind('focus').unbind('blur');
   		        });
   		    },
   		    //还原验证
   		    reduce: function (jq, newposition) {
   		        return jq.each(function () {
   		            var opt = $(this).data().validatebox.options;
   		            $(this).addClass("validatebox-text").validatebox(opt);
   		        });
   		    }
   		}); 
   		
   		/**
   		 * 修改datetimebox的按钮为中文
   		 */
   		$.extend($.fn.datetimebox.defaults,{
   			closeText:'取消',
   			okText:'确定',
   			currentText:'今天'
   		})
   		
   		/**
   		 * 扩展String prorotype,以什么结尾方法
   		 * @param endStr
   		 * @returns {Boolean}
   		 */
   		String.prototype.endWith=function(endStr){
   			var d=this.length-endStr.length;
   			return (d>=0&&this.lastIndexOf(endStr)==d)
   		}

   	// 对Date的扩展，将 Date 转化为指定格式的String
   	// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
   	// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
   	// 例子： 
   	// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
   	// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
   	Date.prototype.Format = function (fmt) { //author: meizz 
   	    var o = {
   	        "M+": this.getMonth() + 1, //月份 
   	        "d+": this.getDate(), //日 
   	        "h+": this.getHours(), //小时 
   	        "m+": this.getMinutes(), //分 
   	        "s+": this.getSeconds(), //秒 
   	        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
   	        "S": this.getMilliseconds() //毫秒 
   	    };
   	    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
   	    for (var k in o)
   	    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
   	    return fmt;
   	}
  // 	调用： 

   //	var time1 = new Date().Format("yyyy-MM-dd");
   //	var time2 = new Date().Format("yyyy-MM-dd HH:mm:ss"); 