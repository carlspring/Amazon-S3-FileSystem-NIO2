package com.upplication.s3fs.util;

import java.io.IOException;
import java.nio.file.Path;

public class MockBucket {
	private AmazonS3ClientMock amazonS3ClientMock;
	private Path mocketPath;

	public MockBucket(AmazonS3ClientMock amazonS3ClientMock, Path mocketPath) {
		this.amazonS3ClientMock = amazonS3ClientMock;
		this.mocketPath = mocketPath;
	}
	
	public MockBucket file(String... file) throws IOException {
		for (String string : file)
			amazonS3ClientMock.addFile(mocketPath, string);
		return this;
	}
	
	public MockBucket file(String file, byte[] content) throws IOException {
		amazonS3ClientMock.addFile(mocketPath, file, content);
		return this;
	}
	
	public MockBucket dir(String... dir) throws IOException {
		for (String string : dir)
			amazonS3ClientMock.addDirectory(mocketPath, string);
		return this;
	}

	public Path resolve(String file) {
		return mocketPath.resolve(file.replaceAll("/", "%2F"));
	}
}