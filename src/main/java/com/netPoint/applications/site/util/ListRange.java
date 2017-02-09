package com.netPoint.applications.site.util;

import java.util.List;

/**
 * Object permettant d'encapsuler une liste de d'objects complexe. Est utilis�e dans les m�thodes AJAX
 * pour remonter une partie d'une liste d'object ainsi que la taille totale
 * @author S.LASCAR
 *
 */
public class ListRange {
	private Object[] data;
	private int totalSize;

	/**
	 * Construit le listeRange � partir d'une partie d'une liste.
	 * @param totalSize
	 * @param list
	 * @param start: index du d�but de la liste
	 * @param count: nombre d'�l�ments � garder
	 */
	@SuppressWarnings("unchecked")
	public ListRange(int totalSize, List list, int start, int count) {
		this.totalSize = totalSize;
		data = list.subList(start, (start + count < list.size()) ? start + count : list.size()).toArray();
	}
	
	
	@SuppressWarnings("unchecked")
	public ListRange(int totalSize, List list) {
		this.totalSize = totalSize;
		data = list.toArray();
	}
	
	public ListRange() { }
	
	public Object[] getData() {
		return data;
	}

	public void setData(Object[] data) {
		this.data = data;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
}