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

