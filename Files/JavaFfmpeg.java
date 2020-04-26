import java.io.*;

/**
 * 
 * @author zhang
 * @date 2020/4/25
 *
 */
public class JavaFfmpeg {
	final static String FOLD_PATH = "/Users/zhang/Downloads/";
	final static String FFMPEG_COMMAND = "/Users/zhang/ffmpeg/bin/ffmpeg -i ";//-threads 4
	final static String CODE_COMMAND = " -vcodec copy -acodec copy ";
	final static String OUTPUT_PATH = "";
	final static String EXT_NAME = ".mp4";
	static int number = 0;

	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		codeVideos(FOLD_PATH);
		double usedTime = (double) (System.currentTimeMillis() - startTime) / 1000;
		System.out.println("耗时：" + usedTime + " 秒。文件与文件夹一共" + number + "个。");
	}

	public static void codeVideos(String path) throws IOException {
		File[] fold = new File(path).listFiles();
		for (File f : fold) {
			if (f.isDirectory()) {
				codeVideos(f.toString());
			} else if (!f.toString().equals(FOLD_PATH + ".DS_Store") && !f.toString().equals(FOLD_PATH + ".localized") && !f.toString().equals(FOLD_PATH + "desktop.ini")) {
				final String command = FFMPEG_COMMAND + f.toString() + CODE_COMMAND + OUTPUT_PATH + getName(f.toString());
				new File(OUTPUT_PATH).mkdirs();
				Runtime.getRuntime().exec(command);
				System.out.println(f.toString());
			}
		}
	}

	public static String getName(String filename) {
		number += 1;
		int start, end;
		if ("".equals(OUTPUT_PATH)) {
			end = filename.indexOf('.');
			if ((end > -1) && (end < (filename.length()))) {
				return filename.substring(0, end) + EXT_NAME;
			}
		} else if ((filename != null) && (filename.length() > 0)) {
			start = filename.lastIndexOf('/');
			end = filename.indexOf('.');
			if ((start > -1) && (start < (filename.length())) && (end > -1) && (end < (filename.length()))) {
				return filename.substring(start, end) + EXT_NAME;
			}
		}
		return filename;
	}
}