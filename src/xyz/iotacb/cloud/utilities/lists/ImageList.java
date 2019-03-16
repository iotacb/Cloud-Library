package xyz.iotacb.cloud.utilities.lists;

import xyz.iotacb.cloud.utilities.rendering.Image;

public class ImageList {
	
	Image[] imageList = {};
	
	public ImageList(final Image...images) {
		this.imageList = images;
	}
	
	public Image[] getImageList() {
		return imageList;
	}
	
	public int getCount() {
		return imageList.length;
	}

}
