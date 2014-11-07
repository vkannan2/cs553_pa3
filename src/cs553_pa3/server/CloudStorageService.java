package cs553_pa3.server;

import java.io.IOException;
import java.nio.ByteBuffer;

import com.google.appengine.tools.cloudstorage.GcsFileMetadata;
import com.google.appengine.tools.cloudstorage.GcsFileOptions;
import com.google.appengine.tools.cloudstorage.GcsFilename;
import com.google.appengine.tools.cloudstorage.GcsInputChannel;
import com.google.appengine.tools.cloudstorage.GcsOutputChannel;
import com.google.appengine.tools.cloudstorage.GcsService;
import com.google.appengine.tools.cloudstorage.GcsServiceFactory;

public class CloudStorageService {
	private final GcsService gcsService =
		      GcsServiceFactory.createGcsService();
	
	public void writeToFile(GcsFilename fileName, byte[] content) throws IOException {
	    GcsOutputChannel outputChannel =
	        gcsService.createOrReplace(fileName, GcsFileOptions.getDefaultInstance());
	    outputChannel.write(ByteBuffer.wrap(content));
	    outputChannel.close();
	  }
	
	public byte[] readFromFile(GcsFilename fileName) throws IOException {
	    int fileSize = (int) gcsService.getMetadata(fileName).getLength();
	    ByteBuffer result = ByteBuffer.allocate(fileSize);
	    GcsInputChannel readChannel = gcsService.openReadChannel(fileName, 0); 
	    readChannel.read(result);
	    readChannel.close();
	    return result.array();
	  }
	
	public boolean checkForFile(GcsFilename fileName) throws IOException{
		boolean result = false;
		GcsFileMetadata gfm = gcsService.getMetadata(fileName);
		result = (gfm != null);
		return result;
	}
}
