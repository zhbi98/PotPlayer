
[![Build Status](https://travis-ci.com/zhbi98/PotPlayer.svg?branch=master)](https://travis-ci.com/zhbi98/PotPlayer)

# 关于

这是一个参考 PotPlayer 的界面(**PotPlayer 是 KMPlayer 的原制作者
姜龙喜先生(韩国)进入 Daum 公司后的新一代网络播放器, PotPlayer
的优势在于强大的内置解码器以及支持各类的视频格式, 而且是免费下载提供使用的**) 
使用 Java 以及图形界面框架 JavaFX 使用 MCV 图形界面与业务逻辑分离的开发模式, 
所开发的个人视频播放器项目, 开发这个项目旨在于学习图形界面框架 JavaFX 
实现了具有和 PotPlayer相同的简洁界面和流畅的操作逻辑。

- 2020/10/28 v1.0.0
- [x] 支持打开文件自动播放
- [x] 支持查看播放记录
- [x] 支持屏幕边沿窗口自动吸附
- [x] 支持双击视频来播放和暂停
- [x] 支持左键点击窗口任意位置来拖到窗口
- [x] 支持左键双击播放窗口打开文件
- [x] 支持根据视频尺寸自动调整窗口大小
- [x] 支持根据播放文件类型调整窗口模式
- [x] 支持根据视频尺寸自动调整窗口显示位置防止超出屏幕
- [x] 支持记录上一次访问的文件路径
- [x] 支持播放记录文件读写


# 已实现样式

<img align="center" src="https://github.com/zhbi98/PotPlayer/blob/master/logo/20210509181933630.jpg" alt="GitHub" title="GitHub,Social Coding" width="456" height="400"/>

<img align="center" src="https://github.com/zhbi98/PotPlayer/blob/master/logo/20210509182256488.jpg" alt="GitHub" title="GitHub,Social Coding" width="912" height="615"/>


# 运行环境
本项目使用 NetBeans 配合 JDK 开发, NetBeans8.0 以及 JDK8.0 以上版本的均可以运行。
亦可使用其他集成开发环境, 例如 Eclipse, IntelliJ IDEA 配合使用 JDK8.0 以上版本均可构建此项目。

[NetBeans download](https://netbeans.apache.org//)
[Eclipse download](https://www.eclipse.org/downloads/)
[IntelliJ download](https://www.jetbrains.com/zh-cn/idea/promo/)


# 更新日志
- [修复]上一次访问的文件路径不存在时, 打开文件程序崩溃问题。
- [修复]播放记录第一条, 无法被读取问题。
- [修复]任务栏图标显示当前播放文件名。
- [修复]无播放记录程序无响应问题。
- [重构]播放记录文件读写部分, 增强文件访问的稳定性。


# 开源计划
此项目开始只是希望通过它进行 Java 相关技术的学习, 在学习期间的设计
由于存在一些抽象上做的不够完善, 所以目前正在一点点地重构, 希望将它
设计的更加完善。


# 免责申明
- 1.本软件提供的所有内容, 仅可用作学习和交流使用, 未经原作者授权, 禁止用于其他用途。
- 2.如有问题请在下载 24 小时内删除。
- 3.为尊重作者版权, 请前往 Potplayer 官方下载原版使用, 支持原创, 谢谢。
- 4.因使用本软件产生的版权问题, 软件作者概不负责。


# 许可证

本项目遵循 [MIT](https://opensource.org/licenses/MIT) 开源许可协议。

```
MIT License

Copyright (c) 2020 zhbi98

Permission is hereby granted, free of charge, to any person obtaining a
copy of this software and associated documentation files (the "Software"),
to deal in the Software without restriction, including without limitation
the rights to use, copy, modify, merge, publish, distribute, sublicense,
and/or sell copies of the Software, and to permit persons to whom the
Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
DEALINGS IN THE SOFTWARE.
```
