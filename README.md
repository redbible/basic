# redbible

[![Release](https://jitpack.io/v/jitpack/android-example.svg)](https://jitpack.io/#redbible/basic)

Example Android Basic DataBinding library project that works with jitpack.io.
https://jitpack.io/#redbible/basic

# Libs
This project run with koin2, rxAndroid, retrofit, gson, MVVM model.

# TDD
maybe you can develop with TDD. you can ran unitTest with ViewModel, Repository. 

# Usage
Add it to your build.gradle with:
```gradle
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```
and:

```gradle
dependencies {
    implementation 'com.github.redbible:basic:{latest version}'
}
```

## Adding a sample app 

If you add a sample app to the same repo then your app needs to have a dependency on the library. To do this in your app/build.gradle add:

```gradle
    dependencies {
        implementation project(':baseview')
    }
```

it support Activity, Fragment, BottomUpSheet, Transparent Dialog, Easy addable RecyclerView, ViewPager2 with Databinding.

it support recyclerView adapter, set ViewType, add view by ViewType easily.

it support ViewModel with disposer, it can make observer dispose easily with activity(or fragment) lifecycle.
