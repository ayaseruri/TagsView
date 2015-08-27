# TagsView
a lib which can creat tags easily

<img src="https://github.com/ayaseruri/TagsView/blob/master/demoapk_image/Screenshot_2015-08-15-19-48-24.png" with = "500" />

[DemoApk](https://github.com/ayaseruri/TagsView/blob/master/demoapk_image/app-debug.apk?raw=true)

# Maven
```xml
<groupId>andy.ayaseruri.tagsview</groupId>
<artifactId>lib</artifactId>
<version>1.0.1</version>
```
# Gradle
```java
dependencies {
        //...
        compile 'andy.ayaseruri.tagsview:lib:1.0.1@aar'
        //...
}
```

# Only 2 Steps:
1. Define yout tags view in xml like:
```xml
<andy.ayaseruri.lib.TagsView
        android:id="@+id/tags_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:textPaddingLeft="2dp"
        app:textPaddingRight="2dp"
        app:textPaddingTop="2dp"
        app:textPaddingBottom="1dp"
        app:textColor="#757575"
        app:textSize="13sp"
        app:tagsSpace="8dp"        //define the space between tags
        app:lineSpace="5dp"
        app:tagsBg="@drawable/tags_bg"
        app:maxlines="10"/>
```

2.call init method like:
```java
TagsView tagsView = (TagsView)findViewById(R.id.tags_view);
tagsView.init(ArrayList<String>() arrayList, OnClickListener onClickListener);
```
