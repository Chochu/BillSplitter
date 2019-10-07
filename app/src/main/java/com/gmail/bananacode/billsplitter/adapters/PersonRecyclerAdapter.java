package com.gmail.bananacode.billsplitter.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gmail.bananacode.billsplitter.Person;
import com.gmail.bananacode.billsplitter.R;

import java.util.HashMap;
import java.util.Map;

import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class PersonRecyclerAdapter extends RecyclerView.Adapter<PersonRecyclerAdapter.ViewHolder> {

    private static final String TAG = "PersonRecyclerAdapter";

    private Map<String, Person> personList;
    private Context mContext;
    private Map<Integer, String> position2Person = new HashMap<>();

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item
        CircleImageView image;
        TextView imageName;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.personIcon);
            imageName = itemView.findViewById(R.id.image_name);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public PersonRecyclerAdapter(Map<String, Person> myDataset,Context Context) {
        personList = myDataset;
        mContext = Context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        // - get element from your dataset at this position
        // - replace the contents of the view with that element
//        Glide.with(mContext).asBitmap().load(mImages.get(position)).into(vh.personIcon);
        Log.d(TAG, "onBindViewHolder: position" + position);
        if(personList.size() != position2Person.size()) {
            refresh_pos2Per();
        }

        holder.imageName.setText(personList.get(position2Person.get(position)).getName());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: " + personList.get(position2Person.get(position)).get_total());

                Toast.makeText(mContext,personList.get(position2Person.get(position)).getName() + String.valueOf(getItemCount()), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void refresh_pos2Per(){
        position2Person = new HashMap<>();
        int counter = 0;
        for (Map.Entry<String, Person> e : personList.entrySet()) {
            position2Person.put(counter,e.getKey());
            counter++;
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return personList.size();
    }
}