

**一 什么是penetrate**

    penetrate是一个java语言开发针对frp进行管理的项目。在v0.9.3及以前的版本，并没有一个可用的管理系统。
    开特权模式，又怕被别人滥用。不开特权模式，每添加一个设置，要我去修改配置文件，重新加载或重启，比较麻烦。
    还是比较适合自用或者朋友之间使用。penetrate是通过后台添加设置的形式，自动修改配置文件，并自动重新加载配置。
  
  
  
**二 penetrate线上运行的产品**

  http://tun.gogl.top/home  是penetrate的一个线上运行的例子，并提供免费的穿透服务。
  
 ![enter image description here](https://github.com/pickear/penetrate/blob/master/manager/src/main/resources/static/images/home.png?raw=true)
  
  ![enter image description here](https://github.com/pickear/penetrate/blob/master/manager/src/main/resources/static/images/manager.png?raw=true)
  
**三 penetrate使用**

     1 penetrate模块
        主要有两个模块。home模块是一个对产品前端展示的模块，manager模块是一个对frp管理的模块。
    
     2 使用
        首先下载源代码并编译成jar，执行jar manager.jar -Dfrp.home=xxxx,其中xxxx为你的frp安装目录。

四 交流
    如遇到什么问题，可以加企鹅群:372831385交流。



如果你觉得对你有帮助，可以请作者喝杯咖啡。


<div class="inner">
		<div class="flex flex-2">
			<article>
				<div class="image round">
					<img src="https://github.com/pickear/penetrate/blob/master/home/images/alipay.jpg" width="200" height="200" alt="支付宝二维码" />
          </div>
			</article>
			<article>
				<div class="image round">
					<img src="https://github.com/pickear/penetrate/blob/master/home/images/wxpay.png" width="200" height="200" alt="微信二维码" />
				</div>
			</article>
		</div>
	</div>


