package mx.ulab.reto2.transporte

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import mx.ulab.reto2.Constants
import mx.ulab.reto2.databinding.ActivityTransporteBinding

class TransporteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransporteBinding
    transporteType = object : TypeToken<List<TransporteData>>() {}.type
    val transporteDataList: List<TransporteData> =
        Gson().fromJson(Constants.transporteJson, transporteType)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransporteBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false)

        var items = arrayListOf<String>()
        transporteDataList.forEach {
            items.add(it.nombre_ruta)
        }
        var paradasTransporte = emptyList<String>()
        val spinner: Spinner = binding.searchView
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener
        {
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
                paradasTransporte = transporteDataList[position].paradas
                val costo = transporteDataList[position].costo
                val frecuencia = transporteDataList[position].frecuencia
                val status = transporteDataList[position].status
                val tipo = transporteDataList[position].tipo_transporte
                recyclerView.adapter = TransporteAdapter(paradasTransporte, costo,
                    frecuencia, status, tipo)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
// Otra lógica para cuando no se selecciona nada
            }
        }

        binding.backButton.setOnClickListener {
            finish()
        }
    }
