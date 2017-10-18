package com.mgcoin.ar_department.lbs_redpacket.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mgcoin.ar_department.apidemo.R;
import com.mgcoin.ar_department.lbs_redpacket.bean.ReturnSellerPacketBean;

import java.util.ArrayList;

public class MGCoinRecord extends AppCompatActivity {

    private RecyclerView mRecycle;
    private Context mContext;
    private ArrayList<ReturnSellerPacketBean> mRecordBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mgcoin_record);
        mContext = this;
        mRecycle = (RecyclerView) findViewById(R.id.recycle);
        setData();
    }



    private void setData() {
        RecycleAdapter recycleAdapter = new RecycleAdapter();
        mRecycle.setLayoutManager(new LinearLayoutManager(this));
        mRecycle.setAdapter(recycleAdapter);
    }

    public void coinClose(View view) {
        finish();
    }

    public void getData(ArrayList<ReturnSellerPacketBean> packetBeen, String v_name, String v_amount) {

        mRecordBean = packetBeen;
    }


    private class RecycleAdapter extends RecyclerView.Adapter {

        private RecycleHolder mRecycleHolder;

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_record, null);
            mRecycleHolder = new RecycleHolder(inflate);
            return mRecycleHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            mRecycleHolder = (RecycleHolder) holder;
            mRecycleHolder.setBindData(mRecordBean.get(position));
        }

        @Override
        public int getItemCount() {
            if (mRecordBean!=null){
                return mRecordBean.size();
            }else return 0;
        }
    }

    private static class RecycleHolder extends RecyclerView.ViewHolder {

        private final TextView mName;
        private final TextView mMoney;
        private final TextView mTime;

        public RecycleHolder(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.name_send);
            mMoney = (TextView) itemView.findViewById(R.id.received);
            mTime = (TextView) itemView.findViewById(R.id.time);
        }


        public void setBindData(ReturnSellerPacketBean packetBean) {

            mName.setText(packetBean.getCa_dname());
            mTime.setText(packetBean.getCa_time());
            mMoney.setText(packetBean.getCa_amount());


        }
    }
}
