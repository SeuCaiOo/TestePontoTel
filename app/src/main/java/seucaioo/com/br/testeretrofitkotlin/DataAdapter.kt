package seucaioo.com.br.testeretrofitkotlin

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.adapter_data.view.*
import seucaioo.com.br.testeretrofitkotlin.model.Data


data class DataAdapter(
        private val contex: Context,
        private val dataList: List<Data>) : Adapter<DataAdapter.DatasViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DatasViewHolder {
        val view = LayoutInflater.from(contex).inflate(R.layout.adapter_data, parent, false)
        return DatasViewHolder(view)
    }

    override fun onBindViewHolder(holder: DatasViewHolder, position: Int) {
        holder?.let {
            it.bindView(dataList.get(position))
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }


    class DatasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(data: Data) {
            val viewId = itemView.txtId
            val viewName = itemView.txtName
            val viewPwd = itemView.txtName

            viewId.setText(data.id)
            viewName.setText(data.name)
            viewPwd.setText(data.pwd.toString())

//            viewId.text = data.id
//            viewName.text = data.name
//            viewPwd.text = data.pwd.toString()
        }




    }

}
