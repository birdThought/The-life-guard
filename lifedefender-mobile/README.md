绑定域名 
---
http://m.lifekeepers.cn

生产环境
---
使用Apache Http Server

部署步骤
---
1. 打开cmd切换到lifedefender-mobile项目下mobile目录
运行命令

          node copy-dist-files 
          npm run build:aot
          npm run serve:aot

2. 打包mobile\aot目录下的
            
        dist文件夹，static文件夹，index.html，shim.min.js，zone.min.js

发布路径
---
    /var/apache/htdocs/lifedefender-mobile

Apache命令
---
    #start apache 
    /var/apache/bin/apachectl -k start
    #stop apache
    /var/apache/bin/apachectl -k stop
    #restart apache
    /var/apache/bin/apachectl -k restart  
    
 <div class="footer">&copy; 2017 Tongzhong Corporation</div>