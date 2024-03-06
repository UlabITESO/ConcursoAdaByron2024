package mx.ulab.reto3

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.ulab.reto3.aire.AireActivity

class HomeAdapter(private val items: List<Any>, private val context: Context)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val VIEW_AIRE = 2
    }
    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is Aire -> VIEW_AIRE
            else -> throw IllegalArgumentException("Tipo de elemento desconocido en la posición $position")
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_AIRE -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.aire_layout,
                        parent,false)
                AireViewHolder(view)
            }
            else -> throw IllegalArgumentException("Tipo de vista desconocido")
        }
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position:
    Int) {
        when (holder) {
            is AireViewHolder -> {
                val item = items[position] as Aire
                holder.bind(item, context)
            }
        }
    }
    override fun getItemCount() = items.size

    class AireViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Aire, context: Context) {
            val textView : TextView = itemView.findViewById(R.id.titleTV)
            textView.text = item.texto
            itemView.setOnClickListener {
                val intent = Intent(context, AireActivity::class.java)
                context.startActivity(intent)
            }
        }
    }
}
data class Aire(val texto: String)