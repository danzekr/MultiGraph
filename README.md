# MultiGraph
![](https://img.shields.io/badge/platform-android-orange.svg)
![](https://img.shields.io/badge/language-kotlin-yellow.svg)
![](https://jitpack.io/v/com.iwdael/multigraph.svg)
![](https://img.shields.io/badge/build-passing-brightgreen.svg)
![](https://img.shields.io/badge/license-apache--2.0-green.svg)
![](https://img.shields.io/badge/api-19+-green.svg)

九图，适用于类似微信群头像的需求，以及社区九图展示。

<img src="screenshot/QtScrcpy_20211227_144613_601.webp" width="200px"/> <img src="screenshot/QtScrcpy_20211227_144550_769.webp" width="200px"/>

<img src="screenshot/QtScrcpy_20211227_144600_171.webp" width="200px"/> <img src="screenshot/QtScrcpy_20211227_144610_553.webp" width="200px"/>

## 示例

在使用MultiGraph之前，请先设置全局图片加载的方式。
```
        MultiGraph.defaultMultiGraphLoader = object :MultiGraphLoader{
            override fun load(view: ImageView, data: Any) {
            }
        }
```
```
    <com.iwdael.multigraph.MultiGraph
        android:layout_width="300dp"
        android:layout_height="300dp"
        android:padding="20dp"
        app:gapSize="2dp"
        app:mode="AVATAR" />
```
内容点击监听
```
    MultiGraph.setOnGraphClickListener
```

## 如何配置
将本仓库引入你的项目:
### Step 1. 添加JitPack仓库到Build文件
合并以下代码到项目根目录下的build.gradle文件的repositories尾。

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

### Step 2. 添加依赖
合并以下代码到需要使用的application Module的dependencies尾。
```Java
	dependencies {
	  ...
          compile 'com.iwdael:multigraph:$version'
	}
```