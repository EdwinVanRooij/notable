package nlusersi324201edwinvanrooij.fhict.httpathena.boodschappen_app.Classes.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import nlusersi324201edwinvanrooij.fhict.httpathena.boodschappen_app.Classes.Interfaces.MyOnItemClickListener;
import nlusersi324201edwinvanrooij.fhict.httpathena.boodschappen_app.Classes.Physical.Product;
import nlusersi324201edwinvanrooij.fhict.httpathena.boodschappen_app.R;

/**
 * Created by Edwin on 11/11/2015.
 */

public class ProductAdapter extends
        RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private final List<Product> notes;
    private MyOnItemClickListener mItemClickListener;

    private boolean listIsEmpty() {
        if (notes.size() == 0 ) {
            return true;
        } else {
            return false;
        }
    }

    public ProductAdapter(List<Product> products) {
        //Collections.sort(notes, new PrivateNoteComparator());
        this.notes = products;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View noteView = inflater.inflate(R.layout.item_product, parent, false);

        return new ProductViewHolder(noteView);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        Product n = notes.get(position);

        // Set item views based on the data model
        TextView tv1 = holder.tvName;
        TextView tv2 = holder.tvAmount;
        TextView tv3 = holder.tvRemark;

        tv1.setText(n.getName());
        tv2.setText(String.valueOf(n.getAmount()));
        tv3.setText(n.getRemark());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public Product getItem(int position) {
        return notes.get(position);
    }

    private Product removeItem(int position) {
        final Product note = notes.remove(position);
        notifyItemRemoved(position);
        return note;
    }

    public void SetOnItemClickListener(MyOnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView tvName;
        public final TextView tvAmount;
        public final TextView tvRemark;

        public ProductViewHolder(View view) {
            super(view);
            tvName = (TextView) view.findViewById(R.id.tvTitle);
            tvAmount = (TextView) view.findViewById(R.id.tvAmount);
            tvRemark = (TextView) view.findViewById(R.id.tvRemark);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mItemClickListener.onItemClick(v, getAdapterPosition());
        }
    }
}