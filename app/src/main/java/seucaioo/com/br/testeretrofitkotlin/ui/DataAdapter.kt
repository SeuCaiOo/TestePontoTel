package seucaioo.com.br.testeretrofitkotlin.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.adapter_data.view.*
import seucaioo.com.br.testeretrofitkotlin.R
import seucaioo.com.br.testeretrofitkotlin.model.Data


data class DataAdapter(
        private val contex: Context,
        private val dataList: List<Data>) : Adapter<DataAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(contex).inflate(R.layout.adapter_data, parent, false)
        return DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder?.let {
            it.bindView(dataList.get(position))
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(data: Data) {
            val viewId = itemView.txtId
            val viewName = itemView.txtName
            val viewPwd = itemView.txtPwd

            viewId.setText("ID: "+ data.id)
            viewName.setText("Name: " + data.name)
            viewPwd.setText("Pwd: "+ data.pwd.toString())
        }

    }

}
