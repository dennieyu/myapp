package myapp.release;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;

public class javaFileNio {

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

}
