import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class FaceCut {
	
	public static void main(String[] args) {
		
	String imgPath = "C:\\Users\\ISJINHAO\\Desktop\\test.jpg";
		
		List<Rect> faceRects = GetImgFace.getImgFace(imgPath);
		
		//把识别出来的图片分别打框
		Iterator<Rect> iterator1 = faceRects.iterator();
		while(iterator1.hasNext()) {
			Rect rect = iterator1.next();
			FaceCut.imageCut(imgPath, "D:\\test\\" + UUID.randomUUID() + ".jpg", rect);
		}
		
	}
	
	public static File imageCut(String imagePath, String outFilePath, Rect rect) {
		Mat image = Imgcodecs.imread(imagePath);
		
		// 按照原始图片中的人脸提取出来
		Mat sub = image.submat(rect);
		Mat mat = new Mat();
		Size size = new Size(rect.width, rect.height);
		
		// 修改提取出来的人脸信息sub在坐标轴的位置。
		Imgproc.resize(sub, mat, size);
		
		// 将截图保存
		Imgcodecs.imwrite(outFilePath, mat);
		return new File(outFilePath);
	}
}
