package mx.ulab.reto3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.ulab.reto3.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    val items: List<Any> = listOf(
        Aire("Calidad del aire")
    )

    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false)

        recyclerView.adapter = HomeAdapter(items, this)
    }
}