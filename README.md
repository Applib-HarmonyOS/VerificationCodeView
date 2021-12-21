# VerificationView

A HMOS library which provides Verification code features.

## Source

Inspired by [yongming9011/VerificationCodeView](https://github.com/yongming9011/VerificationCodeView)

## Feature
VerificationView is a library which provides verification code where a 
verification code is a security protection method used by form owners to 
avoid Internet robots from abusing and spamming their forms

<img src="screenshots/VerificationCode.gif" width="500">


## Dependency
1. For using verificationview module in sample app, include the source code and add the below dependencies in entry/build.gradle to generate hap/support.har.
```groovy
    dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar', '*.har'])
    implementation project(path: ':verificationview')
    testImplementation 'junit:junit:4.13'
    ohosTestImplementation 'com.huawei.ohos.testkit:runner:1.0.0.100'
}
```
2. For using verificationview in separate application using har file, add the har file in the entry/libs folder and add the dependencies in entry/build.gradle file.
```groovy
	dependencies {
		implementation fileTree(dir: 'libs', include: ['*.har'])
		testImplementation 'junit:junit:4.13'
	}
```

## Usage
### Include following code in your layout
``` xml
<?xml version="1.0" encoding="utf-8"?>
 <com.zhangym.customview.VerificationCodeView
      ohos:id="$+id:verificationCodeView"
      ohos:width="0vp"
      ohos:height="50vp"
      ohos:weight="1"
      zhangym:interferenceCirclesCount="40"
      zhangym:interferenceCirclesRadius="5"
      zhangym:interferenceLinesCount="8"
      zhangym:isShowInterferenceCircles="true"
      zhangym:isShowInterferenceLines="true"
      zhangym:isTextBold="true"
      zhangym:textSize="26fp"
      zhangym:textSkewX="0"
      zhangym:verificationText="9D27" />
```

### Include following code in your activity
``` java 
super.setUIContent(ResourceTable.Layout_ability_main);
        initViews();
        login();
        mCodeView.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                String s = String.valueOf(mRandom.nextInt(10))
                        + String.valueOf(mRandom.nextInt(10))
                        + String.valueOf(mRandom.nextInt(10))
                        + String.valueOf(mRandom.nextInt(10));

                mCodeView.setVerificationText(s);
            }
        });
```
