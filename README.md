# Android JSON 解析演示

## 简介

本 Demo 演示 Android 中 JSON 的解析方法。

## 基本原理

使用 JSONObject 解析 JSON 字符串。

## 教程

```kotlin
val jsonString = '{"name":"张三","age":25}'
val jsonObject = JSONObject(jsonString)
val name = jsonObject.getString("name")
val age = jsonObject.getInt("age")
```

## 注意事项

1. JSONObject 用于解析对象
2. JSONArray 用于解析数组
3. 推荐使用 Gson 库简化解析
