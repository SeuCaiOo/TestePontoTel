package seucaioo.com.br.testeretrofit.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import seucaioo.com.br.testeretrofit.R;
import seucaioo.com.br.testeretrofit.model.Data;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder>{

    private Context context;
    private List<Data> dataList;

    public DataAdapter(Context context, List<Data> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(
                parent.getContext()).inflate(R.layout.adapter_data, parent, false);
        return new DataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        holder.viewId.setText("ID: " + dataList.get(position).getId());
        holder.viewName.setText("Name: " + dataList.get(position).getName());
        holder.viewPwd.setText("Pwd: " + String.valueOf(dataList.get(position).getPwd()));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    protected static class DataViewHolder extends RecyclerView.ViewHolder {

        public TextView viewId;
        public TextView viewName;
        public TextView viewPwd;

        public DataViewHolder(View itemView) {
            super(itemView);
            viewId = itemView.findViewById(R.id.txtId);
            viewName = itemView.findViewById(R.id.txtName);
            viewPwd = itemView.findViewById(R.id.txtPwd);

        }
    }
}
