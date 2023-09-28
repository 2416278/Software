校园购物系统
---------------------------
项目简介
   
    校园购物系统是一个用Java开发的校园购物平台，它允许在管理员和用户两种模式下登录系统。该系统提供了用户/管理员注册、用户管理、密码管理、商品浏览、下单、付款和订单管理等功能。

技术栈

     MySQL数据库
     HTML/CSS/jsp（用于前端界面）
     Maven构建工具
     servlet

环境要求

     jdk 1.8
     MySQL数据库

安装和运行

   克隆项目到本地计算机：

          git clone https://github.com/yourusername/your-project.git

   进入项目目录：

         cd your-project

   配置数据库连接信息：

         打开 data.sql 文件，将数据库连接信息配置为您的数据库设置。

   构建和运行项目：

         使用IIntelliJ IDEA 打开项目，配置运行环境Tomcat本地环境。
         点击“+”添加新配置，下载apache-tomcat-8.5.91文件，并添加..\lib\jsp-api.jar和..\lib\servlet-api.jar依赖。
         修改HTTP端口8086，点击“修复”选择要部署的文件“store.war",应用程序上下文填写"/"。
         下载goodsFile文件放置在D://路径下。

   访问应用程序：

         在浏览器中访问 http://localhost:8086/main.jsp，能看到该系统的页面。

