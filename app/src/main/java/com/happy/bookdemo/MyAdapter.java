package com.happy.bookdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * @Describtion
 * @Author LiAng
 * @Date 2017/12/21
 * @Time 13:28
 */

public class MyAdapter extends RecyclerView.Adapter implements ItemSlideHelper.Callback{
    private RecyclerView mRecyclerView;
    public Context mContext;
    private List<String> mDatas;
    private ItemClickListener mItemClickListener;

    public MyAdapter(Context mContext, List<String> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    public void setItemClickListener(ItemClickListener mItemClickListener) {

        this.mItemClickListener = mItemClickListener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rcview_item, parent, false);
        return new TextVH(view);
    }
    @Override
    public int getItemCount() {
        return mDatas.size();
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        TextVH textVH = (TextVH) holder;
        textVH.textView.setText(mDatas.get(position));
        Glide.with(mContext).load(R.drawable.defaulthead_profile).bitmapTransform(new GlideRoundTransform(mContext,10)).into(textVH.iv);
        textVH.iv2.setImageResource(R.drawable.axt);
        textVH.item_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mItemClickListener) {
                    mItemClickListener.onItemClick(v, position);
                }
            }
        });

        textVH.tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("kkkk","删除按钮有效==========="+position);
                mDatas.remove(getLayoutPosition);
                // 刷新ListView内容
                notifyItemRemoved(getLayoutPosition);
            }

        });

    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        mRecyclerView = recyclerView;
        mRecyclerView.addOnItemTouchListener(new ItemSlideHelper(mRecyclerView.getContext(), this));
    }

    @Override
    public int getHorizontalRange(RecyclerView.ViewHolder holder) {
        if(holder.itemView instanceof LinearLayout){
            ViewGroup viewGroup = (ViewGroup) holder.itemView;
            if(viewGroup.getChildCount() == 2){
                return viewGroup.getChildAt(1).getLayoutParams().width;
            }
        }


        return 0;
    }

    @Override
    public RecyclerView.ViewHolder getChildViewHolder(View childView) {
        return mRecyclerView.getChildViewHolder(childView);
    }

    @Override
    public View findTargetView(float x, float y) {
        return mRecyclerView.findChildViewUnder(x, y);
    }
}

class TextVH extends RecyclerView.ViewHolder{
    ImageView iv,iv2;
    TextView textView,tv_delete;
    RelativeLayout item_layout;
    public TextVH(View itemView) {
        super(itemView);
        iv=itemView.findViewById(R.id.item_iv);
        iv2=itemView.findViewById(R.id.item_iv2);
        textView = (TextView) itemView.findViewById(R.id.item_tv);
        tv_delete = (TextView) itemView.findViewById(R.id.tv_delete);
        item_layout =  itemView.findViewById(R.id.item_layout);

    }
}
