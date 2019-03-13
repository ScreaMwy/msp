package com.msp.response;

public class CommonReturnType {
    //表示對應嘅請求嘅返回處理結果"success"抑或"fail"
    private String status;

    //如果status=success，則data就返回前端所需要嘅json數據
    //如果status=fail，則data就返回通用嘅錯誤格式碼
    private Object data;

    public CommonReturnType() {}

    public final static CommonReturnType create(Object result) {
        return CommonReturnType.create("success", result);
    }

    public final static CommonReturnType create(String status, Object result) {
        CommonReturnType type = new CommonReturnType();
        type.setStatus(status);
        type.setData(result);
        return type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CommonReturnType{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }
}
