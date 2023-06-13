package gbp.firebase.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import gbp.firebase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.pbConnectionMenu.visibility = View.GONE
    }

    fun clickStudents(view: View){
        val intent = Intent(this, StudentStaffReciclerView::class.java)
        val listType = Bundle()
        listType.putString("Type", "students")
        intent.putExtras(listType)
        startActivity(intent)
    }

    fun clickStaff(view: View){
        val intent = Intent(this, StudentStaffReciclerView::class.java)
        val listType = Bundle()
        listType.putString("Type", "staff")
        intent.putExtras(listType)
        startActivity(intent)
    }

}