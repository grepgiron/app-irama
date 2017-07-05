package irama.irama.Adapters;

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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import irama.irama.Models.order;
import irama.irama.R;

import static android.content.ContentValues.TAG;

/**
 * Created by enagi on 1/7/2017.
 */

public class HolderAdapter extends RecyclerView.Adapter<HolderAdapter.ViewHolder> {

    private ArrayList<order> orders;
    private Context context;


    public HolderAdapter(ArrayList<order> orders, Context context) {
        this.orders = orders;
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

        final order order = orders.get(position);

        holder.tvClient.setText(order.getDescription());
        holder.tvDescription.setText(order.getRequestOn());

        if(order.getIsSync() == 1){
            holder.checkBox.setChecked(true);
            holder.checkBox.setClickable(false);

        }
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Log.d("chechked", order.getDescription());
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return (null != orders ? orders.size() : 0);
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        protected TextView tvClient;
        protected TextView tvDescription;
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
            this.checkBox = (CheckBox)itemView.findViewById(R.id.check_state);
            this.textView = (TextView)itemView.findViewById(R.id.date_order);
            if (isViewExpanded == false) {
                // Set Views to View.GONE and .setEnabled(false)
                someView.setVisibility(View.GONE);
                someView.setEnabled(false);
            }
        }

        @Override
        public void onClick(final View v) {
             /*  View someView = v.findViewById(R.id.new_oo);
            someView.setVisibility(View.VISIBLE);*/
            if (originalHeight == 0) {
                originalHeight = v.getHeight();
            }

            // Declare a ValueAnimator object
            ValueAnimator valueAnimator;
            if (!isViewExpanded) {
                valueAnimator = ValueAnimator.ofInt(originalHeight, originalHeight + (int) (originalHeight * 1.0)); // These values in this method can be changed to expand however much you like
                someView.setVisibility(View.VISIBLE);
                someView.setEnabled(true);
                isViewExpanded = true;
            } else {
                isViewExpanded = false;
                valueAnimator = ValueAnimator.ofInt(originalHeight + (int) (originalHeight * 1.0), originalHeight);

                Animation a = new AlphaAnimation(1.00f, 0.00f); // Fade out

                a.setDuration(500);
                // Set a listener to the animation and configure onAnimationEnd
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

                // Set the animation on the custom view
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
