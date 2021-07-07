package myapp.release;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class javaFileNio {

	private static String resourceDirectory = "D://dev/javadev/workspace/2021/myapp/src/main/resources/";

	public static void main(String[] args) {
		List<Integer> nums = Arrays.asList(2, 3, 4, 5, 6, 7);
		nums.forEach(r -> {
			deleteFile(resourceDirectory, "copyTest".concat(Integer.toString(r)).concat(".txt"));
		});

		try {
			FileIoCopy(resourceDirectory.concat("copyTest1.txt"), resourceDirectory.concat("copyTest2.txt"));
			FileIoBufferCopy(resourceDirectory.concat("copyTest1.txt"), resourceDirectory.concat("copyTest3.txt"));
			FileNioFullBufferCopy(resourceDirectory.concat("copyTest1.txt"), resourceDirectory.concat("copyTest4.txt"));
			FileNioByteBufferingCopy(resourceDirectory.concat("copyTest1.txt"), resourceDirectory.concat("copyTest5.txt"));
			FileNioChannelToCopy(resourceDirectory.concat("copyTest1.txt"), resourceDirectory.concat("copyTest6.txt"));
			FileNio2Copy(resourceDirectory.concat("copyTest1.txt"), resourceDirectory.concat("copyTest7.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		nums.forEach(r -> {
			deleteFile(resourceDirectory, "copyTest".concat(Integer.toString(r)).concat(".txt"));
		});
	}

	// java.io stream full-size-buffer copy
	public static void FileIoCopy(String source, String dest) throws FileNotFoundException, IOException {
		try (FileInputStream fis = new FileInputStream(source); FileOutputStream fos = new FileOutputStream(dest)) {
			int availableLen = fis.available();
			byte[] buf = new byte[availableLen];
			fis.read(buf);
			fos.write(buf);
		}
	}

	// java.io stream buffering copy
	public static void FileIoBufferCopy(String source, String dest) throws IOException {
		try (FileInputStream fis = new FileInputStream(source); FileOutputStream fos = new FileOutputStream(dest);) {
			byte[] buf = new byte[1024];
			int read;
			while ((read = fis.read(buf)) != -1) {
				fos.write(buf, 0, read);
			}
		}
	}

	// java.nio full-size-buffer copy
	public static void FileNioFullBufferCopy(String source, String dest) throws IOException {
		try (FileInputStream fis = new FileInputStream(source); FileOutputStream fos = new FileOutputStream(dest); FileChannel isbc = fis.getChannel(); FileChannel ogbc = fos.getChannel();) {
			ByteBuffer buf = ByteBuffer.allocateDirect(fis.available());
			isbc.read(buf);
			buf.flip();
			ogbc.write(buf);
		}
	}

	// java.nio bytebuffering copy
	public static void FileNioByteBufferingCopy(String source, String dest) throws IOException {
		try (FileInputStream fis = new FileInputStream(source); FileOutputStream fos = new FileOutputStream(dest); FileChannel isbc = fis.getChannel(); FileChannel ogbc = fos.getChannel();) {
			ByteBuffer buf = ByteBuffer.allocateDirect(1024);
			while (isbc.read(buf) != -1) {
				buf.flip();
				ogbc.write(buf);
				buf.clear();
			}
		}
	}

	// java.nio channel copy
	public static void FileNioChannelToCopy(String source, String dest) throws IOException {
		try (FileInputStream fis = new FileInputStream(source); FileOutputStream fos = new FileOutputStream(dest); FileChannel fic = fis.getChannel(); FileChannel foc = fos.getChannel();) {
			fic.transferTo(0, fic.size(), foc);
		}
	}

	// java.nio2 copy
	public static void FileNio2Copy(String source, String dest) throws IOException {
		Files.copy(new File(source).toPath(), new File(dest).toPath());
	}

	// java.nio2 delete
	public static boolean deleteFile(String path, String name) {
		Path filePath = FileSystems.getDefault().getPath(path, name);
		try {
			Files.delete(filePath);
		} catch (IOException | SecurityException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
