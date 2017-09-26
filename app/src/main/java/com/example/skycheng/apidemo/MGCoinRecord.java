package com.example.skycheng.apidemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MGCoinRecord extends AppCompatActivity {

    private RecyclerView mRecycle;
    private Context mContext;

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


    private class RecycleAdapter extends RecyclerView.Adapter{

        private RecycleHolder mRecycleHolder;

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_record, null);
            mRecycleHolder = new RecycleHolder(inflate);
            return mRecycleHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            mRecycleHolder= (RecycleHolder) holder;
            mRecycleHolder.setBindData(position);
        }

        @Override
        public int getItemCount() {
            return 15;
        }
    }

    private static class RecycleHolder extends RecyclerView.ViewHolder{

        private final TextView mName;

        public RecycleHolder(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.name_send);
        }


        public void setBindData(int position) {
            for (int i = 0; i < 10; i++) {
                mName.setText("小明"+i);
            }
        }
    }
}
