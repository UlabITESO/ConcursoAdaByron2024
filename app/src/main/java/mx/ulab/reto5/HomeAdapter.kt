package mx.ulab.reto5

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HomeAdapter(private val items: List<Any>, private val context: Context)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val VIEW_TRANSPORTE = 1
        private const val VIEW_AIRE = 2
        private const val VIEW_AGUA = 3
        private const val VIEW_RESIDUOS = 4
    }
    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is Transporte -> VIEW_TRANSPORTE
            is Aire -> VIEW_AIRE
            is Agua -> VIEW_AGUA
            is Residuos -> VIEW_RESIDUOS
            else -> throw IllegalArgumentException("Tipo de elemento desconocido en la posición $position")
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TRANSPORTE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.transporte_layout, parent, false)
                TransporteViewHolder(view)
            }
            VIEW_AIRE -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.aire_layout,
                        parent,false)
                AireViewHolder(view)
            }
            VIEW_AGUA -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.agua_layout,
                        parent,false)
                AguaViewHolder(view)
            }
            VIEW_RESIDUOS -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.residuos_layout,
                        parent,false)
                ResiduosViewHolder(view)
            }
            else -> throw IllegalArgumentException("Tipo de vista desconocido")
        }
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position:
    Int) {
        when (holder) {
            is TransporteViewHolder -> {
                val item = items[position] as Transporte
                holder.bind(item, context)
            }
            is AireViewHolder -> {
                val item = items[position] as Aire
                holder.bind(item, context)
            }
            is AguaViewHolder -> {
                val item = items[position] as Agua
                holder.bind(item, context)
            }
            is ResiduosViewHolder -> {
                val item = items[position] as Residuos
                holder.bind(item, context)
            }
        }
    }
    override fun getItemCount() = items.size
    class TransporteViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(item: Transporte, context: Context) {
            val textView : TextView = itemView.findViewById(R.id.titleTV)
            textView.text = item.texto
            itemView.setOnClickListener {
                Log.d("HomeAdapter", "tap tap tap")
            }
        }
    }

    class AireViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Aire, context: Context) {
            val textView : TextView = itemView.findViewById(R.id.titleTV)
            textView.text = item.texto
            itemView.setOnClickListener {
                Log.d("HomeAdapter", "tap tap tap")
            }
        }
    }
    class AguaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Agua, context: Context) {
            val textView : TextView = itemView.findViewById(R.id.titleTV)
            textView.text = item.texto
            itemView.setOnClickListener {
                Log.d("HomeAdapter", "tap tap tap")
            }
        }
    }
    class ResiduosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        fun bind(item: Residuos, context: Context) {
            val textView : TextView = itemView.findViewById(R.id.titleTV)
            textView.text = item.texto
            itemView.setOnClickListener {
                Log.d("HomeAdapter", "tap tap tap")
            }
        }
    }
}
data class Transporte(val texto: String)
data class Aire(val texto: String)
data class Agua(val texto: String)
data class Residuos(val texto: String)