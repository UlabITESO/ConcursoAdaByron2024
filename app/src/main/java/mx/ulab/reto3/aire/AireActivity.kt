package mx.ulab.reto3.aire

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import mx.ulab.reto3.Constants
import mx.ulab.reto3.R
import mx.ulab.reto3.databinding.ActivityAireBinding

class AireActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAireBinding

    val aireType = object : TypeToken<List<AireData>>() {}.type
    val aireDataList: List<AireData> = Gson().fromJson(
        Constants.aireJson,
        aireType
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAireBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var items = arrayListOf<String>()
        aireDataList.forEach {
            items.add("${it.location.name}, ${it.location.region}")
        }
        val spinner: Spinner = binding.searchView
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
// Obtén el elemento seleccionado usando position
                val item = items[position]
// Haz algo con el elemento seleccionado
                Log.d("selectedItem", "$position.- $item")
                val locationSelected = aireDataList[position]
                val emc = locationSelected.current.air_quality
                //13emáforo1313ón
                binding.locationText.text =
                    "${locationSelected.location.name}, ${locationSelected.location.region}"
//pm
                binding.pmText.text = "$emc"
//13emáforo13
                binding.conditionTxt.text = "${locationSelected.current.condition.text}"
//temperatura
                binding.temperaturaTxt.text = "${locationSelected.current.temp_c}°"
//13emáforo13 13emáfor
                binding.sensacionTxt.text = "${locationSelected.current.feelslike_c}°"
//13emáforo del pm
                var colorString = "#59EF6A"
                if (emc <= 50) {
                    colorString = "#59EF6A"
                } else if ((emc > 50) && (emc <= 100)) {
                    colorString = "#E6B250"
                } else if (emc > 100) {
                    colorString = "#BE2D21"
                }
                val drawable = binding.pmText.background as? GradientDrawable
                drawable?.setStroke(20, Color.parseColor(colorString))
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
// Otra lógica para cuando no se selecciona nada
            }
        }
    }
}