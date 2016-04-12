package nlusersi324201edwinvanrooij.fhict.httpathena.boodschappen_app.Classes.AdapterItems;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.mikepenz.fastadapter.items.AbstractItem;

import butterknife.Bind;
import butterknife.ButterKnife;
import nlusersi324201edwinvanrooij.fhict.httpathena.boodschappen_app.Classes.Physical.GroceryList;
import nlusersi324201edwinvanrooij.fhict.httpathena.boodschappen_app.R;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Physical.Account;

/**
 * Created by Edwin on 11/16/2015.
 */
public class AdapterItemGroceryList extends AbstractItem<AdapterItemGroceryList, AdapterItemGroceryList.ViewHolder> {
    private GroceryList item;

    public AdapterItemGroceryList(GroceryList i) {
        this.item = i;
    }

    public GroceryList getItem(){
        return item;
    }

    //The unique ID for this type of item
    @Override
    public int getType() {
        return R.id.item_grocerylist_root_id;
    }

    //The layout to be used for this type of item
    @Override
    public int getLayoutRes() {
        return R.layout.item_grocerylist;
    }

    //The logic to bind your data to the view
    @Override
    public void bindView(RecyclerView.ViewHolder holder) {
        //get our viewHolder
        ViewHolder viewHolder = (ViewHolder) holder;

        //set the item selected if it is
        viewHolder.itemView.setSelected(isSelected());

        viewHolder.tvTitle.setText(item.getTitle());
        viewHolder.tvDescription.setText(item.toString());

        viewHolder.itemView.setSelected(isSelected());

//        UIUtils.setBackground(viewHolder.view, FastAdapterUIUtils.getSelectableBackground(ctx, Color.GREEN, true));
    }

    /**
     * our ViewHolder
     */
    protected static class ViewHolder extends RecyclerView.ViewHolder {
        protected View view;
        @Bind(R.id.tvTitle)
        TextView tvTitle;
        @Bind(R.id.tvDescription)
        TextView tvDescription;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.view = view;
        }
    }
}