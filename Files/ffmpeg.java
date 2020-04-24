package tcp;

import java.io.*;

public class ffmpeg {

	/*
	 * foldPath：源视频文件夹的地址
	 * ffmpegCommand：ffmpeg的地址与命令参数。
	 * 		可追加多线程参数，例如4线程：-threads 4
	 * codeCommand：转码or编码的命令参数。
	 * 		仅封装不重新编码（速度快）使用" -vcodec copy -acodec copy "	注意双引号内部左右有空格
	 * 		重新编码（速度极慢）使用" "	注意双引号内有空格
	 */
	final static String foldPath = "/Users/zhang/Movies/";
	final static String ffmpegCommand = "/Users/zhang/ffmpeg/bin/ffmpeg -i ";
	final static String codeCommand = " -vcodec copy -acodec copy ";

	/*
	 * 提示：若转码需求变更，请修改上方静态代码。
	 * 警告：下方所有代码不可修改。
	 */
	public static void main(String[] args) throws IOException {
		//codeVideos(foldPath);
		File[] fold = new File(foldPath).listFiles();
		for(File f:fold) {
			System.out.println(getName0(f.toString()));
		}
	}

	public static void codeVideos(String path) throws IOException {
		File[] fold = new File(path).listFiles();
		for (File f : fold) {
			if (f.isDirectory()) {
				codeVideos(f.toString());
			} else if (!f.toString().equals(foldPath + ".DS_Store") && !f.toString().equals(foldPath + ".localized")) {
				final String command = ffmpegCommand + f.toString() + codeCommand + getName0(f.toString()) + ".mp4";
				Runtime.getRuntime().exec(command);
				System.out.println(f.toString());
				System.out.println("Done");
			}
		}
	}

	/*
	 * 将文件在系统中的全限定名中"."后所有的内容删去。
	 * 例如：寄明月.flv  >>>  寄明月
	 * 例如：记录2020.03.12.mp4  >>>  记录2020
	 */
	public static String getName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.indexOf('.');
			if ((dot > -1) && (dot < (filename.length()))) {
				return filename.substring(0, dot);
			}
		}
		return filename;
	}
	public static String getName0(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int start = filename.lastIndexOf('/');
			int end=filename.indexOf('.');
			if ((start > -1) && (start < (filename.length()))&&(end> -1)&&(end<(filename.length()))) {
				return filename.substring(start, end);
			}
		}
		return filename;
	}
}