## 简介

一款精简的工具组件提供一些常用的工具类，方便开发者使用。
主要来源于作者项目中经常会使用到的功能进行封装，也会有一些额外尝试的功能放在工具箱中。
基于JDK17开发，尽可能少的依赖第三方库。

-------------------------------------------------------------------------------

## 组件概览

| 模块              | 介绍                                      |
|-----------------|-----------------------------------------|
| tool-base       | 基础工具集合                                  |
| tool-log        | 提供通用构建log组件，适合于第三方框架集成                  |
| tool-gray       | 通用灰度组件，结合基础工具中的condition工具拓展出更灵活的灰度匹配机制 |
| tool-strategy   | 策略相关工具集合。如：轮询策略                         |
| tool-test       | 提供生成测试数据的工具集合                           |
| tool-proxy      | 简易实现动态代理方式组件                            |
| tool-experiment | 一些实验性质的工具组件                             |

-------------------------------------------------------------------------------

## 文档

- [工具包介绍文档](https://github.com/HaydnSyx/syx-toolbox/wiki)

-------------------------------------------------------------------------------

## 安装

仓库地址：https://central.sonatype.com/search?q=io.github.haydnsyx

### Maven
```xml
<dependency>
    <groupId>io.github.haydnsyx</groupId>
    <artifactId>tool-base</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Gradle
```
implementation 'io.github.haydnsyx:tool-base:1.0.0'
```

