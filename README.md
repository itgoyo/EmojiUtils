### Emoji表情过滤


![](/Screenshot/record.gif)

Add it in your root build.gradle at the end of repositories:
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

Step 2. Add the dependency

```
	dependencies {
	        compile 'com.github.itgoyo:EmojiUtils:v1.0'
	}
```

Emoji表情字符集 http://apps.timwhitlock.info/emoji/tables/unicode

根据以上整理出范围，能过滤掉Emoji表情，还有一些个别的四字节字符。

```
!((first == 0x0) ||
                        (first == 0x9) ||
                        (first == 0xA) ||
                        (first == 0xD) ||
                        ((first >= 0x20) && (first <= 0xD7FF)) ||
                        ((first >= 0xE000) && (first <= 0xFFFD)) ||
                        ((first >= 0x10000)))||


                (first == 0xa9 || first == 0xae || first == 0x2122 ||
                        first == 0x3030 || (first >= 0x25b6 && first <= 0x27bf) ||
                        first == 0x2328 || (first >= 0x23e9 && first <= 0x23fa))
                || ((first >= 0x1F000 && first <= 0x1FFFF))
                || ((first >= 0x2702) && (first <= 0x27B0))
                || ((first >= 0x1F601) && (first <= 0x1F64F))
```

#### [彻底搞懂字符编码(unicode,mbcs,utf-8,utf-16,utf-32,big endian,little endian...)](http://blog.csdn.net/haiross/article/details/46360021)

