## 安卓APP读取数据库
* MainActivity2是可以使用的，和MainActivity区别在哪？

## TODO
- [] MainActivity2是可以使用的，和MainActivity区别在哪？
- [] 是把DBUtil分开的原因？

## 注意
1. 配置SQL Server的TCP/IP端口（1433）并且关闭Windows防火墙（Win11在设置里直接搜索defender）
2. SQL Server要开启TCP/IP服务（新版本SQL Server配置管理器在 C:\Windows\SysWOW64下，搜索SQLServerManager15.msc）
3. 开启TCP/IP服务后要重启MSSQLSERVER服务

## Packages
1. [jtds-1.2.7](https://sourceforge.net/projects/jtds/files/jtds/) （据说不能用太高版本的？没使用1.3版本）
2. [sqljdbc](https://www.microsoft.com/zh-cn/download/details.aspx?id=11774) （微软官方页面下载）
- Add as Library

## Referrence
1. https://www.cnblogs.com/panqiaoyan/p/12869971.html