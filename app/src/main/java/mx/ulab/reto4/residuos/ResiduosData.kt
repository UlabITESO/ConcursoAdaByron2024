package mx.ulab.reto4.residuos

data class PuntoDeposito(
    val id: Int,
    val tipoResiduo: String,
    val direccion: String,
    val latitud: Double,
    val longitud: Double,
    val horario: String
)
data class ResiduosData(
    val puntosDeposito: List<PuntoDeposito>
)