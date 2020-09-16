# 网络框架(Retrofit + RxJava + OkHttp)

# 亮点

1. 通信过程日志输出;
2. 通用公共参数/请求头自动设置(待处理);
3. 数据的链式处理;
4. 支持多域名的处理--继承NetworkApi，实现getBaseUrl;
5. 统一的错误处理--ResponseThrowable;
6. 保障网络安全(DNS劫持、 中间人攻击等)--使用权威机构提供的HTTPDNS解析 ;
