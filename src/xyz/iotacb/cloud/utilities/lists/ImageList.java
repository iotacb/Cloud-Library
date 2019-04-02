package xyz.iotacb.cloud.utilities.lists;

import xyz.iotacb.cloud.utilities.rendering.Image;

public class ImageList {
	
	Image[] imageList = {};
	
	public ImageList(final Image...images) {
		this.imageList = images;
	}
	
	/**
	 * This will return the array of images
	 * @return
	 */
	public Image[] getImageList() {
		return imageList;
	}
	
	/**
	 * Get the amount of all images in the image list
	 * @return
	 */
	public int getCount() {
		return imageList.length;
	}

}
