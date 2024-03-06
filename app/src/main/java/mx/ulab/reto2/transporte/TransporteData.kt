package mx.ulab.reto2.transporte

import com.google.gson.annotations.SerializedName

data class TransporteData(
    @SerializedName("nombre_ruta") val nombre_ruta: String,
    @SerializedName("tipo_transporte") val tipo_transporte: String,
    @SerializedName("costo") val costo: String,
    @SerializedName("frecuencia") val frecuencia: String,
    @SerializedName("status") val status: String,
    @SerializedName("paradas") val paradas: List<String>
)