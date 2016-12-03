package com.stone.uilrevision.activity.util;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.DiskCache;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.utils.L;
import com.stone.uilrevision.BuildConfig;
import com.stone.uilrevision.R;

import java.io.File;

/**
 * desc   : ImageLoader便捷操作类
 * author : stone
 * email  : aa86799@163.com
 * time   : 2016/12/4 00 04
 */
public class ImageLoaderWrapper {

    /**
     * 初始化配置
     */
    public static void init(Context context) {
        initCacheLibrary(context);
    }

    /**
     * 异步加载uri资源图到imageView
     * @param uri
     * @param imageView
     */
    public static void loadImage(String uri, ImageView imageView) {
        ImageLoader.getInstance().displayImage(uri, imageView);
    }

    /**
     * 异步加载本地默认图到imageView
     * 可以使用{@link #createDefDisplayOptions(int)} 创建默认图的options
     * {@link #loadImage(String, ImageView, DisplayImageOptions)}
     * @param imageView
     * @param options   加载选项设置
     */
    public static void loadImage(ImageView imageView, DisplayImageOptions options) {
        loadImage("", imageView, options);
    }

    /**
     * 异步加载本地默认图到imageView
     * {@link #loadImage(String, ImageView, DisplayImageOptions)}
     * @param imageView
     * @param resId     默认图的drawable中的资源id
     */
    public static void loadImage(ImageView imageView, int resId) {
        loadImage("", imageView, createDefDisplayOptions(resId));
    }

    /**
     * 异步加载uri资源图到imageView，并设置了默认图
     * @param uri
     * @param imageView
     * @param resId     默认图的drawable中的资源id
     */
    public static void loadImage(String uri, ImageView imageView, int resId) {
        loadImage(uri, imageView, createDefDisplayOptions(resId));
    }


    /**
     * 异步加载uri资源图到imageView，并设置了默认图
     * @param uri
     * @param
     * @param
     */
    public static void loadImage(String uri, ImageAware imageAware, DisplayImageOptions options) {
        ImageLoader.getInstance().displayImage(uri,imageAware,options);
    }


    /**
     * 异步加载uri资源图到imageView，并设置了默认图
     * 可以使用{@link #createDefDisplayOptions(int)} 创建默认图的options
     * @param uri
     * @param imageView
     * @param options   加载选项设置
     */
    public static void loadImage(String uri, ImageView imageView, DisplayImageOptions options) {
        ImageLoader.getInstance().displayImage(uri, imageView, options);
    }

    /**
     * 异步加载uri资源图到imageView，并指定需要的图片尺寸
     * @param uri
     * @param imageView
     * @param targetImageSize   最小尺寸，加载的图片将自动缩放至大于或等于该尺寸
     */
    public static void loadImage(String uri, ImageView imageView, ImageSize targetImageSize) {
        ImageLoader.getInstance().displayImage(uri, imageView, targetImageSize);
    }

    /**
     * 异步加载uri资源图到imageView，并设置ImageLoadingListener
     * @param uri
     * @param imageView
     * @param listener  加载状态监听：开始、失败、完成、取消
     */
    public static void loadImage(String uri, ImageView imageView, ImageLoadingListener listener) {
        ImageLoader.getInstance().displayImage(uri, imageView, listener);
    }

    /**
     * 异步加载uri资源图到imageView，并设置默认图资源id、ImageLoadingListener
     * @param uri
     * @param imageView
     * @param resId     默认图的drawable中的资源id
     * @param listener  加载状态监听：开始、失败、完成、取消
     */
    public static void loadImage(String uri, ImageView imageView, int resId,
                                 ImageLoadingListener listener) {
        loadImage(uri, imageView, createDefDisplayOptions(resId), listener);
    }

    /**
     * 异步加载uri资源图到imageView，并设置DisplayImageOptions、ImageLoadingListener
     * 可以使用{@link #createDefDisplayOptions(int)} 创建默认图的options
     * @param uri
     * @param imageView
     * @param options   加载选项设置
     * @param listener  加载状态监听：开始、失败、完成、取消
     */
    public static void loadImage(String uri, ImageView imageView, DisplayImageOptions options,
                                 ImageLoadingListener listener) {
        ImageLoader.getInstance().displayImage(uri, imageView, options, listener);
    }

    /**
     * 异步加载uri资源图到imageView，并设置默认图资源id、ImageLoadingListener、ImageLoadingProgressListener
     * @param uri
     * @param imageView
     * @param resId             默认图的drawable中的资源id
     * @param listener          加载状态监听：开始、失败、完成、取消
     * @param progressListener  加载进度监听
     */
    public static void loadImage(String uri, ImageView imageView, int resId,
                                 ImageLoadingListener listener, ImageLoadingProgressListener progressListener) {
        loadImage(uri, imageView, createDefDisplayOptions(resId), listener, progressListener);
    }

    /**
     * 异步加载uri资源图到imageView，并设置DisplayImageOptions、ImageLoadingListener、ImageLoadingProgressListener
     * 可以使用{@link #createDefDisplayOptions(int)} 创建默认图的options
     * @param uri
     * @param imageView
     * @param options           加载选项设置
     * @param listener          加载状态监听：开始、失败、完成、取消
     * @param progressListener  加载进度监听
     */
    public static void loadImage(String uri, ImageView imageView, DisplayImageOptions options,
                                 ImageLoadingListener listener, ImageLoadingProgressListener progressListener) {
        ImageLoader.getInstance().displayImage(uri, imageView, options, listener, progressListener);
    }

    /**
     * 异步加载uri资源图；以{@link ImageLoadingListener#onLoadingComplete(String, View, Bitmap)}
     * 回调处理加载成功时的Bitmap
     * @param uri
     * @param listener  加载状态监听：开始、失败、完成、取消
     */
    public static void loadBitmap(String uri, ImageLoadingListener listener) {
        ImageLoader.getInstance().loadImage(uri, listener);
    }

    /**
     * 异步加载uri资源图；以{@link ImageLoadingListener#onLoadingComplete(String, View, Bitmap)}
     * 回调处理加载成功时的Bitmap
     * @param uri
     * @param targetImageSize   最小尺寸，加载的图片将自动缩放至大于或等于该尺寸
     * @param listener          加载状态监听：开始、失败、完成、取消
     */
    public static void loadBitmap(String uri, ImageSize targetImageSize, ImageLoadingListener listener) {
        ImageLoader.getInstance().loadImage(uri, targetImageSize, listener);
    }

    /**
     * 异步加载uri资源图；以{@link ImageLoadingListener#onLoadingComplete(String, View, Bitmap)}
     * 回调处理加载成功时的Bitmap
     * @param uri
     * @param resId       默认图的drawable中的资源id
     * @param listener    加载状态监听：开始、失败、完成、取消
     */
    public static void loadBitmap(String uri, int resId, ImageLoadingListener listener) {
        loadBitmap(uri, createDefDisplayOptions(resId), listener);
    }

    /**
     * 异步加载uri资源图；以{@link ImageLoadingListener#onLoadingComplete(String, View, Bitmap)}
     * 回调处理加载成功时的Bitmap
     * @param uri
     * @param options       加载选项设置
     * @param listener      加载状态监听：开始、失败、完成、取消
     */
    public static void loadBitmap(String uri, DisplayImageOptions options, ImageLoadingListener listener) {
        ImageLoader.getInstance().loadImage(uri, options, listener);
    }

    /**
     * 异步加载uri资源图；以{@link ImageLoadingListener#onLoadingComplete(String, View, Bitmap)}
     * 回调处理加载成功时的Bitmap
     * @param uri
     * @param targetImageSize   最小尺寸，加载的图片将自动缩放至大于或等于该尺寸
     * @param resId             默认图的drawable中的资源id
     * @param listener          加载状态监听：开始、失败、完成、取消
     */
    public static void loadBitmap(String uri, ImageSize targetImageSize, int resId,
                                  ImageLoadingListener listener) {
        loadBitmap(uri, targetImageSize, createDefDisplayOptions(resId), listener);
    }

    /**
     * 异步加载uri资源图；以{@link ImageLoadingListener#onLoadingComplete(String, View, Bitmap)}
     * 回调处理加载成功时的Bitmap
     * @param uri
     * @param targetImageSize   最小尺寸，加载的图片将自动缩放至大于或等于该尺寸
     * @param options           加载选项设置
     * @param listener          加载状态监听：开始、失败、完成、取消
     */
    public static void loadBitmap(String uri, ImageSize targetImageSize, DisplayImageOptions options,
                                  ImageLoadingListener listener) {
        ImageLoader.getInstance().loadImage(uri, targetImageSize, options, listener);
    }

    /**
     * 异步加载uri资源图；以{@link ImageLoadingListener#onLoadingComplete(String, View, Bitmap)}
     * 回调处理加载成功时的Bitmap
     * @param uri
     * @param targetImageSize   最小尺寸，加载的图片将自动缩放至大于或等于该尺寸
     * @param resId             默认图的drawable中的资源id
     * @param listener          加载状态监听：开始、失败、完成、取消
     * @param progressListener  加载进度监听
     */
    public static void loadBitmap(String uri, ImageSize targetImageSize, int resId,
                                  ImageLoadingListener listener, ImageLoadingProgressListener progressListener) {
        loadBitmap(uri, targetImageSize, createDefDisplayOptions(resId), listener, progressListener);
    }

    /**
     * 异步加载uri资源图；以{@link ImageLoadingListener#onLoadingComplete(String, View, Bitmap)}
     * 回调处理加载成功时的Bitmap
     * @param uri
     * @param targetImageSize   最小尺寸，加载的图片将自动缩放至大于或等于该尺寸
     * @param options           加载选项设置
     * @param listener          加载状态监听：开始、失败、完成、取消
     * @param progressListener  加载进度监听
     */
    public static void loadBitmap(String uri, ImageSize targetImageSize, DisplayImageOptions options,
                                  ImageLoadingListener listener, ImageLoadingProgressListener progressListener) {
        ImageLoader.getInstance().loadImage(uri, targetImageSize, options, listener, progressListener);
    }

    /**
     * 从drawable下的资源id，同步加载图片
     * @param resId
     * @return  对应资源的Bitmap
     */
    public static Bitmap getBitmapFromLocal(int resId) {
        if (resId != 0){
            return ImageLoader.getInstance().loadImageSync("drawable://" + resId, createLocalResDefDisplayOptions());
        }else{
            return null;
        }
    }

    /**
     * 使用系统方式获取Drawable对象
     * @param context
     * @param resId
     * @return
     */
    public static Drawable getDrawableFromLocal(Context context, int resId) {
        return ContextCompat.getDrawable(context, resId);
    }


    /**
     * 从指定uri资源，移除对应的磁盘缓存
     * @param uri
     */
    public static boolean removeDiskCache(String uri) {
        return ImageLoader.getInstance().getDiskCache().remove(uri);
    }

    /**
     * 从指定uri资源，移除对应的内存缓存
     * @param uri
     */
    public static Bitmap removeMemoryCache(String uri) {
        return ImageLoader.getInstance().getMemoryCache().remove(uri);
    }

    /**
     * 清除所有磁盘缓存
     */
    public static void clearDiskCache() {
        ImageLoader.getInstance().clearDiskCache();
    }

    /**
     * 清除所有内存缓存
     */
    public static void clearMemoryCache() {
        ImageLoader.getInstance().clearMemoryCache();
    }

    /**
     * 初始化缓存工具
     */
    @SuppressWarnings("deprecation")
    private static void initCacheLibrary(Context context) {
        /** 磁盘缓存大小 */
        int mDiskCacheSize = 1024 * 1024 * 50; // 50M
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .diskCache(getDiskCache(context))//本地缓存配置
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new UsingFreqLimitedMemoryCache(2* 1024 * 1024))//getMemoryCache(context) //new WeakMemoryCache()
                .memoryCacheSize(2 * 1024 * 1024)
                .discCacheSize(mDiskCacheSize)
                .threadPoolSize(3)//图片线程池大小1~5
                .threadPriority(Thread.NORM_PRIORITY - 2)//线程级别 3
                .discCacheFileCount(60) //缓存的文件数量
                .denyCacheImageMultipleSizesInMemory()//一个URL对应一个图片
                .imageDownloader(new BaseImageDownloader(context))
                .tasksProcessingOrder(QueueProcessingType.FIFO)//任务队列执行顺序 后进先出
                .writeDebugLogs()
                .defaultDisplayImageOptions(createDefDisplayOptions())
                .build();

        ImageLoader.getInstance().init(config);

        L.writeDebugLogs(BuildConfig.DEBUG);//打印UIL-log
    }

    /**
     * 内存缓存配置
     */
    public static LruMemoryCache getMemoryCache(Context context) {
        int cacheSize = 4 * 1024 * 1024;
        try {
            int memClass = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
            int availableSize = memClass >> 3;
            cacheSize = 1024 * 1024 * (availableSize == 0 ? 4 : availableSize);
            Log.d("cache", "getMemoryCache---memClass:" + memClass + "----availableSize:" + availableSize);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        LruMemoryCache memoryCache = new LruMemoryCache(cacheSize);

        return memoryCache;
    }

    /**
     * 磁盘缓存
     * 命名：MD5加密
     * 最大大小：50兆
     * @param context
     */
    public static DiskCache getDiskCache(Context context) {
        File cacheFile;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            /*
             SDCard/Android/data/你的应用的包名/files/ 目录      一般放一些长时间保存的数据
             对应 设置->应用->应用详情里面的”清除数据“选项
             */
            cacheFile = context.getExternalCacheDir();
            if (cacheFile == null){
                cacheFile = new File(Environment.getExternalStorageDirectory().getPath()
                        + context.getString(R.string.app_name)+"/img/");
            }
        } else {
            /*
            SDCard/Android/data/你的应用包名/cache/目录         一般存放临时缓存数据
            对应 设置->应用->应用详情里面的”清除缓存“选项
             */
            cacheFile = context.getCacheDir();
        }


        /**
         * note:UnlimitedDiscCache is pretty faster than other limited disk cache implementations
         */
        DiskCache diskCache = new UnlimitedDiskCache(cacheFile, null, new HashCodeFileNameGenerator());

        return diskCache;
    }

    /**
     * 创建默认图的DisplayImagetions
     * @param resId   默认图的drawable中的资源id
     * @return
     */
    public static DisplayImageOptions createDefDisplayOptions(int resId) {
        return new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .resetViewBeforeLoading(true)
                .displayer(new SimpleBitmapDisplayer())
                .bitmapConfig(Bitmap.Config.RGB_565)
//                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .showImageForEmptyUri(resId)
                .showImageOnFail(resId)
                .showImageOnLoading(resId)
                .build();
    }

    /**
     * 创建默认图的DisplayImageOptions，并裁剪成圆角样式
     * @param cornerRadiusPixels    圆角半径像素值
     * @param resId                 默认图的drawable中的资源id
     * @return
     */
    public static DisplayImageOptions createDefDisplayOptions(int cornerRadiusPixels, int resId) {
        return new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .resetViewBeforeLoading(true)
                .displayer(new RoundedBitmapDisplayer(cornerRadiusPixels))
                .bitmapConfig(Bitmap.Config.ARGB_8888)
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
                .showImageForEmptyUri(resId)
                .showImageOnFail(resId)
                .showImageOnLoading(resId)
                .build();
    }

    /**
     * 作为ImageLoader的默认配置使用
     */
    private static DisplayImageOptions createDefDisplayOptions() {
        return new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .resetViewBeforeLoading(true)
                .displayer(new SimpleBitmapDisplayer())
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
//                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .build();
    }

    /**
     * 作为ImageLoader加载本地图drawable图片的默认配置使用
     */
    private static DisplayImageOptions createLocalResDefDisplayOptions() {
        return new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(false)
                .resetViewBeforeLoading(true)
                .displayer(new SimpleBitmapDisplayer())
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
//                .bitmapConfig(Bitmap.Config.ARGB_8888)
//                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
                .build();
    }



    public static void cancelTask(ImageAware imageAware){
        ImageLoader.getInstance().cancelDisplayTask(imageAware);
    }
}
