
SSM 架构后台管理系统

# xms 说明


> 简单介绍

```

已集成组件：

后端
1、Spring MVC
2、mybatis （mybatis-generator）
3、shiro
4、fastjson （json 处理）
5、淘宝客SDK
6、等..

前端
1、Bootstrap
2、JQuery
3、fancybox
4、datatables
5、other...
```

> Module说明

```

1、core：         通用类库
2、auth：         shiro服务模块
3、auth-client：  shiro客户端模块，引用该模块可接入`shiro服务模块`进行统一权限管理。
4、admin-demo：   后台演示模块

```

> 运行项目配置说明

```
1、配置 /auth/src/main/resources/application.properties 

2、导入数据库 /auth/src/test/resources/sql/tables.sql 和 data.sql 

3、修改 /admin-demo/src/main/resources/client/shiro-client.properties 中的远程认证服务URL地址
```


更多补充中。。。。。
