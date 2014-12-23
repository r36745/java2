package rosemak.addapplicatoin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by stevierose on 12/22/14.
 */
public class IdeaAdapter extends BaseAdapter {

    private static final long ID_CONSTANT = 0x010101010L;

    private Context mContext;
    private ArrayList<Idea> mIdea;

    public IdeaAdapter(Context _context, ArrayList<Idea> _idea) {
        mContext = _context;
        mIdea = _idea;
    }
    @Override
    public int getCount() {

        return mIdea.size();
    }

    @Override
    public Idea getItem(int position) {
        return mIdea.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ID_CONSTANT + position;
    }

    @Override
    public View getView(int _position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            convertView =  LayoutInflater.from(mContext).inflate(R.layout.idea_list_item, parent, false);
        }
        Idea idea = getItem(_position);

        TextView coreIdeaName = (TextView) convertView.findViewById(R.id.coreIdeaName);
        coreIdeaName.setText(idea.getmIdeaName());

        return convertView;


    }
}
