## GlideDemo
本项目主要以郭霖先生的 [Android图片加载框架最全解析（一），Glide的基本用法 - CSDN博客 ](https://blog.csdn.net/guolin_blog/article/details/53759439?utm_source=tuicool&utm_medium=referral)
这一系列的 Glide 文章为基础的Demo
* 辅助文章:[Glide4.0使用浅解 - 简书 ](https://www.jianshu.com/p/ab97d6bda8ec)
* 辅助源码文章:[【两篇就懂系列】Glide源码分析之加载图片流程(1/2) - 简书 ](https://www.jianshu.com/p/3bb4977ceb83)
* 辅助源码文章:[深入理解Glide | Jiantao ](https://yangjiantao.github.io/2017/10/25/%E6%B7%B1%E5%85%A5%E7%90%86%E8%A7%A3Glide/#more)


### 项目截图
<a href="./art/meizi.png"><img src="./art/meizi.png" width="40%"/></a><img height="0" width="8px"/><a href="./art/setting.png"><img src="./art/setting.png" width="40%"/></a>

App体验地址：

如果你还没有熟悉RxJava的用法,请看这里:[simplebam/RxJavaDemo 本教程基于 RxJava2 实践练习Demo ](https://github.com/simplebam/RxJavaDemo)


### 项目中用到的知识
* 命名规范-这里主要参考Blankj:[Android 开发规范（完结版） - 简书](https://www.jianshu.com/p/45c1675bec69)
* Android基础:
  * Android基础知识复习:
     * [尚硅谷15天Android基础(复习笔记) - CSDN博客](http://blog.csdn.net/simplebam/article/details/70213167)
     * [《Android 第一行代码》](http://blog.csdn.net/guolin_blog/article/details/52032038)
     * 内部存储跟外部存储的区别,
        * [Android存储访问及目录 - 圣骑士wind - 博客园](http://www.cnblogs.com/mengdd/p/3742623.html)
        * [安卓内外部存储完全解析](https://www.jianshu.com/p/116025bf51f7)
        * [彻底搞懂Android文件存储---内部存储，外部存储以及各种存储路径解惑](http://blog.csdn.net/u010937230/article/details/73303034)
     * [Android之各分辨率定义的图片规格 ](http://www.nljb.net/default/Android%E4%B9%8B%E5%90%84%E5%88%86%E8%BE%A8%E7%8E%87%E5%AE%9A%E4%B9%89%E7%9A%84%E5%9B%BE%E7%89%87%E8%A7%84%E6%A0%BC/)
  * 四大组件:
     * Activity:
        * 启动模式:[Activity的四种启动模式-图文并茂 – Android开发中文站](http://www.androidchina.net/3173.html)
        * 状态保存与恢复:[Android Activity 和 Fragment 状态保存与恢复的最佳实践](https://www.jianshu.com/p/45cc7775a44b)
        * 动画切换:[酷炫的Activity切换动画，打造更好的用户体验 - 简书](https://www.jianshu.com/p/37e94f8b6f59)
        * 标签属性:[Android Activity标签属性 - 简书](https://www.jianshu.com/p/8598825222cc)
     * PreferenceActivity:
        * [Android开发之PreferenceActivity的使用 - 简书](https://www.jianshu.com/p/4a65f4a912c6)
        * [Preference 三种监听事件说明 - wangjicong_215的博客 - CSDN博客](http://blog.csdn.net/wangjicong_215/article/details/52209380)
     * Fragment
        * [实现Activity和Fragment之前通信 - CSDN博客](http://blog.csdn.net/xuxian361/article/details/75529810)
        * [死磕 Fragment 的生命周期 - MeloDev的博客 - CSDN博客](http://blog.csdn.net/MeloDev/article/details/53406019)
        * [android fragment onHiddenChanged的使用 - CSDN博客](http://blog.csdn.net/bzlj2912009596/article/details/62851537)
           ,这里是为了解释第二篇博文准备的
        * [Fragment的setUserVisibleHint方法实现懒加载，但 setUserVisibleHint 不起作用？ - Leevey·L - 博客园](http://www.cnblogs.com/leevey/p/5678037.html)
          ,这里是为了解释第二篇博文准备的
        * [TabLayout使用详解 - 简书](https://www.jianshu.com/p/7f79b08f5afa)
          ,这里是为了解释第二篇博文准备的
        * [套在ViewPagerz中的Fragment在各种状态下的生命周期 - CSDN博客](http://blog.csdn.net/jemenchen/article/details/52645380)
        * [Android -- Fragment 基本用法、生命周期与细节注意 - 简书](https://www.jianshu.com/p/1ff18ec1fb6b)
        * [Fragment全解析系列（一）：那些年踩过的坑 - 简书](https://www.jianshu.com/p/d9143a92ad94)
          ,这系列的四篇都很经典,建议你可以看看
        * 还不知道怎么入门解析Fragment的可以看我的面经,里面涉及了(卖个广告),
          [Android面经-基础篇(持续更新...) - CSDN博客](http://blog.csdn.net/simplebam/article/details/77989675)
        * 关于保存和恢复Fragment目前最正确的状态:[The Real Best Practices to Save/Restore Activity's and Fragment's state. (StatedFragment is now deprecated)](https://inthecheesefactory.com/blog/fragment-state-saving-best-practices/en)
  * Material Design:
    * [Android Theme.AppCompat 中，你应该熟悉的颜色属性 - 简书 ](https://www.jianshu.com/p/15c6397685a0)
      这家伙的关于MD文章也是值得一看的,简短but精辟
    * ConstraintLayout:
        * [Android新特性介绍，ConstraintLayout完全解析 - CSDN博客 ](https://blog.csdn.net/guolin_blog/article/details/53122387)
        * [ConstraintLayout 属性详解 和Chain的使用 - 简书 ](https://www.jianshu.com/p/50debc330e91)
        * [约束布局（ConstraintLayout）1.1.0的新特性 - CSDN博客 ](https://blog.csdn.net/qq_21793463/article/details/80003303)
    * Toolbar:
        * [Android ActionBar完全解析，使用官方推荐的最佳导航栏(上)](http://blog.csdn.net/guolin_blog/article/details/18234477)
        * [ToolBar使用心得(如何改变item的位置) - 泡在网上的日子](http://www.jcodecraeer.com/plus/view.php?aid=7667)
        * [Toolbar+DrawerLayout+NavigationView使用心得](http://www.jcodecraeer.com/a/anzhuokaifa/2017/0317/7694.html)
        * [Android ToolBar 使用完全解析 - 简书]( https://www.jianshu.com/p/ae0013a4f71a)
    * CoordinatorLayout(本身就是一个加强版的FrameLayout)可以监听其所有子控件
      的各种事件,然后自动帮助我们做出最为最为合理的响应 <--(寄生) AppBarLayout
      (垂直的LinearLayout加强版),它在内部做了很多滚动事件的封装
      <--(寄生) CollapsingToolBarLayout(可折叠式标题栏)
        * CoordinatorLayout:[CoordinatorLayout与滚动的处理-泡在网上的日子](http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0717/3196.html)
        * DrawLayout:
          * [android官方侧滑菜单DrawerLayout详解 - 泡在网上的日子](http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2014/0925/1713.html)
          * [用Android自带的DrawerLayout和ActionBarDrawerToggle实现侧滑效果 - CSDN博客](http://blog.csdn.net/miyuexingchen/article/details/52232751)
          * [Drawer 详解 ·Material Design Part 3 - Android - 掘金](https://juejin.im/entry/5825c76d67f3560058d23657)
    * RecyclerView:
        * [RecyclerView简单使用总结 - 简书](https://www.jianshu.com/p/9b3949f7cb0f)
        * [RecyclerView使用完全指南，是时候体验新控件了（一） - 简书](https://www.jianshu.com/p/4fc6164e4709)
    * SwipeRefreshLayout:
        * [SwipeRefreshLayout详解和自定义上拉加载更多 - 简书 ](https://www.jianshu.com/p/d23b42b6360b)
        * [SwipeRefreshLayout+RecyclerView冲突的几种解决方案 - 简书](https://www.jianshu.com/p/34cbaddb668b)
    * NestedScrollView :[NestedScrollView的使用 - CSDN博客 ](http://blog.csdn.net/mchenys/article/details/51541306)
    * 看不懂物料设计的话建议买郭霖先生的《第二行代码》好一点，这本书内容对于初级
      开发者来说还是蛮不错的
* Github知识:
  * stormzhang的开源书籍:[从 0 开始学习 GitHub 系列-CSDN下载 ](http://download.csdn.net/download/simplebam/9745564)
      ,你也可以关注公众号 stormzhang ，id：googdev，聊天页面回复"github"关键
      字，即可获取，完全免费,但我本人感觉他公众号完全发鸡汤,没什么卵用,所以自己
      load下来上传到csdn博客
  * [Git教程 - 廖雪峰的官方网站](https://www.liaoxuefeng.com/wiki/0013739516305929606dd18361248578c67b8067c8c017b000)
      ,我更愿意推荐他的,通俗易懂,我建议可以配合[Pro Git（中文版）](http://git.oschina.net/progit/)一起看效果更佳
  * [github release 功能的使用及问题解决 - Eggy2015的博客 - CSDN博客](http://blog.csdn.net/Eggy2015/article/details/52138751)
* 时间戳
  * 定义:[程序员的日常：时间戳和时区的故事| 编程派 | Coding Python ](http://codingpy.com/article/programmer-daily-story-about-timestamp-and-timezone/)
  * [JAVA获取时间戳，哪个更快 - 潇湘客 - ITeye博客 ](http://tangmingjie2009.iteye.com/blog/1543166)


### 项目中用到的框架
* Glide
  * [Android图片加载框架最全解析（一），Glide的基本用法 - 郭霖的专栏](http://blog.csdn.net/guolin_blog/article/details/53759439)
    郭霖写的东西都很赞,值得推荐阅读
  * [Glide4.0使用浅解 - 简书 ](https://www.jianshu.com/p/ab97d6bda8ec)
* glide-transformations
  * 网上出现了很多Glide的图片变换开源库，其中做的最出色的应该要数glide-transformations这个库了
  * [Android图片加载框架最全解析（五），Glide强大的图片变换功能 - 郭霖](http://blog.csdn.net/guolin_blog/article/details/71524668)
  * [Glide、Picasso、Fresco进阶 - 图像转换 - 简书 ](https://www.jianshu.com/p/976c86fa72bc)
* 还是不会用Glide的话,那么推荐使用[panpf/sketch: Sketch 是 Android 上一个
   强大且全面的图片加载器,支持 GIF，手势缩放以及分块显示超大图片](https://github.com/panpf/sketch)
   无需关心TAG，因为根本就不使用TAG来关联，也自带多种图片处理效果，圆形的、圆角
   的、高斯模糊的等等
* ButterKnife
  * [[Android开发] ButterKnife8.5.1 使用方法教程总结 - CSDN博客](http://blog.csdn.net/niubitianping/article/details/54893571)
* RxJava
  * 如果你还不会用,建议先看:[给初学者的RxJava2.0教程(一) - 简书 ](https://www.jianshu.com/p/464fa025229e)
    ,先不用管RxJava使用的什么的观察者模式。快速上手才是最重要的。
  * [关于RxJava最友好的文章 - 掘金 ](https://juejin.im/post/580103f20e3dd90057fc3e6d)
    ,这几篇系列是我觉得讲解RxJava最基础知识中最友好的文章,很多文章一上来就写个
    代码Demo来讲解观察者模式(我基础差,对我来说这一些类似文章最不友好了)。
  * [这可能是最好的RxJava 2.x 教程（完结版） - 简书 ](https://www.jianshu.com/p/0cd258eecf60)
  * [给 Android 开发者的 RxJava 详解 ](http://gank.io/post/560e15be2dca930e00da1083)
    ，朱凯（抛物线）文笔很不错，建议看一下他的文章
  * [Introduction · ReactiveX文档中文翻译 ](https://mcxiaoke.gitbooks.io/rxdocs/content/)
    ,官方文章，永远都是第一步
* Rxlifecycle
  * [RxAndroid之Rxlifecycle使用 - CSDN博客 ](https://blog.csdn.net/jdsjlzx/article/details/51527542)
  * [不继承RxAppCompatActivity的情况下使用RxLifeCycle - CSDN博客 ](https://blog.csdn.net/kevinscsdn/article/details/78980010)
* OkHttp
  * [Okhttp解析（一）请求的分发，拦截 - 简书 ](https://www.jianshu.com/p/1c39c7bb34ca)
  * [Okhttp解析（二）网络请求的执行 - 简书 ](https://www.jianshu.com/p/601a84fe42a3)
  * [Okhttp解析（三）网络连接的管理 - 简书 ](https://www.jianshu.com/p/9a78fcb77b0a)
  * [Okhttp解析（四）网络连接的建立 - 简书 ](https://www.jianshu.com/p/f7206af8b8a0)
  * [Okhttp解析（五）缓存的处理 - 简书 ](https://www.jianshu.com/p/00d281c226f6)
  * [Android网络编程（六）OkHttp3用法全解析 | 刘望舒的博客](http://liuwangshu.cn/application/network/6-okhttp3.html)
  * 关于OkHttp3无法再通过OkHttpClient.cancel(tag)形式来取消请求,我身边挺多小伙伴纷纷还是使用OkHttp2.x问题,我个人认为技术始终需要更新,并非因为一个     简单的理由就让你停滞,在这里我参考了以下的文章进行取消:
    * [关于取消OkHttp请求的问题 - 简书](https://www.jianshu.com/p/b74466039b84)
    * 上面这篇文章评论这句话说的特别有道理:其实cancel网络请求的时候，如果还未和
      服务器建立连接，它会回调到onFailure()方法中，但是还有一种情况就是它会在
      onResponse的时候刚好cancel网络请求，那么它会在onResponse()方法中抛出
      java.net.SocketException: Socket closed
* Retrofit
   * [你真的会用Retrofit2吗?Retrofit2完全教程 - 简书](https://www.jianshu.com/p/308f3c54abdd)
* Glide
  * [Android图片加载框架最全解析（一），Glide的基本用法 - 郭霖的专栏](http://blog.csdn.net/guolin_blog/article/details/53759439)
    郭霖写的东西都很赞,值得推荐阅读
  * [Google推荐的图片加载库Glide介绍 - 泡在网上的日子](http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0327/2650.html)
  * [Glide 一个专注于平滑滚动的图片加载和缓存库 - 简书](https://www.jianshu.com/p/4a3177b57949)
  * [Glide V4 框架新特性（Migrating from v3 to v4） - HeXinGen的博客 - CSDN博客](http://blog.csdn.net/hexingen/article/details/72578066)


### 开发中遇到的问题
* 相信不少的小伙都会遇到下面的问题，就是setSupportBar就会爆出以下的问题，原因
  是App主题已经有ActionBar了，如果你还使用setSupportBar(toolbar)方法把toolbar
  作为ActionBar，就会报错
  ```
  Caused by: java.lang.IllegalStateException:
  This Activity already has an action bar supplied by the window decor.
  Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar
  to false in your theme to use a Toolbar instead.
  at android.support.v7.app.AppCompatDelegateImplV9.setSupportActionBar(AppCompatDelegateImplV9.java:201)
  at android.support.v7.app.AppCompatActivity.setSupportActionBar(AppCompatActivity.java:129)
  ```
  解决办法：
  * 把App/Theme的主题 "Theme.AppCompat.Light.DarkActionBar" 改为
    "Theme.AppCompat.Light.NoActionBar" 即可
  * 在主题下面加上下面两行代码取消主题 "Theme.AppCompat.Light.DarkActionBar"
    的actionBar
    ```
    <item name="windowActionBar">false</item>
    <item name="windowNoTitle">true</item>
    ```
* 当 MainActivity 出现下面的猫腻时候,百度几乎是废了;Google还是很管用,搜索到
  [android.support.v4.app.BackStackRecord.doAddOp(BackStackRecord） - CSDN博客 ](https://blog.csdn.net/u014737138/article/details/48196121)
  时候,虽然这篇文章没有给出答案,但下面的评论就给出了答案:
  > 其实很多涉及到java.lang.NullPointerException问题的时候，往往是你没有初始化某个变量，或者是你initView（）的时候把某个id名字写错了，他没法找到
  于是我就知道我的代码为什么报错了,是因为我使用了hashmap.put(key,value) 时候中
  使用了R.string.title_chapter1 等 stringId,然而 ViewPager 中的 Adapter
  返回使用 hashMap.get(postion) , 使得ViewPager找不到对应的 fragemnt 而报null

  ```
      Process: com.yueyue.glidedemo, PID: 32529
      java.lang.NullPointerException: Attempt to invoke virtual method 'java.lang.Class java.lang.Object.getClass()' on a null object reference
          at android.support.v4.app.BackStackRecord.doAddOp(BackStackRecord.java:392)
          at android.support.v4.app.BackStackRecord.add(BackStackRecord.java:387)
          at android.support.v4.app.FragmentPagerAdapter.instantiateItem(FragmentPagerAdapter.java:104)
          at android.support.v4.view.ViewPager.addNewItem(ViewPager.java:1004)
          at android.support.v4.view.ViewPager.populate(ViewPager.java:1152)
          at android.support.v4.view.ViewPager.populate(ViewPager.java:1086)
          at android.support.v4.view.ViewPager.onMeasure(ViewPager.java:1616)
          at android.view.View.measure(View.java:19762)
          at android.view.ViewGroup.measureChildWithMargins(ViewGroup.java:6122)
          at android.widget.LinearLayout.measureChildBeforeLayout(LinearLayout.java:1464)
          at android.widget.LinearLayout.measureVertical(LinearLayout.java:758)
          at android.widget.LinearLayout.onMeasure(LinearLayout.java:640)
          at android.view.View.measure(View.java:19762)
          at android.view.ViewGroup.measureChildWithMargins(ViewGroup.java:6122)
          at android.widget.FrameLayout.onMeasure(FrameLayout.java:185)
          at android.support.v7.widget.ContentFrameLayout.onMeasure(ContentFrameLayout.java:141)
          at android.view.View.measure(View.java:19762)
          at android.view.ViewGroup.measureChildWithMargins(ViewGroup.java:6122)
          at android.widget.LinearLayout.measureChildBeforeLayout(LinearLayout.java:1464)
          at android.widget.LinearLayout.measureVertical(LinearLayout.java:758)
          at android.widget.LinearLayout.onMeasure(LinearLayout.java:640)
          at android.view.View.measure(View.java:19762)
          at android.view.ViewGroup.measureChildWithMargins(ViewGroup.java:6122)
          at android.widget.FrameLayout.onMeasure(FrameLayout.java:185)
          at android.view.View.measure(View.java:19762)
          at android.view.ViewGroup.measureChildWithMargins(ViewGroup.java:6122)
          at android.widget.LinearLayout.measureChildBeforeLayout(LinearLayout.java:1464)
          at android.widget.LinearLayout.measureVertical(LinearLayout.java:758)
          at android.widget.LinearLayout.onMeasure(LinearLayout.java:640)
          at android.view.View.measure(View.java:19762)
          at android.view.ViewGroup.measureChildWithMargins(ViewGroup.java:6122)
          at android.widget.FrameLayout.onMeasure(FrameLayout.java:185)
          at com.android.internal.policy.DecorView.onMeasure(DecorView.java:690)
          at android.view.View.measure(View.java:19762)
          at android.view.ViewRootImpl.performMeasure(ViewRootImpl.java:2308)
          at android.view.ViewRootImpl.measureHierarchy(ViewRootImpl.java:1395)
          at android.view.ViewRootImpl.performTraversals(ViewRootImpl.java:1644)
          at android.view.ViewRootImpl.doTraversal(ViewRootImpl.java:1283)
          at android.view.ViewRootImpl$TraversalRunnable.run(ViewRootImpl.java:6359)
          at android.view.Choreographer$CallbackRecord.run(Choreographer.java:873)
          at android.view.Choreographer.doCallbacks(Choreographer.java:685)
          at android.view.Choreographer.doFrame(Choreographer.java:621)
          at android.view.Choreographer$FrameDisplayEventReceiver.run(Choreographer.java:859)
          at android.os.Handler.handleCallback(Handler.java:754)
          at android.os.Handler.dispatchMessage(Handler.java:95)
          at android.os.Looper.loop(Looper.java:163)
          at android.app.ActivityThread.main(ActivityThread.java:6359)
          at java.lang.reflect.Method.invoke(Native Method)
          at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:880)
          at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:770)
  ```
* 下面这个崩溃是由于SwipeRefreshLayout没有给子布局而导致的,关于解决办法:[一不小心踩到 SwipeRefreshLayout 的坑里 - 简书 ](https://www.jianshu.com/p/c5f9f8033266)
  ```
  04-25 17:07:56.893 1456-1456/com.yueyue.glidedemo E/AndroidRuntime: FATAL EXCEPTION: main
      Process: com.yueyue.glidedemo, PID: 1456
      java.lang.NullPointerException: Attempt to invoke virtual method 'boolean android.view.View.canScrollVertically(int)' on a null object reference
          at android.support.v4.widget.SwipeRefreshLayout.canChildScrollUp(SwipeRefreshLayout.java:659)
          at android.support.v4.widget.SwipeRefreshLayout.onInterceptTouchEvent(SwipeRefreshLayout.java:682)
          at android.view.ViewGroup.dispatchTouchEvent(ViewGroup.java:2212)
          at android.view.ViewGroup.dispatchTransformedTouchEvent(ViewGroup.java:2671)
          at android.view.ViewGroup.dispatchTouchEvent(ViewGroup.java:2301)
          at android.view.ViewGroup.dispatchTransformedTouchEvent(ViewGroup.java:2671)
          at android.view.ViewGroup.dispatchTouchEvent(ViewGroup.java:2301)
          at android.view.ViewGroup.dispatchTransformedTouchEvent(ViewGroup.java:2671)
          at android.view.ViewGroup.dispatchTouchEvent(ViewGroup.java:2301)
          at android.view.ViewGroup.dispatchTransformedTouchEvent(ViewGroup.java:2671)
          at android.view.ViewGroup.dispatchTouchEvent(ViewGroup.java:2301)
          at android.view.ViewGroup.dispatchTransformedTouchEvent(ViewGroup.java:2671)
          at android.view.ViewGroup.dispatchTouchEvent(ViewGroup.java:2301)
          at android.view.ViewGroup.dispatchTransformedTouchEvent(ViewGroup.java:2671)
          at android.view.ViewGroup.dispatchTouchEvent(ViewGroup.java:2301)
          at android.view.ViewGroup.dispatchTransformedTouchEvent(ViewGroup.java:2671)
          at android.view.ViewGroup.dispatchTouchEvent(ViewGroup.java:2301)
          at android.view.ViewGroup.dispatchTransformedTouchEvent(ViewGroup.java:2671)
          at android.view.ViewGroup.dispatchTouchEvent(ViewGroup.java:2301)
          at com.android.internal.policy.DecorView.superDispatchTouchEvent(DecorView.java:414)
          at com.android.internal.policy.PhoneWindow.superDispatchTouchEvent(PhoneWindow.java:1810)
          at android.app.Activity.dispatchTouchEvent(Activity.java:3196)
          at android.support.v7.view.WindowCallbackWrapper.dispatchTouchEvent(WindowCallbackWrapper.java:68)
          at android.support.v7.view.WindowCallbackWrapper.dispatchTouchEvent(WindowCallbackWrapper.java:68)
          at com.android.internal.policy.DecorView.dispatchTouchEvent(DecorView.java:376)
          at android.view.View.dispatchPointerEvent(View.java:10180)
          at android.view.ViewRootImpl$ViewPostImeInputStage.processPointerEvent(ViewRootImpl.java:4492)
          at android.view.ViewRootImpl$ViewPostImeInputStage.onProcess(ViewRootImpl.java:4357)
          at android.view.ViewRootImpl$InputStage.deliver(ViewRootImpl.java:3897)
          at android.view.ViewRootImpl$InputStage.onDeliverToNext(ViewRootImpl.java:3950)
          at android.view.ViewRootImpl$InputStage.forward(ViewRootImpl.java:3916)
          at android.view.ViewRootImpl$AsyncInputStage.forward(ViewRootImpl.java:4043)
          at android.view.ViewRootImpl$InputStage.apply(ViewRootImpl.java:3924)
          at android.view.ViewRootImpl$AsyncInputStage.apply(ViewRootImpl.java:4100)
          at android.view.ViewRootImpl$InputStage.deliver(ViewRootImpl.java:3897)
          at android.view.ViewRootImpl$InputStage.onDeliverToNext(ViewRootImpl.java:3950)
          at android.view.ViewRootImpl$InputStage.forward(ViewRootImpl.java:3916)
          at android.view.ViewRootImpl$InputStage.apply(ViewRootImpl.java:3924)
          at android.view.ViewRootImpl$InputStage.deliver(ViewRootImpl.java:3897)
          at android.view.ViewRootImpl.deliverInputEvent(ViewRootImpl.java:6268)
          at android.view.ViewRootImpl.doProcessInputEvents(ViewRootImpl.java:6242)
          at android.view.ViewRootImpl.enqueueInputEvent(ViewRootImpl.java:6203)
          at android.view.ViewRootImpl$WindowInputEventReceiver.onInputEvent(ViewRootImpl.java:6374)
          at android.view.InputEventReceiver.dispatchInputEvent(InputEventReceiver.java:187)
          at android.os.MessageQueue.nativePollOnce(Native Method)
          at android.os.MessageQueue.next(MessageQueue.java:323)
          at android.os.Looper.loop(Looper.java:142)
          at android.app.ActivityThread.main(ActivityThread.java:6359)
          at java.lang.reflect.Method.invoke(Native Method)
          at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:880)
          at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:770)
  ```

