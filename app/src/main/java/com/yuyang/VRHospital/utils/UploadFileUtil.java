package com.yuyang.VRHospital.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.Handler;
import android.text.TextUtils;
import com.yuyang.VRHospital.bean.UploadImageBean;
import com.alibaba.fastjson.JSON;

public class UploadFileUtil {
    private UploadListener uploadListener = null;
    private String filePath;
    private String token;
    private String url;
    private Handler mHandler = new Handler();

    String BOUNDARY = java.util.UUID.randomUUID().toString();
    String PREFIX = "--", LINEND = "\r\n";
    String MULTIPART_FROM_DATA = "multipart/form-data";
    String CHARSET = "UTF-8";

    public UploadFileUtil(String url, String filePath, String token) {
        this.url = url;
        this.filePath = filePath;
        this.token = token;
    }

    public void setUploadListener(UploadListener uploadListener) {
        this.uploadListener = uploadListener;
    }

    public void upload() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                uploadFile();
            }
        }).start();
    }

    // //////////////////////////////////////////////////////////////////////////
    public interface UploadListener {
        void onSuccess(String filePath, String imageUrl);

        void onError(String filePath);
    }

    // ///////////////////////////////////////////////////////////////////////////
    private void uploadFile() {
        if (TextUtils.isEmpty(url) || TextUtils.isEmpty(filePath))
            return;

        InputStream in = null;
        InputStream is = null;
        HttpURLConnection conn = null;
        DataOutputStream outStream = null;

        try {
            URL uri = new URL(url);
            conn = (HttpURLConnection) uri.openConnection();
            conn.setDoOutput(true);
            // conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setConnectTimeout(1000 * 60 * 3);
            conn.setReadTimeout(1000 * 60 * 7);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA + ";boundary=" + BOUNDARY);
            conn.setRequestProperty("Charset", "UTF-8");

            StringBuilder sb = new StringBuilder();
            sb.append(PREFIX);
            sb.append(BOUNDARY);
            sb.append(LINEND);

            //sb.append("Content-Type: text/plain; charset=" + CHARSET + LINEND);
            //sb.append("Content-Transfer-Encoding: gzip, deflate" + LINEND);
            sb.append("Content-Disposition: form-data; name=\"" + "UserCode" + "\"" + LINEND);
            sb.append(LINEND);
            sb.append(this.token);
            sb.append(LINEND);

            outStream = new DataOutputStream(conn.getOutputStream());
            outStream.write(sb.toString().getBytes());

            File file = new File(filePath);
            StringBuilder sb1 = new StringBuilder();
            sb1.append(PREFIX);
            sb1.append(BOUNDARY);
            sb1.append(LINEND);
            sb1.append("Content-Disposition: form-data; name=\"UserHeadImage\"; filename=\"" + file.getName() + "\"" + LINEND);
            sb1.append("Content-Type: image/jpeg" + LINEND);
            sb1.append(LINEND);
            outStream.write(sb1.toString().getBytes());

            is = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
                outStream.flush();
            }

            is.close();
            is = null;
            outStream.write(LINEND.getBytes());

            byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINEND).getBytes();
            outStream.write(end_data);
            outStream.flush();

            int res = conn.getResponseCode();
            in = conn.getInputStream();
            if (res == 200 && in != null) {
                String str = stream2String(in);

                final UploadImageBean result = JSON.parseObject(str, UploadImageBean.class);
                if (result != null && result.getStatus() == 200 && !TextUtils.isEmpty(result.getResult())) {
                    mHandler.post(new Runnable() {

                        @Override
                        public void run() {
                            if (null != uploadListener) {
                                uploadListener.onSuccess(filePath, result.getResult());
                            }
                        }
                    });
                } else {
                    mHandler.post(new Runnable() {

                        @Override
                        public void run() {
                            if (null != uploadListener) {
                                uploadListener.onError(filePath);
                            }
                        }
                    });
                }
            } else {
                mHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        if (null != uploadListener) {
                            uploadListener.onError(filePath);
                        }
                    }
                });
            }

            in.close();
            outStream.close();
            conn.disconnect();

        } catch (IOException e) {
            mHandler.post(new Runnable() {

                @Override
                public void run() {
                    if (null != uploadListener) {
                        uploadListener.onError(filePath);
                    }
                }
            });
        } finally {
            try {
                if (null != is) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (null != outStream) {
                    outStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (null != in) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (null != conn) {
                    conn.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return;
    }

    private Type getTypeArgument() {
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            return ((ParameterizedType) type).getActualTypeArguments()[0];
        } else {
            throw new IllegalArgumentException("you must specified a type of response object");
        }
    }

    private String stream2String(InputStream stream) throws IOException, NullPointerException {
        String responseAsString = null;
        BufferedReader br;
        if (null == stream) {
            throw new NullPointerException();
        }
        br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
        StringBuffer buf = new StringBuffer();
        String line = br.readLine();
        while (null != line) {
            buf.append(line);
            line = br.readLine();
            if (line != null) {
                buf.append("\n");
            }
        }
        responseAsString = buf.toString();
        return responseAsString;
    }

}
