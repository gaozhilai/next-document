# 项目介绍
NextDocument是一个文档管理系统, 功能专注于团队内部文档管理, 工作成果共享.
采用MarkDown格式, 化繁为简
不会窃取你的劳动成果, 支持无限制导出md文件

Write Once, Take it to anywhere

除此之外还有诸如投票, 画板, 共享文档, 表格, 即时通讯IM模块等丰富功能

详细的权限控制, 前端权限控制到按钮级别, 后台权限控制到接口级别.

# 整体设计
整个项目采用前后端分离式开发, 后台基于SpringBoot, 前端基于Vue.

目前整个项目由Server端, PC端, 和Mobile端组成, 后期预计支持全平台的桌面客户端以及手机客户端.

# 后台项目设计相关
后台基于SpringBoot2

整体接口设计采用Restful
Level 2 风格

Shiro 结合 JWT 进行无状态的权限体系校验

每次请求过程中账户的验证与授权操作使用 Guava 缓存组件对用户和权限信息进行缓存, 避免每次请求查询数据库.

Junit单元测试保障接口正确性

代码遵守阿里巴巴java开发规约, IDE 中安装对应插件进行代码规范扫描.

# 前端项目信息
PC端项目基于Vue, UI框架使用ElementUI

手机端, 基于Vue, UI框架使用VantUI

# 安全方面
考虑防范了SQL注入 

考虑防范了XSS 

考虑防范了CSRF 

前端权限控制到按钮级别 

后台权限控制到接口级别 

每个账户独立64位盐值, MD5多次得到真实密码

# More...

后续预计通过Weex支持 安卓/IOS客户端
通过Electron支持全平台的桌面客户端

# 实际效果

![](./pic/next_document1.jpg)

![](./pic/next_document2.jpg)

![](./pic/next_document3.jpg)

![](./pic/next_document4.jpg)

![](./pic/next_document5.jpg)