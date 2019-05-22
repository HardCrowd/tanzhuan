package com.ecxls.integral.util.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil {
	private static Logger log = LoggerFactory.getLogger(FileUtil.class);

	/**
	 * 创建单个文件夹。
	 * 
	 * @param dir
	 * @param ignoreIfExitst
	 *            true 表示如果文件夹存在就不再创建了。false是重新创建。
	 * @throws IOException
	 */
	public static void createDir(String dir, boolean ignoreIfExitst)
			throws IOException {
		File file = new File(dir);
		if (ignoreIfExitst && file.exists()) {
			return;
		}
		if (file.mkdir() == false) {
			throw new IOException("Cannot create the directory = " + dir);
		}
	}

	/**
	 * 创建多个文件夹
	 * 
	 * @param dir
	 * @param ignoreIfExitst
	 * @throws IOException
	 */
	public static void createDirs(String dir, boolean ignoreIfExitst)
			throws IOException {
		File file = new File(dir);
		if (ignoreIfExitst && file.exists()) {
			return;
		}
		if (file.mkdirs() == false) {
			throw new IOException("Cannot create directories = " + dir);
		}
	}

	/**
	 * 删除一个文件。
	 * 
	 * @param filename
	 * @throws IOException
	 */
	public static void deleteFile(String filename) throws IOException {
		File file = new File(filename);
		log.trace("Delete file = " + filename);
		if (file.isDirectory()) {
			throw new IOException(
					"IOException -> BadInputException: not a file.");
		}
		if (file.exists() == false) {
			throw new IOException(
					"IOException -> BadInputException: file is not exist.");
		}
		if (file.delete() == false) {
			throw new IOException("Cannot delete file. filename = " + filename);
		}
	}

	/**
	 * 删除文件夹及其下面的子文件夹
	 * 
	 * @param dir
	 * @throws IOException
	 */
	public static void deleteDir(File dir) throws IOException {
		if (dir.isFile())
			throw new IOException(
					"IOException -> BadInputException: not a directory.");
		File[] files = dir.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				File file = files[i];
				if (file.isFile()) {
					file.delete();
				} else {
					deleteDir(file);
				}
			}
		}// if
		dir.delete();
	}

	public static String getPathSeparator() {
		return java.io.File.pathSeparator;
	}

	public static String getFileSeparator() {
		return java.io.File.separator;
	}



	/**
	 * 列出指定路径下的后缀为suffix参数值的文件集合
	 * 
	 * @param path
	 * @param suffix
	 * @param isDepth
	 *            是否搜索子目录
	 * @return List<File>
	 * @exception
	 * @since 1.0.0
	 */
	public static List<File> getFiles(String path, String suffix,
			boolean isDepth) {
		File dirOrFile = new File(path);
		return getFiles(dirOrFile, suffix, isDepth);
	}

	/**
	 * 列出指定目录或文件下的后缀为suffix参数值的文件集合
	 * 
	 * @param dirOrFile
	 * @param suffix
	 * @param isDepth
	 * @return List<File>
	 * @exception
	 * @since 1.0.0
	 */
	public static List<File> getFiles(File dirOrFile, String suffix,
			boolean isDepth) {
		List<File> results = new ArrayList<File>();
		listFile(results, dirOrFile, suffix, isDepth);
		return results;
	}

	/**
	 * 列出指定路径下的后缀为suffix参数值的文件路径集合
	 * 
	 * @param path
	 * @param suffix
	 * @param isDepth
	 * @return List<String>
	 * @exception
	 * @since 1.0.0
	 */
	public static List<String> getFilePaths(String path, String suffix,
			boolean isDepth) {
		List<File> files = getFiles(path, suffix, isDepth);
		List<String> filePaths = new ArrayList<String>();
		for (File file : files) {
			filePaths.add(file.getAbsolutePath());
		}
		return filePaths;
	}

	private static void listFile(List<File> results, File dirOrFile,
			String suffix, boolean isDepth) {
		// 是目录，同时需要遍历子目录
		if (dirOrFile.isDirectory() && isDepth == true) {
			File[] t = dirOrFile.listFiles();
			for (int i = 0; i < t.length; i++) {
				listFile(results, t[i], suffix, isDepth);
			}
		} else {
			String filePath = dirOrFile.getAbsolutePath();
			if (dirOrFile.isFile()) {
				if (StringUtils.isNotEmpty(suffix)) {
					int begIndex = filePath.lastIndexOf(".");// 最后一个.(即后缀名前面的.)的索引
					String tempsuffix = "";
					if (begIndex != -1) { // 防止是文件但却没有后缀名结束的文件
						tempsuffix = filePath.substring(begIndex + 1,
								filePath.length());
					}
					if (tempsuffix.equals(suffix)) {
						results.add(dirOrFile);
					}
				} else {
					// 后缀名为null则为所有文件
					results.add(dirOrFile);
				}
			}
		}
	}

	/**
	 * 获取到目录下面文件的大小。包含了子目录。
	 * 
	 * @param dir
	 * @return
	 * @throws IOException
	 */
	public static long getDirLength(File dir) throws IOException {
		if (dir.isFile())
			throw new IOException("BadInputException: not a directory.");
		long size = 0;
		File[] files = dir.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				File file = files[i];
				// file.getName();
				// System.out.println(file.getName());
				long length = 0;
				if (file.isFile()) {
					length = file.length();
				} else {
					length = getDirLength(file);
				}
				size += length;
			}// for
		}// if
		return size;
	}

	/**
	 * 将文件清空。
	 * 
	 * @param srcFilename
	 * @throws IOException
	 */
	public static void emptyFile(String srcFilename) throws IOException {
		File srcFile = new File(srcFilename);
		if (!srcFile.exists()) {
			throw new FileNotFoundException("Cannot find the file: "
					+ srcFile.getAbsolutePath());
		}
		if (!srcFile.canWrite()) {
			throw new IOException("Cannot write the file: "
					+ srcFile.getAbsolutePath());
		}
		FileOutputStream outputStream = new FileOutputStream(srcFilename);
		outputStream.close();
	}

	/**
	 * Write content to a fileName with the destEncoding 写文件。如果此文件不存在就创建一个。
	 * 
	 * @param content
	 *            String
	 * @param fileName
	 *            String
	 * @param destEncoding
	 *            String
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void writeFile(String content, String fileName,
			String destEncoding) throws FileNotFoundException, IOException {
		File file = null;
		try {
			file = new File(fileName);
			file.setWritable(true);
			file.setExecutable(true);
			file.setReadable(true);
			if (!file.exists()) {
				if (file.createNewFile() == false) {
					throw new IOException("create file '" + fileName
							+ "' failure.");
				}
			}
			if (file.isFile() == false) {
				throw new IOException("'" + fileName + "' is not a file.");
			}
			if (file.canWrite() == false) {
				throw new IOException("'" + fileName + "' is a read-only file.");
			}
		} finally {
			// we dont have to close File here
		}
		BufferedWriter out = null;
		try {
			FileOutputStream fos = new FileOutputStream(fileName);
			out = new BufferedWriter(new OutputStreamWriter(fos, destEncoding));
			out.write(content);
			out.flush();
		} catch (FileNotFoundException fe) {
			log.error("Error", fe);
			throw fe;
		} catch (IOException e) {
			log.error("Error", e);
			throw e;
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException ex) {
			}
		}
	}

	/**
	 * 写入文件
	 * 
	 * @param fileName
	 * @param charset
	 * @param content
	 * @throws IOException
	 */
	public static void writeFile(String content, File file, String charset)
			throws IOException {
		Writer writer = new OutputStreamWriter(new FileOutputStream(file),
				charset);
		writer.write(content);
		writer.close();
	}

	/**
	 * 将输入流写入指定文件中
	 * 
	 * @param ins
	 * @param file
	 *            void
	 * @exception
	 * @since 1.0.0
	 */
	public static void write(InputStream ins, File file) {
		try {
			OutputStream os = new FileOutputStream(file);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			os.close();
			ins.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * 读取文件的内容，并将文件内容以字符串的形式返回。
	 * 
	 * @param fileName
	 * @param srcEncoding
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String readFile(String fileName, String srcEncoding)
			throws FileNotFoundException, IOException {
		File file = null;
		try {
			file = new File(fileName);
			if (file.isFile() == false) {
				throw new IOException("'" + fileName + "' is not a file.");
			}
		} finally {
			// we dont have to close File here
		}
		BufferedReader reader = null;
		try {
			StringBuffer result = new StringBuffer(1024);
			FileInputStream fis = new FileInputStream(fileName);
			reader = new BufferedReader(new InputStreamReader(fis, srcEncoding));
			char[] block = new char[512];
			while (true) {
				int readLength = reader.read(block);
				if (readLength == -1)
					break;// end of file
				result.append(block, 0, readLength);
			}
			return result.toString();
		} catch (FileNotFoundException fe) {
			log.error("Error", fe);
			throw fe;
		} catch (IOException e) {
			log.error("Error", e);
			throw e;
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (IOException ex) {
			}
		}
	}

	/*
	 * 1 ABC 2 abC Gia su doc tu dong 1 lay ca thay 5 dong => 1 --> 5 3 ABC
	 */
	public static List<String> getLastLines(File file, int fromLine)
			throws IOException, FileNotFoundException {
		List<String> lines = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(file));
		int lineCount = 0;
		String lineString = "";
		while (null != (lineString = br.readLine())) {
			lineCount++;
			if (fromLine <= lineCount) {
				lines.add(lineString);
			}
		}
		br.close();
		return lines;
	}

	/**
	 * 单个文件拷贝。
	 * 
	 * @param srcFilename
	 * @param destFilename
	 * @param overwrite
	 * @throws IOException
	 */
	public static void copyFile(String srcFilename, String destFilename,
			boolean overwrite) throws IOException {
		File srcFile = new File(srcFilename);
		// 首先判断源文件是否存在
		if (!srcFile.exists()) {
			throw new FileNotFoundException("Cannot find the source file: "
					+ srcFile.getAbsolutePath());
		}
		// 判断源文件是否可读
		if (!srcFile.canRead()) {
			throw new IOException("Cannot read the source file: "
					+ srcFile.getAbsolutePath());
		}
		File destFile = new File(destFilename);
		if (overwrite == false) {
			// 目标文件存在就不覆盖
			if (destFile.exists())
				return;
		} else {
			// 如果要覆盖已经存在的目标文件，首先判断是否目标文件可写。
			if (destFile.exists()) {
				if (!destFile.canWrite()) {
					throw new IOException("Cannot write the destination file: "
							+ destFile.getAbsolutePath());
				}
			} else {
				// 不存在就创建一个新的空文件。
				if (!destFile.createNewFile()) {
					throw new IOException("Cannot write the destination file: "
							+ destFile.getAbsolutePath());
				}
			}
		}
		BufferedInputStream inputStream = null;
		BufferedOutputStream outputStream = null;
		byte[] block = new byte[1024];
		try {
			inputStream = new BufferedInputStream(new FileInputStream(srcFile));
			outputStream = new BufferedOutputStream(new FileOutputStream(
					destFile));
			while (true) {
				int readLength = inputStream.read(block);
				if (readLength == -1)
					break;// end of file
				outputStream.write(block, 0, readLength);
			}
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException ex) {
					// just ignore
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException ex) {
					// just ignore
				}
			}
		}
	}

	/**
	 * 单个文件拷贝。
	 * 
	 * @param srcFile
	 * @param destFile
	 * @param overwrite
	 *            是否覆盖目的文件
	 * @throws IOException
	 */
	public static void copyFile(File srcFile, File destFile, boolean overwrite)
			throws IOException {
		// 首先判断源文件是否存在
		if (!srcFile.exists()) {
			throw new FileNotFoundException("Cannot find the source file: "
					+ srcFile.getAbsolutePath());
		}
		// 判断源文件是否可读
		if (!srcFile.canRead()) {
			throw new IOException("Cannot read the source file: "
					+ srcFile.getAbsolutePath());
		}
		if (overwrite == false) {
			// 目标文件存在就不覆盖
			if (destFile.exists())
				return;
		} else {
			// 如果要覆盖已经存在的目标文件，首先判断是否目标文件可写。
			if (destFile.exists()) {
				if (!destFile.canWrite()) {
					throw new IOException("Cannot write the destination file: "
							+ destFile.getAbsolutePath());
				}
			} else {
				// 不存在就创建一个新的空文件。
				if (!destFile.createNewFile()) {
					throw new IOException("Cannot write the destination file: "
							+ destFile.getAbsolutePath());
				}
			}
		}
		BufferedInputStream inputStream = null;
		BufferedOutputStream outputStream = null;
		byte[] block = new byte[1024];
		try {
			inputStream = new BufferedInputStream(new FileInputStream(srcFile));
			outputStream = new BufferedOutputStream(new FileOutputStream(
					destFile));
			while (true) {
				int readLength = inputStream.read(block);
				if (readLength == -1)
					break;// end of file
				outputStream.write(block, 0, readLength);
			}
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException ex) {
					// just ignore
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException ex) {
					// just ignore
				}
			}
		}
	}

	/**
	 * 拷贝文件，从源文件夹拷贝文件到目的文件夹。 <br>
	 * 参数源文件夹和目的文件夹，最后都不要带文件路径符号，例如：c:/aa正确，c:/aa/错误。
	 * 
	 * @param srcDirName
	 *            源文件夹名称 ,例如：c:/test/aa 或者c://test//aa
	 * @param destDirName
	 *            目的文件夹名称,例如：c:/test/aa 或者c://test//aa
	 * @param overwrite
	 *            是否覆盖目的文件夹下面的文件。
	 * @throws IOException
	 */
	public static void copyFiles(String srcDirName, String destDirName,
			boolean overwrite) throws IOException {
		File srcDir = new File(srcDirName);// 声明源文件夹
		// 首先判断源文件夹是否存在
		if (!srcDir.exists()) {
			throw new FileNotFoundException(
					"Cannot find the source directory: "
							+ srcDir.getAbsolutePath());
		}
		File destDir = new File(destDirName);
		if (overwrite == false) {
			if (destDir.exists()) {
				// do nothing
			} else {
				if (destDir.mkdirs() == false) {
					throw new IOException(
							"Cannot create the destination directories = "
									+ destDir);
				}
			}
		} else {
			// 覆盖存在的目的文件夹
			if (destDir.exists()) {
				// do nothing
			} else {
				// create a new directory
				if (destDir.mkdirs() == false) {
					throw new IOException(
							"Cannot create the destination directories = "
									+ destDir);
				}
			}
		}
		// 循环查找源文件夹目录下面的文件（屏蔽子文件夹），然后将其拷贝到指定的目的文件夹下面。
		File[] srcFiles = srcDir.listFiles();
		if (srcFiles == null || srcFiles.length < 1) {
			// throw new IOException ("Cannot find any file from source
			// directory!!!");
			return;// do nothing
		}
		// 开始复制文件
		int SRCLEN = srcFiles.length;
		for (int i = 0; i < SRCLEN; i++) {
			// File tempSrcFile = srcFiles[i];
			File destFile = new File(destDirName + File.separator
					+ srcFiles[i].getName());
			// 注意构造文件对象时候，文件名字符串中不能包含文件路径分隔符";".
			// log.debug(destFile);
			if (srcFiles[i].isFile()) {
				copyFile(srcFiles[i], destFile, overwrite);
			} else {
				// 在这里进行递归调用，就可以实现子文件夹的拷贝
				copyFiles(srcFiles[i].getAbsolutePath(), destDirName
						+ File.separator + srcFiles[i].getName(), overwrite);
			}
		}
	}

	/**
	 * 压缩文件。注意：中文文件名称和中文的评论会乱码。
	 * 
	 * @param srcFilename
	 * @param destFilename
	 * @param overwrite
	 * @throws IOException
	 */
	public static void zipFile(String srcFilename, String destFilename,
			boolean overwrite) throws IOException {

		File srcFile = new File(srcFilename);
		// 首先判断源文件是否存在
		if (!srcFile.exists()) {
			throw new FileNotFoundException("Cannot find the source file: "
					+ srcFile.getAbsolutePath());
		}
		// 判断源文件是否可读
		if (!srcFile.canRead()) {
			throw new IOException("Cannot read the source file: "
					+ srcFile.getAbsolutePath());
		}
		if (destFilename == null || destFilename.trim().equals("")) {
			destFilename = srcFilename + ".zip";
		} else {
			destFilename += ".zip";
		}
		File destFile = new File(destFilename);
		if (overwrite == false) {
			// 目标文件存在就不覆盖
			if (destFile.exists())
				return;
		} else {
			// 如果要覆盖已经存在的目标文件，首先判断是否目标文件可写。
			if (destFile.exists()) {
				if (!destFile.canWrite()) {
					throw new IOException("Cannot write the destination file: "
							+ destFile.getAbsolutePath());
				}
			} else {
				// 不存在就创建一个新的空文件。
				if (!destFile.createNewFile()) {
					throw new IOException("Cannot write the destination file: "
							+ destFile.getAbsolutePath());
				}
			}
		}
		BufferedInputStream inputStream = null;
		BufferedOutputStream outputStream = null;
		ZipOutputStream zipOutputStream = null;
		byte[] block = new byte[1024];
		try {
			inputStream = new BufferedInputStream(new FileInputStream(srcFile));
			outputStream = new BufferedOutputStream(new FileOutputStream(
					destFile));
			zipOutputStream = new ZipOutputStream(outputStream);

			zipOutputStream.setComment("通过java程序压缩的");
			ZipEntry zipEntry = new ZipEntry(srcFile.getName());
			zipEntry.setComment(" zipEntry通过java程序压缩的");
			zipOutputStream.putNextEntry(zipEntry);
			while (true) {
				int readLength = inputStream.read(block);
				if (readLength == -1)
					break;// end of file
				zipOutputStream.write(block, 0, readLength);
			}
			zipOutputStream.flush();
			zipOutputStream.finish();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException ex) {
					// just ignore
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException ex) {
					// just ignore
				}
			}
			if (zipOutputStream != null) {
				try {
					zipOutputStream.close();
				} catch (IOException ex) {
					// just ignore
				}
			}
		}
	}

	public static boolean isExistFile(String dir) {
		boolean isExist = false;
		File fileDir = new File(dir);
		if (fileDir.isDirectory()) {
			File[] files = fileDir.listFiles();
			if (files != null && files.length != 0) {
				isExist = true;
			}
		}
		return isExist;
	}

	/**
	 * 根据目录取得文件列表
	 * 
	 * @param path
	 * @return
	 */
	public static File[] getFiles(String path) {
		File file = new File(path);
		return file.listFiles();
	}

	@SuppressWarnings("resource")
	public static String getCharset(File file) {
		String charset = "GBK";
		byte[] first3Bytes = new byte[3];
		try {
			boolean checked = false;
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(file));
			bis.mark(0);
			int read = bis.read(first3Bytes, 0, 3);
			if (read == -1)
				return charset;
			if (first3Bytes[0] == (byte) 0xFF && first3Bytes[1] == (byte) 0xFE) {
				charset = "UTF-16LE";
				checked = true;
			} else if (first3Bytes[0] == (byte) 0xFE
					&& first3Bytes[1] == (byte) 0xFF) {
				charset = "UTF-16BE";
				checked = true;
			} else if (first3Bytes[0] == (byte) 0xEF
					&& first3Bytes[1] == (byte) 0xBB
					&& first3Bytes[2] == (byte) 0xBF) {
				charset = "UTF-8";
				checked = true;
			}
			bis.reset();

			if (!checked) {
				int loc = 0;
				while ((read = bis.read()) != -1) {
					loc++;
					if (read >= 0xF0)
						break;
					// 单独出现BF以下的，也算是GBK
					if (0x80 <= read && read <= 0xBF)
						break;
					if (0xC0 <= read && read <= 0xDF) {
						read = bis.read();
						if (0x80 <= read && read <= 0xBF)// 双字节 (0xC0 - 0xDF)
							// (0x80 -
							// 0xBF),也可能在GB编码内
							continue;
						else
							break;
						// 也有可能出错，但是几率较小
					} else if (0xE0 <= read && read <= 0xEF) {
						read = bis.read();
						if (0x80 <= read && read <= 0xBF) {
							read = bis.read();
							if (0x80 <= read && read <= 0xBF) {
								charset = "UTF-8";
								break;
							} else
								break;
						} else
							break;
					}
				}

			}
			bis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return charset;
	}

	public static InputStream getInputStream(String filepath) {
		File file = new File(filepath);
		String charset = getCharset(file);
		ByteArrayInputStream stream = null;
		try {
			String str = FileUtil.readFile(filepath, charset);
			stream = new ByteArrayInputStream(str.getBytes(charset));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stream;
	}

	public static byte[] getBytes(String filePath) {
		File file = new File(filePath);
		return getBytes(file);
	}

	public static byte[] getBytes(File file) {
		byte[] buffer = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer;
	}

	public static File toFile(byte[] bfile, String filePath,String fileName) {  
        BufferedOutputStream bos = null;  
        FileOutputStream fos = null;  
        File file = null;  
        try {  
            File dir = new File(filePath);  
            if(!dir.exists()&&dir.isDirectory()){//判断文件目录是否存在  
                dir.mkdirs();  
            }  
            file = new File(filePath+"\\"+fileName);  
            fos = new FileOutputStream(file);  
            bos = new BufferedOutputStream(fos);  
            bos.write(bfile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (bos != null) {  
                try {  
                    bos.close();  
                } catch (IOException e1) {  
                    e1.printStackTrace();  
                }  
            }  
            if (fos != null) {  
                try {  
                    fos.close();  
                } catch (IOException e1) {  
                    e1.printStackTrace();  
                }  
            }  
        }  
        return file;
    }	
	
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// FileUtil.createDirs("E:/fix/log/2014", false);
		FileUtil.zipFile("E:/fix/log/NT_debug.log", null, true);
		System.out.println(getFileSeparator());
		List<String> temp = FileUtil.getLastLines(new File("F:/docs/abc.txt"),
				6);
		for (String str : temp) {
			System.out.println(str);
		}
	}
}
