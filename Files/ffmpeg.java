package tcp;

import java.io.*;

public class ffmpeg {
	final static String foldPath = "/Users/zhang/Downloads/";
	final static String ffmpegCommand = "/Users/zhang/ffmpeg/bin/ffmpeg -i ";
	final static String codeCommand = " -vcodec copy -acodec copy ";
	final static String outputPath = "";
	final static String extName = ".mp4";
	static int number = 0;

	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		codeVideos(foldPath);
		double usedTime = (double) (System.currentTimeMillis() - startTime) / 1000;
		System.out.println("耗时：" + usedTime + " 秒。文件与文件夹一共" + number + "个。");
	}

	public static void codeVideos(String path) throws IOException {
		File[] fold = new File(path).listFiles();
		for (File f : fold) {
			if (f.isDirectory()) {
				codeVideos(f.toString());
			} else if (!f.toString().equals(foldPath + ".DS_Store") && !f.toString().equals(foldPath + ".localized")) {
				final String command = ffmpegCommand + f.toString() + codeCommand + outputPath + getName(f.toString());
				new File(outputPath).mkdirs();
				Runtime.getRuntime().exec(command);
				System.out.println(f.toString());
			}
		}
	}

	public static String getName(String filename) {
		number += 1;
		int start, end;
		if (outputPath.equals("")) {
			end = filename.indexOf('.');
			if ((end > -1) && (end < (filename.length()))) {
				return filename.substring(0, end) + extName;
			}
		} else if ((filename != null) && (filename.length() > 0)) {
			start = filename.lastIndexOf('/');
			end = filename.indexOf('.');
			if ((start > -1) && (start < (filename.length())) && (end > -1) && (end < (filename.length()))) {
				return filename.substring(start, end) + extName;
			}
		}
		return filename;
	}
}