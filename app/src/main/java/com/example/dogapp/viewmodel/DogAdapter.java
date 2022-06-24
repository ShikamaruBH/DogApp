package com.example.dogapp.viewmodel;

import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dogapp.R;
import com.example.dogapp.databinding.DogsItemBinding;
import com.example.dogapp.model.DogBreed;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DogAdapter extends RecyclerView.Adapter<DogAdapter.ViewHolder> implements Filterable {
    private List<DogBreed> dogBreeds;
    private List<DogBreed> dogBreedsFull;

    public DogAdapter(List<DogBreed> data) {
        this.dogBreedsFull = data;
        this.dogBreeds = data;
    }
    @NonNull
    @Override
    public DogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        DogsItemBinding binding =
                DataBindingUtil.inflate(layoutInflater,
                        R.layout.dogs_item,
                        parent,
                        false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DogAdapter.ViewHolder holder, int position) {
        DogBreed dogBreed = dogBreeds.get(position);
        holder.binding.setDog(dogBreed);
        if (dogBreed.getUrl() != null && !dogBreed.getUrl().isEmpty()) {
            Picasso.get().load(dogBreed.getUrl()).into(holder.binding.ivAvatar);
        }
    }

    @Override
    public int getItemCount() {
        return dogBreeds.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString().toLowerCase();
                List<DogBreed> filteredList = new ArrayList<>();
                if (charString.isEmpty()) {
                    filteredList.addAll(dogBreedsFull);
                } else {
                    for (DogBreed dog : dogBreedsFull) {
                        if (dog.getName().toLowerCase().contains(charString)) {
                            filteredList.add(dog);
                        }
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                dogBreeds = new ArrayList<>();
                dogBreeds.addAll((List)filterResults.values);
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public DogsItemBinding binding;
        public ViewHolder(@NonNull DogsItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;

            binding.ivLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    binding.ivLike.setImageResource(R.drawable.redheart);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("dog", dogBreeds.get(getAdapterPosition()));
                    Navigation.findNavController(view).navigate(R.id.detailFragment, bundle);
                }
            });
            itemView.setOnTouchListener(new OnSwipeTouchListener() {
                @Override
                public boolean onSwipeLeft() {
                    if (binding.loMain.getVisibility() == View.GONE) {
                        binding.loMain.setVisibility(View.VISIBLE);
                        binding.loDetail.setVisibility(View.GONE);
                    } else {
                        binding.loMain.setVisibility(View.GONE);
                        binding.loDetail.setVisibility(View.VISIBLE);
                    }
                    return true;
                }

                @Override
                public boolean onSwipeRight() {
                    onSwipeLeft();
                    return true;
                }
            });
        }
    }

    public class OnSwipeTouchListener implements View.OnTouchListener {

        private final GestureDetector gestureDetector = new GestureDetector(new GestureListener());

        public boolean onTouch(final View v, final MotionEvent event) {
            return gestureDetector.onTouchEvent(event);
        }

        private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

            private static final int SWIPE_THRESHOLD = 100;
            private static final int SWIPE_VELOCITY_THRESHOLD = 100;


            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                boolean result = false;
                try {
                    float diffY = e2.getY() - e1.getY();
                    float diffX = e2.getX() - e1.getX();
                    if (Math.abs(diffX) > Math.abs(diffY)) {
                        if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                            if (diffX > 0) {
                                result = onSwipeRight();
                            } else {
                                result = onSwipeLeft();
                            }
                        }
                    } else {
                        if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                            if (diffY > 0) {
                                result = onSwipeBottom();
                            } else {
                                result = onSwipeTop();
                            }
                        }
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                return result;
            }
        }

        public boolean onSwipeRight() {
            return false;
        }

        public boolean onSwipeLeft() {
            return false;
        }

        public boolean onSwipeTop() {
            return false;
        }

        public boolean onSwipeBottom() {
            return false;
        }
    }
}
