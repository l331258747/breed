package com.play.breed.view.post;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.play.breed.R;
import com.play.breed.adapter.ImageAdapter;
import com.play.breed.base.BaseActivity;
import com.play.breed.util.LoginUtil;
import com.play.breed.util.accessory.ImageUtils;
import com.play.breed.util.dialog.LoadingDialog;
import com.play.breed.util.photo.TackPicturesUtil;
import com.play.breed.util.rxbus.RxBus2;
import com.play.breed.util.rxbus.busEvent.UpLoadPhotos;
import com.play.breed.util.thread.MyThreadPool;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.disposables.Disposable;

public class SendPostActivity extends BaseActivity implements View.OnClickListener {

    ImageView iv_switch,iv_camera;
    View view_ba_name;
    TextView tv_ba_name,btn_submit;
    EditText et_title,et_content;

    RecyclerView recyclerView;

    List<String> filePaths = new ArrayList<>();
    ImageAdapter mAdapter;

    boolean isSwitch = false;

    @Override
    public int getLayoutId() {
        return R.layout.activity_send_post;
    }

    @Override
    public void initView() {
        showLeftAndTitle("发帖");

        iv_switch = $(R.id.iv_switch);
        iv_camera = $(R.id.iv_camera);
        view_ba_name = $(R.id.view_ba_name);
        tv_ba_name = $(R.id.tv_ba_name);
        btn_submit = $(R.id.btn_submit);
        et_title = $(R.id.et_title);
        et_content = $(R.id.et_content);

        initRecycler();

        iv_switch.setOnClickListener(this);
        btn_submit.setOnClickListener(this);
        iv_camera.setOnClickListener(this);
    }

    @Override
    public void initData() {
        loadingDialog = new LoadingDialog(context);
        tackPicUtil = new TackPicturesUtil(this);
        getPicPermission(context);
    }

    private void initRecycler() {
        recyclerView = $(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        mAdapter = new ImageAdapter(activity, new ArrayList<>());
        recyclerView.setAdapter(mAdapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_camera:
                tackPicUtil.showDialog(context);
                break;
            case R.id.btn_submit:
                if (!LoginUtil.verifyEmpty(et_title.getText().toString(), "请输入标题"))
                    return;
                if (!LoginUtil.verifyEmpty(et_content.getText().toString(), "请输入内容"))
                    return;
                if (filePaths.size() == 0) {
                    showShortToast("请选择图片");
                    return;
                }

                showShortToast("提交成功");
                break;
            case R.id.iv_switch:
                isSwitch = !isSwitch;
                iv_switch.setImageResource(isSwitch?R.mipmap.ic_switch_off:R.mipmap.ic_switch_on);
                break;
        }
    }

    public String getImgUrls() {
        String str = "";
        for (int i = 0; i < filePaths.size(); i++) {
            str = str + filePaths;
            if(i != filePaths.size() - 1){
                str = str + ",";
            }
        }
        return str;
    }


    //图片
    private TackPicturesUtil tackPicUtil;
    private String headpath;// 头像地址
    private String headCompressPath;
    private Disposable disposable;
    private LoadingDialog loadingDialog;
    //-----------start 拍照-----------

    //拍照，存储权限
    public void getPicPermission(Context context) {
        tackPicUtil.checkPermission(context);
    }


    /**
     * 获取图片回调
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TackPicturesUtil.CHOOSE_PIC:
            case TackPicturesUtil.TACK_PIC:
            case TackPicturesUtil.CROP_PIC:
                String path = tackPicUtil.getPicture(requestCode, resultCode, data, false);
                if (path == null)
                    return;
                headpath = path;
                upFile();
                break;
            default:
                break;
        }
    }

    public void upFile() {
        disposable = RxBus2.getInstance().toObservable(UpLoadPhotos.class, upLoadPhotos -> {
            sendHead();
            disposable.dispose();
        });

        loadingDialog.showDialog("上传图片...");
        loadingDialog.setCancelable(false);
        compressImage();
    }

    public void sendHead() {
        //构建要上传的文件
        File file = new File(headCompressPath);

        loadingDialog.dismiss();
        filePaths.add(headCompressPath);

        showShortToast("图片上传成功");

        if (filePaths.size() >= 3) {
            iv_camera.setVisibility(View.GONE);
        }

        mAdapter.setData(filePaths);
    }

    private void compressImage() {
        MyThreadPool.getInstance().submit(() -> {
            File file = new File(headpath);
            String savePath = TackPicturesUtil.IMAGE_CACHE_PATH + File.separator + "crop" + File.separator + file.getName();
            ImageUtils.getImage(headpath, savePath);
            headCompressPath = savePath;
            RxBus2.getInstance().post(new UpLoadPhotos());
        });
    }

    //----------------end 拍照
}
