import java.util.Arrays;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.objdetect.CascadeClassifier;

public class GetImgFace {

	private static String classifier = "D:/opencv/sources/data/haarcascades/haarcascade_frontalface_default.xml";
	
	static {
		// 必须要加载Opencv的Library
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	public static void main(String[] args) {
		
		//图片路径不能有中文...
		GetImgFace.getImgFace("C:\\Users\\ISJINHAO\\Desktop\\test.jpg");
		
	}
	
	public static List<Rect> getImgFace(String imgPath){
		
		 /*
		  * 加载分类器，选择已经训练好的opencv分类器，训练器路径：%OPENCV_HOME%/sources/data/haarcascades/
		  * 分类器包含眼睛，人脸，人体，微笑等等，其中对于仅仅识别人脸来说，最好的训练器是：
		  * 	haarcascade_frontalface_default.xml
		  * 	haarcascade_frontalface_alt.xml
		  * 	haarcascade_frontalface_alt2.xml
		  * */
		
		// 分类器路径不要有中文...
		CascadeClassifier faceDetector = new CascadeClassifier(classifier);
		if (faceDetector.empty()) {
			System.out.println("请选择正确的分类器！");
			return null;
		}
		// 创建Mat，Mat是用来保存图片信息的类。Imgcodecs是用来读取图片的工具类
		Mat image = Imgcodecs.imread(imgPath);
		
		// 检测人脸，检测结果存在faceDetections中
		MatOfRect faceDetections = new MatOfRect();
		faceDetector.detectMultiScale(image, faceDetections);
		
		List<Rect> faceList = Arrays.asList(faceDetections.toArray());

		System.out.println(faceList);
		
		return faceList;
	}
	
}
