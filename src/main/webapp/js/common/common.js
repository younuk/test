var REQUEST_URI = window.location.pathname.substr(1);

/* set message */
var _REG_SUCCESS = "저장 성공";
var _REG_FAIL = "저장 실패";
var _MOD_SUCCESS = "수정 성공";
var _MOD_FAIL = "수정 실패";
var _REG_CONFIRM = "저장 하시겠습니까?";
var _DEL_CONFIRM = "삭제 하시겠습니까?";
var _REQUIRED = "◁ 필수입력";
var _ONLYNUMBER = "◁ 숫자입력";

var datepickerOption = {
		dateFormat: "yy-mm-dd",
		showOn: 'both',
	    monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	    monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	    dayNames: ['일','월','화','수','목','금','토'],
	    dayNamesShort: ['일','월','화','수','목','금','토'],
	    dayNamesMin: ['일','월','화','수','목','금','토'],
		buttonImage: "/images/contents/calendar_icon.png",
	    buttonImageOnly : true,
	    changeMonth: true,
	    changeYear: true,
	    constrainInput:true,
	    yearRange: 'c-70:c+1'
	}

var common = {
	isObj: function(obj) {
		if(obj != "" && obj != null && typeof(obj) != "undefined") {
			return true;
		} else {
			return false;
		}
	},
	redirect: function(url) {
		if(this.isObj) {
			$(location).attr("href", url);
		}
	},
	redirectSubmit: function(form, url) {
		if(this.isObj) {
			commonForm.SUBMIT = true;
			$(form).attr("action", url).submit();
		}
	},
	getQueryString: function(key) {
		if(this.isObj(key)) {
			var url = window.location.search.substring(1);
			var param = url.split('&');
			for (var i = 0; i < param.length; i++) {
				var  sparam = param[i].split('=');
				if (sparam[0] == key) {
					return sparam[1];
				}
			}
			return "";
		} else {
			var url = window.location.search.substring(1);
			var param = url.split('&');
			return param;
		}
	},
	setQueryString: function(param, param2) {
		var cnt = 0;
		var queryString = "";
		if(this.isObj(param) && param.length > 0) {
			for(var i = 0; i<param.length; i++) {
				if(this.isObj(param[i])) {
					var sparam = this.getQueryString(param[i]);
					if(this.isObj(sparam)) {
						if(cnt == 0) {
							queryString += "?"
						} else {
							queryString += "&"
						}
						queryString += param[i] + "=" + sparam;
						cnt++;
					}
				}
			}
		}
		if(this.isObj(param)) {
			for(var i in param2) {
				if(this.isObj(param2[i])) {
					if(cnt == 0) {
						queryString += "?"
					} else {
						queryString += "&"
					}
					queryString += i + "=" + param2[i];
					cnt++;
				}
			}
		}
		return queryString;
	}
};


var commonForm = {
	SUBMIT : false,

	/**
	 * form submit 설정
	 */
	submit: function() {
		return this.SUBMIT;
	}
};

var commonPage = {
	fnGoList: function(paramNo) {
		 if(!isNull(paramNo))  $("#pageIndex").val(paramNo);
	    commonForm.SUBMIT = true;
	    $("#listForm").submit();
	}
};

function isNull(obj){
	return (typeof obj != "undefined" && obj != null && obj != "")? false: true;
}

function checkValidationArr(objArr, msgArr){
	for(var idx in objArr){
		if(!checkValidation(objArr[idx], msgArr[idx])){
			return false;
		}
	}
	return true;
}

function checkValidation(obj, msg){
	if(isNull($(obj).val())){
		alert(msg+"을/를 입력하세요.");
		$(obj).focus();
        return false;
	}else{
		return true;
	}
}

function makePopupOpt(paramW, paramH){
	var winPosTop  = (screen.height - paramH) / 2;
	var winPosLeft = (screen.width - paramW) / 2;

	 return winOpt = "width="+paramW+",height="+paramH+",top="+winPosTop+",left="+winPosLeft
	 				  + ",menubar=no,status=no,scrollbars=no,resizable=no,location=no";
}

function makePopupOptScroll(paramW, paramH){
	var winPosTop  = (screen.height - paramH) / 2;
	var winPosLeft = (screen.width - paramW) / 2;

	 return winOpt = "width="+paramW+",height="+paramH+",top="+winPosTop+",left="+winPosLeft
	 				  + ",menubar=no,status=no,scrollbars=yes,resizable=no,location=no";
}

function getUnixTimeStamp(){
	return Math.floor(new Date().getTime() / 1000);
}

function getTimeStamp() {
	var d = new Date();
	var s = leadingZeros(d.getFullYear(), 4) + '-' +
			leadingZeros(d.getMonth() + 1, 2) + '-' +
			leadingZeros(d.getDate(), 2) + ' ' +

			leadingZeros(d.getHours(), 2) + ':' +
			leadingZeros(d.getMinutes(), 2) + ':' +
			leadingZeros(d.getSeconds(), 2);
  return s;
}

function leadingZeros(n, digits) {
	var zero = '';
	n = n.toString();

	if (n.length < digits) {
		for (i = 0; i < digits - n.length; i++)
			zero += '0';
	}
	return zero + n;
}

function cmmFnMakeChartDataset(paramChartArr, paramY, paramDataArr){
	var datasets = [];
    var y_val;
    for(var idx in paramChartArr){
        var dataset = [];

        dataset.label = paramChartArr[idx];
        dataset.fill = false;
        dataset.backgroundColor = _CHART_BG_COLOR[idx];
        dataset.borderColor = _CHART_BG_COLOR[idx];
        dataset.data = [];

        for(var j in paramY){
            y_val = paramY[j];
            dataset.data.push(paramDataArr[dataset.label][y_val]);
        };
        datasets.push(dataset);
    }
    return datasets;
}

function cmmChkPwd(param){
	var pw = $(param).val();
	var num = pw.search(/[0-9]/g);
	var eng = pw.search(/[[a-zA-Z]/g);
	/*var spe = pw.search(/[`~!@@#$%^&*|\\\'\";:₩/?]/gi);*/
	var spe = pw.search(/[~!@#$%^&*()_+|<>?:{}]/gi);
	var msg = "비밀번호는 문자, 숫자, 특수문자의 조합으로 최소 8자 이상이어야 합니다.";

	if(pw.search(/₩s/) != -1){
		alert("비밀번호는 공백업이 입력해주세요.");
		$(param).val("");
		$(param).focus();
		return false;
	}else if((pw.length < 8 ) || (num < 0 || eng < 0 || spe < 0 )){
		alert(msg);
		$(param).val("");
		$(param).focus();
		return false;
	}
	return true;
}

function cmmChkId(param){
	var id = $(param).val();
	var regex = /^[a-zA-Z0-9]{8,20}$/; // 아이디 검사식

	if(!regex.test(id)) {
		alert("[아이디 생성 규칙]\n아이디는 아래의 규격과 일치해야 합니다.\n- 영문, 숫자 혼용 8자 이상 20자 이내\n- 특수문자제외");
		$(param).val("");
		$(param).focus();
		return false;
	}
	return true;
}

function cmmChkEmail(paramObj){
	var email = $(paramObj).val();
	if(!isNull(email)){
		var regex=/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;

	    if(!regex.test(email)) {
	        alert("[이메일 규칙]\n이메일 형식을 확인해 주세요.");
	        $(paramObj).val("");
	        $(paramObj).focus();
	        return false;
	    }
	}
	return true;
}

function cmmPopup(pWidth, pHeight){
	var winT = (screen.height-pHeight)/2;
	var winL = (screen.width-pWidth)/2;

	return "width="+pWidth+", height="+pHeight+", top="+winT+", left="+winL+", resizable=no, scrollbars=yes, status=no, location=no;";
}


function cmmPage(pForm, pPageNum, pUrl){
	pForm.pageIndex.value = pPageNum;
	if(pUrl) {
		pForm.action = pUrl;
	}else{
		pForm.action = "list.do";
	}
	pForm.submit();
}


function cmmChkInProgress(){
	var runBatchInfo = true;
	$.ajax({
		url : "/batch/checkInProgress.do"
		, type: "POST"
		, async : false
		, dataType: "json"
       	, success : function(data) {
       		runBatchInfo = ((data.result) != null )? true: false;
       	}
      	, error : function(jqXHR, textStatus, errorThrown){
      		return true;
      	}
    });
	return runBatchInfo;
}

function cmmSetMenu(paramId, paramIdx){
	$(".depth1_menu").children().removeClass("selMenu");
	$(".depth1_menu").find("#"+paramId).addClass("selMenu");
	$(".side#"+paramId).show();
	$(".side#"+paramId).find("li:eq("+paramIdx+")").addClass("actived");
}

function cmmResultMsg(paramRst, paramUrl){
	if(!isNull(paramRst)){
		if(paramRst == "success"){
			//alert("저장 성공");

			if(paramUrl)
				location.href = paramUrl;
			else
				location.href = "list.do";
		}else if(paramRst == "fail"){
			alert("저장 실패");
		}
	}
}


function cmmCallAjax(paramUrl, paramData){
	var rtnObj;
	$.ajax({
		url : paramUrl
		, type: "POST"
		, async : false
		, dataType: "json"
		, data : paramData
       	, success : function(data) {
       		rtnObj = data;
       	}
    });
	return rtnObj;
}
