import java.io.File;
import java.io.IOException;

/**
 * 
 * @author zhang
 * @date 2020/4/25
 *
 */
public class ReCodeVideos {
	final static String FOLD_PATH = "D:\\Zhang\\Downloads\\";
	final static String FFMPEG_COMMAND = "D:\\App\\ffmpeg\\bin\\ffmpeg -i ";// -threads 4
	final static String CODE_COMMAND = " -vcodec copy -acodec copy ";
	final static String OUTPUT_PATH = "";
	final static String EXT_NAME = ".mp4";
	static int number = 0;

	public static void main(String[] args) throws IOException, InterruptedException {
		long startTime = System.currentTimeMillis();
		codeVideos(FOLD_PATH);
		double usedTime = (double) (System.currentTimeMillis() - startTime) / 1000;
		System.out.println("耗时：" + usedTime + " 秒。文件与文件夹一共" + number + "个。");
	}

	public static void codeVideos(String path) throws IOException, InterruptedException {
		File[] fold = new File(path).listFiles();
		for (File f : fold) {
			if (f.isDirectory()) {
				codeVideos(f.toString());
			} else if (!f.toString().equals(FOLD_PATH + ".DS_Store") && !f.toString().equals(FOLD_PATH + ".localized") && !f.toString().equals(FOLD_PATH + "desktop.ini")) {
				final String command = FFMPEG_COMMAND + f.toString() + CODE_COMMAND + OUTPUT_PATH + getName(f.toString());
				new File(OUTPUT_PATH).mkdirs();
				Runtime.getRuntime().exec(command);
				System.out.println(getName(f.toString()));
				Thread.sleep((f.length() / 52428800) * 1000);
				number += 1;
			}
		}
	}

	public static String getName(String filename) {
		int start, end;
		if ("".equals(OUTPUT_PATH)) {
			end = filename.indexOf('.');
			if ((end > -1) && (end < (filename.length()))) {
				return filename.substring(0, end) + EXT_NAME;
			}
		} else if ((filename != null) && (filename.length() > 0)) {
			start = filename.lastIndexOf('\\');
			end = filename.indexOf('.');
			if ((start > -1) && (start < (filename.length())) && (end > -1) && (end < (filename.length()))) {
				return filename.substring(start, end) + EXT_NAME;
			}
		}
		return filename;
	}
}
