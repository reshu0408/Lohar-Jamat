package com.limra.jaipurilohar.gallery;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.limra.jaipurilohar.R;
import com.limra.jaipurilohar.dashboard.DashboardActivity;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.MyViewHolder> {

    ImageClickListener listener;
    private int[] drawableArray;

    public ImagesAdapter(int[] drawableArray) {
        this.drawableArray = drawableArray;
    }

    interface ImageClickListener {
        void onClick(View view, int drawable);
    }

    public ImagesAdapter(int[] drawableArray, ImageClickListener listener) {
        this.drawableArray = drawableArray;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gallery, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {

        Transformation transformation = new Transformation() {

            @Override
            public Bitmap transform(Bitmap source) {
                int targetWidth = viewHolder.imageView.getWidth();

                double aspectRatio = (double) source.getHeight() / (double) source.getWidth();
                int targetHeight = (int) (targetWidth * aspectRatio);
                if (targetHeight == 0) {
                    targetHeight = 100;
                }
                if (targetWidth == 0) targetWidth = 100;
                Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);
                if (result != source) {
                    // Same bitmap is returned if sizes are the same
                    source.recycle();
                }
                return result;
            }

            @Override
            public String key() {
                return "transformation" + " desiredWidth";
            }
        };

        Picasso.get()
               .load(drawableArray[i])
               .placeholder(R.drawable.hammer)
               .transform(transformation)
               .into(viewHolder.imageView);

        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) listener.onClick(v, drawableArray[i]);
            }
        });
    }

    @Override
    public int getItemCount() {
        return drawableArray.length;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imgView)
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
