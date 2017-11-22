$(document).ready(function(){
	var qjh="简拼/全拼/汉字";
	var fst=$("#fromStationText");
	var tst=$("#toStationText");

	function getNowFormatDate() {
		var date = new Date();
		var seperator1 = "-";
		var year = date.getFullYear();
		var month = date.getMonth() + 1;
		var strDate = date.getDate();
		if (month >= 1 && month <= 9) {
			month = "0" + month;
		}
		if (strDate >= 0 && strDate <= 9) {
			strDate = "0" + strDate;
		}
		var currentdate = year + seperator1 + month + seperator1 + strDate;
		return currentdate;
	}
	$("#train_date").val(getNowFormatDate());
	$("#back_train_date").val(getNowFormatDate());

	$("#fc").click(function() {
		$("#fc").prop("checked",true);
		$("#dc").prop("checked",false);
		$("#_wf_span_date").attr("class","label");
		$("#back_train_date").attr("disabled",false);
		$("#back_train_date").val($("#train_date").val());
	});
	$("#dc").click(function() {
		$("#dc").prop("checked",true);
		$("#fc").prop("checked",false);
		$("#_wf_span_date").prop("class","label color999");
		$("#back_train_date").attr("disabled",true);
	});
	
    $(".i-change").click(function () {
        var from=fst.val();
        var to=tst.val();
		var temp=from;
		fst.val(to);
		tst.val(temp);
    });

	function init() {

		if(fst.val()==""){
			fst.val(qjh);
			fst.attr("class","inp-txt");
		}else if (fst.val() != qjh) {
			fst.attr("class","inp-txt_select");
		}
		if(tst.val()==""){
			tst.val(qjh);
			tst.attr("class","inp-txt");
		}if (tst.val() != qjh) {
			tst.attr("class","inp-txt_select");
		}
	}

	init();

	$("#fromStationText").blur(function(){
		init();

	});
	$("#toStationText").blur(function(){
		init();
	});

	fst.focus(function () {
		if(fst.val()==""||fst.val()==qjh){
			fst.val("");
		}else{
			this.select();
		}
	});
	tst.focus(function () {
		if(tst.val()==""||tst.val()==qjh){
			tst.val("");
		}else{
			this.select();
        }
    });

    $("#from_station_imageB").click(function () {

    });

    function datePicker() {
        WdatePicker({el:'train_date',doubleCalendar:true,dateFmt:'yyyy-MM-dd',minDate:'%y-%M-{%d}',maxDate:'%y-%M-{%d+59}',onpicked:trainDate});
    }

    function backDatePicker() {
        WdatePicker({el:'back_train_date',doubleCalendar:true,dateFmt:'yyyy-MM-dd',minDate:'%y-%M-{%d}',maxDate:'%y-%M-{%d+59}',onpicked:backTrainDate});
    }

	$("#from_imageClick").click(function () {
        datePicker();
    });

    $("#back_imageClick").click(function () {
        backDatePicker();
    });

    $("#train_date").click(function () {
        datePicker();
    });

    $("#back_train_date").click(function () {
        backDatePicker();
    });

    function trainDate() {
        $("#train_date").attr("class","inp-txt_select");
    }

    function backTrainDate() {
        $("#back_train_date").attr("class","inp-txt_select");
    }
});
