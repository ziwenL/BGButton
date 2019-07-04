<html>
<body>
<h1 align="center">BGButton</h1>
<p>BGButton是继承于AppCompatButton的自定义控件</p>
<p>目的是为了减少根据UI图设置Button样式时的shape及selector的重复创建工作</p>
<p>通过radius、backgroundColor、strokeColor、strokeWidth、pressedColor等自定义属性，在绘制xml布局的时候就能一步设置圆角、正常背景色、描边颜色、描边宽度及按压时颜色，并保留了Android Button特有的按压阴影效果</p>
<p>入侵性低，只有一个类、一个动画和一个styleable，代码注释清晰，可自行调整，可直接替换原项目里的Button控件，建议直接copy使用</p>
<p>如果能为您带来一些帮助，是我的荣幸</p>
 <br/>
<h3>显示效果</h3>
<img src="https://github.com/ziwenL/BGButton/blob/master/readme/images/readme_demonstration.gif?raw=true" />
<p>布局代码示例</p>
<img src="https://github.com/ziwenL/BGButton/blob/master/readme/images/readme_code_demonstration.png?raw=true"/>
<br/>
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
 </body>
<html>
