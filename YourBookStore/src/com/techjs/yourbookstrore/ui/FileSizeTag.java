package com.techjs.yourbookstrore.ui;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class FileSizeTag extends SimpleTagSupport {
	private Long size;

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}
	
	@Override
	public void doTag() throws JspException, IOException {
		// Size = Bytes, s = Kilobytes
		double s = size/2014;
		if (s > 1014) {
			s = s / 1024; // convert to Mb
			getJspContext().getOut().println(String.format("%.2fMB", s));
		}
		else {
			getJspContext().getOut().println(String.format("%.2fKB", s));
		}
	}
}
