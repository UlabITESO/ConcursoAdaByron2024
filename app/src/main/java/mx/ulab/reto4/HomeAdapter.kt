package mx.ulab.reto4

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.ulab.reto4.residuos.ResiduosActivity

class HomeAdapter(private val items: List<Any>, private val context: Context)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val VIEW_RESIDUOS = 4
    }
    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is Residuos -> VIEW_RESIDUOS
            else -> throw IllegalArgumentException("Tipo de elemento desconocido en la posición $position")
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerView.ViewHolder {
        return when (viewType) {
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
            is ResiduosViewHolder -> {
                val item = items[position] as Residuos
                holder.bind(item, context)
            }
        }
    }
    override fun getItemCount() = items.size

    class ResiduosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        fun bind(item: Residuos, context: Context) {
            val textView : TextView = itemView.findViewById(R.id.titleTV)
            textView.text = item.texto
            itemView.setOnClickListener {
                val intent = Intent(context, ResiduosActivity::class.java)
                context.startActivity(intent)
            }
        }
    }
}
data class Residuos(val texto: String)