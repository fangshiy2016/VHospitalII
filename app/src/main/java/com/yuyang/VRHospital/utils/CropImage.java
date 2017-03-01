package com.yuyang.VRHospital.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;

import com.yuyang.VRHospital.broadcast.AppPreference;

public class CropImage {
    public static final int CHOOSE_BIG_PICTURE = 0x101;
    public static final int CHOOSE_SMALL_PICTURE = 0x102;

    public static final int TAKE_BIG_PICTURE = 0x103;
    public static final int TAKE_SMALL_PICTURE = 0X104;

    public static final int CROP_BIG_PICTURE = 0x105;
    public static final int CROP_SMALL_PICTURE = 0x106;

    public static final int CHOOSE_SMALL_PICUTRE_FROM_KAKAT = 0x107;

    private static String mPath;

    public static void CropBigImageFromCamera(Activity activity) {
        Uri imageUri = Uri.fromFile(new File(imagePath(activity)));
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        activity.startActivityForResult(intent, TAKE_BIG_PICTURE);
    }

    public static void CropBigImageFromCamera(Fragment fragment) {
        Uri imageUri = Uri.fromFile(new File(imagePath(fragment.getActivity())));
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        fragment.startActivityForResult(intent, TAKE_BIG_PICTURE);
    }

    public static void CropSmallImageFromCamera(Activity activity) {
        Uri imageUri = Uri.fromFile(new File(imagePath(activity)));
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        activity.startActivityForResult(intent, TAKE_SMALL_PICTURE);
    }

    public static void CropSmallImageFromCamera(Fragment fragment) {
        Uri imageUri = Uri.fromFile(new File(imagePath(fragment.getActivity())));
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        fragment.startActivityForResult(intent, TAKE_SMALL_PICTURE);
    }

    public static void cropImageUri(Activity activity, Uri uri, int outputX, int outputY, int requestCode) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);

        if (activity != null)
            activity.startActivityForResult(intent, requestCode);
    }

    public static void cropImageUri2(Activity activity, Uri uri, int outputX, int outputY, int requestCode) {
        Uri imageUri = Uri.fromFile(new File(imagePath(activity)));

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);

        if (activity != null)
            activity.startActivityForResult(intent, requestCode);
    }

    public static void cropImageUri(Fragment fragment, Uri uri, boolean isTakeCamera, int outputX, int outputY, int requestCode) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);

        if(isTakeCamera == true){
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        else {
            Uri imageUri = Uri.fromFile(new File(imagePath(fragment.getContext())));
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        }
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);

        if (fragment != null && fragment.getActivity() != null)
            fragment.startActivityForResult(intent, requestCode);
    }

    public static void CropBigImageFromGallery(Activity activity) {
        Uri imageUri = Uri.fromFile(new File(imagePath(activity)));
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
        intent.setType("image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 500);
        intent.putExtra("outputY", 500);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        activity.startActivityForResult(intent, CHOOSE_BIG_PICTURE);
    }

    public static void CropBigImageFromGallery(Fragment fragment) {
        Uri imageUri = Uri.fromFile(new File(imagePath(fragment.getActivity())));
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
        intent.setType("image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 500);
        intent.putExtra("outputY", 500);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        fragment.startActivityForResult(intent, CHOOSE_BIG_PICTURE);
    }

    public static void CropSmallImageFromGallery(Fragment fragment) {
        Uri imageUri = Uri.fromFile(new File(imagePath(fragment.getContext())));
        Intent intent = new Intent();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");

            fragment.startActivityForResult(intent, CHOOSE_SMALL_PICUTRE_FROM_KAKAT);
        } else {
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            intent.putExtra("crop", "true");
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("outputX", 200);
            intent.putExtra("outputY", 200);
            intent.putExtra("scale", true);
            intent.putExtra("return-data", true);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
            intent.putExtra("noFaceDetection", true);
            fragment.startActivityForResult(intent, CHOOSE_SMALL_PICTURE);
        }

    }

    public static void CropSmallImageFromGallery(Activity activity) {
        Uri imageUri = Uri.fromFile(new File(imagePath(activity)));
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
        intent.setType("image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        activity.startActivityForResult(intent, CHOOSE_SMALL_PICTURE);
    }

    public static Bitmap decodeUriAsBitmap(Activity activity, Uri uri) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(activity.getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }

    public static Bitmap decodeUriAsBitmap(Fragment fragment, Uri uri) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(fragment.getContext().getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }

    public static Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) {
            baos.reset();
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);
            options -= 10;
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);
        return bitmap;
    }

    public static String imagePath(Context context) {
        mPath = Environment.getExternalStorageDirectory() + "/VHospital";
        File f = new File(mPath);
        if (!f.exists()) {
            f.mkdir();
        }

        mPath = mPath + "/ImageFile";
        f = new File(mPath);
        if (!f.exists()) {
            f.mkdir();
        }

        mPath = mPath + "/" + String.valueOf(System.currentTimeMillis()) + ".jpg";

        AppPreference.putString(context, "photoPath", mPath);

        return mPath;
    }

    public static String getPath() {
        return mPath;
    }
}