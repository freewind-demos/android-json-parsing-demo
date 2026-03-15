# Android JSON 解析演示

## 简介

本 Demo 演示 Android 中 JSON 数据的基本解析方法，包括 JSONObject 和 JSONArray 的使用。

## 基本原理

JSON（JavaScript Object Notation）是一种轻量级的数据交换格式，广泛用于客户端与服务器之间的数据传输。Android 提供了原生的 JSON 解析类，无需引入额外库。

Android 原生 JSON 解析的主要类：
- **JSONObject**：用于解析 JSON 对象，包含键值对
- **JSONArray**：用于解析 JSON 数组，包含有序的值列表
- **JSONException**：解析过程中的异常类

## 启动和使用

### 环境要求
- Android Studio
- JDK 17
- Gradle 8.x

### 安装和运行

1. 用 Android Studio 打开项目
2. 连接 Android 设备或模拟器
3. 点击 Run 运行

### 使用方法
- 运行后将看到解析后的 JSON 数据展示在屏幕上

## 教程

### 什么是 JSON？

JSON 是一种常用的数据格式，具有以下特点：
- 人类易于阅读和编写
- 机器易于解析和生成
- 基于 JavaScript 对象表示法的子集

JSON 支持的数据类型：
- 对象（Object）：键值对的集合
- 数组（Array）：有序的值列表
- 字符串（String）
- 数字（Number）
- 布尔值（true/false）
- 空值（null）

### JSON 对象解析

JSON 对象的格式：
```json
{
    "name": "张三",
    "age": 25,
    "email": "zhangsan@example.com"
}
```

解析代码：
```kotlin
// JSON 字符串
val jsonString = """
{
    "name": "张三",
    "age": 25,
    "email": "zhangsan@example.com"
}
""".trimIndent()

// 创建 JSONObject
val jsonObject = JSONObject(jsonString)

// 读取各种类型的数据
val name = jsonObject.getString("name")
val age = jsonObject.getInt("age")
val email = jsonObject.getString("email")
```

### JSON 数组解析

JSON 数组的格式：
```json
[
    {"name": "张三", "age": 25},
    {"name": "李四", "age": 30},
    {"name": "王五", "age": 28}
]
```

解析代码：
```kotlin
val jsonString = """[{"name":"张三","age":25},{"name":"李四","age":30}]"""

// 创建 JSONArray
val jsonArray = JSONArray(jsonString)

// 遍历数组
for (i in 0 until jsonArray.length()) {
    val obj = jsonArray.getJSONObject(i)
    val name = obj.getString("name")
    val age = obj.getInt("age")
}
```

### 嵌套 JSON 解析

复杂的嵌套 JSON：
```json
{
    "user": {
        "name": "张三",
        "address": {
            "city": "北京",
            "street": "朝阳区"
        }
    },
    "hobbies": ["篮球", "游泳", "编程"]
}
```

解析代码：
```kotlin
val jsonObject = JSONObject(jsonString)

// 获取嵌套对象
val user = jsonObject.getJSONObject("user")
val name = user.getString("name")

// 获取嵌套的嵌套对象
val address = user.getJSONObject("address")
val city = address.getString("city")

// 获取数组
val hobbies = jsonObject.getJSONArray("hobbies")
for (i in 0 until hobbies.length()) {
    val hobby = hobbies.getString(i)
}
```

### 安全获取数据

使用 optString、optInt 等方法可以避免空值异常：

```kotlin
// 如果键不存在，getString 会抛异常
val name = jsonObject.getString("name")

// 如果键不存在，optString 返回空字符串
val name = jsonObject.optString("name", "默认值")

// 如果键不存在，optInt 返回 0
val age = jsonObject.optInt("age", 0)
```

### 注意事项

1. **异常处理**：JSON 解析可能抛出 JSONException，建议使用 try-catch 包裹
2. **空值处理**：使用 optXxx 方法可以安全处理空值
3. **类型检查**：获取数据前可以使用 has() 检查键是否存在
4. **推荐库**：对于复杂场景，推荐使用 Gson、Moshi 等库简化代码

## 关键代码详解

### MainActivity.kt

```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. 获取 TextView 组件
        val textView = findViewById<TextView>(R.id.textView)

        // 2. 准备 JSON 字符串
        val jsonString = """
        {
            "name": "张三",
            "age": 25,
            "email": "zhangsan@example.com"
        }
        """.trimIndent()

        // 3. 创建 JSONObject 并解析
        val jsonObject = JSONObject(jsonString)

        // 4. 提取数据并格式化显示
        val result = """
            姓名: ${jsonObject.getString("name")}
            年龄: ${jsonObject.getInt("age")}
            邮箱: ${jsonObject.getString("email")}
        """.trimIndent()

        // 5. 设置到 TextView
        textView.text = result
    }
}
```

### activity_main.xml

```xml
<!-- 根布局：垂直线性布局 -->
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <!-- 标题 -->
    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="JSON 解析演示"
        android:textSize="20sp"
        android:textStyle="bold"
        android:gravity="center"
        android:paddingBottom="16dp" />

    <!-- 显示解析结果的 TextView -->
    <TextView
        android:id="@+id/textView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        <!-- 使用等宽字体显示 JSON 数据 -->
        android:fontFamily="monospace" />
</LinearLayout>
```

### 常用方法对比

| 方法 | 作用 | 键不存在时 |
|------|------|-----------|
| getString() | 获取字符串 | 抛异常 |
| optString() | 获取字符串 | 返回空字符串 |
| getInt() | 获取整数 | 抛异常 |
| optInt() | 获取整数 | 返回 0 |
| getBoolean() | 获取布尔值 | 抛异常 |
| optBoolean() | 获取布尔值 | 返回 false |
| getJSONObject() | 获取嵌套对象 | 抛异常 |
| optJSONObject() | 获取嵌套对象 | 返回 null |
| has() | 检查键是否存在 | - |
