package com.crocodic.coderepo.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.crocodic.coderepo.R;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by crocodic-mbp8 on 9/6/17.
 */

public class ImageResult {

    /**
     *  This class is used to take pictures from camera, gallery with additional cropping images
     *
     * Declaration :
     * ImageResult whatEver = new ImageResult(activity);
     *
     * <p>
     * Use This class :
     * <p>
     * - Get Image File Without Cropping Image
     *
     * * From Gallery
     * whatEver.onSelectFromGalleryResult(data); -> this method return file
     *
     * * From Camera
     * whatEver.onSelectFromCamera(getActivity()); -> this method return file
     *
     * * Camera with cropping image
     * whatEver.handleCrop(data); -> this method return file
     *
     * <p>
     * Dependencies :
     * compile 'com.github.yalantis:ucrop:2.2.1'
     * <p>
     *
     * Manifest :
     * <activity
     * android:name="com.yalantis.ucrop.UCropActivity"
     * android:screenOrientation="portrait"
     * android:theme="@style/Theme.AppCompat.Light.NoActionBar"/>
     */


    Activity activity;
    String directoryName;

    public ImageResult(Activity activity, String directoryName) {
        this.activity = activity;
        this.directoryName = directoryName;
    }

    public File onSelectFromGalleryResult(Intent data) {
        Uri selectedImageUri = data.getData();
        String[] projection = {MediaStore.MediaColumns.DATA};
        @SuppressWarnings("deprecation")
        Cursor cursor = activity.managedQuery(selectedImageUri, projection, null, null,
                null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();

        String selectedImagePath = cursor.getString(column_index);

        int rotasi = getCameraPhotoOrientation(activity, selectedImageUri, selectedImagePath);


        Bitmap bm;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(selectedImagePath, options);

        Bitmap bitmap = null;

        try {
            bitmap = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), selectedImageUri);
        } catch (IOException e) {
            e.printStackTrace();
        }


        Bitmap scaledBitmap = scaleDown(bitmap, 1000, false, rotasi);


        return saveBitmap(scaledBitmap);

    }

    public File onSelectFromCamera() {
        String nameFile;
        try {
            File f = new File(Environment.getExternalStorageDirectory().toString());
            for (File temp : f.listFiles()) {
                if (temp.getName().equals("temp.jpg")) {
                    f = temp;
                    break;
                }
            }
            Bitmap bitmap;
            BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();

            bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(), bitmapOptions);

            int rotasi = getCameraPhotoOrientation(activity, Uri.fromFile(f), f.getAbsolutePath());

            Bitmap scaledBitmap = scaleDown(bitmap, 1000, false, rotasi);


            return saveBitmap(scaledBitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private int getCameraPhotoOrientation(Context context, Uri imageUri, String imagePath) {
        int rotate = 0;
        try {
            context.getContentResolver().notifyChange(imageUri, null);
            File imageFile = new File(imagePath);


            ExifInterface exif = new ExifInterface(imageFile.getAbsolutePath());
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = 270;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90;
                    break;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        return rotate;
    }

    private static Bitmap scaleDown(Bitmap realImage, float maxImageSize,
                                    boolean filter, float rotasi) {
        float ratio = Math.min(
                (float) maxImageSize / realImage.getWidth(),
                (float) maxImageSize / realImage.getHeight());
        int width = Math.round((float) ratio * realImage.getWidth());
        int height = Math.round((float) ratio * realImage.getHeight());

        Matrix matrix = new Matrix();
        matrix.postRotate(rotasi);

        Bitmap newBitmap = Bitmap.createScaledBitmap(realImage, width,
                height, filter);
        Bitmap fixBitmap = Bitmap.createBitmap(newBitmap, 0, 0, width, height, matrix, true);

        return fixBitmap;
    }

    private File saveBitmap(Bitmap image) {
        File pictureFile = null;
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());

        pictureFile = new File(activity.getCacheDir() + File.separator + "IMG_" + timeStamp + ".jpg");

        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            image.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            File file = copyFileToDownloads(Uri.fromFile(pictureFile));
            fos.close();

            return file;
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private File copyFileToDownloads(Uri croppedFileUri) throws Exception {

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), directoryName);
        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            mediaStorageDir.mkdir();
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File newMediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");

        FileInputStream inStream = new FileInputStream(new File(croppedFileUri.getPath()));
        FileOutputStream outStream = new FileOutputStream(newMediaFile);
        FileChannel inChannel = inStream.getChannel();
        FileChannel outChannel = outStream.getChannel();
        inChannel.transferTo(0, inChannel.size(), outChannel);
        inStream.close();
        outStream.close();


        return newMediaFile;

    }

    public void cropImageActivity(Uri source) {

        String filename = source.toString().substring(source.toString().lastIndexOf("/") + 1);

        Uri destination = Uri.fromFile(new File(activity.getCacheDir(), filename));
        UCrop uCrop = UCrop.of(source, destination);
        uCrop = advancedConfig(uCrop);
        uCrop.start(activity);
    }

    public void cropImageFragment(Uri source, Fragment fragment) {

        String filename = source.toString().substring(source.toString().lastIndexOf("/") + 1);

        Uri destination = Uri.fromFile(new File(activity.getCacheDir(), filename));
        UCrop uCrop = UCrop.of(source, destination);
        uCrop = advancedConfig(uCrop);
        uCrop.start(activity, fragment);
    }

    private UCrop advancedConfig(@NonNull UCrop uCrop) {
        UCrop.Options options = new UCrop.Options();
        options.setCompressionFormat(Bitmap.CompressFormat.JPEG);
        options.setCompressionQuality(100);

        options.setToolbarColor(ContextCompat.getColor(activity, R.color.colorPrimaryDark));
        options.setStatusBarColor(ContextCompat.getColor(activity, R.color.colorPrimaryDark));
        options.setActiveWidgetColor(ContextCompat.getColor(activity, R.color.colorAccent));
        options.setMaxBitmapSize(800);


        return uCrop.withOptions(options);
    }

    public File handleCrop(@NonNull Intent result) {

        final Uri resultUri = UCrop.getOutput(result);
        File file = null;
        if (resultUri != null) {
            try {
                file = saveCroppedImage(resultUri);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(activity, "Can't retrieve image", Toast.LENGTH_SHORT).show();
        }

        return file;
    }

    private File saveCroppedImage(Uri source) throws IOException {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), directoryName);
        String filename = source.toString().substring(source.toString().lastIndexOf("/") + 1);

        File newMediaFile = new File(mediaStorageDir.getPath() + File.separator + filename);


        FileInputStream inStream = new FileInputStream(new File(source.getPath()));
        FileOutputStream outStream = new FileOutputStream(newMediaFile);
        FileChannel inChannel = inStream.getChannel();
        FileChannel outChannel = outStream.getChannel();
        inChannel.transferTo(0, inChannel.size(), outChannel);
        inStream.close();
        outStream.close();

        return newMediaFile;

    }

    @SuppressWarnings("ThrowableResultOfMethodCallIgnored")
    public void handleCropError(@NonNull Intent result) {
        final Throwable cropError = UCrop.getError(result);
        if (cropError != null) {
            Toast.makeText(activity, cropError.getMessage(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(activity, "Unexpected error", Toast.LENGTH_SHORT).show();
        }
    }


}
