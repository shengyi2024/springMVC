MVC： 一种软件架构的思想，将软件按照模型，视图，控制器来划分
	M ： Model，模型层，指工程中的javaBean，作用是处理数据
	javaBean分为两类:
		1.实体类Bean:专门存储业务数据的，如Student，User等。
		2.业务处理Bean:指Service或Dao对象，专门用于处理业务逻辑和数据访问
	V ： View，视图层，指工程中的html和jsp等页面，作用始于用户进行交互，展示数据
	C ： Controller，控制层，指工程中的servlet，作用是接收请求和响应浏览器
MVC的工作流程：
	用户通过视图层发送请求到服务层，在服务层中请求被Controller接收，
	Controller调用相应的Model层处理请求，处理完毕将结果返回到Controller，
	Controller在根据请求处理的结果找到相应的View视图，渲染数据最终响应给服务器

Servlet:
    什么是Servlet: server applet 运行在服务器端的小程序

SpringMVC本身已经封装了Servlet（统一处理所有请求和相应的前置控制器），
因此我们不需要原来创建继承HttpServlet的自定义Servlet了。
只需要创建一个请求控制器（java类），交给Spring的IOC容器进行管理，
然后用这个类的方法来实现接收和响应请求。