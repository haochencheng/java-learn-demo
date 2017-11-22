<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>火车查询系统</title>
<link href="${pageContext.request.contextPath}static/css/booking.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/static/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/static/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/static/js/booking.js"></script>
<script language="javascript" type="text/javascript"  src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>


	<style type="text/css">
		#train_date {
		}
	</style>
</head>
<body>
	<!-- 车票查询框开始 -->
	<div class="layout booking">
		<div class="lay-hd">&nbsp;车票查询</div>
		<div class="lay-bd">
			<div class="booking-in">
				<div class="booking-hd">
					<span> <input type="radio" id="dc" class="radio"
						checked="checked" style="cursor: pointer;" /> <label id="dc_label"
						for="dc">单程</label>
					</span> <span> <input type="radio" id="fc" class="radio"
						style="cursor: pointer;" /> <label id="fc_label" for="fc">反程</label>
					</span>
				</div>
				<div class="booking-bd">
					<ul class="clearfix">
						<li><span class="label">出发地</span>
							<div class="inp-w">
								<input id="formStation" type="hidden"
									name="leftTicketDTO.from_station" /> <input
									name="leftTicketDTO.from_station_name" maxlength="15"
									type="text" id="fromStationText" class="inp-txt" />
								<span id="from_station_imageB" class="i-city"
									style="cursor: pointer;"></span>
							</div>
						</li>
						<li><span class="label">目的地</span>
							<div class="inp-w">
								<input id="toStation" type="hidden"
									name="leftTicketDTO.to_station" /> <input
									name="leftTicketDTO.to_station_name" maxlength="15" type="text"
									id="toStationText" class="inp-txt" />
								<span id="to_station_imageB" class="i-city" style="cursor: pointer;"></span>
							</div>
						</li>
						<li class="mt10"><span class="label">出发日</span>
							<div class="inp-w">
								<input readonly="readonly" maxlength="10" autocomplete="off" type="text"
									   class="inp-txt" name="leftTicketDTO.train_date" id="train_date" >
								<span id="from_imageClick" class="i-date" style="cursor: pointer;"></span>
							</div>
						</li>
						<li><span class="label color999" id="_wf_span_date">返程日</span>
							<div class="inp-w">
								<input readonly="readonly" maxlength="10" autocomplete="off" name="back_train_date" id="back_train_date" type="text" class="inp-txt" disabled="disabled">
								<span id="back_imageClick" class="i-date" style="cursor: pointer;"></span>
							</div>
						</li>
						<li style="padding-left: 100px;margin-top: 10px;"><span
							style="float: left; margin-right: 50px;"><input
								type="radio" class="radio" name="sf" id="sf1" checked="checked">
								<label class="cursor" for="sf1">普通</label> </span> <span
							style="margin-right: 20px;"><input type="radio"
								class="radio" name="sf" id="sf2"> <label class="cursor"
								for="sf2">学生</label> </span></li>
					</ul>
					<span class="i-change" id="change_station" style="cursor: pointer;"></span>
					<a id="a_search_ticket" href="javascript:" class="btn-login" shape="rect">&nbsp;&nbsp;&nbsp;查&nbsp;&nbsp;&nbsp;&nbsp;询</a>
				</div>
			</div>
		</div>
	</div>

	<!-- 车票查询框结束 -->
</body>
</html>