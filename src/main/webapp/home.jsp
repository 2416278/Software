<%--
  Created by IntelliJ IDEA.
  User: hxx
  Date: 2023/7/12
  Time: 9:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">


  <title>主页</title>
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <link href="css/font-awesome.min.css" rel="stylesheet">

  <!-- Morris -->
  <link href="css/plugins/morris/morris-0.4.3.min.css" rel="stylesheet">

  <!-- Gritter -->
  <link href="js/plugins/gritter/jquery.gritter.css" rel="stylesheet">

  <link href="css/animate.min.css" rel="stylesheet">
  <link href="css/style.min.css" rel="stylesheet">

</head>

<body class="gray-bg">
<div class="wrapper wrapper-content">
  <%
    String mess=(String)session.getAttribute("msg");  //接收后台传来的message
    if(mess!=null&&!mess.equals("")){  //判断message
  %>
  <script type="text/javascript">
    alert("<%=mess%>");  //弹出警示框
  </script>
  <%
      session.setAttribute("msg","");  //将message值设为空，否则将一直弹出。
    }
  %>
  <div class="row">
    <div class="col-sm-3">
      <div class="ibox float-e-margins">
        <div class="ibox-title">
          <span class="label label-success pull-right">月</span>
          <h5>销售额</h5>
        </div>
        <div class="ibox-content">
          <h1 class="no-margins">40 886</h1>
          <div class="stat-percent font-bold text-success">98% <i class="fa fa-bolt"></i>
          </div>
          <small>总收入</small>
        </div>
      </div>
    </div>
    <div class="col-sm-3">
      <div class="ibox float-e-margins">
        <div class="ibox-title">
          <span class="label label-info pull-right">全年</span>
          <h5>订单</h5>
        </div>
        <div class="ibox-content">
          <h1 class="no-margins">35</h1>
          <div class="stat-percent font-bold text-info">20% <i class="fa fa-level-up"></i>
          </div>
          <small>新订单</small>
        </div>
      </div>
    </div>
    <div class="col-sm-3">
      <div class="ibox float-e-margins">
        <div class="ibox-title">
          <span class="label label-primary pull-right">今天</span>
          <h5>访客</h5>
        </div>
        <div class="ibox-content">
          <h1 class="no-margins">106</h1>
          <div class="stat-percent font-bold text-navy">44% <i class="fa fa-level-up"></i>
          </div>
          <small>新访客</small>
        </div>
      </div>
    </div>
    <div class="col-sm-3">
      <div class="ibox float-e-margins">
        <div class="ibox-title">
          <span class="label label-danger pull-right">最近一个月</span>
          <h5>活跃用户</h5>
        </div>
        <div class="ibox-content">
          <h1 class="no-margins">25</h1>
          <div class="stat-percent font-bold text-danger">38% <i class="fa fa-level-down"></i>
          </div>
          <small>12月</small>
        </div>
      </div>
    </div>
  </div>
  <div class="row">
    <div class="col-sm-12">
      <div class="ibox float-e-margins">
        <div class="ibox-title">
          <h5>订单</h5>
          <div class="pull-right">
            <div class="btn-group">
              <button type="button" class="btn btn-xs btn-white active">天</button>
              <button type="button" class="btn btn-xs btn-white">月</button>
              <button type="button" class="btn btn-xs btn-white">年</button>
            </div>
          </div>
        </div>
        <div class="ibox-content">
          <div class="row">
            <div class="col-sm-9">
              <div class="flot-chart">
                <div class="flot-chart-content" id="flot-dashboard-chart"></div>
              </div>
            </div>
            <div class="col-sm-3">
              <ul class="stat-list">
                <li>
                  <h2 class="no-margins">23</h2>
                  <small>订单总数</small>
                  <div class="stat-percent">48% <i class="fa fa-level-up text-navy"></i>
                  </div>
                  <div class="progress progress-mini">
                    <div style="width: 48%;" class="progress-bar"></div>
                  </div>
                </li>
                <li>
                  <h2 class="no-margins ">44</h2>
                  <small>最近一个月订单</small>
                  <div class="stat-percent">60% <i class="fa fa-level-down text-navy"></i>
                  </div>
                  <div class="progress progress-mini">
                    <div style="width: 60%;" class="progress-bar"></div>
                  </div>
                </li>
                <li>
                  <h2 class="no-margins ">9,180</h2>
                  <small>最近一个月销售额</small>
                  <div class="stat-percent">22% <i class="fa fa-bolt text-navy"></i>
                  </div>
                  <div class="progress progress-mini">
                    <div style="width: 22%;" class="progress-bar"></div>
                  </div>
                </li>
            </div>
          </div>
        </div>

      </div>
    </div>
  </div>
  <div class="row">
    <div class="col-sm-6">
      <div class="ibox float-e-margins">
        <div class="ibox-title">
          <h5>统计商品信息</h5>
          <div class="ibox-tools">
            <a class="collapse-link">
              <i class="fa fa-chevron-up"></i>
            </a>
            <a class="dropdown-toggle" data-toggle="dropdown" href="graph_flot.html#">
              <i class="fa fa-wrench"></i>
            </a>
            <ul class="dropdown-menu dropdown-user">
              <li><a href="graph_flot.html#">选项1</a>
              </li>
              <li><a href="graph_flot.html#">选项2</a>
              </li>
            </ul>
            <a class="close-link">
              <i class="fa fa-times"></i>
            </a>
          </div>
        </div>
        <div class="ibox-content">

          <div class="echarts" id="echarts-goods-chart" style="height:400px;"></div>
        </div>
      </div>
    </div>

  </div>


</div>

<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/plugins/flot/jquery.flot.js"></script>
<script src="js/plugins/flot/jquery.flot.tooltip.min.js"></script>
<script src="js/plugins/flot/jquery.flot.spline.js"></script>
<script src="js/plugins/flot/jquery.flot.resize.js"></script>
<script src="js/plugins/flot/jquery.flot.pie.js"></script>
<script src="js/plugins/flot/jquery.flot.symbol.js"></script>
<script src="js/plugins/peity/jquery.peity.min.js"></script>
<script src="js/demo/peity-demo.min.js"></script>
<script src="js/content.min.js"></script>
<script src="js/plugins/jquery-ui/jquery-ui.min.js"></script>
<script src="js/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="js/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<script src="js/plugins/easypiechart/jquery.easypiechart.js"></script>
<script src="js/plugins/sparkline/jquery.sparkline.min.js"></script>
<script src="js/demo/sparkline-demo.min.js"></script>
<script>
  $(document).ready(function(){$(".chart").easyPieChart({barColor:"#f8ac59",scaleLength:5,lineWidth:4,size:80});$(".chart2").easyPieChart({barColor:"#1c84c6",scaleLength:5,lineWidth:4,size:80});var data2=[[gd(2012,1,1),7],[gd(2012,1,2),6],[gd(2012,1,3),4],[gd(2012,1,4),8],[gd(2012,1,5),9],[gd(2012,1,6),7],[gd(2012,1,7),5],[gd(2012,1,8),4],[gd(2012,1,9),7],[gd(2012,1,10),8],[gd(2012,1,11),9],[gd(2012,1,12),6],[gd(2012,1,13),4],[gd(2012,1,14),5],[gd(2012,1,15),11],[gd(2012,1,16),8],[gd(2012,1,17),8],[gd(2012,1,18),11],[gd(2012,1,19),11],[gd(2012,1,20),6],[gd(2012,1,21),6],[gd(2012,1,22),8],[gd(2012,1,23),11],[gd(2012,1,24),13],[gd(2012,1,25),7],[gd(2012,1,26),9],[gd(2012,1,27),9],[gd(2012,1,28),8],[gd(2012,1,29),5],[gd(2012,1,30),8],[gd(2012,1,31),25]];var data3=[[gd(2012,1,1),800],[gd(2012,1,2),500],[gd(2012,1,3),600],[gd(2012,1,4),700],[gd(2012,1,5),500],[gd(2012,1,6),456],[gd(2012,1,7),800],[gd(2012,1,8),589],[gd(2012,1,9),467],[gd(2012,1,10),876],[gd(2012,1,11),689],[gd(2012,1,12),700],[gd(2012,1,13),500],[gd(2012,1,14),600],[gd(2012,1,15),700],[gd(2012,1,16),786],[gd(2012,1,17),345],[gd(2012,1,18),888],[gd(2012,1,19),888],[gd(2012,1,20),888],[gd(2012,1,21),987],[gd(2012,1,22),444],[gd(2012,1,23),999],[gd(2012,1,24),567],[gd(2012,1,25),786],[gd(2012,1,26),666],[gd(2012,1,27),888],[gd(2012,1,28),900],[gd(2012,1,29),178],[gd(2012,1,30),555],[gd(2012,1,31),993]];var dataset=[{label:"订单数",data:data3,color:"#1ab394",bars:{show:true,align:"center",barWidth:24*60*60*600,lineWidth:0}},{label:"付款数",data:data2,yaxis:2,color:"#464f88",lines:{lineWidth:1,show:true,fill:true,fillColor:{colors:[{opacity:0.2},{opacity:0.2}]}},splines:{show:false,tension:0.6,lineWidth:1,fill:0.1},}];var options={xaxis:{mode:"time",tickSize:[3,"day"],tickLength:0,axisLabel:"Date",axisLabelUseCanvas:true,axisLabelFontSizePixels:12,axisLabelFontFamily:"Arial",axisLabelPadding:10,color:"#838383"},yaxes:[{position:"left",max:1070,color:"#838383",axisLabelUseCanvas:true,axisLabelFontSizePixels:12,axisLabelFontFamily:"Arial",axisLabelPadding:3},{position:"right",clolor:"#838383",axisLabelUseCanvas:true,axisLabelFontSizePixels:12,axisLabelFontFamily:" Arial",axisLabelPadding:67}],legend:{noColumns:1,labelBoxBorderColor:"#000000",position:"nw"},grid:{hoverable:false,borderWidth:0,color:"#838383"}};function gd(year,month,day){return new Date(year,month-1,day).getTime()}var previousPoint=null,previousLabel=null;$.plot($("#flot-dashboard-chart"),dataset,options);var mapData={"US":298,"SA":200,"DE":220,"FR":540,"CN":120,"AU":760,"BR":550,"IN":200,"GB":120,};$("#world-map").vectorMap({map:"world_mill_en",backgroundColor:"transparent",regionStyle:{initial:{fill:"#e4e4e4","fill-opacity":0.9,stroke:"none","stroke-width":0,"stroke-opacity":0}},series:{regions:[{values:mapData,scale:["#1ab394","#22d6b1"],normalizeFunction:"polynomial"}]},})});
</script>

<!-- ECharts -->
<script src="/js/plugins/echarts/echarts-all.js"></script>
<!-- ECharts demo data -->
<script src="/js/echarts.min.js"></script>
<script type="text/javascript">
  // 基于准备好的dom，初始化echarts实例
  var myChart = echarts.init(document.getElementById('echarts-goods-chart'));

  // 指定图表的配置项和数据
  var option = {

    tooltip: {},
    legend: {
      data: ['数量']
    },
    xAxis: {
      data: []
    },
    yAxis: {},
    series: [
      {
        name: '数量',
        type: 'bar',
        data: []
      }
    ]
  };
  // 使用刚指定的配置项和数据显示图表。
  myChart.setOption(option);
  //需要后台获取相关数据
  $.get("/sys/indexGoodsServlet?action=list",function (data) {
    //更新数据
    option.xAxis.data=data.typeNames;
    option.series[0].data=data.goodsNums;
    myChart.setOption(option);
  })
</script>
</body>

</html>
