# BGButton
<h3 align="center">基本介绍</h3>
<p>　　支持在布局中直接设置圆角、渐变色、描边、按压色等属性的自定义Button，同时保留了按压阴影效果</p>
<p>　　旨在减少创建shape/selector资源以达到节省开发时间的目的</p>

<h3>显示效果</h3>
<img  src="https://github.com/ziwenL/BGButton/blob/master/readme/images/example_dynamic.gif?raw=true" alt="xml中静态设置属性" />
<p >xml中静态设置属性</p>
<img src="https://github.com/ziwenL/BGButton/blob/master/readme/images/example_dynamic.gif?raw=true" alt="代码中动态设置属性"/>
<p >代码中动态设置属性</p>
<h3 >自定义属性</h3>

|name|format|description|
|:---:|:---:|:---:|
| radius | dimension |设置圆角值
| topLeftRadius | dimension |设置左上角圆角值，当radius大于0时无效
| topRightRadius | dimension |设置右上角圆角值，当radius大于0时无效
| bottomLeftRadius | dimension |设置左下角圆角值，当radius大于0时无效
| bottomRightRadius | dimension |设置右下角圆角值，当radius大于0时无效
| backgroundColor | color |设置背景色
| pressedColor | color |设置按压时背景色,未设置时自动取背景色更浅色为按压色
| unableColor | color |设置不可点击时背景色
| backgroundColorStart | color |设置渐变背景色起始颜色
| backgroundColorEnd | color |设置渐变背景色结束颜色
| backgroundDrawable | reference |设置图片背景
| showShadow | boolean |设置是否显示按压阴影效果
| strokeWidth | dimension |设置描边宽度
| strokeColor | color |设置描边颜色
| unableStrokeColor | color |设置不可点击时描边颜色
| pressedStrokeColor |color| 设置按压时描边颜色

<h3>使用方法</h3>
<h5>第一种：（建议使用）</h5>
<p>直接copy本项目libary中widget包下的BGButton类、res-values文件夹下的attrs.xml、res-animator文件夹下的selector_bg_button_animator.xml到您的项目</p>
 <h5>第二种：</h5>
 <p>根目录build.gradle的allprojects——repositories中添加
  <br/>
<div class="highlight highlight-source-groovy-gradle"><pre>  <span class="pl-s"><span class="pl-pds"></span>maven { url 'https://jitpack.io' }<span class="pl-pds"></span></span></pre></div>
 </p>
 <br/>
 module的build.gradle下添加
<div class="highlight highlight-source-groovy-gradle"><pre> implementation <span class="pl-s"><span class="pl-pds">'</span>com.github.ziwenL:BGButton:v1.0<span class="pl-pds">'</span></span></pre></div>
 </p>
 
 <p>更多的使用效果可参考demo，代码量不多，基本都有注释。感谢您的支持。</p>
 
<h3>About Me<h3>
<ul>
<li>
<p>Email: ziwen.lan@foxmail.com</p>
</li>
</ul>
