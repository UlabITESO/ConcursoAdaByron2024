package mx.ulab.reto3.aire

import com.google.gson.annotations.SerializedName

data class AireData(
    @SerializedName("location") val location: Location,
    @SerializedName("current") val current: Current
)
data class Location(
    @SerializedName("name") val name: String,
    @SerializedName("region") val region: String,
    @SerializedName("country") val country: String,
    @SerializedName("lat") val lat: Double,
    @SerializedName("lon") val lon: Double
)
data class Current(
    @SerializedName("temp_c") val temp_c: Double,
    @SerializedName("feelslike_c") val feelslike_c: Double,
    @SerializedName("wind_kph") val wind_kph: Double,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("condition") val condition: Condition,
    @SerializedName("uv") val uv: Double,
    @SerializedName("air_quality") val air_quality: Int
)
data class Condition(
    @SerializedName("text") val text: String
)