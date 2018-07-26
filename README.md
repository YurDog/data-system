# data-system
## 简单的搭建了一个Spring+SpringMVC+MyBatis的小项目

#### 1.Spring
#### 2.Spring MVC
#### 3.Mybatis
#### 4.Flyway




### 日志

### 2018.07.23 
- 修改了pom文件里junit，spring-test的scope为test，打包不包含测试jar包
- 使用Spring AOP Around拦截接口层的方法(DataSystemAspect)，解耦打印接口的参数，返回值，接口响应时间等信息
- 关闭Flyway配置，报错未解决

### 2018.07.24 
- Flyway配置打开后未报错？？--已解决
- 添加静态ThreadLocal<String>,结合AOP，初步实现一条请求链的日志中都包含该线程的ThreadLocal中保存的字符串(UUID)，便于bug溯源（Dapper）
- Maven pom.xml 添加编译打包的插件，解决默认打包发布到服务器上的乱码问题
- 定义API返回格式，及错误码枚举
- JavaConfig Aspect改为XML配置
### 2018.07.25
- 尝试钉钉机器人（部门任务）
### 2018.07.26
- 开始学习Hystrix（部门任务）