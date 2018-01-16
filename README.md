# BubbleLayout
Android 实现气泡布局
## Demo:
 <img src="/image/demo.png" width = "540" height = "960" alt="图片名称" align=center />
 
## 用法
1. 复制[BubbleLayout](https://github.com/wangyiwy/BubbleLayout/blob/master/app/src/main/java/me/wy/app/BubbleLayout.java)到项目中，
2. 将以下熟悉复制到res/values/attrs中,
~~~
    <declare-styleable name="BubbleLayout">
        <attr name="background_color" format="color" />
        <attr name="shadow_color" format="color" />
        <attr name="shadow_size" format="dimension" />
        <attr name="radius" format="dimension" />
        <attr name="direction" format="enum">
            <enum name="left" value="1" />
            <enum name="top" value="2" />
            <enum name="right" value="3" />
            <enum name="bottom" value="4" />
        </attr>
        <attr name="offset" format="dimension" />
    </declare-styleable>
~~~
3.xml中使用，参考[activity_main](https://github.com/wangyiwy/BubbleLayout/blob/master/app/src/main/res/layout/activity_main.xml)
~~~
    <me.wy.app.BubbleLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:padding="16dp"
        //背景颜色
        app:background_color="#FF4081"
        //三角形方向
        app:direction="left"
        //三角形相对偏移量
        app:offset="-40dp"
        //圆角大小
        app:radius="4dp"
        //阴影颜色
        app:shadow_color="#999999"
        //阴影大小
        app:shadow_size="4dp">

        ...
    </me.wy.app.BubbleLayout>
~~~
