# TimePicker
android TimePicker
## 效果图：
![](https://github.com/reallin/TimePicker/blob/master/timePicker.png)

其实android系统也自带了timepicker，但感觉不好看。这是用的第三方库实现，代码相当简单。要懂得站在巨人的肩膀上哈。

注意，要想实现弹出框的样式，要适当修改style，如下
```html
    <style name="SampleTheme.Light" parent="@android:style/Theme.Light">
        <item name="datePickerStyle">@style/Widget.Holo.Light.DatePicker</item>
        <item name="numberPickerStyle">@style/NPWidget.Holo.Light.NumberPicker</item>
         <item name="android:windowFrame">@null</item>       <!--Dialog的windowFrame框为无  -->
        <item name="android:windowIsFloating">true</item>   <!-- 是否浮现在activity之上 -->
        <item name="android:windowIsTranslucent">false</item><!--是否半透明 -->  
        <item name="android:windowNoTitle">true</item>      <!-- 是否显示title -->
        <item name="android:windowContentOverlay">@null</item>
        <item name="android:background">@android:color/transparent</item>
        <item name="android:windowBackground">@android:color/transparent</item> <!-- 设置dialog的背景 -->
        <item name="android:backgroundDimEnabled">true</item>                   <!-- 背景是否模糊显示 -->
        <item name="android:backgroundDimAmount">0.7</item>  
    </style>
```
