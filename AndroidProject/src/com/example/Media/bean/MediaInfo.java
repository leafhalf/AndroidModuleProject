package com.example.Media.bean;

import java.util.Map;

/**
 * 
 * @author Lin
 * 
 */
public class MediaInfo {
	/**
	 * The data stream for the file
	 */
	private String DATA;
	/**
	 * The time the file was added to the media provider Units are seconds since
	 * 1970
	 */
	private String DATA_ADDED;
	/**
	 * The time the file was last modified Units are seconds since 1970
	 */
	private String DATA_MODIFIED;
	/**
	 * The display name of the file
	 */
	private String DISPLAY_NAME;
	/**
	 * The height of the image/video in pixels
	 */
	private String HEIGHT;
	/**
	 * The MIME type of the file
	 */
	private String MIME_TYPE;
	/**
	 * The size of the file in bytes
	 */
	private String SIZE;
	/**
	 * The title of the content
	 */
	private String TITLE;
	/**
	 * The width of the image/video in pixels.
	 */
	private String WIDTH;
	/**
	 * The duration of the video file, in ms
	 */
	private String DURATION;
	/**
	 * http or https request parameter
	 */
	private Map<String, String> HEADER;

	public static final int TYPE_URI = 0;
	public static final int TYPE_ID = 1;

	private int CUR_TYPE = 0;

	public String getDATA() {
		return DATA;
	}

	public void setDATA(String dATA) {
		DATA = dATA;
	}

	public String getDATA_ADDED() {
		return DATA_ADDED;
	}

	public void setDATA_ADDED(String dATA_ADDED) {
		DATA_ADDED = dATA_ADDED;
	}

	public String getDATA_MODIFIED() {
		return DATA_MODIFIED;
	}

	public void setDATA_MODIFIED(String dATA_MODIFIED) {
		DATA_MODIFIED = dATA_MODIFIED;
	}

	public String getDISPLAY_NAME() {
		return DISPLAY_NAME;
	}

	public void setDISPLAY_NAME(String dISPLAY_NAME) {
		DISPLAY_NAME = dISPLAY_NAME;
	}

	public String getHEIGHT() {
		return HEIGHT;
	}

	public void setHEIGHT(String hEIGHT) {
		HEIGHT = hEIGHT;
	}

	public String getMIME_TYPE() {
		return MIME_TYPE;
	}

	public void setMIME_TYPE(String mIME_TYPE) {
		MIME_TYPE = mIME_TYPE;
	}

	public String getSIZE() {
		return SIZE;
	}

	public void setSIZE(String sIZE) {
		SIZE = sIZE;
	}

	public String getTITLE() {
		return TITLE;
	}

	public void setTITLE(String tITLE) {
		TITLE = tITLE;
	}

	public String getWIDTH() {
		return WIDTH;
	}

	public void setWIDTH(String wIDTH) {
		WIDTH = wIDTH;
	}

	public String getDURATION() {
		return DURATION;
	}

	public void setDURATION(String dURATION) {
		DURATION = dURATION;
	}

	public Map<String, String> getHEADER() {
		return HEADER;
	}

	public void setHEADER(Map<String, String> hEADER) {
		HEADER = hEADER;
	}

	public int getCUR_TYPE() {
		return CUR_TYPE;
	}

	public void setCUR_TYPE(int cUR_TYPE) {
		CUR_TYPE = cUR_TYPE;
	}

}
