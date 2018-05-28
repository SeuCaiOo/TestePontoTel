package seucaioo.com.br.testeretrofit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import seucaioo.com.br.testeretrofit.model.Data;

public class DatasAdapter extends RecyclerView.Adapter<DatasAdapter.DatasViewHolder>{

    private Context mContext;
    private List<Data> dataList;

    public DatasAdapter (Context mContext, List<Data> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public DatasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(
                parent.getContext()).inflate(R.layout.adapter_data, parent, false);
        return new DatasViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DatasViewHolder holder, int position) {
        holder.viewId.setText(dataList.get(position).getId());
        holder.viewName.setText(dataList.get(position).getName());
        holder.viewPwd.setText(String.valueOf(dataList.get(position).getPwd()));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    protected static class DatasViewHolder  extends RecyclerView.ViewHolder {

        public TextView viewId;
        public TextView viewName;
        public TextView viewPwd;

        public DatasViewHolder(View itemView) {
            super(itemView);
            viewId = itemView.findViewById(R.id.txtId);
            viewName = itemView.findViewById(R.id.txtName);
            viewPwd = itemView.findViewById(R.id.txtPwd);

        }
    }
}
