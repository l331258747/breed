package com.play.breed.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.play.breed.R;

import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * Created by liguoqiang on 2017/11/28.
 */

public class TaskDialog extends Dialog implements View.OnClickListener {

    Context context;
    ConstraintLayout cl_parent;
    View view_1;
    TextView tv_title;

    public TaskDialog(Context context) {
        super(context);
        this.context = context;
    }

    public TaskDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_task, null);
        this.setContentView(layout);

        getWindow().setBackgroundDrawable(new ColorDrawable(0x00000000));
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        cl_parent = layout.findViewById(R.id.cl_parent);
        view_1 = layout.findViewById(R.id.view_1);
        tv_title = layout.findViewById(R.id.tv_title);

        cl_parent.setOnClickListener(this);
        view_1.setOnClickListener(this);

        if(!TextUtils.isEmpty(title)){
            tv_title.setText(title);
        }
    }

    String title;
    public TaskDialog setTitle(String title){
        this.title = title;
        return this;
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.cl_parent:
                dismiss();
                break;
            case R.id.view_1:

                break;
        }
    }
}
