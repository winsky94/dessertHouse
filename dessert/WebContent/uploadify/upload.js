$(function() {
	size=0;
	$('#picture').uploadify({
		'auto'	: false,//设置auto为true，当文件被添加至上传队列时，将会自动上传
		'swf'      : 'uploadify/uploadify.swf',//指定上传控件的主体文件
		'uploader' : 'api/plan/add',//指定服务器端上传处理文件
		"method":"post",
		"fileObjName":"uploadFiles",
		'multi': false,
		'fileTypeDesc':'上传文件支持的文件格式:jpg,jpeg,png',
		'fileTypeExts':'*.jpg;*.jpeg;*.png',
		'queueID': 'filequeue',
		'queueSizeLimit' : 999,  //队列最多有多少个
		'buttonCursor' : 'hand',  //'hand'(手形) 和 'arrow'(箭头)
		'buttonText' : '选择图片',   //定义显示在默认按钮上的文本。
		//'checkExisting' : './swf/check-exists.php',//定义检查目标文件夹中是否存在同名文件的脚本文件路径    
		'debug' : false, //当其值为true时，开启SWFUpload调试模式。		                
		//'fileSizeLimit' : '900KB',//大小
		'progressData' : 'percentage', //设置显示在上传进度条中的数据类型，可选项时百分比（percentage）或速度（speed）                
		'successTimeout' : 30, //参数表示uploadify的成功等待时间（单位：秒，默认30秒）
		'onClearQueue' : function(queueItemCount) {
		    // alert('队列中'+queueItemCount + ' 个文件被清空。');
		},
		'onFallback' : function() {
		    alert('未检测到兼容版本的Flash.');
		},
		'onSelect' : function(file) { //每添加一个文件至上传队列时触发该事件
			if(size==1){
				$('#picture').uploadify('cancel')
			}else{
				size=size+1;
			}
			$('#picture').uploadify('settings','buttonText',file.name);
			$("#picName").val(file.name);
		    // alert(file.name + ' 文件添加至上传队列.');
		},                
		'onSelectError':function(file,errorCode){
			//QUEUE_LIMIT_EXCEEDED – 选定的文件数量超过了设置的限值
            //FILE_EXCEEDS_SIZE_LIMIT – 文件的大小超过了设置的限值
            //ZERO_BYTE_FILE – 0大小文件
            //INVALID_FILETYPE – 文件类型不在设置的范围内

            //alert(file.name + '文件返回错误，没有添加到上传队列中.');
        },
        'onCheck':function(file){
           	//aler('文件'+file+'队列中已存在。');
        },
        //当队列中的所有文件全部完成上传时触发
        'onQueueComplete' : function(stats) {
        	// alert(stats);
        },
        'onUploadSuccess':function(file, data, response){
        	//每个文件上传成功后都会触发
        	// alert("success");
        	$(".after").remove();
        	var jsonObject = jQuery.parseJSON(data);
            if(jsonObject.message!=""){
            	$("#process_result").html(jsonObject.message);
            }else{
//            	 alert("success，界面跳转，刷新界面");
            	location.reload();
            }
        },
        'onUploadError' : function(file, errorCode, errorMsg, errorString) {
            //file
            //上传的文件对象。

            //errorCode
            //返回的错误代码。

            //errorMsg
            //返回的错误信息。

            //errorString
            //错误详细信息（字符串）
            //alert(file.name + ' 上传失败。详细信息: ' + errorString);
        }
	});
});