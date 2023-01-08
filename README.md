## 安卓APP读取数据库
* 手机打开开发者模式

## TODO
- [x] MainActivity2是可以使用的，和MainActivity区别在哪？是把DBUtil分开的原因？
    - MainActivity好像又能用了,然后MainActivity2好像bug了，button的监听事件无对象（无语）。
    - 和工具类与Activity是否分开来写没有太大关系
- [x] 增删改查数据
    - TODO 对数据库的操作只用了一次，再用第二次会失败

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
