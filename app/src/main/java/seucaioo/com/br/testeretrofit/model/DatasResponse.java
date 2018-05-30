package seucaioo.com.br.testeretrofit.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DatasResponse {

    @SerializedName("data")
    private List<Data> datas;

    public List<Data> getDatas() {
        return datas;
    }

    public void setDatas(List<Data>datas) {
        this.datas = datas;
    }
}
