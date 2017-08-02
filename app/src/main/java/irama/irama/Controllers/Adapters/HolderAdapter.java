package irama.irama.Controllers.Adapters;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

import irama.irama.Models.parameters;
import irama.irama.R;

/**
 * Created by enagi on 1/7/2017.
 */

public class HolderAdapter extends RecyclerView.Adapter<HolderAdapter.ViewHolder> implements View.OnClickListener{

    private ArrayList<parameters> parameterses;
    private Context context;


    public HolderAdapter(ArrayList<parameters> parameters, Context context) {
        this.parameterses = parameters;
        this.context = context;
    }

    @Override
    public HolderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listview, null);
        HolderAdapter.ViewHolder viewHolder = new HolderAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final HolderAdapter.ViewHolder holder, int position) {

        final parameters parameters = parameterses.get(position);

        holder.tvClient.setText(parameters.getName());
        holder.tvDescription.setText(parameters.getDescription());

        if(parameters.getIsSync() == 1){
            holder.checkBox.setChecked(true);
            holder.checkBox.setClickable(false);
            holder.sync.setEnabled(false);
            holder.edit.setEnabled(false);

        }
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Log.d("chechked", parameters.getDescription());
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return (null != parameterses ? parameterses.size() : 0);
    }

    @Override
    public void onClick(View v) {

    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        protected TextView tvClient;
        protected TextView tvDescription;
        protected Button sync;
        protected Button edit;
        protected CheckBox checkBox;
        protected TextView textView;
        protected View someView;

        private int originalHeight = 0;
        private boolean isViewExpanded = false;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            this.someView = itemView.findViewById(R.id.new_oo);
            this.tvClient = (TextView)itemView.findViewById(R.id.client_item_list);
            this.tvDescription = (TextView)itemView.findViewById(R.id.description_item_list);
            this.sync = (Button)itemView.findViewById(R.id.item_sync);
            this.edit = (Button)itemView.findViewById(R.id.item_edit);
            this.edit.setFocusable(true);
            this.sync.setFocusable(true);
            this.checkBox = (CheckBox)itemView.findViewById(R.id.check_state);
            this.textView = (TextView)itemView.findViewById(R.id.date_order);
            if (isViewExpanded == false) {
                // Set Views to View.GONE and .setEnabled(false)
                someView.setVisibility(View.GONE);
                someView.setEnabled(false);
            }

            this.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     Log.e(getClass().getName(), "edit this " + parameterses.get(getAdapterPosition()).getName());
                }
            });
            this.sync.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e(getClass().getName(), "sync this " + parameterses.get(getAdapterPosition()).getName());

                }
            });
        }

        @Override
        public void onClick(final View v) {

            Log.e("Click", parameterses.get(this.getPosition()).getName());

            if (originalHeight == 0) {
                originalHeight = v.getHeight();
            }

            ValueAnimator valueAnimator;
            if (!isViewExpanded) {
                valueAnimator = ValueAnimator.ofInt(originalHeight, originalHeight + (int) (originalHeight * 1.0));
                someView.setVisibility(View.VISIBLE);
                someView.setEnabled(true);
                isViewExpanded = true;
            } else {
                isViewExpanded = false;
                valueAnimator = ValueAnimator.ofInt(originalHeight + (int) (originalHeight * 1.0), originalHeight);

                Animation a = new AlphaAnimation(1.00f, 0.00f);

                a.setDuration(500);
                a.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        someView.setVisibility(View.GONE);
                        someView.setEnabled(false);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                someView.startAnimation(a);
            }
            valueAnimator.setDuration(500);
            valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator animation) {
                    Integer value = (Integer) animation.getAnimatedValue();
                    v.getLayoutParams().height = value.intValue();
                    v.requestLayout();
                }
            });


            valueAnimator.start();

        }

        @Override
        public boolean onLongClick(final View v) {





            return true;
        }
    }
}
