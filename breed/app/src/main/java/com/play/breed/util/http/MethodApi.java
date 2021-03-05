package com.play.breed.util.http;

import org.json.JSONObject;

import java.io.File;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by LGQ
 * Time: 2018/7/18
 * Function:
 */

public class MethodApi {


    private static RequestBody getRequestBody(Map<String, String> params) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("Content-Type, application/json"),
                new JSONObject(params).toString());
        return requestBody;
    }

    private static MultipartBody.Part fileToMultipartBodyParts(File file) {
        return fileToMultipartBodyParts(file,"file");
    }
    private static MultipartBody.Part fileToMultipartBodyParts(File file,String name) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData(name, file.getName(), requestBody);
        return part;
    }

    private static RequestBody getStringPart(String str) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), str);
        return requestBody;
    }

}
