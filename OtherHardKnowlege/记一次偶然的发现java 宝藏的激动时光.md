#事情发生在一次复习os的时光里

前言 ： 因为换了mac ，比以前的win 最好的一点就是mac 他是mac os基于unix的啊，这个unix发行版提供了很全面的观察内部线程的图形化界面工具，当然必须把握住这个资源，边复习os， 一边有这么个直接的资源可以看，只能用一句话形容。。。灰常爽，完全摆脱了只接受书本上干瘪瘪的文字和也不知道作者怎么就放出来的图（不知道有没有人和我一样，在看一些书籍的时候，当看到作者写到，打开。。。界面or 输入。。。命令 or 点击。。。就手痒太想自己操作一番了，并且心想你倒是做了这些爽了，我只能看你爽，蛋疼。。。）

正题 ： 扯了这么多，必须进入此篇blog的主题 -- 进入java 线程和 os 某一些部分的知识，首先说明一点的是，我的blog从来不会以某个专题去讲解，而是分散式的，又强迫者的coder or learner 麻烦您点击一下浏览器tab里面的 × ，thx，，还有就是，每本书或者blog 都是有自己深浅程度的，读者如果仅仅是想了解有这么回事，ok，看这些就enough， 如果熟悉，那么自己动手，丰衣足食

###os 哪些你需要掌握的事儿
写这个有一部分是自己的复习，有一部分是自己接触的新的概念，必须要记录下来，才能准确的掌握，其实记录是形成自己技术栈的一个过程，也是知识网络的一部分。



1. os 是个啥？拿来干嘛，常见的有哪几种，都有什么好坏，能做到什么操作？
 
 os -》 operation syetem操作系统， 一般来说我们拿到的计算机，没有os之前的，那就是一堆硬件的组合啊，比如cpu， 键盘， 硬盘，内存以及一些io的设备等等，都知道cpu运算能力强，内存可以存储一些东西，硬盘也能存储一些东西，只不过效率没有内存高，因为人家是机械式的嘛，先不说内存直接一个总线进行电流的存储的速度，机械硬盘诶，来一个数据，我得先寻个道，完了还得找到具体的扇区，最后还得移动找到对应的数据，好这个我们不细说，好了有这么一堆硬件，那么问题来了，怎么用？ 以前的cpu去运算的时候，我记得好像是手动波总线。。。这不是很蛋疼吗？所以有了os 这个东西，os 负责管理这些硬件的资源，并且通过进程进行资源的调度
 
 ok 提到了资源的管理，但从这一个方面讲，os做了哪些东西可以进行资源的调度？
 
一 复用

1）。时空分复用， 很好理解的，空分复用就是说，这些硬件的资源可以交给多给进程用嘛，你也看到过你的系统上比如有8g的内存，xxx 进程实际占据了多少，虚拟占了多少之类的是吧，这类的资源还有硬盘啥的
 2）。 空分复用，就是在同一个时间段里面可以有多个进程运行，比如我单个cpu 通过os的时间片的一个设置，（现阶段分时os是很流行的），那么在这个时间段里面，一会儿运行这个进程，然后os给他的时间片到了，不好意思，让一让，保存一下当前的pid 等task status然后，我要交给下一个进程用一会儿cpu了，此时的当前进程或者说线程（因为比如在linux kernel 中没有线程的概念的，在linux 中会有类似于—_pthread 的class ，相信爱看源码的小伙伴一定看过这个，或者了解过jvm的人，看过jvm的call stub 中也发现过，每次开启一个不管是啥的java 线程，都会先进入这个_pthread call, linux 中有pthread 这个库，线程对于os来说只是进程的一种轻量级而已，对进程来说，就是共享代码和地址空间的小弟而已，所以才会有进程是os能够调度的最小单位，而线程是cpu进行作业的最小单位，但如果细想一下假如一个进程就只有线程呢？再想一下jvm中线程的是咋做的？），回归开始的话题，刚才那种分时的机制，再宏观上看着好像，嗯多个进程在“同步操作”，但其实很有可能还没有单个进程每次做完释放cpu快，原因是他有上下文的切换啊，每次保存一下context，在从下一个context中恢复，这个时间加起来还是很可观的，所以其实多线程或者说多线程，更通俗一点说多任务的是需要os 支持，并且处理器多核的情况下效果才是好的，（多核的意思就是一个芯片上多个cpu，在你的电脑里，可以看一下是不是写着一个处理器-芯片，多核-cpu）这样的情况才可以达到可以处理同时间处理多个进程，cpu之间如果有数据需要分享的，会有一个内部总线的东东，别忘了，cpu就是电信号驱使的，电信号就是010101。。。
 
 二 虚拟化
 
 这个东西就是os玩的一个小技巧了， 就是你实际占用了一些些的内存比如90M，但是我可以说我除了kernel 内存的占用外，其余的你都可以占用，这就是你的天下，但是其实这都是虚的，会有一个虚拟内存表的存在，或者说是一个task_struct 的记录，这里面记录了一个链表，其中就是真实内存的页的分配情况，比如哪些内存有数据，哪些内存无数据，哪些可读可写等权限啥的，如果没有这个东西的存在，那么我们只能开启一个进程，类如jvm， 就把想要的程序代码从硬盘上拷过去，这个效率是很低的，所以os 聪明的玩了一个技巧，只要你来，我就说你拥有朕给你打下的江山，你随意用，如果某个进程发现我少了哪个页上的数据，那么就会马上传到os的耳朵中，就是缺页信号，那么os就会马上去硬盘中加载这个页的，然后假装什么事情都没有发生的放到物理内存中，如果内存满了要么就会覆盖要么就是会有jvm的一些技术跑出oom的情况
 
 三 抽象
 
 这个不难理解，这个就是与人打交道的必备了，os你不能只管你和硬件的联盟啊，人这么重要的资源你都不珍惜？哈哈哈，其实单说linux ，其实就是kernel + kernel tool， kernel 就是给硬件资源打交道的，kernel tool就是留下的一些挺高深的api的，如果有过分布式服务经验的程序员来说，完全可以抽象linux 就是一个中台服务，完了他就留出了一些接口，而这些接口只有公司的前台的服务（前台服务不是说前端，而只是说和用户打交道的服务，包括前端）认识，并且可以通过rpc / soa 进行call， 公司的直接用户并无法输入这些api就可以访问资源的， 所以不友好嘛，相继一些linux disturbtions 类的os 发行版就出来啦， 比如mac os，前文我提到过，cent os ，redhat 之类的，他们做的就是友好的提供Linux 服务，只需持续点击下一步，ok linux 就帮你按上了，完了还送你各种自己特性化的软件，比如前言我提到的，mac os 支持很好的观察内部进程的call stub分析之类的。
 
 
 嗯，说了这么多 os 就是个中台嘛，那么我们可以介绍一下linux 这个优秀的os 了，为啥优秀？后面会说，既然很多的web 服务都放在这个os 上，而不是win 上是有一定的原因的，其次就是我们得知道不同的os 能调配的硬件资源的种类也是不同的，比如用过mac 的小伙伴是不是遇到过当我想插入某个外接键盘或者啥的，会跳出判断是不是能够匹配？（mac os 是unix 的kernel） 其实就是在看是不是有你这个键盘的驱动程序，win 也会有相应的判断的，常见的场景就是插入某个usb， 都得等一会，看有没对应型号的驱动匹配？
 
 
 
 2. 优秀的os 始终都是 优秀的os 
 3.  优秀的os 始终都是 优秀的os 

 就讲Linux吧，因为作为一个程序员来说，Linux 比起 win 更有好的研究价值，为啥？因为人家open resource 啊，你要是能力足够完全可以写一个好模块到linux 官网上投递，没准还真的被采用了也不一定呢
 
 所以人家优秀的第一个原因找到了 linux 开源，相对于win来说，微软各种自家自己造，冷不丁就提醒你，诶我发现了一个补丁，你看着办吧，开源就意味着首先可以尽可能多的适应多种的硬件，因为某个硬件要是开发出来了，给linux 社区上自荐一哈，我说我的东西不错，我连驱动程序都写好了，你看看行不行把我加入你们的模块吧，诶，这个硬件就悄无声息的就支持了，同理对用户来也一样，毕竟可以看到已经有很多成功的案例centos 等等这些个已经通过linux卖钱的各路linux 发行版
 
 
其次就是Linux 支持muilt user 和 multi task， 后面的多任务就不讲了，就是可以通过程序进行进行多个进程运行，当然能达到真正效果的还是要 cpu 多核来给力的，那么multi user 是个啥，其实就是多用户的概念，比如某个用户组里面和其他的用户组的资源空间是可以不共享的，单独的分开的，这个我觉得应该docker 就是这么搞出来的，但没有仔细研究过，毕竟容器的概念就是通过环境隔离的方式，将多个服务之间没有交集的进行部署等等操作，也就是独立作业


最后还有一个就是功能强大并且稳定，为啥，因为人家背后是个社区啊，所有自称coder 的人都可以进行检查和打补丁或者添加驱动支持更好的硬件等



 
