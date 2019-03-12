import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class FaceRect {
	public static void main(String[] args) {
		
		String imgPath = "C:\\Users\\ISJINHAO\\Desktop\\test.jpg";
		
		List<Rect> faceRects = GetImgFace.getImgFace(imgPath);
		
		//把识别出来的图片分别打框
		Iterator<Rect> iterator1 = faceRects.iterator();
		while(iterator1.hasNext()) {
			Rect rect = iterator1.next();
			FaceRect.imageMark(imgPath, "D:\\test\\" + UUID.randomUUID() + ".jpg", rect);
		}
		
		//在一张图片中把所有识别出来的人脸都打框
		Mat image = Imgcodecs.imread(imgPath);
		Iterator<Rect> iterator2 = faceRects.iterator();
		while(iterator2.hasNext()) {
			Rect rect = iterator2.next();
			Imgproc.rectangle(image, new Point(rect.x, rect.y), // 左上点
				new Point(rect.x + rect.width, rect.y + rect.height), // 右下点
				new Scalar(0, 255, 0), 2); // 框的颜色和粗细
		}
		Imgcodecs.imwrite("D:\\test\\" + UUID.randomUUID() + ".jpg", image);
	}
	
	public static File imageMark(String imagePath, String outFilePath, Rect rect) {
		Mat image = Imgcodecs.imread(imagePath);	// 原始图片
		
		//Imgproc.rectangle的作用是在修改image中的数据，把相应的位置打上框
		Imgproc.rectangle(image, new Point(rect.x, rect.y), // 左上点
				new Point(rect.x + rect.width, rect.y + rect.height), // 右下点
				new Scalar(0, 255, 0), 2); // 框的颜色和粗细
		
		// 把mat写入图片
		Imgcodecs.imwrite(outFilePath, image);
		return new File(outFilePath);
	}
}
