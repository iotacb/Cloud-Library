package xyz.iotacb.cloud.utilities.lists;

import java.util.ArrayList;
import java.util.Collection;

public class CloudList<E> extends ArrayList<E> {
	
    public CloudList(Collection<? extends E> c) {
    	ArrayList<E> list = new ArrayList<E>(c);
    	addAll(list);
    }
    
    public CloudList() {}

	private static final long serialVersionUID = 1L;
}
