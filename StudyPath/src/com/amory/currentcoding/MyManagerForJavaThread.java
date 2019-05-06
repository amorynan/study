package com.amory.currentcoding;

/**
 * @Author: amory
 * @Date: 2019-04-23 03:42
 */

/**
 * 操作java 中想让线程状态如何变化的manager
 */
public class MyManagerForJavaThread {
    /**
     * 我总结这里其实真正意义上只有4中java线程状态，分别是
     * new ：初始化， runnable ： 可运行， blocked， waiting， timed—wating ： 休眠， terminate ： 死亡
     *
     * 这样划分的含义是可以和os 以及 cpu进行一个比较高度的契合
     *
     * new 属于上层语言中自己初始化线程的状态，这里模仿的是os 对线程（真正情况下其实LWP，所以下面描述中"线程"，"java线程"， "LWP"可以在状态角度上当作一种东西）
     * new 的操作，上层语言的new 不会有os pthread的创建
     * 同理 os 的new 也不会有cpu 的处理
     *
     * runnable 上层语言中的可运行状态，对应os 中对于LWP 的描述状态包含了 runnable & running 态，java 中不区分这两个状态的原因是os 对于
     * LWP 对于这两种状态其实决定与cpu 的执行效率而言的，一般来说cpu 执行效率是很快的，至少比内存中数据改变要快很多，所以java 内存数据如果严格
     * 对应，可能还没有等new 状态变成runnable 就已经running 了
     *
     * 休眠状态， 这三个属于java 层面的休眠状态，让出cpu 的资源，对于os 同理LWP也是休眠的状态，跟cpu 啥没有关系，cpu只是硬件资源
     * 需要注意的是，java 写的io的状态，对于哪个状态？
     * io 状态对应了休眠？线程在休眠？os 让LWP 休眠了吗？阻塞对应休眠？
     * 其实不然，从os 让LWP 休眠这个条件就可以突破，os 本质作用就是链接上层（app），调度线程，准备各种硬件资源，所以可以想象成os 正好在准备io
     * 资源了，io是比较慢的，因为io资源在各种channel 里传输数据，cpu资源是很快的，人家只需要拿到数据计算，如果好的os 没有一个好的情商，傻傻看着
     * cpu 在等别人，不干实事，不会看cpu颜色的os就不是一个合格的调度员，所以阻塞是指cpu 在计算当前线程的东东的时候暂时没法进行下去的状态，再来看
     * os 在干啥，os的LWP在准备io资源呐，对应java 线程不也是在准备io资源呢，所以人家线程在干着活呢，怎么休眠？
     * 所以上面两种场景会导致一个结果那就是cpu 都不执行当前线程的东东了，但原因不一样，前者是当前线程本身进行不下去了，也没必要拿着cpu资源不放
     * 后者是，当前LWP 干着活呢，只不过干活的时候需要其他的资源，但是其他的资源有比较费力气，不想让cpu资源啥等着
     *
     * terminate 死亡，就是os LWP 死亡，cpu肯定也就不占了呗
     */

    /**
     * 下面注重看一下runnable 和 休眠状态的转换吧
     */
}
