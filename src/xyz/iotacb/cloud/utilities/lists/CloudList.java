package xyz.iotacb.cloud.utilities.lists;

import java.util.Collection;

public class CloudList<E> extends GlueList<E> {
	
	/**
	 * Copy the contents of a other list
	 * @param c
	 */
    public CloudList(Collection<? extends E> c) {
    	GlueList<E> list = new GlueList<E>(c);
    	addAll(list);
    }
    
    public CloudList() {}
    
    /**
     * Get the last item on the list
     * @return
     */
    public E getLast() {
    	return get(size - 1);
    }
    
	private static final long serialVersionUID = 1L;
}
