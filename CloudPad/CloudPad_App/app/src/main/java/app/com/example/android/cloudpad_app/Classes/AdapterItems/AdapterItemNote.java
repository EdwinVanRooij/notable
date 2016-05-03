package app.com.example.android.cloudpad_app.classes.adapteritems;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mikepenz.fastadapter.items.AbstractItem;

import app.com.example.android.cloudpad_app.classes.physical.Note;
import app.com.example.android.cloudpad_app.classes.physical.SharedNote;
import app.com.example.android.cloudpad_app.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Edwin on 11/16/2015.
 */
public class AdapterItemNote extends AbstractItem<AdapterItemNote, AdapterItemNote.ViewHolder> {
    private Note note;

    public AdapterItemNote(Note n) {
        this.note = n;
    }

    public Note getNote(){
        return note;
    }

    //The unique ID for this type of item
    @Override
    public int getType() {
        return R.id.item_note_root_id;
    }

    //The layout to be used for this type of item
    @Override
    public int getLayoutRes() {
        return R.layout.item_note;
    }

    //The logic to bind your data to the view
    @Override
    public void bindView(RecyclerView.ViewHolder holder) {
        //get our viewHolder
        ViewHolder viewHolder = (ViewHolder) holder;

        //set the item selected if it is
        viewHolder.itemView.setSelected(isSelected());

        //set the text for the name
        viewHolder.tvSubject.setText(note.getSubject());
        //set the text for the description or hide
        viewHolder.tvText.setText(note.getText());
        if (note instanceof SharedNote) {
            viewHolder.tvOwner.setText(((SharedNote) note).getOwner());
            viewHolder.tvOwner.setVisibility(View.VISIBLE);
            viewHolder.tvLastEdited.setText(((SharedNote) note).getLastEditedString());
            viewHolder.tvLastEdited.setVisibility(View.VISIBLE);
        }

        viewHolder.itemView.setSelected(isSelected());

//        UIUtils.setBackground(viewHolder.view, FastAdapterUIUtils.getSelectableBackground(ctx, Color.GREEN, true));
    }

    /**
     * our ViewHolder
     */
    protected static class ViewHolder extends RecyclerView.ViewHolder {
        protected View view;
        @Bind(R.id.tvSubject)
        TextView tvSubject;
        @Bind(R.id.tvText)
        TextView tvText;
        @Bind(R.id.tvOwner)
        TextView tvOwner;
        @Bind(R.id.tvLastEdited)
        TextView tvLastEdited;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.view = view;
        }
    }
}