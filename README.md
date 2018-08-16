# EMDC
前言

随着经济和科学技术的飞速发展，人民生活水平的不断提高，资源短缺，环境恶化与人口剧增的矛盾却越来越突出，发展设施农业，提高我国农产品的质量和生产效率已经刻不容缓，性能良好的物联网环境监测数据中心系统为发展设施农业提供了良好的技术保障。 本设计说明书根据”昆山现代设施农业科技示范基地”项目建设需要并结合移动通信技术，ZigBee段距离无线技术和Internet网络技术设计了一种基于java和ZigBee技术的物联网环境监测数据系统，该系统能够及时，快捷，准确的抄收温室环境下的相关数据。

传感器实验箱
![image](https://github.com/zhoutingzt/EMDC/blob/master/image/chuanganqi.png)

这里写图片描述

实验箱分上下两个部分,上半部分有八个模块构成

第一个模块:灯光控制(led灯,呼吸灯等) 第二个模块:环境采集(二氧化碳,温度,湿度,光照强度,紫外钱) 第三个模块:安防控制(烟雾,甲烷,人体红外,火光传感器) 意外防范 第四个模块:环境采集(pm2.5,温湿度) 第五个模块:安防控制(报警灯,报警器,光电开关,窗户开关) 人为防范 第六个模块:车库控制(车库栏杆升降,刷卡,显示读卡信息) 第七个模块:家居控制(窗帘升降,中央空调,风扇控制) 第八个模块:入户门控制(指纹识别,密码输入,读卡,显示读卡信息)
系统结构框架图

这里写代码片

这里写图片描述

数据采集系统自上而下的控制命令传输过程如下：数据采集中心通过http网络向树莓派终端发送相关的控制命令，树莓派接受到控制命令后按照事先定制的协议对其进行解析，解析通过ZigBee网络按照控制命令字执行相应的操作。
这里写图片描述

模块划分

该系统分为三部分：客户端，服务器，还要后台管理系统。
客户端实现环境数据的采集并无线传输，服务器则对客户端上传的数据通过数据库进行保存并实现对多客户的并发服务，后台管理系统主要是将对果园监测到的结果从数据库展示到UI界面，并通过JFree插件，生成柱形图和实时统计图，方便果林管理员于对该地区进行综合分析和考察，适合种植什么，或者是进行其他的项目，这样有利于促进农业的发展，更好提高作物的产量及资源的利用率。具体功能如下：

(1) 客户端具备环境的温度，湿度，二氧化碳，光照强度采集功能。
(2) 客户端具有颗粒物检测功能。 
(3) 客户端具有地理信息标识功能（GPS 定位功能）。
(4) 客户端具有无线网络传输功能。 
(5) 服务器是基于 TCP 协议的并发服务器，应用多线程编程技术以实现多客户 并发访问功能。 
(6) 服务器将接受到的环境数据分批保存到数据库对应的表中 (7) 后台管理系统，管理员登录查看系统分析数据报表

系统设计
应用嵌入式技术与无线网络通信技术相结合，设计了客户机（client）/服务器(server)架构下的物联网数据监测数据中心系统。该系统分为三部分：客户端与服务器，后台管理系统；客户端，即终端通过光照强度、温度传感器、湿度传感器、二氧化物传感器对环境数据进行定时采集，并使用移动无线通信技术将数据上传到服务器，同时为了便于直观的感受区域点的实时环境状信息，应用java技术对环境数据进行封装并上传至服务器，服务器接收相关数据并分批存储到oracle数据库。 该系统可以在大区域内实现长时间的、动态的、区域化的多点检测；同时该系统也可以作为大数据时代下的重要数据来源，通过对相关气象数据进行建模统计，以掌握相关指标的发展趋势，对环境信息发布及预测具有重要的指导意义。 这里写图片描述

客户端

负责采集温度,湿度,二氧化碳,光照强度产生的数据信息分为5个模块 
(1)配置模块 (2)日志模块 (3)采集模块 (4)备份模块 (5)网络模块

服务器端

负责接受数据并且将数据批处理入数据库对应的表中，分为6个模块。
(1)配置模块 (2)日志模块 (3)入库模块 (4)备份模块 (5)网络模块

后台管理系统

(1)管理员登录模块 (2)用户注册模块 (3)数据查询模块 (4)数据统计模块 (5)数据导出模块 (6)数据报表模块
这里写图片描述

客户端采集模块

负责采集指定文件中的数据(智能家居环境监测系统产生的数据) 需要实现的方法:Collection gather(); 采集文件样例:

100|101|2|16|1|3|5854997802|1|1516361335964 100|101|2|256|1|3|001003|1|1516361342493 100|101|2|256|1|3|001003|1|1516361343403 ... ...

客户端网络模块

负责把采集好的数据发给服务器 需要实现的方法:void send(Collection c)

　1.如何得到连接服务器的相关信息
　　2.如何得到采集好的数据
　　3.如何把数据发送给服务器
　　4.如果发送数据失败怎么处理
　　5.注意重要信息的日志记录
服务器端网络模块

负责接收客户端传过来的数据 需要实现的方法:Collection revicer();void shutdown();

　1.如何获得服务器启动时候用的相关信息
　　2.如何关闭服务器
　　3.如何接收客户端传过来的信息
　　4.如何处理客户端并发的问题
　　5.接收到数据之后下一步怎么做
　　6.数据的接收或者处理过程备份的问题
　　7.注意重要信息的日志记录
服务器端入库模块

负责接收到的数据插入到数据库中 需要实现的方法:void saveDB(Collection c)

　1.如何获得连接数据库的相关信息
　　　　2.怎么把数据插入到数据库中
　　　　3.插入数据时候的效率问题
　　　　4.什么样的数据对应哪一种表
公共的日志模块

负责记录系统运行过程的一些重要信息 需要实现的方法:

void debug(String msg); void info(String msg); void warn(String msg); void error(String msg); void fatal(String msg);
　1.怎么来实现日记记录
　　2.了解日志级别
　　3.怎么设置日志的级别
　　4.怎么获得日志对象
　　5.怎么控制日志的格式
　　6.怎么控制日志输出到控制台和指定文件中
公共的备份模块

负责备份一些没有处理完的数据 需要实现的方法: void store(String filePath, Object obj,boolean append) Object load(String filePath, boolean del)

　1.如何获得备份文件存放的目录信息
　　2.如何把数据备份到文件
　　3.如何读取文件中的备份数据
　　4.如何实现备份数据时候的追加或者是覆盖
　　5.如何控制读取备份数据后文件是否需要删除
管理员登录模块

负责图形化界面的控制,包括管理员的登录注册,环境数据查询,环境数据统计等功能 需要实现的方法:void login()

1.gui的不同界面如何相互跳转 
2.根据日期,环境种类查询的数据如何以图表显示 
3.数据日期如何以年月日,小时分钟秒的形式显示
4.数据统计功能如何实现
公共的配置模块

该模块相当于一个工厂,负责产生各个模块对象,读取各个模块所需的信息,并且把信息注入到每个模块中. 需要实现的方法: Logger getLogger(); BackUP getBackup(); Gather getGather(); Client getClient(); Server getServer(); DBStore getDBStore();

1.怎么获得每个模块的相关信息
2.如何创建每个模块的对象
3.怎么把每个模块需要的数据注入到模块中
4.什么时候可以把自己(配置模块本身)注入到需要的模块中
数据库

有31张表 e_detail_1 .. e_detail_31

name varchar2(20),
srcId varchar2(5),
dstId varchar2(5),
sersorAddress varchar2(7),
count number(2),
cmd  varchar2(5),
status number(2),
data number(9,4),
每张表对应一个日子,不同的数据需要插入到不同的表里面.例如:1号(不关注年和月)产生的数据,就需要把数据插入到第1张表中,15号产生的数据,就需要插入到第15张表里面.

oracle建表语句

使用PL/SQL建表

BEGIN
　　FOR i IN 1..31 LOOP
　　EXECUTE IMMEDIATE
　　'CREATE TABLE t_detail_'||TO_CHAR(i)||
　　'(
　 　字段
     )';
　　END LOOP;
　　END;
　　/
　　
使用PL/SQL删除表
　　BEGIN
　　FOR i IN 1..31 LOOP
　　EXECUTE IMMEDIATE
　　'DROP TABLE t_detail_'||TO_CHAR(i);
　　END LOOP;
　　END;
　　/
系统运行效果

系统编写环境：MyEclipse+oracle+传感器+Swing+Socket+Jdbc 这里写图片描述
采集的数据 

登录效果图：
![image](https://github.com/zhoutingzt/EMDC/blob/master/image/login.png)

注册效果图：
![image](https://github.com/zhoutingzt/EMDC/blob/master/image/register.png)

用户信息修改效果图：
![image](https://github.com/zhoutingzt/EMDC/blob/master/image/modifyuser.png)

数据展示效果图：
![image](https://github.com/zhoutingzt/EMDC/blob/master/image/table.png)

统计数据效果图：
![image](https://github.com/zhoutingzt/EMDC/blob/master/image/selectcount.png)

根据日期展示：
![image](https://github.com/zhoutingzt/EMDC/blob/master/image/selectdate.png)

获取实时数据的效果图：
![image](https://github.com/zhoutingzt/EMDC/blob/master/image/realtimedata.png)

生成报表的效果图：
![image](https://github.com/zhoutingzt/EMDC/blob/master/image/shechengbaobiao.png)

清空数据的效果图：
![image](https://github.com/zhoutingzt/EMDC/blob/master/image/cleardata.png)

导出报表的效果图：
![image](https://github.com/zhoutingzt/EMDC/blob/master/image/exportExcel.png)

项目下载地址

Github:https://github.com/zhoutingzt/EMDC

总结

本项目从实际的需求与应用出发，将Java技术与ZigBee短距离无线通信技术成功应用在物联网环境数据监测中心项目中。在该系统中树莓派与传感器之间的通讯采用ZigBee短距离无线传输技术，树莓派与客户端采集中心之间的通信采用socket远程数据传输技术，遮阳避免了采用有线方式传输数据时而导致大量布线带来的麻烦。该系统开发成本较低，通用型强使其在低速率小数据量传输方面具有明显的优势，该技术框架在温室大棚中有着广阔的发展与应用空间。
