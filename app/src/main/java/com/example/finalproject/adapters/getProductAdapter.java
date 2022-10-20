package com.example.finalproject.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.finalproject.R;
import com.example.finalproject.interfaces.ProductClick;
import com.example.finalproject.model.dataResponse;
import java.util.ArrayList;

public class getProductAdapter extends RecyclerView.Adapter<getProductAdapter.ProductViewHolder> implements Filterable {
    ArrayList<dataResponse>arrayList;
    ArrayList<dataResponse>datamodelArrayList;
    Context context;
    ProductClick productclick;


    public getProductAdapter(ArrayList<dataResponse> list, Context context, ProductClick productclick) {
        this.arrayList= list;
        this.datamodelArrayList=new ArrayList<>(list);
        this.context = context;
        this.productclick = productclick;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View list = layoutInflater.inflate(R.layout.item_view, parent, false);
        return new ProductViewHolder(list);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder,int position) {
        holder.textViewtitle.setText(arrayList.get(position).title);
        holder.textViewprice.setText(arrayList.get(position).price+ "");
        Glide.with(holder.itemView).load(arrayList.get(position).image).into(holder.imageViewProduct);
        holder.itemView.setOnClickListener(v -> productclick.itemClick(arrayList.get(position).getId()));


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    @Override
    public Filter getFilter() {
        return Searched_Filter;
    }
    private final Filter Searched_Filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<dataResponse> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(datamodelArrayList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (dataResponse item : datamodelArrayList) {
                    if (item.getTitle().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }
        @SuppressLint("NotifyDataSetChanged")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            arrayList.clear();
            arrayList.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewProduct;
        TextView textViewtitle, textViewprice;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);


            imageViewProduct = itemView.findViewById(R.id.product_image);
            textViewtitle = itemView.findViewById(R.id.product_name);
            textViewprice = itemView.findViewById(R.id.product_price);


        }


    }

}
//    Asmaa Karam9:58 AM
//        holder.binding.favouriteIcon.setOnClickListener {
//        itemClick.itemClickListener(holder.adapterPosition, name,price,image)
//        }
//interface ItemClick {
//    fun itemClickListener(id:Int,name:String,price:Double,image:String)
//}
//    implement ItemClick
//    override fun itemClickListener(id: Int, name: String, price: Double, image: String) {
//    favouriteDao = FavouriteDatabase.getDatabaseInstance(requireContext()).favouriteDao()
//    favouriteDao.insertItem(FavouriteModel(id,name, price,image))
//}

