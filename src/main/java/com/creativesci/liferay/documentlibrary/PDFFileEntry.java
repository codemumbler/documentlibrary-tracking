package com.creativesci.liferay.documentlibrary;

import com.liferay.portal.kernel.repository.model.FileEntry;

public class PDFFileEntry {

	private FileEntry fileEntry;

	PDFFileEntry(){}

	PDFFileEntry(final FileEntry fileEntry) {
		this.fileEntry = fileEntry;
	}

	public String getTitle() {
		return this.fileEntry.getTitle();
	}
}
