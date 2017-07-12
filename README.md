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