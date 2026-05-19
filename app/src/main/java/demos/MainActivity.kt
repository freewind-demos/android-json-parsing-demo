package demos.android.json.parsing.demo

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val textView = findViewById<TextView>(R.id.textView)
        
        val jsonString = """
        {
            "name": "张三",
            "age": 25,
            "email": "zhangsan@example.com"
        }
        """.trimIndent()
        
        val jsonObject = JSONObject(jsonString)
        val result = """
            姓名: ${jsonObject.getString("name")}
            年龄: ${jsonObject.getInt("age")}
            邮箱: ${jsonObject.getString("email")}
        """.trimIndent()
        
        textView.text = result
    }
}
