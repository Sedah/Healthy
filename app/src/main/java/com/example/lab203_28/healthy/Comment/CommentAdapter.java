package com.example.lab203_28.healthy.Comment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lab203_28.healthy.R;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CommentAdapter extends ArrayAdapter {

    ArrayList<JSONObject> postList;
    Context context;

    public CommentAdapter(Context context, int resource, ArrayList<JSONObject> objects)
    {
        super(context, resource, objects);
        this.postList = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View commentItem = LayoutInflater.from(context).inflate(R.layout.comment_item, parent, false);
        JSONObject commentObj = postList.get(position);
        TextView commentHeader = commentItem.findViewById(R.id.comment_item_header);
        TextView commentBody  = commentItem.findViewById(R.id.comment_item_body);
        TextView commentName = commentItem.findViewById(R.id.comment_item_name);
        TextView commentEmail  = commentItem.findViewById(R.id.comment_item_email);
        try
        {
            commentHeader.setText(commentObj.getString("postId") + " : " + commentObj.getString("id"));
            commentBody.setText(commentObj.getString("body"));
            commentName.setText("name : " + commentObj.getString("name"));
            commentEmail.setText("email : " + commentObj.getString("email"));
        }
        catch (JSONException e)
        {
            Log.d("test", "catch JSONException : " + e.getMessage());
        }
        return commentItem;
    }
}
