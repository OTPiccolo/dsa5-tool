package de.otpiccolo.dsa5.pdf.data.image;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.otpiccolo.dsa5.pdf.data.IDataReader;

/**
 * Class to read an image from an URL or File.
 */
public class ImageReader implements IDataReader<String, ImageData> {

	private static final Logger LOG = LoggerFactory.getLogger(ImageReader.class);

	private static final String NOT_FOUND_IMG_RESOURCE = "/No_image_available.svg.png";

	@Override
	public ImageData readData(final String query) {
		LOG.atInfo().setMessage("Loading image from: {}").addArgument(query).log();
		final URL url = createUrlFrom(query);
		final byte[] imgData = createImageDataFrom(url);
		return new ImageData(imgData);
	}

	private URL createUrlFrom(final String query) {
		URL url = toUrl(query);
		if (url != null) {
			return url;
		}

		url = toFileUrl(query);
		if (url != null) {
			return url;
		}

		LOG.atError().setMessage("Could not load image from \"{}\". Either not an URL, or file is not readable.");
		return toNotFoundUrl(NOT_FOUND_IMG_RESOURCE);
	}

	private byte[] createImageDataFrom(final URL imgUrl) {
		final byte[] imgData = readImage(imgUrl);
		if (imgData != null) {
			return imgData;
		}

		return readImage(toNotFoundUrl(NOT_FOUND_IMG_RESOURCE));
	}

	private byte[] readImage(final URL imgUrl) {
		try (InputStream is = new BufferedInputStream(imgUrl.openStream())) {
			return is.readAllBytes();
		} catch (final IOException e) {
			LOG.atError().setMessage("Error reading image data. Error message: {}").addArgument(e.getMessage()).setCause(e).log();
			return null;
		}
	}

	private URL toUrl(final String query) {
		try {
			return new URL(query);
		} catch (final MalformedURLException e) {
			LOG.atDebug().setMessage("Query \"{}\" is not an URL. Error message: {}").addArgument(query).addArgument(e.getMessage()).setCause(e).log();
			return null;
		}
	}

	private URL toFileUrl(final String query) {
		try {
			return new File(query).toURI().toURL();
		} catch (final MalformedURLException e) {
			LOG.atDebug().setMessage("Query \"{}\" is not a file. Error message: {}").addArgument(query).addArgument(e.getMessage()).setCause(e).log();
			return null;
		}
	}

	private URL toNotFoundUrl(final String query) {
		return getClass().getResource(query);
	}

}
