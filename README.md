<h1>基于Android-Universal-Image-Loader 1.9.5</h1>  

+  修改BaseImageDownloader#getStreamFromNetwork()，内部以okhttp实现
+  修改LoadAndDisplayImageTask#isViewReused()返回值，以适应特殊情况：加载相同uri资源
+  ImageLoader便捷操作类：ImageLoaderWrapper
