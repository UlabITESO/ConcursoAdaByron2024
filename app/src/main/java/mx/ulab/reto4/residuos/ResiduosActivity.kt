package mx.ulab.reto4.residuos

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import mx.ulab.reto4.Constants
import mx.ulab.reto4.R
import mx.ulab.reto4.databinding.ActivityResiduosBinding

class ResiduosActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActivityResiduosBinding

    private lateinit var gMap: GoogleMap
    private lateinit var mapView: MapView
    val residuosType = object : TypeToken<ResiduosData>() {}.type
    val residuosList: ResiduosData = Gson().fromJson(
        Constants.residuosJson,
        residuosType)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResiduosBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        mapView = binding.mapView
        mapView.onCreate(savedInstanceState)
        mapView.onResume()
        mapView.getMapAsync(this)

        val searchList = residuosList.puntosDeposito
//0 organico
        binding.organico.setOnClickListener {
            gMap.clear()
            val organicoList = searchList.filter { it.tipoResiduo == "Orgánico" }
            Log.d("depositos", organicoList.toString())
            organicoList.forEach {
            }
            setupBackground(0)
        }
//1 plasticos
        binding.plasticos.setOnClickListener {
            gMap.clear()
            val plasticoList = searchList.filter { it.tipoResiduo == "Plástico" }
            Log.d("depositos", plasticoList.toString())
            plasticoList.forEach {
            }
            setupBackground(1)
        }
//2 Papel y carton
        binding.carton.setOnClickListener {
            gMap.clear()
            val cartonList = searchList.filter { it.tipoResiduo == "Papel y Cartón" }
            Log.d("depositos", cartonList.toString())
            cartonList.forEach {
            }
            setupBackground(2)
        }
//3 general
        binding.general.setOnClickListener {
            gMap.clear()
            val generalList = searchList.filter { it.tipoResiduo == "General" }
            Log.d("depositos", generalList.toString())
            generalList.forEach {
            }
            setupBackground(3)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        gMap = googleMap
        gMap.uiSettings.isScrollGesturesEnabled = true
        gMap.uiSettings.isZoomGesturesEnabled = true
// Add a marker in ITESO and move the camera
        val iteso = LatLng(20.6082360, -103.4152069)
        gMap.addMarker(
            MarkerOptions()
                .position(iteso)
                .title("ITESO"))
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(iteso, 16f))
// Check for permission
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
// Request permissions if necessary
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
            return
        }
        gMap.isMyLocationEnabled = true
    }

    fun drawMarkers(deposito: PuntoDeposito) {
        val position = LatLng(deposito.latitud, deposito.longitud)
        gMap.addMarker(
            MarkerOptions()
                .position(position)
            //.title(deposito.direccion)
        )
    }

    private fun setupBackground(tipoBasura: Int) {
        when (tipoBasura) {
            0 -> {
                binding.organico.setBackgroundColor(Color.parseColor("#38CC82"))
                binding.plasticos.setBackgroundColor(Color.parseColor("#E7EAE9"))
                binding.carton.setBackgroundColor(Color.parseColor("#E7EAE9"))
                binding.general.setBackgroundColor(Color.parseColor("#E7EAE9"))
            }
            1 -> {
                binding.organico.setBackgroundColor(Color.parseColor("#E7EAE9"))
                binding.plasticos.setBackgroundColor(Color.parseColor("#38CC82"))
                binding.carton.setBackgroundColor(Color.parseColor("#E7EAE9"))
                binding.general.setBackgroundColor(Color.parseColor("#E7EAE9"))
            }
            2 -> {
                binding.organico.setBackgroundColor(Color.parseColor("#E7EAE9"))
                binding.plasticos.setBackgroundColor(Color.parseColor("#E7EAE9"))
                binding.carton.setBackgroundColor(Color.parseColor("#38CC82"))
                binding.general.setBackgroundColor(Color.parseColor("#E7EAE9"))
            }
            else -> {
                binding.organico.setBackgroundColor(Color.parseColor("#E7EAE9"))
                binding.plasticos.setBackgroundColor(Color.parseColor("#E7EAE9"))
                binding.carton.setBackgroundColor(Color.parseColor("#E7EAE9"))
                binding.general.setBackgroundColor(Color.parseColor("#38CC82"))
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }
    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }
    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}